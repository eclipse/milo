package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.StateVariableType;
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

public class StateVariableTypeNode extends BaseDataVariableTypeNode implements StateVariableType {
    public StateVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                 QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                 UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                 Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                 Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public Object getId() throws UaException {
        PropertyTypeNode node = getIdNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setId(Object id) throws UaException {
        PropertyTypeNode node = getIdNode();
        node.setValue(new Variant(id));
    }

    @Override
    public Object readId() throws UaException {
        try {
            return readIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeId(Object id) throws UaException {
        try {
            writeIdAsync(id).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readIdAsync() {
        return getIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeIdAsync(Object id) {
        DataValue value = DataValue.valueOnly(new Variant(id));
        return getIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getIdNode() throws UaException {
        try {
            return getIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Id", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public QualifiedName getName() throws UaException {
        PropertyTypeNode node = getNameNode();
        return (QualifiedName) node.getValue().getValue().getValue();
    }

    @Override
    public void setName(QualifiedName name) throws UaException {
        PropertyTypeNode node = getNameNode();
        node.setValue(new Variant(name));
    }

    @Override
    public QualifiedName readName() throws UaException {
        try {
            return readNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeName(QualifiedName name) throws UaException {
        try {
            writeNameAsync(name).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends QualifiedName> readNameAsync() {
        return getNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (QualifiedName) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNameAsync(QualifiedName name) {
        DataValue value = DataValue.valueOnly(new Variant(name));
        return getNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNameNode() throws UaException {
        try {
            return getNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Name", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getNumber() throws UaException {
        PropertyTypeNode node = getNumberNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setNumber(UInteger number) throws UaException {
        PropertyTypeNode node = getNumberNode();
        node.setValue(new Variant(number));
    }

    @Override
    public UInteger readNumber() throws UaException {
        try {
            return readNumberAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNumber(UInteger number) throws UaException {
        try {
            writeNumberAsync(number).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readNumberAsync() {
        return getNumberNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNumberAsync(UInteger number) {
        DataValue value = DataValue.valueOnly(new Variant(number));
        return getNumberNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNumberNode() throws UaException {
        try {
            return getNumberNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNumberNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Number", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getEffectiveDisplayName() throws UaException {
        PropertyTypeNode node = getEffectiveDisplayNameNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setEffectiveDisplayName(LocalizedText effectiveDisplayName) throws UaException {
        PropertyTypeNode node = getEffectiveDisplayNameNode();
        node.setValue(new Variant(effectiveDisplayName));
    }

    @Override
    public LocalizedText readEffectiveDisplayName() throws UaException {
        try {
            return readEffectiveDisplayNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEffectiveDisplayName(LocalizedText effectiveDisplayName) throws UaException {
        try {
            writeEffectiveDisplayNameAsync(effectiveDisplayName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readEffectiveDisplayNameAsync() {
        return getEffectiveDisplayNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEffectiveDisplayNameAsync(
        LocalizedText effectiveDisplayName) {
        DataValue value = DataValue.valueOnly(new Variant(effectiveDisplayName));
        return getEffectiveDisplayNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEffectiveDisplayNameNode() throws UaException {
        try {
            return getEffectiveDisplayNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEffectiveDisplayNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EffectiveDisplayName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
