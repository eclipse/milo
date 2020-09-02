package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerDiagnosticsType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ServerDiagnosticsTypeNode extends BaseObjectTypeNode implements ServerDiagnosticsType {
    public ServerDiagnosticsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                     QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                     UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getEnabledFlag() throws UaException {
        PropertyTypeNode node = getEnabledFlagNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnabledFlag(Boolean enabledFlag) throws UaException {
        PropertyTypeNode node = getEnabledFlagNode();
        node.setValue(new Variant(enabledFlag));
    }

    @Override
    public Boolean readEnabledFlag() throws UaException {
        try {
            return readEnabledFlagAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnabledFlag(Boolean enabledFlag) throws UaException {
        try {
            writeEnabledFlagAsync(enabledFlag).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readEnabledFlagAsync() {
        return getEnabledFlagNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEnabledFlagAsync(Boolean enabledFlag) {
        DataValue value = DataValue.valueOnly(new Variant(enabledFlag));
        return getEnabledFlagNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public PropertyTypeNode getEnabledFlagNode() throws UaException {
        try {
            return getEnabledFlagNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEnabledFlagNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnabledFlag", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public ServerDiagnosticsSummaryDataType getServerDiagnosticsSummary() throws UaException {
        ServerDiagnosticsSummaryTypeNode node = getServerDiagnosticsSummaryNode();
        return cast(node.getValue().getValue().getValue(), ServerDiagnosticsSummaryDataType.class);
    }

    @Override
    public void setServerDiagnosticsSummary(ServerDiagnosticsSummaryDataType serverDiagnosticsSummary)
        throws UaException {
        ServerDiagnosticsSummaryTypeNode node = getServerDiagnosticsSummaryNode();
        ExtensionObject value = ExtensionObject.encode(client.getSerializationContext(), serverDiagnosticsSummary);
        node.setValue(new Variant(value));
    }

    @Override
    public ServerDiagnosticsSummaryDataType readServerDiagnosticsSummary() throws UaException {
        try {
            return readServerDiagnosticsSummaryAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerDiagnosticsSummary(
        ServerDiagnosticsSummaryDataType serverDiagnosticsSummary) throws UaException {
        try {
            writeServerDiagnosticsSummaryAsync(serverDiagnosticsSummary).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServerDiagnosticsSummaryDataType> readServerDiagnosticsSummaryAsync(
    ) {
        return getServerDiagnosticsSummaryNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServerDiagnosticsSummaryDataType.class));
    }

    @Override
    public CompletableFuture<Unit> writeServerDiagnosticsSummaryAsync(
        ServerDiagnosticsSummaryDataType serverDiagnosticsSummary) {
        ExtensionObject encoded = ExtensionObject.encode(client.getSerializationContext(), serverDiagnosticsSummary);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getServerDiagnosticsSummaryNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public ServerDiagnosticsSummaryTypeNode getServerDiagnosticsSummaryNode() throws UaException {
        try {
            return getServerDiagnosticsSummaryNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServerDiagnosticsSummaryTypeNode> getServerDiagnosticsSummaryNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerDiagnosticsSummary", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ServerDiagnosticsSummaryTypeNode) node);
    }

    @Override
    public SamplingIntervalDiagnosticsDataType[] getSamplingIntervalDiagnosticsArray() throws
        UaException {
        SamplingIntervalDiagnosticsArrayTypeNode node = getSamplingIntervalDiagnosticsArrayNode();
        return cast(node.getValue().getValue().getValue(), SamplingIntervalDiagnosticsDataType[].class);
    }

    @Override
    public void setSamplingIntervalDiagnosticsArray(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray) throws UaException {
        SamplingIntervalDiagnosticsArrayTypeNode node = getSamplingIntervalDiagnosticsArrayNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), samplingIntervalDiagnosticsArray);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SamplingIntervalDiagnosticsDataType[] readSamplingIntervalDiagnosticsArray() throws
        UaException {
        try {
            return readSamplingIntervalDiagnosticsArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSamplingIntervalDiagnosticsArray(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray) throws UaException {
        try {
            writeSamplingIntervalDiagnosticsArrayAsync(samplingIntervalDiagnosticsArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SamplingIntervalDiagnosticsDataType[]> readSamplingIntervalDiagnosticsArrayAsync(
    ) {
        return getSamplingIntervalDiagnosticsArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SamplingIntervalDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<Unit> writeSamplingIntervalDiagnosticsArrayAsync(
        SamplingIntervalDiagnosticsDataType[] samplingIntervalDiagnosticsArray) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), samplingIntervalDiagnosticsArray);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSamplingIntervalDiagnosticsArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public SamplingIntervalDiagnosticsArrayTypeNode getSamplingIntervalDiagnosticsArrayNode() throws
        UaException {
        try {
            return getSamplingIntervalDiagnosticsArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SamplingIntervalDiagnosticsArrayTypeNode> getSamplingIntervalDiagnosticsArrayNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SamplingIntervalDiagnosticsArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SamplingIntervalDiagnosticsArrayTypeNode) node);
    }

    @Override
    public SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() throws UaException {
        SubscriptionDiagnosticsArrayTypeNode node = getSubscriptionDiagnosticsArrayNode();
        return cast(node.getValue().getValue().getValue(), SubscriptionDiagnosticsDataType[].class);
    }

    @Override
    public void setSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException {
        SubscriptionDiagnosticsArrayTypeNode node = getSubscriptionDiagnosticsArrayNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), subscriptionDiagnosticsArray);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SubscriptionDiagnosticsDataType[] readSubscriptionDiagnosticsArray() throws UaException {
        try {
            return readSubscriptionDiagnosticsArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException {
        try {
            writeSubscriptionDiagnosticsArrayAsync(subscriptionDiagnosticsArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsDataType[]> readSubscriptionDiagnosticsArrayAsync(
    ) {
        return getSubscriptionDiagnosticsArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SubscriptionDiagnosticsDataType[].class));
    }

    @Override
    public CompletableFuture<Unit> writeSubscriptionDiagnosticsArrayAsync(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), subscriptionDiagnosticsArray);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSubscriptionDiagnosticsArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value))
            .thenCompose(statusCode -> {
                if (statusCode != null && statusCode.isBad()) {
                    return FutureUtils.failedUaFuture(statusCode);
                } else {
                    return CompletableFuture.completedFuture(Unit.VALUE);
                }
            });
    }

    @Override
    public SubscriptionDiagnosticsArrayTypeNode getSubscriptionDiagnosticsArrayNode() throws
        UaException {
        try {
            return getSubscriptionDiagnosticsArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsArrayTypeNode> getSubscriptionDiagnosticsArrayNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SubscriptionDiagnosticsArrayTypeNode) node);
    }

    public SessionsDiagnosticsSummaryTypeNode getSessionsDiagnosticsSummaryNode() throws UaException {
        try {
            return getSessionsDiagnosticsSummaryNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends SessionsDiagnosticsSummaryTypeNode> getSessionsDiagnosticsSummaryNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionsDiagnosticsSummary", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SessionsDiagnosticsSummaryTypeNode) node);
    }
}
