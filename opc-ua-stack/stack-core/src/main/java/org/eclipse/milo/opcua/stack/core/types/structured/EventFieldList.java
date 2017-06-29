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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class EventFieldList implements UaStructure {

    public static final NodeId TypeId = Identifiers.EventFieldList;
    public static final NodeId BinaryEncodingId = Identifiers.EventFieldList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFieldList_Encoding_DefaultXml;

    protected final UInteger clientHandle;
    protected final Variant[] eventFields;

    public EventFieldList() {
        this.clientHandle = null;
        this.eventFields = null;
    }

    public EventFieldList(UInteger clientHandle, Variant[] eventFields) {
        this.clientHandle = clientHandle;
        this.eventFields = eventFields;
    }

    public UInteger getClientHandle() { return clientHandle; }

    @Nullable
    public Variant[] getEventFields() { return eventFields; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ClientHandle", clientHandle)
            .add("EventFields", eventFields)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EventFieldList> {

        @Override
        public Class<EventFieldList> getType() {
            return EventFieldList.class;
        }

        @Override
        public EventFieldList decode(UaDecoder decoder) throws UaSerializationException {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            Variant[] eventFields = decoder.readArray("EventFields", decoder::readVariant, Variant.class);

            return new EventFieldList(clientHandle, eventFields);
        }

        @Override
        public void encode(EventFieldList value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("ClientHandle", value.clientHandle);
            encoder.writeArray("EventFields", value.eventFields, encoder::writeVariant);
        }
    }

}
