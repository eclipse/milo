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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class NotificationMessage implements UaStructure {

    public static final NodeId TypeId = Identifiers.NotificationMessage;
    public static final NodeId BinaryEncodingId = Identifiers.NotificationMessage_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NotificationMessage_Encoding_DefaultXml;

    protected final UInteger sequenceNumber;
    protected final DateTime publishTime;
    protected final ExtensionObject[] notificationData;

    public NotificationMessage() {
        this.sequenceNumber = null;
        this.publishTime = null;
        this.notificationData = null;
    }

    public NotificationMessage(UInteger sequenceNumber, DateTime publishTime, ExtensionObject[] notificationData) {
        this.sequenceNumber = sequenceNumber;
        this.publishTime = publishTime;
        this.notificationData = notificationData;
    }

    public UInteger getSequenceNumber() { return sequenceNumber; }

    public DateTime getPublishTime() { return publishTime; }

    @Nullable
    public ExtensionObject[] getNotificationData() { return notificationData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SequenceNumber", sequenceNumber)
            .add("PublishTime", publishTime)
            .add("NotificationData", notificationData)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<NotificationMessage> {

        @Override
        public Class<NotificationMessage> getType() {
            return NotificationMessage.class;
        }

        @Override
        public NotificationMessage decode(UaDecoder decoder) throws UaSerializationException {
            UInteger sequenceNumber = decoder.readUInt32("SequenceNumber");
            DateTime publishTime = decoder.readDateTime("PublishTime");
            ExtensionObject[] notificationData = decoder.readArray("NotificationData", decoder::readExtensionObject, ExtensionObject.class);

            return new NotificationMessage(sequenceNumber, publishTime, notificationData);
        }

        @Override
        public void encode(NotificationMessage value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SequenceNumber", value.sequenceNumber);
            encoder.writeDateTime("PublishTime", value.publishTime);
            encoder.writeArray("NotificationData", value.notificationData, encoder::writeExtensionObject);
        }
    }

}
