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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.google.common.io.CharStreams;
import jakarta.xml.bind.DatatypeConverter;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultXmlEncoding;
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
import org.eclipse.milo.opcua.stack.core.util.SecureXmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaXmlStreamDecoder implements UaDecoder {

    private final DocumentBuilder builder;

    private Document document;
    private Node currentNode;

    private final SerializationContext context;

    public OpcUaXmlStreamDecoder(SerializationContext context) {
        this.context = context;

        try {
            builder = SecureXmlUtil.SHARED_DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new UaRuntimeException(StatusCodes.Bad_InternalError, e);
        }
    }

    public OpcUaXmlStreamDecoder setInput(Document document) {
        this.document = document;
        this.currentNode = document.getFirstChild();

        return this;
    }

    public OpcUaXmlStreamDecoder setInput(Reader reader) throws IOException, SAXException {
        return setInput(new ByteArrayInputStream(CharStreams.toString(reader).getBytes(StandardCharsets.UTF_8)));
    }

    public OpcUaXmlStreamDecoder setInput(InputStream inputStream) throws IOException, SAXException {
        return setInput(builder.parse(inputStream));
    }

    private boolean currentNode(String field) throws UaSerializationException {
        if (currentNode == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "currentNode==null"
            );
        }

        return field == null || field.equals(currentNode.getLocalName());
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseBoolean(currentNode.getTextContent());
            } catch (IllegalArgumentException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return false;
        }
    }

    @Override
    public Byte readSByte(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseByte(currentNode.getTextContent());
            } catch (IllegalArgumentException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return (byte) 0;
        }
    }

    @Override
    public Short readInt16(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseShort(currentNode.getTextContent());
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0;
        }
    }

    @Override
    public Integer readInt32(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseInt(currentNode.getTextContent());
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0;
        }
    }

    @Override
    public Long readInt64(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseLong(currentNode.getTextContent());
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0L;
        }
    }

    @Override
    public UByte readByte(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return ubyte(DatatypeConverter.parseShort(currentNode.getTextContent()));
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return UByte.MIN;
        }
    }

    @Override
    public UShort readUInt16(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return ushort(DatatypeConverter.parseInt(currentNode.getTextContent()));
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return UShort.MIN;
        }
    }

    @Override
    public UInteger readUInt32(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return uint(DatatypeConverter.parseLong(currentNode.getTextContent()));
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return UInteger.MIN;
        }
    }

    @Override
    public ULong readUInt64(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return ulong(DatatypeConverter.parseInteger(currentNode.getTextContent()));
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return ULong.MIN;
        }
    }

    @Override
    public Float readFloat(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseFloat(currentNode.getTextContent());
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0f;
        }
    }

    @Override
    public Double readDouble(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return DatatypeConverter.parseDouble(currentNode.getTextContent());
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0.0;
        }
    }

    @Override
    public String readString(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return currentNode.getTextContent();
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return null;
        }
    }

    @Override
    public DateTime readDateTime(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                Calendar calendar = DatatypeConverter.parseDateTime(currentNode.getTextContent());

                return new DateTime(calendar.getTime());
            } catch (IllegalArgumentException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return DateTime.NULL_VALUE;
        }
    }

    @Override
    public UUID readGuid(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return UUID.fromString(currentNode.getTextContent());
            } catch (IllegalArgumentException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return new UUID(0L, 0L);
        }
    }

    @Override
    public ByteString readByteString(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                byte[] bs = DatatypeConverter.parseBase64Binary(currentNode.getTextContent());

                return ByteString.of(bs);
            } catch (IllegalArgumentException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return ByteString.NULL_VALUE;
        }
    }

    @Override
    public XmlElement readXmlElement(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                return nodeToXmlElement(currentNode);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return XmlElement.of(null);
        }
    }

    @Override
    public NodeId readNodeId(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node idNode = currentNode.getFirstChild();

            try {
                if (idNode != null) {
                    String textContent = idNode.getTextContent();

                    return NodeId.parseSafe(textContent).orElseThrow(() ->
                        new UaSerializationException(
                            StatusCodes.Bad_DecodingError, "invalid NodeId: " + textContent)
                    );
                } else {
                    return NodeId.NULL_VALUE;
                }
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return NodeId.NULL_VALUE;
        }
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node expandedIdNode = currentNode.getFirstChild();

            try {
                if (expandedIdNode != null) {
                    return ExpandedNodeId.parse(expandedIdNode.getTextContent());
                } else {
                    return ExpandedNodeId.NULL_VALUE;
                }
            } catch (UaRuntimeException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return ExpandedNodeId.NULL_VALUE;
        }
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                long code = 0L;

                Node codeNode = currentNode.getFirstChild();

                if (codeNode != null) {
                    code = DatatypeConverter.parseUnsignedInt(codeNode.getTextContent());
                }

                return new StatusCode(code);
            } catch (NumberFormatException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return new StatusCode(0L);
        }
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                Map<String, Node> children = nodeMap(currentNode.getChildNodes());

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

                return new QualifiedName(namespaceIndex, name);
            } catch (Throwable t) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, t);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return QualifiedName.NULL_VALUE;
        }
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        if (currentNode(field)) {
            try {
                Map<String, Node> children = nodeMap(currentNode.getChildNodes());

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

                return new LocalizedText(locale, text);
            } catch (Throwable t) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, t);
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return LocalizedText.NULL_VALUE;
        }
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        NodeId typeId = NodeId.NULL_VALUE;

        ExtensionObject extensionObject = new ExtensionObject(
            new XmlElement(""),
            NodeId.NULL_VALUE
        );

        if (currentNode(field)) {
            Node node = currentNode;

            try {
                Map<String, Node> children = nodeMap(currentNode.getChildNodes());

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
                            nodeToXmlElement(bodyNode.getFirstChild()),
                            typeId
                        );
                    }
                }

                return extensionObject;
            } catch (Throwable t) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, t);
            } finally {
                currentNode = node.getNextSibling();
            }
        } else {
            return extensionObject;
        }
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            Map<String, Node> children = nodeMap(currentNode.getChildNodes());

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
        } else {
            return new DataValue(Variant.NULL_VALUE);
        }
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            try {
                currentNode = node.getFirstChild().getFirstChild();
                Object value = readVariantValue();

                return new Variant(value);
            } catch (Throwable t) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, t);
            } finally {
                currentNode = node.getNextSibling();
            }
        } else {
            return Variant.NULL_VALUE;
        }
    }

    public Object readVariantValue() {
        if (currentNode(null)) {
            Node node = currentNode;

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
        } else {
            return null;
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
        if (currentNode(field)) {
            Node node = currentNode;

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
        } else {
            return DiagnosticInfo.NULL_VALUE;
        }
    }

    @Override
    public UaMessageType readMessage(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            String typeName = node.getLocalName();

            DataTypeCodec codec = null;

            DataTypeDictionary dictionary = context.getDataTypeManager()
                .getTypeDictionary(Namespaces.OPC_UA_XSD);

            if (dictionary != null) {
                codec = dictionary.getCodec(String.format("//xs:element[@name='%s']", typeName));
            }

            if (codec != null) {
                currentNode = node.getFirstChild();

                try {
                    return (UaMessageType) codec.decode(context, this);
                } finally {
                    currentNode = node.getNextSibling();
                }
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered: " + typeName
                );
            }
        } else {
            // TODO could be better if we passed Class<?> into method
            return null;
        }
    }

    @Override
    public Integer readEnum(String field) {
        if (currentNode(field)) {
            try {
                String s = currentNode.getTextContent();
                int lastIndex = s.lastIndexOf("_");

                if (lastIndex != -1) {
                    try {
                        return Integer.parseInt(s.substring(lastIndex + 1));
                    } catch (Exception e) {
                        throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                    }
                } else {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, "invalid enum value: " + s);
                }
            } finally {
                currentNode = currentNode.getNextSibling();
            }
        } else {
            return 0;
        }
    }

    @Override
    public Object readStruct(String field, NodeId dataTypeId) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            DataTypeCodec codec = context.getDataTypeManager()
                .getCodec(OpcUaDefaultXmlEncoding.ENCODING_NAME, dataTypeId);

            if (codec != null) {
                try {
                    currentNode = node.getFirstChild();
                    return codec.decode(context, this);
                } finally {
                    currentNode = node.getNextSibling();
                }
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered: " + dataTypeId
                );
            }
        } else {
            // TODO could be better if we passed Class<?> into method
            return null;
        }
    }

    @Override
    public Object readStruct(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        NodeId localDataTypeId = dataTypeId.toNodeId(context.getNamespaceTable())
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "namespace not registered: " + dataTypeId
            ));

        return readStruct(field, localDataTypeId);
    }

    @Override
    public Object readStruct(String field, DataTypeCodec codec) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            try {
                currentNode = node.getFirstChild();

                return codec.decode(context, this);
            } finally {
                currentNode = node.getNextSibling();
            }
        } else {
            // TODO could be better if we passed Class<?> into method
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] readArray(
        String field,
        Function<String, T> decoder,
        Class<T> clazz) throws UaSerializationException {

        if (currentNode(field)) {
            Node node = currentNode;

            List<Object> values = new ArrayList<>();
            Node listNode = node.getFirstChild();

            if (listNode != null) {
                NodeList children = listNode.getChildNodes();

                for (int i = 0; i < children.getLength(); i++) {
                    currentNode = children.item(i);

                    if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                        values.add(decoder.apply(currentNode.getLocalName()));
                    }
                }
            }

            try {
                checkArrayLength(values.size());

                Object array = Array.newInstance(clazz, values.size());
                for (int i = 0; i < values.size(); i++) {
                    Array.set(array, i, values.get(i));
                }

                return (T[]) array;
            } finally {
                currentNode = node.getNextSibling();
            }
        } else {
            return null;
        }
    }


    @Override
    public Boolean[] readBooleanArray(String field) throws UaSerializationException {
        return readArray(field, this::readBoolean, Boolean.class);
    }

    @Override
    public Byte[] readSByteArray(String field) throws UaSerializationException {
        return readArray(field, this::readSByte, Byte.class);
    }

    @Override
    public Short[] readInt16Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt16, Short.class);
    }

    @Override
    public Integer[] readInt32Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt32, Integer.class);
    }

    @Override
    public Long[] readInt64Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt64, Long.class);
    }

    @Override
    public UByte[] readByteArray(String field) throws UaSerializationException {
        return readArray(field, this::readByte, UByte.class);
    }

    @Override
    public UShort[] readUInt16Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt16, UShort.class);
    }

    @Override
    public UInteger[] readUInt32Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt32, UInteger.class);
    }

    @Override
    public ULong[] readUInt64Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt64, ULong.class);
    }

    @Override
    public Float[] readFloatArray(String field) throws UaSerializationException {
        return readArray(field, this::readFloat, Float.class);
    }

    @Override
    public Double[] readDoubleArray(String field) throws UaSerializationException {
        return readArray(field, this::readDouble, Double.class);
    }

    @Override
    public String[] readStringArray(String field) throws UaSerializationException {
        return readArray(field, this::readString, String.class);
    }

    @Override
    public DateTime[] readDateTimeArray(String field) throws UaSerializationException {
        return readArray(field, this::readDateTime, DateTime.class);
    }

    @Override
    public UUID[] readGuidArray(String field) throws UaSerializationException {
        return readArray(field, this::readGuid, UUID.class);
    }

    @Override
    public ByteString[] readByteStringArray(String field) throws UaSerializationException {
        return readArray(field, this::readByteString, ByteString.class);
    }

    @Override
    public XmlElement[] readXmlElementArray(String field) throws UaSerializationException {
        return readArray(field, this::readXmlElement, XmlElement.class);
    }

    @Override
    public NodeId[] readNodeIdArray(String field) throws UaSerializationException {
        return readArray(field, this::readNodeId, NodeId.class);
    }

    @Override
    public ExpandedNodeId[] readExpandedNodeIdArray(String field) throws UaSerializationException {
        return readArray(field, this::readExpandedNodeId, ExpandedNodeId.class);
    }

    @Override
    public StatusCode[] readStatusCodeArray(String field) throws UaSerializationException {
        return readArray(field, this::readStatusCode, StatusCode.class);
    }

    @Override
    public QualifiedName[] readQualifiedNameArray(String field) throws UaSerializationException {
        return readArray(field, this::readQualifiedName, QualifiedName.class);
    }

    @Override
    public LocalizedText[] readLocalizedTextArray(String field) throws UaSerializationException {
        return readArray(field, this::readLocalizedText, LocalizedText.class);

    }

    @Override
    public ExtensionObject[] readExtensionObjectArray(String field) throws UaSerializationException {
        return readArray(field, this::readExtensionObject, ExtensionObject.class);
    }

    @Override
    public DataValue[] readDataValueArray(String field) throws UaSerializationException {
        return readArray(field, this::readDataValue, DataValue.class);
    }

    @Override
    public Variant[] readVariantArray(String field) throws UaSerializationException {
        return readArray(field, this::readVariant, Variant.class);
    }

    @Override
    public DiagnosticInfo[] readDiagnosticInfoArray(String field) throws UaSerializationException {
        return readArray(field, this::readDiagnosticInfo, DiagnosticInfo.class);
    }

