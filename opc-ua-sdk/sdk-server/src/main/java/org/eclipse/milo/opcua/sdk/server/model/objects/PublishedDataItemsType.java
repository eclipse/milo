package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishedVariableDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.4/#9.1.4.3.1</a>
 */
public interface PublishedDataItemsType extends PublishedDataSetType {
    QualifiedProperty<PublishedVariableDataType[]> PUBLISHED_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PublishedData",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14273"),
        1,
        PublishedVariableDataType[].class
    );

    PublishedVariableDataType[] getPublishedData();

    void setPublishedData(PublishedVariableDataType[] value);

    PropertyType getPublishedDataNode();

    MethodNode getAddVariablesMethodNode();

    MethodNode getRemoveVariablesMethodNode();
}
