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

import javax.annotation.Nullable;

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

@UaDataType("ServerOnNetwork")
public class ServerOnNetwork implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerOnNetwork;
    public static final NodeId BinaryEncodingId = Identifiers.ServerOnNetwork_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerOnNetwork_Encoding_DefaultXml;

    protected final UInteger _recordId;
    protected final String _serverName;
    protected final String _discoveryUrl;
    protected final String[] _serverCapabilities;

    public ServerOnNetwork() {
        this._recordId = null;
        this._serverName = null;
        this._discoveryUrl = null;
        this._serverCapabilities = null;
    }

    public ServerOnNetwork(UInteger _recordId, String _serverName, String _discoveryUrl, String[] _serverCapabilities) {
        this._recordId = _recordId;
        this._serverName = _serverName;
        this._discoveryUrl = _discoveryUrl;
        this._serverCapabilities = _serverCapabilities;
    }

    public UInteger getRecordId() { return _recordId; }

    public String getServerName() { return _serverName; }

    public String getDiscoveryUrl() { return _discoveryUrl; }

    @Nullable
    public String[] getServerCapabilities() { return _serverCapabilities; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RecordId", _recordId)
            .add("ServerName", _serverName)
            .add("DiscoveryUrl", _discoveryUrl)
            .add("ServerCapabilities", _serverCapabilities)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ServerOnNetwork> {
        @Override
        public ServerOnNetwork decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _recordId = reader.readUInt32();
            String _serverName = reader.readString();
            String _discoveryUrl = reader.readString();
            String[] _serverCapabilities = reader.readArray(reader::readString, String.class);

            return new ServerOnNetwork(_recordId, _serverName, _discoveryUrl, _serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, ServerOnNetwork value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(value._recordId);
            writer.writeString(value._serverName);
            writer.writeString(value._discoveryUrl);
            writer.writeArray(value._serverCapabilities, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ServerOnNetwork> {
        @Override
        public ServerOnNetwork decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _recordId = reader.readUInt32("RecordId");
            String _serverName = reader.readString("ServerName");
            String _discoveryUrl = reader.readString("DiscoveryUrl");
            String[] _serverCapabilities = reader.readArray("ServerCapabilities", reader::readString, String.class);

            return new ServerOnNetwork(_recordId, _serverName, _discoveryUrl, _serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, ServerOnNetwork encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("RecordId", encodable._recordId);
            writer.writeString("ServerName", encodable._serverName);
            writer.writeString("DiscoveryUrl", encodable._discoveryUrl);
            writer.writeArray("ServerCapabilities", encodable._serverCapabilities, writer::writeString);
        }
    }

}
