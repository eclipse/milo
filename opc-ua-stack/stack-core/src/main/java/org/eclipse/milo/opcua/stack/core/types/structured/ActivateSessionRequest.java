/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.6.3/#5.6.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.6.3/#5.6.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ActivateSessionRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=465");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=467");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=466");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15145");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 467),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("ClientSignature", LocalizedText.NULL_VALUE, new NodeId(0, 456), -1, null, UInteger.valueOf(0), false),
                new StructureField("ClientSoftwareCertificates", LocalizedText.NULL_VALUE, new NodeId(0, 344), 1, null, UInteger.valueOf(0), false),
                new StructureField("LocaleIds", LocalizedText.NULL_VALUE, new NodeId(0, 295), 1, null, UInteger.valueOf(0), false),
                new StructureField("UserIdentityToken", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserTokenSignature", LocalizedText.NULL_VALUE, new NodeId(0, 456), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ActivateSessionRequest> {
        @Override
        public Class<ActivateSessionRequest> getType() {
            return ActivateSessionRequest.class;
        }

        @Override
        public ActivateSessionRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            SignatureData clientSignature = (SignatureData) decoder.readStruct("ClientSignature", SignatureData.TYPE_ID);
            SignedSoftwareCertificate[] clientSoftwareCertificates = (SignedSoftwareCertificate[]) decoder.readStructArray("ClientSoftwareCertificates", SignedSoftwareCertificate.TYPE_ID);
            String[] localeIds = decoder.readStringArray("LocaleIds");
            ExtensionObject userIdentityToken = decoder.readExtensionObject("UserIdentityToken");
            SignatureData userTokenSignature = (SignatureData) decoder.readStruct("UserTokenSignature", SignatureData.TYPE_ID);
            return new ActivateSessionRequest(requestHeader, clientSignature, clientSoftwareCertificates, localeIds, userIdentityToken, userTokenSignature);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
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
