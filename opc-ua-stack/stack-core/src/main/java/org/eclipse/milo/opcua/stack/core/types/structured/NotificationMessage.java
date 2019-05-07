/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class NotificationMessage extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=803");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=805");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=804");

    private final UInteger sequenceNumber;

    private final DateTime publishTime;

    private final ExtensionObject[] notificationData;

    public NotificationMessage(UInteger sequenceNumber, DateTime publishTime,
                               ExtensionObject[] notificationData) {
        this.sequenceNumber = sequenceNumber;
        this.publishTime = publishTime;
        this.notificationData = notificationData;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public UInteger getSequenceNumber() {
        return sequenceNumber;
    }

    public DateTime getPublishTime() {
        return publishTime;
    }

    public ExtensionObject[] getNotificationData() {
        return notificationData;
    }

    public static final class Codec extends GenericDataTypeCodec<NotificationMessage> {
        @Override
        public Class<NotificationMessage> getType() {
            return NotificationMessage.class;
        }

        @Override
        public NotificationMessage decode(SerializationContext context, UaDecoder decoder) {
            UInteger sequenceNumber = decoder.readUInt32("SequenceNumber");
            DateTime publishTime = decoder.readDateTime("PublishTime");
            ExtensionObject[] notificationData = decoder.readExtensionObjectArray("NotificationData");
            return new NotificationMessage(sequenceNumber, publishTime, notificationData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NotificationMessage value) {
            encoder.writeUInt32("SequenceNumber", value.getSequenceNumber());
            encoder.writeDateTime("PublishTime", value.getPublishTime());
            encoder.writeExtensionObjectArray("NotificationData", value.getNotificationData());
        }
    }
}
