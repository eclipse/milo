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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AttributeOperand extends FilterOperand implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=598");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=599");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=600");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15209");

    private final NodeId nodeId;

    private final String alias;

    private final RelativePath browsePath;

    private final UInteger attributeId;

    private final String indexRange;

    public AttributeOperand(NodeId nodeId, String alias, RelativePath browsePath,
                            UInteger attributeId, String indexRange) {
        this.nodeId = nodeId;
        this.alias = alias;
        this.browsePath = browsePath;
        this.attributeId = attributeId;
        this.indexRange = indexRange;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public String getAlias() {
        return alias;
    }

    public RelativePath getBrowsePath() {
        return browsePath;
    }

    public UInteger getAttributeId() {
        return attributeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public static final class Codec extends GenericDataTypeCodec<AttributeOperand> {
        @Override
        public Class<AttributeOperand> getType() {
            return AttributeOperand.class;
        }

        @Override
        public AttributeOperand decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            String alias = decoder.readString("Alias");
            RelativePath browsePath = (RelativePath) decoder.readStruct("BrowsePath", RelativePath.TYPE_ID);
            UInteger attributeId = decoder.readUInt32("AttributeId");
            String indexRange = decoder.readString("IndexRange");
            return new AttributeOperand(nodeId, alias, browsePath, attributeId, indexRange);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AttributeOperand value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeString("Alias", value.getAlias());
            encoder.writeStruct("BrowsePath", value.getBrowsePath(), RelativePath.TYPE_ID);
            encoder.writeUInt32("AttributeId", value.getAttributeId());
            encoder.writeString("IndexRange", value.getIndexRange());
        }
    }
}
