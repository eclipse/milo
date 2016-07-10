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

@UaDataType("EUInformation")
public class EUInformation implements UaStructure {

    public static final NodeId TypeId = Identifiers.EUInformation;
    public static final NodeId BinaryEncodingId = Identifiers.EUInformation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EUInformation_Encoding_DefaultXml;

    protected final String _namespaceUri;
    protected final Integer _unitId;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;

    public EUInformation() {
        this._namespaceUri = null;
        this._unitId = null;
        this._displayName = null;
        this._description = null;
    }

    public EUInformation(String _namespaceUri, Integer _unitId, LocalizedText _displayName, LocalizedText _description) {
        this._namespaceUri = _namespaceUri;
        this._unitId = _unitId;
        this._displayName = _displayName;
        this._description = _description;
    }

    public String getNamespaceUri() { return _namespaceUri; }

    public Integer getUnitId() { return _unitId; }

    public LocalizedText getDisplayName() { return _displayName; }

    public LocalizedText getDescription() { return _description; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EUInformation eUInformation, UaEncoder encoder) {
        encoder.encodeString("NamespaceUri", eUInformation._namespaceUri);
        encoder.encodeInt32("UnitId", eUInformation._unitId);
        encoder.encodeLocalizedText("DisplayName", eUInformation._displayName);
        encoder.encodeLocalizedText("Description", eUInformation._description);
    }

    public static EUInformation decode(UaDecoder decoder) {
        String _namespaceUri = decoder.decodeString("NamespaceUri");
        Integer _unitId = decoder.decodeInt32("UnitId");
        LocalizedText _displayName = decoder.decodeLocalizedText("DisplayName");
        LocalizedText _description = decoder.decodeLocalizedText("Description");

        return new EUInformation(_namespaceUri, _unitId, _displayName, _description);
    }

    static {
        DelegateRegistry.registerEncoder(EUInformation::encode, EUInformation.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EUInformation::decode, EUInformation.class, BinaryEncodingId, XmlEncodingId);
    }

}
