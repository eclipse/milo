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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SetTriggeringRequest")
public class SetTriggeringRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringRequest;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger _triggeringItemId;
    protected final UInteger[] _linksToAdd;
    protected final UInteger[] _linksToRemove;

    public SetTriggeringRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._triggeringItemId = null;
        this._linksToAdd = null;
        this._linksToRemove = null;
    }

    public SetTriggeringRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger _triggeringItemId, UInteger[] _linksToAdd, UInteger[] _linksToRemove) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._triggeringItemId = _triggeringItemId;
        this._linksToAdd = _linksToAdd;
        this._linksToRemove = _linksToRemove;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger getTriggeringItemId() { return _triggeringItemId; }

    @Nullable
    public UInteger[] getLinksToAdd() { return _linksToAdd; }

    @Nullable
    public UInteger[] getLinksToRemove() { return _linksToRemove; }

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
            .add("SubscriptionId", _subscriptionId)
            .add("TriggeringItemId", _triggeringItemId)
            .add("LinksToAdd", _linksToAdd)
            .add("LinksToRemove", _linksToRemove)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SetTriggeringRequest> {
        @Override
        public SetTriggeringRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger _subscriptionId = reader.readUInt32();
            UInteger _triggeringItemId = reader.readUInt32();
            UInteger[] _linksToAdd = reader.readArray(reader::readUInt32, UInteger.class);
            UInteger[] _linksToRemove = reader.readArray(reader::readUInt32, UInteger.class);

            return new SetTriggeringRequest(_requestHeader, _subscriptionId, _triggeringItemId, _linksToAdd, _linksToRemove);
        }

        @Override
        public void encode(SerializationContext context, SetTriggeringRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeUInt32(encodable._subscriptionId);
            writer.writeUInt32(encodable._triggeringItemId);
            writer.writeArray(encodable._linksToAdd, writer::writeUInt32);
            writer.writeArray(encodable._linksToRemove, writer::writeUInt32);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SetTriggeringRequest> {
        @Override
        public SetTriggeringRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UInteger _triggeringItemId = reader.readUInt32("TriggeringItemId");
            UInteger[] _linksToAdd = reader.readArray("LinksToAdd", reader::readUInt32, UInteger.class);
            UInteger[] _linksToRemove = reader.readArray("LinksToRemove", reader::readUInt32, UInteger.class);

            return new SetTriggeringRequest(_requestHeader, _subscriptionId, _triggeringItemId, _linksToAdd, _linksToRemove);
        }

        @Override
        public void encode(SerializationContext context, SetTriggeringRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeUInt32("TriggeringItemId", encodable._triggeringItemId);
            writer.writeArray("LinksToAdd", encodable._linksToAdd, writer::writeUInt32);
            writer.writeArray("LinksToRemove", encodable._linksToRemove, writer::writeUInt32);
        }
    }

}
