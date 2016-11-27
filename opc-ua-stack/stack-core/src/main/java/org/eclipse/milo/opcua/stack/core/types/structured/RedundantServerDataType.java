/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

@UaDataType("RedundantServerDataType")
public class RedundantServerDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.RedundantServerDataType;
    public static final NodeId BinaryEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RedundantServerDataType_Encoding_DefaultXml;

    protected final String _serverId;
    protected final UByte _serviceLevel;
    protected final ServerState _serverState;

    public RedundantServerDataType() {
        this._serverId = null;
        this._serviceLevel = null;
        this._serverState = null;
    }

    public RedundantServerDataType(String _serverId, UByte _serviceLevel, ServerState _serverState) {
        this._serverId = _serverId;
        this._serviceLevel = _serviceLevel;
        this._serverState = _serverState;
    }

    public String getServerId() { return _serverId; }

    public UByte getServiceLevel() { return _serviceLevel; }

    public ServerState getServerState() { return _serverState; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ServerId", _serverId)
            .add("ServiceLevel", _serviceLevel)
            .add("ServerState", _serverState)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RedundantServerDataType> {
        @Override
        public RedundantServerDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _serverId = reader.readString();
            UByte _serviceLevel = reader.readByte();
            ServerState _serverState = ServerState.from(reader.readInt32());

            return new RedundantServerDataType(_serverId, _serviceLevel, _serverState);
        }

        @Override
        public void encode(SerializationContext context, RedundantServerDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._serverId);
            writer.writeByte(encodable._serviceLevel);
            writer.writeInt32(encodable._serverState != null ? encodable._serverState.getValue() : 0);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RedundantServerDataType> {
        @Override
        public RedundantServerDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _serverId = reader.readString("ServerId");
            UByte _serviceLevel = reader.readByte("ServiceLevel");
            ServerState _serverState = ServerState.from(reader.readInt32("ServerState"));

            return new RedundantServerDataType(_serverId, _serviceLevel, _serverState);
        }

        @Override
        public void encode(SerializationContext context, RedundantServerDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ServerId", encodable._serverId);
            writer.writeByte("ServiceLevel", encodable._serviceLevel);
            writer.writeInt32("ServerState", encodable._serverState != null ? encodable._serverState.getValue() : 0);
        }
    }

}
