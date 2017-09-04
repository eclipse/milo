package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface ServerConfigurationType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_CAPABILITIES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerCapabilities",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> SUPPORTED_PRIVATE_KEY_FORMATS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedPrivateKeyFormats",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<UInteger> MAX_TRUST_LIST_SIZE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTrustListSize",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> MULTICAST_DNS_ENABLED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MulticastDnsEnabled",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    PropertyType getServerCapabilitiesNode();

    String[] getServerCapabilities();

    void setServerCapabilities(String[] value);

    PropertyType getSupportedPrivateKeyFormatsNode();

    String[] getSupportedPrivateKeyFormats();

    void setSupportedPrivateKeyFormats(String[] value);

    PropertyType getMaxTrustListSizeNode();

    UInteger getMaxTrustListSize();

    void setMaxTrustListSize(UInteger value);

    PropertyType getMulticastDnsEnabledNode();

    Boolean getMulticastDnsEnabled();

    void setMulticastDnsEnabled(Boolean value);

    CertificateGroupFolderType getCertificateGroupsNode();
}
