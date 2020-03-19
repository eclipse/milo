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
public class ObjectNode extends InstanceNode implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=261");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=263");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=262");

    private final UByte eventNotifier;

    public ObjectNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                      UInteger userWriteMask, ReferenceNode[] references, UByte eventNotifier) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.eventNotifier = eventNotifier;
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

    public UByte getEventNotifier() {
        return eventNotifier;
    }

    public static final class Codec extends GenericDataTypeCodec<ObjectNode> {
        @Override
        public Class<ObjectNode> getType() {
            return ObjectNode.class;
        }

        @Override
        public ObjectNode decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            ReferenceNode[] references = (ReferenceNode[]) decoder.readStructArray("References", ReferenceNode.TYPE_ID);
            UByte eventNotifier = decoder.readByte("EventNotifier");
            return new ObjectNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, eventNotifier);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ObjectNode value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeStructArray("References", value.getReferences(), ReferenceNode.TYPE_ID);
            encoder.writeByte("EventNotifier", value.getEventNotifier());
        }
    }
}
