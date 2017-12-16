package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditUrlMismatchEventType extends AuditCreateSessionEventType {
    QualifiedProperty<String> ENDPOINT_URL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndpointUrl",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getEndpointUrlNode();

    String getEndpointUrl();

    void setEndpointUrl(String value);
}
