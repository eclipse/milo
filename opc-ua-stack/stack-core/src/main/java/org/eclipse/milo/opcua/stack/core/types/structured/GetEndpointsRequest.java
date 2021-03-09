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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class GetEndpointsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=426");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=428");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=427");

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

    public static final class Codec extends GenericDataTypeCodec<GetEndpointsRequest> {
        @Override
        public Class<GetEndpointsRequest> getType() {
            return GetEndpointsRequest.class;
        }

        @Override
        public GetEndpointsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            String endpointUrl = decoder.readString("EndpointUrl");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            String[] profileUris = decoder.readStringArray("ProfileUris");
            return new GetEndpointsRequest(requestHeader, endpointUrl, localeIds, profileUris);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, GetEndpointsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeString("EndpointUrl", value.getEndpointUrl());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeStringArray("ProfileUris", value.getProfileUris());
        }
    }
}
