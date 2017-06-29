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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class AddNodesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddNodesItem;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesItem_Encoding_DefaultXml;

    protected final ExpandedNodeId parentNodeId;
    protected final NodeId referenceTypeId;
    protected final ExpandedNodeId requestedNewNodeId;
    protected final QualifiedName browseName;
    protected final NodeClass nodeClass;
    protected final ExtensionObject nodeAttributes;
    protected final ExpandedNodeId typeDefinition;

    public AddNodesItem() {
        this.parentNodeId = null;
        this.referenceTypeId = null;
        this.requestedNewNodeId = null;
        this.browseName = null;
        this.nodeClass = null;
        this.nodeAttributes = null;
        this.typeDefinition = null;
    }

    public AddNodesItem(ExpandedNodeId parentNodeId, NodeId referenceTypeId, ExpandedNodeId requestedNewNodeId, QualifiedName browseName, NodeClass nodeClass, ExtensionObject nodeAttributes, ExpandedNodeId typeDefinition) {
        this.parentNodeId = parentNodeId;
        this.referenceTypeId = referenceTypeId;
        this.requestedNewNodeId = requestedNewNodeId;
        this.browseName = browseName;
        this.nodeClass = nodeClass;
        this.nodeAttributes = nodeAttributes;
        this.typeDefinition = typeDefinition;
    }

    public ExpandedNodeId getParentNodeId() { return parentNodeId; }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public ExpandedNodeId getRequestedNewNodeId() { return requestedNewNodeId; }

    public QualifiedName getBrowseName() { return browseName; }

    public NodeClass getNodeClass() { return nodeClass; }

    public ExtensionObject getNodeAttributes() { return nodeAttributes; }

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
            .add("ParentNodeId", parentNodeId)
            .add("ReferenceTypeId", referenceTypeId)
            .add("RequestedNewNodeId", requestedNewNodeId)
            .add("BrowseName", browseName)
            .add("NodeClass", nodeClass)
            .add("NodeAttributes", nodeAttributes)
            .add("TypeDefinition", typeDefinition)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AddNodesItem> {

        @Override
        public Class<AddNodesItem> getType() {
            return AddNodesItem.class;
        }

        @Override
        public AddNodesItem decode(UaDecoder decoder) throws UaSerializationException {
            ExpandedNodeId parentNodeId = decoder.readExpandedNodeId("ParentNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            ExpandedNodeId requestedNewNodeId = decoder.readExpandedNodeId("RequestedNewNodeId");
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            NodeClass nodeClass = NodeClass.from(decoder.readInt32("NodeClass"));
            ExtensionObject nodeAttributes = decoder.readExtensionObject("NodeAttributes");
            ExpandedNodeId typeDefinition = decoder.readExpandedNodeId("TypeDefinition");

            return new AddNodesItem(parentNodeId, referenceTypeId, requestedNewNodeId, browseName, nodeClass, nodeAttributes, typeDefinition);
        }

        @Override
        public void encode(AddNodesItem value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeExpandedNodeId("ParentNodeId", value.parentNodeId);
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeExpandedNodeId("RequestedNewNodeId", value.requestedNewNodeId);
            encoder.writeQualifiedName("BrowseName", value.browseName);
            encoder.writeInt32("NodeClass", value.nodeClass != null ? value.nodeClass.getValue() : 0);
            encoder.writeExtensionObject("NodeAttributes", value.nodeAttributes);
            encoder.writeExpandedNodeId("TypeDefinition", value.typeDefinition);
        }
    }

}
