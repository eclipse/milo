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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("FindServersOnNetworkRequest")
public class FindServersOnNetworkRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.FindServersOnNetworkRequest;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersOnNetworkRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersOnNetworkRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _startingRecordId;
    protected final UInteger _maxRecordsToReturn;
    protected final String[] _serverCapabilityFilter;

    public FindServersOnNetworkRequest() {
        this._requestHeader = null;
        this._startingRecordId = null;
        this._maxRecordsToReturn = null;
        this._serverCapabilityFilter = null;
    }

    public FindServersOnNetworkRequest(RequestHeader _requestHeader, UInteger _startingRecordId, UInteger _maxRecordsToReturn, String[] _serverCapabilityFilter) {
        this._requestHeader = _requestHeader;
        this._startingRecordId = _startingRecordId;
        this._maxRecordsToReturn = _maxRecordsToReturn;
        this._serverCapabilityFilter = _serverCapabilityFilter;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getStartingRecordId() { return _startingRecordId; }

    public UInteger getMaxRecordsToReturn() { return _maxRecordsToReturn; }

    @Nullable
    public String[] getServerCapabilityFilter() { return _serverCapabilityFilter; }

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
            .add("StartingRecordId", _startingRecordId)
            .add("MaxRecordsToReturn", _maxRecordsToReturn)
            .add("ServerCapabilityFilter", _serverCapabilityFilter)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<FindServersOnNetworkRequest> {
        @Override
        public FindServersOnNetworkRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _startingRecordId = reader.readUInt32();
            UInteger _maxRecordsToReturn = reader.readUInt32();
            String[] _serverCapabilityFilter = reader.readArray(reader::readString, String.class);

            return new FindServersOnNetworkRequest(_requestHeader, _startingRecordId, _maxRecordsToReturn, _serverCapabilityFilter);
        }

        @Override
        public void encode(SerializationContext context, FindServersOnNetworkRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32(encodable._startingRecordId);
            writer.writeUInt32(encodable._maxRecordsToReturn);
            writer.writeArray(encodable._serverCapabilityFilter, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<FindServersOnNetworkRequest> {
        @Override
        public FindServersOnNetworkRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _startingRecordId = reader.readUInt32("StartingRecordId");
            UInteger _maxRecordsToReturn = reader.readUInt32("MaxRecordsToReturn");
            String[] _serverCapabilityFilter = reader.readArray("ServerCapabilityFilter", reader::readString, String.class);

            return new FindServersOnNetworkRequest(_requestHeader, _startingRecordId, _maxRecordsToReturn, _serverCapabilityFilter);
        }

        @Override
        public void encode(SerializationContext context, FindServersOnNetworkRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("StartingRecordId", encodable._startingRecordId);
            writer.writeUInt32("MaxRecordsToReturn", encodable._maxRecordsToReturn);
            writer.writeArray("ServerCapabilityFilter", encodable._serverCapabilityFilter, writer::writeString);
        }
    }

}
