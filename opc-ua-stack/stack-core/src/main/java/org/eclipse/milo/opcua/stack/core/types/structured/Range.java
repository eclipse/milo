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

@UaDataType("Range")
public class Range implements UaStructure {

    public static final NodeId TypeId = Identifiers.Range;
    public static final NodeId BinaryEncodingId = Identifiers.Range_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Range_Encoding_DefaultXml;

    protected final Double _low;
    protected final Double _high;

    public Range() {
        this._low = null;
        this._high = null;
    }

    public Range(Double _low, Double _high) {
        this._low = _low;
        this._high = _high;
    }

    public Double getLow() { return _low; }

    public Double getHigh() { return _high; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(Range range, UaEncoder encoder) {
        encoder.encodeDouble("Low", range._low);
        encoder.encodeDouble("High", range._high);
    }

    public static Range decode(UaDecoder decoder) {
        Double _low = decoder.decodeDouble("Low");
        Double _high = decoder.decodeDouble("High");

        return new Range(_low, _high);
    }

    static {
        DelegateRegistry.registerEncoder(Range::encode, Range.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(Range::decode, Range.class, BinaryEncodingId, XmlEncodingId);
    }

}
