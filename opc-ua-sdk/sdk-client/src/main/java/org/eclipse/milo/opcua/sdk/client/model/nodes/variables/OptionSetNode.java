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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.OptionSetType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class OptionSetNode extends BaseDataVariableNode implements OptionSetType {
    public OptionSetNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getOptionSetValuesNode() {
        return getPropertyNode(OptionSetType.OPTION_SET_VALUES);
    }

    public CompletableFuture<LocalizedText[]> getOptionSetValues() {
        return getProperty(OptionSetType.OPTION_SET_VALUES);
    }

    public CompletableFuture<StatusCode> setOptionSetValues(LocalizedText[] value) {
        return setProperty(OptionSetType.OPTION_SET_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getBitMaskNode() {
        return getPropertyNode(OptionSetType.BIT_MASK);
    }

    public CompletableFuture<Boolean[]> getBitMask() {
        return getProperty(OptionSetType.BIT_MASK);
    }

    public CompletableFuture<StatusCode> setBitMask(Boolean[] value) {
        return setProperty(OptionSetType.BIT_MASK, value);
    }
}
