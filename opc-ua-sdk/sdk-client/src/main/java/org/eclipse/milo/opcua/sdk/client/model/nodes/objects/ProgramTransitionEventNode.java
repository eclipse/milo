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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramTransitionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class ProgramTransitionEventNode extends TransitionEventNode implements ProgramTransitionEventType {

    public ProgramTransitionEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> intermediateResult() {
        return getPropertyNode(ProgramTransitionEventType.INTERMEDIATE_RESULT.getBrowseName());
    }

    @Override
    public CompletableFuture<? extends Object> getIntermediateResult() {
        return getProperty(ProgramTransitionEventType.INTERMEDIATE_RESULT);
    }

    @Override
    public CompletableFuture<StatusCode> setIntermediateResult(Object value) {
        return setProperty(ProgramTransitionEventType.INTERMEDIATE_RESULT, value);
    }


}