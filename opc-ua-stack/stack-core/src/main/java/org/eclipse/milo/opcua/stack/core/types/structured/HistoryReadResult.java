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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("HistoryReadResult")
public class HistoryReadResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final ByteString _continuationPoint;
    protected final ExtensionObject _historyData;

    public HistoryReadResult() {
        this._statusCode = null;
        this._continuationPoint = null;
        this._historyData = null;
    }

    public HistoryReadResult(StatusCode _statusCode, ByteString _continuationPoint, ExtensionObject _historyData) {
        this._statusCode = _statusCode;
        this._continuationPoint = _continuationPoint;
        this._historyData = _historyData;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    public ExtensionObject getHistoryData() { return _historyData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("ContinuationPoint", _continuationPoint)
            .add("HistoryData", _historyData)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryReadResult> {
        @Override
        public HistoryReadResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            ByteString _continuationPoint = reader.readByteString();
            ExtensionObject _historyData = reader.readExtensionObject();

            return new HistoryReadResult(_statusCode, _continuationPoint, _historyData);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeByteString(encodable._continuationPoint);
            writer.writeExtensionObject(encodable._historyData);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryReadResult> {
        @Override
        public HistoryReadResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            ByteString _continuationPoint = reader.readByteString("ContinuationPoint");
            ExtensionObject _historyData = reader.readExtensionObject("HistoryData");

            return new HistoryReadResult(_statusCode, _continuationPoint, _historyData);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeByteString("ContinuationPoint", encodable._continuationPoint);
            writer.writeExtensionObject("HistoryData", encodable._historyData);
        }
    }

}
