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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("ReferenceTypeNode")
public class ReferenceTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.ReferenceTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceTypeNode_Encoding_DefaultXml;

    protected final ReferenceNode[] _references;
    protected final Boolean _isAbstract;
    protected final Boolean _symmetric;
    protected final LocalizedText _inverseName;

    public ReferenceTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this._references = null;
        this._isAbstract = null;
        this._symmetric = null;
        this._inverseName = null;
    }

    public ReferenceTypeNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Boolean _isAbstract, Boolean _symmetric, LocalizedText _inverseName) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._references = _references;
        this._isAbstract = _isAbstract;
        this._symmetric = _symmetric;
        this._inverseName = _inverseName;
    }

    @Nullable
    public ReferenceNode[] getReferences() { return _references; }

    public Boolean getIsAbstract() { return _isAbstract; }

    public Boolean getSymmetric() { return _symmetric; }

    public LocalizedText getInverseName() { return _inverseName; }

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
            .add("IsAbstract", _isAbstract)
            .add("Symmetric", _symmetric)
            .add("InverseName", _inverseName)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReferenceTypeNode> {
        @Override
        public ReferenceTypeNode decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
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
            Boolean _isAbstract = reader.readBoolean();
            Boolean _symmetric = reader.readBoolean();
            LocalizedText _inverseName = reader.readLocalizedText();

            return new ReferenceTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _isAbstract, _symmetric, _inverseName);
        }

        @Override
        public void encode(SerializationContext context, ReferenceTypeNode value, OpcBinaryStreamWriter writer) throws UaSerializationException {
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
            writer.writeBoolean(value._isAbstract);
            writer.writeBoolean(value._symmetric);
            writer.writeLocalizedText(value._inverseName);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReferenceTypeNode> {
        @Override
        public ReferenceTypeNode decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
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
            Boolean _isAbstract = reader.readBoolean("IsAbstract");
            Boolean _symmetric = reader.readBoolean("Symmetric");
            LocalizedText _inverseName = reader.readLocalizedText("InverseName");

            return new ReferenceTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _isAbstract, _symmetric, _inverseName);
        }

        @Override
        public void encode(SerializationContext context, ReferenceTypeNode encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
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
            writer.writeBoolean("IsAbstract", encodable._isAbstract);
            writer.writeBoolean("Symmetric", encodable._symmetric);
            writer.writeLocalizedText("InverseName", encodable._inverseName);
        }
    }

}
