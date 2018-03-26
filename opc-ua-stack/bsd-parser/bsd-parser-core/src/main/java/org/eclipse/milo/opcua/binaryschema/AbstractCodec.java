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

package org.eclipse.milo.opcua.binaryschema;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
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
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.opcfoundation.opcua.binaryschema.FieldType;
import org.opcfoundation.opcua.binaryschema.StructuredType;
import org.opcfoundation.opcua.binaryschema.SwitchOperand;

public abstract class AbstractCodec<StructureT, MemberT> implements OpcUaBinaryDataTypeCodec<StructureT> {

    private static final ImmutableMap<String, Function<OpcUaBinaryStreamDecoder, Object>> READERS;
    private static final ImmutableMap<String, BiConsumer<OpcUaBinaryStreamEncoder, Object>> WRITERS;
    private static final ImmutableMap<String, Class<?>> TYPE_CLASS_MAP;

    static {
        READERS = ImmutableMap.<String, Function<OpcUaBinaryStreamDecoder, Object>>builder()
            .put("Boolean", OpcUaBinaryStreamDecoder::readBoolean)
            .put("SByte", OpcUaBinaryStreamDecoder::readSByte)
            .put("Int16", OpcUaBinaryStreamDecoder::readInt16)
            .put("Int32", OpcUaBinaryStreamDecoder::readInt32)
            .put("Int64", OpcUaBinaryStreamDecoder::readInt64)
            .put("Byte", OpcUaBinaryStreamDecoder::readByte)
            .put("UInt16", OpcUaBinaryStreamDecoder::readUInt16)
            .put("UInt32", OpcUaBinaryStreamDecoder::readUInt32)
            .put("UInt64", OpcUaBinaryStreamDecoder::readUInt64)
            .put("Float", OpcUaBinaryStreamDecoder::readFloat)
            .put("Double", OpcUaBinaryStreamDecoder::readDouble)
            .put("String", OpcUaBinaryStreamDecoder::readUtf8CharArray)
            .put("DateTime", OpcUaBinaryStreamDecoder::readDateTime)
            .put("Guid", OpcUaBinaryStreamDecoder::readGuid)
            .put("ByteString", OpcUaBinaryStreamDecoder::readByteString)
            .put("XmlElement", OpcUaBinaryStreamDecoder::readXmlElement)
            .put("NodeId", OpcUaBinaryStreamDecoder::readNodeId)
            .put("ExpandedNodeId", OpcUaBinaryStreamDecoder::readExpandedNodeId)
            .put("StatusCode", OpcUaBinaryStreamDecoder::readStatusCode)
            .put("QualifiedName", OpcUaBinaryStreamDecoder::readQualifiedName)
            .put("LocalizedText", OpcUaBinaryStreamDecoder::readLocalizedText)
            .put("ExtensionObject", OpcUaBinaryStreamDecoder::readExtensionObject)
            .put("DataValue", OpcUaBinaryStreamDecoder::readDataValue)
            .put("Variant", OpcUaBinaryStreamDecoder::readVariant)
            .put("DiagnosticInfo", OpcUaBinaryStreamDecoder::readDiagnosticInfo)

            .put("Bit", OpcUaBinaryStreamDecoder::readBit)
            .put("Char", OpcUaBinaryStreamDecoder::readCharacter)
            .put("CharArray", OpcUaBinaryStreamDecoder::readUtf8CharArray)
            .put("WideChar", OpcUaBinaryStreamDecoder::readWideChar)
            .put("WideCharArray", OpcUaBinaryStreamDecoder::readUtf16CharArray)
            .put("WideString", OpcUaBinaryStreamDecoder::readUtf16CharArray)

            .build();

        WRITERS = ImmutableMap.<String, BiConsumer<OpcUaBinaryStreamEncoder, Object>>builder()
            .put("Boolean", (w, v) -> w.writeBoolean((Boolean) v))
            .put("SByte", (w, v) -> w.writeSByte((Byte) v))
            .put("Int16", (w, v) -> w.writeInt16((Short) v))
            .put("Int32", (w, v) -> w.writeInt32((Integer) v))
            .put("Int64", (w, v) -> w.writeInt64((Long) v))
            .put("Byte", (w, v) -> w.writeByte((UByte) v))
            .put("UInt16", (w, v) -> w.writeUInt16((UShort) v))
            .put("UInt32", (w, v) -> w.writeUInt32((UInteger) v))
            .put("UInt64", (w, v) -> w.writeUInt64((ULong) v))
            .put("Float", (w, v) -> w.writeFloat((Float) v))
            .put("Double", (w, v) -> w.writeDouble((Double) v))
            .put("String", (w, v) -> w.writeUtf8CharArray((String) v))
            .put("DateTime", (w, v) -> w.writeDateTime((DateTime) v))
            .put("Guid", (w, v) -> w.writeGuid((UUID) v))
            .put("ByteString", (w, v) -> w.writeByteString((ByteString) v))
            .put("XmlElement", (w, v) -> w.writeXmlElement((XmlElement) v))
            .put("NodeId", (w, v) -> w.writeNodeId((NodeId) v))
            .put("ExpandedNodeId", (w, v) -> w.writeExpandedNodeId((ExpandedNodeId) v))
            .put("StatusCode", (w, v) -> w.writeStatusCode((StatusCode) v))
            .put("QualifiedName", (w, v) -> w.writeQualifiedName((QualifiedName) v))
            .put("LocalizedText", (w, v) -> w.writeLocalizedText((LocalizedText) v))
            .put("ExtensionObject", (w, v) -> w.writeExtensionObject((ExtensionObject) v))
            .put("DataValue", (w, v) -> w.writeDataValue((DataValue) v))
            .put("Variant", (w, v) -> w.writeVariant((Variant) v))
            .put("DiagnosticInfo", (w, v) -> w.writeDiagnosticInfo((DiagnosticInfo) v))

            .put("Bit", (w, v) -> w.writeBit((Integer) v))
            .put("Char", (w, v) -> w.writeCharacter((Character) v))
            .put("CharArray", (w, v) -> w.writeUtf8CharArray((String) v))
            .put("WideChar", (w, v) -> w.writeWideChar((Character) v))
            .put("WideCharArray", (w, v) -> w.writeUtf16CharArray((String) v))
            .put("WideString", (w, v) -> w.writeUtf16CharArray((String) v))

            .build();

        TYPE_CLASS_MAP = ImmutableMap.<String, Class<?>>builder()
            .put("Boolean", BuiltinDataType.Boolean.getBackingClass())
            .put("SByte", BuiltinDataType.SByte.getBackingClass())
            .put("Int16", BuiltinDataType.Int16.getBackingClass())
            .put("Int32", BuiltinDataType.Int32.getBackingClass())
            .put("Int64", BuiltinDataType.Int64.getBackingClass())
            .put("Byte", BuiltinDataType.Byte.getBackingClass())
            .put("UInt16", BuiltinDataType.UInt16.getBackingClass())
            .put("UInt32", BuiltinDataType.UInt32.getBackingClass())
            .put("UInt64", BuiltinDataType.UInt64.getBackingClass())
            .put("Float", BuiltinDataType.Float.getBackingClass())
            .put("Double", BuiltinDataType.Double.getBackingClass())
            .put("String", BuiltinDataType.String.getBackingClass())
            .put("DateTime", BuiltinDataType.DateTime.getBackingClass())
            .put("Guid", BuiltinDataType.Guid.getBackingClass())
            .put("ByteString", BuiltinDataType.ByteString.getBackingClass())
            .put("XmlElement", BuiltinDataType.XmlElement.getBackingClass())
            .put("NodeId", BuiltinDataType.NodeId.getBackingClass())
            .put("ExpandedNodeId", BuiltinDataType.ExpandedNodeId.getBackingClass())
            .put("StatusCode", BuiltinDataType.StatusCode.getBackingClass())
            .put("QualifiedName", BuiltinDataType.QualifiedName.getBackingClass())
            .put("LocalizedText", BuiltinDataType.LocalizedText.getBackingClass())
            .put("ExtensionObject", BuiltinDataType.ExtensionObject.getBackingClass())
            .put("DataValue", BuiltinDataType.DataValue.getBackingClass())
            .put("Variant", BuiltinDataType.Variant.getBackingClass())
            .put("DiagnosticInfo", BuiltinDataType.DiagnosticInfo.getBackingClass())

            .put("Bit", Integer.class)
            .put("Char", Character.class)
            .put("CharArray", String.class)
            .put("WideChar", Character.class)
            .put("WideCharArray", String.class)
            .put("WideString", String.class)
            .build();
    }

