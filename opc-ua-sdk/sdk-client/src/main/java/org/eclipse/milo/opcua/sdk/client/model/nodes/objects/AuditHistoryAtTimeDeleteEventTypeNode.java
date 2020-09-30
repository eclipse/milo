package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryAtTimeDeleteEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class AuditHistoryAtTimeDeleteEventTypeNode extends AuditHistoryDeleteEventTypeNode implements AuditHistoryAtTimeDeleteEventType {
    public AuditHistoryAtTimeDeleteEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                 NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                 LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public DateTime[] getReqTimes() throws UaException {
        PropertyTypeNode node = getReqTimesNode();
        return (DateTime[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setReqTimes(DateTime[] reqTimes) throws UaException {
        PropertyTypeNode node = getReqTimesNode();
        node.setValue(new Variant(reqTimes));
    }

    @Override
    public DateTime[] readReqTimes() throws UaException {
        try {
            return readReqTimesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReqTimes(DateTime[] reqTimes) throws UaException {
        try {
            writeReqTimesAsync(reqTimes).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime[]> readReqTimesAsync() {
        return getReqTimesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeReqTimesAsync(DateTime[] reqTimes) {
        DataValue value = DataValue.valueOnly(new Variant(reqTimes));
        return getReqTimesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getReqTimesNode() throws UaException {
        try {
            return getReqTimesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getReqTimesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReqTimes", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DataValue[] getOldValues() throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        return (DataValue[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setOldValues(DataValue[] oldValues) throws UaException {
        PropertyTypeNode node = getOldValuesNode();
        node.setValue(new Variant(oldValues));
    }

    @Override
    public DataValue[] readOldValues() throws UaException {
        try {
            return readOldValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldValues(DataValue[] oldValues) throws UaException {
        try {
            writeOldValuesAsync(oldValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DataValue[]> readOldValuesAsync() {
        return getOldValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DataValue[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOldValuesAsync(DataValue[] oldValues) {
        DataValue value = DataValue.valueOnly(new Variant(oldValues));
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
