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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ReadValueId implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReadValueId;
    public static final NodeId BinaryEncodingId = Identifiers.ReadValueId_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadValueId_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final UInteger attributeId;
    protected final String indexRange;
    protected final QualifiedName dataEncoding;

    public ReadValueId() {
        this.nodeId = null;
        this.attributeId = null;
        this.indexRange = null;
        this.dataEncoding = null;
    }

    public ReadValueId(NodeId nodeId, UInteger attributeId, String indexRange, QualifiedName dataEncoding) {
        this.nodeId = nodeId;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
        this.dataEncoding = dataEncoding;
    }

    public NodeId getNodeId() { return nodeId; }

    public UInteger getAttributeId() { return attributeId; }

    public String getIndexRange() { return indexRange; }

    public QualifiedName getDataEncoding() { return dataEncoding; }

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
            .add("DataEncoding", dataEncoding)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadValueId> {

        @Override
        public Class<ReadValueId> getType() {
            return ReadValueId.class;
        }

        @Override
        public ReadValueId decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            QualifiedName dataEncoding = decoder.readQualifiedName("DataEncoding");

            return new ReadValueId(nodeId, attributeId, indexRange, dataEncoding);
        }

        @Override
        public void encode(ReadValueId value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeUInt32("AttributeId", value.attributeId);
            encoder.writeString("IndexRange", value.indexRange);
            encoder.writeQualifiedName("DataEncoding", value.dataEncoding);
        }
    }

}
