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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public class DefaultDataTypeManager implements DataTypeManager {

    private final ConcurrentMap<String, DataTypeDictionary<?>> dictionaries = Maps.newConcurrentMap();
    private final ConcurrentMap<NodeId, DataTypeCodec> codecsByEncodingId = Maps.newConcurrentMap();
    private final Table<QualifiedName, NodeId, DataTypeCodec> codecsByDataTypeId =
        Tables.synchronizedTable(HashBasedTable.create());

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

    @Nullable
    @Override
    public DataTypeDictionary<?> getDataTypeDictionary(String namespaceUri) {
        return dictionaries.get(namespaceUri);
    }

    /**
     * Create a {@link DefaultDataTypeManager} and initialize it with the built-in DataTypes.
     *
     * @param namespaceTable a {@link NamespaceTable}.
     * @return a {@link DataTypeManager} pre-initialized wth the built-in DataTypes.
     */
    public static DataTypeManager createAndInitialize(NamespaceTable namespaceTable) {
        DefaultDataTypeManager dataTypeManager = new DefaultDataTypeManager();

        DataTypeInitializer.initialize(namespaceTable, dataTypeManager);

        return dataTypeManager;
    }

}
