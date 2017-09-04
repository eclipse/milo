package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public interface AuditDeleteReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteReferencesItem[]> REFERENCES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToDelete",
        NodeId.parse("ns=0;i=385"),
        ValueRanks.OneDimension,
        DeleteReferencesItem[].class
    );

    PropertyType getReferencesToDeleteNode();

    DeleteReferencesItem[] getReferencesToDelete();

    void setReferencesToDelete(DeleteReferencesItem[] value);
}
