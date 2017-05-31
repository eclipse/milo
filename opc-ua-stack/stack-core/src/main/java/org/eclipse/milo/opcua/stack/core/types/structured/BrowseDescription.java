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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;

@UaDataType("BrowseDescription")
public class BrowseDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowseDescription;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseDescription_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final BrowseDirection _browseDirection;
    protected final NodeId _referenceTypeId;
    protected final Boolean _includeSubtypes;
    protected final UInteger _nodeClassMask;
    protected final UInteger _resultMask;

    public BrowseDescription() {
        this._nodeId = null;
        this._browseDirection = null;
        this._referenceTypeId = null;
        this._includeSubtypes = null;
        this._nodeClassMask = null;
        this._resultMask = null;
    }

    public BrowseDescription(NodeId _nodeId, BrowseDirection _browseDirection, NodeId _referenceTypeId, Boolean _includeSubtypes, UInteger _nodeClassMask, UInteger _resultMask) {
        this._nodeId = _nodeId;
        this._browseDirection = _browseDirection;
        this._referenceTypeId = _referenceTypeId;
        this._includeSubtypes = _includeSubtypes;
        this._nodeClassMask = _nodeClassMask;
        this._resultMask = _resultMask;
    }

    public NodeId getNodeId() { return _nodeId; }

    public BrowseDirection getBrowseDirection() { return _browseDirection; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIncludeSubtypes() { return _includeSubtypes; }

    public UInteger getNodeClassMask() { return _nodeClassMask; }

    public UInteger getResultMask() { return _resultMask; }

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
            .add("BrowseDirection", _browseDirection)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IncludeSubtypes", _includeSubtypes)
            .add("NodeClassMask", _nodeClassMask)
            .add("ResultMask", _resultMask)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowseDescription> {
        @Override
        public BrowseDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            BrowseDirection _browseDirection = BrowseDirection.from(reader.readInt32());
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _includeSubtypes = reader.readBoolean();
            UInteger _nodeClassMask = reader.readUInt32();
            UInteger _resultMask = reader.readUInt32();

            return new BrowseDescription(_nodeId, _browseDirection, _referenceTypeId, _includeSubtypes, _nodeClassMask, _resultMask);
        }

        @Override
        public void encode(SerializationContext context, BrowseDescription encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._nodeId);
            writer.writeInt32(encodable._browseDirection != null ? encodable._browseDirection.getValue() : 0);
            writer.writeNodeId(encodable._referenceTypeId);
            writer.writeBoolean(encodable._includeSubtypes);
            writer.writeUInt32(encodable._nodeClassMask);
            writer.writeUInt32(encodable._resultMask);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowseDescription> {
        @Override
        public BrowseDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            BrowseDirection _browseDirection = BrowseDirection.from(reader.readInt32("BrowseDirection"));
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _includeSubtypes = reader.readBoolean("IncludeSubtypes");
            UInteger _nodeClassMask = reader.readUInt32("NodeClassMask");
            UInteger _resultMask = reader.readUInt32("ResultMask");

            return new BrowseDescription(_nodeId, _browseDirection, _referenceTypeId, _includeSubtypes, _nodeClassMask, _resultMask);
        }

        @Override
        public void encode(SerializationContext context, BrowseDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeInt32("BrowseDirection", encodable._browseDirection != null ? encodable._browseDirection.getValue() : 0);
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IncludeSubtypes", encodable._includeSubtypes);
            writer.writeUInt32("NodeClassMask", encodable._nodeClassMask);
            writer.writeUInt32("ResultMask", encodable._resultMask);
        }
    }

}
