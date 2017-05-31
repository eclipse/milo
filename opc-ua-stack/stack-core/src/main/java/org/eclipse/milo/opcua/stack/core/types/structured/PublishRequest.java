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

@UaDataType("PublishRequest")
public class PublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.PublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.PublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.PublishRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final SubscriptionAcknowledgement[] _subscriptionAcknowledgements;

    public PublishRequest() {
        this._requestHeader = null;
        this._subscriptionAcknowledgements = null;
    }

    public PublishRequest(RequestHeader _requestHeader, SubscriptionAcknowledgement[] _subscriptionAcknowledgements) {
        this._requestHeader = _requestHeader;
        this._subscriptionAcknowledgements = _subscriptionAcknowledgements;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public SubscriptionAcknowledgement[] getSubscriptionAcknowledgements() { return _subscriptionAcknowledgements; }

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
            .add("SubscriptionAcknowledgements", _subscriptionAcknowledgements)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<PublishRequest> {
        @Override
        public PublishRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            SubscriptionAcknowledgement[] _subscriptionAcknowledgements =
                reader.readArray(
                    () -> (SubscriptionAcknowledgement) context.decode(
                        SubscriptionAcknowledgement.BinaryEncodingId, reader),
                    SubscriptionAcknowledgement.class
                );

            return new PublishRequest(_requestHeader, _subscriptionAcknowledgements);
        }

        @Override
        public void encode(SerializationContext context, PublishRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                encodable._subscriptionAcknowledgements,
                e -> context.encode(SubscriptionAcknowledgement.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<PublishRequest> {
        @Override
        public PublishRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            SubscriptionAcknowledgement[] _subscriptionAcknowledgements =
                reader.readArray(
                    "SubscriptionAcknowledgements",
                    f -> (SubscriptionAcknowledgement) context.decode(
                        SubscriptionAcknowledgement.XmlEncodingId, reader),
                    SubscriptionAcknowledgement.class
                );

            return new PublishRequest(_requestHeader, _subscriptionAcknowledgements);
        }

        @Override
        public void encode(SerializationContext context, PublishRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "SubscriptionAcknowledgements",
                encodable._subscriptionAcknowledgements,
                (f, e) -> context.encode(SubscriptionAcknowledgement.XmlEncodingId, e, writer)
            );
        }
    }

}
