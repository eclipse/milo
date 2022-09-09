package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public interface DataTypeManager2 {

    void registerEnumType(NodeId dataTypeId, DataTypeCodec<?> codec);

    void registerStructType(
        NodeId dataTypeId,
        DataTypeCodec<?> codec,
        @Nullable NodeId binaryEncodingId,
        @Nullable NodeId xmlEncodingId,
        @Nullable NodeId jsonEncodingId
    );

    @Nullable DataTypeCodec<?> getEnumCodec(NodeId dataTypeId);

    @Nullable DataTypeCodec<?> getStructCodec(NodeId encodingId);

    @Nullable DataTypeCodec<?> getStructCodec(QualifiedName encodingName, NodeId dataTypeId);

    @Nullable NodeId getBinaryEncodingId(NodeId dataTypeId);

    @Nullable NodeId getXmlEncodingId(NodeId dataTypeId);

    @Nullable NodeId getJsonEncodingId(NodeId dataTypeId);

    /**
     * Get a registered {@link DataTypeDictionary} by its namespace URI.
     *
     * @param namespaceUri the namespace URI the dictionary is registered under.
     * @return the {@link DataTypeDictionary} registered under {@code namespaceUri}.
     */
    @Nullable DataTypeDictionary<?> getDataTypeDictionary(String namespaceUri);

    /**
     * Register a {@link DataTypeDictionary} and all the {@link DataTypeCodec}s it contains.
     *
     * @param dataTypeDictionary the {@link DataTypeDictionary} to register.
     */
    void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary);

}
