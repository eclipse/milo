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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public Variant[] getValues() { return _values; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(QueryDataSet queryDataSet, UaEncoder encoder) {
        encoder.encodeExpandedNodeId("NodeId", queryDataSet._nodeId);
        encoder.encodeExpandedNodeId("TypeDefinitionNode", queryDataSet._typeDefinitionNode);
        encoder.encodeArray("Values", queryDataSet._values, encoder::encodeVariant);
    }

    public static QueryDataSet decode(UaDecoder decoder) {
        ExpandedNodeId _nodeId = decoder.decodeExpandedNodeId("NodeId");
        ExpandedNodeId _typeDefinitionNode = decoder.decodeExpandedNodeId("TypeDefinitionNode");
        Variant[] _values = decoder.decodeArray("Values", decoder::decodeVariant, Variant.class);

        return new QueryDataSet(_nodeId, _typeDefinitionNode, _values);
    }

    static {
        DelegateRegistry.registerEncoder(QueryDataSet::encode, QueryDataSet.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(QueryDataSet::decode, QueryDataSet.class, BinaryEncodingId, XmlEncodingId);
    }

}
