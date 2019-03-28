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

import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class OpcUaDataTypeManager implements DataTypeManager {

    public static final String BINARY_NAMESPACE_URI = "http://opcfoundation.org/UA/";
    public static final String XML_NAMESPACE_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    public static OpcUaDataTypeManager getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final OpcUaDataTypeManager INSTANCE = new OpcUaDataTypeManager();
    }

    private final ConcurrentMap<String, DataTypeDictionary<?>> dictionaries = Maps.newConcurrentMap();
    private final ConcurrentMap<NodeId, DataTypeCodec> codecsByEncodingId = Maps.newConcurrentMap();
    private final Table<QualifiedName, NodeId, DataTypeCodec> codecsByDataTypeId =
        Tables.synchronizedTable(HashBasedTable.create());

    private OpcUaDataTypeManager() {
        registerTypeDictionary(BuiltinDataTypeDictionary.getBinaryInstance());
        registerTypeDictionary(BuiltinDataTypeDictionary.getXmlInstance());
    }

    @Override
    public void registerCodec(NodeId encodingId, DataTypeCodec codec) {
        codecsByEncodingId.put(encodingId, codec);
    }

    @Override
    public void registerCodec(QualifiedName encodingName, NodeId dataTypeId, DataTypeCodec codec) {
        codecsByDataTypeId.put(encodingName, dataTypeId, codec);
    }

    @Override
    public void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary) {
        dictionaries.put(dataTypeDictionary.getNamespaceUri(), dataTypeDictionary);

        this.codecsByEncodingId.putAll(dataTypeDictionary.getCodecsByEncodingId());

        dataTypeDictionary.getCodecsByDataTypeId().forEach(
            (dataTypeId, codec) ->
                codecsByDataTypeId.put(dataTypeDictionary.getEncodingName(), dataTypeId, codec)
        );
    }

    @Nullable
    @Override
    public DataTypeCodec getCodec(NodeId encodingId) {
        return codecsByEncodingId.get(encodingId);
    }

    @Nullable
    @Override
    public DataTypeCodec getCodec(QualifiedName encodingName, NodeId dataTypeId) {
        return codecsByDataTypeId.get(encodingName, dataTypeId);
    }

    @Nullable
    @Override
    public DataTypeCodec getCodec(String namespaceUri, String description) {
        DataTypeDictionary<?> dataTypeDictionary = dictionaries.get(namespaceUri);

        return dataTypeDictionary != null ? dataTypeDictionary.getCodec(description) : null;
    }

}
