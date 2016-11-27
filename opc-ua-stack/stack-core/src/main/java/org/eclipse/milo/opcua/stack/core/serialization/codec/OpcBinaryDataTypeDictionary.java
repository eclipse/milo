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

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

public class OpcBinaryDataTypeDictionary implements DataTypeDictionary<OpcBinaryDataTypeCodec<?>> {

    private final String namespaceUri;

    private final ConcurrentMap<String, OpcBinaryDataTypeCodec<?>> codecs;

    public OpcBinaryDataTypeDictionary(String namespaceUri) {
        this(namespaceUri, Maps.newConcurrentMap());
    }

    public OpcBinaryDataTypeDictionary(
        String namespaceUri,
        ConcurrentMap<String, OpcBinaryDataTypeCodec<?>> codecs) {

        this.namespaceUri = namespaceUri;
        this.codecs = codecs;
    }

    @Override
    public String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public void registerCodec(OpcBinaryDataTypeCodec<?> codec, String name) {
        codecs.put(name, codec);
    }

    @Override
    public OpcBinaryDataTypeCodec<?> getCodec(String name) {
        return codecs.get(name);
    }

}
