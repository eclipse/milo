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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReferenceDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=518");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=520");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=519");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15182");

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final ExpandedNodeId nodeId;

    private final QualifiedName browseName;

    private final LocalizedText displayName;

    private final NodeClass nodeClass;

    private final ExpandedNodeId typeDefinition;

    public ReferenceDescription(NodeId referenceTypeId, Boolean isForward, ExpandedNodeId nodeId,
                                QualifiedName browseName, LocalizedText displayName, NodeClass nodeClass,
                                ExpandedNodeId typeDefinition) {
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.nodeId = nodeId;
        this.browseName = browseName;
        this.displayName = displayName;
        this.nodeClass = nodeClass;
        this.typeDefinition = typeDefinition;
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

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsForward() {
        return isForward;
    }

    public ExpandedNodeId getNodeId() {
        return nodeId;
    }

    public QualifiedName getBrowseName() {
        return browseName;
    }

    public LocalizedText getDisplayName() {
        return displayName;
    }

    public NodeClass getNodeClass() {
        return nodeClass;
    }

    public ExpandedNodeId getTypeDefinition() {
        return typeDefinition;
    }

    public static final class Codec extends GenericDataTypeCodec<ReferenceDescription> {
        @Override
        public Class<ReferenceDescription> getType() {
            return ReferenceDescription.class;
        }

        @Override
        public ReferenceDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            ExpandedNodeId nodeId = decoder.readExpandedNodeId("NodeId");
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            ExpandedNodeId typeDefinition = decoder.readExpandedNodeId("TypeDefinition");
            return new ReferenceDescription(referenceTypeId, isForward, nodeId, browseName, displayName, nodeClass, typeDefinition);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ReferenceDescription value) {
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeExpandedNodeId("NodeId", value.getNodeId());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeExpandedNodeId("TypeDefinition", value.getTypeDefinition());
        }
    }
}
