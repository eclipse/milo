package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class TwoStateVariableTypeNode extends StateVariableTypeNode implements TwoStateVariableType {
    public TwoStateVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                    Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                    Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public Boolean getId() throws UaException {
        PropertyTypeNode node = getIdNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setId(Boolean id) throws UaException {
        PropertyTypeNode node = getIdNode();
        node.setValue(new Variant(id));
    }

    @Override
    public Boolean readId() throws UaException {
        try {
            return readIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeId(Boolean id) throws UaException {
        try {
            writeIdAsync(id).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readIdAsync() {
        return getIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeIdAsync(Boolean id) {
        DataValue value = DataValue.valueOnly(new Variant(id));
        return getIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getIdNode() throws UaException {
        try {
            return getIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Id", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getTransitionTime() throws UaException {
        PropertyTypeNode node = getTransitionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransitionTime(DateTime transitionTime) throws UaException {
        PropertyTypeNode node = getTransitionTimeNode();
        node.setValue(new Variant(transitionTime));
    }

    @Override
    public DateTime readTransitionTime() throws UaException {
        try {
            return readTransitionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransitionTime(DateTime transitionTime) throws UaException {
        try {
            writeTransitionTimeAsync(transitionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readTransitionTimeAsync() {
        return getTransitionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransitionTimeAsync(DateTime transitionTime) {
        DataValue value = DataValue.valueOnly(new Variant(transitionTime));
        return getTransitionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getTransitionTimeNode() throws UaException {
        try {
            return getTransitionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getTransitionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransitionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getEffectiveTransitionTime() throws UaException {
        PropertyTypeNode node = getEffectiveTransitionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setEffectiveTransitionTime(DateTime effectiveTransitionTime) throws UaException {
        PropertyTypeNode node = getEffectiveTransitionTimeNode();
        node.setValue(new Variant(effectiveTransitionTime));
    }

    @Override
    public DateTime readEffectiveTransitionTime() throws UaException {
        try {
            return readEffectiveTransitionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEffectiveTransitionTime(DateTime effectiveTransitionTime) throws UaException {
        try {
            writeEffectiveTransitionTimeAsync(effectiveTransitionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readEffectiveTransitionTimeAsync() {
        return getEffectiveTransitionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEffectiveTransitionTimeAsync(
        DateTime effectiveTransitionTime) {
        DataValue value = DataValue.valueOnly(new Variant(effectiveTransitionTime));
        return getEffectiveTransitionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEffectiveTransitionTimeNode() throws UaException {
        try {
            return getEffectiveTransitionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEffectiveTransitionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EffectiveTransitionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getTrueState() throws UaException {
        PropertyTypeNode node = getTrueStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setTrueState(LocalizedText trueState) throws UaException {
        PropertyTypeNode node = getTrueStateNode();
        node.setValue(new Variant(trueState));
    }

    @Override
    public LocalizedText readTrueState() throws UaException {
        try {
            return readTrueStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTrueState(LocalizedText trueState) throws UaException {
        try {
            writeTrueStateAsync(trueState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readTrueStateAsync() {
        return getTrueStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTrueStateAsync(LocalizedText trueState) {
        DataValue value = DataValue.valueOnly(new Variant(trueState));
        return getTrueStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getTrueStateNode() throws UaException {
        try {
            return getTrueStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getTrueStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TrueState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getFalseState() throws UaException {
        PropertyTypeNode node = getFalseStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setFalseState(LocalizedText falseState) throws UaException {
        PropertyTypeNode node = getFalseStateNode();
        node.setValue(new Variant(falseState));
    }

    @Override
    public LocalizedText readFalseState() throws UaException {
        try {
            return readFalseStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeFalseState(LocalizedText falseState) throws UaException {
        try {
            writeFalseStateAsync(falseState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readFalseStateAsync() {
        return getFalseStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeFalseStateAsync(LocalizedText falseState) {
        DataValue value = DataValue.valueOnly(new Variant(falseState));
        return getFalseStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getFalseStateNode() throws UaException {
        try {
            return getFalseStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getFalseStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "FalseState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
