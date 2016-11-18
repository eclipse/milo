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

package org.eclipse.milo.opcua.stack.core.serialization.xml;

import java.io.OutputStream;
import java.io.Writer;
import java.util.Calendar;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.xml.bind.DatatypeConverter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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
import org.jooq.lambda.Unchecked;

public class XmlEncoder implements UaEncoder {

    private final Calendar calendar = Calendar.getInstance();
    private final XMLOutputFactory factory = XMLOutputFactory.newFactory();

    private volatile XMLStreamWriter streamWriter;

    public XmlEncoder() {
    }

    public XmlEncoder(OutputStream outputStream) throws XMLStreamException {
        setOutput(outputStream);
    }

    public XmlEncoder(Writer writer) throws XMLStreamException {
        setOutput(writer);
    }

    public XmlEncoder setOutput(OutputStream outputStream) throws XMLStreamException {
        streamWriter = factory.createXMLStreamWriter(outputStream);
        streamWriter.setPrefix("xsi", Namespaces.XML_SCHEMA_INSTANCE);
        streamWriter.setPrefix("tns", Namespaces.OPC_UA_XSD);

        return this;
    }

    public XmlEncoder setOutput(Writer writer) throws XMLStreamException {
        streamWriter = factory.createXMLStreamWriter(writer);
        streamWriter.setPrefix("xsi", Namespaces.XML_SCHEMA_INSTANCE);
        streamWriter.setPrefix("tns", Namespaces.OPC_UA_XSD);

        return this;
    }

    @Override
    public void encodeBoolean(String field, Boolean value) {
        if (value == null) value = false;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeSByte(String field, Byte value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeInt16(String field, Short value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeInt32(String field, Integer value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeInt64(String field, Long value) {
        if (value == null) value = 0L;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeByte(String field, UByte value) throws UaSerializationException {
        if (value == null) value = Unsigned.ubyte(0);

        writeValue(field, value.toString());
    }

    @Override
    public void encodeUInt16(String field, UShort value) throws UaSerializationException {
        if (value == null) value = Unsigned.ushort(0);

        writeValue(field, value.toString());
    }

    @Override
    public void encodeUInt32(String field, UInteger value) throws UaSerializationException {
        if (value == null) value = Unsigned.uint(0);

        writeValue(field, value.toString());
    }

    @Override
    public void encodeUInt64(String field, ULong value) throws UaSerializationException {
        if (value == null) value = Unsigned.ulong(0);

        writeValue(field, value.toString());
    }

    @Override
    public void encodeFloat(String field, Float value) {
        if (value == null) value = 0f;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeDouble(String field, Double value) {
        if (value == null) value = 0.0;

        writeValue(field, value.toString());
    }

    @Override
    public void encodeString(String field, String value) {
        if (value == null) value = "";

        writeValue(field, value);
    }

    @Override
    public void encodeDateTime(String field, DateTime value) {
        if (value == null) value = DateTime.MIN_VALUE;

        calendar.setTime(value.getJavaDate());

        writeValue(field, DatatypeConverter.printDateTime(calendar));
    }

    @Override
    public void encodeGuid(String field, UUID value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                w.writeStartElement("String");
                w.writeCharacters(value.toString());
                w.writeEndElement();
            }));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeByteString(String field, ByteString value) {
        if (value == null) value = ByteString.NULL_VALUE;

        if (value.isNotNull()) {
            byte[] bs = value.bytes();

            writeValue(field, DatatypeConverter.printBase64Binary(bs));
        } else {
            writeNilValue(field, "ByteString");
        }
    }

    @Override
    public void encodeXmlElement(String field, XmlElement value) {
        if (value == null) value = XmlElement.of("");

        writeValue(field, value.getFragment());
    }

    @Override
    public void encodeNodeId(String field, NodeId value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                w.writeStartElement("Identifier");
                w.writeCharacters(value.toParseableString());
                w.writeEndElement();
            }));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeExpandedNodeId(String field, ExpandedNodeId value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                w.writeStartElement("Identifier");
                w.writeCharacters(value.toParseableString());
                w.writeEndElement();
            }));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeStatusCode(String field, StatusCode value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> encodeStatusCode("Identifier", value)));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeQualifiedName(String field, QualifiedName value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                encodeUInt16("NamespaceIndex", value.getNamespaceIndex());
                encodeString("Name", value.getName());
            }));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeLocalizedText(String field, LocalizedText value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                encodeString("Locale", value.getLocale());
                encodeString("Text", value.getText());
            }));
        } else {
            if (field != null) {
                try {
                    streamWriter.writeEmptyElement(field);
                } catch (XMLStreamException e) {
                    throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
                }
            }
        }
    }

    @Override
    public void encodeExtensionObject(String field, ExtensionObject value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                encodeNodeId("TypeId", value.getEncodingTypeId());

                Object object = value.getEncodedBody(DataTypeEncoding.OPC_UA);

                if (object instanceof UaSerializable) {
                    UaSerializable serializable = (UaSerializable) object;

                    encodeSerializable("Body", serializable);
                } else if (object instanceof ByteString) {
                    ByteString byteString = (ByteString) object;

                    streamWriter.writeStartElement("Body");
                    encodeByteString("ByteString", byteString);
                    streamWriter.writeEndElement();
                } else if (object instanceof XmlElement) {
                    XmlElement xmlElement = (XmlElement) object;

                    encodeXmlElement("Body", xmlElement);
                }
            }));
        }
    }

    @Override
    public void encodeDataValue(String field, DataValue value) {

    }

    @Override
    public void encodeVariant(String field, Variant value) {

    }

    @Override
    public void encodeDiagnosticInfo(String field, DiagnosticInfo value) {

    }

    @Override
    public <T extends UaStructure> void encodeMessage(String field, T message) {

    }

    @Override
    public <T extends UaEnumeration> void encodeEnumeration(String field, T value) throws UaSerializationException {
        String s = value == null ? null : value.toString() + "_" + value.getValue();
        writeValue(field, s != null ? s : "");
    }

    @Override
    public <T extends UaSerializable> void encodeSerializable(String field, T value) {

    }

    @Override
    public <T> void encodeArray(String field, T[] values, BiConsumer<String, T> encoder) {

    }

    private void write(String field, Consumer<XMLStreamWriter> c) {
        try {
            if (field != null) {
                streamWriter.writeStartElement(field);
            }

            c.accept(streamWriter);

            if (field != null) {
                streamWriter.writeEndElement();
            }
        } catch (Throwable t) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, t);
        }
    }

    private void writeValue(String field, @Nonnull String value) {
        write(field, Unchecked.consumer(w -> w.writeCharacters(value)));
    }

    private void writeNilValue(String field, String name) {
        write(field, Unchecked.consumer(w -> {
            w.writeEmptyElement(name);
            w.writeAttribute(Namespaces.XML_SCHEMA_INSTANCE, "nil", "true");
        }));
    }

}
