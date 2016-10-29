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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public String[] getServerCapabilities() { return _serverCapabilities; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ServerOnNetwork serverOnNetwork, UaEncoder encoder) {
        encoder.encodeUInt32("RecordId", serverOnNetwork._recordId);
        encoder.encodeString("ServerName", serverOnNetwork._serverName);
        encoder.encodeString("DiscoveryUrl", serverOnNetwork._discoveryUrl);
        encoder.encodeArray("ServerCapabilities", serverOnNetwork._serverCapabilities, encoder::encodeString);
    }

    public static ServerOnNetwork decode(UaDecoder decoder) {
        UInteger _recordId = decoder.decodeUInt32("RecordId");
        String _serverName = decoder.decodeString("ServerName");
        String _discoveryUrl = decoder.decodeString("DiscoveryUrl");
        String[] _serverCapabilities = decoder.decodeArray("ServerCapabilities", decoder::decodeString, String.class);

        return new ServerOnNetwork(_recordId, _serverName, _discoveryUrl, _serverCapabilities);
    }

    static {
        DelegateRegistry.registerEncoder(ServerOnNetwork::encode, ServerOnNetwork.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ServerOnNetwork::decode, ServerOnNetwork.class, BinaryEncodingId, XmlEncodingId);
    }

}
