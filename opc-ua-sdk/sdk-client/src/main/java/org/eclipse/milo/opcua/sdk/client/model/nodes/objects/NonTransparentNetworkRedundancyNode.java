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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonTransparentNetworkRedundancyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkGroupDataType;

public class NonTransparentNetworkRedundancyNode extends NonTransparentRedundancyNode implements NonTransparentNetworkRedundancyType {
    public NonTransparentNetworkRedundancyNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getServerNetworkGroupsNode() {
        return getPropertyNode(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS);
    }

    public CompletableFuture<NetworkGroupDataType[]> getServerNetworkGroups() {
        return getProperty(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS);
    }

    public CompletableFuture<StatusCode> setServerNetworkGroups(NetworkGroupDataType[] value) {
        return setProperty(NonTransparentNetworkRedundancyType.SERVER_NETWORK_GROUPS, value);
    }
}
