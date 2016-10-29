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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("NodeAttributes")
public class NodeAttributes implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.NodeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeAttributes_Encoding_DefaultXml;

    protected final UInteger _specifiedAttributes;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;
    protected final UInteger _writeMask;
    protected final UInteger _userWriteMask;

    public NodeAttributes() {
        this._specifiedAttributes = null;
        this._displayName = null;
        this._description = null;
        this._writeMask = null;
        this._userWriteMask = null;
    }

    public NodeAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask) {
        this._specifiedAttributes = _specifiedAttributes;
        this._displayName = _displayName;
        this._description = _description;
        this._writeMask = _writeMask;
        this._userWriteMask = _userWriteMask;
    }

    public UInteger getSpecifiedAttributes() { return _specifiedAttributes; }

    public LocalizedText getDisplayName() { return _displayName; }

    public LocalizedText getDescription() { return _description; }

    public UInteger getWriteMask() { return _writeMask; }

    public UInteger getUserWriteMask() { return _userWriteMask; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NodeAttributes nodeAttributes, UaEncoder encoder) {
        encoder.encodeUInt32("SpecifiedAttributes", nodeAttributes._specifiedAttributes);
        encoder.encodeLocalizedText("DisplayName", nodeAttributes._displayName);
        encoder.encodeLocalizedText("Description", nodeAttributes._description);
        encoder.encodeUInt32("WriteMask", nodeAttributes._writeMask);
        encoder.encodeUInt32("UserWriteMask", nodeAttributes._userWriteMask);
    }

    public static NodeAttributes decode(UaDecoder decoder) {
        UInteger _specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");
        UInteger _writeMask = decoder.decodeUInt32("WriteMask");
        UInteger _userWriteMask = decoder.decodeUInt32("UserWriteMask");

        return new NodeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
    }

    static {
        DelegateRegistry.registerEncoder(NodeAttributes::encode, NodeAttributes.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NodeAttributes::decode, NodeAttributes.class, BinaryEncodingId, XmlEncodingId);
    }

}
