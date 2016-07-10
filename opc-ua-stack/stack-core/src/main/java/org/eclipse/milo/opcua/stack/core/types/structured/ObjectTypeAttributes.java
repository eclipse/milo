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

@UaDataType("ObjectTypeAttributes")
public class ObjectTypeAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ObjectTypeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ObjectTypeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ObjectTypeAttributes_Encoding_DefaultXml;

    protected final Boolean _isAbstract;

    public ObjectTypeAttributes() {
        super(null, null, null, null, null);
        this._isAbstract = null;
    }

    public ObjectTypeAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Boolean _isAbstract) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        this._isAbstract = _isAbstract;
    }

    public Boolean getIsAbstract() { return _isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ObjectTypeAttributes objectTypeAttributes, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedAttributes", objectTypeAttributes._specifiedAttributes);
        encoder.encodeLocalizedText("DisplayName", objectTypeAttributes._displayName);
        encoder.encodeLocalizedText("Description", objectTypeAttributes._description);
        encoder.encodeUInt32("WriteMask", objectTypeAttributes._writeMask);
        encoder.encodeUInt32("UserWriteMask", objectTypeAttributes._userWriteMask);
        encoder.encodeBoolean("IsAbstract", objectTypeAttributes._isAbstract);
    }

    public static ObjectTypeAttributes decode(UaDecoder decoder) {
        UInteger _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        Boolean _isAbstract = decoder.decodeBoolean("IsAbstract");

        return new ObjectTypeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _isAbstract);
    }

    static {
        DelegateRegistry.registerEncoder(ObjectTypeAttributes::encode, ObjectTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ObjectTypeAttributes::decode, ObjectTypeAttributes.class, BinaryEncodingId, XmlEncodingId);
    }

}
