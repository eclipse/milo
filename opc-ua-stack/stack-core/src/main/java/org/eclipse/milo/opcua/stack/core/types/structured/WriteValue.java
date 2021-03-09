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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class WriteValue extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=668");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=670");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=669");

    private final NodeId nodeId;

    private final UInteger attributeId;

    private final String indexRange;

    private final DataValue value;

    public WriteValue(NodeId nodeId, UInteger attributeId, String indexRange, DataValue value) {
        this.nodeId = nodeId;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
        this.value = value;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public DataValue getValue() {
        return value;
    }

    public static final class Codec extends GenericDataTypeCodec<WriteValue> {
        @Override
        public Class<WriteValue> getType() {
            return WriteValue.class;
        }

        @Override
        public WriteValue decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            DataValue value = decoder.readDataValue("Value");
            return new WriteValue(nodeId, attributeId, indexRange, value);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, WriteValue value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
            encoder.writeDataValue("Value", value.getValue());
        }
    }
}
