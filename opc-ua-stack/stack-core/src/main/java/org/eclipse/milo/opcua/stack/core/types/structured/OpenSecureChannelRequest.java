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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class OpenSecureChannelRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=444");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=446");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=445");

    private final RequestHeader requestHeader;

    private final UInteger clientProtocolVersion;

    private final SecurityTokenRequestType requestType;

    private final MessageSecurityMode securityMode;

    private final ByteString clientNonce;

    private final UInteger requestedLifetime;

    public OpenSecureChannelRequest(RequestHeader requestHeader, UInteger clientProtocolVersion,
                                    SecurityTokenRequestType requestType, MessageSecurityMode securityMode,
                                    ByteString clientNonce, UInteger requestedLifetime) {
        this.requestHeader = requestHeader;
        this.clientProtocolVersion = clientProtocolVersion;
        this.requestType = requestType;
        this.securityMode = securityMode;
        this.clientNonce = clientNonce;
        this.requestedLifetime = requestedLifetime;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public UInteger getClientProtocolVersion() {
        return clientProtocolVersion;
    }

    public SecurityTokenRequestType getRequestType() {
        return requestType;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public ByteString getClientNonce() {
        return clientNonce;
    }

    public UInteger getRequestedLifetime() {
        return requestedLifetime;
    }

    public static final class Codec extends GenericDataTypeCodec<OpenSecureChannelRequest> {
        @Override
        public Class<OpenSecureChannelRequest> getType() {
            return OpenSecureChannelRequest.class;
        }

        @Override
        public OpenSecureChannelRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger clientProtocolVersion = decoder.readUInt32("ClientProtocolVersion");
            SecurityTokenRequestType requestType = decoder.readEnum("RequestType", SecurityTokenRequestType.class);
            MessageSecurityMode securityMode = decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            ByteString clientNonce = decoder.readByteString("ClientNonce");
            UInteger requestedLifetime = decoder.readUInt32("RequestedLifetime");
            return new OpenSecureChannelRequest(requestHeader, clientProtocolVersion, requestType, securityMode, clientNonce, requestedLifetime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           OpenSecureChannelRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("ClientProtocolVersion", value.getClientProtocolVersion());
            encoder.writeEnum("RequestType", value.getRequestType());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeByteString("ClientNonce", value.getClientNonce());
            encoder.writeUInt32("RequestedLifetime", value.getRequestedLifetime());
        }
    }
}
