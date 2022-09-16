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
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.4/#5.4.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.4.4/#5.4.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class GetEndpointsRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=426");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=428");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=427");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15100");

    private final RequestHeader requestHeader;

    private final String endpointUrl;

    private final String[] localeIds;

    private final String[] profileUris;

    public GetEndpointsRequest(RequestHeader requestHeader, String endpointUrl, String[] localeIds,
                               String[] profileUris) {
        this.requestHeader = requestHeader;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.profileUris = profileUris;
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

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public String[] getProfileUris() {
        return profileUris;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 428),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("EndpointUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("LocaleIds", LocalizedText.NULL_VALUE, new NodeId(0, 295), 1, null, UInteger.valueOf(0), false),
                new StructureField("ProfileUris", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<GetEndpointsRequest> {
        @Override
        public Class<GetEndpointsRequest> getType() {
            return GetEndpointsRequest.class;
        }

        @Override
        public GetEndpointsRequest decodeType(EncodingContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.decodeStruct("RequestHeader", RequestHeader.TYPE_ID);
            String endpointUrl = decoder.decodeString("EndpointUrl");
            String[] localeIds = decoder.decodeStringArray("LocaleIds");
            String[] profileUris = decoder.decodeStringArray("ProfileUris");
            return new GetEndpointsRequest(requestHeader, endpointUrl, localeIds, profileUris);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, GetEndpointsRequest value) {
            encoder.encodeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.encodeString("EndpointUrl", value.getEndpointUrl());
            encoder.encodeStringArray("LocaleIds", value.getLocaleIds());
            encoder.encodeStringArray("ProfileUris", value.getProfileUris());
        }
    }
}
