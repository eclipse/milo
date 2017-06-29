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

public class HistoryEvent implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryEvent;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryEvent_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryEvent_Encoding_DefaultXml;

    protected final HistoryEventFieldList[] events;

    public HistoryEvent() {
        this.events = null;
    }

    public HistoryEvent(HistoryEventFieldList[] events) {
        this.events = events;
    }

    @Nullable
    public HistoryEventFieldList[] getEvents() { return events; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Events", events)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryEvent> {

        @Override
        public Class<HistoryEvent> getType() {
            return HistoryEvent.class;
        }

        @Override
        public HistoryEvent decode(UaDecoder decoder) throws UaSerializationException {
            HistoryEventFieldList[] events =
                decoder.readBuiltinStructArray(
                    "Events",
                    HistoryEventFieldList.class
                );

            return new HistoryEvent(events);
        }

        @Override
        public void encode(HistoryEvent value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "Events",
                value.events,
                HistoryEventFieldList.class
            );
        }
    }

}
