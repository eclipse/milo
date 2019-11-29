/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class DiagnosticsManager extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(DiagnosticsManager.class);

    private final List<Runnable> diagnosticTasks =
        Collections.synchronizedList(new ArrayList<>());

    private ServerDiagnosticsObject serverDiagnosticsObject;

    private long updateRateMillis = 1000;

    private ScheduledFuture<?> updateTasksFuture;

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;
    private final UaNodeManager nodeManager;

    public DiagnosticsManager(OpcUaServer server, NodeFactory nodeFactory, UaNodeManager nodeManager) {
        this.server = server;
        this.nodeFactory = nodeFactory;
        this.nodeManager = nodeManager;
    }

    public OpcUaServer getServer() {
        return server;
    }

    @Override
    protected void onStartup() {
        ServerDiagnosticsTypeNode serverDiagnosticsNode = (ServerDiagnosticsTypeNode) getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        serverDiagnosticsNode.getEnabledFlagNode().getFilterChain().addLast(
            AttributeFilters.getValue(
                ctx ->
                    new DataValue(new Variant(isDiagnosticsEnabled()))
            ),
            AttributeFilters.setValue(
                (ctx, value) ->
                    setDiagnosticsEnabled((boolean) value.getValue().getValue())
            )
        );
    }

    @Override
    protected void onShutdown() {
        if (updateTasksFuture != null) {
            updateTasksFuture.cancel(false);
            updateTasksFuture = null;
        }

        if (serverDiagnosticsObject != null) {
            serverDiagnosticsObject.shutdown();
            serverDiagnosticsObject = null;
        }
    }

    /**
     * Enable or disable periodic diagnostics collection and updating.
     *
     * @param diagnosticsEnabled {@code true} if diagnostics collection and updating should be enabled.
     */
    public synchronized void setDiagnosticsEnabled(boolean diagnosticsEnabled) {
        if (diagnosticsEnabled) {
            if (updateTasksFuture == null) {
                if (serverDiagnosticsObject != null) {
                    serverDiagnosticsObject.shutdown();
                }

                ServerDiagnosticsTypeNode serverDiagnosticsNode = (ServerDiagnosticsTypeNode) getServer()
                    .getAddressSpaceManager()
                    .getManagedNode(Identifiers.Server_ServerDiagnostics)
                    .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

                serverDiagnosticsObject = new ServerDiagnosticsObject(serverDiagnosticsNode);
                serverDiagnosticsObject.startup();

                updateTasksFuture = Stack.sharedScheduledExecutor().scheduleWithFixedDelay(
                    this::runUpdateTasks,
                    0L,
                    getUpdateRate(),
                    TimeUnit.MILLISECONDS
                );
            }
        } else {
            // Cancel the updates but don't shut down ServerDiagnosticsObject so the nodes
            // remain in the address space. When diagnostics are enabled again it will be
            // cleaned up and started again.
            if (updateTasksFuture != null) {
                updateTasksFuture.cancel(false);
                updateTasksFuture = null;
            }
        }
    }

    /**
     * @return {@code true} if diagnostics collection and updating is enabled.
     */
    public synchronized boolean isDiagnosticsEnabled() {
        return updateTasksFuture != null;
    }

    /**
     * Set rate at which the diagnostics nodes are updated with the latest diagnostic information.
     *
     * @param updateRateMillis the update rate, in milliseconds.
     * @throws IllegalArgumentException if update rate is <= 0.
     */
    public synchronized void setUpdateRate(long updateRateMillis) {
        Preconditions.checkArgument(updateRateMillis > 0, "update rate must be > 0");

        this.updateRateMillis = updateRateMillis;

        if (updateTasksFuture != null) {
            updateTasksFuture.cancel(false);

            updateTasksFuture = Stack.sharedScheduledExecutor().scheduleWithFixedDelay(
                () -> Stack.sharedExecutor().execute(this::runUpdateTasks),
                0L,
                updateRateMillis,
                TimeUnit.MILLISECONDS
            );
        }
    }

    /**
     * Get the rate which the diagnostics nodes are currently being updated.
     *
     * @return the update rate, in milliseconds.
     */
    public synchronized long getUpdateRate() {
        return updateRateMillis;
    }

    private void runUpdateTasks() {
        synchronized (diagnosticTasks) {
            diagnosticTasks.forEach(r -> {
                try {
                    r.run();
                } catch (Throwable t) {
                    logger.warn("Uncaught exception running diagnostic task", t);
                }
            });
        }
    }

    class ServerDiagnosticsObject extends AbstractLifecycle {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final List<Runnable> registeredTasks = Collections.synchronizedList(new ArrayList<>());

        private SessionsDiagnosticsSummaryObject sessionsDiagnosticsSummaryObject;

        private final ServerDiagnosticsTypeNode node;

        ServerDiagnosticsObject(ServerDiagnosticsTypeNode node) {
            checkNotNull(node, "ServerDiagnosticsTypeNode");
            this.node = node;
        }

        @Override
        protected void onStartup() {
            logger.debug("ServerDiagnosticsNode onStartup()");

            configureServerDiagnosticsSummary();

            configureSubscriptionDiagnosticsArray();

            sessionsDiagnosticsSummaryObject = new SessionsDiagnosticsSummaryObject(
                node.getSessionsDiagnosticsSummaryNode()
            );
            sessionsDiagnosticsSummaryObject.startup();
        }

        @Override
        protected void onShutdown() {
            logger.debug("ServerDiagnosticsNode onShutdown()");

            diagnosticTasks.removeAll(registeredTasks);
            registeredTasks.clear();

            if (sessionsDiagnosticsSummaryObject != null) {
                sessionsDiagnosticsSummaryObject.shutdown();
                sessionsDiagnosticsSummaryObject = null;
            }

            // The ServerDiagnosticsTypeNode is not deleted because it
            // should be present whether or not diagnostics are enabled.
        }

        private void configureSubscriptionDiagnosticsArray() {
            SubscriptionDiagnosticsArrayTypeNode subscriptionDiagnosticsArrayNode =
                node.getSubscriptionDiagnosticsArrayNode();

            subscriptionDiagnosticsArrayNode.getFilterChain().addLast(
                new ArrayValueAttributeFilter(Identifiers.SubscriptionDiagnosticsType)
            );

            Runnable updateTask = () -> {
                ExtensionObject[] xos = getServer().getSubscriptions()
                    .values()
                    .stream()
                    .map(s ->
                        ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            s.getSubscriptionDiagnostics()
                                .getSubscriptionDiagnosticsDataType()
                        )
                    )
                    .toArray(ExtensionObject[]::new);

                subscriptionDiagnosticsArrayNode.setValue(new DataValue(new Variant(xos)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

        private void configureServerDiagnosticsSummary() {
            ServerDiagnosticsSummaryTypeNode serverDiagnosticsSummaryNode = node.getServerDiagnosticsSummaryNode();

            serverDiagnosticsSummaryNode.getFilterChain().addLast(new ComplexValueAttributeFilter());

            Runnable updateTask = () -> {
                ExtensionObject xo = ExtensionObject.encode(
                    getServer().getSerializationContext(),
                    getServer().getDiagnosticsSummary()
                        .getServerDiagnosticsSummaryDataType()
                );

                serverDiagnosticsSummaryNode.setValue(new DataValue(new Variant(xo)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

    }

    class SessionsDiagnosticsSummaryObject extends AbstractLifecycle {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final List<Runnable> registeredTasks = Collections.synchronizedList(new ArrayList<>());

        private final Map<NodeId, SessionDiagnosticsObject> sessionDiagnosticsObjects = Maps.newConcurrentMap();

        private SessionListener sessionListener;

        private final SessionsDiagnosticsSummaryTypeNode node;

        SessionsDiagnosticsSummaryObject(SessionsDiagnosticsSummaryTypeNode node) {
            checkNotNull(node, "SessionsDiagnosticsSummaryTypeNode");
            this.node = node;
        }

        @Override
        protected void onStartup() {
            logger.debug("SessionsDiagnosticsSummaryObject onStartup()");

            configureSessionDiagnosticsArray();

            configureSessionSecurityDiagnosticsArray();

            getServer().getSessionManager().getAllSessions().forEach(session -> {
                SessionDiagnosticsObject sdo = new SessionDiagnosticsObject(session, node);

                sessionDiagnosticsObjects.put(session.getSessionId(), sdo);

                sdo.startup();
            });

            getServer().getSessionManager().addSessionListener(sessionListener = new SessionListener() {
                @Override
                public void onSessionCreated(Session session) {
                    SessionDiagnosticsObject sdo = new SessionDiagnosticsObject(session, node);

                    sessionDiagnosticsObjects.put(session.getSessionId(), sdo);

                    sdo.startup();
                }

                @Override
                public void onSessionClosed(Session session) {
                    SessionDiagnosticsObject sdo = sessionDiagnosticsObjects.remove(session.getSessionId());

                    if (sdo != null) {
                        sdo.shutdown();
                    }
                }
            });
        }

        @Override
        protected void onShutdown() {
            logger.debug("SessionsDiagnosticsSummaryObject onShutdown()");

            diagnosticTasks.removeAll(registeredTasks);
            registeredTasks.clear();

            if (sessionListener != null) {
                getServer().getSessionManager().removeSessionListener(sessionListener);
            }

            sessionDiagnosticsObjects.values()
                .forEach(SessionDiagnosticsObject::shutdown);
            sessionDiagnosticsObjects.clear();

            // The SessionsDiagnosticsSummaryTypeNode is not deleted because it
            // should be present whether or not diagnostics are enabled.
        }

        private void configureSessionSecurityDiagnosticsArray() {
            SessionSecurityDiagnosticsArrayTypeNode sessionSecurityDiagnosticsArrayNode =
                node.getSessionSecurityDiagnosticsArrayNode();

            sessionSecurityDiagnosticsArrayNode.getFilterChain().addLast(
                new ArrayValueAttributeFilter(Identifiers.SessionSecurityDiagnosticsType)
            );

            Runnable updateTask = () -> {
                ExtensionObject[] xos = sessionDiagnosticsObjects.values()
                    .stream()
                    .map(sdo ->
                        ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            sdo.session.getSessionSecurityDiagnostics()
                                .getSessionSecurityDiagnosticsDataType()
                        )
                    )
                    .toArray(ExtensionObject[]::new);

                sessionSecurityDiagnosticsArrayNode.setValue(new DataValue(new Variant(xos)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

        private void configureSessionDiagnosticsArray() {
            SessionDiagnosticsArrayTypeNode sessionDiagnosticsArrayNode = node.getSessionDiagnosticsArrayNode();

            sessionDiagnosticsArrayNode.getFilterChain().addLast(
                new ArrayValueAttributeFilter(Identifiers.SessionDiagnosticsVariableType)
            );

            Runnable updateTask = () -> {
                ExtensionObject[] xos = sessionDiagnosticsObjects.values()
                    .stream()
                    .map(sdo ->
                        ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            sdo.session.getSessionDiagnostics()
                                .getSessionDiagnosticsDataType()
                        )
                    )
                    .toArray(ExtensionObject[]::new);

                sessionDiagnosticsArrayNode.setValue(new DataValue(new Variant(xos)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

    }

    // ServerDiagnosticsType -> SessionDiagnosticsSummaryType -> SessionDiagnosticsObjectType (per session)
    class SessionDiagnosticsObject extends AbstractLifecycle {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final List<Runnable> registeredTasks = Collections.synchronizedList(new ArrayList<>());

        private SessionDiagnosticsObjectTypeNode node;

        private final Session session;
        private final SessionsDiagnosticsSummaryTypeNode summaryNode;

        SessionDiagnosticsObject(Session session, SessionsDiagnosticsSummaryTypeNode summaryNode) {
            this.session = session;
            this.summaryNode = summaryNode;
        }

        @Override
        protected void onStartup() {
            logger.debug("SessionDiagnosticsObject onStartup()");

            try {
                String name = String.format(
                    "%s (%s)",
                    session.getSessionName(),
                    session.getSessionId()
                );

                node = (SessionDiagnosticsObjectTypeNode) nodeFactory.createNode(
                    new NodeId(0, UUID.randomUUID()),
                    Identifiers.SessionDiagnosticsObjectType,
                    false
                );
                node.setBrowseName(new QualifiedName(1, name));
                node.setDisplayName(LocalizedText.english(name));

                nodeManager.addNode(node);
                summaryNode.addComponent(node);

                configureSessionDiagnostics();

                configureSessionSecurityDiagnostics();

                configureSubscriptionDiagnosticsArray();
            } catch (UaException e) {
                logger.error("Error starting SessionDiagnosticsObject: {}", e.getMessage(), e);
            }
        }

        @Override
        protected void onShutdown() {
            logger.debug("SessionDiagnosticsObject onShutdown()");

            diagnosticTasks.removeAll(registeredTasks);
            registeredTasks.clear();

            if (node != null) {
                node.delete();
                summaryNode.removeComponent(node);
            }
        }

        private void configureSessionDiagnostics() {
            SessionDiagnosticsVariableTypeNode sessionDiagnosticsNode = node.getSessionDiagnosticsNode();

            sessionDiagnosticsNode.getFilterChain().addLast(
                new ComplexValueAttributeFilter()
            );

            Runnable updateTask = () -> {
                ExtensionObject xo = ExtensionObject.encode(
                    getServer().getSerializationContext(),
                    session.getSessionDiagnostics()
                        .getSessionDiagnosticsDataType()
                );

                sessionDiagnosticsNode.setValue(new DataValue(new Variant(xo)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

        private void configureSessionSecurityDiagnostics() {
            SessionSecurityDiagnosticsTypeNode sessionSecurityDiagnosticsNode =
                node.getSessionSecurityDiagnosticsNode();

            sessionSecurityDiagnosticsNode.getFilterChain().addLast(
                new ComplexValueAttributeFilter()
            );

            Runnable updateTask = () -> {
                ExtensionObject xo = ExtensionObject.encode(
                    getServer().getSerializationContext(),
                    session.getSessionSecurityDiagnostics()
                        .getSessionSecurityDiagnosticsDataType()
                );

                sessionSecurityDiagnosticsNode.setValue(new DataValue(new Variant(xo)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

        private void configureSubscriptionDiagnosticsArray() {
            SubscriptionDiagnosticsArrayTypeNode subscriptionDiagnosticsArrayNode =
                node.getSubscriptionDiagnosticsArrayNode();

            subscriptionDiagnosticsArrayNode.getFilterChain().addLast(
                new ArrayValueAttributeFilter(Identifiers.SubscriptionDiagnosticsType)
            );

            Runnable updateTask = () -> {
                ExtensionObject[] xos = session.getSubscriptionManager()
                    .getSubscriptions()
                    .stream()
                    .map(s ->
                        ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            s.getSubscriptionDiagnostics()
                                .getSubscriptionDiagnosticsDataType()
                        )
                    )
                    .toArray(ExtensionObject[]::new);

                subscriptionDiagnosticsArrayNode.setValue(new DataValue(new Variant(xos)));
            };

            diagnosticTasks.add(updateTask);
            registeredTasks.add(updateTask);
        }

    }

}
