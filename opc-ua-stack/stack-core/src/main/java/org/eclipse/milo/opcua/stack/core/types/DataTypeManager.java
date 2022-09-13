package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public interface DataTypeManager {

    void registerType(
        NodeId dataTypeId,
        DataTypeCodec codec,
        @Nullable NodeId binaryEncodingId,
        @Nullable NodeId xmlEncodingId,
        @Nullable NodeId jsonEncodingId
    );

    @Nullable DataTypeCodec getCodec(NodeId encodingId);

    @Nullable DataTypeCodec getCodec(QualifiedName encodingName, NodeId dataTypeId);

    @Nullable NodeId getBinaryEncodingId(NodeId dataTypeId);

    @Nullable NodeId getXmlEncodingId(NodeId dataTypeId);

    @Nullable NodeId getJsonEncodingId(NodeId dataTypeId);

    default DataTypeDictionary2 getTypeDictionary(String namespaceUri) {
        return null; // TODO
    }

    default void registerTypeDictionary(DataTypeDictionary2 dictionary) {
        // TODO
    }

}
