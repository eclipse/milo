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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class TwoStateVariableNode extends StateVariableNode implements TwoStateVariableType {

    public TwoStateVariableNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> id() {
        return getPropertyNode(TwoStateVariableType.ID.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getId() {
        return getProperty(TwoStateVariableType.ID);
    }

    @Override
    public CompletableFuture<StatusCode> setId(Boolean value) {
        return setProperty(TwoStateVariableType.ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> transitionTime() {
        return getPropertyNode(TwoStateVariableType.TRANSITION_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getTransitionTime() {
        return getProperty(TwoStateVariableType.TRANSITION_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setTransitionTime(DateTime value) {
        return setProperty(TwoStateVariableType.TRANSITION_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> effectiveTransitionTime() {
        return getPropertyNode(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getEffectiveTransitionTime() {
        return getProperty(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setEffectiveTransitionTime(DateTime value) {
        return setProperty(TwoStateVariableType.EFFECTIVE_TRANSITION_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> trueState() {
        return getPropertyNode(TwoStateVariableType.TRUE_STATE.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getTrueState() {
        return getProperty(TwoStateVariableType.TRUE_STATE);
    }

    @Override
    public CompletableFuture<StatusCode> setTrueState(LocalizedText value) {
        return setProperty(TwoStateVariableType.TRUE_STATE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> falseState() {
        return getPropertyNode(TwoStateVariableType.FALSE_STATE.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getFalseState() {
        return getProperty(TwoStateVariableType.FALSE_STATE);
    }

    @Override
    public CompletableFuture<StatusCode> setFalseState(LocalizedText value) {
        return setProperty(TwoStateVariableType.FALSE_STATE, value);
    }


}