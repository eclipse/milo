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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DoubleComplexNumberType implements UaStructure {

    public static final NodeId TypeId = Identifiers.DoubleComplexNumberType;
    public static final NodeId BinaryEncodingId = Identifiers.DoubleComplexNumberType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DoubleComplexNumberType_Encoding_DefaultXml;

    protected final Double real;
    protected final Double imaginary;

    public DoubleComplexNumberType() {
        this.real = null;
        this.imaginary = null;
    }

    public DoubleComplexNumberType(Double real, Double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Double getReal() { return real; }

    public Double getImaginary() { return imaginary; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Real", real)
            .add("Imaginary", imaginary)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DoubleComplexNumberType> {

        @Override
        public Class<DoubleComplexNumberType> getType() {
            return DoubleComplexNumberType.class;
        }

        @Override
        public DoubleComplexNumberType decode(UaDecoder decoder) throws UaSerializationException {
            Double real = decoder.readDouble("Real");
            Double imaginary = decoder.readDouble("Imaginary");

            return new DoubleComplexNumberType(real, imaginary);
        }

        @Override
        public void encode(DoubleComplexNumberType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDouble("Real", value.real);
            encoder.writeDouble("Imaginary", value.imaginary);
        }
    }

}
