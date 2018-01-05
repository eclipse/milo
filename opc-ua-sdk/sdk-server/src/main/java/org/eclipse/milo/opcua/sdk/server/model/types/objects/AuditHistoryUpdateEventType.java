package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditHistoryUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<NodeId> PARAMETER_DATA_TYPE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ParameterDataTypeId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getParameterDataTypeIdNode();

    NodeId getParameterDataTypeId();

    void setParameterDataTypeId(NodeId value);
}
