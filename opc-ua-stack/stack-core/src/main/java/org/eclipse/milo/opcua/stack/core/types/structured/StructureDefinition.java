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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.5">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StructureDefinition extends DataTypeDefinition implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=99");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=122");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14798");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15066");

    private final NodeId defaultEncodingId;

    private final NodeId baseDataType;

    private final StructureType structureType;

    private final StructureField[] fields;

    public StructureDefinition(NodeId defaultEncodingId, NodeId baseDataType,
                               StructureType structureType, StructureField[] fields) {
        this.defaultEncodingId = defaultEncodingId;
        this.baseDataType = baseDataType;
        this.structureType = structureType;
        this.fields = fields;
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

    public NodeId getDefaultEncodingId() {
        return defaultEncodingId;
    }

    public NodeId getBaseDataType() {
        return baseDataType;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public StructureField[] getFields() {
        return fields;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 122),
            new NodeId(0, 97),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DefaultEncodingId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("BaseDataType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("StructureType", LocalizedText.NULL_VALUE, new NodeId(0, 98), -1, null, UInteger.valueOf(0), false),
                new StructureField("Fields", LocalizedText.NULL_VALUE, new NodeId(0, 101), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StructureDefinition> {
        @Override
        public Class<StructureDefinition> getType() {
            return StructureDefinition.class;
        }

        @Override
        public StructureDefinition decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId defaultEncodingId = decoder.readNodeId("DefaultEncodingId");
            NodeId baseDataType = decoder.readNodeId("BaseDataType");
            StructureType structureType = StructureType.from(decoder.readEnum("StructureType"));
            StructureField[] fields = (StructureField[]) decoder.readStructArray("Fields", StructureField.TYPE_ID);
            return new StructureDefinition(defaultEncodingId, baseDataType, structureType, fields);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               StructureDefinition value) {
            encoder.writeNodeId("DefaultEncodingId", value.getDefaultEncodingId());
            encoder.writeNodeId("BaseDataType", value.getBaseDataType());
            encoder.writeEnum("StructureType", value.getStructureType());
            encoder.writeStructArray("Fields", value.getFields(), StructureField.TYPE_ID);
        }
    }
}