    private final StructuredType structuredType;

    protected AbstractCodec(StructuredType structuredType) {
        this.structuredType = structuredType;
    }

    @Override
    public StructureT decode(
        SerializationContext context,
        OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {

        LinkedHashMap<String, MemberT> members = new LinkedHashMap<>();

        PeekingIterator<FieldType> fieldIterator = Iterators
            .peekingIterator(structuredType.getField().iterator());

        while (fieldIterator.hasNext()) {
            FieldType field = fieldIterator.next();
            String fieldName = field.getName();
            String typeName = field.getTypeName().getLocalPart();
            String typeNamespace = field.getTypeName().getNamespaceURI();

            if (!fieldIsPresent(field, members)) {
                continue;
            }

            boolean typeNamespaceIsUa =
                Namespaces.OPC_UA.equals(typeNamespace) ||
                    Namespaces.OPC_UA_BSD.equals(typeNamespace);

            if (fieldIsScalar(field)) {
                if (typeNamespaceIsUa && READERS.containsKey(typeName)) {
                    Object value = READERS.get(typeName).apply(decoder);

                    members.put(fieldName, opcUaToMemberTypeScalar(fieldName, value, typeName));
                } else {
                    Object value = context.decode(typeNamespace, typeName, decoder);

                    members.put(fieldName, opcUaToMemberTypeScalar(fieldName, value, typeName));
                }
            } else {
                if (field.isIsLengthInBytes()) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "IsLengthInBytes=true not supported");
                }

                int length = fieldLength(field, members);

                if ("Bit".equals(typeName) && typeNamespaceIsUa) {
                    BigInteger bitAccumulation = BigInteger.valueOf(0L);

                    for (int i = 0; i < length; i++) {
                        BigInteger bitValue = BigInteger.valueOf(decoder.readBit());

                        bitAccumulation = bitAccumulation.or(bitValue.shiftLeft(i));
                    }

                    members.put(fieldName, opcUaToMemberTypeArray(fieldName, bitAccumulation.intValue(), typeName));
                } else {
                    Object[] values = new Object[length];

                    if (typeNamespaceIsUa && READERS.containsKey(typeName)) {
                        for (int i = 0; i < length; i++) {
                            Object value = READERS.get(typeName).apply(decoder);

                            values[i] = value;
                        }
                    } else {
                        for (int i = 0; i < length; i++) {
                            Object value = context.decode(typeNamespace, typeName, decoder);

                            values[i] = value;
                        }
                    }

                    members.put(fieldName, opcUaToMemberTypeArray(fieldName, values, typeName));
                }
            }
        }

