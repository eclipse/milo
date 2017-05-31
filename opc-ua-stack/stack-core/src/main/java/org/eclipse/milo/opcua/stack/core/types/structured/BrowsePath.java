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

@UaDataType("BrowsePath")
public class BrowsePath implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePath;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePath_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePath_Encoding_DefaultXml;

    protected final NodeId _startingNode;
    protected final RelativePath _relativePath;

    public BrowsePath() {
        this._startingNode = null;
        this._relativePath = null;
    }

    public BrowsePath(NodeId _startingNode, RelativePath _relativePath) {
        this._startingNode = _startingNode;
        this._relativePath = _relativePath;
    }

    public NodeId getStartingNode() { return _startingNode; }

    public RelativePath getRelativePath() { return _relativePath; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartingNode", _startingNode)
            .add("RelativePath", _relativePath)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowsePath> {
        @Override
        public BrowsePath decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _startingNode = reader.readNodeId();
            RelativePath _relativePath = (RelativePath) context.decode(RelativePath.BinaryEncodingId, reader);

            return new BrowsePath(_startingNode, _relativePath);
        }

        @Override
        public void encode(SerializationContext context, BrowsePath encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._startingNode);
            context.encode(RelativePath.BinaryEncodingId, encodable._relativePath, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowsePath> {
        @Override
        public BrowsePath decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _startingNode = reader.readNodeId("StartingNode");
            RelativePath _relativePath = (RelativePath) context.decode(RelativePath.XmlEncodingId, reader);

            return new BrowsePath(_startingNode, _relativePath);
        }

        @Override
        public void encode(SerializationContext context, BrowsePath encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("StartingNode", encodable._startingNode);
            context.encode(RelativePath.XmlEncodingId, encodable._relativePath, writer);
        }
    }

}
