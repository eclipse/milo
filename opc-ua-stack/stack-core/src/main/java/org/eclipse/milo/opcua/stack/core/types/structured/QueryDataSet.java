/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("QueryDataSet")
public class QueryDataSet implements UaStructure {

    public static final NodeId TypeId = Identifiers.QueryDataSet;
    public static final NodeId BinaryEncodingId = Identifiers.QueryDataSet_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryDataSet_Encoding_DefaultXml;

    protected final ExpandedNodeId _nodeId;
    protected final ExpandedNodeId _typeDefinitionNode;
    protected final Variant[] _values;

    public QueryDataSet() {
        this._nodeId = null;
        this._typeDefinitionNode = null;
        this._values = null;
    }

    public QueryDataSet(ExpandedNodeId _nodeId, ExpandedNodeId _typeDefinitionNode, Variant[] _values) {
        this._nodeId = _nodeId;
        this._typeDefinitionNode = _typeDefinitionNode;
        this._values = _values;
    }

    public ExpandedNodeId getNodeId() { return _nodeId; }

    public ExpandedNodeId getTypeDefinitionNode() { return _typeDefinitionNode; }

    @Nullable
    public Variant[] getValues() { return _values; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("TypeDefinitionNode", _typeDefinitionNode)
            .add("Values", _values)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<QueryDataSet> {
        @Override
        public QueryDataSet decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _nodeId = reader.readExpandedNodeId();
            ExpandedNodeId _typeDefinitionNode = reader.readExpandedNodeId();
            Variant[] _values = reader.readArray(reader::readVariant, Variant.class);

            return new QueryDataSet(_nodeId, _typeDefinitionNode, _values);
        }

        @Override
        public void encode(SerializationContext context, QueryDataSet encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId(encodable._nodeId);
            writer.writeExpandedNodeId(encodable._typeDefinitionNode);
            writer.writeArray(encodable._values, writer::writeVariant);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<QueryDataSet> {
        @Override
        public QueryDataSet decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _nodeId = reader.readExpandedNodeId("NodeId");
            ExpandedNodeId _typeDefinitionNode = reader.readExpandedNodeId("TypeDefinitionNode");
            Variant[] _values = reader.readArray("Values", reader::readVariant, Variant.class);

            return new QueryDataSet(_nodeId, _typeDefinitionNode, _values);
        }

        @Override
        public void encode(SerializationContext context, QueryDataSet encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId("NodeId", encodable._nodeId);
            writer.writeExpandedNodeId("TypeDefinitionNode", encodable._typeDefinitionNode);
            writer.writeArray("Values", encodable._values, writer::writeVariant);
        }
    }

}
