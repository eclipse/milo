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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryUpdateDetails")
public class HistoryUpdateDetails implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryUpdateDetails;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateDetails_Encoding_DefaultXml;

    protected final NodeId _nodeId;

    public HistoryUpdateDetails() {
        this._nodeId = null;
    }

    public HistoryUpdateDetails(NodeId _nodeId) {
        this._nodeId = _nodeId;
    }

    public NodeId getNodeId() { return _nodeId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryUpdateDetails> {
        @Override
        public HistoryUpdateDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();

            return new HistoryUpdateDetails(_nodeId);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateDetails value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryUpdateDetails> {
        @Override
        public HistoryUpdateDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");

            return new HistoryUpdateDetails(_nodeId);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
        }
    }

}
