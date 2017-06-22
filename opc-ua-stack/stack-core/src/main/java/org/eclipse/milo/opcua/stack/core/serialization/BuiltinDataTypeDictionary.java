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

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;

public class BuiltinDataTypeDictionary {

    public static final String BINARY_NAMESPACE_URI = "http://opcfoundation.org/UA/";
    public static final String XML_NAMESPACE_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    public static OpcBinaryDataTypeDictionary getBinaryInstance() {
        return InstanceHolder.BINARY_INSTANCE;
    }

    public static OpcXmlDataTypeDictionary getXmlInstance() {
        return InstanceHolder.XML_INSTANCE;
    }

    private static class InstanceHolder {

        private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

        private static final AtomicReference<OpcBinaryDataTypeDictionary> BINARY_INSTANCE_REF = new AtomicReference<>();
        private static final OpcBinaryDataTypeDictionary BINARY_INSTANCE = getBinaryInstance();

        private static final AtomicReference<OpcXmlDataTypeDictionary> XML_INSTANCE_REF = new AtomicReference<>();
        private static final OpcXmlDataTypeDictionary XML_INSTANCE = getXmlInstance();

        private static synchronized OpcBinaryDataTypeDictionary getBinaryInstance() {
            if (INITIALIZED.compareAndSet(false, true)) {
                BuiltinDataTypeDictionaryInitializer.initialize();
                registerBuiltinTypeCodecs();
            }

            BINARY_INSTANCE_REF.compareAndSet(
                null,
                new OpcBinaryDataTypeDictionary(
                    BINARY_NAMESPACE_URI,
                    BINARY_CODECS_BY_DESC,
                    BINARY_CODECS_BY_ID
                )
            );

            return BINARY_INSTANCE_REF.get();
        }

        private static synchronized OpcXmlDataTypeDictionary getXmlInstance() {
            if (INITIALIZED.compareAndSet(false, true)) {
                BuiltinDataTypeDictionaryInitializer.initialize();
                registerBuiltinTypeCodecs();
            }

            XML_INSTANCE_REF.compareAndSet(
                null,
                new OpcXmlDataTypeDictionary(
                    XML_NAMESPACE_URI,
                    XML_CODECS_BY_DESC,
                    XML_CODECS_BY_ID
                )
            );

            return XML_INSTANCE_REF.get();
        }
    }

    private static final ConcurrentMap<NodeId, OpcBinaryDataTypeCodec<?>> BINARY_CODECS_BY_ID
        = Maps.newConcurrentMap();
    private static final ConcurrentMap<String, OpcBinaryDataTypeCodec<?>> BINARY_CODECS_BY_DESC
        = Maps.newConcurrentMap();

    private static final ConcurrentMap<NodeId, OpcXmlDataTypeCodec<?>> XML_CODECS_BY_ID
        = Maps.newConcurrentMap();
    private static final ConcurrentMap<String, OpcXmlDataTypeCodec<?>> XML_CODECS_BY_DESC
        = Maps.newConcurrentMap();

    static synchronized <T> void register(
        String typeName,
        NodeId binaryEncodingId,
        OpcBinaryDataTypeCodec<T> binaryTypeCodec,
        NodeId xmlEncodingId,
        OpcXmlDataTypeCodec<T> xmlTypeCodec) {

        // The DataTypeDescription for a DataType defined in an XML DataTypeDictionary is an XPath expression that
        // can be used to locate the element in the document. We can cheat for the built-in types since we know what
        // it looks like...
        String xmlDescription = String.format("//xs:element[@name='%s']", typeName);

        registerBinaryCodec(binaryTypeCodec, binaryEncodingId, typeName);
        registerXmlCodec(xmlTypeCodec, xmlEncodingId, xmlDescription);
    }

    static synchronized <T> void registerBinaryCodec(
        OpcBinaryDataTypeCodec<T> codec, NodeId encodingId, String description) {

        BINARY_CODECS_BY_ID.put(encodingId, codec);
        BINARY_CODECS_BY_DESC.put(description, codec);
    }

    static synchronized <T> void registerXmlCodec(
        OpcXmlDataTypeCodec<T> codec, NodeId encodingId, String description) {

        XML_CODECS_BY_ID.put(encodingId, codec);
        XML_CODECS_BY_DESC.put(description, codec);
    }

    private static void registerBuiltinTypeCodecs() {
        // These built-in types are present in the OPC UA binary type dictionary and are defined here so that
        // concrete implementations of these types can be provided since built-in types aren't auto-generated.

        // TODO non-structured types as well?

        register(
            "XmlElement",
            Identifiers.XmlElement,
            new OpcBinaryDataTypeCodec<XmlElement>() {
                @Override
                public XmlElement decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readXmlElement();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    XmlElement value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeXmlElement(value);
                }
            },
            Identifiers.XmlElement,
            new OpcXmlDataTypeCodec<XmlElement>() {
                @Override
                public XmlElement decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readXmlElement(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    XmlElement value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeXmlElement(null, value);
                }
            }
        );

