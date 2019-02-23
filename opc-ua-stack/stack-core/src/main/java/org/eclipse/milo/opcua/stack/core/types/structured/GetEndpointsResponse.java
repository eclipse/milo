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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class GetEndpointsResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.GetEndpointsResponse;
    public static final NodeId BinaryEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final EndpointDescription[] endpoints;

    public GetEndpointsResponse() {
        this.responseHeader = null;
        this.endpoints = null;
    }

    public GetEndpointsResponse(ResponseHeader responseHeader, EndpointDescription[] endpoints) {
        this.responseHeader = responseHeader;
        this.endpoints = endpoints;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public EndpointDescription[] getEndpoints() { return endpoints; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("Endpoints", endpoints)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<GetEndpointsResponse> {

        @Override
        public Class<GetEndpointsResponse> getType() {
            return GetEndpointsResponse.class;
        }

        @Override
        public GetEndpointsResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            EndpointDescription[] endpoints =
                decoder.readBuiltinStructArray(
                    "Endpoints",
                    EndpointDescription.class
                );

            return new GetEndpointsResponse(responseHeader, endpoints);
        }

        @Override
        public void encode(GetEndpointsResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeBuiltinStructArray(
                "Endpoints",
                value.endpoints,
                EndpointDescription.class
            );
        }
    }

}
