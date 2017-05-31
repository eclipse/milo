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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ActivateSessionRequest")
public class ActivateSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ActivateSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ActivateSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ActivateSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final SignatureData _clientSignature;
    protected final SignedSoftwareCertificate[] _clientSoftwareCertificates;
    protected final String[] _localeIds;
    protected final ExtensionObject _userIdentityToken;
    protected final SignatureData _userTokenSignature;

    public ActivateSessionRequest() {
        this._requestHeader = null;
        this._clientSignature = null;
        this._clientSoftwareCertificates = null;
        this._localeIds = null;
        this._userIdentityToken = null;
        this._userTokenSignature = null;
    }

    public ActivateSessionRequest(RequestHeader _requestHeader, SignatureData _clientSignature, SignedSoftwareCertificate[] _clientSoftwareCertificates, String[] _localeIds, ExtensionObject _userIdentityToken, SignatureData _userTokenSignature) {
        this._requestHeader = _requestHeader;
        this._clientSignature = _clientSignature;
        this._clientSoftwareCertificates = _clientSoftwareCertificates;
        this._localeIds = _localeIds;
        this._userIdentityToken = _userIdentityToken;
        this._userTokenSignature = _userTokenSignature;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public SignatureData getClientSignature() { return _clientSignature; }

    @Nullable
    public SignedSoftwareCertificate[] getClientSoftwareCertificates() { return _clientSoftwareCertificates; }

    @Nullable
    public String[] getLocaleIds() { return _localeIds; }

    public ExtensionObject getUserIdentityToken() { return _userIdentityToken; }

    public SignatureData getUserTokenSignature() { return _userTokenSignature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("ClientSignature", _clientSignature)
            .add("ClientSoftwareCertificates", _clientSoftwareCertificates)
            .add("LocaleIds", _localeIds)
            .add("UserIdentityToken", _userIdentityToken)
            .add("UserTokenSignature", _userTokenSignature)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ActivateSessionRequest> {
        @Override
        public ActivateSessionRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            SignatureData _clientSignature = (SignatureData) context.decode(SignatureData.BinaryEncodingId, reader);
            SignedSoftwareCertificate[] _clientSoftwareCertificates =
                reader.readArray(
                    () -> (SignedSoftwareCertificate) context.decode(
                        SignedSoftwareCertificate.BinaryEncodingId, reader),
                    SignedSoftwareCertificate.class
                );
            String[] _localeIds = reader.readArray(reader::readString, String.class);
            ExtensionObject _userIdentityToken = reader.readExtensionObject();
            SignatureData _userTokenSignature = (SignatureData) context.decode(SignatureData.BinaryEncodingId, reader);

            return new ActivateSessionRequest(_requestHeader, _clientSignature, _clientSoftwareCertificates, _localeIds, _userIdentityToken, _userTokenSignature);
        }

        @Override
        public void encode(SerializationContext context, ActivateSessionRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            context.encode(SignatureData.BinaryEncodingId, encodable._clientSignature, writer);
            writer.writeArray(
                encodable._clientSoftwareCertificates,
                e -> context.encode(SignedSoftwareCertificate.BinaryEncodingId, e, writer)
            );
            writer.writeArray(encodable._localeIds, writer::writeString);
            writer.writeExtensionObject(encodable._userIdentityToken);
            context.encode(SignatureData.BinaryEncodingId, encodable._userTokenSignature, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ActivateSessionRequest> {
        @Override
        public ActivateSessionRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            SignatureData _clientSignature = (SignatureData) context.decode(SignatureData.XmlEncodingId, reader);
            SignedSoftwareCertificate[] _clientSoftwareCertificates =
                reader.readArray(
                    "ClientSoftwareCertificates",
                    f -> (SignedSoftwareCertificate) context.decode(
                        SignedSoftwareCertificate.XmlEncodingId, reader),
                    SignedSoftwareCertificate.class
                );
            String[] _localeIds = reader.readArray("LocaleIds", reader::readString, String.class);
            ExtensionObject _userIdentityToken = reader.readExtensionObject("UserIdentityToken");
            SignatureData _userTokenSignature = (SignatureData) context.decode(SignatureData.XmlEncodingId, reader);

            return new ActivateSessionRequest(_requestHeader, _clientSignature, _clientSoftwareCertificates, _localeIds, _userIdentityToken, _userTokenSignature);
        }

        @Override
        public void encode(SerializationContext context, ActivateSessionRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            context.encode(SignatureData.XmlEncodingId, encodable._clientSignature, writer);
            writer.writeArray(
                "ClientSoftwareCertificates",
                encodable._clientSoftwareCertificates,
                (f, e) -> context.encode(SignedSoftwareCertificate.XmlEncodingId, e, writer)
            );
            writer.writeArray("LocaleIds", encodable._localeIds, writer::writeString);
            writer.writeExtensionObject("UserIdentityToken", encodable._userIdentityToken);
            context.encode(SignatureData.XmlEncodingId, encodable._userTokenSignature, writer);
        }
    }

}
