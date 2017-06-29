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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerOnNetwork implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServerOnNetwork;
    public static final NodeId BinaryEncodingId = Identifiers.ServerOnNetwork_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServerOnNetwork_Encoding_DefaultXml;

    protected final UInteger recordId;
    protected final String serverName;
    protected final String discoveryUrl;
    protected final String[] serverCapabilities;

    public ServerOnNetwork() {
        this.recordId = null;
        this.serverName = null;
        this.discoveryUrl = null;
        this.serverCapabilities = null;
    }

    public ServerOnNetwork(UInteger recordId, String serverName, String discoveryUrl, String[] serverCapabilities) {
        this.recordId = recordId;
        this.serverName = serverName;
        this.discoveryUrl = discoveryUrl;
        this.serverCapabilities = serverCapabilities;
    }

    public UInteger getRecordId() { return recordId; }

    public String getServerName() { return serverName; }

    public String getDiscoveryUrl() { return discoveryUrl; }

    @Nullable
    public String[] getServerCapabilities() { return serverCapabilities; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RecordId", recordId)
            .add("ServerName", serverName)
            .add("DiscoveryUrl", discoveryUrl)
            .add("ServerCapabilities", serverCapabilities)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ServerOnNetwork> {

        @Override
        public Class<ServerOnNetwork> getType() {
            return ServerOnNetwork.class;
        }

        @Override
        public ServerOnNetwork decode(UaDecoder decoder) throws UaSerializationException {
            UInteger recordId = decoder.readUInt32("RecordId");
            String serverName = decoder.readString("ServerName");
            String discoveryUrl = decoder.readString("DiscoveryUrl");
            String[] serverCapabilities = decoder.readArray("ServerCapabilities", decoder::readString, String.class);

            return new ServerOnNetwork(recordId, serverName, discoveryUrl, serverCapabilities);
        }

        @Override
        public void encode(ServerOnNetwork value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("RecordId", value.recordId);
            encoder.writeString("ServerName", value.serverName);
            encoder.writeString("DiscoveryUrl", value.discoveryUrl);
            encoder.writeArray("ServerCapabilities", value.serverCapabilities, encoder::writeString);
        }
    }

}
