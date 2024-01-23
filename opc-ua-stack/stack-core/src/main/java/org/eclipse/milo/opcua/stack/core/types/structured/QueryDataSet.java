/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.28">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.28</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
public class QueryDataSet extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=577");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=579");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=578");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15202");

    private final ExpandedNodeId nodeId;

    private final ExpandedNodeId typeDefinitionNode;

    private final Variant @Nullable [] values;

    public QueryDataSet(ExpandedNodeId nodeId, ExpandedNodeId typeDefinitionNode,
                        Variant @Nullable [] values) {
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public ExpandedNodeId getNodeId() {
        return nodeId;
    }

    public ExpandedNodeId getTypeDefinitionNode() {
        return typeDefinitionNode;
    }

    public Variant @Nullable [] getValues() {
        return values;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", QueryDataSet.class.getSimpleName() + "[", "]");
        joiner.add("nodeId=" + getNodeId());
        joiner.add("typeDefinitionNode=" + getTypeDefinitionNode());
        joiner.add("values=" + java.util.Arrays.toString(getValues()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 579),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("TypeDefinitionNode", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("Values", LocalizedText.NULL_VALUE, new NodeId(0, 24), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<QueryDataSet> {
        @Override
        public Class<QueryDataSet> getType() {
            return QueryDataSet.class;
        }

        @Override
        public QueryDataSet decodeType(EncodingContext context, UaDecoder decoder) {
            ExpandedNodeId nodeId = decoder.decodeExpandedNodeId("NodeId");
            ExpandedNodeId typeDefinitionNode = decoder.decodeExpandedNodeId("TypeDefinitionNode");
            Variant[] values = decoder.decodeVariantArray("Values");
            return new QueryDataSet(nodeId, typeDefinitionNode, values);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, QueryDataSet value) {
            encoder.encodeExpandedNodeId("NodeId", value.getNodeId());
            encoder.encodeExpandedNodeId("TypeDefinitionNode", value.getTypeDefinitionNode());
            encoder.encodeVariantArray("Values", value.getValues());
        }
    }
}
