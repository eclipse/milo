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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:SessionSecurityDiagnosticsType")
public class SessionSecurityDiagnosticsNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsType {

    public SessionSecurityDiagnosticsNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeMap, nodeId, variableTypeNode);
    }

    public SessionSecurityDiagnosticsNode(
        ServerNodeMap nodeMap,
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

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public synchronized DataValue getValue() {
        SessionSecurityDiagnosticsDataType value = new SessionSecurityDiagnosticsDataType(
            getSessionId(),
            getClientUserIdOfSession(),
            getClientUserIdHistory(),
            getAuthenticationMechanism(),
            getEncoding(),
            getTransportProtocol(),
            getSecurityMode(),
            getSecurityPolicyUri(),
            getClientCertificate()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof SessionSecurityDiagnosticsDataType) {
            SessionSecurityDiagnosticsDataType v = (SessionSecurityDiagnosticsDataType) o;

            setSessionId(v.getSessionId());
            setClientUserIdOfSession(v.getClientUserIdOfSession());
            setClientUserIdHistory(v.getClientUserIdHistory());
            setAuthenticationMechanism(v.getAuthenticationMechanism());
            setEncoding(v.getEncoding());
            setTransportProtocol(v.getTransportProtocol());
            setSecurityMode(v.getSecurityMode());
            setSecurityPolicyUri(v.getSecurityPolicyUri());
            setClientCertificate(v.getClientCertificate());
        }
    }

    @Override
    public NodeId getSessionId() {
        Optional<VariableNode> component = getVariableComponent("SessionId");

        return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSessionIdNode() {
        Optional<VariableNode> component = getVariableComponent("SessionId");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSessionId(NodeId value) {
        getVariableComponent("SessionId")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getClientUserIdOfSession() {
        Optional<VariableNode> component = getVariableComponent("ClientUserIdOfSession");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientUserIdOfSessionNode() {
        Optional<VariableNode> component = getVariableComponent("ClientUserIdOfSession");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientUserIdOfSession(String value) {
        getVariableComponent("ClientUserIdOfSession")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String[] getClientUserIdHistory() {
        Optional<VariableNode> component = getVariableComponent("ClientUserIdHistory");

        return component.map(node -> (String[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientUserIdHistoryNode() {
        Optional<VariableNode> component = getVariableComponent("ClientUserIdHistory");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientUserIdHistory(String[] value) {
        getVariableComponent("ClientUserIdHistory")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getAuthenticationMechanism() {
        Optional<VariableNode> component = getVariableComponent("AuthenticationMechanism");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getAuthenticationMechanismNode() {
        Optional<VariableNode> component = getVariableComponent("AuthenticationMechanism");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setAuthenticationMechanism(String value) {
        getVariableComponent("AuthenticationMechanism")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getEncoding() {
        Optional<VariableNode> component = getVariableComponent("Encoding");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getEncodingNode() {
        Optional<VariableNode> component = getVariableComponent("Encoding");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setEncoding(String value) {
        getVariableComponent("Encoding")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getTransportProtocol() {
        Optional<VariableNode> component = getVariableComponent("TransportProtocol");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getTransportProtocolNode() {
        Optional<VariableNode> component = getVariableComponent("TransportProtocol");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setTransportProtocol(String value) {
        getVariableComponent("TransportProtocol")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public MessageSecurityMode getSecurityMode() {
        Optional<VariableNode> component = getVariableComponent("SecurityMode");

        return component.map(node -> (MessageSecurityMode) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSecurityModeNode() {
        Optional<VariableNode> component = getVariableComponent("SecurityMode");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSecurityMode(MessageSecurityMode value) {
        getVariableComponent("SecurityMode")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getSecurityPolicyUri() {
        Optional<VariableNode> component = getVariableComponent("SecurityPolicyUri");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSecurityPolicyUriNode() {
        Optional<VariableNode> component = getVariableComponent("SecurityPolicyUri");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSecurityPolicyUri(String value) {
        getVariableComponent("SecurityPolicyUri")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ByteString getClientCertificate() {
        Optional<VariableNode> component = getVariableComponent("ClientCertificate");

        return component.map(node -> (ByteString) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getClientCertificateNode() {
        Optional<VariableNode> component = getVariableComponent("ClientCertificate");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setClientCertificate(ByteString value) {
        getVariableComponent("ClientCertificate")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
