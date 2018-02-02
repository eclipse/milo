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

public class FindServersRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.FindServersRequest;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final String endpointUrl;
    protected final String[] localeIds;
    protected final String[] serverUris;

    public FindServersRequest() {
        this.requestHeader = null;
        this.endpointUrl = null;
        this.localeIds = null;
        this.serverUris = null;
    }

    public FindServersRequest(RequestHeader requestHeader, String endpointUrl, String[] localeIds, String[] serverUris) {
        this.requestHeader = requestHeader;
        this.endpointUrl = endpointUrl;
        this.localeIds = localeIds;
        this.serverUris = serverUris;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public String getEndpointUrl() { return endpointUrl; }

    @Nullable
    public String[] getLocaleIds() { return localeIds; }

    @Nullable
    public String[] getServerUris() { return serverUris; }

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
            .add("ServerUris", serverUris)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<FindServersRequest> {

        @Override
        public Class<FindServersRequest> getType() {
            return FindServersRequest.class;
        }

        @Override
        public FindServersRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            String endpointUrl = decoder.readString("EndpointUrl");
            String[] localeIds = decoder.readArray("LocaleIds", decoder::readString, String.class);
            String[] serverUris = decoder.readArray("ServerUris", decoder::readString, String.class);

            return new FindServersRequest(requestHeader, endpointUrl, localeIds, serverUris);
        }

        @Override
        public void encode(FindServersRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeString("EndpointUrl", value.endpointUrl);
            encoder.writeArray("LocaleIds", value.localeIds, encoder::writeString);
            encoder.writeArray("ServerUris", value.serverUris, encoder::writeString);
        }
    }

}
