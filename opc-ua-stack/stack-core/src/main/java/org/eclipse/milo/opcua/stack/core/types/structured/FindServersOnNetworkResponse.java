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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("FindServersOnNetworkResponse")
public class FindServersOnNetworkResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.FindServersOnNetworkResponse;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersOnNetworkResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final DateTime _lastCounterResetTime;
    protected final ServerOnNetwork[] _servers;

    public FindServersOnNetworkResponse() {
        this._responseHeader = null;
        this._lastCounterResetTime = null;
        this._servers = null;
    }

    public FindServersOnNetworkResponse(ResponseHeader _responseHeader, DateTime _lastCounterResetTime, ServerOnNetwork[] _servers) {
        this._responseHeader = _responseHeader;
        this._lastCounterResetTime = _lastCounterResetTime;
        this._servers = _servers;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public DateTime getLastCounterResetTime() { return _lastCounterResetTime; }

    public ServerOnNetwork[] getServers() { return _servers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(FindServersOnNetworkResponse findServersOnNetworkResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", findServersOnNetworkResponse._responseHeader != null ? findServersOnNetworkResponse._responseHeader : new ResponseHeader());
        encoder.encodeDateTime("LastCounterResetTime", findServersOnNetworkResponse._lastCounterResetTime);
        encoder.encodeArray("Servers", findServersOnNetworkResponse._servers, encoder::encodeSerializable);
    }

    public static FindServersOnNetworkResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        DateTime _lastCounterResetTime = decoder.decodeDateTime("LastCounterResetTime");
        ServerOnNetwork[] _servers = decoder.decodeArray("Servers", decoder::decodeSerializable, ServerOnNetwork.class);

        return new FindServersOnNetworkResponse(_responseHeader, _lastCounterResetTime, _servers);
    }

    static {
        DelegateRegistry.registerEncoder(FindServersOnNetworkResponse::encode, FindServersOnNetworkResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(FindServersOnNetworkResponse::decode, FindServersOnNetworkResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
