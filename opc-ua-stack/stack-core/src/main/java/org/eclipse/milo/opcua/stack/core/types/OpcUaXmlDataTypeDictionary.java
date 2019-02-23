/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
    public void registerEnumCodec(OpcUaXmlDataTypeCodec<?> codec, String description) {
        codecsByDescription.put(description, codec);
    }

    @Override
    public void registerStructCodec(OpcUaXmlDataTypeCodec<?> codec, String description, NodeId encodingId) {
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
