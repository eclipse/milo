package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditSessionEventType extends AuditSecurityEventType {
    QualifiedProperty<NodeId> SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SessionId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getSessionIdNode();

    NodeId getSessionId();

    void setSessionId(NodeId value);
}
