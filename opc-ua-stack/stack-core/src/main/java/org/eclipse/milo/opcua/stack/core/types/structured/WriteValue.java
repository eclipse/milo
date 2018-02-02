/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class WriteValue implements UaStructure {

    public static final NodeId TypeId = Identifiers.WriteValue;
    public static final NodeId BinaryEncodingId = Identifiers.WriteValue_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.WriteValue_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final UInteger attributeId;
    protected final String indexRange;
    protected final DataValue value;

    public WriteValue() {
        this.nodeId = null;
        this.attributeId = null;
        this.indexRange = null;
        this.value = null;
    }

    public WriteValue(NodeId nodeId, UInteger attributeId, String indexRange, DataValue value) {
        this.nodeId = nodeId;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
        this.value = value;
    }

    public NodeId getNodeId() { return nodeId; }

    public UInteger getAttributeId() { return attributeId; }

    public String getIndexRange() { return indexRange; }

    public DataValue getValue() { return value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("AttributeId", attributeId)
            .add("IndexRange", indexRange)
            .add("Value", value)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<WriteValue> {

        @Override
        public Class<WriteValue> getType() {
            return WriteValue.class;
        }

        @Override
        public WriteValue decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            DataValue value = decoder.readDataValue("Value");

            return new WriteValue(nodeId, attributeId, indexRange, value);
        }

        @Override
        public void encode(WriteValue value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeUInt32("AttributeId", value.attributeId);
            encoder.writeString("IndexRange", value.indexRange);
            encoder.writeDataValue("Value", value.value);
        }
    }

}
