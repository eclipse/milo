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

import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class OpcBinaryDataTypeDictionary implements DataTypeDictionary<OpcBinaryDataTypeCodec<?>> {

    private final String namespaceUri;

    private final Map<String, OpcBinaryDataTypeCodec<?>> codecsByDescription;
    private final Map<NodeId, OpcBinaryDataTypeCodec<?>> codecsByEncodingId;

    public OpcBinaryDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByEncodingId = Maps.newConcurrentMap();
    }

    public OpcBinaryDataTypeDictionary(
        String namespaceUri,
        Map<String, OpcBinaryDataTypeCodec<?>> byDescription,
        Map<NodeId, OpcBinaryDataTypeCodec<?>> byEncodingId) {

        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByDescription.putAll(byDescription);

        codecsByEncodingId = Maps.newConcurrentMap();
        codecsByEncodingId.putAll(byEncodingId);
    }

    @Override
    public String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public OpcBinaryDataTypeCodec<?> getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcBinaryDataTypeCodec<?> getCodec(NodeId encodingId) {
        return codecsByEncodingId.get(encodingId);
    }

    @Override
    public void registerCodec(OpcBinaryDataTypeCodec<?> codec, String description, NodeId encodingId) {
        codecsByDescription.put(description, codec);
        codecsByEncodingId.put(encodingId, codec);
    }

    @Override
    public Map<String, OpcBinaryDataTypeCodec<?>> getCodecsByDescription() {
        return codecsByDescription;
    }

    @Override
    public Map<NodeId, OpcBinaryDataTypeCodec<?>> getCodecsByEncodingId() {
        return codecsByEncodingId;
    }
    
}
