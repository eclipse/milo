package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OffNormalAlarmType;
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

public class OffNormalAlarmTypeNode extends DiscreteAlarmTypeNode implements OffNormalAlarmType {
    public OffNormalAlarmTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                  QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                  UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getNormalState() throws UaException {
        PropertyTypeNode node = getNormalStateNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setNormalState(NodeId normalState) throws UaException {
        PropertyTypeNode node = getNormalStateNode();
        node.setValue(new Variant(normalState));
    }

    @Override
    public NodeId readNormalState() throws UaException {
        try {
            return readNormalStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNormalState(NodeId normalState) throws UaException {
        try {
            writeNormalStateAsync(normalState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readNormalStateAsync() {
        return getNormalStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNormalStateAsync(NodeId normalState) {
        DataValue value = DataValue.valueOnly(new Variant(normalState));
        return getNormalStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNormalStateNode() throws UaException {
        try {
            return getNormalStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNormalStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NormalState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
