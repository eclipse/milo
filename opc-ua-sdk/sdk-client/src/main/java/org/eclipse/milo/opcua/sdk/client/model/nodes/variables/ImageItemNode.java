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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ImageItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public class ImageItemNode extends ArrayItemNode implements ImageItemType {
    public ImageItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getXAxisDefinitionNode() {
        return getPropertyNode(ImageItemType.X_AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation> getXAxisDefinition() {
        return getProperty(ImageItemType.X_AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setXAxisDefinition(AxisInformation value) {
        return setProperty(ImageItemType.X_AXIS_DEFINITION, value);
    }

    public CompletableFuture<PropertyNode> getYAxisDefinitionNode() {
        return getPropertyNode(ImageItemType.Y_AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation> getYAxisDefinition() {
        return getProperty(ImageItemType.Y_AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setYAxisDefinition(AxisInformation value) {
        return setProperty(ImageItemType.Y_AXIS_DEFINITION, value);
    }
}
