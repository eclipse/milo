package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TransitionVariableType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class TransitionVariableTypeNode extends BaseDataVariableTypeNode implements TransitionVariableType {
    public TransitionVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
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
    public DateTime getTransitionTime() throws UaException {
        PropertyTypeNode node = getTransitionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransitionTime(DateTime transitionTime) throws UaException {
        PropertyTypeNode node = getTransitionTimeNode();
        node.setValue(new Variant(transitionTime));
    }

    @Override
    public DateTime readTransitionTime() throws UaException {
        try {
            return readTransitionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransitionTime(DateTime transitionTime) throws UaException {
        try {
            writeTransitionTimeAsync(transitionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readTransitionTimeAsync() {
        return getTransitionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransitionTimeAsync(DateTime transitionTime) {
        DataValue value = DataValue.valueOnly(new Variant(transitionTime));
        return getTransitionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getTransitionTimeNode() throws UaException {
        try {
            return getTransitionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getTransitionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "TransitionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public DateTime getEffectiveTransitionTime() throws UaException {
        PropertyTypeNode node = getEffectiveTransitionTimeNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setEffectiveTransitionTime(DateTime effectiveTransitionTime) throws UaException {
        PropertyTypeNode node = getEffectiveTransitionTimeNode();
        node.setValue(new Variant(effectiveTransitionTime));
    }

    @Override
    public DateTime readEffectiveTransitionTime() throws UaException {
        try {
            return readEffectiveTransitionTimeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEffectiveTransitionTime(DateTime effectiveTransitionTime) throws UaException {
        try {
            writeEffectiveTransitionTimeAsync(effectiveTransitionTime).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readEffectiveTransitionTimeAsync() {
        return getEffectiveTransitionTimeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEffectiveTransitionTimeAsync(
        DateTime effectiveTransitionTime) {
        DataValue value = DataValue.valueOnly(new Variant(effectiveTransitionTime));
        return getEffectiveTransitionTimeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEffectiveTransitionTimeNode() throws UaException {
        try {
            return getEffectiveTransitionTimeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEffectiveTransitionTimeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EffectiveTransitionTime", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
