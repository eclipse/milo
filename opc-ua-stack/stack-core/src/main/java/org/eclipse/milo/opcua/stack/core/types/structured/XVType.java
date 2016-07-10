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

@UaDataType("XVType")
public class XVType implements UaStructure {

    public static final NodeId TypeId = Identifiers.XVType;
    public static final NodeId BinaryEncodingId = Identifiers.XVType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.XVType_Encoding_DefaultXml;

    protected final Double _x;
    protected final Float _value;

    public XVType() {
        this._x = null;
        this._value = null;
    }

    public XVType(Double _x, Float _value) {
        this._x = _x;
        this._value = _value;
    }

    public Double getX() { return _x; }

    public Float getValue() { return _value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(XVType xVType, UaEncoder encoder) {
        encoder.encodeDouble("X", xVType._x);
        encoder.encodeFloat("Value", xVType._value);
    }

    public static XVType decode(UaDecoder decoder) {
        Double _x = decoder.decodeDouble("X");
        Float _value = decoder.decodeFloat("Value");

        return new XVType(_x, _value);
    }

    static {
        DelegateRegistry.registerEncoder(XVType::encode, XVType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(XVType::decode, XVType.class, BinaryEncodingId, XmlEncodingId);
    }

}
