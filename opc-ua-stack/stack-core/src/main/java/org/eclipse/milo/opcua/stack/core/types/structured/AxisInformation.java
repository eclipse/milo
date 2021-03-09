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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AxisInformation extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12079");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12081");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12089");

    private final EUInformation engineeringUnits;

    private final Range euRange;

    private final LocalizedText title;

    private final AxisScaleEnumeration axisScaleType;

    private final Double[] axisSteps;

    public AxisInformation(EUInformation engineeringUnits, Range euRange, LocalizedText title,
                           AxisScaleEnumeration axisScaleType, Double[] axisSteps) {
        this.engineeringUnits = engineeringUnits;
        this.euRange = euRange;
        this.title = title;
        this.axisScaleType = axisScaleType;
        this.axisSteps = axisSteps;
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

    public EUInformation getEngineeringUnits() {
        return engineeringUnits;
    }

    public Range getEuRange() {
        return euRange;
    }

    public LocalizedText getTitle() {
        return title;
    }

    public AxisScaleEnumeration getAxisScaleType() {
        return axisScaleType;
    }

    public Double[] getAxisSteps() {
        return axisSteps;
    }

    public static final class Codec extends GenericDataTypeCodec<AxisInformation> {
        @Override
        public Class<AxisInformation> getType() {
            return AxisInformation.class;
        }

        @Override
        public AxisInformation decode(SerializationContext context, UaDecoder decoder) {
            EUInformation engineeringUnits = (EUInformation) decoder.readStruct("EngineeringUnits", EUInformation.TYPE_ID);
            Range euRange = (Range) decoder.readStruct("EURange", Range.TYPE_ID);
            LocalizedText title = decoder.readLocalizedText("Title");
            AxisScaleEnumeration axisScaleType = decoder.readEnum("AxisScaleType", AxisScaleEnumeration.class);
            Double[] axisSteps = decoder.readDoubleArray("AxisSteps");
            return new AxisInformation(engineeringUnits, euRange, title, axisScaleType, axisSteps);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AxisInformation value) {
            encoder.writeStruct("EngineeringUnits", value.getEngineeringUnits(), EUInformation.TYPE_ID);
            encoder.writeStruct("EURange", value.getEuRange(), Range.TYPE_ID);
            encoder.writeLocalizedText("Title", value.getTitle());
            encoder.writeEnum("AxisScaleType", value.getAxisScaleType());
            encoder.writeDoubleArray("AxisSteps", value.getAxisSteps());
        }
    }
}
