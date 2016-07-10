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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RepublishResponse")
public class RepublishResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RepublishResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RepublishResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RepublishResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final NotificationMessage _notificationMessage;

    public RepublishResponse() {
        this._responseHeader = null;
        this._notificationMessage = null;
    }

    public RepublishResponse(ResponseHeader _responseHeader, NotificationMessage _notificationMessage) {
        this._responseHeader = _responseHeader;
        this._notificationMessage = _notificationMessage;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public NotificationMessage getNotificationMessage() { return _notificationMessage; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RepublishResponse republishResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", republishResponse._responseHeader != null ? republishResponse._responseHeader : new ResponseHeader());
        encoder.encodeSerializable("NotificationMessage", republishResponse._notificationMessage != null ? republishResponse._notificationMessage : new NotificationMessage());
    }

    public static RepublishResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        NotificationMessage _notificationMessage = decoder.decodeSerializable("NotificationMessage", NotificationMessage.class);

        return new RepublishResponse(_responseHeader, _notificationMessage);
    }

    static {
        DelegateRegistry.registerEncoder(RepublishResponse::encode, RepublishResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RepublishResponse::decode, RepublishResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
