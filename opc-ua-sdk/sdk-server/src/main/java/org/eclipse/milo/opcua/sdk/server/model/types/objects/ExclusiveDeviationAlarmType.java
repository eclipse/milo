package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ExclusiveDeviationAlarmType extends ExclusiveLimitAlarmType {
    QualifiedProperty<NodeId> SETPOINT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SetpointNode",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getSetpointNodeNode();

    NodeId getSetpointNode();

    void setSetpointNode(NodeId value);
}
