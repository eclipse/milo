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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MethodAttributes")
public class MethodAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.MethodAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.MethodAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MethodAttributes_Encoding_DefaultXml;

    protected final Boolean _executable;
    protected final Boolean _userExecutable;

    public MethodAttributes() {
        super(null, null, null, null, null);
        this._executable = null;
        this._userExecutable = null;
    }

    public MethodAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Boolean _executable, Boolean _userExecutable) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
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


    public static void encode(MethodAttributes methodAttributes, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedAttributes", methodAttributes._specifiedAttributes);
        encoder.encodeLocalizedText("DisplayName", methodAttributes._displayName);
        encoder.encodeLocalizedText("Description", methodAttributes._description);
        encoder.encodeUInt32("WriteMask", methodAttributes._writeMask);
        encoder.encodeUInt32("UserWriteMask", methodAttributes._userWriteMask);
        encoder.encodeBoolean("Executable", methodAttributes._executable);
        encoder.encodeBoolean("UserExecutable", methodAttributes._userExecutable);
    }

    public static MethodAttributes decode(UaDecoder decoder) {
        UInteger _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        Boolean _executable = decoder.decodeBoolean("Executable");
        Boolean _userExecutable = decoder.decodeBoolean("UserExecutable");

        return new MethodAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _executable, _userExecutable);
    }

    static {
        DelegateRegistry.registerEncoder(MethodAttributes::encode, MethodAttributes.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MethodAttributes::decode, MethodAttributes.class, BinaryEncodingId, XmlEncodingId);
    }

}
