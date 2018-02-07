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
    private final ConcurrentMap<NodeId, DataTypeCodec> codecs = Maps.newConcurrentMap();

    public ClientDataTypeManager() {
        registerTypeDictionary(BuiltinDataTypeDictionary.getBinaryInstance());
        registerTypeDictionary(BuiltinDataTypeDictionary.getXmlInstance());
    }

    @Override
    public void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary) {
        dictionaries.put(dataTypeDictionary.getNamespaceUri(), dataTypeDictionary);

        this.codecs.putAll(dataTypeDictionary.getCodecsByEncodingId());
    }

    @Nullable
    @Override
    public DataTypeDictionary getTypeDictionary(String namespaceUri) {
        return dictionaries.get(namespaceUri);
    }

    @Nullable
    @Override
    public OpcUaBinaryDataTypeCodec<?> getBinaryCodec(NodeId encodingId) {
        DataTypeCodec codec = codecs.get(encodingId);

        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            return (OpcUaBinaryDataTypeCodec) codec;
        } else {
            return null;
        }
    }

    @Nullable
    @Override
    public OpcUaXmlDataTypeCodec<?> getXmlCodec(NodeId encodingId) {
        DataTypeCodec codec = codecs.get(encodingId);

        if (codec instanceof OpcUaXmlDataTypeCodec) {
            return (OpcUaXmlDataTypeCodec) codec;
        } else {
            return null;
        }
    }

}
