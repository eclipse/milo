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

@UaDataType("MethodNode")
public class MethodNode extends InstanceNode {

    public static final NodeId TypeId = Identifiers.MethodNode;
    public static final NodeId BinaryEncodingId = Identifiers.MethodNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MethodNode_Encoding_DefaultXml;

    protected final Boolean _executable;
    protected final Boolean _userExecutable;

    public MethodNode() {
        super(null, null, null, null, null, null, null, null);
        this._executable = null;
        this._userExecutable = null;
    }

    public MethodNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Boolean _executable, Boolean _userExecutable) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._executable = _executable;
        this._userExecutable = _userExecutable;
    }

    public Boolean getExecutable() { return _executable; }

    public Boolean getUserExecutable() { return _userExecutable; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MethodNode methodNode, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", methodNode._nodeId);
        encoder.encodeEnumeration("NodeClass", methodNode._nodeClass);
        encoder.encodeQualifiedName("BrowseName", methodNode._browseName);
        encoder.encodeLocalizedText("DisplayName", methodNode._displayName);
        encoder.encodeLocalizedText("Description", methodNode._description);
        encoder.encodeUInt32("WriteMask", methodNode._writeMask);
        encoder.encodeUInt32("UserWriteMask", methodNode._userWriteMask);
        encoder.encodeArray("References", methodNode._references, encoder::encodeSerializable);
        encoder.encodeBoolean("Executable", methodNode._executable);
        encoder.encodeBoolean("UserExecutable", methodNode._userExecutable);
    }

    public static MethodNode decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);
        Boolean _executable = decoder.decodeBoolean("Executable");
        Boolean _userExecutable = decoder.decodeBoolean("UserExecutable");

        return new MethodNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _executable, _userExecutable);
    }

    static {
        DelegateRegistry.registerEncoder(MethodNode::encode, MethodNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MethodNode::decode, MethodNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
