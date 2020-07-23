package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsType;
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

public class SessionSecurityDiagnosticsTypeNode extends BaseDataVariableTypeNode implements SessionSecurityDiagnosticsType {
    public SessionSecurityDiagnosticsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                              UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                              double minimumSamplingInterval, boolean historizing) {
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
    public String getClientUserIdOfSession() throws UaException {
        BaseDataVariableTypeNode node = getClientUserIdOfSessionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientUserIdOfSession(String clientUserIdOfSession) throws UaException {
        BaseDataVariableTypeNode node = getClientUserIdOfSessionNode();
        node.setValue(new Variant(clientUserIdOfSession));
    }

    @Override
    public String readClientUserIdOfSession() throws UaException {
        try {
            return readClientUserIdOfSessionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientUserIdOfSession(String clientUserIdOfSession) throws UaException {
        try {
            writeClientUserIdOfSessionAsync(clientUserIdOfSession).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readClientUserIdOfSessionAsync() {
        return getClientUserIdOfSessionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientUserIdOfSessionAsync(
        String clientUserIdOfSession) {
        DataValue value = DataValue.valueOnly(new Variant(clientUserIdOfSession));
        return getClientUserIdOfSessionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getClientUserIdOfSessionNode() throws UaException {
        try {
            return getClientUserIdOfSessionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientUserIdOfSessionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientUserIdOfSession", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String[] getClientUserIdHistory() throws UaException {
        BaseDataVariableTypeNode node = getClientUserIdHistoryNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientUserIdHistory(String[] clientUserIdHistory) throws UaException {
        BaseDataVariableTypeNode node = getClientUserIdHistoryNode();
        node.setValue(new Variant(clientUserIdHistory));
    }

    @Override
    public String[] readClientUserIdHistory() throws UaException {
        try {
            return readClientUserIdHistoryAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientUserIdHistory(String[] clientUserIdHistory) throws UaException {
        try {
            writeClientUserIdHistoryAsync(clientUserIdHistory).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readClientUserIdHistoryAsync() {
        return getClientUserIdHistoryNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientUserIdHistoryAsync(String[] clientUserIdHistory) {
        DataValue value = DataValue.valueOnly(new Variant(clientUserIdHistory));
        return getClientUserIdHistoryNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getClientUserIdHistoryNode() throws UaException {
        try {
            return getClientUserIdHistoryNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientUserIdHistoryNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientUserIdHistory", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getAuthenticationMechanism() throws UaException {
        BaseDataVariableTypeNode node = getAuthenticationMechanismNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setAuthenticationMechanism(String authenticationMechanism) throws UaException {
        BaseDataVariableTypeNode node = getAuthenticationMechanismNode();
        node.setValue(new Variant(authenticationMechanism));
    }

    @Override
    public String readAuthenticationMechanism() throws UaException {
        try {
            return readAuthenticationMechanismAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAuthenticationMechanism(String authenticationMechanism) throws UaException {
        try {
            writeAuthenticationMechanismAsync(authenticationMechanism).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readAuthenticationMechanismAsync() {
        return getAuthenticationMechanismNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeAuthenticationMechanismAsync(
        String authenticationMechanism) {
        DataValue value = DataValue.valueOnly(new Variant(authenticationMechanism));
        return getAuthenticationMechanismNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getAuthenticationMechanismNode() throws UaException {
        try {
            return getAuthenticationMechanismNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getAuthenticationMechanismNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AuthenticationMechanism", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getEncoding() throws UaException {
        BaseDataVariableTypeNode node = getEncodingNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setEncoding(String encoding) throws UaException {
        BaseDataVariableTypeNode node = getEncodingNode();
        node.setValue(new Variant(encoding));
    }

    @Override
    public String readEncoding() throws UaException {
        try {
            return readEncodingAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEncoding(String encoding) throws UaException {
        try {
            writeEncodingAsync(encoding).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readEncodingAsync() {
        return getEncodingNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEncodingAsync(String encoding) {
        DataValue value = DataValue.valueOnly(new Variant(encoding));
        return getEncodingNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getEncodingNode() throws UaException {
        try {
            return getEncodingNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEncodingNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Encoding", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getTransportProtocol() throws UaException {
        BaseDataVariableTypeNode node = getTransportProtocolNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransportProtocol(String transportProtocol) throws UaException {
        BaseDataVariableTypeNode node = getTransportProtocolNode();
        node.setValue(new Variant(transportProtocol));
    }

    @Override
    public String readTransportProtocol() throws UaException {
        try {
            return readTransportProtocolAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransportProtocol(String transportProtocol) throws UaException {
        try {
            writeTransportProtocolAsync(transportProtocol).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readTransportProtocolAsync() {
        return getTransportProtocolNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransportProtocolAsync(String transportProtocol) {
        DataValue value = DataValue.valueOnly(new Variant(transportProtocol));
        return getTransportProtocolNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTransportProtocolNode() throws UaException {
        try {
            return getTransportProtocolNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransportProtocolNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransportProtocol", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public MessageSecurityMode getSecurityMode() throws UaException {
        BaseDataVariableTypeNode node = getSecurityModeNode();
        return (MessageSecurityMode) node.getValue().getValue().getValue();
    }

    @Override
    public void setSecurityMode(MessageSecurityMode securityMode) throws UaException {
        BaseDataVariableTypeNode node = getSecurityModeNode();
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
        return getSecurityModeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (MessageSecurityMode) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSecurityModeAsync(MessageSecurityMode securityMode) {
        DataValue value = DataValue.valueOnly(new Variant(securityMode));
        return getSecurityModeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSecurityModeNode() throws UaException {
        try {
            return getSecurityModeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSecurityModeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SecurityMode", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getSecurityPolicyUri() throws UaException {
        BaseDataVariableTypeNode node = getSecurityPolicyUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSecurityPolicyUri(String securityPolicyUri) throws UaException {
        BaseDataVariableTypeNode node = getSecurityPolicyUriNode();
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
    public BaseDataVariableTypeNode getSecurityPolicyUriNode() throws UaException {
        try {
            return getSecurityPolicyUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSecurityPolicyUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SecurityPolicyUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ByteString getClientCertificate() throws UaException {
        BaseDataVariableTypeNode node = getClientCertificateNode();
        return (ByteString) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientCertificate(ByteString clientCertificate) throws UaException {
        BaseDataVariableTypeNode node = getClientCertificateNode();
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
    public BaseDataVariableTypeNode getClientCertificateNode() throws UaException {
        try {
            return getClientCertificateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientCertificateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientCertificate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
