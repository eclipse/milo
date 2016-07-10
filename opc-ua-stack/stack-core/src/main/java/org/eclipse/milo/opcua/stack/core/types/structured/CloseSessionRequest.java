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

@UaDataType("CloseSessionRequest")
public class CloseSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CloseSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _deleteSubscriptions;

    public CloseSessionRequest() {
        this._requestHeader = null;
        this._deleteSubscriptions = null;
    }

    public CloseSessionRequest(RequestHeader _requestHeader, Boolean _deleteSubscriptions) {
        this._requestHeader = _requestHeader;
        this._deleteSubscriptions = _deleteSubscriptions;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getDeleteSubscriptions() { return _deleteSubscriptions; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CloseSessionRequest closeSessionRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", closeSessionRequest._requestHeader != null ? closeSessionRequest._requestHeader : new RequestHeader());
        encoder.encodeBoolean("DeleteSubscriptions", closeSessionRequest._deleteSubscriptions);
    }

    public static CloseSessionRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Boolean _deleteSubscriptions = decoder.decodeBoolean("DeleteSubscriptions");

        return new CloseSessionRequest(_requestHeader, _deleteSubscriptions);
    }

    static {
        DelegateRegistry.registerEncoder(CloseSessionRequest::encode, CloseSessionRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CloseSessionRequest::decode, CloseSessionRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
