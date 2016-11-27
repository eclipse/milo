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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("ReadRequest")
public class ReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Double _maxAge;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final ReadValueId[] _nodesToRead;

    public ReadRequest() {
        this._requestHeader = null;
        this._maxAge = null;
        this._timestampsToReturn = null;
        this._nodesToRead = null;
    }

    public ReadRequest(RequestHeader _requestHeader, Double _maxAge, TimestampsToReturn _timestampsToReturn, ReadValueId[] _nodesToRead) {
        this._requestHeader = _requestHeader;
        this._maxAge = _maxAge;
        this._timestampsToReturn = _timestampsToReturn;
        this._nodesToRead = _nodesToRead;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Double getMaxAge() { return _maxAge; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    @Nullable
    public ReadValueId[] getNodesToRead() { return _nodesToRead; }

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
            .add("MaxAge", _maxAge)
            .add("TimestampsToReturn", _timestampsToReturn)
            .add("NodesToRead", _nodesToRead)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadRequest> {
        @Override
        public ReadRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            Double _maxAge = reader.readDouble();
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32());
            ReadValueId[] _nodesToRead =
                reader.readArray(
                    () -> (ReadValueId) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", reader),
                    ReadValueId.class
                );

            return new ReadRequest(_requestHeader, _maxAge, _timestampsToReturn, _nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, ReadRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeDouble(encodable._maxAge);
            writer.writeInt32(encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                encodable._nodesToRead,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadRequest> {
        @Override
        public ReadRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            Double _maxAge = reader.readDouble("MaxAge");
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32("TimestampsToReturn"));
            ReadValueId[] _nodesToRead =
                reader.readArray(
                    "NodesToRead",
                    f -> (ReadValueId) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", reader),
                    ReadValueId.class
                );

            return new ReadRequest(_requestHeader, _maxAge, _timestampsToReturn, _nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, ReadRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeDouble("MaxAge", encodable._maxAge);
            writer.writeInt32("TimestampsToReturn", encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                "NodesToRead",
                encodable._nodesToRead,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", e, writer)
            );
        }
    }

}
