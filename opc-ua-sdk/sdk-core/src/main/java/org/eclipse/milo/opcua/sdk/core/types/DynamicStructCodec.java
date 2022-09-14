/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaEnumeratedType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.jetbrains.annotations.NotNull;

public class DynamicStructCodec extends GenericDataTypeCodec<DynamicStruct> {

    private final Map<StructureField, Object> fieldHints = new ConcurrentHashMap<>();

    private final Map<NodeId, Function<Integer, DynamicEnum>> enumFactories = new ConcurrentHashMap<>();

    private final DataType dataType;
    private final StructureDefinition structureDefinition;

    public DynamicStructCodec(DataTypeTree dataTypeTree, DataType dataType) {
        this.dataType = dataType;
        this.structureDefinition = (StructureDefinition) dataType.getDataTypeDefinition();

        assert structureDefinition != null;

        for (StructureField field : structureDefinition.getFields()) {
            NodeId dataTypeId = field.getDataType();

            Object hint;
            if (BuiltinDataType.isBuiltin(dataTypeId)) {
                hint = BuiltinDataType.fromNodeId(dataTypeId);
            } else if (dataTypeTree.isEnumType(dataTypeId)) {
                hint = CodecType.ENUM;

                DataType enumDataType = dataTypeTree.getDataType(dataTypeId);
                enumFactories.put(dataTypeId, DynamicEnum.newInstanceFactory(enumDataType));
            } else if (dataTypeTree.isStructType(dataTypeId)) {
                hint = CodecType.STRUCT;
            } else {
                hint = dataTypeTree.getBuiltinType(dataTypeId);
            }
            fieldHints.put(field, hint);
        }
    }

    @Override
    public Class<DynamicStruct> getType() {
        return DynamicStruct.class;
    }

    @Override
    public DynamicStruct decodeType(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        switch (structureDefinition.getStructureType()) {
            case Structure:
            case StructureWithOptionalFields:
            case StructureWithSubtypedValues:
                return decodeStruct(context, decoder);
            case Union:
            case UnionWithSubtypedValues:
                return decodeUnion(decoder);
            default:
                throw new IllegalArgumentException(
                    "unhandled StructureType: " + structureDefinition.getStructureType());
        }
    }


    @Override
    public void encodeType(
        SerializationContext context,
        UaEncoder encoder,
        DynamicStruct value
    ) throws UaSerializationException {

        switch (structureDefinition.getStructureType()) {
            case Structure:
            case StructureWithOptionalFields:
            case StructureWithSubtypedValues:
                encodeStruct(encoder, value);
                break;
            case Union:
            case UnionWithSubtypedValues:
                encodeUnion(encoder, value);
                break;
            default:
                throw new IllegalArgumentException(
                    "unhandled StructureType: " + structureDefinition.getStructureType());
        }
    }

    private @NotNull DynamicStruct decodeStruct(SerializationContext context, UaDecoder decoder) {
        StructureField[] fields = structureDefinition.getFields();

        LinkedHashMap<String, Object> members = new LinkedHashMap<>();

        long switchField = 0xFFFFFFFFL;
        if (structureDefinition.getStructureType() == StructureType.StructureWithOptionalFields) {
            switchField = decoder.readUInt32("SwitchField").longValue();
        }

        for (int i = 0; i < fields.length; i++) {
            StructureField field = fields[i];

            if (!field.getIsOptional() || ((switchField >>> i) & 1) == 1) {
                String fieldName = field.getName();
                NodeId dataTypeId = field.getDataType();

                // Note: shall be scalar or fixed dimension
                Integer valueRank = field.getValueRank();
                if (valueRank == -1) {
                    Object value;

                    Object hint = fieldHints.get(field);
                    if (hint instanceof BuiltinDataType) {
                        value = decodeBuiltinDataType(decoder, fieldName, (BuiltinDataType) hint);
                    } else {
                        CodecType codecType = (CodecType) hint;

                        switch (codecType) {
                            case ENUM: {
                                Function<Integer, DynamicEnum> factory = enumFactories.get(dataTypeId);
                                Integer enumValue = decoder.readEnum(fieldName);
                                value = factory.apply(enumValue);
                                break;
                            }
                            case STRUCT:
                                value = decoder.readStruct(fieldName, dataTypeId);
                                break;
                            default:
                                throw new RuntimeException("codecType: " + codecType);
                        }
                    }

                    members.put(fieldName, value);
                } else if (valueRank == 1) {
                    Object value;

                    Object hint = fieldHints.get(field);
                    if (hint instanceof BuiltinDataType) {
                        value = decodeBuiltinDataTypeArray(decoder, fieldName, (BuiltinDataType) hint);
                    } else {
                        CodecType codecType = (CodecType) hint;

                        switch (codecType) {
                            case ENUM: {
                                Function<Integer, DynamicEnum> factory = enumFactories.get(dataTypeId);
                                Integer[] enumValues = decoder.readEnumArray(fieldName);
                                value = Arrays.stream(enumValues).map(factory).toArray(DynamicEnum[]::new);
                                break;
                            }
                            case STRUCT:
                                value = decoder.readStructArray(fieldName, dataTypeId);
                                break;
                            default:
                                throw new RuntimeException("codecType: " + codecType);
                        }
                    }

                    members.put(fieldName, value);
                } else if (valueRank > 1) {
                    // TODO special matrix encoding for multi-dimensional array structure fields
                    throw new RuntimeException("not implemented");
                } else {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError, "illegal ValueRank: " + valueRank);
                }
            }
        }

