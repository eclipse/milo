package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.3/#9.1.3.2</a>
 */
public interface PublishSubscribeType extends PubSubKeyServiceType {
    QualifiedProperty<String[]> SUPPORTED_TRANSPORT_PROFILES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SupportedTransportProfiles",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<ULong> DEFAULT_DATAGRAM_PUBLISHER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DefaultDatagramPublisherId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=9"),
        -1,
        ULong.class
    );

    QualifiedProperty<UInteger> CONFIGURATION_VERSION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ConfigurationVersion",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=20998"),
        -1,
        UInteger.class
    );

    String[] getSupportedTransportProfiles();

    void setSupportedTransportProfiles(String[] value);

    PropertyType getSupportedTransportProfilesNode();

    ULong getDefaultDatagramPublisherId();

    void setDefaultDatagramPublisherId(ULong value);

    PropertyType getDefaultDatagramPublisherIdNode();

    UInteger getConfigurationVersion();

    void setConfigurationVersion(UInteger value);

    PropertyType getConfigurationVersionNode();

    MethodNode getSetSecurityKeysMethodNode();

    MethodNode getAddConnectionMethodNode();

    MethodNode getRemoveConnectionMethodNode();

    DataSetFolderType getPublishedDataSetsNode();

    SubscribedDataSetFolderType getSubscribedDataSetsNode();

    PubSubConfigurationType getPubSubConfigurationNode();

    PubSubStatusType getStatusNode();

    PubSubDiagnosticsRootType getDiagnosticsNode();

    PubSubCapabilitiesType getPubSubCapablitiesNode();

    FolderType getDataSetClassesNode();
}
