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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class Argument extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=296");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=297");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=298");

    private final String name;

    private final NodeId dataType;

    private final Integer valueRank;

    private final UInteger[] arrayDimensions;

    private final LocalizedText description;

    public Argument(String name, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                    LocalizedText description) {
        this.name = name;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public NodeId getDataType() {
        return dataType;
    }

    public Integer getValueRank() {
        return valueRank;
    }

    public UInteger[] getArrayDimensions() {
        return arrayDimensions;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public static final class Codec extends GenericDataTypeCodec<Argument> {
        @Override
        public Class<Argument> getType() {
            return Argument.class;
        }

        @Override
        public Argument decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readUInt32Array("ArrayDimensions");
            LocalizedText description = decoder.readLocalizedText("Description");
            return new Argument(name, dataType, valueRank, arrayDimensions, description);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, Argument value) {
            encoder.writeString("Name", value.getName());
            encoder.writeNodeId("DataType", value.getDataType());
            encoder.writeInt32("ValueRank", value.getValueRank());
            encoder.writeUInt32Array("ArrayDimensions", value.getArrayDimensions());
            encoder.writeLocalizedText("Description", value.getDescription());
        }
    }
}
