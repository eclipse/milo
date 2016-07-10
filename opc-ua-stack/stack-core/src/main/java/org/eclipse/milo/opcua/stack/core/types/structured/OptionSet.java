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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("OptionSet")
public class OptionSet implements UaStructure {

    public static final NodeId TypeId = Identifiers.OptionSet;
    public static final NodeId BinaryEncodingId = Identifiers.OptionSet_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OptionSet_Encoding_DefaultXml;

    protected final ByteString _value;
    protected final ByteString _validBits;

    public OptionSet() {
        this._value = null;
        this._validBits = null;
    }

    public OptionSet(ByteString _value, ByteString _validBits) {
        this._value = _value;
        this._validBits = _validBits;
    }

    public ByteString getValue() { return _value; }

    public ByteString getValidBits() { return _validBits; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(OptionSet optionSet, UaEncoder encoder) {
        encoder.encodeByteString("Value", optionSet._value);
        encoder.encodeByteString("ValidBits", optionSet._validBits);
    }

    public static OptionSet decode(UaDecoder decoder) {
        ByteString _value = decoder.decodeByteString("Value");
        ByteString _validBits = decoder.decodeByteString("ValidBits");

        return new OptionSet(_value, _validBits);
    }

    static {
        DelegateRegistry.registerEncoder(OptionSet::encode, OptionSet.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(OptionSet::decode, OptionSet.class, BinaryEncodingId, XmlEncodingId);
    }

}
