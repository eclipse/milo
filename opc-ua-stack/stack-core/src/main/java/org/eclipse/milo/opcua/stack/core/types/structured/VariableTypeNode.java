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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("VariableTypeNode")
public class VariableTypeNode extends TypeNode {

    public static final NodeId TypeId = Identifiers.VariableTypeNode;
    public static final NodeId BinaryEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableTypeNode_Encoding_DefaultXml;

    protected final Variant _value;
    protected final NodeId _dataType;
    protected final Integer _valueRank;
    protected final UInteger[] _arrayDimensions;
    protected final Boolean _isAbstract;

    public VariableTypeNode() {
        super(null, null, null, null, null, null, null, null);
        this._value = null;
        this._dataType = null;
        this._valueRank = null;
        this._arrayDimensions = null;
        this._isAbstract = null;
    }

    public VariableTypeNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Variant _value, NodeId _dataType, Integer _valueRank, UInteger[] _arrayDimensions, Boolean _isAbstract) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._value = _value;
        this._dataType = _dataType;
        this._valueRank = _valueRank;
        this._arrayDimensions = _arrayDimensions;
        this._isAbstract = _isAbstract;
    }

    public Variant getValue() { return _value; }

    public NodeId getDataType() { return _dataType; }

    public Integer getValueRank() { return _valueRank; }

    public UInteger[] getArrayDimensions() { return _arrayDimensions; }

    public Boolean getIsAbstract() { return _isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(VariableTypeNode variableTypeNode, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", variableTypeNode._nodeId);
        encoder.encodeEnumeration("NodeClass", variableTypeNode._nodeClass);
        encoder.encodeQualifiedName("BrowseName", variableTypeNode._browseName);
        encoder.encodeLocalizedText("DisplayName", variableTypeNode._displayName);
        encoder.encodeLocalizedText("Description", variableTypeNode._description);
        encoder.encodeUInt32("WriteMask", variableTypeNode._writeMask);
        encoder.encodeUInt32("UserWriteMask", variableTypeNode._userWriteMask);
        encoder.encodeArray("References", variableTypeNode._references, encoder::encodeSerializable);
        encoder.encodeVariant("Value", variableTypeNode._value);
        encoder.encodeNodeId("DataType", variableTypeNode._dataType);
        encoder.encodeInt32("ValueRank", variableTypeNode._valueRank);
        encoder.encodeArray("ArrayDimensions", variableTypeNode._arrayDimensions, encoder::encodeUInt32);
        encoder.encodeBoolean("IsAbstract", variableTypeNode._isAbstract);
    }

    public static VariableTypeNode decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        NodeClass _nodeClass = decoder.decodeEnumeration("NodeClass", NodeClass.class);
        QualifiedName _browseName = decoder.decodeQualifiedName("BrowseName");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        ReferenceNode[] _references = decoder.decodeArray("References", decoder::decodeSerializable, ReferenceNode.class);
        Variant _value = decoder.decodeVariant("Value");
        NodeId _dataType = decoder.decodeNodeId("DataType");
        Integer _valueRank = decoder.decodeInt32("ValueRank");
        UInteger[] _arrayDimensions = decoder.decodeArray("ArrayDimensions", decoder::decodeUInt32, UInteger.class);
        Boolean _isAbstract = decoder.decodeBoolean("IsAbstract");

        return new VariableTypeNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _value, _dataType, _valueRank, _arrayDimensions, _isAbstract);
    }

    static {
        DelegateRegistry.registerEncoder(VariableTypeNode::encode, VariableTypeNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(VariableTypeNode::decode, VariableTypeNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
