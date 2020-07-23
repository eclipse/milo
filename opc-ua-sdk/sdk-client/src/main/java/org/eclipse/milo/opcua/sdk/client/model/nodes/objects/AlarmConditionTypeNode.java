package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AlarmConditionType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class AlarmConditionTypeNode extends AcknowledgeableConditionTypeNode implements AlarmConditionType {
    public AlarmConditionTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                  QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                  UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getInputNode() throws UaException {
        PropertyTypeNode node = getInputNodeNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setInputNode(NodeId inputNode) throws UaException {
        PropertyTypeNode node = getInputNodeNode();
        node.setValue(new Variant(inputNode));
    }

    @Override
    public NodeId readInputNode() throws UaException {
        try {
            return readInputNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInputNode(NodeId inputNode) throws UaException {
        try {
            writeInputNodeAsync(inputNode).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readInputNodeAsync() {
        return getInputNodeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInputNodeAsync(NodeId inputNode) {
        DataValue value = DataValue.valueOnly(new Variant(inputNode));
        return getInputNodeNodeAsync()
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
    public PropertyTypeNode getInputNodeNode() throws UaException {
        try {
            return getInputNodeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInputNodeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InputNode", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getSuppressedOrShelved() throws UaException {
        PropertyTypeNode node = getSuppressedOrShelvedNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setSuppressedOrShelved(Boolean suppressedOrShelved) throws UaException {
        PropertyTypeNode node = getSuppressedOrShelvedNode();
        node.setValue(new Variant(suppressedOrShelved));
    }

    @Override
    public Boolean readSuppressedOrShelved() throws UaException {
        try {
            return readSuppressedOrShelvedAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSuppressedOrShelved(Boolean suppressedOrShelved) throws UaException {
        try {
            writeSuppressedOrShelvedAsync(suppressedOrShelved).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readSuppressedOrShelvedAsync() {
        return getSuppressedOrShelvedNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSuppressedOrShelvedAsync(Boolean suppressedOrShelved) {
        DataValue value = DataValue.valueOnly(new Variant(suppressedOrShelved));
        return getSuppressedOrShelvedNodeAsync()
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
    public PropertyTypeNode getSuppressedOrShelvedNode() throws UaException {
        try {
            return getSuppressedOrShelvedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSuppressedOrShelvedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SuppressedOrShelved", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getMaxTimeShelved() throws UaException {
        PropertyTypeNode node = getMaxTimeShelvedNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxTimeShelved(Double maxTimeShelved) throws UaException {
        PropertyTypeNode node = getMaxTimeShelvedNode();
        node.setValue(new Variant(maxTimeShelved));
    }

    @Override
    public Double readMaxTimeShelved() throws UaException {
        try {
            return readMaxTimeShelvedAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxTimeShelved(Double maxTimeShelved) throws UaException {
        try {
            writeMaxTimeShelvedAsync(maxTimeShelved).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readMaxTimeShelvedAsync() {
        return getMaxTimeShelvedNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxTimeShelvedAsync(Double maxTimeShelved) {
        DataValue value = DataValue.valueOnly(new Variant(maxTimeShelved));
        return getMaxTimeShelvedNodeAsync()
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
    public PropertyTypeNode getMaxTimeShelvedNode() throws UaException {
        try {
            return getMaxTimeShelvedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxTimeShelvedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxTimeShelved", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getEnabledState() throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnabledState(LocalizedText enabledState) throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        node.setValue(new Variant(enabledState));
    }

    @Override
    public LocalizedText readEnabledState() throws UaException {
        try {
            return readEnabledStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnabledState(LocalizedText enabledState) throws UaException {
        try {
            writeEnabledStateAsync(enabledState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readEnabledStateAsync() {
        return getEnabledStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEnabledStateAsync(LocalizedText enabledState) {
        DataValue value = DataValue.valueOnly(new Variant(enabledState));
        return getEnabledStateNodeAsync()
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
    public TwoStateVariableTypeNode getEnabledStateNode() throws UaException {
        try {
            return getEnabledStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getEnabledStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnabledState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getActiveState() throws UaException {
        TwoStateVariableTypeNode node = getActiveStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setActiveState(LocalizedText activeState) throws UaException {
        TwoStateVariableTypeNode node = getActiveStateNode();
        node.setValue(new Variant(activeState));
    }

    @Override
    public LocalizedText readActiveState() throws UaException {
        try {
            return readActiveStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeActiveState(LocalizedText activeState) throws UaException {
        try {
            writeActiveStateAsync(activeState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readActiveStateAsync() {
        return getActiveStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeActiveStateAsync(LocalizedText activeState) {
        DataValue value = DataValue.valueOnly(new Variant(activeState));
        return getActiveStateNodeAsync()
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
    public TwoStateVariableTypeNode getActiveStateNode() throws UaException {
        try {
            return getActiveStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getActiveStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ActiveState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getSuppressedState() throws UaException {
        TwoStateVariableTypeNode node = getSuppressedStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setSuppressedState(LocalizedText suppressedState) throws UaException {
        TwoStateVariableTypeNode node = getSuppressedStateNode();
        node.setValue(new Variant(suppressedState));
    }

    @Override
    public LocalizedText readSuppressedState() throws UaException {
        try {
            return readSuppressedStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSuppressedState(LocalizedText suppressedState) throws UaException {
        try {
            writeSuppressedStateAsync(suppressedState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readSuppressedStateAsync() {
        return getSuppressedStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSuppressedStateAsync(LocalizedText suppressedState) {
        DataValue value = DataValue.valueOnly(new Variant(suppressedState));
        return getSuppressedStateNodeAsync()
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
    public TwoStateVariableTypeNode getSuppressedStateNode() throws UaException {
        try {
            return getSuppressedStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getSuppressedStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SuppressedState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    public ShelvedStateMachineTypeNode getShelvingStateNode() throws UaException {
        try {
            return getShelvingStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends ShelvedStateMachineTypeNode> getShelvingStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ShelvingState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ShelvedStateMachineTypeNode) node);
    }
}
