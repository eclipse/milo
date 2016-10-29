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

@UaDataType("CompositeTestType")
public class CompositeTestType implements UaStructure {

    public static final NodeId TypeId = Identifiers.CompositeTestType;
    public static final NodeId BinaryEncodingId = Identifiers.CompositeTestType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CompositeTestType_Encoding_DefaultXml;

    protected final ScalarTestType _field1;
    protected final ArrayTestType _field2;

    public CompositeTestType() {
        this._field1 = null;
        this._field2 = null;
    }

    public CompositeTestType(ScalarTestType _field1, ArrayTestType _field2) {
        this._field1 = _field1;
        this._field2 = _field2;
    }

    public ScalarTestType getField1() {
        return _field1;
    }

    public ArrayTestType getField2() {
        return _field2;
    }

    @Override
    public NodeId getTypeId() {
        return TypeId;
    }

    @Override
    public NodeId getBinaryEncodingId() {
        return BinaryEncodingId;
    }

    @Override
    public NodeId getXmlEncodingId() {
        return XmlEncodingId;
    }


    public static void encode(CompositeTestType compositeTestType, UaEncoder encoder) {
        encoder.encodeSerializable("Field1", compositeTestType._field1 != null ? compositeTestType._field1 : new ScalarTestType());
        encoder.encodeSerializable("Field2", compositeTestType._field2 != null ? compositeTestType._field2 : new ArrayTestType());
    }

    public static CompositeTestType decode(UaDecoder decoder) {
        ScalarTestType _field1 = decoder.decodeSerializable("Field1", ScalarTestType.class);
        ArrayTestType _field2 = decoder.decodeSerializable("Field2", ArrayTestType.class);

        return new CompositeTestType(_field1, _field2);
    }

    static {
        DelegateRegistry.registerEncoder(CompositeTestType::encode, CompositeTestType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CompositeTestType::decode, CompositeTestType.class, BinaryEncodingId, XmlEncodingId);
    }

}
