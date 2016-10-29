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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;

@UaDataType("BrowseDescription")
public class BrowseDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowseDescription;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseDescription_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final BrowseDirection _browseDirection;
    protected final NodeId _referenceTypeId;
    protected final Boolean _includeSubtypes;
    protected final UInteger _nodeClassMask;
    protected final UInteger _resultMask;

    public BrowseDescription() {
        this._nodeId = null;
        this._browseDirection = null;
        this._referenceTypeId = null;
        this._includeSubtypes = null;
        this._nodeClassMask = null;
        this._resultMask = null;
    }

    public BrowseDescription(NodeId _nodeId, BrowseDirection _browseDirection, NodeId _referenceTypeId, Boolean _includeSubtypes, UInteger _nodeClassMask, UInteger _resultMask) {
        this._nodeId = _nodeId;
        this._browseDirection = _browseDirection;
        this._referenceTypeId = _referenceTypeId;
        this._includeSubtypes = _includeSubtypes;
        this._nodeClassMask = _nodeClassMask;
        this._resultMask = _resultMask;
    }

    public NodeId getNodeId() { return _nodeId; }

    public BrowseDirection getBrowseDirection() { return _browseDirection; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIncludeSubtypes() { return _includeSubtypes; }

    public UInteger getNodeClassMask() { return _nodeClassMask; }

    public UInteger getResultMask() { return _resultMask; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(BrowseDescription browseDescription, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", browseDescription._nodeId);
        encoder.encodeEnumeration("BrowseDirection", browseDescription._browseDirection);
        encoder.encodeNodeId("ReferenceTypeId", browseDescription._referenceTypeId);
        encoder.encodeBoolean("IncludeSubtypes", browseDescription._includeSubtypes);
        encoder.encodeUInt32("NodeClassMask", browseDescription._nodeClassMask);
        encoder.encodeUInt32("ResultMask", browseDescription._resultMask);
    }

    public static BrowseDescription decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        BrowseDirection _browseDirection = decoder.decodeEnumeration("BrowseDirection", BrowseDirection.class);
        NodeId _referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
        Boolean _includeSubtypes = decoder.decodeBoolean("IncludeSubtypes");
        UInteger _nodeClassMask = decoder.decodeUInt32("NodeClassMask");
        UInteger _resultMask = decoder.decodeUInt32("ResultMask");

        return new BrowseDescription(_nodeId, _browseDirection, _referenceTypeId, _includeSubtypes, _nodeClassMask, _resultMask);
    }

    static {
        DelegateRegistry.registerEncoder(BrowseDescription::encode, BrowseDescription.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BrowseDescription::decode, BrowseDescription.class, BinaryEncodingId, XmlEncodingId);
    }

}
