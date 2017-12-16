package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;

public interface SystemStatusChangeEventType extends SystemEventType {
    QualifiedProperty<ServerState> SYSTEM_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SystemState",
        NodeId.parse("ns=0;i=852"),
        ValueRanks.Scalar,
        ServerState.class
    );

    PropertyType getSystemStateNode();

    ServerState getSystemState();

    void setSystemState(ServerState value);
}
