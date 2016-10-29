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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@UaDataType("VariableNode")
public class VariableNode extends InstanceNode {

    public static final NodeId TypeId = Identifiers.VariableNode;
    public static final NodeId BinaryEncodingId = Identifiers.VariableNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableNode_Encoding_DefaultXml;

    protected final Variant _value;
    protected final NodeId _dataType;
    protected final Integer _valueRank;
    protected final UInteger[] _arrayDimensions;
    protected final UByte _accessLevel;
    protected final UByte _userAccessLevel;
    protected final Double _minimumSamplingInterval;
    protected final Boolean _historizing;

    public VariableNode() {
        super(null, null, null, null, null, null, null, null);
        this._value = null;
        this._dataType = null;
        this._valueRank = null;
        this._arrayDimensions = null;
        this._accessLevel = null;
        this._userAccessLevel = null;
        this._minimumSamplingInterval = null;
        this._historizing = null;
    }

    public VariableNode(NodeId _nodeId, NodeClass _nodeClass, QualifiedName _browseName, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, ReferenceNode[] _references, Variant _value, NodeId _dataType, Integer _valueRank, UInteger[] _arrayDimensions, UByte _accessLevel, UByte _userAccessLevel, Double _minimumSamplingInterval, Boolean _historizing) {
        super(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references);
        this._value = _value;
        this._dataType = _dataType;
        this._valueRank = _valueRank;
        this._arrayDimensions = _arrayDimensions;
        this._accessLevel = _accessLevel;
        this._userAccessLevel = _userAccessLevel;
        this._minimumSamplingInterval = _minimumSamplingInterval;
        this._historizing = _historizing;
    }

    public Variant getValue() { return _value; }

    public NodeId getDataType() { return _dataType; }

    public Integer getValueRank() { return _valueRank; }

    public UInteger[] getArrayDimensions() { return _arrayDimensions; }

    public UByte getAccessLevel() { return _accessLevel; }

    public UByte getUserAccessLevel() { return _userAccessLevel; }

    public Double getMinimumSamplingInterval() { return _minimumSamplingInterval; }

    public Boolean getHistorizing() { return _historizing; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(VariableNode variableNode, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", variableNode._nodeId);
        encoder.encodeEnumeration("NodeClass", variableNode._nodeClass);
        encoder.encodeQualifiedName("BrowseName", variableNode._browseName);
        encoder.encodeLocalizedText("DisplayName", variableNode._displayName);
        encoder.encodeLocalizedText("Description", variableNode._description);
        encoder.encodeUInt32("WriteMask", variableNode._writeMask);
        encoder.encodeUInt32("UserWriteMask", variableNode._userWriteMask);
        encoder.encodeArray("References", variableNode._references, encoder::encodeSerializable);
        encoder.encodeVariant("Value", variableNode._value);
        encoder.encodeNodeId("DataType", variableNode._dataType);
        encoder.encodeInt32("ValueRank", variableNode._valueRank);
        encoder.encodeArray("ArrayDimensions", variableNode._arrayDimensions, encoder::encodeUInt32);
        encoder.encodeByte("AccessLevel", variableNode._accessLevel);
        encoder.encodeByte("UserAccessLevel", variableNode._userAccessLevel);
        encoder.encodeDouble("MinimumSamplingInterval", variableNode._minimumSamplingInterval);
        encoder.encodeBoolean("Historizing", variableNode._historizing);
    }

    public static VariableNode decode(UaDecoder decoder) {
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
        UByte _accessLevel = decoder.decodeByte("AccessLevel");
        UByte _userAccessLevel = decoder.decodeByte("UserAccessLevel");
        Double _minimumSamplingInterval = decoder.decodeDouble("MinimumSamplingInterval");
        Boolean _historizing = decoder.decodeBoolean("Historizing");

        return new VariableNode(_nodeId, _nodeClass, _browseName, _displayName, _description, _writeMask, _userWriteMask, _references, _value, _dataType, _valueRank, _arrayDimensions, _accessLevel, _userAccessLevel, _minimumSamplingInterval, _historizing);
    }

    static {
        DelegateRegistry.registerEncoder(VariableNode::encode, VariableNode.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(VariableNode::decode, VariableNode.class, BinaryEncodingId, XmlEncodingId);
    }

}
