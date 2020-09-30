package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditDeleteNodesEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesItem;

public class AuditDeleteNodesEventTypeNode extends AuditNodeManagementEventTypeNode implements AuditDeleteNodesEventType {
    public AuditDeleteNodesEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public DeleteNodesItem[] getNodesToDelete() throws UaException {
        PropertyTypeNode node = getNodesToDeleteNode();
        return cast(node.getValue().getValue().getValue(), DeleteNodesItem[].class);
    }

    @Override
    public void setNodesToDelete(DeleteNodesItem[] nodesToDelete) throws UaException {
        PropertyTypeNode node = getNodesToDeleteNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), nodesToDelete);
        node.setValue(new Variant(encoded));
    }

    @Override
    public DeleteNodesItem[] readNodesToDelete() throws UaException {
        try {
            return readNodesToDeleteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNodesToDelete(DeleteNodesItem[] nodesToDelete) throws UaException {
        try {
            writeNodesToDeleteAsync(nodesToDelete).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DeleteNodesItem[]> readNodesToDeleteAsync() {
        return getNodesToDeleteNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), DeleteNodesItem[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeNodesToDeleteAsync(DeleteNodesItem[] nodesToDelete) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), nodesToDelete);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getNodesToDeleteNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNodesToDeleteNode() throws UaException {
        try {
            return getNodesToDeleteNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNodesToDeleteNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NodesToDelete", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
