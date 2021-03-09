package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerRedundancyType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.RedundancySupport;

public class ServerRedundancyTypeNode extends BaseObjectTypeNode implements ServerRedundancyType {
    public ServerRedundancyTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                    UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public RedundancySupport getRedundancySupport() throws UaException {
        PropertyTypeNode node = getRedundancySupportNode();
        Object value = node.getValue().getValue().getValue();
        if (value instanceof Integer) {
            return RedundancySupport.from((Integer) value);
        } else if (value instanceof RedundancySupport) {
            return (RedundancySupport) value;
        } else {
            return null;
        }
    }

    @Override
    public void setRedundancySupport(RedundancySupport redundancySupport) throws UaException {
        PropertyTypeNode node = getRedundancySupportNode();
        node.setValue(new Variant(redundancySupport));
    }

    @Override
    public RedundancySupport readRedundancySupport() throws UaException {
        try {
            return readRedundancySupportAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRedundancySupport(RedundancySupport redundancySupport) throws UaException {
        try {
            writeRedundancySupportAsync(redundancySupport).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends RedundancySupport> readRedundancySupportAsync() {
        return getRedundancySupportNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return RedundancySupport.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writeRedundancySupportAsync(
        RedundancySupport redundancySupport) {
        DataValue value = DataValue.valueOnly(new Variant(redundancySupport));
        return getRedundancySupportNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getRedundancySupportNode() throws UaException {
        try {
            return getRedundancySupportNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getRedundancySupportNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RedundancySupport", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
