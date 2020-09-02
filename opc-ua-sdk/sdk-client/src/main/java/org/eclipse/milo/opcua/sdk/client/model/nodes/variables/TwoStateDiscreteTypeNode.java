package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateDiscreteType;
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

public class TwoStateDiscreteTypeNode extends DiscreteItemTypeNode implements TwoStateDiscreteType {
    public TwoStateDiscreteTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                    UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                    double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
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
}
