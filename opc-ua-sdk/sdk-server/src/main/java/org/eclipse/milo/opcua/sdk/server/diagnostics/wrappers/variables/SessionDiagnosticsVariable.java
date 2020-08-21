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
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class SessionDiagnosticsVariable extends AbstractLifecycle {

    private final OpcUaServer server;

    private final SessionDiagnosticsVariableTypeNode node;
    private final Session session;

    public SessionDiagnosticsVariable(SessionDiagnosticsVariableTypeNode node, Session session) {
        this.node = node;
        this.session = session;

        this.server = node.getNodeContext().getServer();
    }

    public SessionDiagnosticsVariableTypeNode getNode() {
        return node;
    }

    public Session getSession() {
        return session;
    }

    @Override
    protected void onStartup() {
        node.getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject xo = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics()
                    .getSessionDiagnosticsDataType()
            );
            return new DataValue(new Variant(xo));
        }));

        node.getSessionIdNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            NodeId value = session.getSessionDiagnostics().getSessionId();
            return new DataValue(new Variant(value));
        }));
        node.getSessionNameNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            String value = session.getSessionDiagnostics().getSessionName();
            return new DataValue(new Variant(value));
        }));
        node.getClientDescriptionNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getClientDescription()
            );
            return new DataValue(new Variant(value));
        }));
        node.getServerUriNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            String value = session.getSessionDiagnostics().getServerUri();
            return new DataValue(new Variant(value));
        }));
        node.getEndpointUrlNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            String value = session.getSessionDiagnostics().getEndpointUrl();
            return new DataValue(new Variant(value));
        }));
        node.getLocaleIdsNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            String[] value = session.getSessionDiagnostics().getLocaleIds();
            return new DataValue(new Variant(value));
        }));
        node.getActualSessionTimeoutNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            Double value = session.getSessionDiagnostics().getActualSessionTimeout();
            return new DataValue(new Variant(value));
        }));
        node.getMaxResponseMessageSizeNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = session.getSessionDiagnostics().getMaxResponseMessageSize();
            return new DataValue(new Variant(value));
        }));
        node.getClientConnectionTimeNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            DateTime value = session.getSessionDiagnostics().getClientConnectionTime();
            return new DataValue(new Variant(value));
        }));
        node.getClientLastContactTimeNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            DateTime value = session.getSessionDiagnostics().getClientLastContactTime();
            return new DataValue(new Variant(value));
        }));
        node.getCurrentSubscriptionsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = session.getSessionDiagnostics().getCurrentSubscriptionsCount();
            return new DataValue(new Variant(value));
        }));
        node.getCurrentMonitoredItemsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = session.getSessionDiagnostics().getCurrentMonitoredItemsCount();
            return new DataValue(new Variant(value));
        }));
        node.getCurrentPublishRequestsInQueueNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = session.getSessionDiagnostics().getCurrentPublishRequestsInQueue();
            return new DataValue(new Variant(value));
        }));
        node.getTotalRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getTotalRequestCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getUnauthorizedRequestCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(session.getSessionDiagnostics().getUnauthorizedRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getReadCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getReadCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getHistoryReadCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getHistoryReadCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getWriteCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getWriteCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getHistoryUpdateCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getHistoryUpdateCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getCallCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getCallCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getCreateMonitoredItemsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getCreateMonitoredItemsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getModifyMonitoredItemsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getModifyMonitoredItemsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getSetMonitoringModeCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getSetMonitoringModeCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getSetTriggeringCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getSetTriggeringCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getDeleteMonitoredItemsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getDeleteMonitoredItemsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getCreateSubscriptionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getCreateSubscriptionCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getModifySubscriptionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getModifySubscriptionCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getSetPublishingModeCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getSetPublishingModeCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getPublishCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getPublishCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getRepublishCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getRepublishCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getTransferSubscriptionsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getTransferSubscriptionsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getDeleteSubscriptionsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getDeleteSubscriptionsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getAddNodesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getAddNodesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getAddReferencesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getAddReferencesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getDeleteNodesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getDeleteNodesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getDeleteReferencesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getDeleteReferencesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getBrowseCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getBrowseCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getBrowseNextCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getBrowseNextCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getTranslateBrowsePathsToNodeIdsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getTranslateBrowsePathsToNodeIdsCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getQueryFirstCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getQueryFirstCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getQueryNextCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getQueryNextCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getRegisterNodesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getRegisterNodesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
        node.getUnregisterNodesCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject value = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionDiagnostics().getUnregisterNodesCount().getServiceCounter()
            );
            return new DataValue(new Variant(value));
        }));
    }

    @Override
    protected void onShutdown() {
        node.delete();
    }

}
