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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
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

@UaDataType("ModifySubscriptionResponse")
public class ModifySubscriptionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.ModifySubscriptionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.ModifySubscriptionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifySubscriptionResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final Double _revisedPublishingInterval;
    protected final UInteger _revisedLifetimeCount;
    protected final UInteger _revisedMaxKeepAliveCount;

    public ModifySubscriptionResponse() {
        this._responseHeader = null;
        this._revisedPublishingInterval = null;
        this._revisedLifetimeCount = null;
        this._revisedMaxKeepAliveCount = null;
    }

    public ModifySubscriptionResponse(ResponseHeader _responseHeader, Double _revisedPublishingInterval, UInteger _revisedLifetimeCount, UInteger _revisedMaxKeepAliveCount) {
        this._responseHeader = _responseHeader;
        this._revisedPublishingInterval = _revisedPublishingInterval;
        this._revisedLifetimeCount = _revisedLifetimeCount;
        this._revisedMaxKeepAliveCount = _revisedMaxKeepAliveCount;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public Double getRevisedPublishingInterval() { return _revisedPublishingInterval; }

    public UInteger getRevisedLifetimeCount() { return _revisedLifetimeCount; }

    public UInteger getRevisedMaxKeepAliveCount() { return _revisedMaxKeepAliveCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("RevisedPublishingInterval", _revisedPublishingInterval)
            .add("RevisedLifetimeCount", _revisedLifetimeCount)
            .add("RevisedMaxKeepAliveCount", _revisedMaxKeepAliveCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ModifySubscriptionResponse> {
        @Override
        public ModifySubscriptionResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            Double _revisedPublishingInterval = reader.readDouble();
            UInteger _revisedLifetimeCount = reader.readUInt32();
            UInteger _revisedMaxKeepAliveCount = reader.readUInt32();

            return new ModifySubscriptionResponse(_responseHeader, _revisedPublishingInterval, _revisedLifetimeCount, _revisedMaxKeepAliveCount);
        }

        @Override
        public void encode(SerializationContext context, ModifySubscriptionResponse value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, value._responseHeader, writer);
            writer.writeDouble(value._revisedPublishingInterval);
            writer.writeUInt32(value._revisedLifetimeCount);
            writer.writeUInt32(value._revisedMaxKeepAliveCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ModifySubscriptionResponse> {
        @Override
        public ModifySubscriptionResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            Double _revisedPublishingInterval = reader.readDouble("RevisedPublishingInterval");
            UInteger _revisedLifetimeCount = reader.readUInt32("RevisedLifetimeCount");
            UInteger _revisedMaxKeepAliveCount = reader.readUInt32("RevisedMaxKeepAliveCount");

            return new ModifySubscriptionResponse(_responseHeader, _revisedPublishingInterval, _revisedLifetimeCount, _revisedMaxKeepAliveCount);
        }

        @Override
        public void encode(SerializationContext context, ModifySubscriptionResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeDouble("RevisedPublishingInterval", encodable._revisedPublishingInterval);
            writer.writeUInt32("RevisedLifetimeCount", encodable._revisedLifetimeCount);
            writer.writeUInt32("RevisedMaxKeepAliveCount", encodable._revisedMaxKeepAliveCount);
        }
    }

}
