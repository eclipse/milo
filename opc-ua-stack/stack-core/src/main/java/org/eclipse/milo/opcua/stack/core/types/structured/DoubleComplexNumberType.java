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
public class DoubleComplexNumberType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12172");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12174");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12182");

    private final Double real;

    private final Double imaginary;

    public DoubleComplexNumberType(Double real, Double imaginary) {
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

    public Double getReal() {
        return real;
    }

    public Double getImaginary() {
        return imaginary;
    }

    public static final class Codec extends GenericDataTypeCodec<DoubleComplexNumberType> {
        @Override
        public Class<DoubleComplexNumberType> getType() {
            return DoubleComplexNumberType.class;
        }

        @Override
        public DoubleComplexNumberType decode(SerializationContext context, UaDecoder decoder) {
            Double real = decoder.readDouble("Real");
            Double imaginary = decoder.readDouble("Imaginary");
            return new DoubleComplexNumberType(real, imaginary);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DoubleComplexNumberType value) {
            encoder.writeDouble("Real", value.getReal());
            encoder.writeDouble("Imaginary", value.getImaginary());
        }
    }
}
