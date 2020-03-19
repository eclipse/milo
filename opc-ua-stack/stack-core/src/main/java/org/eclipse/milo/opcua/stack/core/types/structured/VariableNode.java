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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class VariableNode extends InstanceNode implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=267");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=269");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=268");

    private final Variant value;

    private final NodeId dataType;

    private final Integer valueRank;

    private final UInteger[] arrayDimensions;

    private final UByte accessLevel;

    private final UByte userAccessLevel;

    private final Double minimumSamplingInterval;

    private final Boolean historizing;

    private final UInteger accessLevelEx;

    public VariableNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
                        LocalizedText displayName, LocalizedText description, UInteger writeMask,
                        UInteger userWriteMask, ReferenceNode[] references, Variant value, NodeId dataType,
                        Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                        Double minimumSamplingInterval, Boolean historizing, UInteger accessLevelEx) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.accessLevel = accessLevel;
        this.userAccessLevel = userAccessLevel;
        this.minimumSamplingInterval = minimumSamplingInterval;
        this.historizing = historizing;
        this.accessLevelEx = accessLevelEx;
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

    public Variant getValue() {
        return value;
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

    public UByte getAccessLevel() {
        return accessLevel;
    }

    public UByte getUserAccessLevel() {
        return userAccessLevel;
    }

    public Double getMinimumSamplingInterval() {
        return minimumSamplingInterval;
    }

    public Boolean getHistorizing() {
        return historizing;
    }

    public UInteger getAccessLevelEx() {
        return accessLevelEx;
    }

    public static final class Codec extends GenericDataTypeCodec<VariableNode> {
        @Override
        public Class<VariableNode> getType() {
            return VariableNode.class;
        }

        @Override
        public VariableNode decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            ReferenceNode[] references = (ReferenceNode[]) decoder.readStructArray("References", ReferenceNode.TYPE_ID);
            Variant value = decoder.readVariant("Value");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readUInt32Array("ArrayDimensions");
            UByte accessLevel = decoder.readByte("AccessLevel");
            UByte userAccessLevel = decoder.readByte("UserAccessLevel");
            Double minimumSamplingInterval = decoder.readDouble("MinimumSamplingInterval");
            Boolean historizing = decoder.readBoolean("Historizing");
            UInteger accessLevelEx = decoder.readUInt32("AccessLevelEx");
            return new VariableNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing, accessLevelEx);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, VariableNode value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeStructArray("References", value.getReferences(), ReferenceNode.TYPE_ID);
            encoder.writeVariant("Value", value.getValue());
            encoder.writeNodeId("DataType", value.getDataType());
            encoder.writeInt32("ValueRank", value.getValueRank());
            encoder.writeUInt32Array("ArrayDimensions", value.getArrayDimensions());
            encoder.writeByte("AccessLevel", value.getAccessLevel());
            encoder.writeByte("UserAccessLevel", value.getUserAccessLevel());
            encoder.writeDouble("MinimumSamplingInterval", value.getMinimumSamplingInterval());
            encoder.writeBoolean("Historizing", value.getHistorizing());
            encoder.writeUInt32("AccessLevelEx", value.getAccessLevelEx());
        }
    }
}
