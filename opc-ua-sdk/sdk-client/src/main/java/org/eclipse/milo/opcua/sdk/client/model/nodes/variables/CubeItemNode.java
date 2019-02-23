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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.CubeItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public class CubeItemNode extends ArrayItemNode implements CubeItemType {
    public CubeItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getXAxisDefinitionNode() {
        return getPropertyNode(CubeItemType.X_AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation> getXAxisDefinition() {
        return getProperty(CubeItemType.X_AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setXAxisDefinition(AxisInformation value) {
        return setProperty(CubeItemType.X_AXIS_DEFINITION, value);
    }

    public CompletableFuture<PropertyNode> getYAxisDefinitionNode() {
        return getPropertyNode(CubeItemType.Y_AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation> getYAxisDefinition() {
        return getProperty(CubeItemType.Y_AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setYAxisDefinition(AxisInformation value) {
        return setProperty(CubeItemType.Y_AXIS_DEFINITION, value);
    }

    public CompletableFuture<PropertyNode> getZAxisDefinitionNode() {
        return getPropertyNode(CubeItemType.Z_AXIS_DEFINITION);
    }

    public CompletableFuture<AxisInformation> getZAxisDefinition() {
        return getProperty(CubeItemType.Z_AXIS_DEFINITION);
    }

    public CompletableFuture<StatusCode> setZAxisDefinition(AxisInformation value) {
        return setProperty(CubeItemType.Z_AXIS_DEFINITION, value);
    }
}
