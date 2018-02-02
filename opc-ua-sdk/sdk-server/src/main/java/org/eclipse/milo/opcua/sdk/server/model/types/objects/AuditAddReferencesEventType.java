package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;

public interface AuditAddReferencesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<AddReferencesItem[]> REFERENCES_TO_ADD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReferencesToAdd",
        NodeId.parse("ns=0;i=379"),
        ValueRanks.OneDimension,
        AddReferencesItem[].class
    );

    PropertyType getReferencesToAddNode();

    AddReferencesItem[] getReferencesToAdd();

    void setReferencesToAdd(AddReferencesItem[] value);
}
