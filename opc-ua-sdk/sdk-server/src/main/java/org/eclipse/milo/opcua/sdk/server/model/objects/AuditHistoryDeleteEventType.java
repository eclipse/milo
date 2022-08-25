package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/5.6.5">https://reference.opcfoundation.org/v104/Core/docs/Part11/5.6.5</a>
 */
public interface AuditHistoryDeleteEventType extends AuditHistoryUpdateEventType {
    QualifiedProperty<NodeId> UPDATED_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdatedNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    NodeId getUpdatedNode();

    void setUpdatedNode(NodeId value);

    PropertyType getUpdatedNodeNode();
}
