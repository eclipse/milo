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

package org.eclipse.milo.opcua.stack.core.serialization.codec;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.xml.bind.DatatypeConverter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.UaSerializable;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;

public class OpcXmlStreamReader {

    private final XMLInputFactory factory = XMLInputFactory.newFactory();

    private volatile XMLStreamReader streamReader;

    private final int maxArrayLength;
    private final int maxStringLength;

    public OpcXmlStreamReader() {
        this(ChannelConfig.DEFAULT_MAX_ARRAY_LENGTH, ChannelConfig.DEFAULT_MAX_STRING_LENGTH);
    }

    public OpcXmlStreamReader(int maxArrayLength, int maxStringLength) {
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
    }

    public OpcXmlStreamReader(InputStream inputStream) throws XMLStreamException {
        this();
        setInput(inputStream);
    }

    public OpcXmlStreamReader(Reader reader) throws XMLStreamException {
        this();
        setInput(reader);
    }

    public OpcXmlStreamReader setInput(InputStream inputStream) throws XMLStreamException {
        streamReader = factory.createXMLStreamReader(inputStream);

        return this;
    }

    public OpcXmlStreamReader setInput(Reader reader) throws XMLStreamException {
        streamReader = factory.createXMLStreamReader(reader);

        return this;
    }

    public XMLStreamReader getStreamReader() {
        return streamReader;
    }

    public void skipElement() throws XMLStreamException {
        streamReader.nextTag();
    }


    public Boolean readBoolean(String field) throws UaSerializationException {
        return parseElement(field, Boolean::valueOf);
    }


    public Byte readSByte(String field) throws UaSerializationException {
        return parseElement(field, Byte::parseByte);
    }


    public Short readInt16(String field) throws UaSerializationException {
        return parseElement(field, Short::parseShort);
    }

    public Integer readInt32(String field) throws UaSerializationException {
        return parseElement(field, Integer::parseInt);
    }


    public Long readInt64(String field) throws UaSerializationException {
        return parseElement(field, Long::parseLong);
    }


    public UByte readByte(String field) throws UaSerializationException {
        return parseElement(field, s -> Unsigned.ubyte(Short.parseShort(s)));
    }


    public UShort readUInt16(String field) throws UaSerializationException {
        return parseElement(field, s -> Unsigned.ushort(Integer.parseInt(s)));
    }


    public UInteger readUInt32(String field) throws UaSerializationException {
        return parseElement(field, s -> Unsigned.uint(Long.parseLong(s)));
    }


    public ULong readUInt64(String field) throws UaSerializationException {
        return parseElement(field, s -> Unsigned.ulong(Long.parseUnsignedLong(s)));
    }


    public Float readFloat(String field) throws UaSerializationException {
        return parseElement(field, Float::parseFloat);
    }


    public Double readDouble(String field) throws UaSerializationException {
        return parseElement(field, Double::parseDouble);
    }


    public String readString(String field) throws UaSerializationException {
        return parseElement(field, content -> content);
    }


    public DateTime readDateTime(String field) throws UaSerializationException {
        return parseElement(field, content -> {
            Calendar calendar = DatatypeConverter.parseDateTime(content);

            return new DateTime(calendar.getTime());
        });
    }


