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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SemanticChangeEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;

public class SemanticChangeEventNode extends BaseModelChangeEventNode implements SemanticChangeEventType {
    public SemanticChangeEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getChangesNode() {
        return getPropertyNode(SemanticChangeEventType.CHANGES);
    }

    public CompletableFuture<SemanticChangeStructureDataType[]> getChanges() {
        return getProperty(SemanticChangeEventType.CHANGES);
    }

    public CompletableFuture<StatusCode> setChanges(SemanticChangeStructureDataType[] value) {
        return setProperty(SemanticChangeEventType.CHANGES, value);
    }
}
