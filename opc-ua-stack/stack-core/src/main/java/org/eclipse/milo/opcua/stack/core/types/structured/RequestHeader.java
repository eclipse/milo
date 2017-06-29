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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class RequestHeader implements UaStructure {

    public static final NodeId TypeId = Identifiers.RequestHeader;
    public static final NodeId BinaryEncodingId = Identifiers.RequestHeader_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RequestHeader_Encoding_DefaultXml;

    protected final NodeId authenticationToken;
    protected final DateTime timestamp;
    protected final UInteger requestHandle;
    protected final UInteger returnDiagnostics;
    protected final String auditEntryId;
    protected final UInteger timeoutHint;
    protected final ExtensionObject additionalHeader;

    public RequestHeader() {
        this.authenticationToken = null;
        this.timestamp = null;
        this.requestHandle = null;
        this.returnDiagnostics = null;
        this.auditEntryId = null;
        this.timeoutHint = null;
        this.additionalHeader = null;
    }

    public RequestHeader(NodeId authenticationToken, DateTime timestamp, UInteger requestHandle, UInteger returnDiagnostics, String auditEntryId, UInteger timeoutHint, ExtensionObject additionalHeader) {
        this.authenticationToken = authenticationToken;
        this.timestamp = timestamp;
        this.requestHandle = requestHandle;
        this.returnDiagnostics = returnDiagnostics;
        this.auditEntryId = auditEntryId;
        this.timeoutHint = timeoutHint;
        this.additionalHeader = additionalHeader;
    }

    public NodeId getAuthenticationToken() { return authenticationToken; }

    public DateTime getTimestamp() { return timestamp; }

    public UInteger getRequestHandle() { return requestHandle; }

    public UInteger getReturnDiagnostics() { return returnDiagnostics; }

    public String getAuditEntryId() { return auditEntryId; }

    public UInteger getTimeoutHint() { return timeoutHint; }

    public ExtensionObject getAdditionalHeader() { return additionalHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("AuthenticationToken", authenticationToken)
            .add("Timestamp", timestamp)
            .add("RequestHandle", requestHandle)
            .add("ReturnDiagnostics", returnDiagnostics)
            .add("AuditEntryId", auditEntryId)
            .add("TimeoutHint", timeoutHint)
            .add("AdditionalHeader", additionalHeader)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RequestHeader> {

        @Override
        public Class<RequestHeader> getType() {
            return RequestHeader.class;
        }

        @Override
        public RequestHeader decode(UaDecoder decoder) throws UaSerializationException {
            NodeId authenticationToken = decoder.readNodeId("AuthenticationToken");
            DateTime timestamp = decoder.readDateTime("Timestamp");
            UInteger requestHandle = decoder.readUInt32("RequestHandle");
            UInteger returnDiagnostics = decoder.readUInt32("ReturnDiagnostics");
            String auditEntryId = decoder.readString("AuditEntryId");
            UInteger timeoutHint = decoder.readUInt32("TimeoutHint");
            ExtensionObject additionalHeader = decoder.readExtensionObject("AdditionalHeader");

            return new RequestHeader(authenticationToken, timestamp, requestHandle, returnDiagnostics, auditEntryId, timeoutHint, additionalHeader);
        }

        @Override
        public void encode(RequestHeader value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("AuthenticationToken", value.authenticationToken);
            encoder.writeDateTime("Timestamp", value.timestamp);
            encoder.writeUInt32("RequestHandle", value.requestHandle);
            encoder.writeUInt32("ReturnDiagnostics", value.returnDiagnostics);
            encoder.writeString("AuditEntryId", value.auditEntryId);
            encoder.writeUInt32("TimeoutHint", value.timeoutHint);
            encoder.writeExtensionObject("AdditionalHeader", value.additionalHeader);
        }
    }

}
