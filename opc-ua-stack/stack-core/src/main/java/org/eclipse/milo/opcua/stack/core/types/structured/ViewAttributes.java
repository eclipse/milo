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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ViewAttributes")
public class ViewAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ViewAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ViewAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ViewAttributes_Encoding_DefaultXml;

    protected final Boolean _containsNoLoops;
    protected final UByte _eventNotifier;

    public ViewAttributes() {
        super(null, null, null, null, null);
        this._containsNoLoops = null;
        this._eventNotifier = null;
    }

    public ViewAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Boolean _containsNoLoops, UByte _eventNotifier) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        this._containsNoLoops = _containsNoLoops;
        this._eventNotifier = _eventNotifier;
    }

    public Boolean getContainsNoLoops() { return _containsNoLoops; }

    public UByte getEventNotifier() { return _eventNotifier; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ViewAttributes viewAttributes, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedAttributes", viewAttributes._specifiedAttributes);
        encoder.encodeLocalizedText("DisplayName", viewAttributes._displayName);
        encoder.encodeLocalizedText("Description", viewAttributes._description);
        encoder.encodeUInt32("WriteMask", viewAttributes._writeMask);
        encoder.encodeUInt32("UserWriteMask", viewAttributes._userWriteMask);
        encoder.encodeBoolean("ContainsNoLoops", viewAttributes._containsNoLoops);
        encoder.encodeByte("EventNotifier", viewAttributes._eventNotifier);
    }

    public static ViewAttributes decode(UaDecoder decoder) {
        UInteger _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");
        Boolean _containsNoLoops = decoder.decodeBoolean("ContainsNoLoops");
        UByte _eventNotifier = decoder.decodeByte("EventNotifier");

        return new ViewAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _containsNoLoops, _eventNotifier);
    }

    static {
        DelegateRegistry.registerEncoder(ViewAttributes::encode, ViewAttributes.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ViewAttributes::decode, ViewAttributes.class, BinaryEncodingId, XmlEncodingId);
    }

}
