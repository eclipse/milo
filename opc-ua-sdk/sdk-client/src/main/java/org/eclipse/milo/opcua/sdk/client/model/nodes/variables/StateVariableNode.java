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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class StateVariableNode extends BaseDataVariableNode implements StateVariableType {

    public StateVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> id() {
        return getPropertyNode(StateVariableType.ID.getBrowseName());
    }

    @Override
    public CompletableFuture<? extends Object> getId() {
        return getProperty(StateVariableType.ID);
    }

    @Override
    public CompletableFuture<StatusCode> setId(Object value) {
        return setProperty(StateVariableType.ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> name() {
        return getPropertyNode(StateVariableType.NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<QualifiedName> getName() {
        return getProperty(StateVariableType.NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setName(QualifiedName value) {
        return setProperty(StateVariableType.NAME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> number() {
        return getPropertyNode(StateVariableType.NUMBER.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getNumber() {
        return getProperty(StateVariableType.NUMBER);
    }

    @Override
    public CompletableFuture<StatusCode> setNumber(UInteger value) {
        return setProperty(StateVariableType.NUMBER, value);
    }

    @Override
    public CompletableFuture<PropertyNode> effectiveDisplayName() {
        return getPropertyNode(StateVariableType.EFFECTIVE_DISPLAY_NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getEffectiveDisplayName() {
        return getProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setEffectiveDisplayName(LocalizedText value) {
        return setProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME, value);
    }


}