package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditCancelEventType extends AuditSessionEventType {
    QualifiedProperty<UInteger> REQUEST_HANDLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RequestHandle",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    PropertyType getRequestHandleNode();

    UInteger getRequestHandle();

    void setRequestHandle(UInteger value);
}
