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

package org.eclipse.milo.opcua.stack.core.types;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class BuiltinDataTypeDictionary {

    public static final String BINARY_NAMESPACE_URI = "http://opcfoundation.org/UA/";
    public static final String XML_NAMESPACE_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    public static OpcUaBinaryDataTypeDictionary getBinaryInstance() {
        return InstanceHolder.BINARY_INSTANCE;
    }

    public static OpcUaXmlDataTypeDictionary getXmlInstance() {
        return InstanceHolder.XML_INSTANCE;
    }

    @Nullable
    public static BuiltinDataTypeCodec<?> getBuiltinCodec(String typeName) {
        return BUILTIN_CODECS_BY_NAME.get(typeName);
    }

    @Nullable
    public static <T extends UaStructure> BuiltinDataTypeCodec<?> getBuiltinCodec(Class<T> clazz) {
        return BUILTIN_CODECS.get(clazz);
    }

    private static class InstanceHolder {

        private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

        private static final AtomicReference<OpcUaBinaryDataTypeDictionary> BINARY_INSTANCE_REF =
            new AtomicReference<>();

        private static final OpcUaBinaryDataTypeDictionary BINARY_INSTANCE = getBinaryInstance();

        private static final AtomicReference<OpcUaXmlDataTypeDictionary> XML_INSTANCE_REF = new AtomicReference<>();

        private static final OpcUaXmlDataTypeDictionary XML_INSTANCE = getXmlInstance();

        private static synchronized OpcUaBinaryDataTypeDictionary getBinaryInstance() {
            if (INITIALIZED.compareAndSet(false, true)) {
                BuiltinDataTypeDictionaryInitializer.initialize();
            }

            BINARY_INSTANCE_REF.compareAndSet(
                null,
                new OpcUaBinaryDataTypeDictionary(
                    BINARY_NAMESPACE_URI,
                    BINARY_CODECS_BY_DESC,
                    BINARY_CODECS_BY_ID
                )
            );

            return BINARY_INSTANCE_REF.get();
        }

        private static synchronized OpcUaXmlDataTypeDictionary getXmlInstance() {
            if (INITIALIZED.compareAndSet(false, true)) {
                BuiltinDataTypeDictionaryInitializer.initialize();
            }

            XML_INSTANCE_REF.compareAndSet(
                null,
                new OpcUaXmlDataTypeDictionary(
                    XML_NAMESPACE_URI,
                    XML_CODECS_BY_DESC,
                    XML_CODECS_BY_ID
                )
            );

            return XML_INSTANCE_REF.get();
        }
    }

    private static final ConcurrentMap<Class<? extends UaStructure>,
        BuiltinDataTypeCodec<? extends UaStructure>> BUILTIN_CODECS = Maps.newConcurrentMap();

    private static final ConcurrentMap<String,
        BuiltinDataTypeCodec<? extends UaStructure>> BUILTIN_CODECS_BY_NAME = Maps.newConcurrentMap();

    private static final ConcurrentMap<NodeId, OpcUaBinaryDataTypeCodec<?>> BINARY_CODECS_BY_ID
        = Maps.newConcurrentMap();
    private static final ConcurrentMap<String, OpcUaBinaryDataTypeCodec<?>> BINARY_CODECS_BY_DESC
        = Maps.newConcurrentMap();

    private static final ConcurrentMap<NodeId, OpcUaXmlDataTypeCodec<?>> XML_CODECS_BY_ID
        = Maps.newConcurrentMap();
    private static final ConcurrentMap<String, OpcUaXmlDataTypeCodec<?>> XML_CODECS_BY_DESC
        = Maps.newConcurrentMap();

    static synchronized <T extends UaStructure> void register(
        String typeName,
        Class<T> typeClazz,
        BuiltinDataTypeCodec<T> codec,
        NodeId binaryEncodingId,
        NodeId xmlEncodingId) {

        // The DataTypeDescription for a DataType defined in an XML DataTypeDictionary is an XPath expression that
        // can be used to locate the element in the document. We can cheat for the built-in types since we know what
        // it looks like...
        String xmlDescription = String.format("//xs:element[@name='%s']", typeName);

        registerBinaryCodec(codec.asBinaryCodec(), binaryEncodingId, typeName);
        registerXmlCodec(codec.asXmlCodec(), xmlEncodingId, xmlDescription);

        BUILTIN_CODECS.put(typeClazz, codec);
        BUILTIN_CODECS_BY_NAME.put(typeName, codec);
    }

    static synchronized <T> void registerBinaryCodec(
        OpcUaBinaryDataTypeCodec<T> codec, NodeId encodingId, String description) {

        BINARY_CODECS_BY_ID.put(encodingId, codec);
        BINARY_CODECS_BY_DESC.put(description, codec);
    }

    static synchronized <T> void registerXmlCodec(
        OpcUaXmlDataTypeCodec<T> codec, NodeId encodingId, String description) {

        XML_CODECS_BY_ID.put(encodingId, codec);
        XML_CODECS_BY_DESC.put(description, codec);
    }

}
