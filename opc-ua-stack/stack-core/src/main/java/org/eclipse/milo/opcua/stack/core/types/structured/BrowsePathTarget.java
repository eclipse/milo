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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("BrowsePathTarget")
public class BrowsePathTarget implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePathTarget;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePathTarget_Encoding_DefaultXml;

    protected final ExpandedNodeId _targetId;
    protected final UInteger _remainingPathIndex;

    public BrowsePathTarget() {
        this._targetId = null;
        this._remainingPathIndex = null;
    }

    public BrowsePathTarget(ExpandedNodeId _targetId, UInteger _remainingPathIndex) {
        this._targetId = _targetId;
        this._remainingPathIndex = _remainingPathIndex;
    }

    public ExpandedNodeId getTargetId() { return _targetId; }

    public UInteger getRemainingPathIndex() { return _remainingPathIndex; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(BrowsePathTarget browsePathTarget, UaEncoder encoder) {
        encoder.encodeExpandedNodeId("TargetId", browsePathTarget._targetId);
        encoder.encodeUInt32("RemainingPathIndex", browsePathTarget._remainingPathIndex);
    }

    public static BrowsePathTarget decode(UaDecoder decoder) {
        ExpandedNodeId _targetId = decoder.decodeExpandedNodeId("TargetId");
        UInteger _remainingPathIndex = decoder.decodeUInt32("RemainingPathIndex");

        return new BrowsePathTarget(_targetId, _remainingPathIndex);
    }

    static {
        DelegateRegistry.registerEncoder(BrowsePathTarget::encode, BrowsePathTarget.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BrowsePathTarget::decode, BrowsePathTarget.class, BinaryEncodingId, XmlEncodingId);
    }

}
