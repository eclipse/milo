package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditWriteUpdateEventType;
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

public class AuditWriteUpdateEventTypeNode extends AuditUpdateEventTypeNode implements AuditWriteUpdateEventType {
    public AuditWriteUpdateEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public UInteger getAttributeId() throws UaException {
        PropertyTypeNode node = getAttributeIdNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setAttributeId(UInteger attributeId) throws UaException {
        PropertyTypeNode node = getAttributeIdNode();
        node.setValue(new Variant(attributeId));
    }

    @Override
    public UInteger readAttributeId() throws UaException {
        try {
            return readAttributeIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAttributeId(UInteger attributeId) throws UaException {
        try {
            writeAttributeIdAsync(attributeId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readAttributeIdAsync() {
        return getAttributeIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeAttributeIdAsync(UInteger attributeId) {
        DataValue value = DataValue.valueOnly(new Variant(attributeId));
        return getAttributeIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getAttributeIdNode() throws UaException {
        try {
            return getAttributeIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAttributeIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AttributeId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getIndexRange() throws UaException {
        PropertyTypeNode node = getIndexRangeNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setIndexRange(String indexRange) throws UaException {
        PropertyTypeNode node = getIndexRangeNode();
        node.setValue(new Variant(indexRange));
    }

    @Override
    public String readIndexRange() throws UaException {
        try {
            return readIndexRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeIndexRange(String indexRange) throws UaException {
        try {
            writeIndexRangeAsync(indexRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readIndexRangeAsync() {
        return getIndexRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeIndexRangeAsync(String indexRange) {
        DataValue value = DataValue.valueOnly(new Variant(indexRange));
        return getIndexRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getIndexRangeNode() throws UaException {
        try {
            return getIndexRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getIndexRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "IndexRange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Object getOldValue() throws UaException {
        PropertyTypeNode node = getOldValueNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setOldValue(Object oldValue) throws UaException {
        PropertyTypeNode node = getOldValueNode();
        node.setValue(new Variant(oldValue));
    }

    @Override
    public Object readOldValue() throws UaException {
        try {
            return readOldValueAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldValue(Object oldValue) throws UaException {
        try {
            writeOldValueAsync(oldValue).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readOldValueAsync() {
        return getOldValueNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOldValueAsync(Object oldValue) {
        DataValue value = DataValue.valueOnly(new Variant(oldValue));
        return getOldValueNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOldValueNode() throws UaException {
        try {
            return getOldValueNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOldValueNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OldValue", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Object getNewValue() throws UaException {
        PropertyTypeNode node = getNewValueNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setNewValue(Object newValue) throws UaException {
        PropertyTypeNode node = getNewValueNode();
        node.setValue(new Variant(newValue));
    }

    @Override
    public Object readNewValue() throws UaException {
        try {
            return readNewValueAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNewValue(Object newValue) throws UaException {
        try {
            writeNewValueAsync(newValue).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readNewValueAsync() {
        return getNewValueNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNewValueAsync(Object newValue) {
        DataValue value = DataValue.valueOnly(new Variant(newValue));
        return getNewValueNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNewValueNode() throws UaException {
        try {
            return getNewValueNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNewValueNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NewValue", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
