package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionRespondEventType;
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

public class AuditConditionRespondEventTypeNode extends AuditConditionEventTypeNode implements AuditConditionRespondEventType {
    public AuditConditionRespondEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Integer getSelectedResponse() throws UaException {
        PropertyTypeNode node = getSelectedResponseNode();
        return (Integer) node.getValue().getValue().getValue();
    }

    @Override
    public void setSelectedResponse(Integer selectedResponse) throws UaException {
        PropertyTypeNode node = getSelectedResponseNode();
        node.setValue(new Variant(selectedResponse));
    }

    @Override
    public Integer readSelectedResponse() throws UaException {
        try {
            return readSelectedResponseAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSelectedResponse(Integer selectedResponse) throws UaException {
        try {
            writeSelectedResponseAsync(selectedResponse).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Integer> readSelectedResponseAsync() {
        return getSelectedResponseNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Integer) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSelectedResponseAsync(Integer selectedResponse) {
        DataValue value = DataValue.valueOnly(new Variant(selectedResponse));
        return getSelectedResponseNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSelectedResponseNode() throws UaException {
        try {
            return getSelectedResponseNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSelectedResponseNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SelectedResponse", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
