/*
 * Copyright (c) 2017 Kevin Herron
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

    public CompletableFuture<PropertyNode> getIdNode() {
        return getPropertyNode(StateVariableType.ID);
    }

    public CompletableFuture<?> getId() {
        return getProperty(StateVariableType.ID);
    }

    public CompletableFuture<StatusCode> setId(Object value) {
        return setProperty(StateVariableType.ID, value);
    }

    public CompletableFuture<PropertyNode> getNameNode() {
        return getPropertyNode(StateVariableType.NAME);
    }

    public CompletableFuture<QualifiedName> getName() {
        return getProperty(StateVariableType.NAME);
    }

    public CompletableFuture<StatusCode> setName(QualifiedName value) {
        return setProperty(StateVariableType.NAME, value);
    }

    public CompletableFuture<PropertyNode> getNumberNode() {
        return getPropertyNode(StateVariableType.NUMBER);
    }

    public CompletableFuture<UInteger> getNumber() {
        return getProperty(StateVariableType.NUMBER);
    }

    public CompletableFuture<StatusCode> setNumber(UInteger value) {
        return setProperty(StateVariableType.NUMBER, value);
    }

    public CompletableFuture<PropertyNode> getEffectiveDisplayNameNode() {
        return getPropertyNode(StateVariableType.EFFECTIVE_DISPLAY_NAME);
    }

    public CompletableFuture<LocalizedText> getEffectiveDisplayName() {
        return getProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME);
    }

    public CompletableFuture<StatusCode> setEffectiveDisplayName(LocalizedText value) {
        return setProperty(StateVariableType.EFFECTIVE_DISPLAY_NAME, value);
    }
}
