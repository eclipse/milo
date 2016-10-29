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
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("StatusResult")
public class StatusResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.StatusResult;
    public static final NodeId BinaryEncodingId = Identifiers.StatusResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.StatusResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final DiagnosticInfo _diagnosticInfo;

    public StatusResult() {
        this._statusCode = null;
        this._diagnosticInfo = null;
    }

    public StatusResult(StatusCode _statusCode, DiagnosticInfo _diagnosticInfo) {
        this._statusCode = _statusCode;
        this._diagnosticInfo = _diagnosticInfo;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public DiagnosticInfo getDiagnosticInfo() { return _diagnosticInfo; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(StatusResult statusResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", statusResult._statusCode);
        encoder.encodeDiagnosticInfo("DiagnosticInfo", statusResult._diagnosticInfo);
    }

    public static StatusResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        DiagnosticInfo _diagnosticInfo = decoder.decodeDiagnosticInfo("DiagnosticInfo");

        return new StatusResult(_statusCode, _diagnosticInfo);
    }

    static {
        DelegateRegistry.registerEncoder(StatusResult::encode, StatusResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(StatusResult::decode, StatusResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
