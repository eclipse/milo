package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SessionDiagnosticsObjectType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class SessionDiagnosticsObjectTypeNode extends BaseObjectTypeNode implements SessionDiagnosticsObjectType {
    public SessionDiagnosticsObjectTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SessionDiagnosticsDataType getSessionDiagnostics() throws UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        return (SessionDiagnosticsDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws
        UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        node.setValue(new Variant(sessionDiagnostics));
    }

    @Override
    public SessionDiagnosticsDataType readSessionDiagnostics() throws UaException {
        try {
            return readSessionDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws
        UaException {
        try {
            writeSessionDiagnosticsAsync(sessionDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsDataType> readSessionDiagnosticsAsync() {
        return getSessionDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SessionDiagnosticsDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionDiagnosticsAsync(
        SessionDiagnosticsDataType sessionDiagnostics) {
        DataValue value = DataValue.valueOnly(new Variant(sessionDiagnostics));
        return getSessionDiagnosticsNodeAsync()
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
    public SessionDiagnosticsVariableTypeNode getSessionDiagnosticsNode() throws UaException {
        try {
            return getSessionDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsVariableTypeNode> getSessionDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2197"), false);
        return future.thenApply(node -> (SessionDiagnosticsVariableTypeNode) node);
    }

    @Override
    public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() throws UaException {
        SessionSecurityDiagnosticsTypeNode node = getSessionSecurityDiagnosticsNode();
        return (SessionSecurityDiagnosticsDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) throws UaException {
        SessionSecurityDiagnosticsTypeNode node = getSessionSecurityDiagnosticsNode();
        node.setValue(new Variant(sessionSecurityDiagnostics));
    }

    @Override
    public SessionSecurityDiagnosticsDataType readSessionSecurityDiagnostics() throws UaException {
        try {
            return readSessionSecurityDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) throws UaException {
        try {
            writeSessionSecurityDiagnosticsAsync(sessionSecurityDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsDataType> readSessionSecurityDiagnosticsAsync(
    ) {
        return getSessionSecurityDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SessionSecurityDiagnosticsDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSessionSecurityDiagnosticsAsync(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) {
        DataValue value = DataValue.valueOnly(new Variant(sessionSecurityDiagnostics));
        return getSessionSecurityDiagnosticsNodeAsync()
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
    public SessionSecurityDiagnosticsTypeNode getSessionSecurityDiagnosticsNode() throws UaException {
        try {
            return getSessionSecurityDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsTypeNode> getSessionSecurityDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2244"), false);
        return future.thenApply(node -> (SessionSecurityDiagnosticsTypeNode) node);
    }

    @Override
    public SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() throws UaException {
        SubscriptionDiagnosticsArrayTypeNode node = getSubscriptionDiagnosticsArrayNode();
        return (SubscriptionDiagnosticsDataType[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException {
        SubscriptionDiagnosticsArrayTypeNode node = getSubscriptionDiagnosticsArrayNode();
        node.setValue(new Variant(subscriptionDiagnosticsArray));
    }

    @Override
    public SubscriptionDiagnosticsDataType[] readSubscriptionDiagnosticsArray() throws UaException {
        try {
            return readSubscriptionDiagnosticsArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSubscriptionDiagnosticsArray(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) throws UaException {
        try {
            writeSubscriptionDiagnosticsArrayAsync(subscriptionDiagnosticsArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsDataType[]> readSubscriptionDiagnosticsArrayAsync(
    ) {
        return getSubscriptionDiagnosticsArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SubscriptionDiagnosticsDataType[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSubscriptionDiagnosticsArrayAsync(
        SubscriptionDiagnosticsDataType[] subscriptionDiagnosticsArray) {
        DataValue value = DataValue.valueOnly(new Variant(subscriptionDiagnosticsArray));
        return getSubscriptionDiagnosticsArrayNodeAsync()
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
    public SubscriptionDiagnosticsArrayTypeNode getSubscriptionDiagnosticsArrayNode() throws
        UaException {
        try {
            return getSubscriptionDiagnosticsArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsArrayTypeNode> getSubscriptionDiagnosticsArrayNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2171"), false);
        return future.thenApply(node -> (SubscriptionDiagnosticsArrayTypeNode) node);
    }
}
