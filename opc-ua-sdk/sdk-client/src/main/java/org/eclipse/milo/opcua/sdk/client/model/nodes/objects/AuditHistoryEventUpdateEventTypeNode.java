package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventUpdateEventType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public class AuditHistoryEventUpdateEventTypeNode extends AuditHistoryUpdateEventTypeNode implements AuditHistoryEventUpdateEventType {
    public AuditHistoryEventUpdateEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getUpdatedNode() throws UaException {
        PropertyTypeNode node = getUpdatedNodeNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setUpdatedNode(NodeId updatedNode) throws UaException {
        PropertyTypeNode node = getUpdatedNodeNode();
        node.setValue(new Variant(updatedNode));
    }

    @Override
    public NodeId readUpdatedNode() throws UaException {
        try {
            return readUpdatedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUpdatedNode(NodeId updatedNode) throws UaException {
        try {
            writeUpdatedNodeAsync(updatedNode).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readUpdatedNodeAsync() {
        return getUpdatedNodeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeUpdatedNodeAsync(NodeId updatedNode) {
        DataValue value = DataValue.valueOnly(new Variant(updatedNode));
        return getUpdatedNodeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getUpdatedNodeNode() throws UaException {
        try {
            return getUpdatedNodeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getUpdatedNodeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UpdatedNode", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public PerformUpdateType getPerformInsertReplace() throws UaException {
        PropertyTypeNode node = getPerformInsertReplaceNode();
        Object value = node.getValue().getValue().getValue();
        if (value instanceof Integer) {
            return PerformUpdateType.from((Integer) value);
        } else if (value instanceof PerformUpdateType) {
            return (PerformUpdateType) value;
        } else {
            return null;
        }
    }

    @Override
    public void setPerformInsertReplace(PerformUpdateType performInsertReplace) throws UaException {
        PropertyTypeNode node = getPerformInsertReplaceNode();
        node.setValue(new Variant(performInsertReplace));
    }

    @Override
    public PerformUpdateType readPerformInsertReplace() throws UaException {
        try {
            return readPerformInsertReplaceAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePerformInsertReplace(PerformUpdateType performInsertReplace) throws UaException {
        try {
            writePerformInsertReplaceAsync(performInsertReplace).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PerformUpdateType> readPerformInsertReplaceAsync() {
        return getPerformInsertReplaceNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return PerformUpdateType.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writePerformInsertReplaceAsync(
        PerformUpdateType performInsertReplace) {
        DataValue value = DataValue.valueOnly(new Variant(performInsertReplace));
        return getPerformInsertReplaceNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getPerformInsertReplaceNode() throws UaException {
        try {
            return getPerformInsertReplaceNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getPerformInsertReplaceNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PerformInsertReplace", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public EventFilter getFilter() throws UaException {
        PropertyTypeNode node = getFilterNode();
        return cast(node.getValue().getValue().getValue(), EventFilter.class);
    }

    @Override
    public void setFilter(EventFilter filter) throws UaException {
        PropertyTypeNode node = getFilterNode();
        ExtensionObject value = ExtensionObject.encode(client.getSerializationContext(), filter);
        node.setValue(new Variant(value));
    }

    @Override
    public EventFilter readFilter() throws UaException {
        try {
            return readFilterAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeFilter(EventFilter filter) throws UaException {
        try {
            writeFilterAsync(filter).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends EventFilter> readFilterAsync() {
        return getFilterNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), EventFilter.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeFilterAsync(EventFilter filter) {
        ExtensionObject encoded = ExtensionObject.encode(client.getSerializationContext(), filter);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getFilterNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getFilterNode() throws UaException {
        try {
            return getFilterNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getFilterNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Filter", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public HistoryEventFieldList[] getNewValues() throws UaException {
        PropertyTypeNode node = getNewValuesNode();
        return cast(node.getValue().getValue().getValue(), HistoryEventFieldList[].class);
    }

    @Override
    public void setNewValues(HistoryEventFieldList[] newValues) throws UaException {
        PropertyTypeNode node = getNewValuesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), newValues);
        node.setValue(new Variant(encoded));
    }

    @Override
    public HistoryEventFieldList[] readNewValues() throws UaException {
        try {
            return readNewValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNewValues(HistoryEventFieldList[] newValues) throws UaException {
        try {
            writeNewValuesAsync(newValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends HistoryEventFieldList[]> readNewValuesAsync() {
        return getNewValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), HistoryEventFieldList[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeNewValuesAsync(HistoryEventFieldList[] newValues) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), newValues);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getNewValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNewValuesNode() throws UaException {
        try {
            return getNewValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNewValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NewValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public HistoryEventFieldList[] getOldValues() throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        return cast(node.getValue().getValue().getValue(), HistoryEventFieldList[].class);
    }

    @Override
    public void setOldValues(HistoryEventFieldList[] oldValues) throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), oldValues);
        node.setValue(new Variant(encoded));
    }

    @Override
    public HistoryEventFieldList[] readOldValues() throws UaException {
        try {
            return readOldValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldValues(HistoryEventFieldList[] oldValues) throws UaException {
        try {
            writeOldValuesAsync(oldValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends HistoryEventFieldList[]> readOldValuesAsync() {
        return getOldValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), HistoryEventFieldList[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeOldValuesAsync(HistoryEventFieldList[] oldValues) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), oldValues);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getOldValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOldValuesNode() throws UaException {
        try {
            return getOldValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOldValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OldValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
