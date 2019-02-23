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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateValueDiscreteType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

public class MultiStateValueDiscreteNode extends DiscreteItemNode implements MultiStateValueDiscreteType {
    public MultiStateValueDiscreteNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEnumValuesNode() {
        return getPropertyNode(MultiStateValueDiscreteType.ENUM_VALUES);
    }

    public CompletableFuture<EnumValueType[]> getEnumValues() {
        return getProperty(MultiStateValueDiscreteType.ENUM_VALUES);
    }

    public CompletableFuture<StatusCode> setEnumValues(EnumValueType[] value) {
        return setProperty(MultiStateValueDiscreteType.ENUM_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getValueAsTextNode() {
        return getPropertyNode(MultiStateValueDiscreteType.VALUE_AS_TEXT);
    }

    public CompletableFuture<LocalizedText> getValueAsText() {
        return getProperty(MultiStateValueDiscreteType.VALUE_AS_TEXT);
    }

    public CompletableFuture<StatusCode> setValueAsText(LocalizedText value) {
        return setProperty(MultiStateValueDiscreteType.VALUE_AS_TEXT, value);
    }
}
