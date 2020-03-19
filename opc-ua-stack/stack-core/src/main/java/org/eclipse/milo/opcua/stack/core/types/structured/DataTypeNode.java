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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DataTypeNode extends TypeNode implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=282");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=284");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=283");

    private final Boolean isAbstract;

    private final ExtensionObject dataTypeDefinition;

    public DataTypeNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
                        LocalizedText displayName, LocalizedText description, UInteger writeMask,
                        UInteger userWriteMask, ReferenceNode[] references, Boolean isAbstract,
                        ExtensionObject dataTypeDefinition) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.isAbstract = isAbstract;
        this.dataTypeDefinition = dataTypeDefinition;
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

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public ExtensionObject getDataTypeDefinition() {
        return dataTypeDefinition;
    }

    public static final class Codec extends GenericDataTypeCodec<DataTypeNode> {
        @Override
        public Class<DataTypeNode> getType() {
            return DataTypeNode.class;
        }

        @Override
        public DataTypeNode decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            ReferenceNode[] references = (ReferenceNode[]) decoder.readStructArray("References", ReferenceNode.TYPE_ID);
            Boolean isAbstract = decoder.readBoolean("IsAbstract");
            ExtensionObject dataTypeDefinition = decoder.readExtensionObject("DataTypeDefinition");
            return new DataTypeNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, isAbstract, dataTypeDefinition);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataTypeNode value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeStructArray("References", value.getReferences(), ReferenceNode.TYPE_ID);
            encoder.writeBoolean("IsAbstract", value.getIsAbstract());
            encoder.writeExtensionObject("DataTypeDefinition", value.getDataTypeDefinition());
        }
    }
}
