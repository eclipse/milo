/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.IOException;
import java.io.Reader;
import java.util.UUID;
import java.util.function.Function;

import com.google.gson.stream.JsonReader;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
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

public class OpcUaJsonDecoder implements UaDecoder {

    JsonReader jsonReader;

    public OpcUaJsonDecoder(Reader reader) {
        this.jsonReader = new JsonReader(reader);
    }

    void reset(Reader reader) {
        jsonReader = new JsonReader(reader);
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Byte readSByte(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Short readInt16(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Integer readInt32(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Long readInt64(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UByte readByte(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UShort readUInt16(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UInteger readUInt32(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public ULong readUInt64(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Float readFloat(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readFloat: %s != %s", field, nextName)
                    );
                }
            }

            switch (jsonReader.peek()) {
                case NUMBER:
                    return (float) jsonReader.nextDouble();
                case STRING: {
                    String s = jsonReader.nextString();
                    switch (s) {
                        case "Infinity":
                            return Float.POSITIVE_INFINITY;
                        case "-Infinity":
                            return Float.NEGATIVE_INFINITY;
                        case "NaN":
                            return Float.NaN;
                        default:
                            throw new UaSerializationException(
                                StatusCodes.Bad_DecodingError,
                                "readFloat: unexpected string value: " + s
                            );
                    }
                }
                default:
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "readFloat: unexpected token: " + jsonReader.peek()
                    );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Double readDouble(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public String readString(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public DateTime readDateTime(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UUID readGuid(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public ByteString readByteString(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public XmlElement readXmlElement(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public NodeId readNodeId(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UaMessage readMessage(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> T readEnum(String field, Class<T> enumType) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, NodeId dataTypeId) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, DataTypeCodec codec) throws UaSerializationException {
        return null;
    }

    @Override
    public Boolean[] readBooleanArray(String field) throws UaSerializationException {
        return new Boolean[0];
    }

    @Override
    public Byte[] readSByteArray(String field) throws UaSerializationException {
        return new Byte[0];
    }

    @Override
    public Short[] readInt16Array(String field) throws UaSerializationException {
        return new Short[0];
    }

    @Override
    public Integer[] readInt32Array(String field) throws UaSerializationException {
        return new Integer[0];
    }

    @Override
    public Long[] readInt64Array(String field) throws UaSerializationException {
        return new Long[0];
    }

    @Override
    public UByte[] readByteArray(String field) throws UaSerializationException {
        return new UByte[0];
    }

    @Override
    public UShort[] readUInt16Array(String field) throws UaSerializationException {
        return new UShort[0];
    }

    @Override
    public UInteger[] readUInt32Array(String field) throws UaSerializationException {
        return new UInteger[0];
    }

    @Override
    public ULong[] readUInt64Array(String field) throws UaSerializationException {
        return new ULong[0];
    }

    @Override
    public Float[] readFloatArray(String field) throws UaSerializationException {
        return new Float[0];
    }

    @Override
    public Double[] readDoubleArray(String field) throws UaSerializationException {
        return new Double[0];
    }

    @Override
    public String[] readStringArray(String field) throws UaSerializationException {
        return new String[0];
    }

    @Override
    public DateTime[] readDateTimeArray(String field) throws UaSerializationException {
        return new DateTime[0];
    }

    @Override
    public UUID[] readGuidArray(String field) throws UaSerializationException {
        return new UUID[0];
    }

    @Override
    public ByteString[] readByteStringArray(String field) throws UaSerializationException {
        return new ByteString[0];
    }

    @Override
    public XmlElement[] readXmlElementArray(String field) throws UaSerializationException {
        return new XmlElement[0];
    }

    @Override
    public NodeId[] readNodeIdArray(String field) throws UaSerializationException {
        return new NodeId[0];
    }

    @Override
    public ExpandedNodeId[] readExpandedNodeIdArray(String field) throws UaSerializationException {
        return new ExpandedNodeId[0];
    }

    @Override
    public StatusCode[] readStatusCodeArray(String field) throws UaSerializationException {
        return new StatusCode[0];
    }

    @Override
    public QualifiedName[] readQualifiedNameArray(String field) throws UaSerializationException {
        return new QualifiedName[0];
    }

    @Override
    public LocalizedText[] readLocalizedTextArray(String field) throws UaSerializationException {
        return new LocalizedText[0];
    }

    @Override
    public ExtensionObject[] readExtensionObjectArray(String field) throws UaSerializationException {
        return new ExtensionObject[0];
    }

    @Override
    public DataValue[] readDataValueArray(String field) throws UaSerializationException {
        return new DataValue[0];
    }

    @Override
    public Variant[] readVariantArray(String field) throws UaSerializationException {
        return new Variant[0];
    }

    @Override
    public DiagnosticInfo[] readDiagnosticInfoArray(String field) throws UaSerializationException {
        return new DiagnosticInfo[0];
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> Object[] readEnumArray(String field, Class<T> enumType) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public Object[] readStructArray(String field, NodeId dataTypeId) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public Object[] readStructArray(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public <T> T[] readArray(String field, Function<String, T> decoder, Class<T> clazz) throws UaSerializationException {
        return null;
    }

}
