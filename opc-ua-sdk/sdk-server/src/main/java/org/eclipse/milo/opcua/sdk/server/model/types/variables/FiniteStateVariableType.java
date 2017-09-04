package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface FiniteStateVariableType extends StateVariableType {
    QualifiedProperty<NodeId> ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Id",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getIdNode();

    NodeId getId();

    void setId(NodeId value);
}
