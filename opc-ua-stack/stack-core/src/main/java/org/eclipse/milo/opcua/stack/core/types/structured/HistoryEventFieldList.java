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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("HistoryEventFieldList")
public class HistoryEventFieldList implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryEventFieldList;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryEventFieldList_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryEventFieldList_Encoding_DefaultXml;

    protected final Variant[] _eventFields;

    public HistoryEventFieldList() {
        this._eventFields = null;
    }

    public HistoryEventFieldList(Variant[] _eventFields) {
        this._eventFields = _eventFields;
    }

    @Nullable
    public Variant[] getEventFields() { return _eventFields; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EventFields", _eventFields)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryEventFieldList> {
        @Override
        public HistoryEventFieldList decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Variant[] _eventFields = reader.readArray(reader::readVariant, Variant.class);

            return new HistoryEventFieldList(_eventFields);
        }

        @Override
        public void encode(SerializationContext context, HistoryEventFieldList encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(encodable._eventFields, writer::writeVariant);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryEventFieldList> {
        @Override
        public HistoryEventFieldList decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Variant[] _eventFields = reader.readArray("EventFields", reader::readVariant, Variant.class);

            return new HistoryEventFieldList(_eventFields);
        }

        @Override
        public void encode(SerializationContext context, HistoryEventFieldList encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("EventFields", encodable._eventFields, writer::writeVariant);
        }
    }

}
