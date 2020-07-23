package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SamplingIntervalDiagnosticsType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class SamplingIntervalDiagnosticsTypeNode extends BaseDataVariableTypeNode implements SamplingIntervalDiagnosticsType {
    public SamplingIntervalDiagnosticsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                               UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                               double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public Double getSamplingInterval() throws UaException {
        BaseDataVariableTypeNode node = getSamplingIntervalNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setSamplingInterval(Double samplingInterval) throws UaException {
        BaseDataVariableTypeNode node = getSamplingIntervalNode();
        node.setValue(new Variant(samplingInterval));
    }

    @Override
    public Double readSamplingInterval() throws UaException {
        try {
            return readSamplingIntervalAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSamplingInterval(Double samplingInterval) throws UaException {
        try {
            writeSamplingIntervalAsync(samplingInterval).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readSamplingIntervalAsync() {
        return getSamplingIntervalNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSamplingIntervalAsync(Double samplingInterval) {
        DataValue value = DataValue.valueOnly(new Variant(samplingInterval));
        return getSamplingIntervalNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSamplingIntervalNode() throws UaException {
        try {
            return getSamplingIntervalNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSamplingIntervalNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SamplingInterval", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getSampledMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getSampledMonitoredItemsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setSampledMonitoredItemsCount(UInteger sampledMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getSampledMonitoredItemsCountNode();
        node.setValue(new Variant(sampledMonitoredItemsCount));
    }

    @Override
    public UInteger readSampledMonitoredItemsCount() throws UaException {
        try {
            return readSampledMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSampledMonitoredItemsCount(UInteger sampledMonitoredItemsCount) throws
        UaException {
        try {
            writeSampledMonitoredItemsCountAsync(sampledMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readSampledMonitoredItemsCountAsync() {
        return getSampledMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSampledMonitoredItemsCountAsync(
        UInteger sampledMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(sampledMonitoredItemsCount));
        return getSampledMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSampledMonitoredItemsCountNode() throws UaException {
        try {
            return getSampledMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSampledMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SampledMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxSampledMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getMaxSampledMonitoredItemsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxSampledMonitoredItemsCount(UInteger maxSampledMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getMaxSampledMonitoredItemsCountNode();
        node.setValue(new Variant(maxSampledMonitoredItemsCount));
    }

    @Override
    public UInteger readMaxSampledMonitoredItemsCount() throws UaException {
        try {
            return readMaxSampledMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxSampledMonitoredItemsCount(UInteger maxSampledMonitoredItemsCount) throws
        UaException {
        try {
            writeMaxSampledMonitoredItemsCountAsync(maxSampledMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxSampledMonitoredItemsCountAsync() {
        return getMaxSampledMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxSampledMonitoredItemsCountAsync(
        UInteger maxSampledMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(maxSampledMonitoredItemsCount));
        return getMaxSampledMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMaxSampledMonitoredItemsCountNode() throws UaException {
        try {
            return getMaxSampledMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxSampledMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxSampledMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getDisabledMonitoredItemsSamplingCount() throws UaException {
        BaseDataVariableTypeNode node = getDisabledMonitoredItemsSamplingCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setDisabledMonitoredItemsSamplingCount(UInteger disabledMonitoredItemsSamplingCount)
        throws UaException {
        BaseDataVariableTypeNode node = getDisabledMonitoredItemsSamplingCountNode();
        node.setValue(new Variant(disabledMonitoredItemsSamplingCount));
    }

    @Override
    public UInteger readDisabledMonitoredItemsSamplingCount() throws UaException {
        try {
            return readDisabledMonitoredItemsSamplingCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDisabledMonitoredItemsSamplingCount(UInteger disabledMonitoredItemsSamplingCount)
        throws UaException {
        try {
            writeDisabledMonitoredItemsSamplingCountAsync(disabledMonitoredItemsSamplingCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readDisabledMonitoredItemsSamplingCountAsync() {
        return getDisabledMonitoredItemsSamplingCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDisabledMonitoredItemsSamplingCountAsync(
        UInteger disabledMonitoredItemsSamplingCount) {
        DataValue value = DataValue.valueOnly(new Variant(disabledMonitoredItemsSamplingCount));
        return getDisabledMonitoredItemsSamplingCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDisabledMonitoredItemsSamplingCountNode() throws UaException {
        try {
            return getDisabledMonitoredItemsSamplingCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDisabledMonitoredItemsSamplingCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DisabledMonitoredItemsSamplingCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
