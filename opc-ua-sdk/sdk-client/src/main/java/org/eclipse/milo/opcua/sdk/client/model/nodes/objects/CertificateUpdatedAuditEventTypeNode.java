package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.CertificateUpdatedAuditEventType;
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

public class CertificateUpdatedAuditEventTypeNode extends AuditUpdateMethodEventTypeNode implements CertificateUpdatedAuditEventType {
    public CertificateUpdatedAuditEventTypeNode(OpcUaClient client, NodeId nodeId,
                                                NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NodeId getCertificateGroup() throws UaException {
        PropertyTypeNode node = getCertificateGroupNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setCertificateGroup(NodeId certificateGroup) throws UaException {
        PropertyTypeNode node = getCertificateGroupNode();
        node.setValue(new Variant(certificateGroup));
    }

    @Override
    public NodeId readCertificateGroup() throws UaException {
        try {
            return readCertificateGroupAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCertificateGroup(NodeId certificateGroup) throws UaException {
        try {
            writeCertificateGroupAsync(certificateGroup).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readCertificateGroupAsync() {
        return getCertificateGroupNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCertificateGroupAsync(NodeId certificateGroup) {
        DataValue value = DataValue.valueOnly(new Variant(certificateGroup));
        return getCertificateGroupNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getCertificateGroupNode() throws UaException {
        try {
            return getCertificateGroupNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getCertificateGroupNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CertificateGroup", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public NodeId getCertificateType() throws UaException {
        PropertyTypeNode node = getCertificateTypeNode();
        return (NodeId) node.getValue().getValue().getValue();
    }

    @Override
    public void setCertificateType(NodeId certificateType) throws UaException {
        PropertyTypeNode node = getCertificateTypeNode();
        node.setValue(new Variant(certificateType));
    }

    @Override
    public NodeId readCertificateType() throws UaException {
        try {
            return readCertificateTypeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCertificateType(NodeId certificateType) throws UaException {
        try {
            writeCertificateTypeAsync(certificateType).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NodeId> readCertificateTypeAsync() {
        return getCertificateTypeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (NodeId) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeCertificateTypeAsync(NodeId certificateType) {
        DataValue value = DataValue.valueOnly(new Variant(certificateType));
        return getCertificateTypeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getCertificateTypeNode() throws UaException {
        try {
            return getCertificateTypeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getCertificateTypeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CertificateType", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
