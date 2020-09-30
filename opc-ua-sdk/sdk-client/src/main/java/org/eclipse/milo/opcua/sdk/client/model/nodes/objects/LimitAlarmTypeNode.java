package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.LimitAlarmType;
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

public class LimitAlarmTypeNode extends AlarmConditionTypeNode implements LimitAlarmType {
    public LimitAlarmTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Double getHighHighLimit() throws UaException {
        PropertyTypeNode node = getHighHighLimitNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setHighHighLimit(Double highHighLimit) throws UaException {
        PropertyTypeNode node = getHighHighLimitNode();
        node.setValue(new Variant(highHighLimit));
    }

    @Override
    public Double readHighHighLimit() throws UaException {
        try {
            return readHighHighLimitAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHighHighLimit(Double highHighLimit) throws UaException {
        try {
            writeHighHighLimitAsync(highHighLimit).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readHighHighLimitAsync() {
        return getHighHighLimitNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeHighHighLimitAsync(Double highHighLimit) {
        DataValue value = DataValue.valueOnly(new Variant(highHighLimit));
        return getHighHighLimitNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getHighHighLimitNode() throws UaException {
        try {
            return getHighHighLimitNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getHighHighLimitNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HighHighLimit", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getHighLimit() throws UaException {
        PropertyTypeNode node = getHighLimitNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setHighLimit(Double highLimit) throws UaException {
        PropertyTypeNode node = getHighLimitNode();
        node.setValue(new Variant(highLimit));
    }

    @Override
    public Double readHighLimit() throws UaException {
        try {
            return readHighLimitAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHighLimit(Double highLimit) throws UaException {
        try {
            writeHighLimitAsync(highLimit).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readHighLimitAsync() {
        return getHighLimitNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeHighLimitAsync(Double highLimit) {
        DataValue value = DataValue.valueOnly(new Variant(highLimit));
        return getHighLimitNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getHighLimitNode() throws UaException {
        try {
            return getHighLimitNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getHighLimitNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HighLimit", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getLowLimit() throws UaException {
        PropertyTypeNode node = getLowLimitNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setLowLimit(Double lowLimit) throws UaException {
        PropertyTypeNode node = getLowLimitNode();
        node.setValue(new Variant(lowLimit));
    }

    @Override
    public Double readLowLimit() throws UaException {
        try {
            return readLowLimitAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLowLimit(Double lowLimit) throws UaException {
        try {
            writeLowLimitAsync(lowLimit).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readLowLimitAsync() {
        return getLowLimitNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLowLimitAsync(Double lowLimit) {
        DataValue value = DataValue.valueOnly(new Variant(lowLimit));
        return getLowLimitNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getLowLimitNode() throws UaException {
        try {
            return getLowLimitNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getLowLimitNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LowLimit", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getLowLowLimit() throws UaException {
        PropertyTypeNode node = getLowLowLimitNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setLowLowLimit(Double lowLowLimit) throws UaException {
        PropertyTypeNode node = getLowLowLimitNode();
        node.setValue(new Variant(lowLowLimit));
    }

    @Override
    public Double readLowLowLimit() throws UaException {
        try {
            return readLowLowLimitAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLowLowLimit(Double lowLowLimit) throws UaException {
        try {
            writeLowLowLimitAsync(lowLowLimit).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readLowLowLimitAsync() {
        return getLowLowLimitNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLowLowLimitAsync(Double lowLowLimit) {
        DataValue value = DataValue.valueOnly(new Variant(lowLowLimit));
        return getLowLowLimitNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getLowLowLimitNode() throws UaException {
        try {
            return getLowLowLimitNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getLowLowLimitNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LowLowLimit", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
