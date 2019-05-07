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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ComplexNumberType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12171");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12173");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12181");

    private final Float real;

    private final Float imaginary;

    public ComplexNumberType(Float real, Float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public Float getReal() {
        return real;
    }

    public Float getImaginary() {
        return imaginary;
    }

    public static final class Codec extends GenericDataTypeCodec<ComplexNumberType> {
        @Override
        public Class<ComplexNumberType> getType() {
            return ComplexNumberType.class;
        }

        @Override
        public ComplexNumberType decode(SerializationContext context, UaDecoder decoder) {
            Float real = decoder.readFloat("Real");
            Float imaginary = decoder.readFloat("Imaginary");
            return new ComplexNumberType(real, imaginary);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ComplexNumberType value) {
            encoder.writeFloat("Real", value.getReal());
            encoder.writeFloat("Imaginary", value.getImaginary());
        }
    }
}
