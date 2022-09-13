package org.eclipse.milo.opcua.sdk.core.dtd;

import java.util.List;

import com.sun.istack.Nullable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary2;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.opcfoundation.opcua.binaryschema.TypeDescription;

public class BinaryDataTypeDictionary implements DataTypeDictionary2 {

    private final String namespaceUri;

    public BinaryDataTypeDictionary(String namespaceUri) {
        this.namespaceUri = namespaceUri;
    }

    @Override
    public String getNamespaceUri() {
        return namespaceUri;
    }

    @Override
    public @Nullable BinaryDataTypeCodec getCodec(String description) {
        return null;
    }

    @Override
    public void registerType(Type type) {

    }

    @Override
    public @Nullable Type getType(String description) {
        return null;
    }

    @Override
    public List<Type> getTypes() {
        return null;
    }

    public @Nullable TypeDescription getTypeDescription(String description) {
        return null;
    }

    public static class BinaryType implements DataTypeDictionary2.Type {

        public final String description;
        public final NodeId dataTypeId;
        public final NodeId encodingId;
        public final BinaryDataTypeCodec codec;

        public BinaryType(String description, NodeId dataTypeId, NodeId encodingId, BinaryDataTypeCodec codec) {
            this.description = description;
            this.dataTypeId = dataTypeId;
            this.encodingId = encodingId;
            this.codec = codec;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public NodeId getDataTypeId() {
            return dataTypeId;
        }

        @Override
        public NodeId getEncodingId() {
            return encodingId;
        }

        @Override
        public DataTypeCodec getCodec() {
            return codec;
        }

    }

}