//    @Override
//    public <T extends Enum<?> & UaEnumeration> Object[] readEnumArray(
//        String field,
//        Class<T> enumType
//    ) throws UaSerializationException {
//
//        return readArray(field, s -> readEnum(s, enumType), enumType);
//    }
//
//    @Override
//    public UaEnumeration[] readEnumArray(String field, NodeId dataTypeId) throws UaSerializationException {
//        if (currentNode(field)) {
//            Node node = currentNode;
//
//            DataTypeCodec codec = context.getDataTypeManager().getEnumCodec(dataTypeId);
//
//            if (codec != null) {
//                List<UaEnumeration> values = new ArrayList<>();
//                Node listNode = node.getFirstChild();
//                NodeList children = listNode.getChildNodes();
//
//                for (int i = 0; i < children.getLength(); i++) {
//                    currentNode = children.item(i);
//
//                    if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
//                        values.add(readEnum(currentNode.getLocalName(), dataTypeId));
//                    }
//                }
//
//                try {
//                    Object array = Array.newInstance(codec.getType(), values.size());
//                    for (int i = 0; i < values.size(); i++) {
//                        Array.set(array, i, values.get(i));
//                    }
//
//                    return (UaEnumeration[]) array;
//                } finally {
//                    currentNode = node.getNextSibling();
//                }
//            } else {
//                throw new UaSerializationException(
//                    StatusCodes.Bad_DecodingError,
//                    "no codec registered: " + dataTypeId
//                );
//            }
//        } else {
//            return null;
//        }
//    }


    @Override
    public Integer[] readEnumArray(String field) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            List<Integer> values = new ArrayList<>();
            Node listNode = node.getFirstChild();
            NodeList children = listNode.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {
                currentNode = children.item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    values.add(readEnum(currentNode.getLocalName()));
                }
            }

            try {
                return values.toArray(Integer[]::new);
            } finally {
                currentNode = node.getNextSibling();
            }
        } else {
            return null;
        }
    }

    @Override
    public Object[] readStructArray(String field, NodeId dataTypeId) throws UaSerializationException {
        if (currentNode(field)) {
            Node node = currentNode;

            DataTypeCodec codec = context.getDataTypeManager()
                .getCodec(OpcUaDefaultXmlEncoding.ENCODING_NAME, dataTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered: " + dataTypeId
                );
            }

            List<Object> values = new ArrayList<>();
            Node listNode = node.getFirstChild();
            NodeList children = listNode.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {
                currentNode = children.item(i);

                if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                    values.add(readStruct(currentNode.getLocalName(), dataTypeId));
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
        } else {
            return null;
        }
    }

    @Override
    public Object[] readStructArray(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        NodeId dataTypeNodeId = dataTypeId.toNodeId(context.getNamespaceTable())
            .orElse(null);

        if (dataTypeNodeId != null) {
            return readStructArray(field, dataTypeNodeId);
        } else {
            if (dataTypeId.isLocal()) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "namespace not registered: " + dataTypeId.getNamespaceUri()
                );
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "ExpandedNodeId not local: " + dataTypeId
                );
            }
        }
    }

    private void checkArrayLength(int length) throws UaSerializationException {
        if (length > context.getEncodingLimits().getMaxMessageSize()) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                String.format(
                    "array length exceeds max message size (length=%s, max=%s)",
                    length, context.getEncodingLimits().getMaxMessageSize())
            );
        }
    }

    private static XmlElement nodeToXmlElement(Node node) throws UaSerializationException {
        try {
            StringWriter sw = new StringWriter();

            Transformer transformer = SecureXmlUtil.SHARED_TRANSFORMER_FACTORY.newTransformer();
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
