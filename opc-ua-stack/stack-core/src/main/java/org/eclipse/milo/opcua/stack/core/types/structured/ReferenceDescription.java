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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class ReferenceDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReferenceDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceDescription_Encoding_DefaultXml;

    protected final NodeId referenceTypeId;
    protected final Boolean isForward;
    protected final ExpandedNodeId nodeId;
    protected final QualifiedName browseName;
    protected final LocalizedText displayName;
    protected final NodeClass nodeClass;
    protected final ExpandedNodeId typeDefinition;

    public ReferenceDescription() {
        this.referenceTypeId = null;
        this.isForward = null;
        this.nodeId = null;
        this.browseName = null;
        this.displayName = null;
        this.nodeClass = null;
        this.typeDefinition = null;
    }

    public ReferenceDescription(NodeId referenceTypeId, Boolean isForward, ExpandedNodeId nodeId, QualifiedName browseName, LocalizedText displayName, NodeClass nodeClass, ExpandedNodeId typeDefinition) {
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.nodeId = nodeId;
        this.browseName = browseName;
        this.displayName = displayName;
        this.nodeClass = nodeClass;
        this.typeDefinition = typeDefinition;
    }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsForward() { return isForward; }

    public ExpandedNodeId getNodeId() { return nodeId; }

    public QualifiedName getBrowseName() { return browseName; }

    public LocalizedText getDisplayName() { return displayName; }

    public NodeClass getNodeClass() { return nodeClass; }

    public ExpandedNodeId getTypeDefinition() { return typeDefinition; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IsForward", isForward)
            .add("NodeId", nodeId)
            .add("BrowseName", browseName)
            .add("DisplayName", displayName)
            .add("NodeClass", nodeClass)
            .add("TypeDefinition", typeDefinition)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReferenceDescription> {

        @Override
        public Class<ReferenceDescription> getType() {
            return ReferenceDescription.class;
        }

        @Override
        public ReferenceDescription decode(UaDecoder decoder) throws UaSerializationException {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            ExpandedNodeId nodeId = decoder.readExpandedNodeId("NodeId");
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            NodeClass nodeClass = NodeClass.from(decoder.readInt32("NodeClass"));
            ExpandedNodeId typeDefinition = decoder.readExpandedNodeId("TypeDefinition");

            return new ReferenceDescription(referenceTypeId, isForward, nodeId, browseName, displayName, nodeClass, typeDefinition);
        }

        @Override
        public void encode(ReferenceDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsForward", value.isForward);
            encoder.writeExpandedNodeId("NodeId", value.nodeId);
            encoder.writeQualifiedName("BrowseName", value.browseName);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeInt32("NodeClass", value.nodeClass != null ? value.nodeClass.getValue() : 0);
            encoder.writeExpandedNodeId("TypeDefinition", value.typeDefinition);
        }
    }

}
