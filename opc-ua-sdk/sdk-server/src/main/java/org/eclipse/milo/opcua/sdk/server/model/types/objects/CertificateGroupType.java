package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface CertificateGroupType extends BaseObjectType {
    QualifiedProperty<NodeId[]> CERTIFICATE_TYPES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CertificateTypes",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.OneDimension,
        NodeId[].class
    );

    PropertyType getCertificateTypesNode();

    NodeId[] getCertificateTypes();

    void setCertificateTypes(NodeId[] value);

    TrustListType getTrustListNode();
}
