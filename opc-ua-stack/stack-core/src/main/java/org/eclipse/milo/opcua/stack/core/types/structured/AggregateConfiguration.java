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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AggregateConfiguration extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=948");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=949");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=950");

    private final Boolean useServerCapabilitiesDefaults;

    private final Boolean treatUncertainAsBad;

    private final UByte percentDataBad;

    private final UByte percentDataGood;

    private final Boolean useSlopedExtrapolation;

    public AggregateConfiguration(Boolean useServerCapabilitiesDefaults, Boolean treatUncertainAsBad,
                                  UByte percentDataBad, UByte percentDataGood, Boolean useSlopedExtrapolation) {
        this.useServerCapabilitiesDefaults = useServerCapabilitiesDefaults;
        this.treatUncertainAsBad = treatUncertainAsBad;
        this.percentDataBad = percentDataBad;
        this.percentDataGood = percentDataGood;
        this.useSlopedExtrapolation = useSlopedExtrapolation;
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

    public Boolean getUseServerCapabilitiesDefaults() {
        return useServerCapabilitiesDefaults;
    }

    public Boolean getTreatUncertainAsBad() {
        return treatUncertainAsBad;
    }

    public UByte getPercentDataBad() {
        return percentDataBad;
    }

    public UByte getPercentDataGood() {
        return percentDataGood;
    }

    public Boolean getUseSlopedExtrapolation() {
        return useSlopedExtrapolation;
    }

    public static final class Codec extends GenericDataTypeCodec<AggregateConfiguration> {
        @Override
        public Class<AggregateConfiguration> getType() {
            return AggregateConfiguration.class;
        }

        @Override
        public AggregateConfiguration decode(SerializationContext context, UaDecoder decoder) {
            Boolean useServerCapabilitiesDefaults = decoder.readBoolean("UseServerCapabilitiesDefaults");
            Boolean treatUncertainAsBad = decoder.readBoolean("TreatUncertainAsBad");
            UByte percentDataBad = decoder.readByte("PercentDataBad");
            UByte percentDataGood = decoder.readByte("PercentDataGood");
            Boolean useSlopedExtrapolation = decoder.readBoolean("UseSlopedExtrapolation");
            return new AggregateConfiguration(useServerCapabilitiesDefaults, treatUncertainAsBad, percentDataBad, percentDataGood, useSlopedExtrapolation);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AggregateConfiguration value) {
            encoder.writeBoolean("UseServerCapabilitiesDefaults", value.getUseServerCapabilitiesDefaults());
            encoder.writeBoolean("TreatUncertainAsBad", value.getTreatUncertainAsBad());
            encoder.writeByte("PercentDataBad", value.getPercentDataBad());
            encoder.writeByte("PercentDataGood", value.getPercentDataGood());
            encoder.writeBoolean("UseSlopedExtrapolation", value.getUseSlopedExtrapolation());
        }
    }
}
