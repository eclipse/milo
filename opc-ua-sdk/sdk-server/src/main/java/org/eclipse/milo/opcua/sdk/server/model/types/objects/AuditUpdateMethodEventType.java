package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditUpdateMethodEventType extends AuditEventType {
    QualifiedProperty<NodeId> METHOD_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MethodId",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<Object[]> INPUT_ARGUMENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputArguments",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.OneDimension,
        Object[].class
    );

    PropertyType getMethodIdNode();

    NodeId getMethodId();

    void setMethodId(NodeId value);

    PropertyType getInputArgumentsNode();

    Object[] getInputArguments();

    void setInputArguments(Object[] value);
}
