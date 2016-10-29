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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("EventFieldList")
public class EventFieldList implements UaStructure {

    public static final NodeId TypeId = Identifiers.EventFieldList;
    public static final NodeId BinaryEncodingId = Identifiers.EventFieldList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFieldList_Encoding_DefaultXml;

    protected final UInteger _clientHandle;
    protected final Variant[] _eventFields;

    public EventFieldList() {
        this._clientHandle = null;
        this._eventFields = null;
    }

    public EventFieldList(UInteger _clientHandle, Variant[] _eventFields) {
        this._clientHandle = _clientHandle;
        this._eventFields = _eventFields;
    }

    public UInteger getClientHandle() { return _clientHandle; }

    public Variant[] getEventFields() { return _eventFields; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EventFieldList eventFieldList, UaEncoder encoder) {
        encoder.encodeUInt32("ClientHandle", eventFieldList._clientHandle);
        encoder.encodeArray("EventFields", eventFieldList._eventFields, encoder::encodeVariant);
    }

    public static EventFieldList decode(UaDecoder decoder) {
        UInteger _clientHandle = decoder.decodeUInt32("ClientHandle");
        Variant[] _eventFields = decoder.decodeArray("EventFields", decoder::decodeVariant, Variant.class);

        return new EventFieldList(_clientHandle, _eventFields);
    }

    static {
        DelegateRegistry.registerEncoder(EventFieldList::encode, EventFieldList.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EventFieldList::decode, EventFieldList.class, BinaryEncodingId, XmlEncodingId);
    }

}
