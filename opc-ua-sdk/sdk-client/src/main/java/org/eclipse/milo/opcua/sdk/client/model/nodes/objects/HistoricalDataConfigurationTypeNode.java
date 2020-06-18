package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class HistoricalDataConfigurationTypeNode extends BaseObjectTypeNode implements HistoricalDataConfigurationType {
    public HistoricalDataConfigurationTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getStepped() throws UaException {
        PropertyTypeNode node = getSteppedNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setStepped(Boolean stepped) throws UaException {
        PropertyTypeNode node = getSteppedNode();
        node.setValue(new Variant(stepped));
    }

    @Override
    public Boolean readStepped() throws UaException {
        try {
            return readSteppedAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStepped(Boolean stepped) throws UaException {
        try {
            writeSteppedAsync(stepped).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readSteppedAsync() {
        return getSteppedNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSteppedAsync(Boolean stepped) {
        DataValue value = DataValue.valueOnly(new Variant(stepped));
        return getSteppedNodeAsync()
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
    public PropertyTypeNode getSteppedNode() throws UaException {
        try {
            return getSteppedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSteppedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Stepped", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getDefinition() throws UaException {
        PropertyTypeNode node = getDefinitionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setDefinition(String definition) throws UaException {
        PropertyTypeNode node = getDefinitionNode();
        node.setValue(new Variant(definition));
    }

    @Override
    public String readDefinition() throws UaException {
        try {
            return readDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDefinition(String definition) throws UaException {
        try {
            writeDefinitionAsync(definition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readDefinitionAsync() {
        return getDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDefinitionAsync(String definition) {
        DataValue value = DataValue.valueOnly(new Variant(definition));
        return getDefinitionNodeAsync()
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
    public PropertyTypeNode getDefinitionNode() throws UaException {
        try {
            return getDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Definition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getMaxTimeInterval() throws UaException {
        PropertyTypeNode node = getMaxTimeIntervalNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxTimeInterval(Double maxTimeInterval) throws UaException {
        PropertyTypeNode node = getMaxTimeIntervalNode();
        node.setValue(new Variant(maxTimeInterval));
    }

    @Override
    public Double readMaxTimeInterval() throws UaException {
        try {
            return readMaxTimeIntervalAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxTimeInterval(Double maxTimeInterval) throws UaException {
        try {
            writeMaxTimeIntervalAsync(maxTimeInterval).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readMaxTimeIntervalAsync() {
        return getMaxTimeIntervalNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxTimeIntervalAsync(Double maxTimeInterval) {
        DataValue value = DataValue.valueOnly(new Variant(maxTimeInterval));
        return getMaxTimeIntervalNodeAsync()
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
    public PropertyTypeNode getMaxTimeIntervalNode() throws UaException {
        try {
            return getMaxTimeIntervalNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxTimeIntervalNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxTimeInterval", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getMinTimeInterval() throws UaException {
        PropertyTypeNode node = getMinTimeIntervalNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setMinTimeInterval(Double minTimeInterval) throws UaException {
        PropertyTypeNode node = getMinTimeIntervalNode();
        node.setValue(new Variant(minTimeInterval));
    }

    @Override
    public Double readMinTimeInterval() throws UaException {
        try {
            return readMinTimeIntervalAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMinTimeInterval(Double minTimeInterval) throws UaException {
        try {
            writeMinTimeIntervalAsync(minTimeInterval).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readMinTimeIntervalAsync() {
        return getMinTimeIntervalNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMinTimeIntervalAsync(Double minTimeInterval) {
        DataValue value = DataValue.valueOnly(new Variant(minTimeInterval));
        return getMinTimeIntervalNodeAsync()
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
    public PropertyTypeNode getMinTimeIntervalNode() throws UaException {
        try {
            return getMinTimeIntervalNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMinTimeIntervalNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MinTimeInterval", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getExceptionDeviation() throws UaException {
        PropertyTypeNode node = getExceptionDeviationNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setExceptionDeviation(Double exceptionDeviation) throws UaException {
        PropertyTypeNode node = getExceptionDeviationNode();
        node.setValue(new Variant(exceptionDeviation));
    }

    @Override
    public Double readExceptionDeviation() throws UaException {
        try {
            return readExceptionDeviationAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeExceptionDeviation(Double exceptionDeviation) throws UaException {
        try {
            writeExceptionDeviationAsync(exceptionDeviation).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readExceptionDeviationAsync() {
        return getExceptionDeviationNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeExceptionDeviationAsync(Double exceptionDeviation) {
        DataValue value = DataValue.valueOnly(new Variant(exceptionDeviation));
        return getExceptionDeviationNodeAsync()
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
    public PropertyTypeNode getExceptionDeviationNode() throws UaException {
        try {
            return getExceptionDeviationNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getExceptionDeviationNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ExceptionDeviation", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public ExceptionDeviationFormat getExceptionDeviationFormat() throws UaException {
        PropertyTypeNode node = getExceptionDeviationFormatNode();
        return (ExceptionDeviationFormat) node.getValue().getValue().getValue();
    }

    @Override
    public void setExceptionDeviationFormat(ExceptionDeviationFormat exceptionDeviationFormat) throws
        UaException {
        PropertyTypeNode node = getExceptionDeviationFormatNode();
        node.setValue(new Variant(exceptionDeviationFormat));
    }

    @Override
    public ExceptionDeviationFormat readExceptionDeviationFormat() throws UaException {
        try {
            return readExceptionDeviationFormatAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeExceptionDeviationFormat(ExceptionDeviationFormat exceptionDeviationFormat)
        throws UaException {
        try {
            writeExceptionDeviationFormatAsync(exceptionDeviationFormat).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ExceptionDeviationFormat> readExceptionDeviationFormatAsync() {
        return getExceptionDeviationFormatNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ExceptionDeviationFormat) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeExceptionDeviationFormatAsync(
        ExceptionDeviationFormat exceptionDeviationFormat) {
        DataValue value = DataValue.valueOnly(new Variant(exceptionDeviationFormat));
        return getExceptionDeviationFormatNodeAsync()
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
    public PropertyTypeNode getExceptionDeviationFormatNode() throws UaException {
        try {
            return getExceptionDeviationFormatNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getExceptionDeviationFormatNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ExceptionDeviationFormat", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getStartOfArchive() throws UaException {
        PropertyTypeNode node = getStartOfArchiveNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setStartOfArchive(DateTime startOfArchive) throws UaException {
        PropertyTypeNode node = getStartOfArchiveNode();
        node.setValue(new Variant(startOfArchive));
    }

    @Override
    public DateTime readStartOfArchive() throws UaException {
        try {
            return readStartOfArchiveAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStartOfArchive(DateTime startOfArchive) throws UaException {
        try {
            writeStartOfArchiveAsync(startOfArchive).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readStartOfArchiveAsync() {
        return getStartOfArchiveNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeStartOfArchiveAsync(DateTime startOfArchive) {
        DataValue value = DataValue.valueOnly(new Variant(startOfArchive));
        return getStartOfArchiveNodeAsync()
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
    public PropertyTypeNode getStartOfArchiveNode() throws UaException {
        try {
            return getStartOfArchiveNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getStartOfArchiveNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "StartOfArchive", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getStartOfOnlineArchive() throws UaException {
        PropertyTypeNode node = getStartOfOnlineArchiveNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setStartOfOnlineArchive(DateTime startOfOnlineArchive) throws UaException {
        PropertyTypeNode node = getStartOfOnlineArchiveNode();
        node.setValue(new Variant(startOfOnlineArchive));
    }

    @Override
    public DateTime readStartOfOnlineArchive() throws UaException {
        try {
            return readStartOfOnlineArchiveAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStartOfOnlineArchive(DateTime startOfOnlineArchive) throws UaException {
        try {
            writeStartOfOnlineArchiveAsync(startOfOnlineArchive).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readStartOfOnlineArchiveAsync() {
        return getStartOfOnlineArchiveNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeStartOfOnlineArchiveAsync(DateTime startOfOnlineArchive) {
        DataValue value = DataValue.valueOnly(new Variant(startOfOnlineArchive));
        return getStartOfOnlineArchiveNodeAsync()
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
    public PropertyTypeNode getStartOfOnlineArchiveNode() throws UaException {
        try {
            return getStartOfOnlineArchiveNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getStartOfOnlineArchiveNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "StartOfOnlineArchive", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    public AggregateConfigurationTypeNode getAggregateConfigurationNode() throws UaException {
        try {
            return getAggregateConfigurationNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends AggregateConfigurationTypeNode> getAggregateConfigurationNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AggregateConfiguration", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (AggregateConfigurationTypeNode) node);
    }

    public FolderTypeNode getAggregateFunctionsNode() throws UaException {
        try {
            return getAggregateFunctionsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends FolderTypeNode> getAggregateFunctionsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AggregateFunctions", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FolderTypeNode) node);
    }
}
