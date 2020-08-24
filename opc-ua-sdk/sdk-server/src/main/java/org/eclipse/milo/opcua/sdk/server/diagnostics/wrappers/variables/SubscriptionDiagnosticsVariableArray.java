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
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionCreatedEvent;
import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionDeletedEvent;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class SubscriptionDiagnosticsVariableArray extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<SubscriptionDiagnosticsVariable> subscriptionDiagnosticsVariables =
        Collections.synchronizedList(new ArrayList<>());

    private final List<Subscription> subscriptions =
        Collections.synchronizedList(new ArrayList<>());

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;
    private final NodeManager<UaNode> nodeManager;

    private final SubscriptionDiagnosticsArrayTypeNode node;

    public SubscriptionDiagnosticsVariableArray(SubscriptionDiagnosticsArrayTypeNode node) {
        checkNotNull(node, "SubscriptionDiagnosticsArrayTypeNode");
        this.node = node;

        this.server = node.getNodeContext().getServer();
        this.nodeFactory = new NodeFactory(node.getNodeContext());
        this.nodeManager = node.getNodeManager();
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

        server.getSubscriptions().values()
            .forEach(this::createSubscriptionDiagnosticsNode);

        //noinspection UnstableApiUsage
        server.getEventBus().register(this);
    }

    @Override
    protected void onShutdown() {
        //noinspection UnstableApiUsage
        server.getEventBus().unregister(this);

        subscriptionDiagnosticsVariables.forEach(AbstractLifecycle::shutdown);
        subscriptionDiagnosticsVariables.clear();

        subscriptions.clear();

        node.delete();
    }

    @Subscribe
    public synchronized void onSubscriptionCreated(SubscriptionCreatedEvent event) {
        createSubscriptionDiagnosticsNode(event.getSubscription());

        assert subscriptions.size() == subscriptionDiagnosticsVariables.size();
    }

    @Subscribe
    public synchronized void onSubscriptionDeleted(SubscriptionDeletedEvent event) {
        assert subscriptions.size() == subscriptionDiagnosticsVariables.size();

        for (int i = 0; i < subscriptions.size(); i++) {
            if (event.getSubscription().getId().equals(subscriptions.get(i).getId())) {
                subscriptions.remove(i);
                SubscriptionDiagnosticsVariable diagnosticsVariable = subscriptionDiagnosticsVariables.remove(i);
                diagnosticsVariable.shutdown();
                break;
            }
        }

        assert subscriptions.size() == subscriptionDiagnosticsVariables.size();
    }

    private void createSubscriptionDiagnosticsNode(Subscription subscription) {
        subscriptions.add(subscription);

        try {
            int index = subscriptionDiagnosticsVariables.size();
            String name = node.getBrowseName().getName() + "[" + index + "]";
            String id = Util.buildBrowseNamePath(node) + "[" + index + "]";
            NodeId elementNodeId = node.getNodeId().withId(id);

            SubscriptionDiagnosticsTypeNode elementNode = (SubscriptionDiagnosticsTypeNode) nodeFactory.createNode(
                elementNodeId,
                Identifiers.SubscriptionDiagnosticsType
            );

            elementNode.setBrowseName(new QualifiedName(
                node.getBrowseName().getNamespaceIndex(),
                name
            ));
            elementNode.setDisplayName(new LocalizedText(
                node.getDisplayName().getLocale(),
                name
            ));
            elementNode.setArrayDimensions(null);
            elementNode.setValueRank(ValueRank.Scalar.getValue());
            elementNode.setDataType(Identifiers.SubscriptionDiagnosticsDataType);
            elementNode.setAccessLevel(AccessLevel.toValue(AccessLevel.READ_ONLY));
            elementNode.setUserAccessLevel(AccessLevel.toValue(AccessLevel.READ_ONLY));

            elementNode.addReference(new Reference(
                elementNode.getNodeId(),
                Identifiers.HasComponent,
                node.getNodeId().expanded(),
                Reference.Direction.INVERSE
            ));
            nodeManager.addNode(elementNode);

            SubscriptionDiagnosticsVariable diagnosticsVariable = new SubscriptionDiagnosticsVariable(
                server,
                elementNode,
                subscription
            );
            diagnosticsVariable.startup();

            subscriptionDiagnosticsVariables.add(diagnosticsVariable);
        } catch (UaException e) {
            logger.error(
                "Failed to create SubscriptionDiagnosticsTypeNode for subscription id={}",
                subscription.getId(), e
            );

            subscriptions.remove(subscription);
        }
    }

}
