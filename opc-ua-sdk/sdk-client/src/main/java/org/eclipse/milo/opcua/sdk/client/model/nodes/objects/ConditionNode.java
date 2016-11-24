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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ConditionVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;


public class ConditionNode extends BaseEventNode implements ConditionType {

    public ConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> conditionClassId() {
        return getPropertyNode(ConditionType.CONDITION_CLASS_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getConditionClassId() {
        return getProperty(ConditionType.CONDITION_CLASS_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setConditionClassId(NodeId value) {
        return setProperty(ConditionType.CONDITION_CLASS_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> conditionClassName() {
        return getPropertyNode(ConditionType.CONDITION_CLASS_NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getConditionClassName() {
        return getProperty(ConditionType.CONDITION_CLASS_NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setConditionClassName(LocalizedText value) {
        return setProperty(ConditionType.CONDITION_CLASS_NAME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> conditionName() {
        return getPropertyNode(ConditionType.CONDITION_NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getConditionName() {
        return getProperty(ConditionType.CONDITION_NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setConditionName(String value) {
        return setProperty(ConditionType.CONDITION_NAME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> branchId() {
        return getPropertyNode(ConditionType.BRANCH_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getBranchId() {
        return getProperty(ConditionType.BRANCH_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setBranchId(NodeId value) {
        return setProperty(ConditionType.BRANCH_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> retain() {
        return getPropertyNode(ConditionType.RETAIN.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getRetain() {
        return getProperty(ConditionType.RETAIN);
    }

    @Override
    public CompletableFuture<StatusCode> setRetain(Boolean value) {
        return setProperty(ConditionType.RETAIN, value);
    }

    @Override
    public CompletableFuture<PropertyNode> clientUserId() {
        return getPropertyNode(ConditionType.CLIENT_USER_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getClientUserId() {
        return getProperty(ConditionType.CLIENT_USER_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setClientUserId(String value) {
        return setProperty(ConditionType.CLIENT_USER_ID, value);
    }


    @Override
    public CompletableFuture<TwoStateVariableNode> enabledState() {
        return getVariableComponent(QualifiedName.parse("0:EnabledState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getEnabledState() {
        return enabledState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return enabledState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ConditionVariableNode> quality() {
        return getVariableComponent(QualifiedName.parse("0:Quality"))
            .thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<StatusCode> getQuality() {
        return quality()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, StatusCode.class));
    }

    @Override
    public CompletableFuture<StatusCode> setQuality(StatusCode value) {
        return quality()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ConditionVariableNode> lastSeverity() {
        return getVariableComponent(QualifiedName.parse("0:LastSeverity"))
            .thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<UShort> getLastSeverity() {
        return lastSeverity()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, UShort.class));
    }

    @Override
    public CompletableFuture<StatusCode> setLastSeverity(UShort value) {
        return lastSeverity()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ConditionVariableNode> comment() {
        return getVariableComponent(QualifiedName.parse("0:Comment"))
            .thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getComment() {
        return comment()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setComment(LocalizedText value) {
        return comment()
            .thenCompose(node -> node.setValue(value));
    }


}