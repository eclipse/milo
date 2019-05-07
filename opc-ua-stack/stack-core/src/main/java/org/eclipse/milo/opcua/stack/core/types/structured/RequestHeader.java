/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RequestHeader extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=389");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=391");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=390");

    private final NodeId authenticationToken;

    private final DateTime timestamp;

    private final UInteger requestHandle;

    private final UInteger returnDiagnostics;

    private final String auditEntryId;

    private final UInteger timeoutHint;

    private final ExtensionObject additionalHeader;

    public RequestHeader(NodeId authenticationToken, DateTime timestamp, UInteger requestHandle,
                         UInteger returnDiagnostics, String auditEntryId, UInteger timeoutHint,
                         ExtensionObject additionalHeader) {
        this.authenticationToken = authenticationToken;
        this.timestamp = timestamp;
        this.requestHandle = requestHandle;
        this.returnDiagnostics = returnDiagnostics;
        this.auditEntryId = auditEntryId;
        this.timeoutHint = timeoutHint;
        this.additionalHeader = additionalHeader;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public NodeId getAuthenticationToken() {
        return authenticationToken;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public UInteger getRequestHandle() {
        return requestHandle;
    }

    public UInteger getReturnDiagnostics() {
        return returnDiagnostics;
    }

    public String getAuditEntryId() {
        return auditEntryId;
    }

    public UInteger getTimeoutHint() {
        return timeoutHint;
    }

    public ExtensionObject getAdditionalHeader() {
        return additionalHeader;
    }

    public static final class Codec extends GenericDataTypeCodec<RequestHeader> {
        @Override
        public Class<RequestHeader> getType() {
            return RequestHeader.class;
        }

        @Override
        public RequestHeader decode(SerializationContext context, UaDecoder decoder) {
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
        public void encode(SerializationContext context, UaEncoder encoder, RequestHeader value) {
            encoder.writeNodeId("AuthenticationToken", value.getAuthenticationToken());
            encoder.writeDateTime("Timestamp", value.getTimestamp());
            encoder.writeUInt32("RequestHandle", value.getRequestHandle());
            encoder.writeUInt32("ReturnDiagnostics", value.getReturnDiagnostics());
            encoder.writeString("AuditEntryId", value.getAuditEntryId());
            encoder.writeUInt32("TimeoutHint", value.getTimeoutHint());
            encoder.writeExtensionObject("AdditionalHeader", value.getAdditionalHeader());
        }
    }
}
