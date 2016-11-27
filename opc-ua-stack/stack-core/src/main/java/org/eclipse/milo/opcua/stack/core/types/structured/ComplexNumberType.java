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

@UaDataType("ComplexNumberType")
public class ComplexNumberType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ComplexNumberType;
    public static final NodeId BinaryEncodingId = Identifiers.ComplexNumberType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ComplexNumberType_Encoding_DefaultXml;

    protected final Float _real;
    protected final Float _imaginary;

    public ComplexNumberType() {
        this._real = null;
        this._imaginary = null;
    }

    public ComplexNumberType(Float _real, Float _imaginary) {
        this._real = _real;
        this._imaginary = _imaginary;
    }

    public Float getReal() { return _real; }

    public Float getImaginary() { return _imaginary; }

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

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ComplexNumberType> {
        @Override
        public ComplexNumberType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Float _real = reader.readFloat();
            Float _imaginary = reader.readFloat();

            return new ComplexNumberType(_real, _imaginary);
        }

        @Override
        public void encode(SerializationContext context, ComplexNumberType encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeFloat(encodable._real);
            writer.writeFloat(encodable._imaginary);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ComplexNumberType> {
        @Override
        public ComplexNumberType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Float _real = reader.readFloat("Real");
            Float _imaginary = reader.readFloat("Imaginary");

            return new ComplexNumberType(_real, _imaginary);
        }

        @Override
        public void encode(SerializationContext context, ComplexNumberType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeFloat("Real", encodable._real);
            writer.writeFloat("Imaginary", encodable._imaginary);
        }
    }

}
