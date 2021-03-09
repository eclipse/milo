package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FileType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class FileTypeNode extends BaseObjectTypeNode implements FileType {
    public FileTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                        UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public ULong getSize() throws UaException {
        PropertyTypeNode node = getSizeNode();
        return (ULong) node.getValue().getValue().getValue();
    }

    @Override
    public void setSize(ULong size) throws UaException {
        PropertyTypeNode node = getSizeNode();
        node.setValue(new Variant(size));
    }

    @Override
    public ULong readSize() throws UaException {
        try {
            return readSizeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSize(ULong size) throws UaException {
        try {
            writeSizeAsync(size).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ULong> readSizeAsync() {
        return getSizeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (ULong) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSizeAsync(ULong size) {
        DataValue value = DataValue.valueOnly(new Variant(size));
        return getSizeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSizeNode() throws UaException {
        try {
            return getSizeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSizeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Size", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getWritable() throws UaException {
        PropertyTypeNode node = getWritableNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setWritable(Boolean writable) throws UaException {
        PropertyTypeNode node = getWritableNode();
        node.setValue(new Variant(writable));
    }

    @Override
    public Boolean readWritable() throws UaException {
        try {
            return readWritableAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeWritable(Boolean writable) throws UaException {
        try {
            writeWritableAsync(writable).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readWritableAsync() {
        return getWritableNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeWritableAsync(Boolean writable) {
        DataValue value = DataValue.valueOnly(new Variant(writable));
        return getWritableNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getWritableNode() throws UaException {
        try {
            return getWritableNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getWritableNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Writable", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getUserWritable() throws UaException {
        PropertyTypeNode node = getUserWritableNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setUserWritable(Boolean userWritable) throws UaException {
        PropertyTypeNode node = getUserWritableNode();
        node.setValue(new Variant(userWritable));
    }

    @Override
    public Boolean readUserWritable() throws UaException {
        try {
            return readUserWritableAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeUserWritable(Boolean userWritable) throws UaException {
        try {
            writeUserWritableAsync(userWritable).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readUserWritableAsync() {
        return getUserWritableNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeUserWritableAsync(Boolean userWritable) {
        DataValue value = DataValue.valueOnly(new Variant(userWritable));
        return getUserWritableNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getUserWritableNode() throws UaException {
        try {
            return getUserWritableNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getUserWritableNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "UserWritable", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UShort getOpenCount() throws UaException {
        PropertyTypeNode node = getOpenCountNode();
        return (UShort) node.getValue().getValue().getValue();
    }

    @Override
    public void setOpenCount(UShort openCount) throws UaException {
        PropertyTypeNode node = getOpenCountNode();
        node.setValue(new Variant(openCount));
    }

    @Override
    public UShort readOpenCount() throws UaException {
        try {
            return readOpenCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOpenCount(UShort openCount) throws UaException {
        try {
            writeOpenCountAsync(openCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UShort> readOpenCountAsync() {
        return getOpenCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UShort) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOpenCountAsync(UShort openCount) {
        DataValue value = DataValue.valueOnly(new Variant(openCount));
        return getOpenCountNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOpenCountNode() throws UaException {
        try {
            return getOpenCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOpenCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OpenCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getMimeType() throws UaException {
        PropertyTypeNode node = getMimeTypeNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setMimeType(String mimeType) throws UaException {
        PropertyTypeNode node = getMimeTypeNode();
        node.setValue(new Variant(mimeType));
    }

    @Override
    public String readMimeType() throws UaException {
        try {
            return readMimeTypeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMimeType(String mimeType) throws UaException {
        try {
            writeMimeTypeAsync(mimeType).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readMimeTypeAsync() {
        return getMimeTypeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMimeTypeAsync(String mimeType) {
        DataValue value = DataValue.valueOnly(new Variant(mimeType));
        return getMimeTypeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMimeTypeNode() throws UaException {
        try {
            return getMimeTypeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMimeTypeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MimeType", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
