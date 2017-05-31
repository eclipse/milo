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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("MdnsDiscoveryConfiguration")
public class MdnsDiscoveryConfiguration extends DiscoveryConfiguration {

    public static final NodeId TypeId = Identifiers.MdnsDiscoveryConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MdnsDiscoveryConfiguration_Encoding_DefaultXml;

    protected final String _mdnsServerName;
    protected final String[] _serverCapabilities;

    public MdnsDiscoveryConfiguration() {
        super();
        this._mdnsServerName = null;
        this._serverCapabilities = null;
    }

    public MdnsDiscoveryConfiguration(String _mdnsServerName, String[] _serverCapabilities) {
        super();
        this._mdnsServerName = _mdnsServerName;
        this._serverCapabilities = _serverCapabilities;
    }

    public String getMdnsServerName() { return _mdnsServerName; }

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
            .add("MdnsServerName", _mdnsServerName)
            .add("ServerCapabilities", _serverCapabilities)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MdnsDiscoveryConfiguration> {
        @Override
        public MdnsDiscoveryConfiguration decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _mdnsServerName = reader.readString();
            String[] _serverCapabilities = reader.readArray(reader::readString, String.class);

            return new MdnsDiscoveryConfiguration(_mdnsServerName, _serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, MdnsDiscoveryConfiguration encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._mdnsServerName);
            writer.writeArray(encodable._serverCapabilities, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MdnsDiscoveryConfiguration> {
        @Override
        public MdnsDiscoveryConfiguration decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _mdnsServerName = reader.readString("MdnsServerName");
            String[] _serverCapabilities = reader.readArray("ServerCapabilities", reader::readString, String.class);

            return new MdnsDiscoveryConfiguration(_mdnsServerName, _serverCapabilities);
        }

        @Override
        public void encode(SerializationContext context, MdnsDiscoveryConfiguration encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("MdnsServerName", encodable._mdnsServerName);
            writer.writeArray("ServerCapabilities", encodable._serverCapabilities, writer::writeString);
        }
    }

}
