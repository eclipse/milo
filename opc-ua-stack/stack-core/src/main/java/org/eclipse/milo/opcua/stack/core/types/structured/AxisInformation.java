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
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;

@UaDataType("AxisInformation")
public class AxisInformation implements UaStructure {

    public static final NodeId TypeId = Identifiers.AxisInformation;
    public static final NodeId BinaryEncodingId = Identifiers.AxisInformation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AxisInformation_Encoding_DefaultXml;

    protected final EUInformation _engineeringUnits;
    protected final Range _eURange;
    protected final LocalizedText _title;
    protected final AxisScaleEnumeration _axisScaleType;
    protected final Double[] _axisSteps;

    public AxisInformation() {
        this._engineeringUnits = null;
        this._eURange = null;
        this._title = null;
        this._axisScaleType = null;
        this._axisSteps = null;
    }

    public AxisInformation(EUInformation _engineeringUnits, Range _eURange, LocalizedText _title, AxisScaleEnumeration _axisScaleType, Double[] _axisSteps) {
        this._engineeringUnits = _engineeringUnits;
        this._eURange = _eURange;
        this._title = _title;
        this._axisScaleType = _axisScaleType;
        this._axisSteps = _axisSteps;
    }

    public EUInformation getEngineeringUnits() { return _engineeringUnits; }

    public Range getEURange() { return _eURange; }

    public LocalizedText getTitle() { return _title; }

    public AxisScaleEnumeration getAxisScaleType() { return _axisScaleType; }

    public Double[] getAxisSteps() { return _axisSteps; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AxisInformation axisInformation, UaEncoder encoder) {
        encoder.encodeSerializable("EngineeringUnits", axisInformation._engineeringUnits != null ? axisInformation._engineeringUnits : new EUInformation());
        encoder.encodeSerializable("EURange", axisInformation._eURange != null ? axisInformation._eURange : new Range());
        encoder.encodeLocalizedText("Title", axisInformation._title);
        encoder.encodeEnumeration("AxisScaleType", axisInformation._axisScaleType);
        encoder.encodeArray("AxisSteps", axisInformation._axisSteps, encoder::encodeDouble);
    }

    public static AxisInformation decode(UaDecoder decoder) {
        EUInformation _engineeringUnits = decoder.decodeSerializable("EngineeringUnits", EUInformation.class);
        Range _eURange = decoder.decodeSerializable("EURange", Range.class);
        LocalizedText _title = decoder.decodeLocalizedText("Title");
        AxisScaleEnumeration _axisScaleType = decoder.decodeEnumeration("AxisScaleType", AxisScaleEnumeration.class);
        Double[] _axisSteps = decoder.decodeArray("AxisSteps", decoder::decodeDouble, Double.class);

        return new AxisInformation(_engineeringUnits, _eURange, _title, _axisScaleType, _axisSteps);
    }

    static {
        DelegateRegistry.registerEncoder(AxisInformation::encode, AxisInformation.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AxisInformation::decode, AxisInformation.class, BinaryEncodingId, XmlEncodingId);
    }

}
