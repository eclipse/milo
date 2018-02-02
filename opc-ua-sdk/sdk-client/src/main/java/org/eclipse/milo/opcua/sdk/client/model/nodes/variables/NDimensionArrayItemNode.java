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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.NDimensionArrayItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public class NDimensionArrayItemNode extends ArrayItemNode implements NDimensionArrayItemType {
    public NDimensionArrayItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getAxisDefinitionNode() {
        return getPropertyNode(NDimensionArrayItemType.AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation[]> getAxisDefinition() {
        return getProperty(NDimensionArrayItemType.AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setAxisDefinition(AxisInformation[] value) {
        return setProperty(NDimensionArrayItemType.AXIS_DEFINITION, value);
    }
}
