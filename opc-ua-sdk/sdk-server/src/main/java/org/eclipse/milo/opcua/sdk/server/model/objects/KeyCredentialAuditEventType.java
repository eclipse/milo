package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public interface KeyCredentialAuditEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<String> RESOURCE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ResourceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    String getResourceUri();

    void setResourceUri(String value);

    PropertyType getResourceUriNode();
}
