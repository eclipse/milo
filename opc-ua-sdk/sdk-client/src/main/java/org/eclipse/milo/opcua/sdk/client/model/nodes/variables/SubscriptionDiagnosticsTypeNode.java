package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsType;
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

public class SubscriptionDiagnosticsTypeNode extends BaseDataVariableTypeNode implements SubscriptionDiagnosticsType {
    public SubscriptionDiagnosticsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                           Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                           Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public NodeId getSessionId() throws UaException {
        BaseDataVariableTypeNode node = getSessionIdNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionId(NodeId sessionId) throws UaException {
        BaseDataVariableTypeNode node = getSessionIdNode();
        node.setValue(new Variant(sessionId));
    }

    @Override
    public NodeId readSessionId() throws UaException {
        try {
            return readSessionIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionId(NodeId sessionId) throws UaException {
        try {
            writeSessionIdAsync(sessionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readSessionIdAsync() {
        return getSessionIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSessionIdAsync(NodeId sessionId) {
        DataValue value = DataValue.valueOnly(new Variant(sessionId));
        return getSessionIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSessionIdNode() throws UaException {
        try {
            return getSessionIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSessionIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getSubscriptionId() throws UaException {
        BaseDataVariableTypeNode node = getSubscriptionIdNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setSubscriptionId(UInteger subscriptionId) throws UaException {
        BaseDataVariableTypeNode node = getSubscriptionIdNode();
        node.setValue(new Variant(subscriptionId));
    }

    @Override
    public UInteger readSubscriptionId() throws UaException {
        try {
            return readSubscriptionIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSubscriptionId(UInteger subscriptionId) throws UaException {
        try {
            writeSubscriptionIdAsync(subscriptionId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readSubscriptionIdAsync() {
        return getSubscriptionIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSubscriptionIdAsync(UInteger subscriptionId) {
        DataValue value = DataValue.valueOnly(new Variant(subscriptionId));
        return getSubscriptionIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSubscriptionIdNode() throws UaException {
        try {
            return getSubscriptionIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSubscriptionIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SubscriptionId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UByte getPriority() throws UaException {
        BaseDataVariableTypeNode node = getPriorityNode();
        return (UByte) node.getValue().getValue().getValue();
    }

    @Override
    public void setPriority(UByte priority) throws UaException {
        BaseDataVariableTypeNode node = getPriorityNode();
        node.setValue(new Variant(priority));
    }

    @Override
    public UByte readPriority() throws UaException {
        try {
            return readPriorityAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePriority(UByte priority) throws UaException {
        try {
            writePriorityAsync(priority).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UByte> readPriorityAsync() {
        return getPriorityNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UByte) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writePriorityAsync(UByte priority) {
        DataValue value = DataValue.valueOnly(new Variant(priority));
        return getPriorityNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getPriorityNode() throws UaException {
        try {
            return getPriorityNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPriorityNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Priority", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public Double getPublishingInterval() throws UaException {
        BaseDataVariableTypeNode node = getPublishingIntervalNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setPublishingInterval(Double publishingInterval) throws UaException {
        BaseDataVariableTypeNode node = getPublishingIntervalNode();
        node.setValue(new Variant(publishingInterval));
    }

    @Override
    public Double readPublishingInterval() throws UaException {
        try {
            return readPublishingIntervalAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePublishingInterval(Double publishingInterval) throws UaException {
        try {
            writePublishingIntervalAsync(publishingInterval).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readPublishingIntervalAsync() {
        return getPublishingIntervalNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writePublishingIntervalAsync(Double publishingInterval) {
        DataValue value = DataValue.valueOnly(new Variant(publishingInterval));
        return getPublishingIntervalNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getPublishingIntervalNode() throws UaException {
        try {
            return getPublishingIntervalNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPublishingIntervalNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PublishingInterval", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxKeepAliveCount() throws UaException {
        BaseDataVariableTypeNode node = getMaxKeepAliveCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxKeepAliveCount(UInteger maxKeepAliveCount) throws UaException {
        BaseDataVariableTypeNode node = getMaxKeepAliveCountNode();
        node.setValue(new Variant(maxKeepAliveCount));
    }

    @Override
    public UInteger readMaxKeepAliveCount() throws UaException {
        try {
            return readMaxKeepAliveCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxKeepAliveCount(UInteger maxKeepAliveCount) throws UaException {
        try {
            writeMaxKeepAliveCountAsync(maxKeepAliveCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxKeepAliveCountAsync() {
        return getMaxKeepAliveCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxKeepAliveCountAsync(UInteger maxKeepAliveCount) {
        DataValue value = DataValue.valueOnly(new Variant(maxKeepAliveCount));
        return getMaxKeepAliveCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMaxKeepAliveCountNode() throws UaException {
        try {
            return getMaxKeepAliveCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxKeepAliveCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxKeepAliveCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxLifetimeCount() throws UaException {
        BaseDataVariableTypeNode node = getMaxLifetimeCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxLifetimeCount(UInteger maxLifetimeCount) throws UaException {
        BaseDataVariableTypeNode node = getMaxLifetimeCountNode();
        node.setValue(new Variant(maxLifetimeCount));
    }

    @Override
    public UInteger readMaxLifetimeCount() throws UaException {
        try {
            return readMaxLifetimeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxLifetimeCount(UInteger maxLifetimeCount) throws UaException {
        try {
            writeMaxLifetimeCountAsync(maxLifetimeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxLifetimeCountAsync() {
        return getMaxLifetimeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxLifetimeCountAsync(UInteger maxLifetimeCount) {
        DataValue value = DataValue.valueOnly(new Variant(maxLifetimeCount));
        return getMaxLifetimeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMaxLifetimeCountNode() throws UaException {
        try {
            return getMaxLifetimeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxLifetimeCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxLifetimeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxNotificationsPerPublish() throws UaException {
        BaseDataVariableTypeNode node = getMaxNotificationsPerPublishNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxNotificationsPerPublish(UInteger maxNotificationsPerPublish) throws
        UaException {
        BaseDataVariableTypeNode node = getMaxNotificationsPerPublishNode();
        node.setValue(new Variant(maxNotificationsPerPublish));
    }

    @Override
    public UInteger readMaxNotificationsPerPublish() throws UaException {
        try {
            return readMaxNotificationsPerPublishAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxNotificationsPerPublish(UInteger maxNotificationsPerPublish) throws
        UaException {
        try {
            writeMaxNotificationsPerPublishAsync(maxNotificationsPerPublish).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxNotificationsPerPublishAsync() {
        return getMaxNotificationsPerPublishNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxNotificationsPerPublishAsync(
        UInteger maxNotificationsPerPublish) {
        DataValue value = DataValue.valueOnly(new Variant(maxNotificationsPerPublish));
        return getMaxNotificationsPerPublishNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMaxNotificationsPerPublishNode() throws UaException {
        try {
            return getMaxNotificationsPerPublishNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxNotificationsPerPublishNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxNotificationsPerPublish", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public Boolean getPublishingEnabled() throws UaException {
        BaseDataVariableTypeNode node = getPublishingEnabledNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setPublishingEnabled(Boolean publishingEnabled) throws UaException {
        BaseDataVariableTypeNode node = getPublishingEnabledNode();
        node.setValue(new Variant(publishingEnabled));
    }

    @Override
    public Boolean readPublishingEnabled() throws UaException {
        try {
            return readPublishingEnabledAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePublishingEnabled(Boolean publishingEnabled) throws UaException {
        try {
            writePublishingEnabledAsync(publishingEnabled).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readPublishingEnabledAsync() {
        return getPublishingEnabledNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writePublishingEnabledAsync(Boolean publishingEnabled) {
        DataValue value = DataValue.valueOnly(new Variant(publishingEnabled));
        return getPublishingEnabledNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getPublishingEnabledNode() throws UaException {
        try {
            return getPublishingEnabledNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPublishingEnabledNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PublishingEnabled", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getModifyCount() throws UaException {
        BaseDataVariableTypeNode node = getModifyCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setModifyCount(UInteger modifyCount) throws UaException {
        BaseDataVariableTypeNode node = getModifyCountNode();
        node.setValue(new Variant(modifyCount));
    }

    @Override
    public UInteger readModifyCount() throws UaException {
        try {
            return readModifyCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeModifyCount(UInteger modifyCount) throws UaException {
        try {
            writeModifyCountAsync(modifyCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readModifyCountAsync() {
        return getModifyCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeModifyCountAsync(UInteger modifyCount) {
        DataValue value = DataValue.valueOnly(new Variant(modifyCount));
        return getModifyCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getModifyCountNode() throws UaException {
        try {
            return getModifyCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getModifyCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModifyCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getEnableCount() throws UaException {
        BaseDataVariableTypeNode node = getEnableCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnableCount(UInteger enableCount) throws UaException {
        BaseDataVariableTypeNode node = getEnableCountNode();
        node.setValue(new Variant(enableCount));
    }

    @Override
    public UInteger readEnableCount() throws UaException {
        try {
            return readEnableCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnableCount(UInteger enableCount) throws UaException {
        try {
            writeEnableCountAsync(enableCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readEnableCountAsync() {
        return getEnableCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEnableCountAsync(UInteger enableCount) {
        DataValue value = DataValue.valueOnly(new Variant(enableCount));
        return getEnableCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getEnableCountNode() throws UaException {
        try {
            return getEnableCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEnableCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnableCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getDisableCount() throws UaException {
        BaseDataVariableTypeNode node = getDisableCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setDisableCount(UInteger disableCount) throws UaException {
        BaseDataVariableTypeNode node = getDisableCountNode();
        node.setValue(new Variant(disableCount));
    }

    @Override
    public UInteger readDisableCount() throws UaException {
        try {
            return readDisableCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDisableCount(UInteger disableCount) throws UaException {
        try {
            writeDisableCountAsync(disableCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readDisableCountAsync() {
        return getDisableCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDisableCountAsync(UInteger disableCount) {
        DataValue value = DataValue.valueOnly(new Variant(disableCount));
        return getDisableCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDisableCountNode() throws UaException {
        try {
            return getDisableCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDisableCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DisableCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getRepublishRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getRepublishRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setRepublishRequestCount(UInteger republishRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getRepublishRequestCountNode();
        node.setValue(new Variant(republishRequestCount));
    }

    @Override
    public UInteger readRepublishRequestCount() throws UaException {
        try {
            return readRepublishRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRepublishRequestCount(UInteger republishRequestCount) throws UaException {
        try {
            writeRepublishRequestCountAsync(republishRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readRepublishRequestCountAsync() {
        return getRepublishRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeRepublishRequestCountAsync(
        UInteger republishRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(republishRequestCount));
        return getRepublishRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getRepublishRequestCountNode() throws UaException {
        try {
            return getRepublishRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRepublishRequestCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RepublishRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getRepublishMessageRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getRepublishMessageRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setRepublishMessageRequestCount(UInteger republishMessageRequestCount) throws
        UaException {
        BaseDataVariableTypeNode node = getRepublishMessageRequestCountNode();
        node.setValue(new Variant(republishMessageRequestCount));
    }

    @Override
    public UInteger readRepublishMessageRequestCount() throws UaException {
        try {
            return readRepublishMessageRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRepublishMessageRequestCount(UInteger republishMessageRequestCount) throws
        UaException {
        try {
            writeRepublishMessageRequestCountAsync(republishMessageRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readRepublishMessageRequestCountAsync() {
        return getRepublishMessageRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeRepublishMessageRequestCountAsync(
        UInteger republishMessageRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(republishMessageRequestCount));
        return getRepublishMessageRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getRepublishMessageRequestCountNode() throws UaException {
        try {
            return getRepublishMessageRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRepublishMessageRequestCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RepublishMessageRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getRepublishMessageCount() throws UaException {
        BaseDataVariableTypeNode node = getRepublishMessageCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setRepublishMessageCount(UInteger republishMessageCount) throws UaException {
        BaseDataVariableTypeNode node = getRepublishMessageCountNode();
        node.setValue(new Variant(republishMessageCount));
    }

    @Override
    public UInteger readRepublishMessageCount() throws UaException {
        try {
            return readRepublishMessageCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRepublishMessageCount(UInteger republishMessageCount) throws UaException {
        try {
            writeRepublishMessageCountAsync(republishMessageCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readRepublishMessageCountAsync() {
        return getRepublishMessageCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeRepublishMessageCountAsync(
        UInteger republishMessageCount) {
        DataValue value = DataValue.valueOnly(new Variant(republishMessageCount));
        return getRepublishMessageCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getRepublishMessageCountNode() throws UaException {
        try {
            return getRepublishMessageCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRepublishMessageCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RepublishMessageCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getTransferRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getTransferRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransferRequestCount(UInteger transferRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getTransferRequestCountNode();
        node.setValue(new Variant(transferRequestCount));
    }

    @Override
    public UInteger readTransferRequestCount() throws UaException {
        try {
            return readTransferRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransferRequestCount(UInteger transferRequestCount) throws UaException {
        try {
            writeTransferRequestCountAsync(transferRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readTransferRequestCountAsync() {
        return getTransferRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransferRequestCountAsync(
        UInteger transferRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(transferRequestCount));
        return getTransferRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTransferRequestCountNode() throws UaException {
        try {
            return getTransferRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransferRequestCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransferRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getTransferredToAltClientCount() throws UaException {
        BaseDataVariableTypeNode node = getTransferredToAltClientCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransferredToAltClientCount(UInteger transferredToAltClientCount) throws
        UaException {
        BaseDataVariableTypeNode node = getTransferredToAltClientCountNode();
        node.setValue(new Variant(transferredToAltClientCount));
    }

    @Override
    public UInteger readTransferredToAltClientCount() throws UaException {
        try {
            return readTransferredToAltClientCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransferredToAltClientCount(UInteger transferredToAltClientCount) throws
        UaException {
        try {
            writeTransferredToAltClientCountAsync(transferredToAltClientCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readTransferredToAltClientCountAsync() {
        return getTransferredToAltClientCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransferredToAltClientCountAsync(
        UInteger transferredToAltClientCount) {
        DataValue value = DataValue.valueOnly(new Variant(transferredToAltClientCount));
        return getTransferredToAltClientCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTransferredToAltClientCountNode() throws UaException {
        try {
            return getTransferredToAltClientCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransferredToAltClientCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransferredToAltClientCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getTransferredToSameClientCount() throws UaException {
        BaseDataVariableTypeNode node = getTransferredToSameClientCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransferredToSameClientCount(UInteger transferredToSameClientCount) throws
        UaException {
        BaseDataVariableTypeNode node = getTransferredToSameClientCountNode();
        node.setValue(new Variant(transferredToSameClientCount));
    }

    @Override
    public UInteger readTransferredToSameClientCount() throws UaException {
        try {
            return readTransferredToSameClientCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransferredToSameClientCount(UInteger transferredToSameClientCount) throws
        UaException {
        try {
            writeTransferredToSameClientCountAsync(transferredToSameClientCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readTransferredToSameClientCountAsync() {
        return getTransferredToSameClientCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransferredToSameClientCountAsync(
        UInteger transferredToSameClientCount) {
        DataValue value = DataValue.valueOnly(new Variant(transferredToSameClientCount));
        return getTransferredToSameClientCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTransferredToSameClientCountNode() throws UaException {
        try {
            return getTransferredToSameClientCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransferredToSameClientCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransferredToSameClientCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getPublishRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getPublishRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setPublishRequestCount(UInteger publishRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getPublishRequestCountNode();
        node.setValue(new Variant(publishRequestCount));
    }

    @Override
    public UInteger readPublishRequestCount() throws UaException {
        try {
            return readPublishRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePublishRequestCount(UInteger publishRequestCount) throws UaException {
        try {
            writePublishRequestCountAsync(publishRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readPublishRequestCountAsync() {
        return getPublishRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writePublishRequestCountAsync(UInteger publishRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(publishRequestCount));
        return getPublishRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getPublishRequestCountNode() throws UaException {
        try {
            return getPublishRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPublishRequestCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PublishRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getDataChangeNotificationsCount() throws UaException {
        BaseDataVariableTypeNode node = getDataChangeNotificationsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setDataChangeNotificationsCount(UInteger dataChangeNotificationsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDataChangeNotificationsCountNode();
        node.setValue(new Variant(dataChangeNotificationsCount));
    }

    @Override
    public UInteger readDataChangeNotificationsCount() throws UaException {
        try {
            return readDataChangeNotificationsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDataChangeNotificationsCount(UInteger dataChangeNotificationsCount) throws
        UaException {
        try {
            writeDataChangeNotificationsCountAsync(dataChangeNotificationsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readDataChangeNotificationsCountAsync() {
        return getDataChangeNotificationsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDataChangeNotificationsCountAsync(
        UInteger dataChangeNotificationsCount) {
        DataValue value = DataValue.valueOnly(new Variant(dataChangeNotificationsCount));
        return getDataChangeNotificationsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDataChangeNotificationsCountNode() throws UaException {
        try {
            return getDataChangeNotificationsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDataChangeNotificationsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DataChangeNotificationsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getEventNotificationsCount() throws UaException {
        BaseDataVariableTypeNode node = getEventNotificationsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setEventNotificationsCount(UInteger eventNotificationsCount) throws UaException {
        BaseDataVariableTypeNode node = getEventNotificationsCountNode();
        node.setValue(new Variant(eventNotificationsCount));
    }

    @Override
    public UInteger readEventNotificationsCount() throws UaException {
        try {
            return readEventNotificationsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEventNotificationsCount(UInteger eventNotificationsCount) throws UaException {
        try {
            writeEventNotificationsCountAsync(eventNotificationsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readEventNotificationsCountAsync() {
        return getEventNotificationsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEventNotificationsCountAsync(
        UInteger eventNotificationsCount) {
        DataValue value = DataValue.valueOnly(new Variant(eventNotificationsCount));
        return getEventNotificationsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getEventNotificationsCountNode() throws UaException {
        try {
            return getEventNotificationsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEventNotificationsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EventNotificationsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getNotificationsCount() throws UaException {
        BaseDataVariableTypeNode node = getNotificationsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setNotificationsCount(UInteger notificationsCount) throws UaException {
        BaseDataVariableTypeNode node = getNotificationsCountNode();
        node.setValue(new Variant(notificationsCount));
    }

    @Override
    public UInteger readNotificationsCount() throws UaException {
        try {
            return readNotificationsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNotificationsCount(UInteger notificationsCount) throws UaException {
        try {
            writeNotificationsCountAsync(notificationsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readNotificationsCountAsync() {
        return getNotificationsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNotificationsCountAsync(UInteger notificationsCount) {
        DataValue value = DataValue.valueOnly(new Variant(notificationsCount));
        return getNotificationsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getNotificationsCountNode() throws UaException {
        try {
            return getNotificationsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getNotificationsCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NotificationsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getLatePublishRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getLatePublishRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setLatePublishRequestCount(UInteger latePublishRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getLatePublishRequestCountNode();
        node.setValue(new Variant(latePublishRequestCount));
    }

    @Override
    public UInteger readLatePublishRequestCount() throws UaException {
        try {
            return readLatePublishRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLatePublishRequestCount(UInteger latePublishRequestCount) throws UaException {
        try {
            writeLatePublishRequestCountAsync(latePublishRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readLatePublishRequestCountAsync() {
        return getLatePublishRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLatePublishRequestCountAsync(
        UInteger latePublishRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(latePublishRequestCount));
        return getLatePublishRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getLatePublishRequestCountNode() throws UaException {
        try {
            return getLatePublishRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getLatePublishRequestCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LatePublishRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentKeepAliveCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentKeepAliveCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentKeepAliveCount(UInteger currentKeepAliveCount) throws UaException {
        BaseDataVariableTypeNode node = getCurrentKeepAliveCountNode();
        node.setValue(new Variant(currentKeepAliveCount));
    }

    @Override
    public UInteger readCurrentKeepAliveCount() throws UaException {
        try {
            return readCurrentKeepAliveCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentKeepAliveCount(UInteger currentKeepAliveCount) throws UaException {
        try {
            writeCurrentKeepAliveCountAsync(currentKeepAliveCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentKeepAliveCountAsync() {
        return getCurrentKeepAliveCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentKeepAliveCountAsync(
        UInteger currentKeepAliveCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentKeepAliveCount));
        return getCurrentKeepAliveCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentKeepAliveCountNode() throws UaException {
        try {
            return getCurrentKeepAliveCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentKeepAliveCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentKeepAliveCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentLifetimeCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentLifetimeCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentLifetimeCount(UInteger currentLifetimeCount) throws UaException {
        BaseDataVariableTypeNode node = getCurrentLifetimeCountNode();
        node.setValue(new Variant(currentLifetimeCount));
    }

    @Override
    public UInteger readCurrentLifetimeCount() throws UaException {
        try {
            return readCurrentLifetimeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentLifetimeCount(UInteger currentLifetimeCount) throws UaException {
        try {
            writeCurrentLifetimeCountAsync(currentLifetimeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentLifetimeCountAsync() {
        return getCurrentLifetimeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentLifetimeCountAsync(
        UInteger currentLifetimeCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentLifetimeCount));
        return getCurrentLifetimeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentLifetimeCountNode() throws UaException {
        try {
            return getCurrentLifetimeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentLifetimeCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentLifetimeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getUnacknowledgedMessageCount() throws UaException {
        BaseDataVariableTypeNode node = getUnacknowledgedMessageCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setUnacknowledgedMessageCount(UInteger unacknowledgedMessageCount) throws
        UaException {
        BaseDataVariableTypeNode node = getUnacknowledgedMessageCountNode();
        node.setValue(new Variant(unacknowledgedMessageCount));
    }

    @Override
    public UInteger readUnacknowledgedMessageCount() throws UaException {
        try {
            return readUnacknowledgedMessageCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUnacknowledgedMessageCount(UInteger unacknowledgedMessageCount) throws
        UaException {
        try {
            writeUnacknowledgedMessageCountAsync(unacknowledgedMessageCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readUnacknowledgedMessageCountAsync() {
        return getUnacknowledgedMessageCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeUnacknowledgedMessageCountAsync(
        UInteger unacknowledgedMessageCount) {
        DataValue value = DataValue.valueOnly(new Variant(unacknowledgedMessageCount));
        return getUnacknowledgedMessageCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getUnacknowledgedMessageCountNode() throws UaException {
        try {
            return getUnacknowledgedMessageCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getUnacknowledgedMessageCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UnacknowledgedMessageCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getDiscardedMessageCount() throws UaException {
        BaseDataVariableTypeNode node = getDiscardedMessageCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setDiscardedMessageCount(UInteger discardedMessageCount) throws UaException {
        BaseDataVariableTypeNode node = getDiscardedMessageCountNode();
        node.setValue(new Variant(discardedMessageCount));
    }

    @Override
    public UInteger readDiscardedMessageCount() throws UaException {
        try {
            return readDiscardedMessageCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDiscardedMessageCount(UInteger discardedMessageCount) throws UaException {
        try {
            writeDiscardedMessageCountAsync(discardedMessageCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readDiscardedMessageCountAsync() {
        return getDiscardedMessageCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDiscardedMessageCountAsync(
        UInteger discardedMessageCount) {
        DataValue value = DataValue.valueOnly(new Variant(discardedMessageCount));
        return getDiscardedMessageCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDiscardedMessageCountNode() throws UaException {
        try {
            return getDiscardedMessageCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDiscardedMessageCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DiscardedMessageCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMonitoredItemCount() throws UaException {
        BaseDataVariableTypeNode node = getMonitoredItemCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMonitoredItemCount(UInteger monitoredItemCount) throws UaException {
        BaseDataVariableTypeNode node = getMonitoredItemCountNode();
        node.setValue(new Variant(monitoredItemCount));
    }

    @Override
    public UInteger readMonitoredItemCount() throws UaException {
        try {
            return readMonitoredItemCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMonitoredItemCount(UInteger monitoredItemCount) throws UaException {
        try {
            writeMonitoredItemCountAsync(monitoredItemCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMonitoredItemCountAsync() {
        return getMonitoredItemCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMonitoredItemCountAsync(UInteger monitoredItemCount) {
        DataValue value = DataValue.valueOnly(new Variant(monitoredItemCount));
        return getMonitoredItemCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMonitoredItemCountNode() throws UaException {
        try {
            return getMonitoredItemCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMonitoredItemCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MonitoredItemCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getDisabledMonitoredItemCount() throws UaException {
        BaseDataVariableTypeNode node = getDisabledMonitoredItemCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setDisabledMonitoredItemCount(UInteger disabledMonitoredItemCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDisabledMonitoredItemCountNode();
        node.setValue(new Variant(disabledMonitoredItemCount));
    }

    @Override
    public UInteger readDisabledMonitoredItemCount() throws UaException {
        try {
            return readDisabledMonitoredItemCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDisabledMonitoredItemCount(UInteger disabledMonitoredItemCount) throws
        UaException {
        try {
            writeDisabledMonitoredItemCountAsync(disabledMonitoredItemCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readDisabledMonitoredItemCountAsync() {
        return getDisabledMonitoredItemCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDisabledMonitoredItemCountAsync(
        UInteger disabledMonitoredItemCount) {
        DataValue value = DataValue.valueOnly(new Variant(disabledMonitoredItemCount));
        return getDisabledMonitoredItemCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDisabledMonitoredItemCountNode() throws UaException {
        try {
            return getDisabledMonitoredItemCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDisabledMonitoredItemCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DisabledMonitoredItemCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMonitoringQueueOverflowCount() throws UaException {
        BaseDataVariableTypeNode node = getMonitoringQueueOverflowCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMonitoringQueueOverflowCount(UInteger monitoringQueueOverflowCount) throws
        UaException {
        BaseDataVariableTypeNode node = getMonitoringQueueOverflowCountNode();
        node.setValue(new Variant(monitoringQueueOverflowCount));
    }

    @Override
    public UInteger readMonitoringQueueOverflowCount() throws UaException {
        try {
            return readMonitoringQueueOverflowCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMonitoringQueueOverflowCount(UInteger monitoringQueueOverflowCount) throws
        UaException {
        try {
            writeMonitoringQueueOverflowCountAsync(monitoringQueueOverflowCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMonitoringQueueOverflowCountAsync() {
        return getMonitoringQueueOverflowCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMonitoringQueueOverflowCountAsync(
        UInteger monitoringQueueOverflowCount) {
        DataValue value = DataValue.valueOnly(new Variant(monitoringQueueOverflowCount));
        return getMonitoringQueueOverflowCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMonitoringQueueOverflowCountNode() throws UaException {
        try {
            return getMonitoringQueueOverflowCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMonitoringQueueOverflowCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MonitoringQueueOverflowCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getNextSequenceNumber() throws UaException {
        BaseDataVariableTypeNode node = getNextSequenceNumberNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setNextSequenceNumber(UInteger nextSequenceNumber) throws UaException {
        BaseDataVariableTypeNode node = getNextSequenceNumberNode();
        node.setValue(new Variant(nextSequenceNumber));
    }

    @Override
    public UInteger readNextSequenceNumber() throws UaException {
        try {
            return readNextSequenceNumberAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNextSequenceNumber(UInteger nextSequenceNumber) throws UaException {
        try {
            writeNextSequenceNumberAsync(nextSequenceNumber).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readNextSequenceNumberAsync() {
        return getNextSequenceNumberNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNextSequenceNumberAsync(UInteger nextSequenceNumber) {
        DataValue value = DataValue.valueOnly(new Variant(nextSequenceNumber));
        return getNextSequenceNumberNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getNextSequenceNumberNode() throws UaException {
        try {
            return getNextSequenceNumberNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getNextSequenceNumberNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NextSequenceNumber", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getEventQueueOverflowCount() throws UaException {
        BaseDataVariableTypeNode node = getEventQueueOverflowCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setEventQueueOverflowCount(UInteger eventQueueOverflowCount) throws UaException {
        BaseDataVariableTypeNode node = getEventQueueOverflowCountNode();
        node.setValue(new Variant(eventQueueOverflowCount));
    }

    @Override
    public UInteger readEventQueueOverflowCount() throws UaException {
        try {
            return readEventQueueOverflowCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEventQueueOverflowCount(UInteger eventQueueOverflowCount) throws UaException {
        try {
            writeEventQueueOverflowCountAsync(eventQueueOverflowCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readEventQueueOverflowCountAsync() {
        return getEventQueueOverflowCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEventQueueOverflowCountAsync(
        UInteger eventQueueOverflowCount) {
        DataValue value = DataValue.valueOnly(new Variant(eventQueueOverflowCount));
        return getEventQueueOverflowCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getEventQueueOverflowCountNode() throws UaException {
        try {
            return getEventQueueOverflowCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEventQueueOverflowCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EventQueueOverflowCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
