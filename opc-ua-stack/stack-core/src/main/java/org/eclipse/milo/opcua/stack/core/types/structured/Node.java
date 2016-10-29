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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("Node")
public class Node implements UaStructure {

    public static final NodeId TypeId = Identifiers.Node;
    public static final NodeId BinaryEncodingId = Identifiers.Node_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Node_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final NodeClass _nodeClass;
    protected final QualifiedName _browseName;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;
    protected final UInteger _writeMask;
    protected final UInteger _userWriteMask;
    protected final ReferenceNode[] _references;

    public Node() {
        this._nodeId = null;
        this._nodeClass = null;
        this._browseName = null;
        this._displayName = null;
        this._description = null;
        this._writeMask = null;
        this._userWriteMask = null;
        this._references = null;
    }

    public Node(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references) {
        this._nodeId = _nodeId;
        this._nodeClass = _nodeClass;
        this._browseName = _browseName;
        this._displayName = _displayName;
        this._description = _description;
        this._writeMask = _writeMask;
        this._userWriteMask = _userWriteMask;
        this._references = _references;
    }

    public NodeId getNodeId() { return _nodeId; }

    public NodeClass getNodeClass() { return _nodeClass; }

    public QualifiedName getBrowseName() { return _browseName; }

    public LocalizedText getDisplayName() { return _displayName; }

    public LocalizedText getDescription() { return _description; }

    public UInteger getWriteMask() { return _writeMask; }

    public UInteger getUserWriteMask() { return _userWriteMask; }

    public ReferenceNode[] getReferences() { return _references; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(Node node, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", node._nodeId);
        encoder.encodeEnumeration("NodeClass", node._nodeClass);
        encoder.encodeQualifiedName("BrowseName", node._browseName);
        encoder.encodeLocalizedText("DisplayName", node._displayName);
        encoder.encodeLocalizedText("Description", node._description);
        encoder.encodeUInt32("WriteMask", node._writeMask);
        encoder.encodeUInt32("UserWriteMask", node._userWriteMask);
        encoder.encodeArray("References", node._references, encoder::encodeSerializable);
    }

    public static Node decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);

        return new Node(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
    }

    static {
        DelegateRegistry.registerEncoder(Node::encode, Node.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(Node::decode, Node.class, BinaryEncodingId, XmlEncodingId);
    }

}
