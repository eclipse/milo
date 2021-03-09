package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonExclusiveLimitAlarmType;
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

public class NonExclusiveLimitAlarmTypeNode extends LimitAlarmTypeNode implements NonExclusiveLimitAlarmType {
    public NonExclusiveLimitAlarmTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
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
    public CompletableFuture<StatusCode> writeActiveStateAsync(LocalizedText activeState) {
        DataValue value = DataValue.valueOnly(new Variant(activeState));
        return getActiveStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
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
    public LocalizedText getHighHighState() throws UaException {
        TwoStateVariableTypeNode node = getHighHighStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setHighHighState(LocalizedText highHighState) throws UaException {
        TwoStateVariableTypeNode node = getHighHighStateNode();
        node.setValue(new Variant(highHighState));
    }

    @Override
    public LocalizedText readHighHighState() throws UaException {
        try {
            return readHighHighStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHighHighState(LocalizedText highHighState) throws UaException {
        try {
            writeHighHighStateAsync(highHighState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readHighHighStateAsync() {
        return getHighHighStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeHighHighStateAsync(LocalizedText highHighState) {
        DataValue value = DataValue.valueOnly(new Variant(highHighState));
        return getHighHighStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getHighHighStateNode() throws UaException {
        try {
            return getHighHighStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getHighHighStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HighHighState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getHighState() throws UaException {
        TwoStateVariableTypeNode node = getHighStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setHighState(LocalizedText highState) throws UaException {
        TwoStateVariableTypeNode node = getHighStateNode();
        node.setValue(new Variant(highState));
    }

    @Override
    public LocalizedText readHighState() throws UaException {
        try {
            return readHighStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHighState(LocalizedText highState) throws UaException {
        try {
            writeHighStateAsync(highState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readHighStateAsync() {
        return getHighStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeHighStateAsync(LocalizedText highState) {
        DataValue value = DataValue.valueOnly(new Variant(highState));
        return getHighStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getHighStateNode() throws UaException {
        try {
            return getHighStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getHighStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HighState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getLowState() throws UaException {
        TwoStateVariableTypeNode node = getLowStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setLowState(LocalizedText lowState) throws UaException {
        TwoStateVariableTypeNode node = getLowStateNode();
        node.setValue(new Variant(lowState));
    }

    @Override
    public LocalizedText readLowState() throws UaException {
        try {
            return readLowStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLowState(LocalizedText lowState) throws UaException {
        try {
            writeLowStateAsync(lowState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readLowStateAsync() {
        return getLowStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLowStateAsync(LocalizedText lowState) {
        DataValue value = DataValue.valueOnly(new Variant(lowState));
        return getLowStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getLowStateNode() throws UaException {
        try {
            return getLowStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getLowStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LowState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getLowLowState() throws UaException {
        TwoStateVariableTypeNode node = getLowLowStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setLowLowState(LocalizedText lowLowState) throws UaException {
        TwoStateVariableTypeNode node = getLowLowStateNode();
        node.setValue(new Variant(lowLowState));
    }

    @Override
    public LocalizedText readLowLowState() throws UaException {
        try {
            return readLowLowStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLowLowState(LocalizedText lowLowState) throws UaException {
        try {
            writeLowLowStateAsync(lowLowState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readLowLowStateAsync() {
        return getLowLowStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLowLowStateAsync(LocalizedText lowLowState) {
        DataValue value = DataValue.valueOnly(new Variant(lowLowState));
        return getLowLowStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getLowLowStateNode() throws UaException {
        try {
            return getLowLowStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getLowLowStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LowLowState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }
}
