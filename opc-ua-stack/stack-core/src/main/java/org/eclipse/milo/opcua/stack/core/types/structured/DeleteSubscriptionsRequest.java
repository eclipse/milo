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

@UaDataType("DeleteSubscriptionsRequest")
public class DeleteSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger[] _subscriptionIds;

    public DeleteSubscriptionsRequest() {
        this._requestHeader = null;
        this._subscriptionIds = null;
    }

    public DeleteSubscriptionsRequest(RequestHeader _requestHeader, UInteger[] _subscriptionIds) {
        this._requestHeader = _requestHeader;
        this._subscriptionIds = _subscriptionIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

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
            .add("SubscriptionIds", _subscriptionIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteSubscriptionsRequest> {
        @Override
        public DeleteSubscriptionsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger[] _subscriptionIds = reader.readArray(reader::readUInt32, UInteger.class);

            return new DeleteSubscriptionsRequest(_requestHeader, _subscriptionIds);
        }

        @Override
        public void encode(SerializationContext context, DeleteSubscriptionsRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray(encodable._subscriptionIds, writer::writeUInt32);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteSubscriptionsRequest> {
        @Override
        public DeleteSubscriptionsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger[] _subscriptionIds = reader.readArray("SubscriptionIds", reader::readUInt32, UInteger.class);

            return new DeleteSubscriptionsRequest(_requestHeader, _subscriptionIds);
        }

        @Override
        public void encode(SerializationContext context, DeleteSubscriptionsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray("SubscriptionIds", encodable._subscriptionIds, writer::writeUInt32);
        }
    }

}
