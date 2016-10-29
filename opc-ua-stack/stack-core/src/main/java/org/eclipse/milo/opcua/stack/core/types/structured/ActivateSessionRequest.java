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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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

    public SignedSoftwareCertificate[] getClientSoftwareCertificates() { return _clientSoftwareCertificates; }

    public String[] getLocaleIds() { return _localeIds; }

    public ExtensionObject getUserIdentityToken() { return _userIdentityToken; }

    public SignatureData getUserTokenSignature() { return _userTokenSignature; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ActivateSessionRequest activateSessionRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", activateSessionRequest._requestHeader != null ? activateSessionRequest._requestHeader : new RequestHeader());
        encoder.encodeSerializable("ClientSignature", activateSessionRequest._clientSignature != null ? activateSessionRequest._clientSignature : new SignatureData());
        encoder.encodeArray("ClientSoftwareCertificates", activateSessionRequest._clientSoftwareCertificates, encoder::encodeSerializable);
        encoder.encodeArray("LocaleIds", activateSessionRequest._localeIds, encoder::encodeString);
        encoder.encodeExtensionObject("UserIdentityToken", activateSessionRequest._userIdentityToken);
        encoder.encodeSerializable("UserTokenSignature", activateSessionRequest._userTokenSignature != null ? activateSessionRequest._userTokenSignature : new SignatureData());
    }

    public static ActivateSessionRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        SignatureData _clientSignature = decoder.decodeSerializable("ClientSignature", SignatureData.class);
        SignedSoftwareCertificate[] _clientSoftwareCertificates = decoder.decodeArray("ClientSoftwareCertificates", decoder::decodeSerializable, SignedSoftwareCertificate.class);
        String[] _localeIds = decoder.decodeArray("LocaleIds", decoder::decodeString, String.class);
        ExtensionObject _userIdentityToken = decoder.decodeExtensionObject("UserIdentityToken");
        SignatureData _userTokenSignature = decoder.decodeSerializable("UserTokenSignature", SignatureData.class);

        return new ActivateSessionRequest(_requestHeader, _clientSignature, _clientSoftwareCertificates, _localeIds, _userIdentityToken, _userTokenSignature);
    }

    static {
        DelegateRegistry.registerEncoder(ActivateSessionRequest::encode, ActivateSessionRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ActivateSessionRequest::decode, ActivateSessionRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
