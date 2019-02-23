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
