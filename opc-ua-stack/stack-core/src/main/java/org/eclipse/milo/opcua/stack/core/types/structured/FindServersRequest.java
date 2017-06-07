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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("FindServersRequest")
public class FindServersRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.FindServersRequest;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final String _endpointUrl;
    protected final String[] _localeIds;
    protected final String[] _serverUris;

    public FindServersRequest() {
        this._requestHeader = null;
        this._endpointUrl = null;
        this._localeIds = null;
        this._serverUris = null;
    }

    public FindServersRequest(RequestHeader _requestHeader, String _endpointUrl, String[] _localeIds, String[] _serverUris) {
        this._requestHeader = _requestHeader;
        this._endpointUrl = _endpointUrl;
        this._localeIds = _localeIds;
        this._serverUris = _serverUris;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public String getEndpointUrl() { return _endpointUrl; }

    @Nullable
    public String[] getLocaleIds() { return _localeIds; }

    @Nullable
    public String[] getServerUris() { return _serverUris; }

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
            .add("EndpointUrl", _endpointUrl)
            .add("LocaleIds", _localeIds)
            .add("ServerUris", _serverUris)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<FindServersRequest> {
        @Override
        public FindServersRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            String _endpointUrl = reader.readString();
            String[] _localeIds = reader.readArray(reader::readString, String.class);
            String[] _serverUris = reader.readArray(reader::readString, String.class);

            return new FindServersRequest(_requestHeader, _endpointUrl, _localeIds, _serverUris);
        }

        @Override
        public void encode(SerializationContext context, FindServersRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeString(value._endpointUrl);
            writer.writeArray(value._localeIds, writer::writeString);
            writer.writeArray(value._serverUris, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<FindServersRequest> {
        @Override
        public FindServersRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            String _endpointUrl = reader.readString("EndpointUrl");
            String[] _localeIds = reader.readArray("LocaleIds", reader::readString, String.class);
            String[] _serverUris = reader.readArray("ServerUris", reader::readString, String.class);

            return new FindServersRequest(_requestHeader, _endpointUrl, _localeIds, _serverUris);
        }

        @Override
        public void encode(SerializationContext context, FindServersRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeString("EndpointUrl", encodable._endpointUrl);
            writer.writeArray("LocaleIds", encodable._localeIds, writer::writeString);
            writer.writeArray("ServerUris", encodable._serverUris, writer::writeString);
        }
    }

}
