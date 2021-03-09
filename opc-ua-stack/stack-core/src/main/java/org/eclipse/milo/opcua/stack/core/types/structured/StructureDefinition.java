/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class StructureDefinition extends DataTypeDefinition implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=99");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=122");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14798");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15066");

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

    public static final class Codec extends GenericDataTypeCodec<StructureDefinition> {
        @Override
        public Class<StructureDefinition> getType() {
            return StructureDefinition.class;
        }

        @Override
        public StructureDefinition decode(SerializationContext context, UaDecoder decoder) {
            NodeId defaultEncodingId = decoder.readNodeId("DefaultEncodingId");
            NodeId baseDataType = decoder.readNodeId("BaseDataType");
            StructureType structureType = StructureType.from(decoder.readInt32("StructureType"));
            StructureField[] fields = (StructureField[]) decoder.readStructArray("Fields", StructureField.TYPE_ID);
            return new StructureDefinition(defaultEncodingId, baseDataType, structureType, fields);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, StructureDefinition value) {
            encoder.writeNodeId("DefaultEncodingId", value.getDefaultEncodingId());
            encoder.writeNodeId("BaseDataType", value.getBaseDataType());
            encoder.writeInt32("StructureType", value.getStructureType().getValue());
            encoder.writeStructArray("Fields", value.getFields(), StructureField.TYPE_ID);
        }
    }
}
