package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FiniteStateMachineType;
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

public class FiniteStateMachineTypeNode extends StateMachineTypeNode implements FiniteStateMachineType {
    public FiniteStateMachineTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getCurrentState() throws UaException {
        FiniteStateVariableTypeNode node = getCurrentStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentState(LocalizedText currentState) throws UaException {
        FiniteStateVariableTypeNode node = getCurrentStateNode();
        node.setValue(new Variant(currentState));
    }

    @Override
    public LocalizedText readCurrentState() throws UaException {
        try {
            return readCurrentStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentState(LocalizedText currentState) throws UaException {
        try {
            writeCurrentStateAsync(currentState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readCurrentStateAsync() {
        return getCurrentStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentStateAsync(LocalizedText currentState) {
        DataValue value = DataValue.valueOnly(new Variant(currentState));
        return getCurrentStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public FiniteStateVariableTypeNode getCurrentStateNode() throws UaException {
        try {
            return getCurrentStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends FiniteStateVariableTypeNode> getCurrentStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FiniteStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getLastTransition() throws UaException {
        FiniteTransitionVariableTypeNode node = getLastTransitionNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setLastTransition(LocalizedText lastTransition) throws UaException {
        FiniteTransitionVariableTypeNode node = getLastTransitionNode();
        node.setValue(new Variant(lastTransition));
    }

    @Override
    public LocalizedText readLastTransition() throws UaException {
        try {
            return readLastTransitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLastTransition(LocalizedText lastTransition) throws UaException {
        try {
            writeLastTransitionAsync(lastTransition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readLastTransitionAsync() {
        return getLastTransitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLastTransitionAsync(LocalizedText lastTransition) {
        DataValue value = DataValue.valueOnly(new Variant(lastTransition));
        return getLastTransitionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public FiniteTransitionVariableTypeNode getLastTransitionNode() throws UaException {
        try {
            return getLastTransitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends FiniteTransitionVariableTypeNode> getLastTransitionNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LastTransition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FiniteTransitionVariableTypeNode) node);
    }
}
