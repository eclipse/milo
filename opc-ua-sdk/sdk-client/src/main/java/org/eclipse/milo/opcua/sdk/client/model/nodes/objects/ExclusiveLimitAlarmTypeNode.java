package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ExclusiveLimitAlarmType;
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

public class ExclusiveLimitAlarmTypeNode extends LimitAlarmTypeNode implements ExclusiveLimitAlarmType {
    public ExclusiveLimitAlarmTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
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

    public ExclusiveLimitStateMachineTypeNode getLimitStateNode() throws UaException {
        try {
            return getLimitStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends ExclusiveLimitStateMachineTypeNode> getLimitStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LimitState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ExclusiveLimitStateMachineTypeNode) node);
    }
}
