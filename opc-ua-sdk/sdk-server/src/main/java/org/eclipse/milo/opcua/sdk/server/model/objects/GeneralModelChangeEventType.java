package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ModelChangeStructureDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.32">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.32</a>
 */
public interface GeneralModelChangeEventType extends BaseModelChangeEventType {
    QualifiedProperty<ModelChangeStructureDataType[]> CHANGES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Changes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=877"),
        1,
        ModelChangeStructureDataType[].class
    );

    ModelChangeStructureDataType[] getChanges();

    void setChanges(ModelChangeStructureDataType[] value);

    PropertyType getChangesNode();
}
