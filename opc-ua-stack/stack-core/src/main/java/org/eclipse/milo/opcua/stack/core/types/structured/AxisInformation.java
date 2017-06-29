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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;

public class AxisInformation implements UaStructure {

    public static final NodeId TypeId = Identifiers.AxisInformation;
    public static final NodeId BinaryEncodingId = Identifiers.AxisInformation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AxisInformation_Encoding_DefaultXml;

    protected final EUInformation engineeringUnits;
    protected final Range eURange;
    protected final LocalizedText title;
    protected final AxisScaleEnumeration axisScaleType;
    protected final Double[] axisSteps;

    public AxisInformation() {
        this.engineeringUnits = null;
        this.eURange = null;
        this.title = null;
        this.axisScaleType = null;
        this.axisSteps = null;
    }

    public AxisInformation(EUInformation engineeringUnits, Range eURange, LocalizedText title, AxisScaleEnumeration axisScaleType, Double[] axisSteps) {
        this.engineeringUnits = engineeringUnits;
        this.eURange = eURange;
        this.title = title;
        this.axisScaleType = axisScaleType;
        this.axisSteps = axisSteps;
    }

    public EUInformation getEngineeringUnits() { return engineeringUnits; }

    public Range getEURange() { return eURange; }

    public LocalizedText getTitle() { return title; }

    public AxisScaleEnumeration getAxisScaleType() { return axisScaleType; }

    @Nullable
    public Double[] getAxisSteps() { return axisSteps; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EngineeringUnits", engineeringUnits)
            .add("EURange", eURange)
            .add("Title", title)
            .add("AxisScaleType", axisScaleType)
            .add("AxisSteps", axisSteps)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AxisInformation> {

        @Override
        public Class<AxisInformation> getType() {
            return AxisInformation.class;
        }

        @Override
        public AxisInformation decode(UaDecoder decoder) throws UaSerializationException {
            EUInformation engineeringUnits = (EUInformation) decoder.readBuiltinStruct("EngineeringUnits", EUInformation.class);
            Range eURange = (Range) decoder.readBuiltinStruct("EURange", Range.class);
            LocalizedText title = decoder.readLocalizedText("Title");
            AxisScaleEnumeration axisScaleType = AxisScaleEnumeration.from(decoder.readInt32("AxisScaleType"));
            Double[] axisSteps = decoder.readArray("AxisSteps", decoder::readDouble, Double.class);

            return new AxisInformation(engineeringUnits, eURange, title, axisScaleType, axisSteps);
        }

        @Override
        public void encode(AxisInformation value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("EngineeringUnits", value.engineeringUnits, EUInformation.class);
            encoder.writeBuiltinStruct("EURange", value.eURange, Range.class);
            encoder.writeLocalizedText("Title", value.title);
            encoder.writeInt32("AxisScaleType", value.axisScaleType != null ? value.axisScaleType.getValue() : 0);
            encoder.writeArray("AxisSteps", value.axisSteps, encoder::writeDouble);
        }
    }

}
