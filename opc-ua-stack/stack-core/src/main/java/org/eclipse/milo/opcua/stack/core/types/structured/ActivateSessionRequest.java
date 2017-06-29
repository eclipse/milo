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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ActivateSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ActivateSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ActivateSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ActivateSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final SignatureData clientSignature;
    protected final SignedSoftwareCertificate[] clientSoftwareCertificates;
    protected final String[] localeIds;
    protected final ExtensionObject userIdentityToken;
    protected final SignatureData userTokenSignature;

    public ActivateSessionRequest() {
        this.requestHeader = null;
        this.clientSignature = null;
        this.clientSoftwareCertificates = null;
        this.localeIds = null;
        this.userIdentityToken = null;
        this.userTokenSignature = null;
    }

    public ActivateSessionRequest(RequestHeader requestHeader, SignatureData clientSignature, SignedSoftwareCertificate[] clientSoftwareCertificates, String[] localeIds, ExtensionObject userIdentityToken, SignatureData userTokenSignature) {
        this.requestHeader = requestHeader;
        this.clientSignature = clientSignature;
        this.clientSoftwareCertificates = clientSoftwareCertificates;
        this.localeIds = localeIds;
        this.userIdentityToken = userIdentityToken;
        this.userTokenSignature = userTokenSignature;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public SignatureData getClientSignature() { return clientSignature; }

    @Nullable
    public SignedSoftwareCertificate[] getClientSoftwareCertificates() { return clientSoftwareCertificates; }

    @Nullable
    public String[] getLocaleIds() { return localeIds; }

    public ExtensionObject getUserIdentityToken() { return userIdentityToken; }

    public SignatureData getUserTokenSignature() { return userTokenSignature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("ClientSignature", clientSignature)
            .add("ClientSoftwareCertificates", clientSoftwareCertificates)
            .add("LocaleIds", localeIds)
            .add("UserIdentityToken", userIdentityToken)
            .add("UserTokenSignature", userTokenSignature)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ActivateSessionRequest> {

        @Override
        public Class<ActivateSessionRequest> getType() {
            return ActivateSessionRequest.class;
        }

        @Override
        public ActivateSessionRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            SignatureData clientSignature = (SignatureData) decoder.readBuiltinStruct("ClientSignature", SignatureData.class);
            SignedSoftwareCertificate[] clientSoftwareCertificates =
                decoder.readBuiltinStructArray(
                    "ClientSoftwareCertificates",
                    SignedSoftwareCertificate.class
                );
            String[] localeIds = decoder.readArray("LocaleIds", decoder::readString, String.class);
            ExtensionObject userIdentityToken = decoder.readExtensionObject("UserIdentityToken");
            SignatureData userTokenSignature = (SignatureData) decoder.readBuiltinStruct("UserTokenSignature", SignatureData.class);

            return new ActivateSessionRequest(requestHeader, clientSignature, clientSoftwareCertificates, localeIds, userIdentityToken, userTokenSignature);
        }

        @Override
        public void encode(ActivateSessionRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("ClientSignature", value.clientSignature, SignatureData.class);
            encoder.writeBuiltinStructArray(
                "ClientSoftwareCertificates",
                value.clientSoftwareCertificates,
                SignedSoftwareCertificate.class
            );
            encoder.writeArray("LocaleIds", value.localeIds, encoder::writeString);
            encoder.writeExtensionObject("UserIdentityToken", value.userIdentityToken);
            encoder.writeBuiltinStruct("UserTokenSignature", value.userTokenSignature, SignatureData.class);
        }
    }

}
