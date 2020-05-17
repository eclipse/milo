package org.eclipse.milo.opcua.sdk.client.model2.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerCapabilitiesTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusTypeNode;
import org.eclipse.milo.opcua.sdk.client.model2.BaseObjectTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ServerTypeNode extends BaseObjectTypeNode implements ServerType {
    @Override
    public String[] getServerArray() throws UaException {
        PropertyTypeNode node = getServerArrayNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerArray(String[] serverArray) throws UaException {
        PropertyTypeNode node = getServerArrayNode();
        node.setValue(new Variant(serverArray));
    }

    @Override
    public String[] readServerArray() throws UaException {
        try {
            return readServerArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerArray(String[] serverArray) throws UaException {
        try {
            writeServerArrayAsync(serverArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<String[]> readServerArrayAsync() {
        return getServerArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeServerArrayAsync(String[] serverArray) {
        DataValue value = DataValue.valueOnly(new Variant(serverArray));
        return getServerArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public PropertyTypeNode getServerArrayNode() throws UaException {
        try {
            return getServerArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<PropertyTypeNode> getServerArrayNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String[] getNamespaceArray() throws UaException {
        PropertyTypeNode node = getNamespaceArrayNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setNamespaceArray(String[] namespaceArray) throws UaException {
        PropertyTypeNode node = getNamespaceArrayNode();
        node.setValue(new Variant(namespaceArray));
    }

    @Override
    public String[] readNamespaceArray() throws UaException {
        try {
            return readNamespaceArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNamespaceArray(String[] namespaceArray) throws UaException {
        try {
            writeNamespaceArrayAsync(namespaceArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<String[]> readNamespaceArrayAsync() {
        return getNamespaceArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeNamespaceArrayAsync(String[] namespaceArray) {
        DataValue value = DataValue.valueOnly(new Variant(namespaceArray));
        return getNamespaceArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public PropertyTypeNode getNamespaceArrayNode() throws UaException {
        try {
            return getNamespaceArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<PropertyTypeNode> getNamespaceArrayNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespaceArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UByte getServiceLevel() throws UaException {
        PropertyTypeNode node = getServiceLevelNode();
        return (UByte) node.getValue().getValue().getValue();
    }

    @Override
    public void setServiceLevel(UByte serviceLevel) throws UaException {
        PropertyTypeNode node = getServiceLevelNode();
        node.setValue(new Variant(serviceLevel));
    }

    @Override
    public UByte readServiceLevel() throws UaException {
        try {
            return readServiceLevelAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServiceLevel(UByte serviceLevel) throws UaException {
        try {
            writeServiceLevelAsync(serviceLevel).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<UByte> readServiceLevelAsync() {
        return getServiceLevelNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UByte) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeServiceLevelAsync(UByte serviceLevel) {
        DataValue value = DataValue.valueOnly(new Variant(serviceLevel));
        return getServiceLevelNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public PropertyTypeNode getServiceLevelNode() throws UaException {
        try {
            return getServiceLevelNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<PropertyTypeNode> getServiceLevelNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServiceLevel", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getAuditing() throws UaException {
        PropertyTypeNode node = getAuditingNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setAuditing(Boolean auditing) throws UaException {
        PropertyTypeNode node = getAuditingNode();
        node.setValue(new Variant(auditing));
    }

    @Override
    public Boolean readAuditing() throws UaException {
        try {
            return readAuditingAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAuditing(Boolean auditing) throws UaException {
        try {
            writeAuditingAsync(auditing).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Boolean> readAuditingAsync() {
        return getAuditingNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeAuditingAsync(Boolean auditing) {
        DataValue value = DataValue.valueOnly(new Variant(auditing));
        return getAuditingNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public PropertyTypeNode getAuditingNode() throws UaException {
        try {
            return getAuditingNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<PropertyTypeNode> getAuditingNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Auditing", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getEstimatedReturnTime() throws UaException {
        PropertyTypeNode node = getEstimatedReturnTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setEstimatedReturnTime(DateTime estimatedReturnTime) throws UaException {
        PropertyTypeNode node = getEstimatedReturnTimeNode();
        node.setValue(new Variant(estimatedReturnTime));
    }

    @Override
    public DateTime readEstimatedReturnTime() throws UaException {
        try {
            return readEstimatedReturnTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEstimatedReturnTime(DateTime estimatedReturnTime) throws UaException {
        try {
            writeEstimatedReturnTimeAsync(estimatedReturnTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<DateTime> readEstimatedReturnTimeAsync() {
        return getEstimatedReturnTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeEstimatedReturnTimeAsync(DateTime estimatedReturnTime) {
        DataValue value = DataValue.valueOnly(new Variant(estimatedReturnTime));
        return getEstimatedReturnTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public PropertyTypeNode getEstimatedReturnTimeNode() throws UaException {
        try {
            return getEstimatedReturnTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<PropertyTypeNode> getEstimatedReturnTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EstimatedReturnTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public ServerStatusDataType getServerStatus() throws UaException {
        ServerStatusTypeNode node = getServerStatusNode();
        return (ServerStatusDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerStatus(ServerStatusDataType serverStatus) throws UaException {
        ServerStatusTypeNode node = getServerStatusNode();
        node.setValue(new Variant(serverStatus));
    }

    @Override
    public ServerStatusDataType readServerStatus() throws UaException {
        try {
            return readServerStatusAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerStatus(ServerStatusDataType serverStatus) throws UaException {
        try {
            writeServerStatusAsync(serverStatus).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<ServerStatusDataType> readServerStatusAsync() {
        return getServerStatusNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ServerStatusDataType) v.getValue().getValue());
    }

    public CompletableFuture<Unit> writeServerStatusAsync(ServerStatusDataType serverStatus) {
        DataValue value = DataValue.valueOnly(new Variant(serverStatus));
        return getServerStatusNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });;
    }

    public ServerStatusTypeNode getServerStatusNode() throws UaException {
        try {
            return getServerStatusNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<ServerStatusTypeNode> getServerStatusNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerStatus", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2138"), false);
        return future.thenApply(node -> (ServerStatusTypeNode) node);
    }

    public ServerCapabilitiesTypeNode getServerCapabilitiesNode() throws UaException {
        try {
            return getServerCapabilitiesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<ServerCapabilitiesTypeNode> getServerCapabilitiesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerCapabilities", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2013"), false);
        return future.thenApply(node -> (ServerCapabilitiesTypeNode) node);
    }

    public ServerDiagnosticsTypeNode getServerDiagnosticsNode() throws UaException {
        try {
            return getServerDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<ServerDiagnosticsTypeNode> getServerDiagnosticsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2020"), false);
        return future.thenApply(node -> (ServerDiagnosticsTypeNode) node);
    }

    public VendorServerInfoTypeNode getVendorServerInfoNode() throws UaException {
        try {
            return getVendorServerInfoNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<VendorServerInfoTypeNode> getVendorServerInfoNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "VendorServerInfo", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2033"), false);
        return future.thenApply(node -> (VendorServerInfoTypeNode) node);
    }

    public ServerRedundancyTypeNode getServerRedundancyNode() throws UaException {
        try {
            return getServerRedundancyNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<ServerRedundancyTypeNode> getServerRedundancyNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerRedundancy", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2034"), false);
        return future.thenApply(node -> (ServerRedundancyTypeNode) node);
    }

    public NamespacesTypeNode getNamespacesNode() throws UaException {
        try {
            return getNamespacesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<NamespacesTypeNode> getNamespacesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Namespaces", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11645"), false);
        return future.thenApply(node -> (NamespacesTypeNode) node);
    }
}
