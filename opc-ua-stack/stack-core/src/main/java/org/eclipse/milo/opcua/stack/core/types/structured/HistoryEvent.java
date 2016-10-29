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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryEvent")
public class HistoryEvent implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryEvent;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryEvent_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryEvent_Encoding_DefaultXml;

    protected final HistoryEventFieldList[] _events;

    public HistoryEvent() {
        this._events = null;
    }

    public HistoryEvent(HistoryEventFieldList[] _events) {
        this._events = _events;
    }

    public HistoryEventFieldList[] getEvents() { return _events; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryEvent historyEvent, UaEncoder encoder) {
        encoder.encodeArray("Events", historyEvent._events, encoder::encodeSerializable);
    }

    public static HistoryEvent decode(UaDecoder decoder) {
        HistoryEventFieldList[] _events = decoder.decodeArray("Events", decoder::decodeSerializable, HistoryEventFieldList.class);

        return new HistoryEvent(_events);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryEvent::encode, HistoryEvent.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryEvent::decode, HistoryEvent.class, BinaryEncodingId, XmlEncodingId);
    }

}
