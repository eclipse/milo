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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EventNotificationList")
public class EventNotificationList extends NotificationData {

    public static final NodeId TypeId = Identifiers.EventNotificationList;
    public static final NodeId BinaryEncodingId = Identifiers.EventNotificationList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventNotificationList_Encoding_DefaultXml;

    protected final EventFieldList[] _events;

    public EventNotificationList() {
        super();
        this._events = null;
    }

    public EventNotificationList(EventFieldList[] _events) {
        super();
        this._events = _events;
    }

    public EventFieldList[] getEvents() { return _events; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EventNotificationList eventNotificationList, UaEncoder encoder) {
        encoder.encodeArray("Events", eventNotificationList._events, encoder::encodeSerializable);
    }

    public static EventNotificationList decode(UaDecoder decoder) {
        EventFieldList[] _events = decoder.decodeArray("Events", decoder::decodeSerializable, EventFieldList.class);

        return new EventNotificationList(_events);
    }

    static {
        DelegateRegistry.registerEncoder(EventNotificationList::encode, EventNotificationList.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EventNotificationList::decode, EventNotificationList.class, BinaryEncodingId, XmlEncodingId);
    }

}
