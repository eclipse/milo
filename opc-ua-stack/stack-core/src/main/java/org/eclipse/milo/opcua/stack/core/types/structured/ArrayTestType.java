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

import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.EnumeratedTestType;

@UaDataType("ArrayTestType")
public class ArrayTestType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ArrayTestType;
    public static final NodeId BinaryEncodingId = Identifiers.ArrayTestType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ArrayTestType_Encoding_DefaultXml;

    protected final Boolean[] _booleans;
    protected final Byte[] _sBytes;
    protected final Short[] _int16s;
    protected final UShort[] _uInt16s;
    protected final Integer[] _int32s;
    protected final UInteger[] _uInt32s;
    protected final Long[] _int64s;
    protected final ULong[] _uInt64s;
    protected final Float[] _floats;
    protected final Double[] _doubles;
    protected final String[] _strings;
    protected final DateTime[] _dateTimes;
    protected final UUID[] _guids;
    protected final ByteString[] _byteStrings;
    protected final XmlElement[] _xmlElements;
    protected final NodeId[] _nodeIds;
    protected final ExpandedNodeId[] _expandedNodeIds;
    protected final StatusCode[] _statusCodes;
    protected final DiagnosticInfo[] _diagnosticInfos;
    protected final QualifiedName[] _qualifiedNames;
    protected final LocalizedText[] _localizedTexts;
    protected final ExtensionObject[] _extensionObjects;
    protected final DataValue[] _dataValues;
    protected final Variant[] _variants;
    protected final EnumeratedTestType[] _enumeratedValues;

    public ArrayTestType() {
        this._booleans = null;
        this._sBytes = null;
        this._int16s = null;
        this._uInt16s = null;
        this._int32s = null;
        this._uInt32s = null;
        this._int64s = null;
        this._uInt64s = null;
        this._floats = null;
        this._doubles = null;
        this._strings = null;
        this._dateTimes = null;
        this._guids = null;
        this._byteStrings = null;
        this._xmlElements = null;
        this._nodeIds = null;
        this._expandedNodeIds = null;
        this._statusCodes = null;
        this._diagnosticInfos = null;
        this._qualifiedNames = null;
        this._localizedTexts = null;
        this._extensionObjects = null;
        this._dataValues = null;
        this._variants = null;
        this._enumeratedValues = null;
    }

    public ArrayTestType(Boolean[] _booleans, Byte[] _sBytes, Short[] _int16s, UShort[] _uInt16s, Integer[] _int32s, UInteger[] _uInt32s, Long[] _int64s, ULong[] _uInt64s, Float[] _floats, Double[] _doubles, String[] _strings, DateTime[] _dateTimes, UUID[] _guids, ByteString[] _byteStrings, XmlElement[] _xmlElements, NodeId[] _nodeIds, ExpandedNodeId[] _expandedNodeIds, StatusCode[] _statusCodes, DiagnosticInfo[] _diagnosticInfos, QualifiedName[] _qualifiedNames, LocalizedText[] _localizedTexts, ExtensionObject[] _extensionObjects, DataValue[] _dataValues, Variant[] _variants, EnumeratedTestType[] _enumeratedValues) {
        this._booleans = _booleans;
        this._sBytes = _sBytes;
        this._int16s = _int16s;
        this._uInt16s = _uInt16s;
        this._int32s = _int32s;
        this._uInt32s = _uInt32s;
        this._int64s = _int64s;
        this._uInt64s = _uInt64s;
        this._floats = _floats;
        this._doubles = _doubles;
        this._strings = _strings;
        this._dateTimes = _dateTimes;
        this._guids = _guids;
        this._byteStrings = _byteStrings;
        this._xmlElements = _xmlElements;
        this._nodeIds = _nodeIds;
        this._expandedNodeIds = _expandedNodeIds;
        this._statusCodes = _statusCodes;
        this._diagnosticInfos = _diagnosticInfos;
        this._qualifiedNames = _qualifiedNames;
        this._localizedTexts = _localizedTexts;
        this._extensionObjects = _extensionObjects;
        this._dataValues = _dataValues;
        this._variants = _variants;
        this._enumeratedValues = _enumeratedValues;
    }

    public Boolean[] getBooleans() {
        return _booleans;
    }

    public Byte[] getSBytes() {
        return _sBytes;
    }

    public Short[] getInt16s() {
        return _int16s;
    }

    public UShort[] getUInt16s() {
        return _uInt16s;
    }

    public Integer[] getInt32s() {
        return _int32s;
    }

    public UInteger[] getUInt32s() {
        return _uInt32s;
    }

    public Long[] getInt64s() {
        return _int64s;
    }

    public ULong[] getUInt64s() {
        return _uInt64s;
    }

    public Float[] getFloats() {
        return _floats;
    }

    public Double[] getDoubles() {
        return _doubles;
    }

    public String[] getStrings() {
        return _strings;
    }

    public DateTime[] getDateTimes() {
        return _dateTimes;
    }

    public UUID[] getGuids() {
        return _guids;
    }

    public ByteString[] getByteStrings() {
        return _byteStrings;
    }

    public XmlElement[] getXmlElements() {
        return _xmlElements;
    }

    public NodeId[] getNodeIds() {
        return _nodeIds;
    }

    public ExpandedNodeId[] getExpandedNodeIds() {
        return _expandedNodeIds;
    }

    public StatusCode[] getStatusCodes() {
        return _statusCodes;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return _diagnosticInfos;
    }

    public QualifiedName[] getQualifiedNames() {
        return _qualifiedNames;
    }

    public LocalizedText[] getLocalizedTexts() {
        return _localizedTexts;
    }

    public ExtensionObject[] getExtensionObjects() {
        return _extensionObjects;
    }

    public DataValue[] getDataValues() {
        return _dataValues;
    }

    public Variant[] getVariants() {
        return _variants;
    }

    public EnumeratedTestType[] getEnumeratedValues() {
        return _enumeratedValues;
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


    public static void encode(ArrayTestType arrayTestType, UaEncoder encoder) {
        encoder.encodeArray("Booleans", arrayTestType._booleans, encoder::encodeBoolean);
        encoder.encodeArray("SBytes", arrayTestType._sBytes, encoder::encodeSByte);
        encoder.encodeArray("Int16s", arrayTestType._int16s, encoder::encodeInt16);
        encoder.encodeArray("UInt16s", arrayTestType._uInt16s, encoder::encodeUInt16);
        encoder.encodeArray("Int32s", arrayTestType._int32s, encoder::encodeInt32);
        encoder.encodeArray("UInt32s", arrayTestType._uInt32s, encoder::encodeUInt32);
        encoder.encodeArray("Int64s", arrayTestType._int64s, encoder::encodeInt64);
        encoder.encodeArray("UInt64s", arrayTestType._uInt64s, encoder::encodeUInt64);
        encoder.encodeArray("Floats", arrayTestType._floats, encoder::encodeFloat);
        encoder.encodeArray("Doubles", arrayTestType._doubles, encoder::encodeDouble);
        encoder.encodeArray("Strings", arrayTestType._strings, encoder::encodeString);
        encoder.encodeArray("DateTimes", arrayTestType._dateTimes, encoder::encodeDateTime);
        encoder.encodeArray("Guids", arrayTestType._guids, encoder::encodeGuid);
        encoder.encodeArray("ByteStrings", arrayTestType._byteStrings, encoder::encodeByteString);
        encoder.encodeArray("XmlElements", arrayTestType._xmlElements, encoder::encodeXmlElement);
        encoder.encodeArray("NodeIds", arrayTestType._nodeIds, encoder::encodeNodeId);
        encoder.encodeArray("ExpandedNodeIds", arrayTestType._expandedNodeIds, encoder::encodeExpandedNodeId);
        encoder.encodeArray("StatusCodes", arrayTestType._statusCodes, encoder::encodeStatusCode);
        encoder.encodeArray("DiagnosticInfos", arrayTestType._diagnosticInfos, encoder::encodeDiagnosticInfo);
        encoder.encodeArray("QualifiedNames", arrayTestType._qualifiedNames, encoder::encodeQualifiedName);
        encoder.encodeArray("LocalizedTexts", arrayTestType._localizedTexts, encoder::encodeLocalizedText);
        encoder.encodeArray("ExtensionObjects", arrayTestType._extensionObjects, encoder::encodeExtensionObject);
        encoder.encodeArray("DataValues", arrayTestType._dataValues, encoder::encodeDataValue);
        encoder.encodeArray("Variants", arrayTestType._variants, encoder::encodeVariant);
        encoder.encodeArray("EnumeratedValues", arrayTestType._enumeratedValues, encoder::encodeEnumeration);
    }

    public static ArrayTestType decode(UaDecoder decoder) {
        Boolean[] _booleans = decoder.decodeArray("Booleans", decoder::decodeBoolean, Boolean.class);
        Byte[] _sBytes = decoder.decodeArray("SBytes", decoder::decodeSByte, Byte.class);
        Short[] _int16s = decoder.decodeArray("Int16s", decoder::decodeInt16, Short.class);
        UShort[] _uInt16s = decoder.decodeArray("UInt16s", decoder::decodeUInt16, UShort.class);
        Integer[] _int32s = decoder.decodeArray("Int32s", decoder::decodeInt32, Integer.class);
        UInteger[] _uInt32s = decoder.decodeArray("UInt32s", decoder::decodeUInt32, UInteger.class);
        Long[] _int64s = decoder.decodeArray("Int64s", decoder::decodeInt64, Long.class);
        ULong[] _uInt64s = decoder.decodeArray("UInt64s", decoder::decodeUInt64, ULong.class);
        Float[] _floats = decoder.decodeArray("Floats", decoder::decodeFloat, Float.class);
        Double[] _doubles = decoder.decodeArray("Doubles", decoder::decodeDouble, Double.class);
        String[] _strings = decoder.decodeArray("Strings", decoder::decodeString, String.class);
        DateTime[] _dateTimes = decoder.decodeArray("DateTimes", decoder::decodeDateTime, DateTime.class);
        UUID[] _guids = decoder.decodeArray("Guids", decoder::decodeGuid, UUID.class);
        ByteString[] _byteStrings = decoder.decodeArray("ByteStrings", decoder::decodeByteString, ByteString.class);
        XmlElement[] _xmlElements = decoder.decodeArray("XmlElements", decoder::decodeXmlElement, XmlElement.class);
        NodeId[] _nodeIds = decoder.decodeArray("NodeIds", decoder::decodeNodeId, NodeId.class);
        ExpandedNodeId[] _expandedNodeIds = decoder.decodeArray("ExpandedNodeIds", decoder::decodeExpandedNodeId, ExpandedNodeId.class);
        StatusCode[] _statusCodes = decoder.decodeArray("StatusCodes", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);
        QualifiedName[] _qualifiedNames = decoder.decodeArray("QualifiedNames", decoder::decodeQualifiedName, QualifiedName.class);
        LocalizedText[] _localizedTexts = decoder.decodeArray("LocalizedTexts", decoder::decodeLocalizedText, LocalizedText.class);
        ExtensionObject[] _extensionObjects = decoder.decodeArray("ExtensionObjects", decoder::decodeExtensionObject, ExtensionObject.class);
        DataValue[] _dataValues = decoder.decodeArray("DataValues", decoder::decodeDataValue, DataValue.class);
        Variant[] _variants = decoder.decodeArray("Variants", decoder::decodeVariant, Variant.class);
        EnumeratedTestType[] _enumeratedValues = decoder.decodeArray("EnumeratedValues", decoder::decodeEnumeration, EnumeratedTestType.class);

        return new ArrayTestType(_booleans, _sBytes, _int16s, _uInt16s, _int32s, _uInt32s, _int64s, _uInt64s, _floats, _doubles, _strings, _dateTimes, _guids, _byteStrings, _xmlElements, _nodeIds, _expandedNodeIds, _statusCodes, _diagnosticInfos, _qualifiedNames, _localizedTexts, _extensionObjects, _dataValues, _variants, _enumeratedValues);
    }

    static {
        DelegateRegistry.registerEncoder(ArrayTestType::encode, ArrayTestType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ArrayTestType::decode, ArrayTestType.class, BinaryEncodingId, XmlEncodingId);
    }

}
