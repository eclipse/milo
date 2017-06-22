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

@UaDataType("SetPublishingModeRequest")
public class SetPublishingModeRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.SetPublishingModeRequest;
    public static final NodeId BinaryEncodingId = Identifiers.SetPublishingModeRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetPublishingModeRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _publishingEnabled;
    protected final UInteger[] _subscriptionIds;

    public SetPublishingModeRequest() {
        this._requestHeader = null;
        this._publishingEnabled = null;
        this._subscriptionIds = null;
    }

    public SetPublishingModeRequest(RequestHeader _requestHeader, Boolean _publishingEnabled, UInteger[] _subscriptionIds) {
        this._requestHeader = _requestHeader;
        this._publishingEnabled = _publishingEnabled;
        this._subscriptionIds = _subscriptionIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getPublishingEnabled() { return _publishingEnabled; }

    @Nullable
    public UInteger[] getSubscriptionIds() { return _subscriptionIds; }

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
            .add("PublishingEnabled", _publishingEnabled)
            .add("SubscriptionIds", _subscriptionIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SetPublishingModeRequest> {
        @Override
        public SetPublishingModeRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            Boolean _publishingEnabled = reader.readBoolean();
            UInteger[] _subscriptionIds = reader.readArray(reader::readUInt32, UInteger.class);

            return new SetPublishingModeRequest(_requestHeader, _publishingEnabled, _subscriptionIds);
        }

        @Override
        public void encode(SerializationContext context, SetPublishingModeRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeBoolean(value._publishingEnabled);
            writer.writeArray(value._subscriptionIds, writer::writeUInt32);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SetPublishingModeRequest> {
        @Override
        public SetPublishingModeRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            Boolean _publishingEnabled = reader.readBoolean("PublishingEnabled");
            UInteger[] _subscriptionIds = reader.readArray("SubscriptionIds", reader::readUInt32, UInteger.class);

            return new SetPublishingModeRequest(_requestHeader, _publishingEnabled, _subscriptionIds);
        }

        @Override
        public void encode(SerializationContext context, SetPublishingModeRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeBoolean("PublishingEnabled", encodable._publishingEnabled);
            writer.writeArray("SubscriptionIds", encodable._subscriptionIds, writer::writeUInt32);
        }
    }

}
