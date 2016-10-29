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

@UaDataType("NetworkGroupDataType")
public class NetworkGroupDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.NetworkGroupDataType;
    public static final NodeId BinaryEncodingId = Identifiers.NetworkGroupDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NetworkGroupDataType_Encoding_DefaultXml;

    protected final String _serverUri;
    protected final EndpointUrlListDataType[] _networkPaths;

    public NetworkGroupDataType() {
        this._serverUri = null;
        this._networkPaths = null;
    }

    public NetworkGroupDataType(String _serverUri, EndpointUrlListDataType[] _networkPaths) {
        this._serverUri = _serverUri;
        this._networkPaths = _networkPaths;
    }

    public String getServerUri() { return _serverUri; }

    public EndpointUrlListDataType[] getNetworkPaths() { return _networkPaths; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NetworkGroupDataType networkGroupDataType, UaEncoder encoder) {
        encoder.encodeString("ServerUri", networkGroupDataType._serverUri);
        encoder.encodeArray("NetworkPaths", networkGroupDataType._networkPaths, encoder::encodeSerializable);
    }

    public static NetworkGroupDataType decode(UaDecoder decoder) {
        String _serverUri = decoder.decodeString("ServerUri");
        EndpointUrlListDataType[] _networkPaths = decoder.decodeArray("NetworkPaths", decoder::decodeSerializable, EndpointUrlListDataType.class);

        return new NetworkGroupDataType(_serverUri, _networkPaths);
    }

    static {
        DelegateRegistry.registerEncoder(NetworkGroupDataType::encode, NetworkGroupDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NetworkGroupDataType::decode, NetworkGroupDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
