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

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.google.common.io.CharStreams;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
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
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaXmlStreamDecoder implements UaDecoder {

    private static final SerializationContext SERIALIZATION_CONTEXT = OpcUaDataTypeManager::getInstance;

    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

    static {
        FACTORY.setCoalescing(true);
        FACTORY.setNamespaceAware(true);
    }

    private final DocumentBuilder builder;

    private Document document;
    private Node currentNode;

    public OpcUaXmlStreamDecoder() {
        try {
            builder = FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new UaRuntimeException(StatusCodes.Bad_InternalError, e);
        }
    }

    public OpcUaXmlStreamDecoder(Reader reader) throws IOException, SAXException {
        this();

        setInput(reader);
    }

    public OpcUaXmlStreamDecoder(InputStream inputStream) throws IOException, SAXException {
        this();

        setInput(inputStream);
    }

    public OpcUaXmlStreamDecoder setInput(Reader reader) throws IOException, SAXException {
        String s = CharStreams.toString(reader);

        return setInput(new ByteArrayInputStream(s.getBytes()));
    }

    public OpcUaXmlStreamDecoder setInput(InputStream inputStream) throws IOException, SAXException {
        document = builder.parse(inputStream);
        currentNode = document.getFirstChild();

        return this;
    }

    private Node currentNode(String field) throws UaSerializationException {
        if (currentNode == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "currentNode==null"
            );
        }

        if (field != null && !field.equals(currentNode.getLocalName())) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                String.format("expected '%s' found '%s'",
                    field, currentNode.getLocalName())
            );
        }

        return currentNode;
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseBoolean(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Byte readSByte(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseByte(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Short readInt16(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseShort(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Integer readInt32(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseInt(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Long readInt64(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseLong(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public UByte readByte(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return ubyte(DatatypeConverter.parseShort(node.getTextContent()));
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public UShort readUInt16(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return ushort(DatatypeConverter.parseInt(node.getTextContent()));
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public UInteger readUInt32(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return uint(DatatypeConverter.parseLong(node.getTextContent()));
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public ULong readUInt64(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return ulong(DatatypeConverter.parseInteger(node.getTextContent()));
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Float readFloat(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseFloat(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Double readDouble(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return DatatypeConverter.parseDouble(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public String readString(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return node.getTextContent();
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public DateTime readDateTime(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            Calendar calendar = DatatypeConverter.parseDateTime(node.getTextContent());

            return new DateTime(calendar.getTime());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public UUID readGuid(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return UUID.fromString(node.getTextContent());
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public ByteString readByteString(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            byte[] bs = DatatypeConverter.parseBase64Binary(node.getTextContent());

            return ByteString.of(bs);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public XmlElement readXmlElement(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            return nodeToXmlElement(node);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public NodeId readNodeId(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Node idNode = node.getFirstChild();

        try {
            if (idNode != null) {
                return NodeId.parse(idNode.getTextContent());
            } else {
                return NodeId.NULL_VALUE;
            }
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Node expandedIdNode = node.getFirstChild();

        try {
            if (expandedIdNode != null) {
                return ExpandedNodeId.parse(expandedIdNode.getTextContent());
            } else {
                return ExpandedNodeId.NULL_VALUE;
            }
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        Node node = currentNode(field);

        try {
            long code = 0L;

            Node codeNode = node.getFirstChild();
            if (codeNode != null) {
                code = DatatypeConverter.parseUnsignedInt(codeNode.getTextContent());
            }

            return new StatusCode(code);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Map<String, Node> children = nodeMap(node.getChildNodes());

        int namespaceIndex = 0;
        String name = null;

        Node namespaceIndexNode = children.get("NamespaceIndex");
        if (namespaceIndexNode != null) {
            namespaceIndex = DatatypeConverter.parseInt(namespaceIndexNode.getTextContent());
        }

        Node nameNode = children.get("Name");
        if (nameNode != null) {
            name = nameNode.getTextContent();
        }

        try {
            return new QualifiedName(namespaceIndex, name);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Map<String, Node> children = nodeMap(node.getChildNodes());

        String locale = null;
        String text = null;

        Node localeNode = children.get("Locale");
        if (localeNode != null) {
            locale = localeNode.getTextContent();
        }

        Node textNode = children.get("Text");
        if (textNode != null) {
            text = textNode.getTextContent();
        }

        try {
            return new LocalizedText(locale, text);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Map<String, Node> children = nodeMap(node.getChildNodes());

        NodeId typeId = NodeId.NULL_VALUE;
        ExtensionObject extensionObject = new ExtensionObject(
            new XmlElement(""),
            NodeId.NULL_VALUE
        );

        Node typeIdNode = children.get("TypeId");
        if (typeIdNode != null) {
            currentNode = typeIdNode;
            typeId = readNodeId("TypeId");
        }

        Node bodyNode = children.get("Body");
        if (bodyNode != null) {
            if ("ByteString".equals(bodyNode.getLocalName()) &&
                Namespaces.OPC_UA_XSD.equals(bodyNode.getNamespaceURI())) {

                currentNode = bodyNode;

                extensionObject = new ExtensionObject(
                    readByteString("ByteString"),
                    typeId
                );
            } else {
                extensionObject = new ExtensionObject(
                    nodeToXmlElement(bodyNode),
                    typeId
                );
            }
        }

        try {
            return extensionObject;
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Map<String, Node> children = nodeMap(node.getChildNodes());

        Variant value = Variant.NULL_VALUE;
        StatusCode statusCode = StatusCode.GOOD;
        DateTime sourceTimestamp = null;
        UShort sourcePicoseconds = null;
        DateTime serverTimestamp = null;
        UShort serverPicoseconds = null;

        try {
            Node valueNode = children.get("Value");
            if (valueNode != null) {
                currentNode = valueNode;
                value = readVariant("Value");
            }

            Node statusCodeNode = children.get("StatusCode");
            if (statusCodeNode != null) {
                currentNode = statusCodeNode;
                statusCode = readStatusCode("StatusCode");
            }

            Node sourceTimestampNode = children.get("SourceTimestamp");
            if (sourceTimestampNode != null) {
                currentNode = sourceTimestampNode;
                sourceTimestamp = readDateTime("SourceTimestamp");
            }

            Node sourcePicosecondsNode = children.get("SourcePicoseconds");
            if (sourcePicosecondsNode != null) {
                currentNode = sourcePicosecondsNode;
                sourcePicoseconds = readUInt16("SourcePicoseconds");
            }

            Node serverTimestampNode = children.get("ServerTimestamp");
            if (serverTimestampNode != null) {
                currentNode = serverTimestampNode;
                serverTimestamp = readDateTime("ServerTimestamp");
            }

            Node serverPicosecondsNode = children.get("ServerPicoseconds");
            if (serverPicosecondsNode != null) {
                currentNode = serverPicosecondsNode;
                serverPicoseconds = readUInt16("ServerPicoseconds");
            }

            return new DataValue(
                value,
                statusCode,
                sourceTimestamp,
                sourcePicoseconds,
                serverTimestamp,
                serverPicoseconds
            );
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        Node node = currentNode(field);

        currentNode = node.getFirstChild().getFirstChild();

        Object value = readVariantValue();

        try {
            return new Variant(value);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    public Object readVariantValue() {
        Node node = currentNode(null);

        String nodeName = node.getLocalName();

        if (nodeName.startsWith("ListOf")) {
            String type = nodeName.substring(6);

            List<Object> values = new ArrayList<>();
            NodeList childNodes = node.getChildNodes();

            for (int i = 0; i < childNodes.getLength(); i++) {
                currentNode = childNodes.item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    values.add(readBuiltinType(type, type));
                }
            }

            Object array = Array.newInstance(builtinTypeClass(type), values.size());
            for (int i = 0; i < values.size(); i++) {
                Array.set(array, i, values.get(i));
            }

            return array;
        } else if (nodeName.equals("Matrix")) {
            List<Integer> dimensions = new ArrayList<>();
            Node child = node.getFirstChild();
            for (int i = 0; i < child.getChildNodes().getLength(); i++) {
                currentNode = child.getChildNodes().item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    dimensions.add(readInt32("Int32"));
                }
            }

            List<Object> elements = new ArrayList<>();
            child = child.getNextSibling();
            for (int i = 0; i < child.getChildNodes().getLength(); i++) {
                currentNode = child.getChildNodes().item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    String type = currentNode.getLocalName();
                    elements.add(readBuiltinType(type, type));
                }
            }

            Class<?> clazz = elements.get(0).getClass();
            Object array = Array.newInstance(clazz, elements.size());
            for (int i = 0; i < elements.size(); i++) {
                Array.set(array, i, elements.get(i));
            }

            int[] dims = new int[dimensions.size()];
            for (int i = 0; i < dimensions.size(); i++) {
                dims[i] = dimensions.get(i);
            }

            return ArrayUtil.unflatten(array, dims);
        } else {
            return readBuiltinType(nodeName, nodeName);
        }
    }

    private Object readBuiltinType(String field, String type) {
        switch (type) {
            case "Boolean":
                return readBoolean(field);
            case "SByte":
                return readSByte(field);
            case "Int16":
                return readInt16(field);
            case "Int32":
                return readInt32(field);
            case "Int64":
                return readInt64(field);
            case "Byte":
                return readByte(field);
            case "UInt16":
                return readUInt16(field);
            case "UInt32":
                return readUInt32(field);
            case "UInt64":
                return readUInt64(field);
            case "Float":
                return readFloat(field);
            case "Double":
                return readDouble(field);
            case "String":
                return readString(field);
            case "DateTime":
                return readDateTime(field);
            case "Guid":
                return readGuid(field);
            case "ByteString":
                return readByteString(field);
            case "XmlElement":
                return readXmlElement(field);
            case "NodeId":
                return readNodeId(field);
            case "ExpandedNodeId":
                return readExpandedNodeId(field);
            case "StatusCode":
                return readStatusCode(field);
            case "QualifiedName":
                return readQualifiedName(field);
            case "LocalizedText":
                return readLocalizedText(field);
            case "ExtensionObject":
                return readExtensionObject(field);
            case "DataValue":
                return readDataValue(field);
            case "Variant":
                return readVariant(field);
            case "DiagnosticInfo":
                return readDiagnosticInfo(field);
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "not builtin type: " + type);
        }
    }

    private static Class<?> builtinTypeClass(String type) {
        switch (type) {
            case "Boolean":
                return Boolean.class;
            case "SByte":
                return Byte.class;
            case "Int16":
                return Short.class;
            case "Int32":
                return Integer.class;
            case "Int64":
                return Long.class;
            case "Byte":
                return UByte.class;
            case "UInt16":
                return UShort.class;
            case "UInt32":
                return UInteger.class;
            case "UInt64":
                return ULong.class;
            case "Float":
                return Float.class;
            case "Double":
                return Double.class;
            case "String":
                return String.class;
            case "DateTime":
                return DateTime.class;
            case "Guid":
                return UUID.class;
            case "ByteString":
                return ByteString.class;
            case "XmlElement":
                return XmlElement.class;
            case "NodeId":
                return NodeId.class;
            case "ExpandedNodeId":
                return ExpandedNodeId.class;
            case "StatusCode":
                return StatusCode.class;
            case "QualifiedName":
                return QualifiedName.class;
            case "LocalizedText":
                return LocalizedText.class;
            case "ExtensionObject":
                return ExtensionObject.class;
            case "DataValue":
                return DataValue.class;
            case "Variant":
                return Variant.class;
            case "DiagnosticInfo":
                return DiagnosticInfo.class;
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "not builtin type: " + type);
        }
    }

    @Override
    public DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException {
        Node node = currentNode(field);

        Map<String, Node> children = nodeMap(node.getChildNodes());

        int symbolicId = -1;
        int namespaceUri = -1;
        int locale = -1;
        int localizedText = -1;
        String additionalInfo = null;
        StatusCode innerStatusCode = null;
        DiagnosticInfo innerDiagnosticInfo = null;

        Node child = children.get("SymbolicId");
        if (child != null) {
            currentNode = child;
            symbolicId = readInt32("SymbolicId");
        }

        child = children.get("NamespaceUri");
        if (child != null) {
            currentNode = child;
            namespaceUri = readInt32("NamespaceUri");
        }

        child = children.get("Locale");
        if (child != null) {
            currentNode = child;
            locale = readInt32("Locale");
        }

        child = children.get("LocalizedText");
        if (child != null) {
            currentNode = child;
            localizedText = readInt32("LocalizedText");
        }

        child = children.get("AdditionalInfo");
        if (child != null) {
            currentNode = child;
            additionalInfo = readString("AdditionalInfo");
        }

        child = children.get("InnerStatusCode");
        if (child != null) {
            currentNode = child;
            innerStatusCode = readStatusCode("InnerStatusCode");
        }

        child = children.get("InnerDiagnosticInfo");
        if (child != null) {
            currentNode = child;
            innerDiagnosticInfo = readDiagnosticInfo("InnerDiagnosticInfo");
        }

        try {
            return new DiagnosticInfo(
                namespaceUri,
                symbolicId,
                locale,
                localizedText,
                additionalInfo,
                innerStatusCode,
                innerDiagnosticInfo
            );
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] readArray(
        String field,
        Function<String, T> decoder,
        Class<T> clazz) throws UaSerializationException {

        Node node = currentNode(field);

        List<Object> values = new ArrayList<>();
        Node listNode = node.getFirstChild();
        NodeList children = listNode.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            currentNode = children.item(i);

            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                values.add(decoder.apply(currentNode.getLocalName()));
            }
        }

        try {
            Object array = Array.newInstance(clazz, values.size());
            for (int i = 0; i < values.size(); i++) {
                Array.set(array, i, values.get(i));
            }

            return (T[]) array;
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends UaStructure> T readBuiltinStruct(
        String field,
        Class<T> typeClass) throws UaSerializationException {

        Node node = currentNode(field);

        BuiltinDataTypeCodec<?> codec = BuiltinDataTypeDictionary.getBuiltinCodec(typeClass);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + typeClass
            );
        }

        try {
            return (T) codec.decode(SERIALIZATION_CONTEXT, this);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public <T extends UaStructure> T[] readBuiltinStructArray(
        String field,
        Class<T> clazz) throws UaSerializationException {

        return readArray(field, f -> readBuiltinStruct(null, clazz), clazz);
    }

    @Override
    public Object readStruct(String field, NodeId encodingId) throws UaSerializationException {
        Node node = currentNode(field);

        OpcUaXmlDataTypeCodec<?> codec = OpcUaDataTypeManager.getInstance().getXmlCodec(encodingId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + encodingId
            );
        }

        try {
            return codec.decode(SERIALIZATION_CONTEXT, this);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public Object[] readStructArray(String field, NodeId encodingId) throws UaSerializationException {
        Node node = currentNode(field);

        OpcUaXmlDataTypeCodec<?> codec = OpcUaDataTypeManager.getInstance().getXmlCodec(encodingId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + encodingId
            );
        }

        List<Object> values = new ArrayList<>();
        Node listNode = node.getFirstChild();
        NodeList children = listNode.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            currentNode = children.item(i);

            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                values.add(readStruct(currentNode.getLocalName(), encodingId));
            }
        }

        try {
            Object array = Array.newInstance(codec.getType(), values.size());
            for (int i = 0; i < values.size(); i++) {
                Array.set(array, i, values.get(i));
            }

            return (Object[]) array;
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    @Override
    public UaMessage readMessage(String field) throws UaSerializationException {
        Node node = currentNode(field);

        String typeName = node.getLocalName();

        BuiltinDataTypeCodec<?> codec = BuiltinDataTypeDictionary.getBuiltinCodec(typeName);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + typeName
            );
        }

        currentNode = node.getFirstChild();

        try {
            return (UaMessage) codec.decode(SERIALIZATION_CONTEXT, this);
        } finally {
            currentNode = node.getNextSibling();
        }
    }

    private static XmlElement nodeToXmlElement(Node node) throws UaSerializationException {
        try {
            StringWriter sw = new StringWriter();

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            transformer.transform(new DOMSource(node), new StreamResult(sw));

            return new XmlElement(sw.toString());
        } catch (TransformerException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private static Map<String, Node> nodeMap(NodeList nodeList) {
        LinkedHashMap<String, Node> nodeMap = new LinkedHashMap<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            nodeMap.put(node.getLocalName(), node);
        }

        return nodeMap;
    }

}
