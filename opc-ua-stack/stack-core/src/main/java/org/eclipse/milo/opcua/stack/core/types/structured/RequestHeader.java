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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("RequestHeader")
public class RequestHeader implements UaStructure {

    public static final NodeId TypeId = Identifiers.RequestHeader;
    public static final NodeId BinaryEncodingId = Identifiers.RequestHeader_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RequestHeader_Encoding_DefaultXml;

    protected final NodeId _authenticationToken;
    protected final DateTime _timestamp;
    protected final UInteger _requestHandle;
    protected final UInteger _returnDiagnostics;
    protected final String _auditEntryId;
    protected final UInteger _timeoutHint;
    protected final ExtensionObject _additionalHeader;

    public RequestHeader() {
        this._authenticationToken = null;
        this._timestamp = null;
        this._requestHandle = null;
        this._returnDiagnostics = null;
        this._auditEntryId = null;
        this._timeoutHint = null;
        this._additionalHeader = null;
    }

    public RequestHeader(NodeId _authenticationToken, DateTime _timestamp, UInteger _requestHandle, UInteger _returnDiagnostics, String _auditEntryId, UInteger _timeoutHint, ExtensionObject _additionalHeader) {
        this._authenticationToken = _authenticationToken;
        this._timestamp = _timestamp;
        this._requestHandle = _requestHandle;
        this._returnDiagnostics = _returnDiagnostics;
        this._auditEntryId = _auditEntryId;
        this._timeoutHint = _timeoutHint;
        this._additionalHeader = _additionalHeader;
    }

    public NodeId getAuthenticationToken() { return _authenticationToken; }

    public DateTime getTimestamp() { return _timestamp; }

    public UInteger getRequestHandle() { return _requestHandle; }

    public UInteger getReturnDiagnostics() { return _returnDiagnostics; }

    public String getAuditEntryId() { return _auditEntryId; }

    public UInteger getTimeoutHint() { return _timeoutHint; }

    public ExtensionObject getAdditionalHeader() { return _additionalHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RequestHeader requestHeader, UaEncoder encoder) {
        encoder.encodeNodeId("AuthenticationToken", requestHeader._authenticationToken);
        encoder.encodeDateTime("Timestamp", requestHeader._timestamp);
        encoder.encodeUInt32("RequestHandle", requestHeader._requestHandle);
        encoder.encodeUInt32("ReturnDiagnostics", requestHeader._returnDiagnostics);
        encoder.encodeString("AuditEntryId", requestHeader._auditEntryId);
        encoder.encodeUInt32("TimeoutHint", requestHeader._timeoutHint);
        encoder.encodeExtensionObject("AdditionalHeader", requestHeader._additionalHeader);
    }

    public static RequestHeader decode(UaDecoder decoder) {
        NodeId _authenticationToken = decoder.decodeNodeId("AuthenticationToken");
        DateTime _timestamp = decoder.decodeDateTime("Timestamp");
        UInteger _requestHandle = decoder.decodeUInt32("RequestHandle");
        UInteger _returnDiagnostics = decoder.decodeUInt32("ReturnDiagnostics");
        String _auditEntryId = decoder.decodeString("AuditEntryId");
        UInteger _timeoutHint = decoder.decodeUInt32("TimeoutHint");
        ExtensionObject _additionalHeader = decoder.decodeExtensionObject("AdditionalHeader");

        return new RequestHeader(_authenticationToken, _timestamp, _requestHandle, _returnDiagnostics, _auditEntryId, _timeoutHint, _additionalHeader);
    }

    static {
        DelegateRegistry.registerEncoder(RequestHeader::encode, RequestHeader.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RequestHeader::decode, RequestHeader.class, BinaryEncodingId, XmlEncodingId);
    }

}
