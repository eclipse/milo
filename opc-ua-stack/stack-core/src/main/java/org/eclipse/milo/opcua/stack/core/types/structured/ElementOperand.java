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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ElementOperand")
public class ElementOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.ElementOperand;
    public static final NodeId BinaryEncodingId = Identifiers.ElementOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ElementOperand_Encoding_DefaultXml;

    protected final UInteger _index;

    public ElementOperand() {
        super();
        this._index = null;
    }

    public ElementOperand(UInteger _index) {
        super();
        this._index = _index;
    }

    public UInteger getIndex() { return _index; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ElementOperand elementOperand, UaEncoder encoder) {
        encoder.encodeUInt32("Index", elementOperand._index);
    }

    public static ElementOperand decode(UaDecoder decoder) {
        UInteger _index = decoder.decodeUInt32("Index");

        return new ElementOperand(_index);
    }

    static {
        DelegateRegistry.registerEncoder(ElementOperand::encode, ElementOperand.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ElementOperand::decode, ElementOperand.class, BinaryEncodingId, XmlEncodingId);
    }

}
