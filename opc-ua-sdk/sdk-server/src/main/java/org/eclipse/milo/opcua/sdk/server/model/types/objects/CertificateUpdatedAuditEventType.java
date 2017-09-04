package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateUpdatedAuditEventType extends AuditUpdateMethodEventType {
    QualifiedProperty<NodeId> CERTIFICATE_GROUP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateGroup",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<NodeId> CERTIFICATE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateType",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    PropertyType getCertificateGroupNode();

    NodeId getCertificateGroup();

    void setCertificateGroup(NodeId value);

    PropertyType getCertificateTypeNode();

    NodeId getCertificateType();

    void setCertificateType(NodeId value);
}
