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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitAlarmType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class ExclusiveLimitAlarmNode extends LimitAlarmNode implements ExclusiveLimitAlarmType {

    public ExclusiveLimitAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<TwoStateVariableNode> activeState() {
        return getVariableComponent(QualifiedName.parse("0:ActiveState"))
            .thenApply(TwoStateVariableNode.class::cast);
    }

    public CompletableFuture<LocalizedText> getActiveState() {
        return activeState()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, LocalizedText.class));
    }

    @Override
    public CompletableFuture<StatusCode> setActiveState(LocalizedText value) {
        return activeState()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ExclusiveLimitStateMachineNode> limitState() {
        return getObjectComponent(QualifiedName.parse("0:LimitState"))
            .thenApply(ExclusiveLimitStateMachineNode.class::cast);
    }

}