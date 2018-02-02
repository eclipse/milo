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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.TransparentRedundancyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;

public class TransparentRedundancyNode extends ServerRedundancyNode implements TransparentRedundancyType {
    public TransparentRedundancyNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getCurrentServerIdNode() {
        return getPropertyNode(TransparentRedundancyType.CURRENT_SERVER_ID);
    }

    public CompletableFuture<String> getCurrentServerId() {
        return getProperty(TransparentRedundancyType.CURRENT_SERVER_ID);
    }

    public CompletableFuture<StatusCode> setCurrentServerId(String value) {
        return setProperty(TransparentRedundancyType.CURRENT_SERVER_ID, value);
    }

    public CompletableFuture<PropertyNode> getRedundantServerArrayNode() {
        return getPropertyNode(TransparentRedundancyType.REDUNDANT_SERVER_ARRAY);
    }

    public CompletableFuture<RedundantServerDataType[]> getRedundantServerArray() {
        return getProperty(TransparentRedundancyType.REDUNDANT_SERVER_ARRAY);
    }

    public CompletableFuture<StatusCode> setRedundantServerArray(RedundantServerDataType[] value) {
        return setProperty(TransparentRedundancyType.REDUNDANT_SERVER_ARRAY, value);
    }
}
