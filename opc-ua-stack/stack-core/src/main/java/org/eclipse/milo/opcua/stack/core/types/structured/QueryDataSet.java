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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class QueryDataSet extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=577");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=579");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=578");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15202");

    private final ExpandedNodeId nodeId;

    private final ExpandedNodeId typeDefinitionNode;

    private final Variant[] values;

    public QueryDataSet(ExpandedNodeId nodeId, ExpandedNodeId typeDefinitionNode, Variant[] values) {
        this.nodeId = nodeId;
        this.typeDefinitionNode = typeDefinitionNode;
        this.values = values;
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

    public ExpandedNodeId getNodeId() {
        return nodeId;
    }

    public ExpandedNodeId getTypeDefinitionNode() {
        return typeDefinitionNode;
    }

    public Variant[] getValues() {
        return values;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryDataSet> {
        @Override
        public Class<QueryDataSet> getType() {
            return QueryDataSet.class;
        }

        @Override
        public QueryDataSet decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId nodeId = decoder.readExpandedNodeId("NodeId");
            ExpandedNodeId typeDefinitionNode = decoder.readExpandedNodeId("TypeDefinitionNode");
            Variant[] values = decoder.readVariantArray("Values");
            return new QueryDataSet(nodeId, typeDefinitionNode, values);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryDataSet value) {
            encoder.writeExpandedNodeId("NodeId", value.getNodeId());
            encoder.writeExpandedNodeId("TypeDefinitionNode", value.getTypeDefinitionNode());
            encoder.writeVariantArray("Values", value.getValues());
        }
    }
}
