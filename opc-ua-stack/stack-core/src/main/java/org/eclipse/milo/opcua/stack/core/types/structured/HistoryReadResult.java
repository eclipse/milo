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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("HistoryReadResult")
public class HistoryReadResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final ByteString _continuationPoint;
    protected final ExtensionObject _historyData;

    public HistoryReadResult() {
        this._statusCode = null;
        this._continuationPoint = null;
        this._historyData = null;
    }

    public HistoryReadResult(StatusCode _statusCode, ByteString _continuationPoint, ExtensionObject _historyData) {
        this._statusCode = _statusCode;
        this._continuationPoint = _continuationPoint;
        this._historyData = _historyData;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    public ExtensionObject getHistoryData() { return _historyData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryReadResult historyReadResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", historyReadResult._statusCode);
        encoder.encodeByteString("ContinuationPoint", historyReadResult._continuationPoint);
        encoder.encodeExtensionObject("HistoryData", historyReadResult._historyData);
    }

    public static HistoryReadResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        ByteString _continuationPoint = decoder.decodeByteString("ContinuationPoint");
        ExtensionObject _historyData = decoder.decodeExtensionObject("HistoryData");

        return new HistoryReadResult(_statusCode, _continuationPoint, _historyData);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryReadResult::encode, HistoryReadResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryReadResult::decode, HistoryReadResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
