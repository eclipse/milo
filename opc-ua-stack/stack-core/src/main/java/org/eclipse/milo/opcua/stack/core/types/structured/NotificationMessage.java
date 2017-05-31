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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("NotificationMessage")
public class NotificationMessage implements UaStructure {

    public static final NodeId TypeId = Identifiers.NotificationMessage;
    public static final NodeId BinaryEncodingId = Identifiers.NotificationMessage_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NotificationMessage_Encoding_DefaultXml;

    protected final UInteger _sequenceNumber;
    protected final DateTime _publishTime;
    protected final ExtensionObject[] _notificationData;

    public NotificationMessage() {
        this._sequenceNumber = null;
        this._publishTime = null;
        this._notificationData = null;
    }

    public NotificationMessage(UInteger _sequenceNumber, DateTime _publishTime, ExtensionObject[] _notificationData) {
        this._sequenceNumber = _sequenceNumber;
        this._publishTime = _publishTime;
        this._notificationData = _notificationData;
    }

    public UInteger getSequenceNumber() { return _sequenceNumber; }

    public DateTime getPublishTime() { return _publishTime; }

    @Nullable
    public ExtensionObject[] getNotificationData() { return _notificationData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SequenceNumber", _sequenceNumber)
            .add("PublishTime", _publishTime)
            .add("NotificationData", _notificationData)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<NotificationMessage> {
        @Override
        public NotificationMessage decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _sequenceNumber = reader.readUInt32();
            DateTime _publishTime = reader.readDateTime();
            ExtensionObject[] _notificationData = reader.readArray(reader::readExtensionObject, ExtensionObject.class);

            return new NotificationMessage(_sequenceNumber, _publishTime, _notificationData);
        }

        @Override
        public void encode(SerializationContext context, NotificationMessage encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._sequenceNumber);
            writer.writeDateTime(encodable._publishTime);
            writer.writeArray(encodable._notificationData, writer::writeExtensionObject);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<NotificationMessage> {
        @Override
        public NotificationMessage decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _sequenceNumber = reader.readUInt32("SequenceNumber");
            DateTime _publishTime = reader.readDateTime("PublishTime");
            ExtensionObject[] _notificationData = reader.readArray("NotificationData", reader::readExtensionObject, ExtensionObject.class);

            return new NotificationMessage(_sequenceNumber, _publishTime, _notificationData);
        }

        @Override
        public void encode(SerializationContext context, NotificationMessage encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SequenceNumber", encodable._sequenceNumber);
            writer.writeDateTime("PublishTime", encodable._publishTime);
            writer.writeArray("NotificationData", encodable._notificationData, writer::writeExtensionObject);
        }
    }

}
