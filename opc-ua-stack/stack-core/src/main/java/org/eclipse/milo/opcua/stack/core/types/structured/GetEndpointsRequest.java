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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class GetEndpointsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.GetEndpointsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.GetEndpointsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.GetEndpointsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final String endpointUrl;
    protected final String[] localeIds;
    protected final String[] profileUris;

    public GetEndpointsRequest() {
        this.requestHeader = null;
        this.endpointUrl = null;
        this.localeIds = null;
        this.profileUris = null;
    }

    public GetEndpointsRequest(RequestHeader requestHeader, String endpointUrl, String[] localeIds, String[] profileUris) {
        this.requestHeader = requestHeader;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.profileUris = profileUris;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public String getEndpointUrl() { return endpointUrl; }

    @Nullable
    public String[] getLocaleIds() { return localeIds; }

    @Nullable
    public String[] getProfileUris() { return profileUris; }

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
            .add("EndpointUrl", endpointUrl)
            .add("LocaleIds", localeIds)
            .add("ProfileUris", profileUris)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<GetEndpointsRequest> {

        @Override
        public Class<GetEndpointsRequest> getType() {
            return GetEndpointsRequest.class;
        }

        @Override
        public GetEndpointsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            String endpointUrl = decoder.readString("EndpointUrl");
            String[] localeIds = decoder.readArray("LocaleIds", decoder::readString, String.class);
            String[] profileUris = decoder.readArray("ProfileUris", decoder::readString, String.class);

            return new GetEndpointsRequest(requestHeader, endpointUrl, localeIds, profileUris);
        }

        @Override
        public void encode(GetEndpointsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeString("EndpointUrl", value.endpointUrl);
            encoder.writeArray("LocaleIds", value.localeIds, encoder::writeString);
            encoder.writeArray("ProfileUris", value.profileUris, encoder::writeString);
        }
    }

}
