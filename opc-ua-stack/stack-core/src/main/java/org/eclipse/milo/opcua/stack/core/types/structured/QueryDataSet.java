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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class QueryDataSet implements UaStructure {

    public static final NodeId TypeId = Identifiers.QueryDataSet;
    public static final NodeId BinaryEncodingId = Identifiers.QueryDataSet_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryDataSet_Encoding_DefaultXml;

    protected final ExpandedNodeId nodeId;
    protected final ExpandedNodeId typeDefinitionNode;
    protected final Variant[] values;

    public QueryDataSet() {
        this.nodeId = null;
        this.typeDefinitionNode = null;
        this.values = null;
    }

    public QueryDataSet(ExpandedNodeId nodeId, ExpandedNodeId typeDefinitionNode, Variant[] values) {
        this.nodeId = nodeId;
        this.typeDefinitionNode = typeDefinitionNode;
        this.values = values;
    }

    public ExpandedNodeId getNodeId() { return nodeId; }

    public ExpandedNodeId getTypeDefinitionNode() { return typeDefinitionNode; }

    @Nullable
    public Variant[] getValues() { return values; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("TypeDefinitionNode", typeDefinitionNode)
            .add("Values", values)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryDataSet> {

        @Override
        public Class<QueryDataSet> getType() {
            return QueryDataSet.class;
        }

        @Override
        public QueryDataSet decode(UaDecoder decoder) throws UaSerializationException {
            ExpandedNodeId nodeId = decoder.readExpandedNodeId("NodeId");
            ExpandedNodeId typeDefinitionNode = decoder.readExpandedNodeId("TypeDefinitionNode");
            Variant[] values = decoder.readArray("Values", decoder::readVariant, Variant.class);

            return new QueryDataSet(nodeId, typeDefinitionNode, values);
        }

        @Override
        public void encode(QueryDataSet value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeExpandedNodeId("NodeId", value.nodeId);
            encoder.writeExpandedNodeId("TypeDefinitionNode", value.typeDefinitionNode);
            encoder.writeArray("Values", value.values, encoder::writeVariant);
        }
    }

}
