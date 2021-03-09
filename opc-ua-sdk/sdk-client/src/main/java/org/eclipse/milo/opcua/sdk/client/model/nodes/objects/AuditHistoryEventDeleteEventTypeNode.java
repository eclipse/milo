package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryEventDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
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
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public class AuditHistoryEventDeleteEventTypeNode extends AuditHistoryDeleteEventTypeNode implements AuditHistoryEventDeleteEventType {
    public AuditHistoryEventDeleteEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public ByteString[] getEventIds() throws UaException {
        PropertyTypeNode node = getEventIdsNode();
        return (ByteString[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setEventIds(ByteString[] eventIds) throws UaException {
        PropertyTypeNode node = getEventIdsNode();
        node.setValue(new Variant(eventIds));
    }

    @Override
    public ByteString[] readEventIds() throws UaException {
        try {
            return readEventIdsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEventIds(ByteString[] eventIds) throws UaException {
        try {
            writeEventIdsAsync(eventIds).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ByteString[]> readEventIdsAsync() {
        return getEventIdsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ByteString[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEventIdsAsync(ByteString[] eventIds) {
        DataValue value = DataValue.valueOnly(new Variant(eventIds));
        return getEventIdsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEventIdsNode() throws UaException {
        try {
            return getEventIdsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEventIdsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EventIds", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public HistoryEventFieldList getOldValues() throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        return cast(node.getValue().getValue().getValue(), HistoryEventFieldList.class);
    }

    @Override
    public void setOldValues(HistoryEventFieldList oldValues) throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        ExtensionObject value = ExtensionObject.encode(client.getSerializationContext(), oldValues);
        node.setValue(new Variant(value));
    }

    @Override
    public HistoryEventFieldList readOldValues() throws UaException {
        try {
            return readOldValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldValues(HistoryEventFieldList oldValues) throws UaException {
        try {
            writeOldValuesAsync(oldValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends HistoryEventFieldList> readOldValuesAsync() {
        return getOldValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), HistoryEventFieldList.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeOldValuesAsync(HistoryEventFieldList oldValues) {
        ExtensionObject encoded = ExtensionObject.encode(client.getSerializationContext(), oldValues);
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
