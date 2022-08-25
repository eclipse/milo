package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.2.2</a>
 */
public interface ExtensionFieldsType extends BaseObjectType {
    QualifiedProperty<Object> EXTENSION_FIELD_NAME_PLACEHOLDER = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "<ExtensionFieldName>",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        -1,
        Object.class
    );

    Object getExtensionFieldNamePlaceholder();

    void setExtensionFieldNamePlaceholder(Object value);

    PropertyType getExtensionFieldNamePlaceholderNode();

    MethodNode getAddExtensionFieldMethodNode();

    MethodNode getRemoveExtensionFieldMethodNode();
}
