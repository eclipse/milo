/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SamplingIntervalDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerDiagnosticsSummaryNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class ServerDiagnosticsNode extends BaseObjectNode implements ServerDiagnosticsType {
    public ServerDiagnosticsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ServerDiagnosticsNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getEnabledFlagNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerDiagnosticsType.ENABLED_FLAG);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getEnabledFlag() {
        Optional<Boolean> propertyValue = getProperty(ServerDiagnosticsType.ENABLED_FLAG);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEnabledFlag(Boolean value) {
        setProperty(ServerDiagnosticsType.ENABLED_FLAG, value);
    }

    @Override
    public SubscriptionDiagnosticsArrayNode getSubscriptionDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray");
        return (SubscriptionDiagnosticsArrayNode) component.orElse(null);
    }

    @Override
    public SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionDiagnosticsArray");
        return component.map(node -> (SubscriptionDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value) {
        getVariableComponent("SubscriptionDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ServerDiagnosticsSummaryNode getServerDiagnosticsSummaryNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerDiagnosticsSummary");
        return (ServerDiagnosticsSummaryNode) component.orElse(null);
    }

    @Override
    public ServerDiagnosticsSummaryDataType getServerDiagnosticsSummary() {
        Optional<VariableNode> component = getVariableComponent("ServerDiagnosticsSummary");
        return component.map(node -> (ServerDiagnosticsSummaryDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType value) {
        getVariableComponent("ServerDiagnosticsSummary").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public SamplingIntervalDiagnosticsArrayNode getSamplingIntervalDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SamplingIntervalDiagnosticsArray");
        return (SamplingIntervalDiagnosticsArrayNode) component.orElse(null);
    }

    @Override
    public SamplingIntervalDiagnosticsDataType[] getSamplingIntervalDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SamplingIntervalDiagnosticsArray");
        return component.map(node -> (SamplingIntervalDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSamplingIntervalDiagnosticsArray(SamplingIntervalDiagnosticsDataType[] value) {
        getVariableComponent("SamplingIntervalDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public SessionsDiagnosticsSummaryNode getSessionsDiagnosticsSummaryNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "SessionsDiagnosticsSummary");
        return (SessionsDiagnosticsSummaryNode) component.orElse(null);
    }
}
