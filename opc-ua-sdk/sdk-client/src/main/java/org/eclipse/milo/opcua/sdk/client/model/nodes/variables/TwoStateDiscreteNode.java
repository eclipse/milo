/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateDiscreteType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class TwoStateDiscreteNode extends DiscreteItemNode implements TwoStateDiscreteType {
    public TwoStateDiscreteNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getFalseStateNode() {
        return getPropertyNode(TwoStateDiscreteType.FALSE_STATE);
    }

    public CompletableFuture<LocalizedText> getFalseState() {
        return getProperty(TwoStateDiscreteType.FALSE_STATE);
    }

    public CompletableFuture<StatusCode> setFalseState(LocalizedText value) {
        return setProperty(TwoStateDiscreteType.FALSE_STATE, value);
    }

    public CompletableFuture<PropertyNode> getTrueStateNode() {
        return getPropertyNode(TwoStateDiscreteType.TRUE_STATE);
    }

    public CompletableFuture<LocalizedText> getTrueState() {
        return getProperty(TwoStateDiscreteType.TRUE_STATE);
    }

    public CompletableFuture<StatusCode> setTrueState(LocalizedText value) {
        return setProperty(TwoStateDiscreteType.TRUE_STATE, value);
    }
}
