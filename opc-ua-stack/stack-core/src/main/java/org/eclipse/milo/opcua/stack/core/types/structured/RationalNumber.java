/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RationalNumber extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18806");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18815");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=18851");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19064");

    private final Integer numerator;

    private final UInteger denominator;

    public RationalNumber(Integer numerator, UInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public Integer getNumerator() {
        return numerator;
    }

    public UInteger getDenominator() {
        return denominator;
    }

    public static final class Codec extends GenericDataTypeCodec<RationalNumber> {
        @Override
        public Class<RationalNumber> getType() {
            return RationalNumber.class;
        }

        @Override
        public RationalNumber decode(SerializationContext context, UaDecoder decoder) {
            Integer numerator = decoder.readInt32("Numerator");
            UInteger denominator = decoder.readUInt32("Denominator");
            return new RationalNumber(numerator, denominator);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RationalNumber value) {
            encoder.writeInt32("Numerator", value.getNumerator());
            encoder.writeUInt32("Denominator", value.getDenominator());
        }
    }
}
