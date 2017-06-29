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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class VariableAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.VariableAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.VariableAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableAttributes_Encoding_DefaultXml;

    protected final Variant value;
    protected final NodeId dataType;
    protected final Integer valueRank;
    protected final UInteger[] arrayDimensions;
    protected final UByte accessLevel;
    protected final UByte userAccessLevel;
    protected final Double minimumSamplingInterval;
    protected final Boolean historizing;

    public VariableAttributes() {
        super(null, null, null, null, null);
        this.value = null;
        this.dataType = null;
        this.valueRank = null;
        this.arrayDimensions = null;
        this.accessLevel = null;
        this.userAccessLevel = null;
        this.minimumSamplingInterval = null;
        this.historizing = null;
    }

    public VariableAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, Variant value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.accessLevel = accessLevel;
        this.userAccessLevel = userAccessLevel;
        this.minimumSamplingInterval = minimumSamplingInterval;
        this.historizing = historizing;
    }

    public Variant getValue() { return value; }

    public NodeId getDataType() { return dataType; }

    public Integer getValueRank() { return valueRank; }

    @Nullable
    public UInteger[] getArrayDimensions() { return arrayDimensions; }

    public UByte getAccessLevel() { return accessLevel; }

    public UByte getUserAccessLevel() { return userAccessLevel; }

    public Double getMinimumSamplingInterval() { return minimumSamplingInterval; }

    public Boolean getHistorizing() { return historizing; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedAttributes", specifiedAttributes)
            .add("DisplayName", displayName)
            .add("Description", description)
            .add("WriteMask", writeMask)
            .add("UserWriteMask", userWriteMask)
            .add("Value", value)
            .add("DataType", dataType)
            .add("ValueRank", valueRank)
            .add("ArrayDimensions", arrayDimensions)
            .add("AccessLevel", accessLevel)
            .add("UserAccessLevel", userAccessLevel)
            .add("MinimumSamplingInterval", minimumSamplingInterval)
            .add("Historizing", historizing)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<VariableAttributes> {

        @Override
        public Class<VariableAttributes> getType() {
            return VariableAttributes.class;
        }

        @Override
        public VariableAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Variant value = decoder.readVariant("Value");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readArray("ArrayDimensions", decoder::readUInt32, UInteger.class);
            UByte accessLevel = decoder.readByte("AccessLevel");
            UByte userAccessLevel = decoder.readByte("UserAccessLevel");
            Double minimumSamplingInterval = decoder.readDouble("MinimumSamplingInterval");
            Boolean historizing = decoder.readBoolean("Historizing");

            return new VariableAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
        }

        @Override
        public void encode(VariableAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeVariant("Value", value.value);
            encoder.writeNodeId("DataType", value.dataType);
            encoder.writeInt32("ValueRank", value.valueRank);
            encoder.writeArray("ArrayDimensions", value.arrayDimensions, encoder::writeUInt32);
            encoder.writeByte("AccessLevel", value.accessLevel);
            encoder.writeByte("UserAccessLevel", value.userAccessLevel);
            encoder.writeDouble("MinimumSamplingInterval", value.minimumSamplingInterval);
            encoder.writeBoolean("Historizing", value.historizing);
        }
    }

}
