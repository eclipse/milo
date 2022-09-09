package org.eclipse.milo.opcua.stack.core.types;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public class DefaultDataTypeManager2 implements DataTypeManager2 {

    /**
     * QualifiedName of the OPC UA Binary encoding.
     */
    private static final QualifiedName BINARY_ENCODING_NAME =
        new QualifiedName(0, "Default Binary");

    /**
     * QualifiedName of the OPC UA XML encoding.
     */
    private static final QualifiedName XML_ENCODING_NAME =
        new QualifiedName(0, "Default XML");

    /**
     * QualifiedName of the OPC UA JSON encoding.
     */
    private static final QualifiedName JSON_ENCODING_NAME =
        new QualifiedName(0, "Default JSON");

    /**
     * K = String of DataTypeDictionary namespace URI
     * V = DataTypeDictionary
     */
    private final Map<String, DataTypeDictionary<?>> dictionariesByNamespaceUri = new ConcurrentHashMap<>();

    /**
     * K = NodeId of DataType
     * V = DataTypeCodec
     */
    private final Map<NodeId, DataTypeCodec<?>> enumCodecsByTypeId = new ConcurrentHashMap<>();

    /**
     * K = NodeId of DataType Encoding
     * V = DataTypeCodec
     */
    private final Map<NodeId, DataTypeCodec<?>> structCodecsByEncodingId = new ConcurrentHashMap<>();

    /**
     * R = QualifiedName of DataType Encoding
     * C = NodeId of DataType
     * V = DataTypeCodec
     */
    private final Map<QualifiedName, Map<NodeId, DataTypeCodec<?>>> structCodecsByEncodingName =
        new ConcurrentHashMap<>();

    /**
     * R = QualifiedName of DataType Encoding
     * C = NodeId of DataType
     * V = NodeId of DataType Encoding
     */
    private final Map<QualifiedName, Map<NodeId, NodeId>> structEncodingIdsByEncodingName =
        new ConcurrentHashMap<>();

    @Override
    public void registerEnumType(NodeId dataTypeId, DataTypeCodec<?> codec) {
        enumCodecsByTypeId.put(dataTypeId, codec);
    }

    @Override
    public void registerStructType(
        NodeId dataTypeId,
        DataTypeCodec<?> codec,
        @Nullable NodeId binaryEncodingId,
        @Nullable NodeId xmlEncodingId,
        @Nullable NodeId jsonEncodingId
    ) {

        if (binaryEncodingId != null && binaryEncodingId.isNotNull()) {
            putCodecForEncoding(BINARY_ENCODING_NAME, dataTypeId, codec);
            putEncodingIdForEncoding(BINARY_ENCODING_NAME, dataTypeId, binaryEncodingId);
            structCodecsByEncodingId.put(binaryEncodingId, codec);
        }
        if (xmlEncodingId != null && xmlEncodingId.isNotNull()) {
            putCodecForEncoding(XML_ENCODING_NAME, dataTypeId, codec);
            putEncodingIdForEncoding(XML_ENCODING_NAME, dataTypeId, binaryEncodingId);
            structCodecsByEncodingId.put(xmlEncodingId, codec);
        }
        if (jsonEncodingId != null && jsonEncodingId.isNotNull()) {
            putCodecForEncoding(JSON_ENCODING_NAME, dataTypeId, codec);
            putEncodingIdForEncoding(JSON_ENCODING_NAME, dataTypeId, binaryEncodingId);
            structCodecsByEncodingId.put(jsonEncodingId, codec);
        }
    }

    @Override
    public @Nullable DataTypeCodec<?> getEnumCodec(NodeId dataTypeId) {
        return enumCodecsByTypeId.get(dataTypeId);
    }

    @Override
    public @Nullable DataTypeCodec<?> getStructCodec(NodeId encodingId) {
        return structCodecsByEncodingId.get(encodingId);
    }

    @Override
    public @Nullable DataTypeCodec<?> getStructCodec(QualifiedName encodingName, NodeId dataTypeId) {
        Map<NodeId, DataTypeCodec<?>> byDataTypeId = structCodecsByEncodingName.get(encodingName);

        return byDataTypeId != null ? byDataTypeId.get(dataTypeId) : null;
    }

    @Override
    public @Nullable NodeId getBinaryEncodingId(NodeId dataTypeId) {
        Map<NodeId, NodeId> byDataTypeId = structEncodingIdsByEncodingName.get(BINARY_ENCODING_NAME);

        return byDataTypeId != null ? byDataTypeId.get(dataTypeId) : null;
    }

    @Override
    public @Nullable NodeId getXmlEncodingId(NodeId dataTypeId) {
        Map<NodeId, NodeId> byDataTypeId = structEncodingIdsByEncodingName.get(XML_ENCODING_NAME);

        return byDataTypeId != null ? byDataTypeId.get(dataTypeId) : null;
    }

    @Override
    public @Nullable NodeId getJsonEncodingId(NodeId dataTypeId) {
        Map<NodeId, NodeId> byDataTypeId = structEncodingIdsByEncodingName.get(JSON_ENCODING_NAME);

        return byDataTypeId != null ? byDataTypeId.get(dataTypeId) : null;
    }

    @Override
    public @Nullable DataTypeDictionary<?> getDataTypeDictionary(String namespaceUri) {
        return dictionariesByNamespaceUri.get(namespaceUri);
    }

    @Override
    public void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary) {
        dictionariesByNamespaceUri.put(dataTypeDictionary.getNamespaceUri(), dataTypeDictionary);
    }

    private void putCodecForEncoding(QualifiedName encodingName, NodeId dataTypeId, DataTypeCodec<?> codec) {
        Map<NodeId, DataTypeCodec<?>> byDataTypeId = structCodecsByEncodingName.computeIfAbsent(
            encodingName,
            k -> new ConcurrentHashMap<>()
        );
        byDataTypeId.put(dataTypeId, codec);
    }

    private void putEncodingIdForEncoding(QualifiedName encodingName, NodeId dataTypeId, NodeId encodingId) {
        Map<NodeId, NodeId> byDataTypeId = structEncodingIdsByEncodingName.computeIfAbsent(
            encodingName,
            k -> new ConcurrentHashMap<>()
        );
        byDataTypeId.put(dataTypeId, encodingId);
    }

    /**
     * Create a {@link DefaultDataTypeManager2} and initialize it with the built-in DataTypes.
     *
     * @param namespaceTable a {@link NamespaceTable}.
     * @return a {@link DataTypeManager2} pre-initialized wth the built-in DataTypes.
     */
    public static DataTypeManager2 createAndInitialize(NamespaceTable namespaceTable) {
        DefaultDataTypeManager2 dataTypeManager = new DefaultDataTypeManager2();

        new DataTypeInitializer2().initialize(namespaceTable, dataTypeManager);

        return dataTypeManager;
    }

}
