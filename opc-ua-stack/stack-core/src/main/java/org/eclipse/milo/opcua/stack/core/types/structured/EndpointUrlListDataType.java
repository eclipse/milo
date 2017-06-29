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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EndpointUrlListDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointUrlListDataType;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultXml;

    protected final String[] endpointUrlList;

    public EndpointUrlListDataType() {
        this.endpointUrlList = null;
    }

    public EndpointUrlListDataType(String[] endpointUrlList) {
        this.endpointUrlList = endpointUrlList;
    }

    @Nullable
    public String[] getEndpointUrlList() { return endpointUrlList; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EndpointUrlList", endpointUrlList)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EndpointUrlListDataType> {

        @Override
        public Class<EndpointUrlListDataType> getType() {
            return EndpointUrlListDataType.class;
        }

        @Override
        public EndpointUrlListDataType decode(UaDecoder decoder) throws UaSerializationException {
            String[] endpointUrlList = decoder.readArray("EndpointUrlList", decoder::readString, String.class);

            return new EndpointUrlListDataType(endpointUrlList);
        }

        @Override
        public void encode(EndpointUrlListDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("EndpointUrlList", value.endpointUrlList, encoder::writeString);
        }
    }

}
