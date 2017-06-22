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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("VariableTypeNode")
public class VariableTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.VariableTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultXml;

    protected final Variant _value;
    protected final NodeId _dataType;
    protected final Integer _valueRank;
    protected final UInteger[] _arrayDimensions;
    protected final Boolean _isAbstract;

    public VariableTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this._value = null;
        this._dataType = null;
        this._valueRank = null;
        this._arrayDimensions = null;
        this._isAbstract = null;
    }

    public VariableTypeNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Variant _value, NodeId _dataType, Integer _valueRank, UInteger[] _arrayDimensions, Boolean _isAbstract) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._value = _value;
        this._dataType = _dataType;
        this._valueRank = _valueRank;
        this._arrayDimensions = _arrayDimensions;
        this._isAbstract = _isAbstract;
    }

    public Variant getValue() { return _value; }

    public NodeId getDataType() { return _dataType; }

    public Integer getValueRank() { return _valueRank; }

    @Nullable
    public UInteger[] getArrayDimensions() { return _arrayDimensions; }

    public Boolean getIsAbstract() { return _isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("NodeClass", _nodeClass)
            .add("BrowseName", _browseName)
            .add("DisplayName", _displayName)
            .add("Description", _description)
            .add("WriteMask", _writeMask)
            .add("UserWriteMask", _userWriteMask)
            .add("References", _references)
            .add("Value", _value)
            .add("DataType", _dataType)
            .add("ValueRank", _valueRank)
            .add("ArrayDimensions", _arrayDimensions)
            .add("IsAbstract", _isAbstract)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<VariableTypeNode> {
        @Override
        public VariableTypeNode decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            NodeClass _nodeClass = NodeClass.from(reader.readInt32());
            QualifiedName _browseName = reader.readQualifiedName();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();
            UInteger _writeMask = reader.readUInt32();
            UInteger _userWriteMask = reader.readUInt32();
            ReferenceNode[] _references =
                reader.readArray(
                    () -> (ReferenceNode) context.decode(
                        ReferenceNode.BinaryEncodingId, reader),
                    ReferenceNode.class
                );
            Variant _value = reader.readVariant();
            NodeId _dataType = reader.readNodeId();
            Integer _valueRank = reader.readInt32();
            UInteger[] _arrayDimensions = reader.readArray(reader::readUInt32, UInteger.class);
            Boolean _isAbstract = reader.readBoolean();

            return new VariableTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _value, _dataType, _valueRank, _arrayDimensions, _isAbstract);
        }

        @Override
        public void encode(SerializationContext context, VariableTypeNode value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeInt32(value._nodeClass != null ? value._nodeClass.getValue() : 0);
            writer.writeQualifiedName(value._browseName);
            writer.writeLocalizedText(value._displayName);
            writer.writeLocalizedText(value._description);
            writer.writeUInt32(value._writeMask);
            writer.writeUInt32(value._userWriteMask);
            writer.writeArray(
                value._references,
                e -> context.encode(ReferenceNode.BinaryEncodingId, e, writer)
            );
            writer.writeVariant(value._value);
            writer.writeNodeId(value._dataType);
            writer.writeInt32(value._valueRank);
            writer.writeArray(value._arrayDimensions, writer::writeUInt32);
            writer.writeBoolean(value._isAbstract);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<VariableTypeNode> {
        @Override
        public VariableTypeNode decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            NodeClass _nodeClass = NodeClass.from(reader.readInt32("NodeClass"));
            QualifiedName _browseName = reader.readQualifiedName("BrowseName");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");
            UInteger _writeMask = reader.readUInt32("WriteMask");
            UInteger _userWriteMask = reader.readUInt32("UserWriteMask");
            ReferenceNode[] _references =
                reader.readArray(
                    "References",
                    f -> (ReferenceNode) context.decode(
                        ReferenceNode.XmlEncodingId, reader),
                    ReferenceNode.class
                );
            Variant _value = reader.readVariant("Value");
            NodeId _dataType = reader.readNodeId("DataType");
            Integer _valueRank = reader.readInt32("ValueRank");
            UInteger[] _arrayDimensions = reader.readArray("ArrayDimensions", reader::readUInt32, UInteger.class);
            Boolean _isAbstract = reader.readBoolean("IsAbstract");

            return new VariableTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _value, _dataType, _valueRank, _arrayDimensions, _isAbstract);
        }

        @Override
        public void encode(SerializationContext context, VariableTypeNode encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeInt32("NodeClass", encodable._nodeClass != null ? encodable._nodeClass.getValue() : 0);
            writer.writeQualifiedName("BrowseName", encodable._browseName);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
            writer.writeUInt32("WriteMask", encodable._writeMask);
            writer.writeUInt32("UserWriteMask", encodable._userWriteMask);
            writer.writeArray(
                "References",
                encodable._references,
                (f, e) -> context.encode(ReferenceNode.XmlEncodingId, e, writer)
            );
            writer.writeVariant("Value", encodable._value);
            writer.writeNodeId("DataType", encodable._dataType);
            writer.writeInt32("ValueRank", encodable._valueRank);
            writer.writeArray("ArrayDimensions", encodable._arrayDimensions, writer::writeUInt32);
            writer.writeBoolean("IsAbstract", encodable._isAbstract);
        }
    }

}
