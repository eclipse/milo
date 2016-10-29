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

@UaDataType("WriteRequest")
public class WriteRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.WriteRequest;
    public static final NodeId BinaryEncodingId = Identifiers.WriteRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.WriteRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final WriteValue[] _nodesToWrite;

    public WriteRequest() {
        this._requestHeader = null;
        this._nodesToWrite = null;
    }

    public WriteRequest(RequestHeader _requestHeader, WriteValue[] _nodesToWrite) {
        this._requestHeader = _requestHeader;
        this._nodesToWrite = _nodesToWrite;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public WriteValue[] getNodesToWrite() { return _nodesToWrite; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(WriteRequest writeRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", writeRequest._requestHeader != null ? writeRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("NodesToWrite", writeRequest._nodesToWrite, encoder::encodeSerializable);
    }

    public static WriteRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        WriteValue[] _nodesToWrite = decoder.decodeArray("NodesToWrite", decoder::decodeSerializable, WriteValue.class);

        return new WriteRequest(_requestHeader, _nodesToWrite);
    }

    static {
        DelegateRegistry.registerEncoder(WriteRequest::encode, WriteRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(WriteRequest::decode, WriteRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
