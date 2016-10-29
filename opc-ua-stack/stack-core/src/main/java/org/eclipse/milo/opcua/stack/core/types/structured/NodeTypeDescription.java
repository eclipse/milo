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

@UaDataType("NodeTypeDescription")
public class NodeTypeDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeTypeDescription;
    public static final NodeId BinaryEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultXml;

    protected final ExpandedNodeId _typeDefinitionNode;
    protected final Boolean _includeSubTypes;
    protected final QueryDataDescription[] _dataToReturn;

    public NodeTypeDescription() {
        this._typeDefinitionNode = null;
        this._includeSubTypes = null;
        this._dataToReturn = null;
    }

    public NodeTypeDescription(ExpandedNodeId _typeDefinitionNode, Boolean _includeSubTypes, QueryDataDescription[] _dataToReturn) {
        this._typeDefinitionNode = _typeDefinitionNode;
        this._includeSubTypes = _includeSubTypes;
        this._dataToReturn = _dataToReturn;
    }

    public ExpandedNodeId getTypeDefinitionNode() { return _typeDefinitionNode; }

    public Boolean getIncludeSubTypes() { return _includeSubTypes; }

    public QueryDataDescription[] getDataToReturn() { return _dataToReturn; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NodeTypeDescription nodeTypeDescription, UaEncoder encoder) {
        encoder.encodeExpandedNodeId("TypeDefinitionNode", nodeTypeDescription._typeDefinitionNode);
        encoder.encodeBoolean("IncludeSubTypes", nodeTypeDescription._includeSubTypes);
        encoder.encodeArray("DataToReturn", nodeTypeDescription._dataToReturn, encoder::encodeSerializable);
    }

    public static NodeTypeDescription decode(UaDecoder decoder) {
        ExpandedNodeId _typeDefinitionNode = decoder.decodeExpandedNodeId("TypeDefinitionNode");
        Boolean _includeSubTypes = decoder.decodeBoolean("IncludeSubTypes");
        QueryDataDescription[] _dataToReturn = decoder.decodeArray("DataToReturn", decoder::decodeSerializable, QueryDataDescription.class);

        return new NodeTypeDescription(_typeDefinitionNode, _includeSubTypes, _dataToReturn);
    }

    static {
        DelegateRegistry.registerEncoder(NodeTypeDescription::encode, NodeTypeDescription.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NodeTypeDescription::decode, NodeTypeDescription.class, BinaryEncodingId, XmlEncodingId);
    }

}
