package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditUpdateMethodEventType;
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

public class AuditUpdateMethodEventTypeNode extends AuditEventTypeNode implements AuditUpdateMethodEventType {
    public AuditUpdateMethodEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getMethodId() throws UaException {
        PropertyTypeNode node = getMethodIdNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setMethodId(NodeId methodId) throws UaException {
        PropertyTypeNode node = getMethodIdNode();
        node.setValue(new Variant(methodId));
    }

    @Override
    public NodeId readMethodId() throws UaException {
        try {
            return readMethodIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMethodId(NodeId methodId) throws UaException {
        try {
            writeMethodIdAsync(methodId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readMethodIdAsync() {
        return getMethodIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMethodIdAsync(NodeId methodId) {
        DataValue value = DataValue.valueOnly(new Variant(methodId));
        return getMethodIdNodeAsync()
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
    public PropertyTypeNode getMethodIdNode() throws UaException {
        try {
            return getMethodIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMethodIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MethodId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Object[] getInputArguments() throws UaException {
        PropertyTypeNode node = getInputArgumentsNode();
        return (Object[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setInputArguments(Object[] inputArguments) throws UaException {
        PropertyTypeNode node = getInputArgumentsNode();
        node.setValue(new Variant(inputArguments));
    }

    @Override
    public Object[] readInputArguments() throws UaException {
        try {
            return readInputArgumentsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInputArguments(Object[] inputArguments) throws UaException {
        try {
            writeInputArgumentsAsync(inputArguments).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Object[]> readInputArgumentsAsync() {
        return getInputArgumentsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInputArgumentsAsync(Object[] inputArguments) {
        DataValue value = DataValue.valueOnly(new Variant(inputArguments));
        return getInputArgumentsNodeAsync()
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
    public PropertyTypeNode getInputArgumentsNode() throws UaException {
        try {
            return getInputArgumentsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInputArgumentsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InputArguments", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
