package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface DataTypeDictionaryType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<String> NAMESPACE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceUri",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getDataTypeVersionNode();

    String getDataTypeVersion();

    void setDataTypeVersion(String value);

    PropertyType getNamespaceUriNode();

    String getNamespaceUri();

    void setNamespaceUri(String value);
}
