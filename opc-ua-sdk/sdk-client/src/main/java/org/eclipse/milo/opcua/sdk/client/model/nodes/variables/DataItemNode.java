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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class DataItemNode extends BaseDataVariableNode implements DataItemType {

    public DataItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> definition() {
        return getPropertyNode(DataItemType.DEFINITION.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getDefinition() {
        return getProperty(DataItemType.DEFINITION);
    }

    @Override
    public CompletableFuture<StatusCode> setDefinition(String value) {
        return setProperty(DataItemType.DEFINITION, value);
    }

    @Override
    public CompletableFuture<PropertyNode> valuePrecision() {
        return getPropertyNode(DataItemType.VALUE_PRECISION.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getValuePrecision() {
        return getProperty(DataItemType.VALUE_PRECISION);
    }

    @Override
    public CompletableFuture<StatusCode> setValuePrecision(Double value) {
        return setProperty(DataItemType.VALUE_PRECISION, value);
    }


}