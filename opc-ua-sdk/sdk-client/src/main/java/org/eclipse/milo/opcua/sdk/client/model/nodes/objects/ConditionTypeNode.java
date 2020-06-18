package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ConditionVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ConditionType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ConditionTypeNode extends BaseEventTypeNode implements ConditionType {
    public ConditionTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getConditionClassId() throws UaException {
        PropertyTypeNode node = getConditionClassIdNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setConditionClassId(NodeId conditionClassId) throws UaException {
        PropertyTypeNode node = getConditionClassIdNode();
        node.setValue(new Variant(conditionClassId));
    }

    @Override
    public NodeId readConditionClassId() throws UaException {
        try {
            return readConditionClassIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeConditionClassId(NodeId conditionClassId) throws UaException {
        try {
            writeConditionClassIdAsync(conditionClassId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readConditionClassIdAsync() {
        return getConditionClassIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeConditionClassIdAsync(NodeId conditionClassId) {
        DataValue value = DataValue.valueOnly(new Variant(conditionClassId));
        return getConditionClassIdNodeAsync()
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
    public PropertyTypeNode getConditionClassIdNode() throws UaException {
        try {
            return getConditionClassIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getConditionClassIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ConditionClassId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getConditionClassName() throws UaException {
        PropertyTypeNode node = getConditionClassNameNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setConditionClassName(LocalizedText conditionClassName) throws UaException {
        PropertyTypeNode node = getConditionClassNameNode();
        node.setValue(new Variant(conditionClassName));
    }

    @Override
    public LocalizedText readConditionClassName() throws UaException {
        try {
            return readConditionClassNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeConditionClassName(LocalizedText conditionClassName) throws UaException {
        try {
            writeConditionClassNameAsync(conditionClassName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readConditionClassNameAsync() {
        return getConditionClassNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeConditionClassNameAsync(LocalizedText conditionClassName) {
        DataValue value = DataValue.valueOnly(new Variant(conditionClassName));
        return getConditionClassNameNodeAsync()
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
    public PropertyTypeNode getConditionClassNameNode() throws UaException {
        try {
            return getConditionClassNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getConditionClassNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ConditionClassName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getConditionName() throws UaException {
        PropertyTypeNode node = getConditionNameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setConditionName(String conditionName) throws UaException {
        PropertyTypeNode node = getConditionNameNode();
        node.setValue(new Variant(conditionName));
    }

    @Override
    public String readConditionName() throws UaException {
        try {
            return readConditionNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeConditionName(String conditionName) throws UaException {
        try {
            writeConditionNameAsync(conditionName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readConditionNameAsync() {
        return getConditionNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeConditionNameAsync(String conditionName) {
        DataValue value = DataValue.valueOnly(new Variant(conditionName));
        return getConditionNameNodeAsync()
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
    public PropertyTypeNode getConditionNameNode() throws UaException {
        try {
            return getConditionNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getConditionNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ConditionName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public NodeId getBranchId() throws UaException {
        PropertyTypeNode node = getBranchIdNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setBranchId(NodeId branchId) throws UaException {
        PropertyTypeNode node = getBranchIdNode();
        node.setValue(new Variant(branchId));
    }

    @Override
    public NodeId readBranchId() throws UaException {
        try {
            return readBranchIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBranchId(NodeId branchId) throws UaException {
        try {
            writeBranchIdAsync(branchId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readBranchIdAsync() {
        return getBranchIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeBranchIdAsync(NodeId branchId) {
        DataValue value = DataValue.valueOnly(new Variant(branchId));
        return getBranchIdNodeAsync()
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
    public PropertyTypeNode getBranchIdNode() throws UaException {
        try {
            return getBranchIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getBranchIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BranchId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getRetain() throws UaException {
        PropertyTypeNode node = getRetainNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setRetain(Boolean retain) throws UaException {
        PropertyTypeNode node = getRetainNode();
        node.setValue(new Variant(retain));
    }

    @Override
    public Boolean readRetain() throws UaException {
        try {
            return readRetainAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRetain(Boolean retain) throws UaException {
        try {
            writeRetainAsync(retain).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readRetainAsync() {
        return getRetainNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeRetainAsync(Boolean retain) {
        DataValue value = DataValue.valueOnly(new Variant(retain));
        return getRetainNodeAsync()
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
    public PropertyTypeNode getRetainNode() throws UaException {
        try {
            return getRetainNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getRetainNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Retain", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getClientUserId() throws UaException {
        PropertyTypeNode node = getClientUserIdNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientUserId(String clientUserId) throws UaException {
        PropertyTypeNode node = getClientUserIdNode();
        node.setValue(new Variant(clientUserId));
    }

    @Override
    public String readClientUserId() throws UaException {
        try {
            return readClientUserIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientUserId(String clientUserId) throws UaException {
        try {
            writeClientUserIdAsync(clientUserId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readClientUserIdAsync() {
        return getClientUserIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeClientUserIdAsync(String clientUserId) {
        DataValue value = DataValue.valueOnly(new Variant(clientUserId));
        return getClientUserIdNodeAsync()
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
    public PropertyTypeNode getClientUserIdNode() throws UaException {
        try {
            return getClientUserIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getClientUserIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientUserId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getEnabledState() throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnabledState(LocalizedText enabledState) throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        node.setValue(new Variant(enabledState));
    }

    @Override
    public LocalizedText readEnabledState() throws UaException {
        try {
            return readEnabledStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnabledState(LocalizedText enabledState) throws UaException {
        try {
            writeEnabledStateAsync(enabledState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readEnabledStateAsync() {
        return getEnabledStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEnabledStateAsync(LocalizedText enabledState) {
        DataValue value = DataValue.valueOnly(new Variant(enabledState));
        return getEnabledStateNodeAsync()
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
    public TwoStateVariableTypeNode getEnabledStateNode() throws UaException {
        try {
            return getEnabledStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getEnabledStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnabledState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public StatusCode getQuality() throws UaException {
        ConditionVariableTypeNode node = getQualityNode();
        return (StatusCode) node.getValue().getValue().getValue();
    }

    @Override
    public void setQuality(StatusCode quality) throws UaException {
        ConditionVariableTypeNode node = getQualityNode();
        node.setValue(new Variant(quality));
    }

    @Override
    public StatusCode readQuality() throws UaException {
        try {
            return readQualityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeQuality(StatusCode quality) throws UaException {
        try {
            writeQualityAsync(quality).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends StatusCode> readQualityAsync() {
        return getQualityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (StatusCode) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeQualityAsync(StatusCode quality) {
        DataValue value = DataValue.valueOnly(new Variant(quality));
        return getQualityNodeAsync()
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
    public ConditionVariableTypeNode getQualityNode() throws UaException {
        try {
            return getQualityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ConditionVariableTypeNode> getQualityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Quality", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ConditionVariableTypeNode) node);
    }

    @Override
    public UShort getLastSeverity() throws UaException {
        ConditionVariableTypeNode node = getLastSeverityNode();
        return (UShort) node.getValue().getValue().getValue();
    }

    @Override
    public void setLastSeverity(UShort lastSeverity) throws UaException {
        ConditionVariableTypeNode node = getLastSeverityNode();
        node.setValue(new Variant(lastSeverity));
    }

    @Override
    public UShort readLastSeverity() throws UaException {
        try {
            return readLastSeverityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLastSeverity(UShort lastSeverity) throws UaException {
        try {
            writeLastSeverityAsync(lastSeverity).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UShort> readLastSeverityAsync() {
        return getLastSeverityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UShort) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeLastSeverityAsync(UShort lastSeverity) {
        DataValue value = DataValue.valueOnly(new Variant(lastSeverity));
        return getLastSeverityNodeAsync()
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
    public ConditionVariableTypeNode getLastSeverityNode() throws UaException {
        try {
            return getLastSeverityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ConditionVariableTypeNode> getLastSeverityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LastSeverity", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ConditionVariableTypeNode) node);
    }

    @Override
    public LocalizedText getComment() throws UaException {
        ConditionVariableTypeNode node = getCommentNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setComment(LocalizedText comment) throws UaException {
        ConditionVariableTypeNode node = getCommentNode();
        node.setValue(new Variant(comment));
    }

    @Override
    public LocalizedText readComment() throws UaException {
        try {
            return readCommentAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeComment(LocalizedText comment) throws UaException {
        try {
            writeCommentAsync(comment).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readCommentAsync() {
        return getCommentNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCommentAsync(LocalizedText comment) {
        DataValue value = DataValue.valueOnly(new Variant(comment));
        return getCommentNodeAsync()
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
    public ConditionVariableTypeNode getCommentNode() throws UaException {
        try {
            return getCommentNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ConditionVariableTypeNode> getCommentNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Comment", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ConditionVariableTypeNode) node);
    }
}
