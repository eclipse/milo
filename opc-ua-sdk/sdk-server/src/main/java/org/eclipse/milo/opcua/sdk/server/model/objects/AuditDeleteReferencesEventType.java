package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.23">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.23</a>
 */
public interface AuditDeleteReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteReferencesItem[]> REFERENCES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToDelete",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=385"),
        1,
        DeleteReferencesItem[].class
    );

    DeleteReferencesItem[] getReferencesToDelete();

    void setReferencesToDelete(DeleteReferencesItem[] value);

    PropertyType getReferencesToDeleteNode();
}
