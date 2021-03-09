package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.OperationLimitsType;
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

public class OperationLimitsTypeNode extends FolderTypeNode implements OperationLimitsType {
    public OperationLimitsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                   QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                   UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public UInteger getMaxNodesPerRead() throws UaException {
        PropertyTypeNode node = getMaxNodesPerReadNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerRead(UInteger maxNodesPerRead) throws UaException {
        PropertyTypeNode node = getMaxNodesPerReadNode();
        node.setValue(new Variant(maxNodesPerRead));
    }

    @Override
    public UInteger readMaxNodesPerRead() throws UaException {
        try {
            return readMaxNodesPerReadAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerRead(UInteger maxNodesPerRead) throws UaException {
        try {
            writeMaxNodesPerReadAsync(maxNodesPerRead).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerReadAsync() {
        return getMaxNodesPerReadNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerReadAsync(UInteger maxNodesPerRead) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerRead));
        return getMaxNodesPerReadNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerReadNode() throws UaException {
        try {
            return getMaxNodesPerReadNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerReadNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerRead", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadData() throws UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryReadDataNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerHistoryReadData(UInteger maxNodesPerHistoryReadData) throws
        UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryReadDataNode();
        node.setValue(new Variant(maxNodesPerHistoryReadData));
    }

    @Override
    public UInteger readMaxNodesPerHistoryReadData() throws UaException {
        try {
            return readMaxNodesPerHistoryReadDataAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerHistoryReadData(UInteger maxNodesPerHistoryReadData) throws
        UaException {
        try {
            writeMaxNodesPerHistoryReadDataAsync(maxNodesPerHistoryReadData).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerHistoryReadDataAsync() {
        return getMaxNodesPerHistoryReadDataNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerHistoryReadDataAsync(
        UInteger maxNodesPerHistoryReadData) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerHistoryReadData));
        return getMaxNodesPerHistoryReadDataNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerHistoryReadDataNode() throws UaException {
        try {
            return getMaxNodesPerHistoryReadDataNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerHistoryReadDataNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerHistoryReadData", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerHistoryReadEvents() throws UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryReadEventsNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerHistoryReadEvents(UInteger maxNodesPerHistoryReadEvents) throws
        UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryReadEventsNode();
        node.setValue(new Variant(maxNodesPerHistoryReadEvents));
    }

    @Override
    public UInteger readMaxNodesPerHistoryReadEvents() throws UaException {
        try {
            return readMaxNodesPerHistoryReadEventsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerHistoryReadEvents(UInteger maxNodesPerHistoryReadEvents) throws
        UaException {
        try {
            writeMaxNodesPerHistoryReadEventsAsync(maxNodesPerHistoryReadEvents).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerHistoryReadEventsAsync() {
        return getMaxNodesPerHistoryReadEventsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerHistoryReadEventsAsync(
        UInteger maxNodesPerHistoryReadEvents) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerHistoryReadEvents));
        return getMaxNodesPerHistoryReadEventsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerHistoryReadEventsNode() throws UaException {
        try {
            return getMaxNodesPerHistoryReadEventsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerHistoryReadEventsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerHistoryReadEvents", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerWrite() throws UaException {
        PropertyTypeNode node = getMaxNodesPerWriteNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerWrite(UInteger maxNodesPerWrite) throws UaException {
        PropertyTypeNode node = getMaxNodesPerWriteNode();
        node.setValue(new Variant(maxNodesPerWrite));
    }

    @Override
    public UInteger readMaxNodesPerWrite() throws UaException {
        try {
            return readMaxNodesPerWriteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerWrite(UInteger maxNodesPerWrite) throws UaException {
        try {
            writeMaxNodesPerWriteAsync(maxNodesPerWrite).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerWriteAsync() {
        return getMaxNodesPerWriteNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerWriteAsync(UInteger maxNodesPerWrite) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerWrite));
        return getMaxNodesPerWriteNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerWriteNode() throws UaException {
        try {
            return getMaxNodesPerWriteNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerWriteNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerWrite", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateData() throws UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryUpdateDataNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerHistoryUpdateData(UInteger maxNodesPerHistoryUpdateData) throws
        UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryUpdateDataNode();
        node.setValue(new Variant(maxNodesPerHistoryUpdateData));
    }

    @Override
    public UInteger readMaxNodesPerHistoryUpdateData() throws UaException {
        try {
            return readMaxNodesPerHistoryUpdateDataAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerHistoryUpdateData(UInteger maxNodesPerHistoryUpdateData) throws
        UaException {
        try {
            writeMaxNodesPerHistoryUpdateDataAsync(maxNodesPerHistoryUpdateData).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerHistoryUpdateDataAsync() {
        return getMaxNodesPerHistoryUpdateDataNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerHistoryUpdateDataAsync(
        UInteger maxNodesPerHistoryUpdateData) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerHistoryUpdateData));
        return getMaxNodesPerHistoryUpdateDataNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerHistoryUpdateDataNode() throws UaException {
        try {
            return getMaxNodesPerHistoryUpdateDataNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerHistoryUpdateDataNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerHistoryUpdateData", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerHistoryUpdateEvents() throws UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryUpdateEventsNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerHistoryUpdateEvents(UInteger maxNodesPerHistoryUpdateEvents) throws
        UaException {
        PropertyTypeNode node = getMaxNodesPerHistoryUpdateEventsNode();
        node.setValue(new Variant(maxNodesPerHistoryUpdateEvents));
    }

    @Override
    public UInteger readMaxNodesPerHistoryUpdateEvents() throws UaException {
        try {
            return readMaxNodesPerHistoryUpdateEventsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerHistoryUpdateEvents(UInteger maxNodesPerHistoryUpdateEvents) throws
        UaException {
        try {
            writeMaxNodesPerHistoryUpdateEventsAsync(maxNodesPerHistoryUpdateEvents).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerHistoryUpdateEventsAsync() {
        return getMaxNodesPerHistoryUpdateEventsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerHistoryUpdateEventsAsync(
        UInteger maxNodesPerHistoryUpdateEvents) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerHistoryUpdateEvents));
        return getMaxNodesPerHistoryUpdateEventsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerHistoryUpdateEventsNode() throws UaException {
        try {
            return getMaxNodesPerHistoryUpdateEventsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerHistoryUpdateEventsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerHistoryUpdateEvents", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerMethodCall() throws UaException {
        PropertyTypeNode node = getMaxNodesPerMethodCallNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerMethodCall(UInteger maxNodesPerMethodCall) throws UaException {
        PropertyTypeNode node = getMaxNodesPerMethodCallNode();
        node.setValue(new Variant(maxNodesPerMethodCall));
    }

    @Override
    public UInteger readMaxNodesPerMethodCall() throws UaException {
        try {
            return readMaxNodesPerMethodCallAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerMethodCall(UInteger maxNodesPerMethodCall) throws UaException {
        try {
            writeMaxNodesPerMethodCallAsync(maxNodesPerMethodCall).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerMethodCallAsync() {
        return getMaxNodesPerMethodCallNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerMethodCallAsync(
        UInteger maxNodesPerMethodCall) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerMethodCall));
        return getMaxNodesPerMethodCallNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerMethodCallNode() throws UaException {
        try {
            return getMaxNodesPerMethodCallNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerMethodCallNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerMethodCall", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerBrowse() throws UaException {
        PropertyTypeNode node = getMaxNodesPerBrowseNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerBrowse(UInteger maxNodesPerBrowse) throws UaException {
        PropertyTypeNode node = getMaxNodesPerBrowseNode();
        node.setValue(new Variant(maxNodesPerBrowse));
    }

    @Override
    public UInteger readMaxNodesPerBrowse() throws UaException {
        try {
            return readMaxNodesPerBrowseAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerBrowse(UInteger maxNodesPerBrowse) throws UaException {
        try {
            writeMaxNodesPerBrowseAsync(maxNodesPerBrowse).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerBrowseAsync() {
        return getMaxNodesPerBrowseNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerBrowseAsync(UInteger maxNodesPerBrowse) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerBrowse));
        return getMaxNodesPerBrowseNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerBrowseNode() throws UaException {
        try {
            return getMaxNodesPerBrowseNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerBrowseNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerBrowse", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerRegisterNodes() throws UaException {
        PropertyTypeNode node = getMaxNodesPerRegisterNodesNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerRegisterNodes(UInteger maxNodesPerRegisterNodes) throws UaException {
        PropertyTypeNode node = getMaxNodesPerRegisterNodesNode();
        node.setValue(new Variant(maxNodesPerRegisterNodes));
    }

    @Override
    public UInteger readMaxNodesPerRegisterNodes() throws UaException {
        try {
            return readMaxNodesPerRegisterNodesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerRegisterNodes(UInteger maxNodesPerRegisterNodes) throws UaException {
        try {
            writeMaxNodesPerRegisterNodesAsync(maxNodesPerRegisterNodes).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerRegisterNodesAsync() {
        return getMaxNodesPerRegisterNodesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerRegisterNodesAsync(
        UInteger maxNodesPerRegisterNodes) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerRegisterNodes));
        return getMaxNodesPerRegisterNodesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerRegisterNodesNode() throws UaException {
        try {
            return getMaxNodesPerRegisterNodesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerRegisterNodesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerRegisterNodes", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds() throws UaException {
        PropertyTypeNode node = getMaxNodesPerTranslateBrowsePathsToNodeIdsNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerTranslateBrowsePathsToNodeIds(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds) throws UaException {
        PropertyTypeNode node = getMaxNodesPerTranslateBrowsePathsToNodeIdsNode();
        node.setValue(new Variant(maxNodesPerTranslateBrowsePathsToNodeIds));
    }

    @Override
    public UInteger readMaxNodesPerTranslateBrowsePathsToNodeIds() throws UaException {
        try {
            return readMaxNodesPerTranslateBrowsePathsToNodeIdsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerTranslateBrowsePathsToNodeIds(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds) throws UaException {
        try {
            writeMaxNodesPerTranslateBrowsePathsToNodeIdsAsync(maxNodesPerTranslateBrowsePathsToNodeIds).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerTranslateBrowsePathsToNodeIdsAsync() {
        return getMaxNodesPerTranslateBrowsePathsToNodeIdsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerTranslateBrowsePathsToNodeIdsAsync(
        UInteger maxNodesPerTranslateBrowsePathsToNodeIds) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerTranslateBrowsePathsToNodeIds));
        return getMaxNodesPerTranslateBrowsePathsToNodeIdsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerTranslateBrowsePathsToNodeIdsNode() throws UaException {
        try {
            return getMaxNodesPerTranslateBrowsePathsToNodeIdsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerTranslateBrowsePathsToNodeIdsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerTranslateBrowsePathsToNodeIds", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxNodesPerNodeManagement() throws UaException {
        PropertyTypeNode node = getMaxNodesPerNodeManagementNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNodesPerNodeManagement(UInteger maxNodesPerNodeManagement) throws UaException {
        PropertyTypeNode node = getMaxNodesPerNodeManagementNode();
        node.setValue(new Variant(maxNodesPerNodeManagement));
    }

    @Override
    public UInteger readMaxNodesPerNodeManagement() throws UaException {
        try {
            return readMaxNodesPerNodeManagementAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNodesPerNodeManagement(UInteger maxNodesPerNodeManagement) throws
        UaException {
        try {
            writeMaxNodesPerNodeManagementAsync(maxNodesPerNodeManagement).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNodesPerNodeManagementAsync() {
        return getMaxNodesPerNodeManagementNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNodesPerNodeManagementAsync(
        UInteger maxNodesPerNodeManagement) {
        DataValue value = DataValue.valueOnly(new Variant(maxNodesPerNodeManagement));
        return getMaxNodesPerNodeManagementNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxNodesPerNodeManagementNode() throws UaException {
        try {
            return getMaxNodesPerNodeManagementNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxNodesPerNodeManagementNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNodesPerNodeManagement", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxMonitoredItemsPerCall() throws UaException {
        PropertyTypeNode node = getMaxMonitoredItemsPerCallNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxMonitoredItemsPerCall(UInteger maxMonitoredItemsPerCall) throws UaException {
        PropertyTypeNode node = getMaxMonitoredItemsPerCallNode();
        node.setValue(new Variant(maxMonitoredItemsPerCall));
    }

    @Override
    public UInteger readMaxMonitoredItemsPerCall() throws UaException {
        try {
            return readMaxMonitoredItemsPerCallAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxMonitoredItemsPerCall(UInteger maxMonitoredItemsPerCall) throws UaException {
        try {
            writeMaxMonitoredItemsPerCallAsync(maxMonitoredItemsPerCall).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxMonitoredItemsPerCallAsync() {
        return getMaxMonitoredItemsPerCallNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxMonitoredItemsPerCallAsync(
        UInteger maxMonitoredItemsPerCall) {
        DataValue value = DataValue.valueOnly(new Variant(maxMonitoredItemsPerCall));
        return getMaxMonitoredItemsPerCallNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxMonitoredItemsPerCallNode() throws UaException {
        try {
            return getMaxMonitoredItemsPerCallNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxMonitoredItemsPerCallNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxMonitoredItemsPerCall", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
