/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public abstract class DataTypeSchemaHeader extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15534");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15676");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15950");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16151");

    private final String[] namespaces;

    private final StructureDescription[] structureDataTypes;

    private final EnumDescription[] enumDataTypes;

    private final SimpleTypeDescription[] simpleDataTypes;

    public DataTypeSchemaHeader(String[] namespaces, StructureDescription[] structureDataTypes,
                                EnumDescription[] enumDataTypes, SimpleTypeDescription[] simpleDataTypes) {
        this.namespaces = namespaces;
        this.structureDataTypes = structureDataTypes;
        this.enumDataTypes = enumDataTypes;
        this.simpleDataTypes = simpleDataTypes;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String[] getNamespaces() {
        return namespaces;
    }

    public StructureDescription[] getStructureDataTypes() {
        return structureDataTypes;
    }

    public EnumDescription[] getEnumDataTypes() {
        return enumDataTypes;
    }

    public SimpleTypeDescription[] getSimpleDataTypes() {
        return simpleDataTypes;
    }
}
