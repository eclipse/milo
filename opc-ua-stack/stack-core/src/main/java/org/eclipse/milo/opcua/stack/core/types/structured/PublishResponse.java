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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("PublishResponse")
public class PublishResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.PublishResponse;
    public static final NodeId BinaryEncodingId = Identifiers.PublishResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.PublishResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger[] _availableSequenceNumbers;
    protected final Boolean _moreNotifications;
    protected final NotificationMessage _notificationMessage;
    protected final StatusCode[] _results;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public PublishResponse() {
        this._responseHeader = null;
        this._subscriptionId = null;
        this._availableSequenceNumbers = null;
        this._moreNotifications = null;
        this._notificationMessage = null;
        this._results = null;
        this._diagnosticInfos = null;
    }

    public PublishResponse(ResponseHeader _responseHeader, UInteger _subscriptionId, UInteger[] _availableSequenceNumbers, Boolean _moreNotifications, NotificationMessage _notificationMessage, StatusCode[] _results, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._subscriptionId = _subscriptionId;
        this._availableSequenceNumbers = _availableSequenceNumbers;
        this._moreNotifications = _moreNotifications;
        this._notificationMessage = _notificationMessage;
        this._results = _results;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    @Nullable
    public UInteger[] getAvailableSequenceNumbers() { return _availableSequenceNumbers; }

    public Boolean getMoreNotifications() { return _moreNotifications; }

    public NotificationMessage getNotificationMessage() { return _notificationMessage; }

    @Nullable
    public StatusCode[] getResults() { return _results; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

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
            .add("SubscriptionId", _subscriptionId)
            .add("AvailableSequenceNumbers", _availableSequenceNumbers)
            .add("MoreNotifications", _moreNotifications)
            .add("NotificationMessage", _notificationMessage)
            .add("Results", _results)
            .add("DiagnosticInfos", _diagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<PublishResponse> {
        @Override
        public PublishResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            UInteger _subscriptionId = reader.readUInt32();
            UInteger[] _availableSequenceNumbers = reader.readArray(reader::readUInt32, UInteger.class);
            Boolean _moreNotifications = reader.readBoolean();
            NotificationMessage _notificationMessage = (NotificationMessage) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "NotificationMessage", reader);
            StatusCode[] _results = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new PublishResponse(_responseHeader, _subscriptionId, _availableSequenceNumbers, _moreNotifications, _notificationMessage, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, PublishResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeUInt32(encodable._subscriptionId);
            writer.writeArray(encodable._availableSequenceNumbers, writer::writeUInt32);
            writer.writeBoolean(encodable._moreNotifications);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "NotificationMessage", encodable._notificationMessage, writer);
            writer.writeArray(encodable._results, writer::writeStatusCode);
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<PublishResponse> {
        @Override
        public PublishResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UInteger[] _availableSequenceNumbers = reader.readArray("AvailableSequenceNumbers", reader::readUInt32, UInteger.class);
            Boolean _moreNotifications = reader.readBoolean("MoreNotifications");
            NotificationMessage _notificationMessage = (NotificationMessage) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "NotificationMessage", reader);
            StatusCode[] _results = reader.readArray("Results", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new PublishResponse(_responseHeader, _subscriptionId, _availableSequenceNumbers, _moreNotifications, _notificationMessage, _results, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, PublishResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeArray("AvailableSequenceNumbers", encodable._availableSequenceNumbers, writer::writeUInt32);
            writer.writeBoolean("MoreNotifications", encodable._moreNotifications);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "NotificationMessage", encodable._notificationMessage, writer);
            writer.writeArray("Results", encodable._results, writer::writeStatusCode);
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
