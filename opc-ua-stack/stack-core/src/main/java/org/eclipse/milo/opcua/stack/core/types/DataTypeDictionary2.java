package org.eclipse.milo.opcua.stack.core.types;

import java.util.List;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.jetbrains.annotations.Nullable;

public interface DataTypeDictionary2 {

    String getNamespaceUri();

    @Nullable DataTypeCodec getCodec(String description);

    void registerType(Type type);

    @Nullable Type getType(String description);

    List<Type> getTypes();

    interface Type {
        String getDescription();

        NodeId getDataTypeId();

        NodeId getEncodingId();

        DataTypeCodec getCodec();
    }

}
