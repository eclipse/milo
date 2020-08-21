/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.eventbus.Subscribe;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class SubscriptionDiagnosticsVariableArray extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<SubscriptionDiagnosticsVariable> diagnosticsVariables =
        Collections.synchronizedList(new ArrayList<>());

    private final List<Subscription> subscriptions =
        Collections.synchronizedList(new ArrayList<>());

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;

    private final SubscriptionDiagnosticsArrayTypeNode node;

    public SubscriptionDiagnosticsVariableArray(SubscriptionDiagnosticsArrayTypeNode node) {
        checkNotNull(node, "SubscriptionDiagnosticsArrayTypeNode");
        this.node = node;

        this.server = node.getNodeContext().getServer();
        this.nodeFactory = new NodeFactory(node.getNodeContext());
    }

    @Override
    protected void onStartup() {
        node.getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject[] xos = ExtensionObject.encodeArray(
                server.getSerializationContext(),
                server.getSubscriptions()
                    .values()
                    .stream()
                    .map(s ->
                        s.getSubscriptionDiagnostics()
                            .getSubscriptionDiagnosticsDataType()
                    )
                    .toArray(SubscriptionDiagnosticsDataType[]::new)
            );
            return new DataValue(new Variant(xos));
        }));

        // TODO register subscription listener
    }

    @Override
    protected void onShutdown() {
        // TODO unregister subscription listener

        diagnosticsVariables.forEach(AbstractLifecycle::shutdown);
        diagnosticsVariables.clear();

        subscriptions.clear();

        node.delete();
    }

    @Subscribe
    public synchronized void onSubscriptionCreated(SubscriptionCreatedEvent event) {
        subscriptions.add(event.subscription);

        try {
            SubscriptionDiagnosticsTypeNode elementNode = (SubscriptionDiagnosticsTypeNode) nodeFactory.createNode(
                node.getNodeId(),
                Identifiers.SubscriptionDiagnosticsType
            );

            SubscriptionDiagnosticsVariable diagnosticsVariable = new SubscriptionDiagnosticsVariable(
                server,
                elementNode,
                event.subscription
            );
            diagnosticsVariable.startup();

            diagnosticsVariables.add(diagnosticsVariable);
        } catch (UaException e) {
            logger.error(
                "Failed to create SubscriptionDiagnosticsTypeNode for subscription id={}",
                event.subscription.getId(), e
            );

            subscriptions.remove(event.subscription);
        }

        assert subscriptions.size() == diagnosticsVariables.size();
    }

    @Subscribe
    public synchronized void onSubscriptionDeleted(SubscriptionDeletedEvent event) {
        assert subscriptions.size() == diagnosticsVariables.size();

        for (int i = 0; i < subscriptions.size(); i++) {
            if (event.subscription.getId().equals(subscriptions.get(i).getId())) {
                subscriptions.remove(i);
                SubscriptionDiagnosticsVariable diagnosticsVariable = diagnosticsVariables.remove(i);
                diagnosticsVariable.shutdown();
                break;
            }
        }

        assert subscriptions.size() == diagnosticsVariables.size();
    }

    public static class SubscriptionCreatedEvent {
        final Subscription subscription;

        public SubscriptionCreatedEvent(Subscription subscription) {
            this.subscription = subscription;
        }
    }

    public static class SubscriptionDeletedEvent {
        final Subscription subscription;

        public SubscriptionDeletedEvent(Subscription subscription) {
            this.subscription = subscription;
        }
    }

}