        return createStructure(structuredType.getName(), members);
    }

    @Override
    public void encode(
        SerializationContext context,
        StructureT structure,
        OpcUaBinaryStreamEncoder encoder) throws UaSerializationException {

        LinkedHashMap<String, MemberT> members = new LinkedHashMap<>(getMembers(structure));

        PeekingIterator<FieldType> fieldIterator = Iterators
            .peekingIterator(structuredType.getField().iterator());

        while (fieldIterator.hasNext()) {
            FieldType field = fieldIterator.next();

            if (!fieldIsPresent(field, members)) {
                continue;
            }

            String typeName = field.getTypeName().getLocalPart();
            String typeNamespace = field.getTypeName().getNamespaceURI();

            MemberT member = members.get(field.getName());

            boolean typeNamespaceIsUa =
                Namespaces.OPC_UA.equals(typeNamespace) ||
                    Namespaces.OPC_UA_BSD.equals(typeNamespace);

            if (fieldIsScalar(field)) {
                Object scalarValue = memberTypeToOpcUaScalar(member, typeName);

                if (typeNamespaceIsUa && WRITERS.containsKey(typeName)) {
                    WRITERS.get(typeName).accept(encoder, scalarValue);
                } else {
                    context.encode(typeNamespace, typeName, scalarValue, encoder);
                }
            } else {
                if (field.isIsLengthInBytes()) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_EncodingError,
                        "IsLengthInBytes=true not supported");
                }

                int length = fieldLength(field, members);

                if ("Bit".equals(typeName) && typeNamespaceIsUa) {
                    Number number = (Number) memberTypeToOpcUaArray(member, typeName);
                    BigInteger bi = BigInteger.valueOf(number.longValue());

                    for (int i = 0; i < length; i++) {
                        encoder.writeBit(bi.shiftRight(i).and(BigInteger.ONE).intValue());
                    }
                } else {
                    Object[] valueArray = (Object[]) memberTypeToOpcUaArray(member, typeName);

                    if (valueArray != null) {
                        if (typeNamespaceIsUa && WRITERS.containsKey(typeName)) {
                            for (int i = 0; i < length; i++) {
                                Object value = valueArray[i];

                                WRITERS.get(typeName).accept(encoder, value);
                            }
                        } else {
                            for (int i = 0; i < length; i++) {
                                Object value = valueArray[i];

                                context.encode(typeNamespace, typeName, value, encoder);
                            }
                        }
                    }
                }
            }
        }
    }

    protected abstract StructureT createStructure(String name, LinkedHashMap<String, MemberT> members);

    protected abstract Map<String, MemberT> getMembers(StructureT value);

    /**
     * Convert an OPC UA scalar value into a member of type {@link MemberT}.
     *
     * @param name     then name of the member.
     * @param value    the value of the member.
     * @param typeName the name of the OPC UA DataType.
     * @return a member of type {@link MemberT}.
     */
    protected abstract MemberT opcUaToMemberTypeScalar(String name, Object value, String typeName);

    /**
     * Convert an OPC UA array value into a member of type {@link MemberT}.
     *
     * @param name     the name of the member.
     * @param values   the values of the member array.
     * @param typeName the name of the OPC UA DataType.
     * @return member of type {@link MemberT}.
     */
    protected abstract MemberT opcUaToMemberTypeArray(String name, Object values, String typeName);

    protected abstract Object memberTypeToOpcUaScalar(MemberT member, String typeName);

    protected abstract Object memberTypeToOpcUaArray(MemberT member, String typeName);

    private int fieldLength(FieldType field, LinkedHashMap<String, MemberT> members) {
        int length = 1;

        if (field.getLength() != null) {
            length = field.getLength().intValue();
        } else if (field.getLengthField() != null) {
            MemberT lengthMember = members.get(field.getLengthField());

            if (lengthMember != null) {
                String lengthTypeName = structuredType.getField().stream()
                    .filter(f -> f.getName().equals(field.getLengthField()))
                    .findFirst()
                    .map(f -> f.getTypeName().getLocalPart())
                    .orElse("Int32");

                length = ((Number) memberTypeToOpcUaScalar(lengthMember, lengthTypeName)).intValue();
            }
        }

        return length;
    }

    private boolean fieldIsPresent(FieldType field, Map<String, MemberT> members) {
        if (field.getSwitchField() == null) {
            return true;
        } else {
            MemberT controlField = members.get(field.getSwitchField());

            String controlTypeName = structuredType.getField().stream()
                .filter(f -> f.getName().equals(field.getSwitchField()))
                .findFirst()
                .map(f -> f.getTypeName().getLocalPart())
                .orElse("Int32");

            long controlValue = ((Number) memberTypeToOpcUaScalar(controlField, controlTypeName)).longValue();

            long switchValue = field.getSwitchValue() != null ?
                field.getSwitchValue() : 1L;

            SwitchOperand switchOperand = field.getSwitchOperand() != null ?
                field.getSwitchOperand() : SwitchOperand.EQUALS;

            return compareToSwitchValue(controlValue, switchOperand, switchValue);
        }
    }

    private static boolean compareToSwitchValue(long controlValue, SwitchOperand switchOperand, long switchValue) {
        switch (switchOperand) {
            case EQUALS:
                return controlValue == switchValue;
            case NOT_EQUAL:
                return controlValue != switchValue;
            case GREATER_THAN:
                return controlValue > switchValue;
            case GREATER_THAN_OR_EQUAL:
                return controlValue >= switchValue;
            case LESS_THAN:
                return controlValue < switchValue;
            case LESS_THAN_OR_EQUAL:
                return controlValue <= switchValue;
            default:
                throw new UaSerializationException(
                    StatusCodes.Bad_InternalError,
                    "unknown SwitchOperand: " + switchOperand);
        }
    }

    private static boolean fieldIsScalar(FieldType field) {
        return field.getLengthField() == null && field.getLength() == null;
    }

}
