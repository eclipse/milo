package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoryServerCapabilitiesType;
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

public class HistoryServerCapabilitiesTypeNode extends BaseObjectTypeNode implements HistoryServerCapabilitiesType {
    public HistoryServerCapabilitiesTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getAccessHistoryDataCapability() throws UaException {
        PropertyTypeNode node = getAccessHistoryDataCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setAccessHistoryDataCapability(Boolean accessHistoryDataCapability) throws
        UaException {
        PropertyTypeNode node = getAccessHistoryDataCapabilityNode();
        node.setValue(new Variant(accessHistoryDataCapability));
    }

    @Override
    public Boolean readAccessHistoryDataCapability() throws UaException {
        try {
            return readAccessHistoryDataCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAccessHistoryDataCapability(Boolean accessHistoryDataCapability) throws
        UaException {
        try {
            writeAccessHistoryDataCapabilityAsync(accessHistoryDataCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readAccessHistoryDataCapabilityAsync() {
        return getAccessHistoryDataCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeAccessHistoryDataCapabilityAsync(
        Boolean accessHistoryDataCapability) {
        DataValue value = DataValue.valueOnly(new Variant(accessHistoryDataCapability));
        return getAccessHistoryDataCapabilityNodeAsync()
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
    public PropertyTypeNode getAccessHistoryDataCapabilityNode() throws UaException {
        try {
            return getAccessHistoryDataCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAccessHistoryDataCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AccessHistoryDataCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getAccessHistoryEventsCapability() throws UaException {
        PropertyTypeNode node = getAccessHistoryEventsCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setAccessHistoryEventsCapability(Boolean accessHistoryEventsCapability) throws
        UaException {
        PropertyTypeNode node = getAccessHistoryEventsCapabilityNode();
        node.setValue(new Variant(accessHistoryEventsCapability));
    }

    @Override
    public Boolean readAccessHistoryEventsCapability() throws UaException {
        try {
            return readAccessHistoryEventsCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAccessHistoryEventsCapability(Boolean accessHistoryEventsCapability) throws
        UaException {
        try {
            writeAccessHistoryEventsCapabilityAsync(accessHistoryEventsCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readAccessHistoryEventsCapabilityAsync() {
        return getAccessHistoryEventsCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeAccessHistoryEventsCapabilityAsync(
        Boolean accessHistoryEventsCapability) {
        DataValue value = DataValue.valueOnly(new Variant(accessHistoryEventsCapability));
        return getAccessHistoryEventsCapabilityNodeAsync()
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
    public PropertyTypeNode getAccessHistoryEventsCapabilityNode() throws UaException {
        try {
            return getAccessHistoryEventsCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAccessHistoryEventsCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AccessHistoryEventsCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxReturnDataValues() throws UaException {
        PropertyTypeNode node = getMaxReturnDataValuesNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxReturnDataValues(UInteger maxReturnDataValues) throws UaException {
        PropertyTypeNode node = getMaxReturnDataValuesNode();
        node.setValue(new Variant(maxReturnDataValues));
    }

    @Override
    public UInteger readMaxReturnDataValues() throws UaException {
        try {
            return readMaxReturnDataValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxReturnDataValues(UInteger maxReturnDataValues) throws UaException {
        try {
            writeMaxReturnDataValuesAsync(maxReturnDataValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxReturnDataValuesAsync() {
        return getMaxReturnDataValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxReturnDataValuesAsync(UInteger maxReturnDataValues) {
        DataValue value = DataValue.valueOnly(new Variant(maxReturnDataValues));
        return getMaxReturnDataValuesNodeAsync()
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
    public PropertyTypeNode getMaxReturnDataValuesNode() throws UaException {
        try {
            return getMaxReturnDataValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxReturnDataValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxReturnDataValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxReturnEventValues() throws UaException {
        PropertyTypeNode node = getMaxReturnEventValuesNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxReturnEventValues(UInteger maxReturnEventValues) throws UaException {
        PropertyTypeNode node = getMaxReturnEventValuesNode();
        node.setValue(new Variant(maxReturnEventValues));
    }

    @Override
    public UInteger readMaxReturnEventValues() throws UaException {
        try {
            return readMaxReturnEventValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxReturnEventValues(UInteger maxReturnEventValues) throws UaException {
        try {
            writeMaxReturnEventValuesAsync(maxReturnEventValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxReturnEventValuesAsync() {
        return getMaxReturnEventValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxReturnEventValuesAsync(UInteger maxReturnEventValues) {
        DataValue value = DataValue.valueOnly(new Variant(maxReturnEventValues));
        return getMaxReturnEventValuesNodeAsync()
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
    public PropertyTypeNode getMaxReturnEventValuesNode() throws UaException {
        try {
            return getMaxReturnEventValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxReturnEventValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxReturnEventValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getInsertDataCapability() throws UaException {
        PropertyTypeNode node = getInsertDataCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setInsertDataCapability(Boolean insertDataCapability) throws UaException {
        PropertyTypeNode node = getInsertDataCapabilityNode();
        node.setValue(new Variant(insertDataCapability));
    }

    @Override
    public Boolean readInsertDataCapability() throws UaException {
        try {
            return readInsertDataCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInsertDataCapability(Boolean insertDataCapability) throws UaException {
        try {
            writeInsertDataCapabilityAsync(insertDataCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readInsertDataCapabilityAsync() {
        return getInsertDataCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInsertDataCapabilityAsync(Boolean insertDataCapability) {
        DataValue value = DataValue.valueOnly(new Variant(insertDataCapability));
        return getInsertDataCapabilityNodeAsync()
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
    public PropertyTypeNode getInsertDataCapabilityNode() throws UaException {
        try {
            return getInsertDataCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInsertDataCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InsertDataCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getReplaceDataCapability() throws UaException {
        PropertyTypeNode node = getReplaceDataCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setReplaceDataCapability(Boolean replaceDataCapability) throws UaException {
        PropertyTypeNode node = getReplaceDataCapabilityNode();
        node.setValue(new Variant(replaceDataCapability));
    }

    @Override
    public Boolean readReplaceDataCapability() throws UaException {
        try {
            return readReplaceDataCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReplaceDataCapability(Boolean replaceDataCapability) throws UaException {
        try {
            writeReplaceDataCapabilityAsync(replaceDataCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readReplaceDataCapabilityAsync() {
        return getReplaceDataCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeReplaceDataCapabilityAsync(Boolean replaceDataCapability) {
        DataValue value = DataValue.valueOnly(new Variant(replaceDataCapability));
        return getReplaceDataCapabilityNodeAsync()
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
    public PropertyTypeNode getReplaceDataCapabilityNode() throws UaException {
        try {
            return getReplaceDataCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getReplaceDataCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReplaceDataCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getUpdateDataCapability() throws UaException {
        PropertyTypeNode node = getUpdateDataCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setUpdateDataCapability(Boolean updateDataCapability) throws UaException {
        PropertyTypeNode node = getUpdateDataCapabilityNode();
        node.setValue(new Variant(updateDataCapability));
    }

    @Override
    public Boolean readUpdateDataCapability() throws UaException {
        try {
            return readUpdateDataCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUpdateDataCapability(Boolean updateDataCapability) throws UaException {
        try {
            writeUpdateDataCapabilityAsync(updateDataCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readUpdateDataCapabilityAsync() {
        return getUpdateDataCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeUpdateDataCapabilityAsync(Boolean updateDataCapability) {
        DataValue value = DataValue.valueOnly(new Variant(updateDataCapability));
        return getUpdateDataCapabilityNodeAsync()
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
    public PropertyTypeNode getUpdateDataCapabilityNode() throws UaException {
        try {
            return getUpdateDataCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getUpdateDataCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UpdateDataCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getDeleteRawCapability() throws UaException {
        PropertyTypeNode node = getDeleteRawCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteRawCapability(Boolean deleteRawCapability) throws UaException {
        PropertyTypeNode node = getDeleteRawCapabilityNode();
        node.setValue(new Variant(deleteRawCapability));
    }

    @Override
    public Boolean readDeleteRawCapability() throws UaException {
        try {
            return readDeleteRawCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteRawCapability(Boolean deleteRawCapability) throws UaException {
        try {
            writeDeleteRawCapabilityAsync(deleteRawCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readDeleteRawCapabilityAsync() {
        return getDeleteRawCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteRawCapabilityAsync(Boolean deleteRawCapability) {
        DataValue value = DataValue.valueOnly(new Variant(deleteRawCapability));
        return getDeleteRawCapabilityNodeAsync()
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
    public PropertyTypeNode getDeleteRawCapabilityNode() throws UaException {
        try {
            return getDeleteRawCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDeleteRawCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteRawCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getDeleteAtTimeCapability() throws UaException {
        PropertyTypeNode node = getDeleteAtTimeCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteAtTimeCapability(Boolean deleteAtTimeCapability) throws UaException {
        PropertyTypeNode node = getDeleteAtTimeCapabilityNode();
        node.setValue(new Variant(deleteAtTimeCapability));
    }

    @Override
    public Boolean readDeleteAtTimeCapability() throws UaException {
        try {
            return readDeleteAtTimeCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteAtTimeCapability(Boolean deleteAtTimeCapability) throws UaException {
        try {
            writeDeleteAtTimeCapabilityAsync(deleteAtTimeCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readDeleteAtTimeCapabilityAsync() {
        return getDeleteAtTimeCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteAtTimeCapabilityAsync(Boolean deleteAtTimeCapability) {
        DataValue value = DataValue.valueOnly(new Variant(deleteAtTimeCapability));
        return getDeleteAtTimeCapabilityNodeAsync()
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
    public PropertyTypeNode getDeleteAtTimeCapabilityNode() throws UaException {
        try {
            return getDeleteAtTimeCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDeleteAtTimeCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteAtTimeCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getInsertEventCapability() throws UaException {
        PropertyTypeNode node = getInsertEventCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setInsertEventCapability(Boolean insertEventCapability) throws UaException {
        PropertyTypeNode node = getInsertEventCapabilityNode();
        node.setValue(new Variant(insertEventCapability));
    }

    @Override
    public Boolean readInsertEventCapability() throws UaException {
        try {
            return readInsertEventCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInsertEventCapability(Boolean insertEventCapability) throws UaException {
        try {
            writeInsertEventCapabilityAsync(insertEventCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readInsertEventCapabilityAsync() {
        return getInsertEventCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInsertEventCapabilityAsync(Boolean insertEventCapability) {
        DataValue value = DataValue.valueOnly(new Variant(insertEventCapability));
        return getInsertEventCapabilityNodeAsync()
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
    public PropertyTypeNode getInsertEventCapabilityNode() throws UaException {
        try {
            return getInsertEventCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInsertEventCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InsertEventCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getReplaceEventCapability() throws UaException {
        PropertyTypeNode node = getReplaceEventCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setReplaceEventCapability(Boolean replaceEventCapability) throws UaException {
        PropertyTypeNode node = getReplaceEventCapabilityNode();
        node.setValue(new Variant(replaceEventCapability));
    }

    @Override
    public Boolean readReplaceEventCapability() throws UaException {
        try {
            return readReplaceEventCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReplaceEventCapability(Boolean replaceEventCapability) throws UaException {
        try {
            writeReplaceEventCapabilityAsync(replaceEventCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readReplaceEventCapabilityAsync() {
        return getReplaceEventCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeReplaceEventCapabilityAsync(Boolean replaceEventCapability) {
        DataValue value = DataValue.valueOnly(new Variant(replaceEventCapability));
        return getReplaceEventCapabilityNodeAsync()
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
    public PropertyTypeNode getReplaceEventCapabilityNode() throws UaException {
        try {
            return getReplaceEventCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getReplaceEventCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReplaceEventCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getUpdateEventCapability() throws UaException {
        PropertyTypeNode node = getUpdateEventCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setUpdateEventCapability(Boolean updateEventCapability) throws UaException {
        PropertyTypeNode node = getUpdateEventCapabilityNode();
        node.setValue(new Variant(updateEventCapability));
    }

    @Override
    public Boolean readUpdateEventCapability() throws UaException {
        try {
            return readUpdateEventCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUpdateEventCapability(Boolean updateEventCapability) throws UaException {
        try {
            writeUpdateEventCapabilityAsync(updateEventCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readUpdateEventCapabilityAsync() {
        return getUpdateEventCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeUpdateEventCapabilityAsync(Boolean updateEventCapability) {
        DataValue value = DataValue.valueOnly(new Variant(updateEventCapability));
        return getUpdateEventCapabilityNodeAsync()
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
    public PropertyTypeNode getUpdateEventCapabilityNode() throws UaException {
        try {
            return getUpdateEventCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getUpdateEventCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UpdateEventCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getDeleteEventCapability() throws UaException {
        PropertyTypeNode node = getDeleteEventCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeleteEventCapability(Boolean deleteEventCapability) throws UaException {
        PropertyTypeNode node = getDeleteEventCapabilityNode();
        node.setValue(new Variant(deleteEventCapability));
    }

    @Override
    public Boolean readDeleteEventCapability() throws UaException {
        try {
            return readDeleteEventCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteEventCapability(Boolean deleteEventCapability) throws UaException {
        try {
            writeDeleteEventCapabilityAsync(deleteEventCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readDeleteEventCapabilityAsync() {
        return getDeleteEventCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeleteEventCapabilityAsync(Boolean deleteEventCapability) {
        DataValue value = DataValue.valueOnly(new Variant(deleteEventCapability));
        return getDeleteEventCapabilityNodeAsync()
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
    public PropertyTypeNode getDeleteEventCapabilityNode() throws UaException {
        try {
            return getDeleteEventCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDeleteEventCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteEventCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getInsertAnnotationCapability() throws UaException {
        PropertyTypeNode node = getInsertAnnotationCapabilityNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setInsertAnnotationCapability(Boolean insertAnnotationCapability) throws UaException {
        PropertyTypeNode node = getInsertAnnotationCapabilityNode();
        node.setValue(new Variant(insertAnnotationCapability));
    }

    @Override
    public Boolean readInsertAnnotationCapability() throws UaException {
        try {
            return readInsertAnnotationCapabilityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInsertAnnotationCapability(Boolean insertAnnotationCapability) throws
        UaException {
        try {
            writeInsertAnnotationCapabilityAsync(insertAnnotationCapability).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readInsertAnnotationCapabilityAsync() {
        return getInsertAnnotationCapabilityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInsertAnnotationCapabilityAsync(
        Boolean insertAnnotationCapability) {
        DataValue value = DataValue.valueOnly(new Variant(insertAnnotationCapability));
        return getInsertAnnotationCapabilityNodeAsync()
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
    public PropertyTypeNode getInsertAnnotationCapabilityNode() throws UaException {
        try {
            return getInsertAnnotationCapabilityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInsertAnnotationCapabilityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InsertAnnotationCapability", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    public FolderTypeNode getAggregateFunctionsNode() throws UaException {
        try {
            return getAggregateFunctionsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends FolderTypeNode> getAggregateFunctionsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AggregateFunctions", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FolderTypeNode) node);
    }
}
