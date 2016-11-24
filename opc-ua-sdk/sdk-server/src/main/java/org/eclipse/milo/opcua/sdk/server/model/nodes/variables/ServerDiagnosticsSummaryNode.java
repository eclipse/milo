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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:ServerDiagnosticsSummaryType")
public class ServerDiagnosticsSummaryNode extends BaseDataVariableNode implements ServerDiagnosticsSummaryType {

    public ServerDiagnosticsSummaryNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeMap, nodeId, variableTypeNode);
    }

    public ServerDiagnosticsSummaryNode(
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
        ServerDiagnosticsSummaryDataType value = new ServerDiagnosticsSummaryDataType(
            getServerViewCount(),
            getCurrentSessionCount(),
            getCumulatedSessionCount(),
            getSecurityRejectedSessionCount(),
            getRejectedSessionCount(),
            getSessionTimeoutCount(),
            getSessionAbortCount(),
            getPublishingIntervalCount(),
            getCurrentSubscriptionCount(),
            getCumulatedSubscriptionCount(),
            getSecurityRejectedRequestsCount(),
            getRejectedRequestsCount()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof ServerDiagnosticsSummaryDataType) {
            ServerDiagnosticsSummaryDataType v = (ServerDiagnosticsSummaryDataType) o;

            setServerViewCount(v.getServerViewCount());
            setCurrentSessionCount(v.getCurrentSessionCount());
            setCumulatedSessionCount(v.getCumulatedSessionCount());
            setSecurityRejectedSessionCount(v.getSecurityRejectedSessionCount());
            setRejectedSessionCount(v.getRejectedSessionCount());
            setSessionTimeoutCount(v.getSessionTimeoutCount());
            setSessionAbortCount(v.getSessionAbortCount());
            setPublishingIntervalCount(v.getPublishingIntervalCount());
            setCurrentSubscriptionCount(v.getCurrentSubscriptionCount());
            setCumulatedSubscriptionCount(v.getCumulatedSubscriptionCount());
            setSecurityRejectedRequestsCount(v.getSecurityRejectedRequestsCount());
            setRejectedRequestsCount(v.getRejectedRequestsCount());
        }
    }

    @Override
    public UInteger getServerViewCount() {
        Optional<VariableNode> component = getVariableComponent("ServerViewCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getServerViewCountNode() {
        Optional<VariableNode> component = getVariableComponent("ServerViewCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setServerViewCount(UInteger value) {
        getVariableComponent("ServerViewCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentSessionCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentSessionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentSessionCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentSessionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentSessionCount(UInteger value) {
        getVariableComponent("CurrentSessionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCumulatedSessionCount() {
        Optional<VariableNode> component = getVariableComponent("CumulatedSessionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCumulatedSessionCountNode() {
        Optional<VariableNode> component = getVariableComponent("CumulatedSessionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCumulatedSessionCount(UInteger value) {
        getVariableComponent("CumulatedSessionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getSecurityRejectedSessionCount() {
        Optional<VariableNode> component = getVariableComponent("SecurityRejectedSessionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSecurityRejectedSessionCountNode() {
        Optional<VariableNode> component = getVariableComponent("SecurityRejectedSessionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSecurityRejectedSessionCount(UInteger value) {
        getVariableComponent("SecurityRejectedSessionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getRejectedSessionCount() {
        Optional<VariableNode> component = getVariableComponent("RejectedSessionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRejectedSessionCountNode() {
        Optional<VariableNode> component = getVariableComponent("RejectedSessionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRejectedSessionCount(UInteger value) {
        getVariableComponent("RejectedSessionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getSessionTimeoutCount() {
        Optional<VariableNode> component = getVariableComponent("SessionTimeoutCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSessionTimeoutCountNode() {
        Optional<VariableNode> component = getVariableComponent("SessionTimeoutCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSessionTimeoutCount(UInteger value) {
        getVariableComponent("SessionTimeoutCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getSessionAbortCount() {
        Optional<VariableNode> component = getVariableComponent("SessionAbortCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSessionAbortCountNode() {
        Optional<VariableNode> component = getVariableComponent("SessionAbortCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSessionAbortCount(UInteger value) {
        getVariableComponent("SessionAbortCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getPublishingIntervalCount() {
        Optional<VariableNode> component = getVariableComponent("PublishingIntervalCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getPublishingIntervalCountNode() {
        Optional<VariableNode> component = getVariableComponent("PublishingIntervalCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setPublishingIntervalCount(UInteger value) {
        getVariableComponent("PublishingIntervalCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCurrentSubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCurrentSubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCurrentSubscriptionCount(UInteger value) {
        getVariableComponent("CurrentSubscriptionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getCumulatedSubscriptionCount() {
        Optional<VariableNode> component = getVariableComponent("CumulatedSubscriptionCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getCumulatedSubscriptionCountNode() {
        Optional<VariableNode> component = getVariableComponent("CumulatedSubscriptionCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setCumulatedSubscriptionCount(UInteger value) {
        getVariableComponent("CumulatedSubscriptionCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getSecurityRejectedRequestsCount() {
        Optional<VariableNode> component = getVariableComponent("SecurityRejectedRequestsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSecurityRejectedRequestsCountNode() {
        Optional<VariableNode> component = getVariableComponent("SecurityRejectedRequestsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSecurityRejectedRequestsCount(UInteger value) {
        getVariableComponent("SecurityRejectedRequestsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UInteger getRejectedRequestsCount() {
        Optional<VariableNode> component = getVariableComponent("RejectedRequestsCount");

        return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getRejectedRequestsCountNode() {
        Optional<VariableNode> component = getVariableComponent("RejectedRequestsCount");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setRejectedRequestsCount(UInteger value) {
        getVariableComponent("RejectedRequestsCount")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
