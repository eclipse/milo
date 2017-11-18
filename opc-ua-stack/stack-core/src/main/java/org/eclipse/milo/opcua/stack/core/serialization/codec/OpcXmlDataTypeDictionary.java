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

public class OpcXmlDataTypeDictionary implements DataTypeDictionary<OpcXmlDataTypeCodec<?>> {

    private final String namespaceUri;

    private final Map<String, OpcXmlDataTypeCodec<?>> codecsByDescription;
    private final Map<NodeId, OpcXmlDataTypeCodec<?>> codecsByEncodingId;

    public OpcXmlDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByEncodingId = Maps.newConcurrentMap();
    }

    public OpcXmlDataTypeDictionary(
        String namespaceUri,
        Map<String, OpcXmlDataTypeCodec<?>> byDescription,
        Map<NodeId, OpcXmlDataTypeCodec<?>> byEncodingId) {

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
    public OpcXmlDataTypeCodec<?> getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcXmlDataTypeCodec<?> getCodec(NodeId encodingId) {
        return codecsByEncodingId.get(encodingId);
    }

    @Override
    public void registerCodec(OpcXmlDataTypeCodec<?> codec, String description, NodeId encodingId) {
        codecsByDescription.put(description, codec);
        codecsByEncodingId.put(encodingId, codec);
    }

    @Override
    public Map<String, OpcXmlDataTypeCodec<?>> getCodecsByDescription() {
        return codecsByDescription;
    }

    @Override
    public Map<NodeId, OpcXmlDataTypeCodec<?>> getCodecsByEncodingId() {
        return codecsByEncodingId;
    }

}
