/*
 * Copyright (c) 2017 Kevin Herron
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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("AuthenticationToken", _authenticationToken)
            .add("Timestamp", _timestamp)
            .add("RequestHandle", _requestHandle)
            .add("ReturnDiagnostics", _returnDiagnostics)
            .add("AuditEntryId", _auditEntryId)
            .add("TimeoutHint", _timeoutHint)
            .add("AdditionalHeader", _additionalHeader)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RequestHeader> {
        @Override
        public RequestHeader decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _authenticationToken = reader.readNodeId();
            DateTime _timestamp = reader.readDateTime();
            UInteger _requestHandle = reader.readUInt32();
            UInteger _returnDiagnostics = reader.readUInt32();
            String _auditEntryId = reader.readString();
            UInteger _timeoutHint = reader.readUInt32();
            ExtensionObject _additionalHeader = reader.readExtensionObject();

            return new RequestHeader(_authenticationToken, _timestamp, _requestHandle, _returnDiagnostics, _auditEntryId, _timeoutHint, _additionalHeader);
        }

        @Override
        public void encode(SerializationContext context, RequestHeader encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._authenticationToken);
            writer.writeDateTime(encodable._timestamp);
            writer.writeUInt32(encodable._requestHandle);
            writer.writeUInt32(encodable._returnDiagnostics);
            writer.writeString(encodable._auditEntryId);
            writer.writeUInt32(encodable._timeoutHint);
            writer.writeExtensionObject(encodable._additionalHeader);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RequestHeader> {
        @Override
        public RequestHeader decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _authenticationToken = reader.readNodeId("AuthenticationToken");
            DateTime _timestamp = reader.readDateTime("Timestamp");
            UInteger _requestHandle = reader.readUInt32("RequestHandle");
            UInteger _returnDiagnostics = reader.readUInt32("ReturnDiagnostics");
            String _auditEntryId = reader.readString("AuditEntryId");
            UInteger _timeoutHint = reader.readUInt32("TimeoutHint");
            ExtensionObject _additionalHeader = reader.readExtensionObject("AdditionalHeader");

            return new RequestHeader(_authenticationToken, _timestamp, _requestHandle, _returnDiagnostics, _auditEntryId, _timeoutHint, _additionalHeader);
        }

        @Override
        public void encode(SerializationContext context, RequestHeader encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("AuthenticationToken", encodable._authenticationToken);
            writer.writeDateTime("Timestamp", encodable._timestamp);
            writer.writeUInt32("RequestHandle", encodable._requestHandle);
            writer.writeUInt32("ReturnDiagnostics", encodable._returnDiagnostics);
            writer.writeString("AuditEntryId", encodable._auditEntryId);
            writer.writeUInt32("TimeoutHint", encodable._timeoutHint);
            writer.writeExtensionObject("AdditionalHeader", encodable._additionalHeader);
        }
    }

}
