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

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.diagnostics.SubscriptionDiagnostics;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class SubscriptionDiagnosticsVariable extends AbstractLifecycle {

    private final OpcUaServer server;
    private final SubscriptionDiagnosticsTypeNode node;
    private final Subscription subscription;

    public SubscriptionDiagnosticsVariable(
        OpcUaServer server,
        SubscriptionDiagnosticsTypeNode node,
        Subscription subscription
    ) {

        checkNotNull(node, "SubscriptionDiagnosticsTypeNode");

        this.server = server;
        this.node = node;
        this.subscription = subscription;
    }

    @Override
    protected void onStartup() {
        SubscriptionDiagnostics subscriptionDiagnostics = subscription.getSubscriptionDiagnostics();

        node.getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject xo = ExtensionObject.encode(
                server.getSerializationContext(),
                subscriptionDiagnostics
                    .getSubscriptionDiagnosticsDataType()
            );
            return new DataValue(new Variant(xo));
        }));
        node.getSessionIdNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            NodeId value = subscriptionDiagnostics.getSessionId();
            return new DataValue(new Variant(value));
        }));
        node.getSubscriptionIdNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = subscriptionDiagnostics.getSubscriptionId();
            return new DataValue(new Variant(value));
        }));
        node.getPriorityNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UByte value = subscriptionDiagnostics.getPriority();
            return new DataValue(new Variant(value));
        }));
        node.getPublishingIntervalNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            Double value = subscriptionDiagnostics.getPublishingInterval();
            return new DataValue(new Variant(value));
        }));
        node.getMaxKeepAliveCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = subscriptionDiagnostics.getMaxKeepAliveCount();
            return new DataValue(new Variant(value));
        }));
        node.getMaxLifetimeCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = subscriptionDiagnostics.getMaxLifetimeCount();
            return new DataValue(new Variant(value));
        }));
        node.getMaxNotificationsPerPublishNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = subscriptionDiagnostics.getMaxNotificationsPerPublish();
            return new DataValue(new Variant(value));
        }));
        node.getPublishingEnabledNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            Boolean value = subscriptionDiagnostics.isPublishingEnabled();
            return new DataValue(new Variant(value));
        }));
        node.getModifyCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getModifyCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getEnableCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getEnableCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getDisableCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getDisableCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getRepublishRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getRepublishRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getRepublishMessageRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getRepublishMessageRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getRepublishMessageCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getRepublishMessageCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getTransferRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getTransferRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getTransferredToAltClientCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getTransferredToAltClientCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getTransferredToSameClientCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getTransferredToSameClientCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getPublishRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getPublishRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getDataChangeNotificationsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getDataChangeNotificationsCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getEventNotificationsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getEventNotificationsCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getNotificationsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getNotificationsCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getLatePublishRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getLatePublishRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCurrentKeepAliveCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getCurrentKeepAliveCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCurrentLifetimeCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getCurrentLifetimeCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getUnacknowledgedMessageCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getUnacknowledgedMessageCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getDiscardedMessageCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getDiscardedMessageCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getMonitoredItemCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getMonitoredItemCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getDisabledMonitoredItemCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getDisabledMonitoredItemCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getMonitoringQueueOverflowCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getMonitoringQueueOverflowCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getNextSequenceNumberNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getNextSequenceNumber().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getEventQueueOverFlowCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(subscriptionDiagnostics.getEventQueueOverFlowCount().longValue());
            return new DataValue(new Variant(value));
        }));
    }

    @Override
    protected void onShutdown() {
        node.delete();
    }

}
