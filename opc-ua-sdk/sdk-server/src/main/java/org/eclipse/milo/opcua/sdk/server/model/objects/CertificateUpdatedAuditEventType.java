package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateUpdatedAuditEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<NodeId> CERTIFICATE_GROUP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateGroup",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<NodeId> CERTIFICATE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    NodeId getCertificateGroup();

    void setCertificateGroup(NodeId value);

    PropertyType getCertificateGroupNode();

    NodeId getCertificateType();

    void setCertificateType(NodeId value);

    PropertyType getCertificateTypeNode();
}
