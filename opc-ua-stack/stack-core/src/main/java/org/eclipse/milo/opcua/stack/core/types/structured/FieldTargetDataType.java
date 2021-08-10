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

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.OverrideValueHandling;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class FieldTargetDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14744");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14848");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14804");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15061");

    private final UUID dataSetFieldId;

    private final String receiverIndexRange;

    private final NodeId targetNodeId;

    private final UInteger attributeId;

    private final String writeIndexRange;

    private final OverrideValueHandling overrideValueHandling;

    private final Variant overrideValue;

    public FieldTargetDataType(UUID dataSetFieldId, String receiverIndexRange, NodeId targetNodeId,
                               UInteger attributeId, String writeIndexRange, OverrideValueHandling overrideValueHandling,
                               Variant overrideValue) {
        this.dataSetFieldId = dataSetFieldId;
        this.receiverIndexRange = receiverIndexRange;
        this.targetNodeId = targetNodeId;
        this.attributeId = attributeId;
        this.writeIndexRange = writeIndexRange;
        this.overrideValueHandling = overrideValueHandling;
        this.overrideValue = overrideValue;
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

    public UUID getDataSetFieldId() {
        return dataSetFieldId;
    }

    public String getReceiverIndexRange() {
        return receiverIndexRange;
    }

    public NodeId getTargetNodeId() {
        return targetNodeId;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getWriteIndexRange() {
        return writeIndexRange;
    }

    public OverrideValueHandling getOverrideValueHandling() {
        return overrideValueHandling;
    }

    public Variant getOverrideValue() {
        return overrideValue;
    }

    public static final class Codec extends GenericDataTypeCodec<FieldTargetDataType> {
        @Override
        public Class<FieldTargetDataType> getType() {
            return FieldTargetDataType.class;
        }

        @Override
        public FieldTargetDataType decode(SerializationContext context, UaDecoder decoder) {
            UUID dataSetFieldId = decoder.readGuid("DataSetFieldId");
            String receiverIndexRange = decoder.readString("ReceiverIndexRange");
            NodeId targetNodeId = decoder.readNodeId("TargetNodeId");
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String writeIndexRange = decoder.readString("WriteIndexRange");
            OverrideValueHandling overrideValueHandling = decoder.readEnum("OverrideValueHandling", OverrideValueHandling.class);
            Variant overrideValue = decoder.readVariant("OverrideValue");
            return new FieldTargetDataType(dataSetFieldId, receiverIndexRange, targetNodeId, attributeId, writeIndexRange, overrideValueHandling, overrideValue);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, FieldTargetDataType value) {
            encoder.writeGuid("DataSetFieldId", value.getDataSetFieldId());
            encoder.writeString("ReceiverIndexRange", value.getReceiverIndexRange());
            encoder.writeNodeId("TargetNodeId", value.getTargetNodeId());
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("WriteIndexRange", value.getWriteIndexRange());
            encoder.writeEnum("OverrideValueHandling", value.getOverrideValueHandling());
            encoder.writeVariant("OverrideValue", value.getOverrideValue());
        }
    }
}
