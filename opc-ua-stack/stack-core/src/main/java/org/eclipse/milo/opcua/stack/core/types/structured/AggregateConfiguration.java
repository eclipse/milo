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


    public static void encode(AggregateConfiguration aggregateConfiguration, UaEncoder encoder) {
        encoder.encodeBoolean("UseServerCapabilitiesDefaults", aggregateConfiguration._useServerCapabilitiesDefaults);
        encoder.encodeBoolean("TreatUncertainAsBad", aggregateConfiguration._treatUncertainAsBad);
        encoder.encodeByte("PercentDataBad", aggregateConfiguration._percentDataBad);
        encoder.encodeByte("PercentDataGood", aggregateConfiguration._percentDataGood);
        encoder.encodeBoolean("UseSlopedExtrapolation", aggregateConfiguration._useSlopedExtrapolation);
    }

    public static AggregateConfiguration decode(UaDecoder decoder) {
        Boolean _useServerCapabilitiesDefaults = decoder.decodeBoolean("UseServerCapabilitiesDefaults");
        Boolean _treatUncertainAsBad = decoder.decodeBoolean("TreatUncertainAsBad");
        UByte _percentDataBad = decoder.decodeByte("PercentDataBad");
        UByte _percentDataGood = decoder.decodeByte("PercentDataGood");
        Boolean _useSlopedExtrapolation = decoder.decodeBoolean("UseSlopedExtrapolation");

        return new AggregateConfiguration(_useServerCapabilitiesDefaults, _treatUncertainAsBad, _percentDataBad, _percentDataGood, _useSlopedExtrapolation);
    }

    static {
        DelegateRegistry.registerEncoder(AggregateConfiguration::encode, AggregateConfiguration.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AggregateConfiguration::decode, AggregateConfiguration.class, BinaryEncodingId, XmlEncodingId);
    }

}
