/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.4">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.4</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AggregateConfiguration extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=948");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=950");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=949");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15304");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 950),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("UseServerCapabilitiesDefaults", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("TreatUncertainAsBad", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("PercentDataBad", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("PercentDataGood", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("UseSlopedExtrapolation", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AggregateConfiguration> {
        @Override
        public Class<AggregateConfiguration> getType() {
            return AggregateConfiguration.class;
        }

        @Override
        public AggregateConfiguration decodeType(SerializationContext context, UaDecoder decoder) {
            Boolean useServerCapabilitiesDefaults = decoder.readBoolean("UseServerCapabilitiesDefaults");
            Boolean treatUncertainAsBad = decoder.readBoolean("TreatUncertainAsBad");
            UByte percentDataBad = decoder.readByte("PercentDataBad");
            UByte percentDataGood = decoder.readByte("PercentDataGood");
            Boolean useSlopedExtrapolation = decoder.readBoolean("UseSlopedExtrapolation");
            return new AggregateConfiguration(useServerCapabilitiesDefaults, treatUncertainAsBad, percentDataBad, percentDataGood, useSlopedExtrapolation);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               AggregateConfiguration value) {
            encoder.writeBoolean("UseServerCapabilitiesDefaults", value.getUseServerCapabilitiesDefaults());
            encoder.writeBoolean("TreatUncertainAsBad", value.getTreatUncertainAsBad());
            encoder.writeByte("PercentDataBad", value.getPercentDataBad());
            encoder.writeByte("PercentDataGood", value.getPercentDataGood());
            encoder.writeBoolean("UseSlopedExtrapolation", value.getUseSlopedExtrapolation());
        }
    }
}
