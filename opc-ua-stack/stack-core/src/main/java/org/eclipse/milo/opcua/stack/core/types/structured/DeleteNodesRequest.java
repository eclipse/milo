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

@UaDataType("DeleteNodesRequest")
public class DeleteNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final DeleteNodesItem[] _nodesToDelete;

    public DeleteNodesRequest() {
        this._requestHeader = null;
        this._nodesToDelete = null;
    }

    public DeleteNodesRequest(RequestHeader _requestHeader, DeleteNodesItem[] _nodesToDelete) {
        this._requestHeader = _requestHeader;
        this._nodesToDelete = _nodesToDelete;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public DeleteNodesItem[] getNodesToDelete() { return _nodesToDelete; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteNodesRequest deleteNodesRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", deleteNodesRequest._requestHeader != null ? deleteNodesRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("NodesToDelete", deleteNodesRequest._nodesToDelete, encoder::encodeSerializable);
    }

    public static DeleteNodesRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        DeleteNodesItem[] _nodesToDelete = decoder.decodeArray("NodesToDelete", decoder::decodeSerializable, DeleteNodesItem.class);

        return new DeleteNodesRequest(_requestHeader, _nodesToDelete);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteNodesRequest::encode, DeleteNodesRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteNodesRequest::decode, DeleteNodesRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