        return new DynamicStruct(dataType, members);
    }

    private @NotNull DynamicUnion decodeUnion(UaDecoder decoder) {
        int switchField = decoder.readUInt32("SwitchField").intValue();

        StructureField[] fields = structureDefinition.getFields();

        if (switchField == 0) {
            return DynamicUnion.ofNull(dataType);
        } else if (switchField <= fields.length) {
            StructureField field = fields[switchField - 1];
            String fieldName = field.getName();
            NodeId dataTypeId = field.getDataType();

            // Note: shall be scalar or fixed dimension
            Integer valueRank = field.getValueRank();
            if (valueRank == -1) {
                Object value;

                Object hint = fieldHints.get(field);
                if (hint instanceof BuiltinDataType) {
                    value = decodeBuiltinDataType(decoder, fieldName, (BuiltinDataType) hint);
                } else {
                    CodecType codecType = (CodecType) hint;

                    switch (codecType) {
                        case ENUM: {
                            Function<Integer, DynamicEnum> factory = enumFactories.get(dataTypeId);
                            Integer enumValue = decoder.readEnum(fieldName);
                            value = factory.apply(enumValue);
                            break;
                        }
                        case STRUCT:
                            value = decoder.readStruct(fieldName, dataTypeId);
                            break;
                        default:
                            throw new RuntimeException("codecType: " + codecType);
                    }
                }

                return DynamicUnion.of(dataType, fieldName, value);
            } else if (valueRank == 1) {
                Object value;

                Object hint = fieldHints.get(field);
                if (hint instanceof BuiltinDataType) {
                    value = decodeBuiltinDataTypeArray(decoder, fieldName, (BuiltinDataType) hint);
                } else {
                    CodecType codecType = (CodecType) hint;

                    switch (codecType) {
                        case ENUM: {
                            Function<Integer, DynamicEnum> factory = enumFactories.get(dataTypeId);
                            Integer[] enumValues = decoder.readEnumArray(fieldName);
                            value = Arrays.stream(enumValues).map(factory).toArray(DynamicEnum[]::new);
                            break;
                        }
                        case STRUCT:
                            value = decoder.readStructArray(fieldName, dataTypeId);
                            break;
                        default:
                            throw new RuntimeException("codecType: " + codecType);
                    }
                }

                return DynamicUnion.of(dataType, fieldName, value);
            } else if (valueRank > 1) {
                // TODO special matrix encoding for multi-dimensional array structure fields
                throw new RuntimeException("not implemented");
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError, "illegal ValueRank: " + valueRank);
            }
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "invalid Union SwitchField value: " + switchField);
        }
    }

    private void encodeStruct(UaEncoder encoder, DynamicStruct struct) {
        StructureField[] fields = structureDefinition.getFields();

        long switchField = 0xFFFFFFFFL;
        if (structureDefinition.getStructureType() == StructureType.StructureWithOptionalFields) {
            switchField = 0L;
            for (int i = 0; i < fields.length; i++) {
                StructureField field = fields[i];
                if (!field.getIsOptional() ||
                    (field.getIsOptional() && struct.getMembers().containsKey(field.getName()))) {

                    switchField |= (1L << i);
                }
            }
            encoder.writeUInt32("SwitchField", UInteger.valueOf(switchField));
        }

        for (int i = 0; i < fields.length; i++) {
            StructureField field = fields[i];

            if (!field.getIsOptional() || ((switchField >>> i) & 1) == 1) {
                Object value = struct.getMembers().get(field.getName());

                encodeFieldValue(encoder, field, value);
            }
        }
    }

    private void encodeUnion(UaEncoder encoder, DynamicStruct struct) {
        StructureField[] fields = structureDefinition.getFields();

        if (struct.getMembers().isEmpty()) {
            encoder.writeUInt32("SwitchValue", UInteger.valueOf(0));
        } else {
            for (int i = 0; i < fields.length; i++) {
                StructureField field = fields[i];
                String fieldName = field.getName();

                if (struct.getMembers().containsKey(fieldName)) {
                    encoder.writeUInt32("SwitchValue", UInteger.valueOf(i + 1));

                    Object value = struct.getMembers().get(fieldName);

                    encodeFieldValue(encoder, field, value);

                    // Return as soon as a field has been encoded.
                    // Unions are only one field, indicated by SwitchValue.
                    return;
                }
            }

            // struct contained no members or the name didn't match a field name... encoding failure.
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, "no Union value found");
        }
    }

    private void encodeFieldValue(UaEncoder encoder, StructureField field, Object value) {
        String fieldName = field.getName();
        NodeId dataTypeId = field.getDataType();

        // Note: shall be scalar or fixed dimension
        Integer valueRank = field.getValueRank();
        if (valueRank == -1) {
            Object hint = fieldHints.get(field);
            if (hint instanceof BuiltinDataType) {
                encodeBuiltinDataType(encoder, fieldName, (BuiltinDataType) hint, value);
            } else {
                CodecType codecType = (CodecType) hint;

                switch (codecType) {
                    case ENUM:
                        encoder.writeEnum(fieldName, (UaEnumeratedType) value);
                        break;
                    case STRUCT:
                        encoder.writeStruct(fieldName, value, dataTypeId);
                        break;
                    default:
                        throw new RuntimeException("codecType: " + codecType);
                }
            }
        } else if (valueRank == 1) {
            Object hint = fieldHints.get(field);
            if (hint instanceof BuiltinDataType) {
                encodeBuiltinDataTypeArray(encoder, fieldName, (BuiltinDataType) hint, value);
            } else {
                CodecType codecType = (CodecType) hint;

                switch (codecType) {
                    case ENUM:
                        encoder.writeEnumArray(fieldName, (UaEnumeratedType[]) value);
                        break;
                    case STRUCT:
                        encoder.writeStructArray(fieldName, (Object[]) value, dataTypeId);
                        break;
                    default:
                        throw new RuntimeException("codecType: " + codecType);
                }
            }
        } else if (valueRank > 1) {
            // TODO special matrix encoding for multi-dimensional array structure fields
            throw new RuntimeException("not implemented");
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError, "illegal ValueRank: " + valueRank);
        }
    }

    private static Object decodeBuiltinDataType(
        UaDecoder decoder,
        String fieldName,
        BuiltinDataType builtinDataType
    ) {

        switch (builtinDataType) {
            case Boolean:
                return decoder.readBoolean(fieldName);
            case SByte:
                return decoder.readSByte(fieldName);
            case Byte:
                return decoder.readByte(fieldName);
            case Int16:
                return decoder.readInt16(fieldName);
            case UInt16:
                return decoder.readUInt16(fieldName);
            case Int32:
                return decoder.readInt32(fieldName);
            case UInt32:
                return decoder.readUInt32(fieldName);
            case Int64:
                return decoder.readInt64(fieldName);
            case UInt64:
                return decoder.readUInt64(fieldName);
            case Float:
                return decoder.readFloat(fieldName);
            case Double:
                return decoder.readDouble(fieldName);
            case String:
                return decoder.readString(fieldName);
            case DateTime:
                return decoder.readDateTime(fieldName);
            case Guid:
                return decoder.readGuid(fieldName);
            case ByteString:
                return decoder.readByteString(fieldName);
            case XmlElement:
                return decoder.readXmlElement(fieldName);
            case NodeId:
                return decoder.readNodeId(fieldName);
            case ExpandedNodeId:
                return decoder.readExpandedNodeId(fieldName);
            case StatusCode:
                return decoder.readStatusCode(fieldName);
            case QualifiedName:
                return decoder.readQualifiedName(fieldName);
            case LocalizedText:
                return decoder.readLocalizedText(fieldName);
            case ExtensionObject:
                return decoder.readExtensionObject(fieldName);
            case DataValue:
                return decoder.readDataValue(fieldName);
            case Variant:
                return decoder.readVariant(fieldName);
            case DiagnosticInfo:
                return decoder.readDiagnosticInfo(fieldName);
            default:
                // Shouldn't happen
                throw new RuntimeException("unhandled BuiltinDataType: " + builtinDataType);
        }
    }

    private static Object decodeBuiltinDataTypeArray(
        UaDecoder decoder,
        String fieldName,
        BuiltinDataType builtinDataType
    ) {

        switch (builtinDataType) {
            case Boolean:
                return decoder.readBooleanArray(fieldName);
            case SByte:
                return decoder.readSByteArray(fieldName);
            case Byte:
                return decoder.readByteArray(fieldName);
            case Int16:
                return decoder.readInt16Array(fieldName);
            case UInt16:
                return decoder.readUInt16Array(fieldName);
            case Int32:
                return decoder.readInt32Array(fieldName);
            case UInt32:
                return decoder.readUInt32Array(fieldName);
            case Int64:
                return decoder.readInt64Array(fieldName);
            case UInt64:
                return decoder.readUInt64Array(fieldName);
            case Float:
                return decoder.readFloatArray(fieldName);
            case Double:
                return decoder.readDoubleArray(fieldName);
            case String:
                return decoder.readStringArray(fieldName);
            case DateTime:
                return decoder.readDateTimeArray(fieldName);
            case Guid:
                return decoder.readGuidArray(fieldName);
            case ByteString:
                return decoder.readByteStringArray(fieldName);
            case XmlElement:
                return decoder.readXmlElementArray(fieldName);
            case NodeId:
                return decoder.readNodeIdArray(fieldName);
            case ExpandedNodeId:
                return decoder.readExpandedNodeIdArray(fieldName);
            case StatusCode:
                return decoder.readStatusCodeArray(fieldName);
            case QualifiedName:
                return decoder.readQualifiedNameArray(fieldName);
            case LocalizedText:
                return decoder.readLocalizedTextArray(fieldName);
            case ExtensionObject:
                return decoder.readExtensionObjectArray(fieldName);
            case DataValue:
                return decoder.readDataValueArray(fieldName);
            case Variant:
                return decoder.readVariantArray(fieldName);
            case DiagnosticInfo:
                return decoder.readDiagnosticInfoArray(fieldName);
            default:
                // Shouldn't happen
                throw new RuntimeException("unhandled BuiltinDataType: " + builtinDataType);
        }
    }

    private static void encodeBuiltinDataType(
        UaEncoder encoder,
        String fieldName,
        BuiltinDataType builtinDataType,
        Object value
    ) {

        switch (builtinDataType) {
            case Boolean:
                encoder.writeBoolean(fieldName, (Boolean) value);
                break;
            case SByte:
                encoder.writeSByte(fieldName, (Byte) value);
                break;
            case Byte:
                encoder.writeByte(fieldName, (UByte) value);
                break;
            case Int16:
                encoder.writeInt16(fieldName, (Short) value);
                break;
            case UInt16:
                encoder.writeUInt16(fieldName, (UShort) value);
                break;
            case Int32:
                encoder.writeInt32(fieldName, (Integer) value);
                break;
            case UInt32:
                encoder.writeUInt32(fieldName, (UInteger) value);
                break;
            case Int64:
                encoder.writeInt64(fieldName, (Long) value);
                break;
            case UInt64:
                encoder.writeUInt64(fieldName, (ULong) value);
                break;
            case Float:
                encoder.writeFloat(fieldName, (Float) value);
                break;
            case Double:
                encoder.writeDouble(fieldName, (Double) value);
                break;
            case String:
                encoder.writeString(fieldName, (String) value);
                break;
            case DateTime:
                encoder.writeDateTime(fieldName, (DateTime) value);
                break;
            case Guid:
                encoder.writeGuid(fieldName, (UUID) value);
                break;
            case ByteString:
                encoder.writeByteString(fieldName, (ByteString) value);
                break;
            case XmlElement:
                encoder.writeXmlElement(fieldName, (XmlElement) value);
                break;
            case NodeId:
                encoder.writeNodeId(fieldName, (NodeId) value);
                break;
            case ExpandedNodeId:
                encoder.writeExpandedNodeId(fieldName, (ExpandedNodeId) value);
                break;
            case StatusCode:
                encoder.writeStatusCode(fieldName, (StatusCode) value);
                break;
            case QualifiedName:
                encoder.writeQualifiedName(fieldName, (QualifiedName) value);
                break;
            case LocalizedText:
                encoder.writeLocalizedText(fieldName, (LocalizedText) value);
                break;
            case ExtensionObject:
                encoder.writeExtensionObject(fieldName, (ExtensionObject) value);
                break;
            case DataValue:
                encoder.writeDataValue(fieldName, (DataValue) value);
                break;
            case Variant:
                encoder.writeVariant(fieldName, (Variant) value);
                break;
            case DiagnosticInfo:
                encoder.writeDiagnosticInfo(fieldName, (DiagnosticInfo) value);
                break;
            default:
                // Shouldn't happen
                throw new RuntimeException("unhandled BuiltinDataType: " + builtinDataType);
        }
    }

    private static void encodeBuiltinDataTypeArray(
        UaEncoder encoder,
        String fieldName,
        BuiltinDataType builtinDataType,
        Object value
    ) {

        switch (builtinDataType) {
            case Boolean:
                encoder.writeBooleanArray(fieldName, (Boolean[]) value);
                break;
            case SByte:
                encoder.writeSByteArray(fieldName, (Byte[]) value);
                break;
            case Byte:
                encoder.writeByteArray(fieldName, (UByte[]) value);
                break;
            case Int16:
                encoder.writeInt16Array(fieldName, (Short[]) value);
                break;
            case UInt16:
                encoder.writeUInt16Array(fieldName, (UShort[]) value);
                break;
            case Int32:
                encoder.writeInt32Array(fieldName, (Integer[]) value);
                break;
            case UInt32:
                encoder.writeUInt32Array(fieldName, (UInteger[]) value);
                break;
            case Int64:
                encoder.writeInt64Array(fieldName, (Long[]) value);
                break;
            case UInt64:
                encoder.writeUInt64Array(fieldName, (ULong[]) value);
                break;
            case Float:
                encoder.writeFloatArray(fieldName, (Float[]) value);
                break;
            case Double:
                encoder.writeDoubleArray(fieldName, (Double[]) value);
                break;
            case String:
                encoder.writeStringArray(fieldName, (String[]) value);
                break;
            case DateTime:
                encoder.writeDateTimeArray(fieldName, (DateTime[]) value);
                break;
            case Guid:
                encoder.writeGuidArray(fieldName, (UUID[]) value);
                break;
            case ByteString:
                encoder.writeByteStringArray(fieldName, (ByteString[]) value);
                break;
            case XmlElement:
                encoder.writeXmlElementArray(fieldName, (XmlElement[]) value);
                break;
            case NodeId:
                encoder.writeNodeIdArray(fieldName, (NodeId[]) value);
                break;
            case ExpandedNodeId:
                encoder.writeExpandedNodeIdArray(fieldName, (ExpandedNodeId[]) value);
                break;
            case StatusCode:
                encoder.writeStatusCodeArray(fieldName, (StatusCode[]) value);
                break;
            case QualifiedName:
                encoder.writeQualifiedNameArray(fieldName, (QualifiedName[]) value);
                break;
            case LocalizedText:
                encoder.writeLocalizedTextArray(fieldName, (LocalizedText[]) value);
                break;
            case ExtensionObject:
                encoder.writeExtensionObjectArray(fieldName, (ExtensionObject[]) value);
                break;
            case DataValue:
                encoder.writeDataValueArray(fieldName, (DataValue[]) value);
                break;
            case Variant:
                encoder.writeVariantArray(fieldName, (Variant[]) value);
                break;
            case DiagnosticInfo:
                encoder.writeDiagnosticInfoArray(fieldName, (DiagnosticInfo[]) value);
                break;
            default:
                // Shouldn't happen
                throw new RuntimeException("unhandled BuiltinDataType: " + builtinDataType);
        }
    }

    private enum CodecType {ENUM, STRUCT}

}
