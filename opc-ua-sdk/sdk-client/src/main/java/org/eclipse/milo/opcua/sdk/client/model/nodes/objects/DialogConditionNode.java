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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.DialogConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class DialogConditionNode extends ConditionNode implements DialogConditionType {

    public DialogConditionNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> prompt() {
        return getPropertyNode(DialogConditionType.PROMPT.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getPrompt() {
        return getProperty(DialogConditionType.PROMPT);
    }

    @Override
    public CompletableFuture<StatusCode> setPrompt(LocalizedText value) {
        return setProperty(DialogConditionType.PROMPT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> responseOptionSet() {
        return getPropertyNode(DialogConditionType.RESPONSE_OPTION_SET.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText[]> getResponseOptionSet() {
        return getProperty(DialogConditionType.RESPONSE_OPTION_SET);
    }

    @Override
    public CompletableFuture<StatusCode> setResponseOptionSet(LocalizedText[] value) {
        return setProperty(DialogConditionType.RESPONSE_OPTION_SET, value);
    }

    @Override
    public CompletableFuture<PropertyNode> defaultResponse() {
        return getPropertyNode(DialogConditionType.DEFAULT_RESPONSE.getBrowseName());
    }

    @Override
    public CompletableFuture<Integer> getDefaultResponse() {
        return getProperty(DialogConditionType.DEFAULT_RESPONSE);
    }

    @Override
    public CompletableFuture<StatusCode> setDefaultResponse(Integer value) {
        return setProperty(DialogConditionType.DEFAULT_RESPONSE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> okResponse() {
        return getPropertyNode(DialogConditionType.OK_RESPONSE.getBrowseName());
    }

    @Override
    public CompletableFuture<Integer> getOkResponse() {
        return getProperty(DialogConditionType.OK_RESPONSE);
    }

    @Override
    public CompletableFuture<StatusCode> setOkResponse(Integer value) {
        return setProperty(DialogConditionType.OK_RESPONSE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> cancelResponse() {
        return getPropertyNode(DialogConditionType.CANCEL_RESPONSE.getBrowseName());
    }

    @Override
    public CompletableFuture<Integer> getCancelResponse() {
        return getProperty(DialogConditionType.CANCEL_RESPONSE);
    }

    @Override
    public CompletableFuture<StatusCode> setCancelResponse(Integer value) {
        return setProperty(DialogConditionType.CANCEL_RESPONSE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lastResponse() {
        return getPropertyNode(DialogConditionType.LAST_RESPONSE.getBrowseName());
    }

    @Override
    public CompletableFuture<Integer> getLastResponse() {
        return getProperty(DialogConditionType.LAST_RESPONSE);
    }

    @Override
    public CompletableFuture<StatusCode> setLastResponse(Integer value) {
        return setProperty(DialogConditionType.LAST_RESPONSE, value);
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
    public CompletableFuture<TwoStateVariableNode> dialogState() {
        return getVariableComponent(QualifiedName.parse("0:DialogState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getDialogState() {
        return dialogState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setDialogState(LocalizedText value) {
        return dialogState()
            .thenCompose(node -> node.setValue(value));
    }


}