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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class OpenSecureChannelResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=447");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=449");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=448");

    private final ResponseHeader responseHeader;

    private final UInteger serverProtocolVersion;

    private final ChannelSecurityToken securityToken;

    private final ByteString serverNonce;

    public OpenSecureChannelResponse(ResponseHeader responseHeader, UInteger serverProtocolVersion,
                                     ChannelSecurityToken securityToken, ByteString serverNonce) {
        this.responseHeader = responseHeader;
        this.serverProtocolVersion = serverProtocolVersion;
        this.securityToken = securityToken;
        this.serverNonce = serverNonce;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public UInteger getServerProtocolVersion() {
        return serverProtocolVersion;
    }

    public ChannelSecurityToken getSecurityToken() {
        return securityToken;
    }

    public ByteString getServerNonce() {
        return serverNonce;
    }

    public static final class Codec extends GenericDataTypeCodec<OpenSecureChannelResponse> {
        @Override
        public Class<OpenSecureChannelResponse> getType() {
            return OpenSecureChannelResponse.class;
        }

        @Override
        public OpenSecureChannelResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            UInteger serverProtocolVersion = decoder.readUInt32("ServerProtocolVersion");
            ChannelSecurityToken securityToken = (ChannelSecurityToken) decoder.readStruct("SecurityToken", ChannelSecurityToken.TYPE_ID);
            ByteString serverNonce = decoder.readByteString("ServerNonce");
            return new OpenSecureChannelResponse(responseHeader, serverProtocolVersion, securityToken, serverNonce);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           OpenSecureChannelResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeUInt32("ServerProtocolVersion", value.getServerProtocolVersion());
            encoder.writeStruct("SecurityToken", value.getSecurityToken(), ChannelSecurityToken.TYPE_ID);
            encoder.writeByteString("ServerNonce", value.getServerNonce());
        }
    }
}
