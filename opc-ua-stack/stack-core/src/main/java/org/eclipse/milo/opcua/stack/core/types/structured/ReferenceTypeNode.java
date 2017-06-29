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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class ReferenceTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.ReferenceTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceTypeNode_Encoding_DefaultXml;

    protected final ReferenceNode[] references;
    protected final Boolean isAbstract;
    protected final Boolean symmetric;
    protected final LocalizedText inverseName;

    public ReferenceTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this.references = null;
        this.isAbstract = null;
        this.symmetric = null;
        this.inverseName = null;
    }

    public ReferenceTypeNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, ReferenceNode[] references, Boolean isAbstract, Boolean symmetric, LocalizedText inverseName) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.references = references;
        this.isAbstract = isAbstract;
        this.symmetric = symmetric;
        this.inverseName = inverseName;
    }

    @Nullable
    public ReferenceNode[] getReferences() { return references; }

    public Boolean getIsAbstract() { return isAbstract; }

    public Boolean getSymmetric() { return symmetric; }

    public LocalizedText getInverseName() { return inverseName; }

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
            .add("IsAbstract", isAbstract)
            .add("Symmetric", symmetric)
            .add("InverseName", inverseName)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReferenceTypeNode> {

        @Override
        public Class<ReferenceTypeNode> getType() {
            return ReferenceTypeNode.class;
        }

        @Override
        public ReferenceTypeNode decode(UaDecoder decoder) throws UaSerializationException {
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
            Boolean isAbstract = decoder.readBoolean("IsAbstract");
            Boolean symmetric = decoder.readBoolean("Symmetric");
            LocalizedText inverseName = decoder.readLocalizedText("InverseName");

            return new ReferenceTypeNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, isAbstract, symmetric, inverseName);
        }

        @Override
        public void encode(ReferenceTypeNode value, UaEncoder encoder) throws UaSerializationException {
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
            encoder.writeBoolean("IsAbstract", value.isAbstract);
            encoder.writeBoolean("Symmetric", value.symmetric);
            encoder.writeLocalizedText("InverseName", value.inverseName);
        }
    }

}
