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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class BrowseDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=514");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=516");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=515");

    private final NodeId nodeId;

    private final BrowseDirection browseDirection;

    private final NodeId referenceTypeId;

    private final Boolean includeSubtypes;

    private final UInteger nodeClassMask;

    private final UInteger resultMask;

    public BrowseDescription(NodeId nodeId, BrowseDirection browseDirection, NodeId referenceTypeId,
                             Boolean includeSubtypes, UInteger nodeClassMask, UInteger resultMask) {
        this.nodeId = nodeId;
        this.browseDirection = browseDirection;
        this.referenceTypeId = referenceTypeId;
        this.includeSubtypes = includeSubtypes;
        this.nodeClassMask = nodeClassMask;
        this.resultMask = resultMask;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public BrowseDirection getBrowseDirection() {
        return browseDirection;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIncludeSubtypes() {
        return includeSubtypes;
    }

    public UInteger getNodeClassMask() {
        return nodeClassMask;
    }

    public UInteger getResultMask() {
        return resultMask;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseDescription> {
        @Override
        public Class<BrowseDescription> getType() {
            return BrowseDescription.class;
        }

        @Override
        public BrowseDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            BrowseDirection browseDirection = decoder.readEnum("BrowseDirection", BrowseDirection.class);
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean includeSubtypes = decoder.readBoolean("IncludeSubtypes");
            UInteger nodeClassMask = decoder.readUInt32("NodeClassMask");
            UInteger resultMask = decoder.readUInt32("ResultMask");
            return new BrowseDescription(nodeId, browseDirection, referenceTypeId, includeSubtypes, nodeClassMask, resultMask);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseDescription value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("BrowseDirection", value.getBrowseDirection());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IncludeSubtypes", value.getIncludeSubtypes());
            encoder.writeUInt32("NodeClassMask", value.getNodeClassMask());
            encoder.writeUInt32("ResultMask", value.getResultMask());
        }
    }
}
