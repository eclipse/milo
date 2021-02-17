package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditDeleteReferencesEventType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesItem;

public class AuditDeleteReferencesEventTypeNode extends AuditNodeManagementEventTypeNode implements AuditDeleteReferencesEventType {
    public AuditDeleteReferencesEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public DeleteReferencesItem[] getReferencesToDelete() throws UaException {
        PropertyTypeNode node = getReferencesToDeleteNode();
        return cast(node.getValue().getValue().getValue(), DeleteReferencesItem[].class);
    }

    @Override
    public void setReferencesToDelete(DeleteReferencesItem[] referencesToDelete) throws UaException {
        PropertyTypeNode node = getReferencesToDeleteNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), referencesToDelete);
        node.setValue(new Variant(encoded));
    }

    @Override
    public DeleteReferencesItem[] readReferencesToDelete() throws UaException {
        try {
            return readReferencesToDeleteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReferencesToDelete(DeleteReferencesItem[] referencesToDelete) throws
        UaException {
        try {
            writeReferencesToDeleteAsync(referencesToDelete).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DeleteReferencesItem[]> readReferencesToDeleteAsync() {
        return getReferencesToDeleteNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), DeleteReferencesItem[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeReferencesToDeleteAsync(
        DeleteReferencesItem[] referencesToDelete) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), referencesToDelete);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getReferencesToDeleteNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getReferencesToDeleteNode() throws UaException {
        try {
            return getReferencesToDeleteNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getReferencesToDeleteNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReferencesToDelete", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
