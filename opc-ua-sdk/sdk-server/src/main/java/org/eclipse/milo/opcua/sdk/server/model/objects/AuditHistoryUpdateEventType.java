package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.26">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.26</a>
 */
public interface AuditHistoryUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<NodeId> PARAMETER_DATA_TYPE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ParameterDataTypeId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    NodeId getParameterDataTypeId();

    void setParameterDataTypeId(NodeId value);

    PropertyType getParameterDataTypeIdNode();
}