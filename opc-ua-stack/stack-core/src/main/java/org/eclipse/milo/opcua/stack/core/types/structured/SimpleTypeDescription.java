/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.35">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.35</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class SimpleTypeDescription extends DataTypeDescription implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15005");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15421");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15529");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15700");

    private final NodeId baseDataType;

    private final UByte builtInType;

    public SimpleTypeDescription(NodeId dataTypeId, QualifiedName name, NodeId baseDataType,
                                 UByte builtInType) {
        super(dataTypeId, name);
        this.baseDataType = baseDataType;
        this.builtInType = builtInType;
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

    public NodeId getBaseDataType() {
        return baseDataType;
    }

    public UByte getBuiltInType() {
        return builtInType;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15421),
            new NodeId(0, 14525),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("BaseDataType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("BuiltInType", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SimpleTypeDescription> {
        @Override
        public Class<SimpleTypeDescription> getType() {
            return SimpleTypeDescription.class;
        }

        @Override
        public SimpleTypeDescription decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId dataTypeId = decoder.readNodeId("DataTypeId");
            QualifiedName name = decoder.readQualifiedName("Name");
            NodeId baseDataType = decoder.readNodeId("BaseDataType");
            UByte builtInType = decoder.readByte("BuiltInType");
            return new SimpleTypeDescription(dataTypeId, name, baseDataType, builtInType);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SimpleTypeDescription value) {
            encoder.writeNodeId("DataTypeId", value.getDataTypeId());
            encoder.writeQualifiedName("Name", value.getName());
            encoder.writeNodeId("BaseDataType", value.getBaseDataType());
            encoder.writeByte("BuiltInType", value.getBuiltInType());
        }
    }
}
