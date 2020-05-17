package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditActivateSessionEventType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class AuditActivateSessionEventTypeNode extends AuditSessionEventTypeNode implements AuditActivateSessionEventType {
    public AuditActivateSessionEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SignedSoftwareCertificate[] getClientSoftwareCertificates() throws UaException {
        PropertyTypeNode node = getClientSoftwareCertificatesNode();
        return (SignedSoftwareCertificate[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientSoftwareCertificates(SignedSoftwareCertificate[] clientSoftwareCertificates)
        throws UaException {
        PropertyTypeNode node = getClientSoftwareCertificatesNode();
        node.setValue(new Variant(clientSoftwareCertificates));
    }

    @Override
    public SignedSoftwareCertificate[] readClientSoftwareCertificates() throws UaException {
        try {
            return readClientSoftwareCertificatesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientSoftwareCertificates(
        SignedSoftwareCertificate[] clientSoftwareCertificates) throws UaException {
        try {
            writeClientSoftwareCertificatesAsync(clientSoftwareCertificates).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SignedSoftwareCertificate[]> readClientSoftwareCertificatesAsync(
    ) {
        return getClientSoftwareCertificatesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SignedSoftwareCertificate[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeClientSoftwareCertificatesAsync(
        SignedSoftwareCertificate[] clientSoftwareCertificates) {
        DataValue value = DataValue.valueOnly(new Variant(clientSoftwareCertificates));
        return getClientSoftwareCertificatesNodeAsync()
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
    public PropertyTypeNode getClientSoftwareCertificatesNode() throws UaException {
        try {
            return getClientSoftwareCertificatesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getClientSoftwareCertificatesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientSoftwareCertificates", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UserIdentityToken getUserIdentityToken() throws UaException {
        PropertyTypeNode node = getUserIdentityTokenNode();
        return (UserIdentityToken) node.getValue().getValue().getValue();
    }

    @Override
    public void setUserIdentityToken(UserIdentityToken userIdentityToken) throws UaException {
        PropertyTypeNode node = getUserIdentityTokenNode();
        node.setValue(new Variant(userIdentityToken));
    }

    @Override
    public UserIdentityToken readUserIdentityToken() throws UaException {
        try {
            return readUserIdentityTokenAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUserIdentityToken(UserIdentityToken userIdentityToken) throws UaException {
        try {
            writeUserIdentityTokenAsync(userIdentityToken).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UserIdentityToken> readUserIdentityTokenAsync() {
        return getUserIdentityTokenNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UserIdentityToken) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeUserIdentityTokenAsync(UserIdentityToken userIdentityToken) {
        DataValue value = DataValue.valueOnly(new Variant(userIdentityToken));
        return getUserIdentityTokenNodeAsync()
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
    public PropertyTypeNode getUserIdentityTokenNode() throws UaException {
        try {
            return getUserIdentityTokenNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getUserIdentityTokenNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UserIdentityToken", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getSecureChannelId() throws UaException {
        PropertyTypeNode node = getSecureChannelIdNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSecureChannelId(String secureChannelId) throws UaException {
        PropertyTypeNode node = getSecureChannelIdNode();
        node.setValue(new Variant(secureChannelId));
    }

    @Override
    public String readSecureChannelId() throws UaException {
        try {
            return readSecureChannelIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSecureChannelId(String secureChannelId) throws UaException {
        try {
            writeSecureChannelIdAsync(secureChannelId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readSecureChannelIdAsync() {
        return getSecureChannelIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSecureChannelIdAsync(String secureChannelId) {
        DataValue value = DataValue.valueOnly(new Variant(secureChannelId));
        return getSecureChannelIdNodeAsync()
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
    public PropertyTypeNode getSecureChannelIdNode() throws UaException {
        try {
            return getSecureChannelIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSecureChannelIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SecureChannelId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
