package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.22">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.22</a>
 */
public interface AuditAddReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddReferencesItem[]> REFERENCES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToAdd",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=379"),
        1,
        AddReferencesItem[].class
    );

    AddReferencesItem[] getReferencesToAdd();

    void setReferencesToAdd(AddReferencesItem[] value);

    PropertyType getReferencesToAddNode();
}
