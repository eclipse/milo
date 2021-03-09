package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataItemType;
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

public class DataItemTypeNode extends BaseDataVariableTypeNode implements DataItemType {
    public DataItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                            UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                            Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                            Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public String getDefinition() throws UaException {
        PropertyTypeNode node = getDefinitionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setDefinition(String definition) throws UaException {
        PropertyTypeNode node = getDefinitionNode();
        node.setValue(new Variant(definition));
    }

    @Override
    public String readDefinition() throws UaException {
        try {
            return readDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDefinition(String definition) throws UaException {
        try {
            writeDefinitionAsync(definition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readDefinitionAsync() {
        return getDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDefinitionAsync(String definition) {
        DataValue value = DataValue.valueOnly(new Variant(definition));
        return getDefinitionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getDefinitionNode() throws UaException {
        try {
            return getDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Definition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getValuePrecision() throws UaException {
        PropertyTypeNode node = getValuePrecisionNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setValuePrecision(Double valuePrecision) throws UaException {
        PropertyTypeNode node = getValuePrecisionNode();
        node.setValue(new Variant(valuePrecision));
    }

    @Override
    public Double readValuePrecision() throws UaException {
        try {
            return readValuePrecisionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeValuePrecision(Double valuePrecision) throws UaException {
        try {
            writeValuePrecisionAsync(valuePrecision).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readValuePrecisionAsync() {
        return getValuePrecisionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeValuePrecisionAsync(Double valuePrecision) {
        DataValue value = DataValue.valueOnly(new Variant(valuePrecision));
        return getValuePrecisionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getValuePrecisionNode() throws UaException {
        try {
            return getValuePrecisionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getValuePrecisionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ValuePrecision", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
