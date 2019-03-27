/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ClientDataTypeManager implements DataTypeManager {

    private final ConcurrentMap<String, DataTypeDictionary<?>> dictionaries = Maps.newConcurrentMap();
    private final ConcurrentMap<NodeId, DataTypeCodec> codecsByEncodingId = Maps.newConcurrentMap();
    private final ConcurrentMap<NodeId, DataTypeCodec> codecsByDataTypeId = Maps.newConcurrentMap();


    public ClientDataTypeManager() {
        registerTypeDictionary(BuiltinDataTypeDictionary.getBinaryInstance());
        registerTypeDictionary(BuiltinDataTypeDictionary.getXmlInstance());
    }

    @Override
    public void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary) {
        dictionaries.put(dataTypeDictionary.getNamespaceUri(), dataTypeDictionary);

        this.codecsByEncodingId.putAll(dataTypeDictionary.getCodecsByEncodingId());
        this.codecsByDataTypeId.putAll(dataTypeDictionary.getCodecsByDataTypeId());
    }

    @Nullable
    @Override
    public DataTypeDictionary getTypeDictionary(String namespaceUri) {
        return dictionaries.get(namespaceUri);
    }

    @Nullable
    @Override
    public OpcUaBinaryDataTypeCodec<?> getBinaryCodecByDataTypeId(NodeId dataTypeId) {
        DataTypeCodec codec = codecsByDataTypeId.get(dataTypeId);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            return (OpcUaBinaryDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public OpcUaBinaryDataTypeCodec<?> getBinaryCodecByEncodingId(NodeId encodingId) {
        DataTypeCodec codec = codecsByEncodingId.get(encodingId);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            return (OpcUaBinaryDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public OpcUaXmlDataTypeCodec<?> getXmlCodecByDataTypeId(NodeId dataTypeId) {
        DataTypeCodec codec = codecsByDataTypeId.get(dataTypeId);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            return (OpcUaXmlDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public OpcUaXmlDataTypeCodec<?> getXmlCodecByEncodingId(NodeId encodingId) {
        DataTypeCodec codec = codecsByEncodingId.get(encodingId);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            return (OpcUaXmlDataTypeCodec) codec;
        } else {
            return null;
        }
    }

}
