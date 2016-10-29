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

@UaDataType("DeleteReferencesRequest")
public class DeleteReferencesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteReferencesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteReferencesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final DeleteReferencesItem[] _referencesToDelete;

    public DeleteReferencesRequest() {
        this._requestHeader = null;
        this._referencesToDelete = null;
    }

    public DeleteReferencesRequest(RequestHeader _requestHeader, DeleteReferencesItem[] _referencesToDelete) {
        this._requestHeader = _requestHeader;
        this._referencesToDelete = _referencesToDelete;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public DeleteReferencesItem[] getReferencesToDelete() { return _referencesToDelete; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteReferencesRequest deleteReferencesRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", deleteReferencesRequest._requestHeader != null ? deleteReferencesRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("ReferencesToDelete", deleteReferencesRequest._referencesToDelete, encoder::encodeSerializable);
    }

    public static DeleteReferencesRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        DeleteReferencesItem[] _referencesToDelete = decoder.decodeArray("ReferencesToDelete", decoder::decodeSerializable, DeleteReferencesItem.class);

        return new DeleteReferencesRequest(_requestHeader, _referencesToDelete);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteReferencesRequest::encode, DeleteReferencesRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteReferencesRequest::decode, DeleteReferencesRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
