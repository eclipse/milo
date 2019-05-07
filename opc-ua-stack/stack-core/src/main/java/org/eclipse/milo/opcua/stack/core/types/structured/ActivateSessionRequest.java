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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ActivateSessionRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=465");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=467");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=466");

    private final RequestHeader requestHeader;

    private final SignatureData clientSignature;

    private final SignedSoftwareCertificate[] clientSoftwareCertificates;

    private final String[] localeIds;

    private final ExtensionObject userIdentityToken;

    private final SignatureData userTokenSignature;

    public ActivateSessionRequest(RequestHeader requestHeader, SignatureData clientSignature,
                                  SignedSoftwareCertificate[] clientSoftwareCertificates, String[] localeIds,
                                  ExtensionObject userIdentityToken, SignatureData userTokenSignature) {
        this.requestHeader = requestHeader;
        this.clientSignature = clientSignature;
        this.clientSoftwareCertificates = clientSoftwareCertificates;
        this.localeIds = localeIds;
        this.userIdentityToken = userIdentityToken;
        this.userTokenSignature = userTokenSignature;
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

    public SignatureData getClientSignature() {
        return clientSignature;
    }

    public SignedSoftwareCertificate[] getClientSoftwareCertificates() {
        return clientSoftwareCertificates;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public ExtensionObject getUserIdentityToken() {
        return userIdentityToken;
    }

    public SignatureData getUserTokenSignature() {
        return userTokenSignature;
    }

    public static final class Codec extends GenericDataTypeCodec<ActivateSessionRequest> {
        @Override
        public Class<ActivateSessionRequest> getType() {
            return ActivateSessionRequest.class;
        }

        @Override
        public ActivateSessionRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            SignatureData clientSignature = (SignatureData) decoder.readStruct("ClientSignature", SignatureData.TYPE_ID);
            SignedSoftwareCertificate[] clientSoftwareCertificates = (SignedSoftwareCertificate[]) decoder.readStructArray("ClientSoftwareCertificates", SignedSoftwareCertificate.TYPE_ID);
            String[] localeIds = decoder.readStringArray("LocaleIds");
            ExtensionObject userIdentityToken = decoder.readExtensionObject("UserIdentityToken");
            SignatureData userTokenSignature = (SignatureData) decoder.readStruct("UserTokenSignature", SignatureData.TYPE_ID);
            return new ActivateSessionRequest(requestHeader, clientSignature, clientSoftwareCertificates, localeIds, userIdentityToken, userTokenSignature);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ActivateSessionRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStruct("ClientSignature", value.getClientSignature(), SignatureData.TYPE_ID);
            encoder.writeStructArray("ClientSoftwareCertificates", value.getClientSoftwareCertificates(), SignedSoftwareCertificate.TYPE_ID);
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeExtensionObject("UserIdentityToken", value.getUserIdentityToken());
            encoder.writeStruct("UserTokenSignature", value.getUserTokenSignature(), SignatureData.TYPE_ID);
        }
    }
}
