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

public class HistoryEventFieldList implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryEventFieldList;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryEventFieldList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryEventFieldList_Encoding_DefaultXml;

    protected final Variant[] eventFields;

    public HistoryEventFieldList() {
        this.eventFields = null;
    }

    public HistoryEventFieldList(Variant[] eventFields) {
        this.eventFields = eventFields;
    }

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
            .add("EventFields", eventFields)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryEventFieldList> {

        @Override
        public Class<HistoryEventFieldList> getType() {
            return HistoryEventFieldList.class;
        }

        @Override
        public HistoryEventFieldList decode(UaDecoder decoder) throws UaSerializationException {
            Variant[] eventFields = decoder.readArray("EventFields", decoder::readVariant, Variant.class);

            return new HistoryEventFieldList(eventFields);
        }

        @Override
        public void encode(HistoryEventFieldList value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("EventFields", value.eventFields, encoder::writeVariant);
        }
    }

}
