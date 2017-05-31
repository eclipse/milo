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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryUpdateRequest")
public class HistoryUpdateRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryUpdateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ExtensionObject[] _historyUpdateDetails;

    public HistoryUpdateRequest() {
        this._requestHeader = null;
        this._historyUpdateDetails = null;
    }

    public HistoryUpdateRequest(RequestHeader _requestHeader, ExtensionObject[] _historyUpdateDetails) {
        this._requestHeader = _requestHeader;
        this._historyUpdateDetails = _historyUpdateDetails;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public ExtensionObject[] getHistoryUpdateDetails() { return _historyUpdateDetails; }

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
            .add("HistoryUpdateDetails", _historyUpdateDetails)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryUpdateRequest> {
        @Override
        public HistoryUpdateRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            ExtensionObject[] _historyUpdateDetails = reader.readArray(reader::readExtensionObject, ExtensionObject.class);

            return new HistoryUpdateRequest(_requestHeader, _historyUpdateDetails);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeArray(encodable._historyUpdateDetails, writer::writeExtensionObject);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryUpdateRequest> {
        @Override
        public HistoryUpdateRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            ExtensionObject[] _historyUpdateDetails = reader.readArray("HistoryUpdateDetails", reader::readExtensionObject, ExtensionObject.class);

            return new HistoryUpdateRequest(_requestHeader, _historyUpdateDetails);
        }

        @Override
        public void encode(SerializationContext context, HistoryUpdateRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray("HistoryUpdateDetails", encodable._historyUpdateDetails, writer::writeExtensionObject);
        }
    }

}
