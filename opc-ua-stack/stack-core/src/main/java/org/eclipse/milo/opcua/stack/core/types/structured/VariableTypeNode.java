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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class VariableTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.VariableTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultXml;

    protected final Variant value;
    protected final NodeId dataType;
    protected final Integer valueRank;
    protected final UInteger[] arrayDimensions;
    protected final Boolean isAbstract;

    public VariableTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this.value = null;
        this.dataType = null;
        this.valueRank = null;
        this.arrayDimensions = null;
        this.isAbstract = null;
    }

    public VariableTypeNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, ReferenceNode[] references, Variant value, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, Boolean isAbstract) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.isAbstract = isAbstract;
    }

    public Variant getValue() { return value; }

    public NodeId getDataType() { return dataType; }

    public Integer getValueRank() { return valueRank; }

    @Nullable
    public UInteger[] getArrayDimensions() { return arrayDimensions; }

    public Boolean getIsAbstract() { return isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("NodeClass", nodeClass)
            .add("BrowseName", browseName)
            .add("DisplayName", displayName)
            .add("Description", description)
            .add("WriteMask", writeMask)
            .add("UserWriteMask", userWriteMask)
            .add("References", references)
            .add("Value", value)
            .add("DataType", dataType)
            .add("ValueRank", valueRank)
            .add("ArrayDimensions", arrayDimensions)
            .add("IsAbstract", isAbstract)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<VariableTypeNode> {

        @Override
        public Class<VariableTypeNode> getType() {
            return VariableTypeNode.class;
        }

        @Override
        public VariableTypeNode decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeClass nodeClass = NodeClass.from(decoder.readInt32("NodeClass"));
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            ReferenceNode[] references =
                decoder.readBuiltinStructArray(
                    "References",
                    ReferenceNode.class
                );
            Variant value = decoder.readVariant("Value");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readArray("ArrayDimensions", decoder::readUInt32, UInteger.class);
            Boolean isAbstract = decoder.readBoolean("IsAbstract");

            return new VariableTypeNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, value, dataType, valueRank, arrayDimensions, isAbstract);
        }

        @Override
        public void encode(VariableTypeNode value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeInt32("NodeClass", value.nodeClass != null ? value.nodeClass.getValue() : 0);
            encoder.writeQualifiedName("BrowseName", value.browseName);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeBuiltinStructArray(
                "References",
                value.references,
                ReferenceNode.class
            );
            encoder.writeVariant("Value", value.value);
            encoder.writeNodeId("DataType", value.dataType);
            encoder.writeInt32("ValueRank", value.valueRank);
            encoder.writeArray("ArrayDimensions", value.arrayDimensions, encoder::writeUInt32);
            encoder.writeBoolean("IsAbstract", value.isAbstract);
        }
    }

}
