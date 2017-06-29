/*
 * Copyright (c) 2017 Kevin Herron
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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class XVType implements UaStructure {

    public static final NodeId TypeId = Identifiers.XVType;
    public static final NodeId BinaryEncodingId = Identifiers.XVType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.XVType_Encoding_DefaultXml;

    protected final Double x;
    protected final Float value;

    public XVType() {
        this.x = null;
        this.value = null;
    }

    public XVType(Double x, Float value) {
        this.x = x;
        this.value = value;
    }

    public Double getX() { return x; }

    public Float getValue() { return value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("X", x)
            .add("Value", value)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<XVType> {

        @Override
        public Class<XVType> getType() {
            return XVType.class;
        }

        @Override
        public XVType decode(UaDecoder decoder) throws UaSerializationException {
            Double x = decoder.readDouble("X");
            Float value = decoder.readFloat("Value");

            return new XVType(x, value);
        }

        @Override
        public void encode(XVType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDouble("X", value.x);
            encoder.writeFloat("Value", value.value);
        }
    }

}
