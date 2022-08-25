package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.7">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.7</a>
 */
public interface AuditSessionEventType extends AuditSecurityEventType {
    QualifiedProperty<NodeId> SESSION_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SessionId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    NodeId getSessionId();

    void setSessionId(NodeId value);

    PropertyType getSessionIdNode();
}
