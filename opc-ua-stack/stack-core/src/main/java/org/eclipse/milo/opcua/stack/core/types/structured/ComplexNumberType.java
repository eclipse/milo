/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

public class ComplexNumberType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ComplexNumberType;
    public static final NodeId BinaryEncodingId = Identifiers.ComplexNumberType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ComplexNumberType_Encoding_DefaultXml;

    protected final Float real;
    protected final Float imaginary;

    public ComplexNumberType() {
        this.real = null;
        this.imaginary = null;
    }

    public ComplexNumberType(Float real, Float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Float getReal() { return real; }

    public Float getImaginary() { return imaginary; }

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

    public static class Codec extends BuiltinDataTypeCodec<ComplexNumberType> {

        @Override
        public Class<ComplexNumberType> getType() {
            return ComplexNumberType.class;
        }

        @Override
        public ComplexNumberType decode(UaDecoder decoder) throws UaSerializationException {
            Float real = decoder.readFloat("Real");
            Float imaginary = decoder.readFloat("Imaginary");

            return new ComplexNumberType(real, imaginary);
        }

        @Override
        public void encode(ComplexNumberType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeFloat("Real", value.real);
            encoder.writeFloat("Imaginary", value.imaginary);
        }
    }

}
