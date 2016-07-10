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

    public String[] getServerCapabilities() { return _serverCapabilities; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MdnsDiscoveryConfiguration mdnsDiscoveryConfiguration, UaEncoder encoder) {
        encoder.encodeString("MdnsServerName", mdnsDiscoveryConfiguration._mdnsServerName);
        encoder.encodeArray("ServerCapabilities", mdnsDiscoveryConfiguration._serverCapabilities, encoder::encodeString);
    }

    public static MdnsDiscoveryConfiguration decode(UaDecoder decoder) {
        String _mdnsServerName = decoder.decodeString("MdnsServerName");
        String[] _serverCapabilities = decoder.decodeArray("ServerCapabilities", decoder::decodeString, String.class);

        return new MdnsDiscoveryConfiguration(_mdnsServerName, _serverCapabilities);
    }

    static {
        DelegateRegistry.registerEncoder(MdnsDiscoveryConfiguration::encode, MdnsDiscoveryConfiguration.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MdnsDiscoveryConfiguration::decode, MdnsDiscoveryConfiguration.class, BinaryEncodingId, XmlEncodingId);
    }

}
