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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Nullable
    public Double[] getAxisSteps() { return _axisSteps; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EngineeringUnits", _engineeringUnits)
            .add("EURange", _eURange)
            .add("Title", _title)
            .add("AxisScaleType", _axisScaleType)
            .add("AxisSteps", _axisSteps)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AxisInformation> {
        @Override
        public AxisInformation decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            EUInformation _engineeringUnits = (EUInformation) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EUInformation", reader);
            Range _eURange = (Range) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "Range", reader);
            LocalizedText _title = reader.readLocalizedText();
            AxisScaleEnumeration _axisScaleType = AxisScaleEnumeration.from(reader.readInt32());
            Double[] _axisSteps = reader.readArray(reader::readDouble, Double.class);

            return new AxisInformation(_engineeringUnits, _eURange, _title, _axisScaleType, _axisSteps);
        }

        @Override
        public void encode(SerializationContext context, AxisInformation encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EUInformation", encodable._engineeringUnits, writer);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "Range", encodable._eURange, writer);
            writer.writeLocalizedText(encodable._title);
            writer.writeInt32(encodable._axisScaleType != null ? encodable._axisScaleType.getValue() : 0);
            writer.writeArray(encodable._axisSteps, writer::writeDouble);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AxisInformation> {
        @Override
        public AxisInformation decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            EUInformation _engineeringUnits = (EUInformation) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EUInformation", reader);
            Range _eURange = (Range) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "Range", reader);
            LocalizedText _title = reader.readLocalizedText("Title");
            AxisScaleEnumeration _axisScaleType = AxisScaleEnumeration.from(reader.readInt32("AxisScaleType"));
            Double[] _axisSteps = reader.readArray("AxisSteps", reader::readDouble, Double.class);

            return new AxisInformation(_engineeringUnits, _eURange, _title, _axisScaleType, _axisSteps);
        }

        @Override
        public void encode(SerializationContext context, AxisInformation encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EUInformation", encodable._engineeringUnits, writer);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "Range", encodable._eURange, writer);
            writer.writeLocalizedText("Title", encodable._title);
            writer.writeInt32("AxisScaleType", encodable._axisScaleType != null ? encodable._axisScaleType.getValue() : 0);
            writer.writeArray("AxisSteps", encodable._axisSteps, writer::writeDouble);
        }
    }

}
