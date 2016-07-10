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

@UaDataType("ReferenceNode")
public class ReferenceNode implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReferenceNode;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceNode_Encoding_DefaultXml;

    protected final NodeId _referenceTypeId;
    protected final Boolean _isInverse;
    protected final ExpandedNodeId _targetId;

    public ReferenceNode() {
        this._referenceTypeId = null;
        this._isInverse = null;
        this._targetId = null;
    }

    public ReferenceNode(NodeId _referenceTypeId, Boolean _isInverse, ExpandedNodeId _targetId) {
        this._referenceTypeId = _referenceTypeId;
        this._isInverse = _isInverse;
        this._targetId = _targetId;
    }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsInverse() { return _isInverse; }

    public ExpandedNodeId getTargetId() { return _targetId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ReferenceNode referenceNode, UaEncoder encoder) {
        encoder.encodeNodeId("ReferenceTypeId", referenceNode._referenceTypeId);
        encoder.encodeBoolean("IsInverse", referenceNode._isInverse);
        encoder.encodeExpandedNodeId("TargetId", referenceNode._targetId);
    }

    public static ReferenceNode decode(UaDecoder decoder) {
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        Boolean _isInverse = decoder.decodeBoolean("IsInverse");
        ExpandedNodeId _targetId = decoder.decodeExpandedNodeId("TargetId");

        return new ReferenceNode(_referenceTypeId, _isInverse, _targetId);
    }

    static {
        DelegateRegistry.registerEncoder(ReferenceNode::encode, ReferenceNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ReferenceNode::decode, ReferenceNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
