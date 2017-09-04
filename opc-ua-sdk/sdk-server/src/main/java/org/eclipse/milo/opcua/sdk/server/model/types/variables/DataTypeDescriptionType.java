package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface DataTypeDescriptionType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> DICTIONARY_FRAGMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DictionaryFragment",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    PropertyType getDataTypeVersionNode();

    String getDataTypeVersion();

    void setDataTypeVersion(String value);

    PropertyType getDictionaryFragmentNode();

    ByteString getDictionaryFragment();

    void setDictionaryFragment(ByteString value);
}
