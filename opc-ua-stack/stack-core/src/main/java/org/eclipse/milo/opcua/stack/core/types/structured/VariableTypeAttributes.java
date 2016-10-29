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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("VariableTypeAttributes")
public class VariableTypeAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.VariableTypeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.VariableTypeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.VariableTypeAttributes_Encoding_DefaultXml;

    protected final Variant _value;
    protected final NodeId _dataType;
    protected final Integer _valueRank;
    protected final UInteger[] _arrayDimensions;
    protected final Boolean _isAbstract;

    public VariableTypeAttributes() {
        super(null, null, null, null, null);
        this._value = null;
        this._dataType = null;
        this._valueRank = null;
        this._arrayDimensions = null;
        this._isAbstract = null;
    }

    public VariableTypeAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Variant _value, NodeId _dataType, Integer _valueRank, UInteger[] _arrayDimensions, Boolean _isAbstract) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
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


    public static void encode(VariableTypeAttributes variableTypeAttributes, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedAttributes", variableTypeAttributes._specifiedAttributes);
        encoder.encodeLocalizedText("DisplayName", variableTypeAttributes._displayName);
        encoder.encodeLocalizedText("Description", variableTypeAttributes._description);
        encoder.encodeUInt32("WriteMask", variableTypeAttributes._writeMask);
        encoder.encodeUInt32("UserWriteMask", variableTypeAttributes._userWriteMask);
        encoder.encodeVariant("Value", variableTypeAttributes._value);
        encoder.encodeNodeId("DataType", variableTypeAttributes._dataType);
        encoder.encodeInt32("ValueRank", variableTypeAttributes._valueRank);
        encoder.encodeArray("ArrayDimensions", variableTypeAttributes._arrayDimensions, encoder::encodeUInt32);
        encoder.encodeBoolean("IsAbstract", variableTypeAttributes._isAbstract);
    }

    public static VariableTypeAttributes decode(UaDecoder decoder) {
        UInteger _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        Variant _value = decoder.decodeVariant("Value");
        NodeId _dataType = decoder.decodeNodeId("DataType");
        Integer _valueRank = decoder.decodeInt32("ValueRank");
        UInteger[] _arrayDimensions = decoder.decodeArray("ArrayDimensions", decoder::decodeUInt32, UInteger.class);
        Boolean _isAbstract = decoder.decodeBoolean("IsAbstract");

        return new VariableTypeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _value, _dataType, _valueRank, _arrayDimensions, _isAbstract);
    }

    static {
        DelegateRegistry.registerEncoder(VariableTypeAttributes::encode, VariableTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(VariableTypeAttributes::decode, VariableTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
    }

}
