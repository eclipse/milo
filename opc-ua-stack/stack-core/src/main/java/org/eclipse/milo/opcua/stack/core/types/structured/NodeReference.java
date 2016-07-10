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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("NodeReference")
public class NodeReference implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeReference;
    public static final NodeId BinaryEncodingId = Identifiers.NodeReference_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeReference_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final NodeId _referenceTypeId;
    protected final Boolean _isForward;
    protected final NodeId[] _referencedNodeIds;

    public NodeReference() {
        this._nodeId = null;
        this._referenceTypeId = null;
        this._isForward = null;
        this._referencedNodeIds = null;
    }

    public NodeReference(NodeId _nodeId, NodeId _referenceTypeId, Boolean _isForward, NodeId[] _referencedNodeIds) {
        this._nodeId = _nodeId;
        this._referenceTypeId = _referenceTypeId;
        this._isForward = _isForward;
        this._referencedNodeIds = _referencedNodeIds;
    }

    public NodeId getNodeId() { return _nodeId; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsForward() { return _isForward; }

    public NodeId[] getReferencedNodeIds() { return _referencedNodeIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NodeReference nodeReference, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", nodeReference._nodeId);
        encoder.encodeNodeId("ReferenceTypeId", nodeReference._referenceTypeId);
        encoder.encodeBoolean("IsForward", nodeReference._isForward);
        encoder.encodeArray("ReferencedNodeIds", nodeReference._referencedNodeIds, encoder::encodeNodeId);
    }

    public static NodeReference decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        Boolean _isForward = decoder.decodeBoolean("IsForward");
        NodeId[] _referencedNodeIds = decoder.decodeArray("ReferencedNodeIds", decoder::decodeNodeId, NodeId.class);

        return new NodeReference(_nodeId, _referenceTypeId, _isForward, _referencedNodeIds);
    }

    static {
        DelegateRegistry.registerEncoder(NodeReference::encode, NodeReference.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NodeReference::decode, NodeReference.class, BinaryEncodingId, XmlEncodingId);
    }

}
