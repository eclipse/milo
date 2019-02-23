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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class OpcUaBinaryDataTypeDictionary implements DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> {

    private final String namespaceUri;

    private final Map<String, OpcUaBinaryDataTypeCodec<?>> codecsByDescription;
    private final Map<NodeId, OpcUaBinaryDataTypeCodec<?>> codecsByEncodingId;

    public OpcUaBinaryDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByEncodingId = Maps.newConcurrentMap();
    }

    public OpcUaBinaryDataTypeDictionary(
        String namespaceUri,
        Map<String, OpcUaBinaryDataTypeCodec<?>> byDescription,
        Map<NodeId, OpcUaBinaryDataTypeCodec<?>> byEncodingId) {

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
    public OpcUaBinaryDataTypeCodec<?> getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcUaBinaryDataTypeCodec<?> getCodec(NodeId encodingId) {
        return codecsByEncodingId.get(encodingId);
    }

    @Override
    public void registerEnumCodec(OpcUaBinaryDataTypeCodec<?> codec, String description) {
        codecsByDescription.put(description, codec);
    }

    @Override
    public void registerStructCodec(OpcUaBinaryDataTypeCodec<?> codec, String description, NodeId encodingId) {
        codecsByDescription.put(description, codec);
        codecsByEncodingId.put(encodingId, codec);
    }

    @Override
    public Map<String, OpcUaBinaryDataTypeCodec<?>> getCodecsByDescription() {
        return codecsByDescription;
    }

    @Override
    public Map<NodeId, OpcUaBinaryDataTypeCodec<?>> getCodecsByEncodingId() {
        return codecsByEncodingId;
    }

}