    public UUID readGuid(String field) throws UaSerializationException {
        requireNextStartElement(field);

        UUID uuid;

        if (nextStartElement("String")) {
            try {
                String text = streamReader.getElementText();
                uuid = UUID.fromString(text);

                // getElementText() advances to end of element

                requireNextEndElement(field);
            } catch (XMLStreamException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            uuid = new UUID(0, 0);

            requireCurrentElement(field);
        }

        return uuid;
    }


    public ByteString readByteString(String field) throws UaSerializationException {
        return parseNillableElement(field, content -> {
            if (content != null) {
                byte[] bs = DatatypeConverter.parseBase64Binary(content);

                return ByteString.of(bs);
            } else {
                return ByteString.NULL_VALUE;
            }
        });
    }


    public XmlElement readXmlElement(String field) {
        return null;
    }


    public NodeId readNodeId(String field) throws UaSerializationException {
        requireNextStartElement(field);

        NodeId nodeId;

        if (nextStartElement("Identifier")) {
            try {
                String text = streamReader.getElementText();
                nodeId = NodeId.parse(text);

                // getElementText() advances to end of element

                requireNextEndElement(field);
            } catch (XMLStreamException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            nodeId = NodeId.NULL_VALUE;
        }

        return nodeId;
    }


    public ExpandedNodeId readExpandedNodeId(String field) {
        requireNextStartElement(field);

        ExpandedNodeId nodeId;

        if (nextStartElement("Identifier")) {
            try {
                String text = streamReader.getElementText();
                nodeId = ExpandedNodeId.parse(text);

                requireNextEndElement(field);
            } catch (XMLStreamException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            nodeId = ExpandedNodeId.NULL_VALUE;
        }

        return nodeId;
    }


    public StatusCode readStatusCode(String field) {
        if (nextStartElement(field)) {
            UInteger value = Unsigned.uint(0);

            if (nextStartElement("Code")) {
                value = readUInt32(null);
                requireNextEndElement("Code");
            }

            requireNextEndElement(field);

            return new StatusCode(value);
        } else {
            return new StatusCode(0);
        }
    }


    public QualifiedName readQualifiedName(String field) {
        if (nextStartElement(field)) {
            UShort namespaceIndex = Unsigned.ushort(0);
            String name = "";

            if (nextStartElement("NamespaceIndex")) {
                namespaceIndex = readUInt16(null);
                requireNextEndElement("NamespaceIndex");
            }

            if (nextStartElement("Name")) {
                name = readString(null);
                requireNextEndElement("Name");
            }

            requireNextEndElement(field);

            return new QualifiedName(namespaceIndex, name);
        } else {
            return QualifiedName.NULL_VALUE;
        }
    }


    public LocalizedText readLocalizedText(String field) {
        if (nextStartElement(field)) {
            String locale = LocalizedText.NULL_VALUE.getLocale();
            String text = LocalizedText.NULL_VALUE.getText();

            if (nextStartElement("Locale")) {
                locale = readElementText().orElse(LocalizedText.NULL_VALUE.getLocale());
            }

            if (nextStartElement("Text")) {
                text = readElementText().orElse(LocalizedText.NULL_VALUE.getText());
            }

            requireNextEndElement(field);

            return new LocalizedText(locale, text);
        } else {
            return LocalizedText.NULL_VALUE;
        }
    }


    public ExtensionObject readExtensionObject(String field) {
        if (nextStartElement(field)) {
            NodeId encodingTypeId = NodeId.NULL_VALUE;
            Object body = null;

            if (nextStartElement("TypeId")) {
                encodingTypeId = readNodeId(null);

                requireNextEndElement("TypeId");
            }

            if (nextStartElement("Body")) {
                try {
                    body = readExtensionObjectBody();
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }

                requireNextEndElement("Body");
            }

            requireNextEndElement(field);

            if (body instanceof XmlElement) {
                return new ExtensionObject((XmlElement) body, encodingTypeId);
            } else if (body instanceof ByteString) {
                return new ExtensionObject((ByteString) body, encodingTypeId);
            } else {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                    "unrecognized ExtensionObject body: " + body);
            }
        } else {
            return new ExtensionObject(ByteString.NULL_VALUE, NodeId.NULL_VALUE);
        }
    }

    private Object readExtensionObjectBody() throws XMLStreamException {
        String bodyStartElement = getNextStartElement();

        if ("ByteString".equals(bodyStartElement)) {
            ByteString byteString = readByteString(null);

            requireNextEndElement("ByteString");

            return byteString;
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append("<").append(bodyStartElement).append(">");

            while (streamReader.hasNext()) {
                streamReader.next();

                if (streamReader.hasText()) {
                    String text = streamReader.getText();

                    builder.append(text);
                } else if (streamReader.hasName()) {
                    String name = streamReader.getLocalName();

                    builder.append("<");
                    if (streamReader.isEndElement()) {
                        builder.append("/");
                    }
                    builder.append(name).append(">");

                    if (bodyStartElement.equals(name)) {
                        break;
                    }
                }
            }

            String bodyXml = builder.toString();

            return new XmlElement(bodyXml);
        }
    }


    public DataValue readDataValue(String field) {
        if (nextStartElement(field)) {
            Variant value = Variant.NULL_VALUE;
            StatusCode statusCode = new StatusCode(0);
            DateTime sourceTimestamp = DateTime.MIN_VALUE;
            UShort sourcePicoseconds = Unsigned.ushort(0);
            DateTime serverTimestamp = DateTime.MIN_VALUE;
            UShort serverPicoseconds = Unsigned.ushort(0);

            if (nextStartElement("Value")) {
                value = readVariant(null);

                requireNextEndElement("Value");
            }

            if (nextStartElement("StatusCode")) {
                statusCode = readStatusCode(null);

                requireNextEndElement("StatusCode");
            }

            if (nextStartElement("SourceTimestamp")) {
                sourceTimestamp = readDateTime(null);

                requireNextEndElement("SourceTimestamp");
            }

            if (nextStartElement("SourcePicoseconds")) {
                sourcePicoseconds = readUInt16(null);

                requireNextEndElement("SourcePicoseconds");
            }

            if (nextStartElement("ServerTimestamp")) {
                serverTimestamp = readDateTime(null);

                requireNextEndElement("ServerTimestamp");
            }

            if (nextStartElement("ServerPicoseconds")) {
                serverPicoseconds = readUInt16(null);

                requireNextEndElement("ServerPicoseconds");
            }

            requireNextEndElement(field);

            return new DataValue(
                value, statusCode,
                sourceTimestamp, sourcePicoseconds,
                serverTimestamp, serverPicoseconds);
        } else {
            return new DataValue(Variant.NULL_VALUE);
        }
    }


    public Variant readVariant(String field) {
        if (nextStartElement(field)) {
            Object value = null;

            if (nextStartElement("Value")) {
                try {
                    value = readVariantValue();
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }

                requireNextEndElement("Value");
            }

            requireNextEndElement(field);

            return new Variant(value);
        } else {
            return Variant.NULL_VALUE;
        }
    }

    public Object readVariantValue() throws XMLStreamException {
        String valueStartElement = getNextStartElement();

        if (valueStartElement.startsWith("ListOf")) {
            String valueType = valueStartElement.substring(6);
            List<Object> values = new ArrayList<>();

            while (true) {
                if (nextStartElement(valueType)) {
                    values.add(readBuiltinType(valueType));

                    requireNextEndElement(valueType);
                } else {
                    break;
                }
            }

            if (values.size() > 0) {
                Class<?> c = values.get(0).getClass();
                Object a = Array.newInstance(c, values.size());
                for (int i = 0; i < values.size(); i++) {
                    Array.set(a, i, values.get(i));
                }
                return a;
            } else {
                return null;
            }
        } else {
            Object value = readBuiltinType(valueStartElement);

            requireNextEndElement(valueStartElement);

            return value;
        }
    }

    private Object readBuiltinType(String type) throws UaSerializationException {
        switch (type) {
            case "Boolean":
                return readBoolean(null);
            case "SByte":
                return readSByte(null);
            case "Byte":
                return readByte(null);
            case "Int16":
                return readInt16(null);
            case "UInt16":
                return readUInt16(null);
            case "Int32":
                return readInt32(null);
            case "UInt32":
                return readUInt32(null);
            case "Int64":
                return readInt64(null);
            case "UInt64":
                return readUInt64(null);
            case "Float":
                return readFloat(null);
            case "Double":
                return readDouble(null);
            case "String":
                return readString(null);
            case "DateTime":
                return readDateTime(null);
            case "Guid":
                return readGuid(null);
            case "ByteString":
                return readByteString(null);
            case "XmlElement":
                return readXmlElement(null);
            case "NodeId":
                return readNodeId(null);
            case "ExpandedNodeId":
                return readExpandedNodeId(null);
            case "StatusCode":
                return readStatusCode(null);
            case "QualifiedName":
                return readQualifiedName(null);
            case "LocalizedText":
                return readLocalizedText(null);
            case "ExtensionObject":
                return readExtensionObject(null);
            case "DataValue":
                return readDataValue(null);
            case "Variant":
                return readVariant(null);
            case "DiagnosticInfo":
                return readDiagnosticInfo(null);
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "unknown builtin type: " + type);
        }
    }


    public DiagnosticInfo readDiagnosticInfo(String field) {
        int symbolicId = -1;
        int namespaceUri = -1;
        int localizedText = -1;
        int locale = -1;
        String additionalInfo = null;
        StatusCode innerStatusCode = null;
        DiagnosticInfo innerDiagnosticInfo = null;

        if (nextStartElement("SymbolicId")) {
            symbolicId = readInt32(null);

            requireNextEndElement("SymbolicId");
        }

        if (nextStartElement("NamespaceUri")) {
            namespaceUri = readInt32(null);

            requireNextEndElement("NamespaceUri");
        }

        if (nextStartElement("LocalizedText")) {
            localizedText = readInt32(null);

            requireNextEndElement("LocalizedText");
        }

        if (nextStartElement("Locale")) {
            locale = readInt32(null);

            requireNextEndElement("Locale");
        }

        if (nextStartElement("AdditionalInfo")) {
            additionalInfo = readString(null);

            requireNextEndElement("AdditionalInfo");
        }

        if (nextStartElement("InnerStatusCode")) {
            innerStatusCode = readStatusCode(null);

            requireNextEndElement("InnerStatusCode");
        }

        if (nextStartElement("InnerDiagnosticInfo")) {
            innerDiagnosticInfo = readDiagnosticInfo(null);

            requireNextEndElement("InnerDiagnosticInfo");
        }

        return new DiagnosticInfo(
            namespaceUri, symbolicId, locale, localizedText,
            additionalInfo, innerStatusCode, innerDiagnosticInfo);
    }


    public <T extends UaStructure> T readMessage(String field) {
        return null;
    }

    @SuppressWarnings("unchecked")

    public <T extends UaEnumeration> T readEnumeration(String field, Class<T> clazz) throws UaSerializationException {
        return parseElement(field, content -> {
            int separator = content.lastIndexOf('_');
            String name = separator < 1 ? content : content.substring(0, separator);

            Object enumInstance = Enum.valueOf(clazz.asSubclass(Enum.class), name);

            return clazz.cast(enumInstance);
        });
    }


    public <T extends UaSerializable> T readSerializable(String field, Class<T> clazz) {
        return null;
    }


    public <T> T readStructuredType(
        String field,
        String namespaceUri,
        Class<T> typeClass) throws UaSerializationException {

        return null;
    }


    public Object readStructuredType(
        String field,
        String namespaceUri,
        String typeName) throws UaSerializationException {

        return null;
    }


    public <T> T[] readArray(String field, Function<String, T> reader, Class<T> clazz) {
        return null;
    }


    public <T> T[] readArray(String field, BiFunction<String, Class<T>, T> reader, Class<T> clazz) {
        return null;
    }


    private <T> T parseElement(String element, Function<String, T> parser) throws UaSerializationException {
        requireNextStartElement(element);

        T parsed = parser.apply(readCharacterContent());

        requireNextEndElement(element);

        return parsed;
    }

    private <T> T parseNillableElement(String element, Function<String, T> parser) throws UaSerializationException {
        requireNextStartElement(element);

        T parsed;

        String nilValue = streamReader.getAttributeValue(Namespaces.XML_SCHEMA_INSTANCE, "nil");

        if (nilValue != null && Boolean.parseBoolean(nilValue)) {
            parsed = parser.apply(null);
        } else {
            parsed = parser.apply(readCharacterContent());
        }

        requireNextEndElement(element);

        return parsed;
    }

    private String getNextStartElement() throws UaSerializationException {
        try {
            streamReader.nextTag();

            if (streamReader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                return streamReader.getLocalName();
            } else {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "expected start element");
            }
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private boolean nextStartElement(String element) throws UaSerializationException {
        try {
            if (element == null || element.isEmpty()) return true;

            streamReader.nextTag();

            return streamReader.getEventType() == XMLStreamConstants.START_ELEMENT &&
                element.equals(streamReader.getLocalName());
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private void requireNextStartElement(String element) throws UaSerializationException {
        if (!nextStartElement(element)) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "start of element '" + element + "' not found");
        }
    }

    private boolean nextEndElement(String element) throws UaSerializationException {
        try {
            if (element == null || element.isEmpty()) return true;

            streamReader.nextTag();

            return streamReader.getEventType() == XMLStreamConstants.END_ELEMENT &&
                element.equals(streamReader.getLocalName());
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private void requireNextEndElement(String element) throws UaSerializationException {
        if (!nextEndElement(element)) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "end of element '" + element + "' not found");
        }
    }

    private boolean currentElement(String element) {
        return element.equals(streamReader.getLocalName());
    }

    private void requireCurrentElement(String element) throws UaSerializationException {
        if (!currentElement(element)) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError,
                "expected current element '" + element + "'");
        }
    }

    private String readCharacterContent() throws UaSerializationException {
        try {
            while (streamReader.hasNext()) {
                streamReader.next();

                if (streamReader.getEventType() == XMLStreamReader.CHARACTERS) {
                    return streamReader.getText();
                }
            }

            throw new UaSerializationException(StatusCodes.Bad_DecodingError, "no character content found");
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private Optional<String> readElementText() throws UaSerializationException {
        try {
            if (!streamReader.isStartElement() && !streamReader.isEndElement()) {
                return Optional.empty();
            }
            return Optional.ofNullable(streamReader.getElementText());
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
