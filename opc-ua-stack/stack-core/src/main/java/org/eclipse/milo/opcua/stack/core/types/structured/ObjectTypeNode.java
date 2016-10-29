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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("ObjectTypeNode")
public class ObjectTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.ObjectTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.ObjectTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ObjectTypeNode_Encoding_DefaultXml;

    protected final Boolean _isAbstract;

    public ObjectTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this._isAbstract = null;
    }

    public ObjectTypeNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Boolean _isAbstract) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._isAbstract = _isAbstract;
    }

    public Boolean getIsAbstract() { return _isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ObjectTypeNode objectTypeNode, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", objectTypeNode._nodeId);
        encoder.encodeEnumeration("NodeClass", objectTypeNode._nodeClass);
        encoder.encodeQualifiedName("BrowseName", objectTypeNode._browseName);
        encoder.encodeLocalizedText("DisplayName", objectTypeNode._displayName);
        encoder.encodeLocalizedText("Description", objectTypeNode._description);
        encoder.encodeUInt32("WriteMask", objectTypeNode._writeMask);
        encoder.encodeUInt32("UserWriteMask", objectTypeNode._userWriteMask);
        encoder.encodeArray("References", objectTypeNode._references, encoder::encodeSerializable);
        encoder.encodeBoolean("IsAbstract", objectTypeNode._isAbstract);
    }

    public static ObjectTypeNode decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);
        Boolean _isAbstract = decoder.decodeBoolean("IsAbstract");

        return new ObjectTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _isAbstract);
    }

    static {
        DelegateRegistry.registerEncoder(ObjectTypeNode::encode, ObjectTypeNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ObjectTypeNode::decode, ObjectTypeNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
