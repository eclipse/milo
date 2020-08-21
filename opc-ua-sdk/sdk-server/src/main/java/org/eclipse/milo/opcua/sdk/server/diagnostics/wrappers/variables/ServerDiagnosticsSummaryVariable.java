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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ServerDiagnosticsSummaryVariable extends AbstractLifecycle {

    private final OpcUaServer server;
    private final ServerDiagnosticsSummaryTypeNode node;

    public ServerDiagnosticsSummaryVariable(OpcUaServer server, ServerDiagnosticsSummaryTypeNode node) {
        this.server = server;
        this.node = node;
    }

    @Override
    protected void onStartup() {
        node.getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject xo = ExtensionObject.encode(
                server.getSerializationContext(),
                server.getDiagnosticsSummary()
                    .getServerDiagnosticsSummaryDataType()
            );
            return new DataValue(new Variant(xo));
        }));
        node.getServerViewCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getCurrentViewCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCurrentSessionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getCurrentSessionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCumulatedSessionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getCumulatedSessionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getSecurityRejectedSessionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getSecurityRejectedSessionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getRejectedSessionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getRejectedSessionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getSessionTimeoutCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getSessionTimeoutCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getSessionAbortCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getSessionAbortCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getPublishingIntervalCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getPublishingIntervalCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCurrentSubscriptionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getCurrentSubscriptionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getCumulatedSubscriptionCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getCumulatedSubscriptionCount().longValue());
            return new DataValue(new Variant(value));
        }));
        node.getSecurityRejectedRequestsCountNode().getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            UInteger value = uint(server.getDiagnosticsSummary().getSecurityRejectedRequestCount().longValue());
            return new DataValue(new Variant(value));
        }));
    }

    @Override
    protected void onShutdown() {
        node.delete();
    }

}
