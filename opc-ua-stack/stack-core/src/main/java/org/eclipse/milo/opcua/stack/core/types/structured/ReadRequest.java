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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("ReadRequest")
public class ReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Double _maxAge;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final ReadValueId[] _nodesToRead;

    public ReadRequest() {
        this._requestHeader = null;
        this._maxAge = null;
        this._timestampsToReturn = null;
        this._nodesToRead = null;
    }

    public ReadRequest(RequestHeader _requestHeader, Double _maxAge, TimestampsToReturn _timestampsToReturn, ReadValueId[] _nodesToRead) {
        this._requestHeader = _requestHeader;
        this._maxAge = _maxAge;
        this._timestampsToReturn = _timestampsToReturn;
        this._nodesToRead = _nodesToRead;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Double getMaxAge() { return _maxAge; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    public ReadValueId[] getNodesToRead() { return _nodesToRead; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ReadRequest readRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", readRequest._requestHeader != null ? readRequest._requestHeader : new RequestHeader());
        encoder.encodeDouble("MaxAge", readRequest._maxAge);
        encoder.encodeEnumeration("TimestampsToReturn", readRequest._timestampsToReturn);
        encoder.encodeArray("NodesToRead", readRequest._nodesToRead, encoder::encodeSerializable);
    }

    public static ReadRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Double _maxAge = decoder.decodeDouble("MaxAge");
        TimestampsToReturn _timestampsToReturn = decoder.decodeEnumeration("TimestampsToReturn", TimestampsToReturn.class);
        ReadValueId[] _nodesToRead = decoder.decodeArray("NodesToRead", decoder::decodeSerializable, ReadValueId.class);

        return new ReadRequest(_requestHeader, _maxAge, _timestampsToReturn, _nodesToRead);
    }

    static {
        DelegateRegistry.registerEncoder(ReadRequest::encode, ReadRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ReadRequest::decode, ReadRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
