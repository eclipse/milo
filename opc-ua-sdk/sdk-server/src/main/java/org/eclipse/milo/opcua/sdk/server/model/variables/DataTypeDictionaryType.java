package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part5/D.5.2">https://reference.opcfoundation.org/v104/Core/docs/Part5/D.5.2</a>
 */
public interface DataTypeDictionaryType extends BaseDataVariableType {
    QualifiedProperty<String> DATA_TYPE_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DataTypeVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<String> NAMESPACE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NamespaceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<Boolean> DEPRECATED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Deprecated",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    String getDataTypeVersion();

    void setDataTypeVersion(String value);

    PropertyType getDataTypeVersionNode();

    String getNamespaceUri();

    void setNamespaceUri(String value);

    PropertyType getNamespaceUriNode();

    Boolean getDeprecated();

    void setDeprecated(Boolean value);

    PropertyType getDeprecatedNode();
}
