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

import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class OpcUaXmlDataTypeDictionary implements DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> {

    private final String namespaceUri;

    private final Map<String, OpcUaXmlDataTypeCodec<?>> codecsByDescription;
    private final Map<NodeId, OpcUaXmlDataTypeCodec<?>> codecsByEncodingId;

    public OpcUaXmlDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByEncodingId = Maps.newConcurrentMap();
    }

    public OpcUaXmlDataTypeDictionary(
        String namespaceUri,
        Map<String, OpcUaXmlDataTypeCodec<?>> byDescription,
        Map<NodeId, OpcUaXmlDataTypeCodec<?>> byEncodingId) {

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
    public OpcUaXmlDataTypeCodec<?> getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcUaXmlDataTypeCodec<?> getCodec(NodeId encodingId) {
        return codecsByEncodingId.get(encodingId);
    }

    @Override
    public void registerCodec(OpcUaXmlDataTypeCodec<?> codec, String description, NodeId encodingId) {
        codecsByDescription.put(description, codec);
        codecsByEncodingId.put(encodingId, codec);
    }

    @Override
    public Map<String, OpcUaXmlDataTypeCodec<?>> getCodecsByDescription() {
        return codecsByDescription;
    }

    @Override
    public Map<NodeId, OpcUaXmlDataTypeCodec<?>> getCodecsByEncodingId() {
        return codecsByEncodingId;
    }

}
