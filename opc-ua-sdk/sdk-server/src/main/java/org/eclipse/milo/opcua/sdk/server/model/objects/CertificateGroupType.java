package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateGroupType extends BaseObjectType {
    QualifiedProperty<NodeId[]> CERTIFICATE_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateTypes",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        1,
        NodeId[].class
    );

    NodeId[] getCertificateTypes();

    void setCertificateTypes(NodeId[] value);

    PropertyType getCertificateTypesNode();

    TrustListType getTrustListNode();

    MethodNode getGetRejectedListMethodNode();

    CertificateExpirationAlarmType getCertificateExpiredNode();

    TrustListOutOfDateAlarmType getTrustListOutOfDateNode();
}
