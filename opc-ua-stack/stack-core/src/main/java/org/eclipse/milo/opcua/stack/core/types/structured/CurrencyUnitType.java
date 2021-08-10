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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CurrencyUnitType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23498");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23507");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23520");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23528");

    private final Short numericCode;

    private final Byte exponent;

    private final String alphabeticCode;

    private final LocalizedText currency;

    public CurrencyUnitType(Short numericCode, Byte exponent, String alphabeticCode,
                            LocalizedText currency) {
        this.numericCode = numericCode;
        this.exponent = exponent;
        this.alphabeticCode = alphabeticCode;
        this.currency = currency;
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

    public Short getNumericCode() {
        return numericCode;
    }

    public Byte getExponent() {
        return exponent;
    }

    public String getAlphabeticCode() {
        return alphabeticCode;
    }

    public LocalizedText getCurrency() {
        return currency;
    }

    public static final class Codec extends GenericDataTypeCodec<CurrencyUnitType> {
        @Override
        public Class<CurrencyUnitType> getType() {
            return CurrencyUnitType.class;
        }

        @Override
        public CurrencyUnitType decode(SerializationContext context, UaDecoder decoder) {
            Short numericCode = decoder.readInt16("NumericCode");
            Byte exponent = decoder.readSByte("Exponent");
            String alphabeticCode = decoder.readString("AlphabeticCode");
            LocalizedText currency = decoder.readLocalizedText("Currency");
            return new CurrencyUnitType(numericCode, exponent, alphabeticCode, currency);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, CurrencyUnitType value) {
            encoder.writeInt16("NumericCode", value.getNumericCode());
            encoder.writeSByte("Exponent", value.getExponent());
            encoder.writeString("AlphabeticCode", value.getAlphabeticCode());
            encoder.writeLocalizedText("Currency", value.getCurrency());
        }
    }
}
