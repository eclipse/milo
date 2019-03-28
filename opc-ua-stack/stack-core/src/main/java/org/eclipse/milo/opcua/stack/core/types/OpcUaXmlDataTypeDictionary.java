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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class OpcUaXmlDataTypeDictionary implements DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> {

    private final String namespaceUri;

    private final Map<String, OpcUaXmlDataTypeCodec<?>> codecsByDescription;
    private final Map<NodeId, OpcUaXmlDataTypeCodec<?>> codecsByEncodingId;
    private final Map<NodeId, OpcUaXmlDataTypeCodec<?>> codecsByDataTypeId;

    public OpcUaXmlDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByEncodingId = Maps.newConcurrentMap();
        codecsByDataTypeId = Maps.newConcurrentMap();
    }

    public OpcUaXmlDataTypeDictionary(
        String namespaceUri,
        Map<String, OpcUaXmlDataTypeCodec<?>> byDescription,
        Map<NodeId, OpcUaXmlDataTypeCodec<?>> byEncodingId,
        Map<NodeId, OpcUaXmlDataTypeCodec<?>> byDataTypeId
    ) {

        this.namespaceUri = namespaceUri;

        codecsByDescription = Maps.newConcurrentMap();
        codecsByDescription.putAll(byDescription);

        codecsByEncodingId = Maps.newConcurrentMap();
        codecsByEncodingId.putAll(byEncodingId);

        codecsByDataTypeId = Maps.newConcurrentMap();
        codecsByDataTypeId.putAll(byDataTypeId);
    }

    @Override
    public String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public QualifiedName getEncodingName() {
        return OpcUaDefaultXmlEncoding.ENCODING_NAME;
    }

    @Override
    public OpcUaXmlDataTypeCodec<?> getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcUaXmlDataTypeCodec<?> getCodec(NodeId dataTypeId) {
        return codecsByDataTypeId.get(dataTypeId);
    }

    @Override
    public void registerEnumCodec(OpcUaXmlDataTypeCodec<?> codec, String description) {
        codecsByDescription.put(description, codec);
    }

    @Override
    public void registerStructCodec(
        OpcUaXmlDataTypeCodec<?> codec,
        String description,
        NodeId dataTypeId,
        NodeId encodingId
    ) {

        codecsByDescription.put(description, codec);
        codecsByDataTypeId.put(dataTypeId, codec);
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

    @Override
    public Map<NodeId, OpcUaXmlDataTypeCodec<?>> getCodecsByDataTypeId() {
        return codecsByDataTypeId;
    }

}
