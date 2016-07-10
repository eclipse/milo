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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryUpdateRequest")
public class HistoryUpdateRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryUpdateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ExtensionObject[] _historyUpdateDetails;

    public HistoryUpdateRequest() {
        this._requestHeader = null;
        this._historyUpdateDetails = null;
    }

    public HistoryUpdateRequest(RequestHeader _requestHeader, ExtensionObject[] _historyUpdateDetails) {
        this._requestHeader = _requestHeader;
        this._historyUpdateDetails = _historyUpdateDetails;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ExtensionObject[] getHistoryUpdateDetails() { return _historyUpdateDetails; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryUpdateRequest historyUpdateRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", historyUpdateRequest._requestHeader != null ? historyUpdateRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("HistoryUpdateDetails", historyUpdateRequest._historyUpdateDetails, encoder::encodeExtensionObject);
    }

    public static HistoryUpdateRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        ExtensionObject[] _historyUpdateDetails = decoder.decodeArray("HistoryUpdateDetails", decoder::decodeExtensionObject, ExtensionObject.class);

        return new HistoryUpdateRequest(_requestHeader, _historyUpdateDetails);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryUpdateRequest::encode, HistoryUpdateRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryUpdateRequest::decode, HistoryUpdateRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
