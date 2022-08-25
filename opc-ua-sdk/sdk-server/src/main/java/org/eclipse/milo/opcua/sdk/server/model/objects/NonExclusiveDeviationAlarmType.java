package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.21/#5.8.21.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.21/#5.8.21.2</a>
 */
public interface NonExclusiveDeviationAlarmType extends NonExclusiveLimitAlarmType {
    QualifiedProperty<NodeId> SETPOINT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SetpointNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<NodeId> BASE_SETPOINT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "BaseSetpointNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    NodeId getSetpointNode();

    void setSetpointNode(NodeId value);

    PropertyType getSetpointNodeNode();

    NodeId getBaseSetpointNode();

    void setBaseSetpointNode(NodeId value);

    PropertyType getBaseSetpointNodeNode();
}
