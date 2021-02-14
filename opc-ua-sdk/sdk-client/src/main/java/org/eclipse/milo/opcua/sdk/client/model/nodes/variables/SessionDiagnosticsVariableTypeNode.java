package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsVariableType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
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
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceCounterDataType;

public class SessionDiagnosticsVariableTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsVariableType {
    public SessionDiagnosticsVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
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
    public String getSessionName() throws UaException {
        BaseDataVariableTypeNode node = getSessionNameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSessionName(String sessionName) throws UaException {
        BaseDataVariableTypeNode node = getSessionNameNode();
        node.setValue(new Variant(sessionName));
    }

    @Override
    public String readSessionName() throws UaException {
        try {
            return readSessionNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionName(String sessionName) throws UaException {
        try {
            writeSessionNameAsync(sessionName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readSessionNameAsync() {
        return getSessionNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSessionNameAsync(String sessionName) {
        DataValue value = DataValue.valueOnly(new Variant(sessionName));
        return getSessionNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSessionNameNode() throws UaException {
        try {
            return getSessionNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSessionNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ApplicationDescription getClientDescription() throws UaException {
        BaseDataVariableTypeNode node = getClientDescriptionNode();
        return cast(node.getValue().getValue().getValue(), ApplicationDescription.class);
    }

    @Override
    public void setClientDescription(ApplicationDescription clientDescription) throws UaException {
        BaseDataVariableTypeNode node = getClientDescriptionNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), clientDescription);
        node.setValue(new Variant(value));
    }

    @Override
    public ApplicationDescription readClientDescription() throws UaException {
        try {
            return readClientDescriptionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientDescription(ApplicationDescription clientDescription) throws UaException {
        try {
            writeClientDescriptionAsync(clientDescription).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ApplicationDescription> readClientDescriptionAsync() {
        return getClientDescriptionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ApplicationDescription.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeClientDescriptionAsync(
        ApplicationDescription clientDescription) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), clientDescription);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getClientDescriptionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getClientDescriptionNode() throws UaException {
        try {
            return getClientDescriptionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientDescriptionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientDescription", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getServerUri() throws UaException {
        BaseDataVariableTypeNode node = getServerUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerUri(String serverUri) throws UaException {
        BaseDataVariableTypeNode node = getServerUriNode();
        node.setValue(new Variant(serverUri));
    }

    @Override
    public String readServerUri() throws UaException {
        try {
            return readServerUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerUri(String serverUri) throws UaException {
        try {
            writeServerUriAsync(serverUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readServerUriAsync() {
        return getServerUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeServerUriAsync(String serverUri) {
        DataValue value = DataValue.valueOnly(new Variant(serverUri));
        return getServerUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getServerUriNode() throws UaException {
        try {
            return getServerUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getServerUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getEndpointUrl() throws UaException {
        BaseDataVariableTypeNode node = getEndpointUrlNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setEndpointUrl(String endpointUrl) throws UaException {
        BaseDataVariableTypeNode node = getEndpointUrlNode();
        node.setValue(new Variant(endpointUrl));
    }

    @Override
    public String readEndpointUrl() throws UaException {
        try {
            return readEndpointUrlAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEndpointUrl(String endpointUrl) throws UaException {
        try {
            writeEndpointUrlAsync(endpointUrl).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readEndpointUrlAsync() {
        return getEndpointUrlNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEndpointUrlAsync(String endpointUrl) {
        DataValue value = DataValue.valueOnly(new Variant(endpointUrl));
        return getEndpointUrlNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getEndpointUrlNode() throws UaException {
        try {
            return getEndpointUrlNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getEndpointUrlNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EndpointUrl", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String[] getLocaleIds() throws UaException {
        BaseDataVariableTypeNode node = getLocaleIdsNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setLocaleIds(String[] localeIds) throws UaException {
        BaseDataVariableTypeNode node = getLocaleIdsNode();
        node.setValue(new Variant(localeIds));
    }

    @Override
    public String[] readLocaleIds() throws UaException {
        try {
            return readLocaleIdsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLocaleIds(String[] localeIds) throws UaException {
        try {
            writeLocaleIdsAsync(localeIds).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readLocaleIdsAsync() {
        return getLocaleIdsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLocaleIdsAsync(String[] localeIds) {
        DataValue value = DataValue.valueOnly(new Variant(localeIds));
        return getLocaleIdsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getLocaleIdsNode() throws UaException {
        try {
            return getLocaleIdsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getLocaleIdsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LocaleIds", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public Double getActualSessionTimeout() throws UaException {
        BaseDataVariableTypeNode node = getActualSessionTimeoutNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setActualSessionTimeout(Double actualSessionTimeout) throws UaException {
        BaseDataVariableTypeNode node = getActualSessionTimeoutNode();
        node.setValue(new Variant(actualSessionTimeout));
    }

    @Override
    public Double readActualSessionTimeout() throws UaException {
        try {
            return readActualSessionTimeoutAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeActualSessionTimeout(Double actualSessionTimeout) throws UaException {
        try {
            writeActualSessionTimeoutAsync(actualSessionTimeout).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readActualSessionTimeoutAsync() {
        return getActualSessionTimeoutNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeActualSessionTimeoutAsync(Double actualSessionTimeout) {
        DataValue value = DataValue.valueOnly(new Variant(actualSessionTimeout));
        return getActualSessionTimeoutNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getActualSessionTimeoutNode() throws UaException {
        try {
            return getActualSessionTimeoutNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getActualSessionTimeoutNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ActualSessionTimeout", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getMaxResponseMessageSize() throws UaException {
        BaseDataVariableTypeNode node = getMaxResponseMessageSizeNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException {
        BaseDataVariableTypeNode node = getMaxResponseMessageSizeNode();
        node.setValue(new Variant(maxResponseMessageSize));
    }

    @Override
    public UInteger readMaxResponseMessageSize() throws UaException {
        try {
            return readMaxResponseMessageSizeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxResponseMessageSize(UInteger maxResponseMessageSize) throws UaException {
        try {
            writeMaxResponseMessageSizeAsync(maxResponseMessageSize).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxResponseMessageSizeAsync() {
        return getMaxResponseMessageSizeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxResponseMessageSizeAsync(
        UInteger maxResponseMessageSize) {
        DataValue value = DataValue.valueOnly(new Variant(maxResponseMessageSize));
        return getMaxResponseMessageSizeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getMaxResponseMessageSizeNode() throws UaException {
        try {
            return getMaxResponseMessageSizeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getMaxResponseMessageSizeNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxResponseMessageSize", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public DateTime getClientConnectionTime() throws UaException {
        BaseDataVariableTypeNode node = getClientConnectionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientConnectionTime(DateTime clientConnectionTime) throws UaException {
        BaseDataVariableTypeNode node = getClientConnectionTimeNode();
        node.setValue(new Variant(clientConnectionTime));
    }

    @Override
    public DateTime readClientConnectionTime() throws UaException {
        try {
            return readClientConnectionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientConnectionTime(DateTime clientConnectionTime) throws UaException {
        try {
            writeClientConnectionTimeAsync(clientConnectionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readClientConnectionTimeAsync() {
        return getClientConnectionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientConnectionTimeAsync(
        DateTime clientConnectionTime) {
        DataValue value = DataValue.valueOnly(new Variant(clientConnectionTime));
        return getClientConnectionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getClientConnectionTimeNode() throws UaException {
        try {
            return getClientConnectionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientConnectionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientConnectionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public DateTime getClientLastContactTime() throws UaException {
        BaseDataVariableTypeNode node = getClientLastContactTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setClientLastContactTime(DateTime clientLastContactTime) throws UaException {
        BaseDataVariableTypeNode node = getClientLastContactTimeNode();
        node.setValue(new Variant(clientLastContactTime));
    }

    @Override
    public DateTime readClientLastContactTime() throws UaException {
        try {
            return readClientLastContactTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeClientLastContactTime(DateTime clientLastContactTime) throws UaException {
        try {
            writeClientLastContactTimeAsync(clientLastContactTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readClientLastContactTimeAsync() {
        return getClientLastContactTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeClientLastContactTimeAsync(
        DateTime clientLastContactTime) {
        DataValue value = DataValue.valueOnly(new Variant(clientLastContactTime));
        return getClientLastContactTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getClientLastContactTimeNode() throws UaException {
        try {
            return getClientLastContactTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getClientLastContactTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ClientLastContactTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentSubscriptionsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws UaException {
        BaseDataVariableTypeNode node = getCurrentSubscriptionsCountNode();
        node.setValue(new Variant(currentSubscriptionsCount));
    }

    @Override
    public UInteger readCurrentSubscriptionsCount() throws UaException {
        try {
            return readCurrentSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentSubscriptionsCount(UInteger currentSubscriptionsCount) throws
        UaException {
        try {
            writeCurrentSubscriptionsCountAsync(currentSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentSubscriptionsCountAsync() {
        return getCurrentSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentSubscriptionsCountAsync(
        UInteger currentSubscriptionsCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentSubscriptionsCount));
        return getCurrentSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentSubscriptionsCountNode() throws UaException {
        try {
            return getCurrentSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getCurrentMonitoredItemsCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCurrentMonitoredItemsCountNode();
        node.setValue(new Variant(currentMonitoredItemsCount));
    }

    @Override
    public UInteger readCurrentMonitoredItemsCount() throws UaException {
        try {
            return readCurrentMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentMonitoredItemsCount(UInteger currentMonitoredItemsCount) throws
        UaException {
        try {
            writeCurrentMonitoredItemsCountAsync(currentMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentMonitoredItemsCountAsync() {
        return getCurrentMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentMonitoredItemsCountAsync(
        UInteger currentMonitoredItemsCount) {
        DataValue value = DataValue.valueOnly(new Variant(currentMonitoredItemsCount));
        return getCurrentMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentMonitoredItemsCountNode() throws UaException {
        try {
            return getCurrentMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getCurrentPublishRequestsInQueue() throws UaException {
        BaseDataVariableTypeNode node = getCurrentPublishRequestsInQueueNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws
        UaException {
        BaseDataVariableTypeNode node = getCurrentPublishRequestsInQueueNode();
        node.setValue(new Variant(currentPublishRequestsInQueue));
    }

    @Override
    public UInteger readCurrentPublishRequestsInQueue() throws UaException {
        try {
            return readCurrentPublishRequestsInQueueAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentPublishRequestsInQueue(UInteger currentPublishRequestsInQueue) throws
        UaException {
        try {
            writeCurrentPublishRequestsInQueueAsync(currentPublishRequestsInQueue).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readCurrentPublishRequestsInQueueAsync() {
        return getCurrentPublishRequestsInQueueNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCurrentPublishRequestsInQueueAsync(
        UInteger currentPublishRequestsInQueue) {
        DataValue value = DataValue.valueOnly(new Variant(currentPublishRequestsInQueue));
        return getCurrentPublishRequestsInQueueNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCurrentPublishRequestsInQueueNode() throws UaException {
        try {
            return getCurrentPublishRequestsInQueueNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCurrentPublishRequestsInQueueNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentPublishRequestsInQueue", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTotalRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getTotalRequestCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getTotalRequestCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), totalRequestCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readTotalRequestCount() throws UaException {
        try {
            return readTotalRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTotalRequestCount(ServiceCounterDataType totalRequestCount) throws UaException {
        try {
            writeTotalRequestCountAsync(totalRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTotalRequestCountAsync() {
        return getTotalRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeTotalRequestCountAsync(
        ServiceCounterDataType totalRequestCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), totalRequestCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getTotalRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTotalRequestCountNode() throws UaException {
        try {
            return getTotalRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTotalRequestCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TotalRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public UInteger getUnauthorizedRequestCount() throws UaException {
        BaseDataVariableTypeNode node = getUnauthorizedRequestCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException {
        BaseDataVariableTypeNode node = getUnauthorizedRequestCountNode();
        node.setValue(new Variant(unauthorizedRequestCount));
    }

    @Override
    public UInteger readUnauthorizedRequestCount() throws UaException {
        try {
            return readUnauthorizedRequestCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUnauthorizedRequestCount(UInteger unauthorizedRequestCount) throws UaException {
        try {
            writeUnauthorizedRequestCountAsync(unauthorizedRequestCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readUnauthorizedRequestCountAsync() {
        return getUnauthorizedRequestCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeUnauthorizedRequestCountAsync(
        UInteger unauthorizedRequestCount) {
        DataValue value = DataValue.valueOnly(new Variant(unauthorizedRequestCount));
        return getUnauthorizedRequestCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getUnauthorizedRequestCountNode() throws UaException {
        try {
            return getUnauthorizedRequestCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getUnauthorizedRequestCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UnauthorizedRequestCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getReadCount() throws UaException {
        BaseDataVariableTypeNode node = getReadCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setReadCount(ServiceCounterDataType readCount) throws UaException {
        BaseDataVariableTypeNode node = getReadCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), readCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readReadCount() throws UaException {
        try {
            return readReadCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeReadCount(ServiceCounterDataType readCount) throws UaException {
        try {
            writeReadCountAsync(readCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readReadCountAsync() {
        return getReadCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeReadCountAsync(ServiceCounterDataType readCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), readCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getReadCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getReadCountNode() throws UaException {
        try {
            return getReadCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getReadCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReadCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getHistoryReadCount() throws UaException {
        BaseDataVariableTypeNode node = getHistoryReadCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException {
        BaseDataVariableTypeNode node = getHistoryReadCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), historyReadCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readHistoryReadCount() throws UaException {
        try {
            return readHistoryReadCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHistoryReadCount(ServiceCounterDataType historyReadCount) throws UaException {
        try {
            writeHistoryReadCountAsync(historyReadCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readHistoryReadCountAsync() {
        return getHistoryReadCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeHistoryReadCountAsync(
        ServiceCounterDataType historyReadCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), historyReadCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getHistoryReadCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getHistoryReadCountNode() throws UaException {
        try {
            return getHistoryReadCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getHistoryReadCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HistoryReadCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getWriteCount() throws UaException {
        BaseDataVariableTypeNode node = getWriteCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setWriteCount(ServiceCounterDataType writeCount) throws UaException {
        BaseDataVariableTypeNode node = getWriteCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), writeCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readWriteCount() throws UaException {
        try {
            return readWriteCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeWriteCount(ServiceCounterDataType writeCount) throws UaException {
        try {
            writeWriteCountAsync(writeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readWriteCountAsync() {
        return getWriteCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeWriteCountAsync(ServiceCounterDataType writeCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), writeCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getWriteCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getWriteCountNode() throws UaException {
        try {
            return getWriteCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getWriteCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "WriteCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getHistoryUpdateCount() throws UaException {
        BaseDataVariableTypeNode node = getHistoryUpdateCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws UaException {
        BaseDataVariableTypeNode node = getHistoryUpdateCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), historyUpdateCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readHistoryUpdateCount() throws UaException {
        try {
            return readHistoryUpdateCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeHistoryUpdateCount(ServiceCounterDataType historyUpdateCount) throws
        UaException {
        try {
            writeHistoryUpdateCountAsync(historyUpdateCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readHistoryUpdateCountAsync() {
        return getHistoryUpdateCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeHistoryUpdateCountAsync(
        ServiceCounterDataType historyUpdateCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), historyUpdateCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getHistoryUpdateCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getHistoryUpdateCountNode() throws UaException {
        try {
            return getHistoryUpdateCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getHistoryUpdateCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HistoryUpdateCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCallCount() throws UaException {
        BaseDataVariableTypeNode node = getCallCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setCallCount(ServiceCounterDataType callCount) throws UaException {
        BaseDataVariableTypeNode node = getCallCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), callCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readCallCount() throws UaException {
        try {
            return readCallCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCallCount(ServiceCounterDataType callCount) throws UaException {
        try {
            writeCallCountAsync(callCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCallCountAsync() {
        return getCallCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeCallCountAsync(ServiceCounterDataType callCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), callCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getCallCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCallCountNode() throws UaException {
        try {
            return getCallCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCallCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CallCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCreateMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getCreateMonitoredItemsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCreateMonitoredItemsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), createMonitoredItemsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readCreateMonitoredItemsCount() throws UaException {
        try {
            return readCreateMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCreateMonitoredItemsCount(ServiceCounterDataType createMonitoredItemsCount)
        throws UaException {
        try {
            writeCreateMonitoredItemsCountAsync(createMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCreateMonitoredItemsCountAsync() {
        return getCreateMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeCreateMonitoredItemsCountAsync(
        ServiceCounterDataType createMonitoredItemsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), createMonitoredItemsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getCreateMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCreateMonitoredItemsCountNode() throws UaException {
        try {
            return getCreateMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCreateMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CreateMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getModifyMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getModifyMonitoredItemsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getModifyMonitoredItemsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), modifyMonitoredItemsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readModifyMonitoredItemsCount() throws UaException {
        try {
            return readModifyMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeModifyMonitoredItemsCount(ServiceCounterDataType modifyMonitoredItemsCount)
        throws UaException {
        try {
            writeModifyMonitoredItemsCountAsync(modifyMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readModifyMonitoredItemsCountAsync() {
        return getModifyMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeModifyMonitoredItemsCountAsync(
        ServiceCounterDataType modifyMonitoredItemsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), modifyMonitoredItemsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getModifyMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getModifyMonitoredItemsCountNode() throws UaException {
        try {
            return getModifyMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getModifyMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModifyMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetMonitoringModeCount() throws UaException {
        BaseDataVariableTypeNode node = getSetMonitoringModeCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws
        UaException {
        BaseDataVariableTypeNode node = getSetMonitoringModeCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), setMonitoringModeCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readSetMonitoringModeCount() throws UaException {
        try {
            return readSetMonitoringModeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetMonitoringModeCount(ServiceCounterDataType setMonitoringModeCount) throws
        UaException {
        try {
            writeSetMonitoringModeCountAsync(setMonitoringModeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetMonitoringModeCountAsync() {
        return getSetMonitoringModeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSetMonitoringModeCountAsync(
        ServiceCounterDataType setMonitoringModeCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), setMonitoringModeCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSetMonitoringModeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSetMonitoringModeCountNode() throws UaException {
        try {
            return getSetMonitoringModeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetMonitoringModeCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetMonitoringModeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetTriggeringCount() throws UaException {
        BaseDataVariableTypeNode node = getSetTriggeringCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws UaException {
        BaseDataVariableTypeNode node = getSetTriggeringCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), setTriggeringCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readSetTriggeringCount() throws UaException {
        try {
            return readSetTriggeringCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetTriggeringCount(ServiceCounterDataType setTriggeringCount) throws
        UaException {
        try {
            writeSetTriggeringCountAsync(setTriggeringCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetTriggeringCountAsync() {
        return getSetTriggeringCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSetTriggeringCountAsync(
        ServiceCounterDataType setTriggeringCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), setTriggeringCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSetTriggeringCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSetTriggeringCountNode() throws UaException {
        try {
            return getSetTriggeringCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetTriggeringCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetTriggeringCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteMonitoredItemsCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteMonitoredItemsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteMonitoredItemsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), deleteMonitoredItemsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readDeleteMonitoredItemsCount() throws UaException {
        try {
            return readDeleteMonitoredItemsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteMonitoredItemsCount(ServiceCounterDataType deleteMonitoredItemsCount)
        throws UaException {
        try {
            writeDeleteMonitoredItemsCountAsync(deleteMonitoredItemsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteMonitoredItemsCountAsync() {
        return getDeleteMonitoredItemsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeDeleteMonitoredItemsCountAsync(
        ServiceCounterDataType deleteMonitoredItemsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), deleteMonitoredItemsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getDeleteMonitoredItemsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteMonitoredItemsCountNode() throws UaException {
        try {
            return getDeleteMonitoredItemsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteMonitoredItemsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteMonitoredItemsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getCreateSubscriptionCount() throws UaException {
        BaseDataVariableTypeNode node = getCreateSubscriptionCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException {
        BaseDataVariableTypeNode node = getCreateSubscriptionCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), createSubscriptionCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readCreateSubscriptionCount() throws UaException {
        try {
            return readCreateSubscriptionCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCreateSubscriptionCount(ServiceCounterDataType createSubscriptionCount) throws
        UaException {
        try {
            writeCreateSubscriptionCountAsync(createSubscriptionCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readCreateSubscriptionCountAsync() {
        return getCreateSubscriptionCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeCreateSubscriptionCountAsync(
        ServiceCounterDataType createSubscriptionCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), createSubscriptionCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getCreateSubscriptionCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getCreateSubscriptionCountNode() throws UaException {
        try {
            return getCreateSubscriptionCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getCreateSubscriptionCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CreateSubscriptionCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getModifySubscriptionCount() throws UaException {
        BaseDataVariableTypeNode node = getModifySubscriptionCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException {
        BaseDataVariableTypeNode node = getModifySubscriptionCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), modifySubscriptionCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readModifySubscriptionCount() throws UaException {
        try {
            return readModifySubscriptionCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeModifySubscriptionCount(ServiceCounterDataType modifySubscriptionCount) throws
        UaException {
        try {
            writeModifySubscriptionCountAsync(modifySubscriptionCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readModifySubscriptionCountAsync() {
        return getModifySubscriptionCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeModifySubscriptionCountAsync(
        ServiceCounterDataType modifySubscriptionCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), modifySubscriptionCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getModifySubscriptionCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getModifySubscriptionCountNode() throws UaException {
        try {
            return getModifySubscriptionCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getModifySubscriptionCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModifySubscriptionCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getSetPublishingModeCount() throws UaException {
        BaseDataVariableTypeNode node = getSetPublishingModeCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws
        UaException {
        BaseDataVariableTypeNode node = getSetPublishingModeCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), setPublishingModeCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readSetPublishingModeCount() throws UaException {
        try {
            return readSetPublishingModeCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSetPublishingModeCount(ServiceCounterDataType setPublishingModeCount) throws
        UaException {
        try {
            writeSetPublishingModeCountAsync(setPublishingModeCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readSetPublishingModeCountAsync() {
        return getSetPublishingModeCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSetPublishingModeCountAsync(
        ServiceCounterDataType setPublishingModeCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), setPublishingModeCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSetPublishingModeCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSetPublishingModeCountNode() throws UaException {
        try {
            return getSetPublishingModeCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSetPublishingModeCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SetPublishingModeCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getPublishCount() throws UaException {
        BaseDataVariableTypeNode node = getPublishCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setPublishCount(ServiceCounterDataType publishCount) throws UaException {
        BaseDataVariableTypeNode node = getPublishCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), publishCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readPublishCount() throws UaException {
        try {
            return readPublishCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writePublishCount(ServiceCounterDataType publishCount) throws UaException {
        try {
            writePublishCountAsync(publishCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readPublishCountAsync() {
        return getPublishCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writePublishCountAsync(ServiceCounterDataType publishCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), publishCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getPublishCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getPublishCountNode() throws UaException {
        try {
            return getPublishCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getPublishCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "PublishCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getRepublishCount() throws UaException {
        BaseDataVariableTypeNode node = getRepublishCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setRepublishCount(ServiceCounterDataType republishCount) throws UaException {
        BaseDataVariableTypeNode node = getRepublishCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), republishCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readRepublishCount() throws UaException {
        try {
            return readRepublishCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRepublishCount(ServiceCounterDataType republishCount) throws UaException {
        try {
            writeRepublishCountAsync(republishCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readRepublishCountAsync() {
        return getRepublishCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeRepublishCountAsync(
        ServiceCounterDataType republishCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), republishCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getRepublishCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getRepublishCountNode() throws UaException {
        try {
            return getRepublishCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRepublishCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RepublishCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTransferSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getTransferSubscriptionsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount)
        throws UaException {
        BaseDataVariableTypeNode node = getTransferSubscriptionsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), transferSubscriptionsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readTransferSubscriptionsCount() throws UaException {
        try {
            return readTransferSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransferSubscriptionsCount(ServiceCounterDataType transferSubscriptionsCount)
        throws UaException {
        try {
            writeTransferSubscriptionsCountAsync(transferSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTransferSubscriptionsCountAsync() {
        return getTransferSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeTransferSubscriptionsCountAsync(
        ServiceCounterDataType transferSubscriptionsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), transferSubscriptionsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getTransferSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTransferSubscriptionsCountNode() throws UaException {
        try {
            return getTransferSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTransferSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransferSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteSubscriptionsCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteSubscriptionsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteSubscriptionsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), deleteSubscriptionsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readDeleteSubscriptionsCount() throws UaException {
        try {
            return readDeleteSubscriptionsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteSubscriptionsCount(ServiceCounterDataType deleteSubscriptionsCount) throws
        UaException {
        try {
            writeDeleteSubscriptionsCountAsync(deleteSubscriptionsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteSubscriptionsCountAsync() {
        return getDeleteSubscriptionsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeDeleteSubscriptionsCountAsync(
        ServiceCounterDataType deleteSubscriptionsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), deleteSubscriptionsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getDeleteSubscriptionsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteSubscriptionsCountNode() throws UaException {
        try {
            return getDeleteSubscriptionsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteSubscriptionsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteSubscriptionsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getAddNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getAddNodesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getAddNodesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), addNodesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readAddNodesCount() throws UaException {
        try {
            return readAddNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAddNodesCount(ServiceCounterDataType addNodesCount) throws UaException {
        try {
            writeAddNodesCountAsync(addNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readAddNodesCountAsync() {
        return getAddNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeAddNodesCountAsync(
        ServiceCounterDataType addNodesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), addNodesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getAddNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getAddNodesCountNode() throws UaException {
        try {
            return getAddNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getAddNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AddNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getAddReferencesCount() throws UaException {
        BaseDataVariableTypeNode node = getAddReferencesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setAddReferencesCount(ServiceCounterDataType addReferencesCount) throws UaException {
        BaseDataVariableTypeNode node = getAddReferencesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), addReferencesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readAddReferencesCount() throws UaException {
        try {
            return readAddReferencesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAddReferencesCount(ServiceCounterDataType addReferencesCount) throws
        UaException {
        try {
            writeAddReferencesCountAsync(addReferencesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readAddReferencesCountAsync() {
        return getAddReferencesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeAddReferencesCountAsync(
        ServiceCounterDataType addReferencesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), addReferencesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getAddReferencesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getAddReferencesCountNode() throws UaException {
        try {
            return getAddReferencesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getAddReferencesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AddReferencesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteNodesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getDeleteNodesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), deleteNodesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readDeleteNodesCount() throws UaException {
        try {
            return readDeleteNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteNodesCount(ServiceCounterDataType deleteNodesCount) throws UaException {
        try {
            writeDeleteNodesCountAsync(deleteNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteNodesCountAsync() {
        return getDeleteNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeDeleteNodesCountAsync(
        ServiceCounterDataType deleteNodesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), deleteNodesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getDeleteNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteNodesCountNode() throws UaException {
        try {
            return getDeleteNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getDeleteReferencesCount() throws UaException {
        BaseDataVariableTypeNode node = getDeleteReferencesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws
        UaException {
        BaseDataVariableTypeNode node = getDeleteReferencesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), deleteReferencesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readDeleteReferencesCount() throws UaException {
        try {
            return readDeleteReferencesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeleteReferencesCount(ServiceCounterDataType deleteReferencesCount) throws
        UaException {
        try {
            writeDeleteReferencesCountAsync(deleteReferencesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readDeleteReferencesCountAsync() {
        return getDeleteReferencesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeDeleteReferencesCountAsync(
        ServiceCounterDataType deleteReferencesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), deleteReferencesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getDeleteReferencesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getDeleteReferencesCountNode() throws UaException {
        try {
            return getDeleteReferencesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getDeleteReferencesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DeleteReferencesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getBrowseCount() throws UaException {
        BaseDataVariableTypeNode node = getBrowseCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setBrowseCount(ServiceCounterDataType browseCount) throws UaException {
        BaseDataVariableTypeNode node = getBrowseCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), browseCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readBrowseCount() throws UaException {
        try {
            return readBrowseCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBrowseCount(ServiceCounterDataType browseCount) throws UaException {
        try {
            writeBrowseCountAsync(browseCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readBrowseCountAsync() {
        return getBrowseCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeBrowseCountAsync(ServiceCounterDataType browseCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), browseCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getBrowseCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getBrowseCountNode() throws UaException {
        try {
            return getBrowseCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBrowseCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BrowseCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getBrowseNextCount() throws UaException {
        BaseDataVariableTypeNode node = getBrowseNextCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException {
        BaseDataVariableTypeNode node = getBrowseNextCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), browseNextCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readBrowseNextCount() throws UaException {
        try {
            return readBrowseNextCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBrowseNextCount(ServiceCounterDataType browseNextCount) throws UaException {
        try {
            writeBrowseNextCountAsync(browseNextCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readBrowseNextCountAsync() {
        return getBrowseNextCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeBrowseNextCountAsync(
        ServiceCounterDataType browseNextCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), browseNextCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getBrowseNextCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getBrowseNextCountNode() throws UaException {
        try {
            return getBrowseNextCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBrowseNextCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BrowseNextCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getTranslateBrowsePathsToNodeIdsCount() throws UaException {
        BaseDataVariableTypeNode node = getTranslateBrowsePathsToNodeIdsCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException {
        BaseDataVariableTypeNode node = getTranslateBrowsePathsToNodeIdsCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), translateBrowsePathsToNodeIdsCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readTranslateBrowsePathsToNodeIdsCount() throws UaException {
        try {
            return readTranslateBrowsePathsToNodeIdsCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTranslateBrowsePathsToNodeIdsCount(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) throws UaException {
        try {
            writeTranslateBrowsePathsToNodeIdsCountAsync(translateBrowsePathsToNodeIdsCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readTranslateBrowsePathsToNodeIdsCountAsync(
    ) {
        return getTranslateBrowsePathsToNodeIdsCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeTranslateBrowsePathsToNodeIdsCountAsync(
        ServiceCounterDataType translateBrowsePathsToNodeIdsCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), translateBrowsePathsToNodeIdsCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getTranslateBrowsePathsToNodeIdsCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getTranslateBrowsePathsToNodeIdsCountNode() throws UaException {
        try {
            return getTranslateBrowsePathsToNodeIdsCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getTranslateBrowsePathsToNodeIdsCountNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TranslateBrowsePathsToNodeIdsCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getQueryFirstCount() throws UaException {
        BaseDataVariableTypeNode node = getQueryFirstCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException {
        BaseDataVariableTypeNode node = getQueryFirstCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), queryFirstCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readQueryFirstCount() throws UaException {
        try {
            return readQueryFirstCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeQueryFirstCount(ServiceCounterDataType queryFirstCount) throws UaException {
        try {
            writeQueryFirstCountAsync(queryFirstCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readQueryFirstCountAsync() {
        return getQueryFirstCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeQueryFirstCountAsync(
        ServiceCounterDataType queryFirstCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), queryFirstCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getQueryFirstCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getQueryFirstCountNode() throws UaException {
        try {
            return getQueryFirstCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getQueryFirstCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "QueryFirstCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getQueryNextCount() throws UaException {
        BaseDataVariableTypeNode node = getQueryNextCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException {
        BaseDataVariableTypeNode node = getQueryNextCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), queryNextCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readQueryNextCount() throws UaException {
        try {
            return readQueryNextCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeQueryNextCount(ServiceCounterDataType queryNextCount) throws UaException {
        try {
            writeQueryNextCountAsync(queryNextCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readQueryNextCountAsync() {
        return getQueryNextCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeQueryNextCountAsync(
        ServiceCounterDataType queryNextCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), queryNextCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getQueryNextCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getQueryNextCountNode() throws UaException {
        try {
            return getQueryNextCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getQueryNextCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "QueryNextCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getRegisterNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getRegisterNodesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws UaException {
        BaseDataVariableTypeNode node = getRegisterNodesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), registerNodesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readRegisterNodesCount() throws UaException {
        try {
            return readRegisterNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRegisterNodesCount(ServiceCounterDataType registerNodesCount) throws
        UaException {
        try {
            writeRegisterNodesCountAsync(registerNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readRegisterNodesCountAsync() {
        return getRegisterNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeRegisterNodesCountAsync(
        ServiceCounterDataType registerNodesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), registerNodesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getRegisterNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getRegisterNodesCountNode() throws UaException {
        try {
            return getRegisterNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getRegisterNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RegisterNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public ServiceCounterDataType getUnregisterNodesCount() throws UaException {
        BaseDataVariableTypeNode node = getUnregisterNodesCountNode();
        return cast(node.getValue().getValue().getValue(), ServiceCounterDataType.class);
    }

    @Override
    public void setUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws
        UaException {
        BaseDataVariableTypeNode node = getUnregisterNodesCountNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), unregisterNodesCount);
        node.setValue(new Variant(value));
    }

    @Override
    public ServiceCounterDataType readUnregisterNodesCount() throws UaException {
        try {
            return readUnregisterNodesCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUnregisterNodesCount(ServiceCounterDataType unregisterNodesCount) throws
        UaException {
        try {
            writeUnregisterNodesCountAsync(unregisterNodesCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServiceCounterDataType> readUnregisterNodesCountAsync() {
        return getUnregisterNodesCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ServiceCounterDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeUnregisterNodesCountAsync(
        ServiceCounterDataType unregisterNodesCount) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), unregisterNodesCount);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getUnregisterNodesCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getUnregisterNodesCountNode() throws UaException {
        try {
            return getUnregisterNodesCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getUnregisterNodesCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UnregisterNodesCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
