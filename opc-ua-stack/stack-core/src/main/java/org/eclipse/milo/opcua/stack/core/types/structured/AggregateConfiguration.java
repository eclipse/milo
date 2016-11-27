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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@UaDataType("AggregateConfiguration")
public class AggregateConfiguration implements UaStructure {

    public static final NodeId TypeId = Identifiers.AggregateConfiguration;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateConfiguration_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateConfiguration_Encoding_DefaultXml;

    protected final Boolean _useServerCapabilitiesDefaults;
    protected final Boolean _treatUncertainAsBad;
    protected final UByte _percentDataBad;
    protected final UByte _percentDataGood;
    protected final Boolean _useSlopedExtrapolation;

    public AggregateConfiguration() {
        this._useServerCapabilitiesDefaults = null;
        this._treatUncertainAsBad = null;
        this._percentDataBad = null;
        this._percentDataGood = null;
        this._useSlopedExtrapolation = null;
    }

    public AggregateConfiguration(Boolean _useServerCapabilitiesDefaults, Boolean _treatUncertainAsBad, UByte _percentDataBad, UByte _percentDataGood, Boolean _useSlopedExtrapolation) {
        this._useServerCapabilitiesDefaults = _useServerCapabilitiesDefaults;
        this._treatUncertainAsBad = _treatUncertainAsBad;
        this._percentDataBad = _percentDataBad;
        this._percentDataGood = _percentDataGood;
        this._useSlopedExtrapolation = _useSlopedExtrapolation;
    }

    public Boolean getUseServerCapabilitiesDefaults() { return _useServerCapabilitiesDefaults; }

    public Boolean getTreatUncertainAsBad() { return _treatUncertainAsBad; }

    public UByte getPercentDataBad() { return _percentDataBad; }

    public UByte getPercentDataGood() { return _percentDataGood; }

    public Boolean getUseSlopedExtrapolation() { return _useSlopedExtrapolation; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("UseServerCapabilitiesDefaults", _useServerCapabilitiesDefaults)
            .add("TreatUncertainAsBad", _treatUncertainAsBad)
            .add("PercentDataBad", _percentDataBad)
            .add("PercentDataGood", _percentDataGood)
            .add("UseSlopedExtrapolation", _useSlopedExtrapolation)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AggregateConfiguration> {
        @Override
        public AggregateConfiguration decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Boolean _useServerCapabilitiesDefaults = reader.readBoolean();
            Boolean _treatUncertainAsBad = reader.readBoolean();
            UByte _percentDataBad = reader.readByte();
            UByte _percentDataGood = reader.readByte();
            Boolean _useSlopedExtrapolation = reader.readBoolean();

            return new AggregateConfiguration(_useServerCapabilitiesDefaults, _treatUncertainAsBad, _percentDataBad, _percentDataGood, _useSlopedExtrapolation);
        }

        @Override
        public void encode(SerializationContext context, AggregateConfiguration encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeBoolean(encodable._useServerCapabilitiesDefaults);
            writer.writeBoolean(encodable._treatUncertainAsBad);
            writer.writeByte(encodable._percentDataBad);
            writer.writeByte(encodable._percentDataGood);
            writer.writeBoolean(encodable._useSlopedExtrapolation);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AggregateConfiguration> {
        @Override
        public AggregateConfiguration decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Boolean _useServerCapabilitiesDefaults = reader.readBoolean("UseServerCapabilitiesDefaults");
            Boolean _treatUncertainAsBad = reader.readBoolean("TreatUncertainAsBad");
            UByte _percentDataBad = reader.readByte("PercentDataBad");
            UByte _percentDataGood = reader.readByte("PercentDataGood");
            Boolean _useSlopedExtrapolation = reader.readBoolean("UseSlopedExtrapolation");

            return new AggregateConfiguration(_useServerCapabilitiesDefaults, _treatUncertainAsBad, _percentDataBad, _percentDataGood, _useSlopedExtrapolation);
        }

        @Override
        public void encode(SerializationContext context, AggregateConfiguration encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeBoolean("UseServerCapabilitiesDefaults", encodable._useServerCapabilitiesDefaults);
            writer.writeBoolean("TreatUncertainAsBad", encodable._treatUncertainAsBad);
            writer.writeByte("PercentDataBad", encodable._percentDataBad);
            writer.writeByte("PercentDataGood", encodable._percentDataGood);
            writer.writeBoolean("UseSlopedExtrapolation", encodable._useSlopedExtrapolation);
        }
    }

}
