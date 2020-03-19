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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AddNodesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=376");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=377");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=378");

    private final ExpandedNodeId parentNodeId;

    private final NodeId referenceTypeId;

    private final ExpandedNodeId requestedNewNodeId;

    private final QualifiedName browseName;

    private final NodeClass nodeClass;

    private final ExtensionObject nodeAttributes;

    private final ExpandedNodeId typeDefinition;

    public AddNodesItem(ExpandedNodeId parentNodeId, NodeId referenceTypeId,
                        ExpandedNodeId requestedNewNodeId, QualifiedName browseName, NodeClass nodeClass,
                        ExtensionObject nodeAttributes, ExpandedNodeId typeDefinition) {
        this.parentNodeId = parentNodeId;
        this.referenceTypeId = referenceTypeId;
        this.requestedNewNodeId = requestedNewNodeId;
        this.browseName = browseName;
        this.nodeClass = nodeClass;
        this.nodeAttributes = nodeAttributes;
        this.typeDefinition = typeDefinition;
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

    public ExpandedNodeId getParentNodeId() {
        return parentNodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public ExpandedNodeId getRequestedNewNodeId() {
        return requestedNewNodeId;
    }

    public QualifiedName getBrowseName() {
        return browseName;
    }

    public NodeClass getNodeClass() {
        return nodeClass;
    }

    public ExtensionObject getNodeAttributes() {
        return nodeAttributes;
    }

    public ExpandedNodeId getTypeDefinition() {
        return typeDefinition;
    }

    public static final class Codec extends GenericDataTypeCodec<AddNodesItem> {
        @Override
        public Class<AddNodesItem> getType() {
            return AddNodesItem.class;
        }

        @Override
        public AddNodesItem decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId parentNodeId = decoder.readExpandedNodeId("ParentNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            ExpandedNodeId requestedNewNodeId = decoder.readExpandedNodeId("RequestedNewNodeId");
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            ExtensionObject nodeAttributes = decoder.readExtensionObject("NodeAttributes");
            ExpandedNodeId typeDefinition = decoder.readExpandedNodeId("TypeDefinition");
            return new AddNodesItem(parentNodeId, referenceTypeId, requestedNewNodeId, browseName, nodeClass, nodeAttributes, typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AddNodesItem value) {
            encoder.writeExpandedNodeId("ParentNodeId", value.getParentNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeExpandedNodeId("RequestedNewNodeId", value.getRequestedNewNodeId());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeExtensionObject("NodeAttributes", value.getNodeAttributes());
            encoder.writeExpandedNodeId("TypeDefinition", value.getTypeDefinition());
        }
    }
}
