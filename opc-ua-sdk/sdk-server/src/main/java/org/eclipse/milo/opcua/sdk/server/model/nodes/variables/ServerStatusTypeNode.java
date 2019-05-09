/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerStatusType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;

public class ServerStatusTypeNode extends BaseDataVariableTypeNode implements ServerStatusType {
    public ServerStatusTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ServerStatusTypeNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                double minimumSamplingInterval, boolean historizing) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public BaseDataVariableTypeNode getStartTimeNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "StartTime");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public DateTime getStartTime() {
        Optional<VariableNode> component = getVariableComponent("StartTime");
        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setStartTime(DateTime value) {
        getVariableComponent("StartTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentTimeNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentTime");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public DateTime getCurrentTime() {
        Optional<VariableNode> component = getVariableComponent("CurrentTime");
        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setCurrentTime(DateTime value) {
        getVariableComponent("CurrentTime").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "State");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public ServerState getState() {
        Optional<VariableNode> component = getVariableComponent("State");
        return component.map(node -> (ServerState) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setState(ServerState value) {
        getVariableComponent("State").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BuildInfoTypeNode getBuildInfoNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BuildInfo");
        return (BuildInfoTypeNode) component.orElse(null);
    }

    @Override
    public BuildInfo getBuildInfo() {
        Optional<VariableNode> component = getVariableComponent("BuildInfo");
        return component.map(node -> (BuildInfo) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setBuildInfo(BuildInfo value) {
        getVariableComponent("BuildInfo").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getSecondsTillShutdownNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SecondsTillShutdown");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public UInteger getSecondsTillShutdown() {
        Optional<VariableNode> component = getVariableComponent("SecondsTillShutdown");
        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSecondsTillShutdown(UInteger value) {
        getVariableComponent("SecondsTillShutdown").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public BaseDataVariableTypeNode getShutdownReasonNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ShutdownReason");
        return (BaseDataVariableTypeNode) component.orElse(null);
    }

    @Override
    public LocalizedText getShutdownReason() {
        Optional<VariableNode> component = getVariableComponent("ShutdownReason");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setShutdownReason(LocalizedText value) {
        getVariableComponent("ShutdownReason").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
