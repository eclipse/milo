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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("ObjectNode")
public class ObjectNode extends InstanceNode {

    public static final NodeId TypeId = Identifiers.ObjectNode;
    public static final NodeId BinaryEncodingId = Identifiers.ObjectNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ObjectNode_Encoding_DefaultXml;

    protected final UByte _eventNotifier;

    public ObjectNode() {
        super(null, null, null, null, null, null, null, null);
        this._eventNotifier = null;
    }

    public ObjectNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, UByte _eventNotifier) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._eventNotifier = _eventNotifier;
    }

    public UByte getEventNotifier() { return _eventNotifier; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ObjectNode objectNode, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", objectNode._nodeId);
        encoder.encodeEnumeration("NodeClass", objectNode._nodeClass);
        encoder.encodeQualifiedName("BrowseName", objectNode._browseName);
        encoder.encodeLocalizedText("DisplayName", objectNode._displayName);
        encoder.encodeLocalizedText("Description", objectNode._description);
        encoder.encodeUInt32("WriteMask", objectNode._writeMask);
        encoder.encodeUInt32("UserWriteMask", objectNode._userWriteMask);
        encoder.encodeArray("References", objectNode._references, encoder::encodeSerializable);
        encoder.encodeByte("EventNotifier", objectNode._eventNotifier);
    }

    public static ObjectNode decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);
        UByte _eventNotifier = decoder.decodeByte("EventNotifier");

        return new ObjectNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _eventNotifier);
    }

    static {
        DelegateRegistry.registerEncoder(ObjectNode::encode, ObjectNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ObjectNode::decode, ObjectNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
