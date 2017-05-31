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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("BrowsePathTarget")
public class BrowsePathTarget implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePathTarget;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultXml;

    protected final ExpandedNodeId _targetId;
    protected final UInteger _remainingPathIndex;

    public BrowsePathTarget() {
        this._targetId = null;
        this._remainingPathIndex = null;
    }

    public BrowsePathTarget(ExpandedNodeId _targetId, UInteger _remainingPathIndex) {
        this._targetId = _targetId;
        this._remainingPathIndex = _remainingPathIndex;
    }

    public ExpandedNodeId getTargetId() { return _targetId; }

    public UInteger getRemainingPathIndex() { return _remainingPathIndex; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TargetId", _targetId)
            .add("RemainingPathIndex", _remainingPathIndex)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowsePathTarget> {
        @Override
        public BrowsePathTarget decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _targetId = reader.readExpandedNodeId();
            UInteger _remainingPathIndex = reader.readUInt32();

            return new BrowsePathTarget(_targetId, _remainingPathIndex);
        }

        @Override
        public void encode(SerializationContext context, BrowsePathTarget encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId(encodable._targetId);
            writer.writeUInt32(encodable._remainingPathIndex);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowsePathTarget> {
        @Override
        public BrowsePathTarget decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _targetId = reader.readExpandedNodeId("TargetId");
            UInteger _remainingPathIndex = reader.readUInt32("RemainingPathIndex");

            return new BrowsePathTarget(_targetId, _remainingPathIndex);
        }

        @Override
        public void encode(SerializationContext context, BrowsePathTarget encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId("TargetId", encodable._targetId);
            writer.writeUInt32("RemainingPathIndex", encodable._remainingPathIndex);
        }
    }

}
