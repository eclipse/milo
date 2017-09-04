package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditChannelEventType extends AuditSecurityEventType {
    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getSecureChannelIdNode();

    String getSecureChannelId();

    void setSecureChannelId(String value);
}
