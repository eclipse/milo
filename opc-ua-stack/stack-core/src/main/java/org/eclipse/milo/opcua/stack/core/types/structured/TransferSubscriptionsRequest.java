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

@UaDataType("TransferSubscriptionsRequest")
public class TransferSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TransferSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger[] _subscriptionIds;
    protected final Boolean _sendInitialValues;

    public TransferSubscriptionsRequest() {
        this._requestHeader = null;
        this._subscriptionIds = null;
        this._sendInitialValues = null;
    }

    public TransferSubscriptionsRequest(RequestHeader _requestHeader, UInteger[] _subscriptionIds, Boolean _sendInitialValues) {
        this._requestHeader = _requestHeader;
        this._subscriptionIds = _subscriptionIds;
        this._sendInitialValues = _sendInitialValues;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public UInteger[] getSubscriptionIds() { return _subscriptionIds; }

    public Boolean getSendInitialValues() { return _sendInitialValues; }

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
            .add("SendInitialValues", _sendInitialValues)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<TransferSubscriptionsRequest> {
        @Override
        public TransferSubscriptionsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger[] _subscriptionIds = reader.readArray(reader::readUInt32, UInteger.class);
            Boolean _sendInitialValues = reader.readBoolean();

            return new TransferSubscriptionsRequest(_requestHeader, _subscriptionIds, _sendInitialValues);
        }

        @Override
        public void encode(SerializationContext context, TransferSubscriptionsRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray(encodable._subscriptionIds, writer::writeUInt32);
            writer.writeBoolean(encodable._sendInitialValues);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<TransferSubscriptionsRequest> {
        @Override
        public TransferSubscriptionsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            UInteger[] _subscriptionIds = reader.readArray("SubscriptionIds", reader::readUInt32, UInteger.class);
            Boolean _sendInitialValues = reader.readBoolean("SendInitialValues");

            return new TransferSubscriptionsRequest(_requestHeader, _subscriptionIds, _sendInitialValues);
        }

        @Override
        public void encode(SerializationContext context, TransferSubscriptionsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray("SubscriptionIds", encodable._subscriptionIds, writer::writeUInt32);
            writer.writeBoolean("SendInitialValues", encodable._sendInitialValues);
        }
    }

}
