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

@UaDataType("EnumValueType")
public class EnumValueType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EnumValueType;
    public static final NodeId BinaryEncodingId = Identifiers.EnumValueType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EnumValueType_Encoding_DefaultXml;

    protected final Long _value;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;

    public EnumValueType() {
        this._value = null;
        this._displayName = null;
        this._description = null;
    }

    public EnumValueType(Long _value, LocalizedText _displayName, LocalizedText _description) {
        this._value = _value;
        this._displayName = _displayName;
        this._description = _description;
    }

    public Long getValue() { return _value; }

    public LocalizedText getDisplayName() { return _displayName; }

    public LocalizedText getDescription() { return _description; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EnumValueType enumValueType, UaEncoder encoder) {
        encoder.encodeInt64("Value", enumValueType._value);
        encoder.encodeLocalizedText("DisplayName", enumValueType._displayName);
        encoder.encodeLocalizedText("Description", enumValueType._description);
    }

    public static EnumValueType decode(UaDecoder decoder) {
        Long _value = decoder.decodeInt64("Value");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");

        return new EnumValueType(_value, _displayName, _description);
    }

    static {
        DelegateRegistry.registerEncoder(EnumValueType::encode, EnumValueType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EnumValueType::decode, EnumValueType.class, BinaryEncodingId, XmlEncodingId);
    }

}
