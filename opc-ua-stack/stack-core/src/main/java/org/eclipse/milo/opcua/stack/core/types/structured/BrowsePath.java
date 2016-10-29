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

@UaDataType("BrowsePath")
public class BrowsePath implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePath;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePath_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePath_Encoding_DefaultXml;

    protected final NodeId _startingNode;
    protected final RelativePath _relativePath;

    public BrowsePath() {
        this._startingNode = null;
        this._relativePath = null;
    }

    public BrowsePath(NodeId _startingNode, RelativePath _relativePath) {
        this._startingNode = _startingNode;
        this._relativePath = _relativePath;
    }

    public NodeId getStartingNode() { return _startingNode; }

    public RelativePath getRelativePath() { return _relativePath; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(BrowsePath browsePath, UaEncoder encoder) {
        encoder.encodeNodeId("StartingNode", browsePath._startingNode);
        encoder.encodeSerializable("RelativePath", browsePath._relativePath != null ? browsePath._relativePath : new RelativePath());
    }

    public static BrowsePath decode(UaDecoder decoder) {
        NodeId _startingNode = decoder.decodeNodeId("StartingNode");
        RelativePath _relativePath = decoder.decodeSerializable("RelativePath", RelativePath.class);

        return new BrowsePath(_startingNode, _relativePath);
    }

    static {
        DelegateRegistry.registerEncoder(BrowsePath::encode, BrowsePath.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BrowsePath::decode, BrowsePath.class, BinaryEncodingId, XmlEncodingId);
    }

}
