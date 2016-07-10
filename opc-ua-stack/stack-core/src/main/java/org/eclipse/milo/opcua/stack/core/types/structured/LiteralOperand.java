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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("LiteralOperand")
public class LiteralOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.LiteralOperand;
    public static final NodeId BinaryEncodingId = Identifiers.LiteralOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.LiteralOperand_Encoding_DefaultXml;

    protected final Variant _value;

    public LiteralOperand() {
        super();
        this._value = null;
    }

    public LiteralOperand(Variant _value) {
        super();
        this._value = _value;
    }

    public Variant getValue() { return _value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(LiteralOperand literalOperand, UaEncoder encoder) {
        encoder.encodeVariant("Value", literalOperand._value);
    }

    public static LiteralOperand decode(UaDecoder decoder) {
        Variant _value = decoder.decodeVariant("Value");

        return new LiteralOperand(_value);
    }

    static {
        DelegateRegistry.registerEncoder(LiteralOperand::encode, LiteralOperand.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(LiteralOperand::decode, LiteralOperand.class, BinaryEncodingId, XmlEncodingId);
    }

}
