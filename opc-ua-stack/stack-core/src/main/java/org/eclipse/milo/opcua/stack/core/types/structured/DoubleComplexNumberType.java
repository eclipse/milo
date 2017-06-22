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

@UaDataType("DoubleComplexNumberType")
public class DoubleComplexNumberType implements UaStructure {

    public static final NodeId TypeId = Identifiers.DoubleComplexNumberType;
    public static final NodeId BinaryEncodingId = Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DoubleComplexNumberType_Encoding_DefaultXml;

    protected final Double _real;
    protected final Double _imaginary;

    public DoubleComplexNumberType() {
        this._real = null;
        this._imaginary = null;
    }

    public DoubleComplexNumberType(Double _real, Double _imaginary) {
        this._real = _real;
        this._imaginary = _imaginary;
    }

    public Double getReal() { return _real; }

    public Double getImaginary() { return _imaginary; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Real", _real)
            .add("Imaginary", _imaginary)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DoubleComplexNumberType> {
        @Override
        public DoubleComplexNumberType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Double _real = reader.readDouble();
            Double _imaginary = reader.readDouble();

            return new DoubleComplexNumberType(_real, _imaginary);
        }

        @Override
        public void encode(SerializationContext context, DoubleComplexNumberType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDouble(value._real);
            writer.writeDouble(value._imaginary);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DoubleComplexNumberType> {
        @Override
        public DoubleComplexNumberType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Double _real = reader.readDouble("Real");
            Double _imaginary = reader.readDouble("Imaginary");

            return new DoubleComplexNumberType(_real, _imaginary);
        }

        @Override
        public void encode(SerializationContext context, DoubleComplexNumberType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDouble("Real", encodable._real);
            writer.writeDouble("Imaginary", encodable._imaginary);
        }
    }

}
