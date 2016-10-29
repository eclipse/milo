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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("HistoryReadRequest")
public class HistoryReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ExtensionObject _historyReadDetails;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final Boolean _releaseContinuationPoints;
    protected final HistoryReadValueId[] _nodesToRead;

    public HistoryReadRequest() {
        this._requestHeader = null;
        this._historyReadDetails = null;
        this._timestampsToReturn = null;
        this._releaseContinuationPoints = null;
        this._nodesToRead = null;
    }

    public HistoryReadRequest(RequestHeader _requestHeader, ExtensionObject _historyReadDetails, TimestampsToReturn _timestampsToReturn, Boolean _releaseContinuationPoints, HistoryReadValueId[] _nodesToRead) {
        this._requestHeader = _requestHeader;
        this._historyReadDetails = _historyReadDetails;
        this._timestampsToReturn = _timestampsToReturn;
        this._releaseContinuationPoints = _releaseContinuationPoints;
        this._nodesToRead = _nodesToRead;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ExtensionObject getHistoryReadDetails() { return _historyReadDetails; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    public Boolean getReleaseContinuationPoints() { return _releaseContinuationPoints; }

    public HistoryReadValueId[] getNodesToRead() { return _nodesToRead; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryReadRequest historyReadRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", historyReadRequest._requestHeader != null ? historyReadRequest._requestHeader : new RequestHeader());
        encoder.encodeExtensionObject("HistoryReadDetails", historyReadRequest._historyReadDetails);
        encoder.encodeEnumeration("TimestampsToReturn", historyReadRequest._timestampsToReturn);
        encoder.encodeBoolean("ReleaseContinuationPoints", historyReadRequest._releaseContinuationPoints);
        encoder.encodeArray("NodesToRead", historyReadRequest._nodesToRead, encoder::encodeSerializable);
    }

    public static HistoryReadRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        ExtensionObject _historyReadDetails = decoder.decodeExtensionObject("HistoryReadDetails");
        TimestampsToReturn _timestampsToReturn = decoder.decodeEnumeration("TimestampsToReturn", TimestampsToReturn.class);
        Boolean _releaseContinuationPoints = decoder.decodeBoolean("ReleaseContinuationPoints");
        HistoryReadValueId[] _nodesToRead = decoder.decodeArray("NodesToRead", decoder::decodeSerializable, HistoryReadValueId.class);

        return new HistoryReadRequest(_requestHeader, _historyReadDetails, _timestampsToReturn, _releaseContinuationPoints, _nodesToRead);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryReadRequest::encode, HistoryReadRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryReadRequest::decode, HistoryReadRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
