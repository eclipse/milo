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
import org.jooq.lambda.Unchecked;

public class OpcXmlStreamWriter {

    private final Calendar calendar = Calendar.getInstance();
    private final XMLOutputFactory factory = XMLOutputFactory.newFactory();

    private volatile XMLStreamWriter streamWriter;

    private final int maxArrayLength;
    private final int maxStringLength;

    public OpcXmlStreamWriter() {
        this(ChannelConfig.DEFAULT_MAX_ARRAY_LENGTH, ChannelConfig.DEFAULT_MAX_STRING_LENGTH);
    }

    public OpcXmlStreamWriter(int maxArrayLength, int maxStringLength) {
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
    }

    public OpcXmlStreamWriter(OutputStream outputStream) throws XMLStreamException {
        this();
        setOutput(outputStream);
    }

    public OpcXmlStreamWriter(Writer writer) throws XMLStreamException {
        this();
        setOutput(writer);
    }

    public OpcXmlStreamWriter setOutput(OutputStream outputStream) throws XMLStreamException {
        streamWriter = factory.createXMLStreamWriter(outputStream);
        streamWriter.setPrefix("xsi", Namespaces.XML_SCHEMA_INSTANCE);
        streamWriter.setPrefix("tns", Namespaces.OPC_UA_XSD);

        return this;
    }

    public OpcXmlStreamWriter setOutput(Writer writer) throws XMLStreamException {
        streamWriter = factory.createXMLStreamWriter(writer);
        streamWriter.setPrefix("xsi", Namespaces.XML_SCHEMA_INSTANCE);
        streamWriter.setPrefix("tns", Namespaces.OPC_UA_XSD);

        return this;
    }

    public XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }

    public void writeBoolean(String field, Boolean value) {
        if (value == null) value = false;

        writeValue(field, value.toString());
    }


    public void writeSByte(String field, Byte value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }


    public void writeInt16(String field, Short value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }


    public void writeInt32(String field, Integer value) {
        if (value == null) value = 0;

        writeValue(field, value.toString());
    }


    public void writeInt64(String field, Long value) {
        if (value == null) value = 0L;

        writeValue(field, value.toString());
    }


    public void writeByte(String field, UByte value) throws UaSerializationException {
        if (value == null) value = Unsigned.ubyte(0);

        writeValue(field, value.toString());
    }


    public void writeUInt16(String field, UShort value) throws UaSerializationException {
        if (value == null) value = Unsigned.ushort(0);

        writeValue(field, value.toString());
    }


    public void writeUInt32(String field, UInteger value) throws UaSerializationException {
        if (value == null) value = Unsigned.uint(0);

        writeValue(field, value.toString());
    }


    public void writeUInt64(String field, ULong value) throws UaSerializationException {
        if (value == null) value = Unsigned.ulong(0);

        writeValue(field, value.toString());
    }


    public void writeFloat(String field, Float value) {
        if (value == null) value = 0f;

        writeValue(field, value.toString());
    }


    public void writeDouble(String field, Double value) {
        if (value == null) value = 0.0;

        writeValue(field, value.toString());
    }


    public void writeString(String field, String value) {
        if (value == null) value = "";

        writeValue(field, value);
    }


    public void writeDateTime(String field, DateTime value) {
        if (value == null) value = DateTime.MIN_VALUE;

        calendar.setTime(value.getJavaDate());

        writeValue(field, DatatypeConverter.printDateTime(calendar));
    }


    public void writeGuid(String field, UUID value) {
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


    public void writeByteString(String field, ByteString value) {
        if (value == null) value = ByteString.NULL_VALUE;

        if (value.isNotNull()) {
            byte[] bs = value.bytes();

            writeValue(field, DatatypeConverter.printBase64Binary(bs));
        } else {
            writeNilValue(field, "ByteString");
        }
    }


    public void writeXmlElement(String field, XmlElement value) {
        if (value == null) value = XmlElement.of("");

        writeValue(field, value.getFragment());
    }


    public void writeNodeId(String field, NodeId value) {
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


    public void writeExpandedNodeId(String field, ExpandedNodeId value) {
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


    public void writeStatusCode(String field, StatusCode value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> writeStatusCode("Identifier", value)));
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


    public void writeQualifiedName(String field, QualifiedName value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                writeUInt16("NamespaceIndex", value.getNamespaceIndex());
                writeString("Name", value.getName());
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


    public void writeLocalizedText(String field, LocalizedText value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                writeString("Locale", value.getLocale());
                writeString("Text", value.getText());
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


    public void writeExtensionObject(String field, ExtensionObject value) {
        if (value != null) {
            write(field, Unchecked.consumer(w -> {
                writeNodeId("TypeId", value.getEncodingTypeId());

                Object object = value.getEncoded();

                if (object instanceof UaSerializable) {
                    UaSerializable serializable = (UaSerializable) object;

                    writeSerializable("Body", serializable);
                } else if (object instanceof ByteString) {
                    ByteString byteString = (ByteString) object;

                    streamWriter.writeStartElement("Body");
                    writeByteString("ByteString", byteString);
                    streamWriter.writeEndElement();
                } else if (object instanceof XmlElement) {
                    XmlElement xmlElement = (XmlElement) object;

                    writeXmlElement("Body", xmlElement);
                }
            }));
        }
    }


    public void writeDataValue(String field, DataValue value) {

    }


    public void writeVariant(String field, Variant value) {

    }


    public void writeDiagnosticInfo(String field, DiagnosticInfo value) {

    }


    public <T extends UaStructure> void writeMessage(String field, T message) {

    }


    public <T extends UaEnumeration> void writeEnumeration(String field, T value) throws UaSerializationException {
        String s = value == null ? null : value.toString() + "_" + value.getValue();
        writeValue(field, s != null ? s : "");
    }


    public <T extends UaSerializable> void writeSerializable(String field, T value) {

    }


    public <T> void writeStructuredType(
        String field,
        T value,
        String namespaceUri) throws UaSerializationException {

    }


    public void writeStructuredType(
        String field,
        Object value,
        String namespaceUri,
        String typeName) throws UaSerializationException {

    }


    public <T> void writeArray(String field, T[] values, BiConsumer<String, T> writer) {

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
