package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.OptionSetType;
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

public class OptionSetTypeNode extends BaseDataVariableTypeNode implements OptionSetType {
    public OptionSetTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                             UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                             Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                             Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public LocalizedText[] getOptionSetValues() throws UaException {
        PropertyTypeNode node = getOptionSetValuesNode();
        return (LocalizedText[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setOptionSetValues(LocalizedText[] optionSetValues) throws UaException {
        PropertyTypeNode node = getOptionSetValuesNode();
        node.setValue(new Variant(optionSetValues));
    }

    @Override
    public LocalizedText[] readOptionSetValues() throws UaException {
        try {
            return readOptionSetValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOptionSetValues(LocalizedText[] optionSetValues) throws UaException {
        try {
            writeOptionSetValuesAsync(optionSetValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText[]> readOptionSetValuesAsync() {
        return getOptionSetValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOptionSetValuesAsync(LocalizedText[] optionSetValues) {
        DataValue value = DataValue.valueOnly(new Variant(optionSetValues));
        return getOptionSetValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOptionSetValuesNode() throws UaException {
        try {
            return getOptionSetValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOptionSetValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OptionSetValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean[] getBitMask() throws UaException {
        PropertyTypeNode node = getBitMaskNode();
        return (Boolean[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setBitMask(Boolean[] bitMask) throws UaException {
        PropertyTypeNode node = getBitMaskNode();
        node.setValue(new Variant(bitMask));
    }

    @Override
    public Boolean[] readBitMask() throws UaException {
        try {
            return readBitMaskAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBitMask(Boolean[] bitMask) throws UaException {
        try {
            writeBitMaskAsync(bitMask).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean[]> readBitMaskAsync() {
        return getBitMaskNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeBitMaskAsync(Boolean[] bitMask) {
        DataValue value = DataValue.valueOnly(new Variant(bitMask));
        return getBitMaskNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getBitMaskNode() throws UaException {
        try {
            return getBitMaskNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getBitMaskNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BitMask", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
