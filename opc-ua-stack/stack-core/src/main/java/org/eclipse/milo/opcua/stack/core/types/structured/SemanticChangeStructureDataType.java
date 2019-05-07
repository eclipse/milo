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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SemanticChangeStructureDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=897");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=898");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=899");

    private final NodeId affected;

    private final NodeId affectedType;

    public SemanticChangeStructureDataType(NodeId affected, NodeId affectedType) {
        this.affected = affected;
        this.affectedType = affectedType;
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

    public NodeId getAffected() {
        return affected;
    }

    public NodeId getAffectedType() {
        return affectedType;
    }

    public static final class Codec extends GenericDataTypeCodec<SemanticChangeStructureDataType> {
        @Override
        public Class<SemanticChangeStructureDataType> getType() {
            return SemanticChangeStructureDataType.class;
        }

        @Override
        public SemanticChangeStructureDataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId affected = decoder.readNodeId("Affected");
            NodeId affectedType = decoder.readNodeId("AffectedType");
            return new SemanticChangeStructureDataType(affected, affectedType);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SemanticChangeStructureDataType value) {
            encoder.writeNodeId("Affected", value.getAffected());
            encoder.writeNodeId("AffectedType", value.getAffectedType());
        }
    }
}
