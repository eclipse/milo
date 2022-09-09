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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class OpcUaXmlDataTypeDictionary implements DataTypeDictionary<OpcUaXmlDataTypeCodec> {

    private final String namespaceUri;

    private final Map<String, OpcUaXmlDataTypeCodec> codecsByDescription;
    private final Map<NodeId, OpcUaXmlDataTypeCodec> codecsByEncodingId;
    private final Map<NodeId, OpcUaXmlDataTypeCodec> codecsByDataTypeId;

    private final List<EnumCodecInfo> enumCodecInfos = Collections.synchronizedList(new ArrayList<>());
    private final List<StructCodecInfo> structCodecInfos = Collections.synchronizedList(new ArrayList<>());

    public OpcUaXmlDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;

        codecsByDescription = new ConcurrentHashMap<>();
        codecsByEncodingId = new ConcurrentHashMap<>();
        codecsByDataTypeId = new ConcurrentHashMap<>();
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
    public OpcUaXmlDataTypeCodec getCodec(String description) {
        return codecsByDescription.get(description);
    }

    @Override
    public OpcUaXmlDataTypeCodec getCodec(NodeId dataTypeId) {
        return codecsByDataTypeId.get(dataTypeId);
    }

    @Override
    public void registerEnumCodec(OpcUaXmlDataTypeCodec codec, String description) {
        codecsByDescription.put(description, codec);
    }

    @Override
    public void registerEnumCodec(OpcUaXmlDataTypeCodec codec, String description, NodeId dataTypeId) {
        codecsByDescription.put(description, codec);
        codecsByDataTypeId.put(dataTypeId, codec);

        enumCodecInfos.add(new EnumCodecInfo(description, dataTypeId, codec));
    }

    @Override
    public void registerStructCodec(OpcUaXmlDataTypeCodec codec, String description) {
        codecsByDescription.put(description, codec);
    }

    @Override
    public void registerStructCodec(
        OpcUaXmlDataTypeCodec codec,
        String description,
        NodeId dataTypeId,
        NodeId encodingId
    ) {

        codecsByDescription.put(description, codec);
        codecsByDataTypeId.put(dataTypeId, codec);
        codecsByEncodingId.put(encodingId, codec);

        structCodecInfos.add(new StructCodecInfo(description, dataTypeId, encodingId, codec));
    }

    @Override
    public List<EnumCodecInfo> getEnumCodecInfos() {
        return enumCodecInfos;
    }

    @Override
    public List<StructCodecInfo> getStructCodecInfos() {
        return structCodecInfos;
    }

}
