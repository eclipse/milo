package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class PublishSubscribeTypeNode extends PubSubKeyServiceTypeNode implements PublishSubscribeType {
    public PublishSubscribeTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                    RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                    UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public String[] getSupportedTransportProfiles() throws UaException {
        PropertyTypeNode node = getSupportedTransportProfilesNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSupportedTransportProfiles(String[] value) throws UaException {
        PropertyTypeNode node = getSupportedTransportProfilesNode();
        node.setValue(new Variant(value));
    }

    @Override
    public String[] readSupportedTransportProfiles() throws UaException {
        try {
            return readSupportedTransportProfilesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSupportedTransportProfiles(String[] value) throws UaException {
        try {
            writeSupportedTransportProfilesAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readSupportedTransportProfilesAsync() {
        return getSupportedTransportProfilesNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSupportedTransportProfilesAsync(
        String[] supportedTransportProfiles) {
        DataValue value = DataValue.valueOnly(new Variant(supportedTransportProfiles));
        return getSupportedTransportProfilesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSupportedTransportProfilesNode() throws UaException {
        try {
            return getSupportedTransportProfilesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSupportedTransportProfilesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SupportedTransportProfiles",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public ULong getDefaultDatagramPublisherId() throws UaException {
        PropertyTypeNode node = getDefaultDatagramPublisherIdNode();
        return (ULong) node.getValue().getValue().getValue();
    }

    @Override
    public void setDefaultDatagramPublisherId(ULong value) throws UaException {
        PropertyTypeNode node = getDefaultDatagramPublisherIdNode();
        node.setValue(new Variant(value));
    }

    @Override
    public ULong readDefaultDatagramPublisherId() throws UaException {
        try {
            return readDefaultDatagramPublisherIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDefaultDatagramPublisherId(ULong value) throws UaException {
        try {
            writeDefaultDatagramPublisherIdAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ULong> readDefaultDatagramPublisherIdAsync() {
        return getDefaultDatagramPublisherIdNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (ULong) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDefaultDatagramPublisherIdAsync(
        ULong defaultDatagramPublisherId) {
        DataValue value = DataValue.valueOnly(new Variant(defaultDatagramPublisherId));
        return getDefaultDatagramPublisherIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getDefaultDatagramPublisherIdNode() throws UaException {
        try {
            return getDefaultDatagramPublisherIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDefaultDatagramPublisherIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "DefaultDatagramPublisherId",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getConfigurationVersion() throws UaException {
        PropertyTypeNode node = getConfigurationVersionNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setConfigurationVersion(UInteger value) throws UaException {
        PropertyTypeNode node = getConfigurationVersionNode();
        node.setValue(new Variant(value));
    }

    @Override
    public UInteger readConfigurationVersion() throws UaException {
        try {
            return readConfigurationVersionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeConfigurationVersion(UInteger value) throws UaException {
        try {
            writeConfigurationVersionAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readConfigurationVersionAsync() {
        return getConfigurationVersionNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeConfigurationVersionAsync(
        UInteger configurationVersion) {
        DataValue value = DataValue.valueOnly(new Variant(configurationVersion));
        return getConfigurationVersionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getConfigurationVersionNode() throws UaException {
        try {
            return getConfigurationVersionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getConfigurationVersionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "ConfigurationVersion",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DataSetFolderTypeNode getPublishedDataSetsNode() throws UaException {
        try {
            return getPublishedDataSetsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends DataSetFolderTypeNode> getPublishedDataSetsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "PublishedDataSets",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (DataSetFolderTypeNode) node);
    }

    @Override
    public SubscribedDataSetFolderTypeNode getSubscribedDataSetsNode() throws UaException {
        try {
            return getSubscribedDataSetsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends SubscribedDataSetFolderTypeNode> getSubscribedDataSetsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SubscribedDataSets",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (SubscribedDataSetFolderTypeNode) node);
    }

    @Override
    public PubSubConfigurationTypeNode getPubSubConfigurationNode() throws UaException {
        try {
            return getPubSubConfigurationNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PubSubConfigurationTypeNode> getPubSubConfigurationNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "PubSubConfiguration",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (PubSubConfigurationTypeNode) node);
    }

    @Override
    public PubSubStatusTypeNode getStatusNode() throws UaException {
        try {
            return getStatusNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PubSubStatusTypeNode> getStatusNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "Status",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (PubSubStatusTypeNode) node);
    }

    @Override
    public PubSubDiagnosticsRootTypeNode getDiagnosticsNode() throws UaException {
        try {
            return getDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PubSubDiagnosticsRootTypeNode> getDiagnosticsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "Diagnostics",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (PubSubDiagnosticsRootTypeNode) node);
    }

    @Override
    public PubSubCapabilitiesTypeNode getPubSubCapablitiesNode() throws UaException {
        try {
            return getPubSubCapablitiesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PubSubCapabilitiesTypeNode> getPubSubCapablitiesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "PubSubCapablities",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (PubSubCapabilitiesTypeNode) node);
    }

    @Override
    public FolderTypeNode getDataSetClassesNode() throws UaException {
        try {
            return getDataSetClassesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends FolderTypeNode> getDataSetClassesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "DataSetClasses",
            ExpandedNodeId.parse("ns=0;i=47"),
            false
        );
        return future.thenApply(node -> (FolderTypeNode) node);
    }
}
