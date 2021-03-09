package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditCertificateDataMismatchEventType;
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

public class AuditCertificateDataMismatchEventTypeNode extends AuditCertificateEventTypeNode implements AuditCertificateDataMismatchEventType {
    public AuditCertificateDataMismatchEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                     NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                     LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String getInvalidHostname() throws UaException {
        PropertyTypeNode node = getInvalidHostnameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setInvalidHostname(String invalidHostname) throws UaException {
        PropertyTypeNode node = getInvalidHostnameNode();
        node.setValue(new Variant(invalidHostname));
    }

    @Override
    public String readInvalidHostname() throws UaException {
        try {
            return readInvalidHostnameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInvalidHostname(String invalidHostname) throws UaException {
        try {
            writeInvalidHostnameAsync(invalidHostname).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readInvalidHostnameAsync() {
        return getInvalidHostnameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeInvalidHostnameAsync(String invalidHostname) {
        DataValue value = DataValue.valueOnly(new Variant(invalidHostname));
        return getInvalidHostnameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getInvalidHostnameNode() throws UaException {
        try {
            return getInvalidHostnameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInvalidHostnameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InvalidHostname", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getInvalidUri() throws UaException {
        PropertyTypeNode node = getInvalidUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setInvalidUri(String invalidUri) throws UaException {
        PropertyTypeNode node = getInvalidUriNode();
        node.setValue(new Variant(invalidUri));
    }

    @Override
    public String readInvalidUri() throws UaException {
        try {
            return readInvalidUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInvalidUri(String invalidUri) throws UaException {
        try {
            writeInvalidUriAsync(invalidUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readInvalidUriAsync() {
        return getInvalidUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeInvalidUriAsync(String invalidUri) {
        DataValue value = DataValue.valueOnly(new Variant(invalidUri));
        return getInvalidUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getInvalidUriNode() throws UaException {
        try {
            return getInvalidUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInvalidUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InvalidUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
