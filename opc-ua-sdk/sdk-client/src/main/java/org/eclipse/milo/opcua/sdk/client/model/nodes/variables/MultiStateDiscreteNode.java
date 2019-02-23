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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateDiscreteType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class MultiStateDiscreteNode extends DiscreteItemNode implements MultiStateDiscreteType {
    public MultiStateDiscreteNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEnumStringsNode() {
        return getPropertyNode(MultiStateDiscreteType.ENUM_STRINGS);
    }

    public CompletableFuture<LocalizedText[]> getEnumStrings() {
        return getProperty(MultiStateDiscreteType.ENUM_STRINGS);
    }

    public CompletableFuture<StatusCode> setEnumStrings(LocalizedText[] value) {
        return setProperty(MultiStateDiscreteType.ENUM_STRINGS, value);
    }
}
