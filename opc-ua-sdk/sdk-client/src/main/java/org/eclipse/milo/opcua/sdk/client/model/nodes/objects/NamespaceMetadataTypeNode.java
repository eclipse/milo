package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NamespaceMetadataType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class NamespaceMetadataTypeNode extends BaseObjectTypeNode implements NamespaceMetadataType {
    public NamespaceMetadataTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                     QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                     UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String getNamespaceUri() throws UaException {
        PropertyTypeNode node = getNamespaceUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setNamespaceUri(String namespaceUri) throws UaException {
        PropertyTypeNode node = getNamespaceUriNode();
        node.setValue(new Variant(namespaceUri));
    }

    @Override
    public String readNamespaceUri() throws UaException {
        try {
            return readNamespaceUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNamespaceUri(String namespaceUri) throws UaException {
        try {
            writeNamespaceUriAsync(namespaceUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readNamespaceUriAsync() {
        return getNamespaceUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeNamespaceUriAsync(String namespaceUri) {
        DataValue value = DataValue.valueOnly(new Variant(namespaceUri));
        return getNamespaceUriNodeAsync()
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
    public PropertyTypeNode getNamespaceUriNode() throws UaException {
        try {
            return getNamespaceUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNamespaceUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespaceUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getNamespaceVersion() throws UaException {
        PropertyTypeNode node = getNamespaceVersionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setNamespaceVersion(String namespaceVersion) throws UaException {
        PropertyTypeNode node = getNamespaceVersionNode();
        node.setValue(new Variant(namespaceVersion));
    }

    @Override
    public String readNamespaceVersion() throws UaException {
        try {
            return readNamespaceVersionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNamespaceVersion(String namespaceVersion) throws UaException {
        try {
            writeNamespaceVersionAsync(namespaceVersion).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readNamespaceVersionAsync() {
        return getNamespaceVersionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeNamespaceVersionAsync(String namespaceVersion) {
        DataValue value = DataValue.valueOnly(new Variant(namespaceVersion));
        return getNamespaceVersionNodeAsync()
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
    public PropertyTypeNode getNamespaceVersionNode() throws UaException {
        try {
            return getNamespaceVersionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNamespaceVersionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespaceVersion", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getNamespacePublicationDate() throws UaException {
        PropertyTypeNode node = getNamespacePublicationDateNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setNamespacePublicationDate(DateTime namespacePublicationDate) throws UaException {
        PropertyTypeNode node = getNamespacePublicationDateNode();
        node.setValue(new Variant(namespacePublicationDate));
    }

    @Override
    public DateTime readNamespacePublicationDate() throws UaException {
        try {
            return readNamespacePublicationDateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNamespacePublicationDate(DateTime namespacePublicationDate) throws UaException {
        try {
            writeNamespacePublicationDateAsync(namespacePublicationDate).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readNamespacePublicationDateAsync() {
        return getNamespacePublicationDateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeNamespacePublicationDateAsync(
        DateTime namespacePublicationDate) {
        DataValue value = DataValue.valueOnly(new Variant(namespacePublicationDate));
        return getNamespacePublicationDateNodeAsync()
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
    public PropertyTypeNode getNamespacePublicationDateNode() throws UaException {
        try {
            return getNamespacePublicationDateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNamespacePublicationDateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespacePublicationDate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getIsNamespaceSubset() throws UaException {
        PropertyTypeNode node = getIsNamespaceSubsetNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setIsNamespaceSubset(Boolean isNamespaceSubset) throws UaException {
        PropertyTypeNode node = getIsNamespaceSubsetNode();
        node.setValue(new Variant(isNamespaceSubset));
    }

    @Override
    public Boolean readIsNamespaceSubset() throws UaException {
        try {
            return readIsNamespaceSubsetAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeIsNamespaceSubset(Boolean isNamespaceSubset) throws UaException {
        try {
            writeIsNamespaceSubsetAsync(isNamespaceSubset).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readIsNamespaceSubsetAsync() {
        return getIsNamespaceSubsetNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeIsNamespaceSubsetAsync(Boolean isNamespaceSubset) {
        DataValue value = DataValue.valueOnly(new Variant(isNamespaceSubset));
        return getIsNamespaceSubsetNodeAsync()
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
    public PropertyTypeNode getIsNamespaceSubsetNode() throws UaException {
        try {
            return getIsNamespaceSubsetNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getIsNamespaceSubsetNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "IsNamespaceSubset", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public IdType[] getStaticNodeIdTypes() throws UaException {
        PropertyTypeNode node = getStaticNodeIdTypesNode();
        return (IdType[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setStaticNodeIdTypes(IdType[] staticNodeIdTypes) throws UaException {
        PropertyTypeNode node = getStaticNodeIdTypesNode();
        node.setValue(new Variant(staticNodeIdTypes));
    }

    @Override
    public IdType[] readStaticNodeIdTypes() throws UaException {
        try {
            return readStaticNodeIdTypesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStaticNodeIdTypes(IdType[] staticNodeIdTypes) throws UaException {
        try {
            writeStaticNodeIdTypesAsync(staticNodeIdTypes).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends IdType[]> readStaticNodeIdTypesAsync() {
        return getStaticNodeIdTypesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (IdType[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeStaticNodeIdTypesAsync(IdType[] staticNodeIdTypes) {
        DataValue value = DataValue.valueOnly(new Variant(staticNodeIdTypes));
        return getStaticNodeIdTypesNodeAsync()
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
    public PropertyTypeNode getStaticNodeIdTypesNode() throws UaException {
        try {
            return getStaticNodeIdTypesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getStaticNodeIdTypesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "StaticNodeIdTypes", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String[] getStaticNumericNodeIdRange() throws UaException {
        PropertyTypeNode node = getStaticNumericNodeIdRangeNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setStaticNumericNodeIdRange(String[] staticNumericNodeIdRange) throws UaException {
        PropertyTypeNode node = getStaticNumericNodeIdRangeNode();
        node.setValue(new Variant(staticNumericNodeIdRange));
    }

    @Override
    public String[] readStaticNumericNodeIdRange() throws UaException {
        try {
            return readStaticNumericNodeIdRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStaticNumericNodeIdRange(String[] staticNumericNodeIdRange) throws UaException {
        try {
            writeStaticNumericNodeIdRangeAsync(staticNumericNodeIdRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readStaticNumericNodeIdRangeAsync() {
        return getStaticNumericNodeIdRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeStaticNumericNodeIdRangeAsync(
        String[] staticNumericNodeIdRange) {
        DataValue value = DataValue.valueOnly(new Variant(staticNumericNodeIdRange));
        return getStaticNumericNodeIdRangeNodeAsync()
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
    public PropertyTypeNode getStaticNumericNodeIdRangeNode() throws UaException {
        try {
            return getStaticNumericNodeIdRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getStaticNumericNodeIdRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "StaticNumericNodeIdRange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getStaticStringNodeIdPattern() throws UaException {
        PropertyTypeNode node = getStaticStringNodeIdPatternNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setStaticStringNodeIdPattern(String staticStringNodeIdPattern) throws UaException {
        PropertyTypeNode node = getStaticStringNodeIdPatternNode();
        node.setValue(new Variant(staticStringNodeIdPattern));
    }

    @Override
    public String readStaticStringNodeIdPattern() throws UaException {
        try {
            return readStaticStringNodeIdPatternAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeStaticStringNodeIdPattern(String staticStringNodeIdPattern) throws UaException {
        try {
            writeStaticStringNodeIdPatternAsync(staticStringNodeIdPattern).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readStaticStringNodeIdPatternAsync() {
        return getStaticStringNodeIdPatternNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeStaticStringNodeIdPatternAsync(
        String staticStringNodeIdPattern) {
        DataValue value = DataValue.valueOnly(new Variant(staticStringNodeIdPattern));
        return getStaticStringNodeIdPatternNodeAsync()
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
    public PropertyTypeNode getStaticStringNodeIdPatternNode() throws UaException {
        try {
            return getStaticStringNodeIdPatternNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getStaticStringNodeIdPatternNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "StaticStringNodeIdPattern", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    public AddressSpaceFileTypeNode getNamespaceFileNode() throws UaException {
        try {
            return getNamespaceFileNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends AddressSpaceFileTypeNode> getNamespaceFileNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespaceFile", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (AddressSpaceFileTypeNode) node);
    }
}
