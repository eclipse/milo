/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ConditionNode extends BaseEventNode implements ConditionType {
    public ConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getConditionClassIdNode() {
        return getPropertyNode(ConditionType.CONDITION_CLASS_ID);
    }

    public CompletableFuture<NodeId> getConditionClassId() {
        return getProperty(ConditionType.CONDITION_CLASS_ID);
    }

    public CompletableFuture<StatusCode> setConditionClassId(NodeId value) {
        return setProperty(ConditionType.CONDITION_CLASS_ID, value);
    }

    public CompletableFuture<PropertyNode> getConditionClassNameNode() {
        return getPropertyNode(ConditionType.CONDITION_CLASS_NAME);
    }

    public CompletableFuture<LocalizedText> getConditionClassName() {
        return getProperty(ConditionType.CONDITION_CLASS_NAME);
    }

    public CompletableFuture<StatusCode> setConditionClassName(LocalizedText value) {
        return setProperty(ConditionType.CONDITION_CLASS_NAME, value);
    }

    public CompletableFuture<PropertyNode> getConditionNameNode() {
        return getPropertyNode(ConditionType.CONDITION_NAME);
    }

    public CompletableFuture<String> getConditionName() {
        return getProperty(ConditionType.CONDITION_NAME);
    }

    public CompletableFuture<StatusCode> setConditionName(String value) {
        return setProperty(ConditionType.CONDITION_NAME, value);
    }

    public CompletableFuture<PropertyNode> getBranchIdNode() {
        return getPropertyNode(ConditionType.BRANCH_ID);
    }

    public CompletableFuture<NodeId> getBranchId() {
        return getProperty(ConditionType.BRANCH_ID);
    }

    public CompletableFuture<StatusCode> setBranchId(NodeId value) {
        return setProperty(ConditionType.BRANCH_ID, value);
    }

    public CompletableFuture<PropertyNode> getRetainNode() {
        return getPropertyNode(ConditionType.RETAIN);
    }

    public CompletableFuture<Boolean> getRetain() {
        return getProperty(ConditionType.RETAIN);
    }

    public CompletableFuture<StatusCode> setRetain(Boolean value) {
        return setProperty(ConditionType.RETAIN, value);
    }

    public CompletableFuture<PropertyNode> getClientUserIdNode() {
        return getPropertyNode(ConditionType.CLIENT_USER_ID);
    }

    public CompletableFuture<String> getClientUserId() {
        return getProperty(ConditionType.CLIENT_USER_ID);
    }

    public CompletableFuture<StatusCode> setClientUserId(String value) {
        return setProperty(ConditionType.CLIENT_USER_ID, value);
    }

    public CompletableFuture<TwoStateVariableNode> getEnabledStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EnabledState").thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getEnabledState() {
        return getEnabledStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return getEnabledStateNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ConditionVariableNode> getQualityNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "Quality").thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<StatusCode> getQuality() {
        return getQualityNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, StatusCode.class));
    }

    public CompletableFuture<StatusCode> setQuality(StatusCode value) {
        return getQualityNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ConditionVariableNode> getLastSeverityNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "LastSeverity").thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<UShort> getLastSeverity() {
        return getLastSeverityNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, UShort.class));
    }

    public CompletableFuture<StatusCode> setLastSeverity(UShort value) {
        return getLastSeverityNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ConditionVariableNode> getCommentNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "Comment").thenApply(ConditionVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getComment() {
        return getCommentNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    public CompletableFuture<StatusCode> setComment(LocalizedText value) {
        return getCommentNode().thenCompose(node -> node.setValue(value));
    }
}
