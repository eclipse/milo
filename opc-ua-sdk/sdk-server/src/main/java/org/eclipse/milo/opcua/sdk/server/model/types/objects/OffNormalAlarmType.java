package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface OffNormalAlarmType extends DiscreteAlarmType {
    QualifiedProperty<NodeId> NORMAL_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NormalState",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getNormalStateNode();

    NodeId getNormalState();

    void setNormalState(NodeId value);
}
