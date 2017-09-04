package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;

public interface AuditDeleteNodesEventType extends AuditNodeManagementEventType {
    QualifiedProperty<DeleteNodesItem[]> NODES_TO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NodesToDelete",
        NodeId.parse("ns=0;i=382"),
        ValueRanks.OneDimension,
        DeleteNodesItem[].class
    );

    PropertyType getNodesToDeleteNode();

    DeleteNodesItem[] getNodesToDelete();

    void setNodesToDelete(DeleteNodesItem[] value);
}
