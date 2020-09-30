package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditOpenSecureChannelEventType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public class AuditOpenSecureChannelEventTypeNode extends AuditChannelEventTypeNode implements AuditOpenSecureChannelEventType {
    public AuditOpenSecureChannelEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public ByteString getClientCertificate() throws UaException {
        PropertyTypeNode node = getClientCertificateNode();
        return (ByteString) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientCertificate(ByteString clientCertificate) throws UaException {
        PropertyTypeNode node = getClientCertificateNode();
        node.setValue(new Variant(clientCertificate));
    }

    @Override
    public ByteString readClientCertificate() throws UaException {
        try {
            return readClientCertificateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientCertificate(ByteString clientCertificate) throws UaException {
        try {
            writeClientCertificateAsync(clientCertificate).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ByteString> readClientCertificateAsync() {
        return getClientCertificateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ByteString) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientCertificateAsync(ByteString clientCertificate) {
        DataValue value = DataValue.valueOnly(new Variant(clientCertificate));
        return getClientCertificateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getClientCertificateNode() throws UaException {
        try {
            return getClientCertificateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getClientCertificateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientCertificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getClientCertificateThumbprint() throws UaException {
        PropertyTypeNode node = getClientCertificateThumbprintNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientCertificateThumbprint(String clientCertificateThumbprint) throws
        UaException {
        PropertyTypeNode node = getClientCertificateThumbprintNode();
        node.setValue(new Variant(clientCertificateThumbprint));
    }

    @Override
    public String readClientCertificateThumbprint() throws UaException {
        try {
            return readClientCertificateThumbprintAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientCertificateThumbprint(String clientCertificateThumbprint) throws
        UaException {
        try {
            writeClientCertificateThumbprintAsync(clientCertificateThumbprint).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readClientCertificateThumbprintAsync() {
        return getClientCertificateThumbprintNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientCertificateThumbprintAsync(
        String clientCertificateThumbprint) {
        DataValue value = DataValue.valueOnly(new Variant(clientCertificateThumbprint));
        return getClientCertificateThumbprintNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getClientCertificateThumbprintNode() throws UaException {
        try {
            return getClientCertificateThumbprintNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getClientCertificateThumbprintNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientCertificateThumbprint", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public SecurityTokenRequestType getRequestType() throws UaException {
        PropertyTypeNode node = getRequestTypeNode();
        Object value = node.getValue().getValue().getValue();
        if (value instanceof Integer) {
            return SecurityTokenRequestType.from((Integer) value);
        } else if (value instanceof SecurityTokenRequestType) {
            return (SecurityTokenRequestType) value;
        } else {
            return null;
        }
    }

    @Override
    public void setRequestType(SecurityTokenRequestType requestType) throws UaException {
        PropertyTypeNode node = getRequestTypeNode();
        node.setValue(new Variant(requestType));
    }

    @Override
    public SecurityTokenRequestType readRequestType() throws UaException {
        try {
            return readRequestTypeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRequestType(SecurityTokenRequestType requestType) throws UaException {
        try {
            writeRequestTypeAsync(requestType).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SecurityTokenRequestType> readRequestTypeAsync() {
        return getRequestTypeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return SecurityTokenRequestType.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writeRequestTypeAsync(SecurityTokenRequestType requestType) {
        DataValue value = DataValue.valueOnly(new Variant(requestType));
        return getRequestTypeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getRequestTypeNode() throws UaException {
        try {
            return getRequestTypeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getRequestTypeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RequestType", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getSecurityPolicyUri() throws UaException {
        PropertyTypeNode node = getSecurityPolicyUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSecurityPolicyUri(String securityPolicyUri) throws UaException {
        PropertyTypeNode node = getSecurityPolicyUriNode();
        node.setValue(new Variant(securityPolicyUri));
    }

    @Override
    public String readSecurityPolicyUri() throws UaException {
        try {
            return readSecurityPolicyUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSecurityPolicyUri(String securityPolicyUri) throws UaException {
        try {
            writeSecurityPolicyUriAsync(securityPolicyUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readSecurityPolicyUriAsync() {
        return getSecurityPolicyUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSecurityPolicyUriAsync(String securityPolicyUri) {
        DataValue value = DataValue.valueOnly(new Variant(securityPolicyUri));
        return getSecurityPolicyUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSecurityPolicyUriNode() throws UaException {
        try {
            return getSecurityPolicyUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSecurityPolicyUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SecurityPolicyUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public MessageSecurityMode getSecurityMode() throws UaException {
        PropertyTypeNode node = getSecurityModeNode();
        Object value = node.getValue().getValue().getValue();
        if (value instanceof Integer) {
            return MessageSecurityMode.from((Integer) value);
        } else if (value instanceof MessageSecurityMode) {
            return (MessageSecurityMode) value;
        } else {
            return null;
        }
    }

    @Override
    public void setSecurityMode(MessageSecurityMode securityMode) throws UaException {
        PropertyTypeNode node = getSecurityModeNode();
        node.setValue(new Variant(securityMode));
    }

    @Override
    public MessageSecurityMode readSecurityMode() throws UaException {
        try {
            return readSecurityModeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSecurityMode(MessageSecurityMode securityMode) throws UaException {
        try {
            writeSecurityModeAsync(securityMode).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends MessageSecurityMode> readSecurityModeAsync() {
        return getSecurityModeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return MessageSecurityMode.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writeSecurityModeAsync(MessageSecurityMode securityMode) {
        DataValue value = DataValue.valueOnly(new Variant(securityMode));
        return getSecurityModeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSecurityModeNode() throws UaException {
        try {
            return getSecurityModeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSecurityModeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SecurityMode", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getRequestedLifetime() throws UaException {
        PropertyTypeNode node = getRequestedLifetimeNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setRequestedLifetime(Double requestedLifetime) throws UaException {
        PropertyTypeNode node = getRequestedLifetimeNode();
        node.setValue(new Variant(requestedLifetime));
    }

    @Override
    public Double readRequestedLifetime() throws UaException {
        try {
            return readRequestedLifetimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRequestedLifetime(Double requestedLifetime) throws UaException {
        try {
            writeRequestedLifetimeAsync(requestedLifetime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readRequestedLifetimeAsync() {
        return getRequestedLifetimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeRequestedLifetimeAsync(Double requestedLifetime) {
        DataValue value = DataValue.valueOnly(new Variant(requestedLifetime));
        return getRequestedLifetimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getRequestedLifetimeNode() throws UaException {
        try {
            return getRequestedLifetimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getRequestedLifetimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RequestedLifetime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
