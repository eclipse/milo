/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.31">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.31</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class DataTypeSchemaHeader extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15534");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15676");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15950");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16151");

    private final String @Nullable [] namespaces;

    private final StructureDescription @Nullable [] structureDataTypes;

    private final EnumDescription @Nullable [] enumDataTypes;

    private final SimpleTypeDescription @Nullable [] simpleDataTypes;

    public DataTypeSchemaHeader(String @Nullable [] namespaces,
                                StructureDescription @Nullable [] structureDataTypes,
                                EnumDescription @Nullable [] enumDataTypes,
                                SimpleTypeDescription @Nullable [] simpleDataTypes) {
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

    public String @Nullable [] getNamespaces() {
        return namespaces;
    }

    public StructureDescription @Nullable [] getStructureDataTypes() {
        return structureDataTypes;
    }

    public EnumDescription @Nullable [] getEnumDataTypes() {
        return enumDataTypes;
    }

    public SimpleTypeDescription @Nullable [] getSimpleDataTypes() {
        return simpleDataTypes;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15676),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Namespaces", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("StructureDataTypes", LocalizedText.NULL_VALUE, new NodeId(0, 15487), 1, null, UInteger.valueOf(0), false),
                new StructureField("EnumDataTypes", LocalizedText.NULL_VALUE, new NodeId(0, 15488), 1, null, UInteger.valueOf(0), false),
                new StructureField("SimpleDataTypes", LocalizedText.NULL_VALUE, new NodeId(0, 15005), 1, null, UInteger.valueOf(0), false)
            }
        );
    }
}
