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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("TransferResult")
public class TransferResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.TransferResult;
    public static final NodeId BinaryEncodingId = Identifiers.TransferResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final UInteger[] _availableSequenceNumbers;

    public TransferResult() {
        this._statusCode = null;
        this._availableSequenceNumbers = null;
    }

    public TransferResult(StatusCode _statusCode, UInteger[] _availableSequenceNumbers) {
        this._statusCode = _statusCode;
        this._availableSequenceNumbers = _availableSequenceNumbers;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public UInteger[] getAvailableSequenceNumbers() { return _availableSequenceNumbers; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TransferResult transferResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", transferResult._statusCode);
        encoder.encodeArray("AvailableSequenceNumbers", transferResult._availableSequenceNumbers, encoder::encodeUInt32);
    }

    public static TransferResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        UInteger[] _availableSequenceNumbers = decoder.decodeArray("AvailableSequenceNumbers", decoder::decodeUInt32, UInteger.class);

        return new TransferResult(_statusCode, _availableSequenceNumbers);
    }

    static {
        DelegateRegistry.registerEncoder(TransferResult::encode, TransferResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TransferResult::decode, TransferResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
