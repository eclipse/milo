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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("FindServersRequest")
public class FindServersRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.FindServersRequest;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final String _endpointUrl;
    protected final String[] _localeIds;
    protected final String[] _serverUris;

    public FindServersRequest() {
        this._requestHeader = null;
        this._endpointUrl = null;
        this._localeIds = null;
        this._serverUris = null;
    }

    public FindServersRequest(RequestHeader _requestHeader, String _endpointUrl, String[] _localeIds, String[] _serverUris) {
        this._requestHeader = _requestHeader;
        this._endpointUrl = _endpointUrl;
        this._localeIds = _localeIds;
        this._serverUris = _serverUris;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public String getEndpointUrl() { return _endpointUrl; }

    public String[] getLocaleIds() { return _localeIds; }

    public String[] getServerUris() { return _serverUris; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(FindServersRequest findServersRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", findServersRequest._requestHeader != null ? findServersRequest._requestHeader : new RequestHeader());
        encoder.encodeString("EndpointUrl", findServersRequest._endpointUrl);
        encoder.encodeArray("LocaleIds", findServersRequest._localeIds, encoder::encodeString);
        encoder.encodeArray("ServerUris", findServersRequest._serverUris, encoder::encodeString);
    }

    public static FindServersRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        String _endpointUrl = decoder.decodeString("EndpointUrl");
        String[] _localeIds = decoder.decodeArray("LocaleIds", decoder::decodeString, String.class);
        String[] _serverUris = decoder.decodeArray("ServerUris", decoder::decodeString, String.class);

        return new FindServersRequest(_requestHeader, _endpointUrl, _localeIds, _serverUris);
    }

    static {
        DelegateRegistry.registerEncoder(FindServersRequest::encode, FindServersRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(FindServersRequest::decode, FindServersRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
