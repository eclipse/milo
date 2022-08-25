package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.FieldTargetDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.9/#9.1.9.2.1</a>
 */
public interface TargetVariablesType extends SubscribedDataSetType {
    QualifiedProperty<FieldTargetDataType[]> TARGET_VARIABLES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TargetVariables",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14744"),
        1,
        FieldTargetDataType[].class
    );

    FieldTargetDataType[] getTargetVariables();

    void setTargetVariables(FieldTargetDataType[] value);

    PropertyType getTargetVariablesNode();

    MethodNode getAddTargetVariablesMethodNode();

    MethodNode getRemoveTargetVariablesMethodNode();
}
