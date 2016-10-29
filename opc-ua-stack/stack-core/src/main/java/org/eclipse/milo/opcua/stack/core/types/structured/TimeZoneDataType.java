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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("TimeZoneDataType")
public class TimeZoneDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.TimeZoneDataType;
    public static final NodeId BinaryEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultXml;

    protected final Short _offset;
    protected final Boolean _daylightSavingInOffset;

    public TimeZoneDataType() {
        this._offset = null;
        this._daylightSavingInOffset = null;
    }

    public TimeZoneDataType(Short _offset, Boolean _daylightSavingInOffset) {
        this._offset = _offset;
        this._daylightSavingInOffset = _daylightSavingInOffset;
    }

    public Short getOffset() { return _offset; }

    public Boolean getDaylightSavingInOffset() { return _daylightSavingInOffset; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TimeZoneDataType timeZoneDataType, UaEncoder encoder) {
        encoder.encodeInt16("Offset", timeZoneDataType._offset);
        encoder.encodeBoolean("DaylightSavingInOffset", timeZoneDataType._daylightSavingInOffset);
    }

    public static TimeZoneDataType decode(UaDecoder decoder) {
        Short _offset = decoder.decodeInt16("Offset");
        Boolean _daylightSavingInOffset = decoder.decodeBoolean("DaylightSavingInOffset");

        return new TimeZoneDataType(_offset, _daylightSavingInOffset);
    }

    static {
        DelegateRegistry.registerEncoder(TimeZoneDataType::encode, TimeZoneDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TimeZoneDataType::decode, TimeZoneDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