        register(
            "NodeId",
            Identifiers.NodeId,
            new OpcBinaryDataTypeCodec<NodeId>() {
                @Override
                public NodeId decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readNodeId();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    NodeId value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeNodeId(value);
                }
            },
            Identifiers.NodeId,
            new OpcXmlDataTypeCodec<NodeId>() {
                @Override
                public NodeId decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readNodeId(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    NodeId value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeNodeId(null, value);
                }
            }
        );

        register(
            "ExpandedNodeId",
            Identifiers.ExpandedNodeId,
            new OpcBinaryDataTypeCodec<ExpandedNodeId>() {
                @Override
                public ExpandedNodeId decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readExpandedNodeId();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    ExpandedNodeId value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeExpandedNodeId(value);
                }
            },
            Identifiers.ExpandedNodeId,
            new OpcXmlDataTypeCodec<ExpandedNodeId>() {
                @Override
                public ExpandedNodeId decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readExpandedNodeId(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    ExpandedNodeId value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeExpandedNodeId(null, value);
                }
            }
        );

        register(
            "StatusCode",
            Identifiers.StatusCode,
            new OpcBinaryDataTypeCodec<StatusCode>() {
                @Override
                public StatusCode decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readStatusCode();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    StatusCode value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeStatusCode(value);
                }
            },
            Identifiers.StatusCode,
            new OpcXmlDataTypeCodec<StatusCode>() {
                @Override
                public StatusCode decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readStatusCode(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    StatusCode value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeStatusCode(null, value);
                }
            }
        );

        register(
            "QualifiedName",
            Identifiers.QualifiedName,
            new OpcBinaryDataTypeCodec<QualifiedName>() {
                @Override
                public QualifiedName decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readQualifiedName();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    QualifiedName value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeQualifiedName(value);
                }
            },
            Identifiers.QualifiedName,
            new OpcXmlDataTypeCodec<QualifiedName>() {
                @Override
                public QualifiedName decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readQualifiedName(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    QualifiedName value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeQualifiedName(null, value);
                }
            }
        );

        register(
            "LocalizedText",
            Identifiers.LocalizedText,
            new OpcBinaryDataTypeCodec<LocalizedText>() {
                @Override
                public LocalizedText decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readLocalizedText();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    LocalizedText value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeLocalizedText(value);
                }
            },
            Identifiers.LocalizedText,
            new OpcXmlDataTypeCodec<LocalizedText>() {
                @Override
                public LocalizedText decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readLocalizedText(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    LocalizedText value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeLocalizedText(null, value);
                }
            }
        );

        register(
            "DataValue",
            Identifiers.DataValue,
            new OpcBinaryDataTypeCodec<DataValue>() {
                @Override
                public DataValue decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readDataValue();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    DataValue value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeDataValue(value);
                }
            },
            Identifiers.DataValue,
            new OpcXmlDataTypeCodec<DataValue>() {
                @Override
                public DataValue decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readDataValue(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    DataValue value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeDataValue(null, value);
                }
            }
        );

        register(
            "Variant",
            Identifiers.BaseDataType,
            new OpcBinaryDataTypeCodec<Variant>() {
                @Override
                public Variant decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readVariant();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    Variant value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeVariant(value);
                }
            },
            Identifiers.BaseDataType,
            new OpcXmlDataTypeCodec<Variant>() {
                @Override
                public Variant decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readVariant(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    Variant value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeVariant(null, value);
                }
            }
        );

        register(
            "DiagnosticInfo",
            Identifiers.DiagnosticInfo,
            new OpcBinaryDataTypeCodec<DiagnosticInfo>() {
                @Override
                public DiagnosticInfo decode(
                    SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

                    return reader.readDiagnosticInfo();
                }

                @Override
                public void encode(
                    SerializationContext context,
                    DiagnosticInfo value,
                    OpcBinaryStreamWriter writer) throws UaSerializationException {

                    writer.writeDiagnosticInfo(value);
                }
            },
            Identifiers.DiagnosticInfo,
            new OpcXmlDataTypeCodec<DiagnosticInfo>() {
                @Override
                public DiagnosticInfo decode(
                    SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

                    return reader.readDiagnosticInfo(null);
                }

                @Override
                public void encode(
                    SerializationContext context,
                    DiagnosticInfo value,
                    OpcXmlStreamWriter writer) throws UaSerializationException {

                    writer.writeDiagnosticInfo(null, value);
                }
            }
        );
    }

}
