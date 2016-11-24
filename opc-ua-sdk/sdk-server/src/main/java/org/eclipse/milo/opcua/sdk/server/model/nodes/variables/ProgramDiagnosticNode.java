/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:ProgramDiagnosticType")
public class ProgramDiagnosticNode extends BaseDataVariableNode implements ProgramDiagnosticType {

    public ProgramDiagnosticNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public ProgramDiagnosticNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public synchronized DataValue getValue() {
        ProgramDiagnosticDataType value = new ProgramDiagnosticDataType(
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof ProgramDiagnosticDataType) {
            ProgramDiagnosticDataType v = (ProgramDiagnosticDataType) o;

        }
    }

    @Override
    public NodeId getCreateSessionId() {
        Optional<NodeId> property = getProperty(ProgramDiagnosticType.CREATE_SESSION_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCreateSessionIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.CREATE_SESSION_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCreateSessionId(NodeId value) {
        setProperty(ProgramDiagnosticType.CREATE_SESSION_ID, value);
    }

    @Override
    public String getCreateClientName() {
        Optional<String> property = getProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCreateClientNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.CREATE_CLIENT_NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCreateClientName(String value) {
        setProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME, value);
    }

    @Override
    public DateTime getInvocationCreationTime() {
        Optional<DateTime> property = getProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getInvocationCreationTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.INVOCATION_CREATION_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setInvocationCreationTime(DateTime value) {
        setProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME, value);
    }

    @Override
    public DateTime getLastTransitionTime() {
        Optional<DateTime> property = getProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastTransitionTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_TRANSITION_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastTransitionTime(DateTime value) {
        setProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME, value);
    }

    @Override
    public String getLastMethodCall() {
        Optional<String> property = getProperty(ProgramDiagnosticType.LAST_METHOD_CALL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodCallNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodCall(String value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_CALL, value);
    }

    @Override
    public NodeId getLastMethodSessionId() {
        Optional<NodeId> property = getProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodSessionIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_SESSION_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodSessionId(NodeId value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID, value);
    }

    @Override
    public Argument[] getLastMethodInputArguments() {
        Optional<Argument[]> property = getProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodInputArgumentsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodInputArguments(Argument[] value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS, value);
    }

    @Override
    public Argument[] getLastMethodOutputArguments() {
        Optional<Argument[]> property = getProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodOutputArgumentsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodOutputArguments(Argument[] value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS, value);
    }

    @Override
    public DateTime getLastMethodCallTime() {
        Optional<DateTime> property = getProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodCallTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodCallTime(DateTime value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME, value);
    }

    @Override
    public StatusResult getLastMethodReturnStatus() {
        Optional<StatusResult> property = getProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastMethodReturnStatusNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastMethodReturnStatus(StatusResult value) {
        setProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS, value);
    }

}
