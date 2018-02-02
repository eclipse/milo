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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public class AggregateConfiguration implements UaStructure {

    public static final NodeId TypeId = Identifiers.AggregateConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateConfiguration_Encoding_DefaultXml;

    protected final Boolean useServerCapabilitiesDefaults;
    protected final Boolean treatUncertainAsBad;
    protected final UByte percentDataBad;
    protected final UByte percentDataGood;
    protected final Boolean useSlopedExtrapolation;

    public AggregateConfiguration() {
        this.useServerCapabilitiesDefaults = null;
        this.treatUncertainAsBad = null;
        this.percentDataBad = null;
        this.percentDataGood = null;
        this.useSlopedExtrapolation = null;
    }

    public AggregateConfiguration(Boolean useServerCapabilitiesDefaults, Boolean treatUncertainAsBad, UByte percentDataBad, UByte percentDataGood, Boolean useSlopedExtrapolation) {
        this.useServerCapabilitiesDefaults = useServerCapabilitiesDefaults;
        this.treatUncertainAsBad = treatUncertainAsBad;
        this.percentDataBad = percentDataBad;
        this.percentDataGood = percentDataGood;
        this.useSlopedExtrapolation = useSlopedExtrapolation;
    }

    public Boolean getUseServerCapabilitiesDefaults() { return useServerCapabilitiesDefaults; }

    public Boolean getTreatUncertainAsBad() { return treatUncertainAsBad; }

    public UByte getPercentDataBad() { return percentDataBad; }

    public UByte getPercentDataGood() { return percentDataGood; }

    public Boolean getUseSlopedExtrapolation() { return useSlopedExtrapolation; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("UseServerCapabilitiesDefaults", useServerCapabilitiesDefaults)
            .add("TreatUncertainAsBad", treatUncertainAsBad)
            .add("PercentDataBad", percentDataBad)
            .add("PercentDataGood", percentDataGood)
            .add("UseSlopedExtrapolation", useSlopedExtrapolation)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AggregateConfiguration> {

        @Override
        public Class<AggregateConfiguration> getType() {
            return AggregateConfiguration.class;
        }

        @Override
        public AggregateConfiguration decode(UaDecoder decoder) throws UaSerializationException {
            Boolean useServerCapabilitiesDefaults = decoder.readBoolean("UseServerCapabilitiesDefaults");
            Boolean treatUncertainAsBad = decoder.readBoolean("TreatUncertainAsBad");
            UByte percentDataBad = decoder.readByte("PercentDataBad");
            UByte percentDataGood = decoder.readByte("PercentDataGood");
            Boolean useSlopedExtrapolation = decoder.readBoolean("UseSlopedExtrapolation");

            return new AggregateConfiguration(useServerCapabilitiesDefaults, treatUncertainAsBad, percentDataBad, percentDataGood, useSlopedExtrapolation);
        }

        @Override
        public void encode(AggregateConfiguration value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBoolean("UseServerCapabilitiesDefaults", value.useServerCapabilitiesDefaults);
            encoder.writeBoolean("TreatUncertainAsBad", value.treatUncertainAsBad);
            encoder.writeByte("PercentDataBad", value.percentDataBad);
            encoder.writeByte("PercentDataGood", value.percentDataGood);
            encoder.writeBoolean("UseSlopedExtrapolation", value.useSlopedExtrapolation);
        }
    }

}
