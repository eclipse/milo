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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EndpointUrlListDataType")
public class EndpointUrlListDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointUrlListDataType;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointUrlListDataType_Encoding_DefaultXml;

    protected final String[] _endpointUrlList;

    public EndpointUrlListDataType() {
        this._endpointUrlList = null;
    }

    public EndpointUrlListDataType(String[] _endpointUrlList) {
        this._endpointUrlList = _endpointUrlList;
    }

    @Nullable
    public String[] getEndpointUrlList() { return _endpointUrlList; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EndpointUrlList", _endpointUrlList)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EndpointUrlListDataType> {
        @Override
        public EndpointUrlListDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String[] _endpointUrlList = reader.readArray(reader::readString, String.class);

            return new EndpointUrlListDataType(_endpointUrlList);
        }

        @Override
        public void encode(SerializationContext context, EndpointUrlListDataType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(encodable._endpointUrlList, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EndpointUrlListDataType> {
        @Override
        public EndpointUrlListDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String[] _endpointUrlList = reader.readArray("EndpointUrlList", reader::readString, String.class);

            return new EndpointUrlListDataType(_endpointUrlList);
        }

        @Override
        public void encode(SerializationContext context, EndpointUrlListDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("EndpointUrlList", encodable._endpointUrlList, writer::writeString);
        }
    }

}
