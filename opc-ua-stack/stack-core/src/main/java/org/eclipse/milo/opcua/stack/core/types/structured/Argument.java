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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class Argument implements UaStructure {

    public static final NodeId TypeId = Identifiers.Argument;
    public static final NodeId BinaryEncodingId = Identifiers.Argument_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Argument_Encoding_DefaultXml;

    protected final String name;
    protected final NodeId dataType;
    protected final Integer valueRank;
    protected final UInteger[] arrayDimensions;
    protected final LocalizedText description;

    public Argument() {
        this.name = null;
        this.dataType = null;
        this.valueRank = null;
        this.arrayDimensions = null;
        this.description = null;
    }

    public Argument(String name, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, LocalizedText description) {
        this.name = name;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.description = description;
    }

    public String getName() { return name; }

    public NodeId getDataType() { return dataType; }

    public Integer getValueRank() { return valueRank; }

    @Nullable
    public UInteger[] getArrayDimensions() { return arrayDimensions; }

    public LocalizedText getDescription() { return description; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Name", name)
            .add("DataType", dataType)
            .add("ValueRank", valueRank)
            .add("ArrayDimensions", arrayDimensions)
            .add("Description", description)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<Argument> {

        @Override
        public Class<Argument> getType() {
            return Argument.class;
        }

        @Override
        public Argument decode(UaDecoder decoder) throws UaSerializationException {
            String name = decoder.readString("Name");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readArray("ArrayDimensions", decoder::readUInt32, UInteger.class);
            LocalizedText description = decoder.readLocalizedText("Description");

            return new Argument(name, dataType, valueRank, arrayDimensions, description);
        }

        @Override
        public void encode(Argument value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("Name", value.name);
            encoder.writeNodeId("DataType", value.dataType);
            encoder.writeInt32("ValueRank", value.valueRank);
            encoder.writeArray("ArrayDimensions", value.arrayDimensions, encoder::writeUInt32);
            encoder.writeLocalizedText("Description", value.description);
        }
    }

}
