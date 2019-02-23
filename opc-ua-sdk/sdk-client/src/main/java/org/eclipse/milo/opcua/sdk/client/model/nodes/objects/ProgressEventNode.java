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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgressEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ProgressEventNode extends BaseEventNode implements ProgressEventType {
    public ProgressEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getContextNode() {
        return getPropertyNode(ProgressEventType.CONTEXT);
    }

    public CompletableFuture<?> getContext() {
        return getProperty(ProgressEventType.CONTEXT);
    }

    public CompletableFuture<StatusCode> setContext(Object value) {
        return setProperty(ProgressEventType.CONTEXT, value);
    }

    public CompletableFuture<PropertyNode> getProgressNode() {
        return getPropertyNode(ProgressEventType.PROGRESS);
    }

    public CompletableFuture<UShort> getProgress() {
        return getProperty(ProgressEventType.PROGRESS);
    }

    public CompletableFuture<StatusCode> setProgress(UShort value) {
        return setProperty(ProgressEventType.PROGRESS, value);
    }
}
