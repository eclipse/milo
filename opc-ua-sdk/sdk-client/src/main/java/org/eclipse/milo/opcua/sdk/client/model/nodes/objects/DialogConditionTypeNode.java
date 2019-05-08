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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DialogConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class DialogConditionTypeNode extends ConditionTypeNode implements DialogConditionType {
    public DialogConditionTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getPromptNode() {
        return getPropertyNode(DialogConditionType.PROMPT);
    }

    public CompletableFuture<LocalizedText> getPrompt() {
        return getProperty(DialogConditionType.PROMPT);
    }

    public CompletableFuture<StatusCode> setPrompt(LocalizedText value) {
        return setProperty(DialogConditionType.PROMPT, value);
    }

    public CompletableFuture<PropertyTypeNode> getResponseOptionSetNode() {
        return getPropertyNode(DialogConditionType.RESPONSE_OPTION_SET);
    }

    public CompletableFuture<LocalizedText[]> getResponseOptionSet() {
        return getProperty(DialogConditionType.RESPONSE_OPTION_SET);
    }

    public CompletableFuture<StatusCode> setResponseOptionSet(LocalizedText[] value) {
        return setProperty(DialogConditionType.RESPONSE_OPTION_SET, value);
    }

    public CompletableFuture<PropertyTypeNode> getDefaultResponseNode() {
        return getPropertyNode(DialogConditionType.DEFAULT_RESPONSE);
    }

    public CompletableFuture<Integer> getDefaultResponse() {
        return getProperty(DialogConditionType.DEFAULT_RESPONSE);
    }

    public CompletableFuture<StatusCode> setDefaultResponse(Integer value) {
        return setProperty(DialogConditionType.DEFAULT_RESPONSE, value);
    }

    public CompletableFuture<PropertyTypeNode> getOkResponseNode() {
        return getPropertyNode(DialogConditionType.OK_RESPONSE);
    }

    public CompletableFuture<Integer> getOkResponse() {
        return getProperty(DialogConditionType.OK_RESPONSE);
    }

    public CompletableFuture<StatusCode> setOkResponse(Integer value) {
        return setProperty(DialogConditionType.OK_RESPONSE, value);
    }

    public CompletableFuture<PropertyTypeNode> getCancelResponseNode() {
        return getPropertyNode(DialogConditionType.CANCEL_RESPONSE);
    }

    public CompletableFuture<Integer> getCancelResponse() {
        return getProperty(DialogConditionType.CANCEL_RESPONSE);
    }

    public CompletableFuture<StatusCode> setCancelResponse(Integer value) {
        return setProperty(DialogConditionType.CANCEL_RESPONSE, value);
    }

    public CompletableFuture<PropertyTypeNode> getLastResponseNode() {
        return getPropertyNode(DialogConditionType.LAST_RESPONSE);
    }

    public CompletableFuture<Integer> getLastResponse() {
        return getProperty(DialogConditionType.LAST_RESPONSE);
    }

    public CompletableFuture<StatusCode> setLastResponse(Integer value) {
        return setProperty(DialogConditionType.LAST_RESPONSE, value);
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getEnabledStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "EnabledState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getEnabledState() {
        return getEnabledStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setEnabledState(LocalizedText value) {
        return getEnabledStateNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<TwoStateVariableTypeNode> getDialogStateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "DialogState").thenApply(TwoStateVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<LocalizedText> getDialogState() {
        return getDialogStateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDialogState(LocalizedText value) {
        return getDialogStateNode().thenCompose(node -> node.setValue(value));
    }
}
