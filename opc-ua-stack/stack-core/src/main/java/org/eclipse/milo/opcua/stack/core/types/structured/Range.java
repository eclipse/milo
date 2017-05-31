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

@UaDataType("Range")
public class Range implements UaStructure {

    public static final NodeId TypeId = Identifiers.Range;
    public static final NodeId BinaryEncodingId = Identifiers.Range_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Range_Encoding_DefaultXml;

    protected final Double _low;
    protected final Double _high;

    public Range() {
        this._low = null;
        this._high = null;
    }

    public Range(Double _low, Double _high) {
        this._low = _low;
        this._high = _high;
    }

    public Double getLow() { return _low; }

    public Double getHigh() { return _high; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Low", _low)
            .add("High", _high)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<Range> {
        @Override
        public Range decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Double _low = reader.readDouble();
            Double _high = reader.readDouble();

            return new Range(_low, _high);
        }

        @Override
        public void encode(SerializationContext context, Range encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDouble(encodable._low);
            writer.writeDouble(encodable._high);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<Range> {
        @Override
        public Range decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Double _low = reader.readDouble("Low");
            Double _high = reader.readDouble("High");

            return new Range(_low, _high);
        }

        @Override
        public void encode(SerializationContext context, Range encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDouble("Low", encodable._low);
            writer.writeDouble("High", encodable._high);
        }
    }

}
