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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public ExtensionObject[] getNotificationData() { return _notificationData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NotificationMessage notificationMessage, UaEncoder encoder) {
        encoder.encodeUInt32("SequenceNumber", notificationMessage._sequenceNumber);
        encoder.encodeDateTime("PublishTime", notificationMessage._publishTime);
        encoder.encodeArray("NotificationData", notificationMessage._notificationData, encoder::encodeExtensionObject);
    }

    public static NotificationMessage decode(UaDecoder decoder) {
        UInteger _sequenceNumber = decoder.decodeUInt32("SequenceNumber");
        DateTime _publishTime = decoder.decodeDateTime("PublishTime");
        ExtensionObject[] _notificationData = decoder.decodeArray("NotificationData", decoder::decodeExtensionObject, ExtensionObject.class);

        return new NotificationMessage(_sequenceNumber, _publishTime, _notificationData);
    }

    static {
        DelegateRegistry.registerEncoder(NotificationMessage::encode, NotificationMessage.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NotificationMessage::decode, NotificationMessage.class, BinaryEncodingId, XmlEncodingId);
    }

}
