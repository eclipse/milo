/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json;

import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.jetbrains.annotations.Nullable;

public class TestDataType implements DataType {

    final NodeId nodeId;
    final QualifiedName browseName;
    final DataTypeDefinition definition;
    final boolean isAbstract;

    public TestDataType(NodeId nodeId, QualifiedName browseName, DataTypeDefinition definition, boolean isAbstract) {
        this.nodeId = nodeId;
        this.browseName = browseName;
        this.definition = definition;
        this.isAbstract = isAbstract;
    }

    @Override
    public NodeId getNodeId() {
        return nodeId;
    }

    @Override
    public QualifiedName getBrowseName() {
        return browseName;
    }

    @Override
    public @Nullable NodeId getBinaryEncodingId() {
        return null;
    }

    @Override
    public @Nullable NodeId getXmlEncodingId() {
        return null;
    }

    @Override
    public @Nullable NodeId getJsonEncodingId() {
        return null;
    }

    @Override
    public @Nullable DataTypeDefinition getDataTypeDefinition() {
        return definition;
    }

    @Override
    public Boolean isAbstract() {
        return isAbstract;
    }

}
