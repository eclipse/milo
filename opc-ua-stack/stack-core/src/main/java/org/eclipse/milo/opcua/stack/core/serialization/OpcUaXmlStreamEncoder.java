/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.util.UUID;
import java.util.function.BiConsumer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
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
import org.eclipse.milo.opcua.stack.core.util.DocumentBuilderUtil;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OpcUaXmlStreamEncoder implements UaEncoder {

    private final DocumentBuilder builder;

    private Document document;
    private Node currentNode;

    private final SerializationContext context;

    public OpcUaXmlStreamEncoder(SerializationContext context) {
        this.context = context;

        try {
            builder = DocumentBuilderUtil.SHARED_FACTORY.newDocumentBuilder();

            document = builder.newDocument();
            currentNode = document;
        } catch (ParserConfigurationException e) {
            throw new UaRuntimeException(StatusCodes.Bad_InternalError, e);
        }
    }

    public Document getDocument() {
        return document;
    }

    public String getDocumentXml() {
        return "";
    }

    @Override
    public void writeBoolean(String field, Boolean value) throws UaSerializationException {
        Node element = document.createElementNS(Namespaces.OPC_UA_XSD, field);

        element.appendChild(document.createTextNode(value.toString()));

        currentNode.appendChild(element);
    }

    @Override
    public void writeSByte(String field, Byte value) throws UaSerializationException {

    }

    @Override
    public void writeInt16(String field, Short value) throws UaSerializationException {

    }

    @Override
    public void writeInt32(String field, Integer value) throws UaSerializationException {

    }

    @Override
    public void writeInt64(String field, Long value) throws UaSerializationException {

    }

    @Override
    public void writeByte(String field, UByte value) throws UaSerializationException {

    }

    @Override
    public void writeUInt16(String field, UShort value) throws UaSerializationException {

    }

    @Override
    public void writeUInt32(String field, UInteger value) throws UaSerializationException {

    }

    @Override
    public void writeUInt64(String field, ULong value) throws UaSerializationException {

    }

    @Override
    public void writeFloat(String field, Float value) throws UaSerializationException {

    }

    @Override
    public void writeDouble(String field, Double value) throws UaSerializationException {

    }

    @Override
    public void writeString(String field, String value) throws UaSerializationException {

    }

    @Override
    public void writeDateTime(String field, DateTime value) throws UaSerializationException {

    }

    @Override
    public void writeGuid(String field, UUID value) throws UaSerializationException {

    }

    @Override
    public void writeByteString(String field, ByteString value) throws UaSerializationException {

    }

    @Override
    public void writeXmlElement(String field, XmlElement value) throws UaSerializationException {

    }

    @Override
    public void writeNodeId(String field, NodeId value) throws UaSerializationException {

    }

    @Override
    public void writeExpandedNodeId(String field, ExpandedNodeId value) throws UaSerializationException {

    }

    @Override
    public void writeStatusCode(String field, StatusCode value) throws UaSerializationException {

    }

    @Override
    public void writeQualifiedName(String field, QualifiedName value) throws UaSerializationException {

    }

    @Override
    public void writeLocalizedText(String field, LocalizedText value) throws UaSerializationException {

    }

    @Override
    public void writeExtensionObject(String field, ExtensionObject value) throws UaSerializationException {

    }

    @Override
    public void writeDataValue(String field, DataValue value) throws UaSerializationException {

    }

    @Override
    public void writeVariant(String field, Variant value) throws UaSerializationException {

    }

    @Override
    public void writeDiagnosticInfo(String field, DiagnosticInfo value) throws UaSerializationException {

    }

    @Override
    public void writeMessage(String field, UaMessage message) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, NodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, ExpandedNodeId dataTypeId) throws UaSerializationException {
        NodeId localDateTypeId = dataTypeId
            .local(context.getNamespaceTable())
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                "no codec registered: " + dataTypeId
            ));

        writeStruct(field, value, localDateTypeId);
    }

    @Override
    public void writeStruct(String field, Object value, DataTypeCodec codec) throws UaSerializationException {

    }

    @Override
    public void writeBooleanArray(String field, Boolean[] value) throws UaSerializationException {
        writeArray(field, value, this::writeBoolean);
    }

    @Override
    public void writeSByteArray(String field, Byte[] value) throws UaSerializationException {
        writeArray(field, value, this::writeSByte);
    }

    @Override
    public void writeInt16Array(String field, Short[] value) throws UaSerializationException {
        writeArray(field, value, this::writeInt16);
    }

    @Override
    public void writeInt32Array(String field, Integer[] value) throws UaSerializationException {
        writeArray(field, value, this::writeInt32);
    }

    @Override
    public void writeInt64Array(String field, Long[] value) throws UaSerializationException {
        writeArray(field, value, this::writeInt64);
    }

    @Override
    public void writeByteArray(String field, UByte[] value) throws UaSerializationException {
        writeArray(field, value, this::writeByte);
    }

    @Override
    public void writeUInt16Array(String field, UShort[] value) throws UaSerializationException {
        writeArray(field, value, this::writeUInt16);
    }

    @Override
    public void writeUInt32Array(String field, UInteger[] value) throws UaSerializationException {
        writeArray(field, value, this::writeUInt32);
    }

    @Override
    public void writeUInt64Array(String field, ULong[] value) throws UaSerializationException {
        writeArray(field, value, this::writeUInt64);
    }

    @Override
    public void writeFloatArray(String field, Float[] value) throws UaSerializationException {
        writeArray(field, value, this::writeFloat);
    }

    @Override
    public void writeDoubleArray(String field, Double[] value) throws UaSerializationException {
        writeArray(field, value, this::writeDouble);
    }

    @Override
    public void writeStringArray(String field, String[] value) throws UaSerializationException {
        writeArray(field, value, this::writeString);
    }

    @Override
    public void writeDateTimeArray(String field, DateTime[] value) throws UaSerializationException {
        writeArray(field, value, this::writeDateTime);
    }

    @Override
    public void writeGuidArray(String field, UUID[] value) throws UaSerializationException {
        writeArray(field, value, this::writeGuid);
    }

    @Override
    public void writeByteStringArray(String field, ByteString[] value) throws UaSerializationException {
        writeArray(field, value, this::writeByteString);
    }

    @Override
    public void writeXmlElementArray(String field, XmlElement[] value) throws UaSerializationException {
        writeArray(field, value, this::writeXmlElement);
    }

    @Override
    public void writeNodeIdArray(String field, NodeId[] value) throws UaSerializationException {
        writeArray(field, value, this::writeNodeId);
    }

    @Override
    public void writeExpandedNodeIdArray(String field, ExpandedNodeId[] value) throws UaSerializationException {
        writeArray(field, value, this::writeExpandedNodeId);
    }

    @Override
    public void writeStatusCodeArray(String field, StatusCode[] value) throws UaSerializationException {
        writeArray(field, value, this::writeStatusCode);
    }

    @Override
    public void writeQualifiedNameArray(String field, QualifiedName[] value) throws UaSerializationException {
        writeArray(field, value, this::writeQualifiedName);
    }

    @Override
    public void writeLocalizedTextArray(String field, LocalizedText[] value) throws UaSerializationException {
        writeArray(field, value, this::writeLocalizedText);
    }

    @Override
    public void writeExtensionObjectArray(String field, ExtensionObject[] value) throws UaSerializationException {
        writeArray(field, value, this::writeExtensionObject);
    }

    @Override
    public void writeDataValueArray(String field, DataValue[] value) throws UaSerializationException {
        writeArray(field, value, this::writeDataValue);
    }

    @Override
    public void writeVariantArray(String field, Variant[] value) throws UaSerializationException {
        writeArray(field, value, this::writeVariant);
    }

    @Override
    public void writeDiagnosticInfoArray(String field, DiagnosticInfo[] value) throws UaSerializationException {
        writeArray(field, value, this::writeDiagnosticInfo);
    }

    @Override
    public void writeStructArray(
        String field, Object[] values, NodeId dataTypeId) throws UaSerializationException {

        writeArray(field, values, (s, o) -> writeStruct(s, o, dataTypeId));
    }

    @Override
    public void writeStructArray(
        String field,
        Object[] value,
        ExpandedNodeId dataTypeId
    ) throws UaSerializationException {

        NodeId localDateTypeId = dataTypeId
            .local(context.getNamespaceTable())
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                "no codec registered: " + dataTypeId
            ));

        writeStructArray(field, value, localDateTypeId);
    }

    @Override
    public <T> void writeArray(
        String field,
        T[] values,
        BiConsumer<String, T> encoder) throws UaSerializationException {

    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends UaStructure> void writeBuiltinStruct(
        String field,
        T value,
        Class<T> clazz) throws UaSerializationException {

        Node node = currentNode;

        BuiltinDataTypeCodec<T> codec = (BuiltinDataTypeCodec<T>) BuiltinDataTypeDictionary.getBuiltinCodec(clazz);

        if (codec == null) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, "no codec found: " + clazz);
        }

        currentNode = document.createElementNS(Namespaces.OPC_UA_XSD, field);
        codec.encode(context, this, value);

        currentNode = node;
    }

    @Override
    public <T extends UaStructure> void writeBuiltinStructArray(
        String field,
        T[] values,
        Class<T> clazz) throws UaSerializationException {

    }

}
