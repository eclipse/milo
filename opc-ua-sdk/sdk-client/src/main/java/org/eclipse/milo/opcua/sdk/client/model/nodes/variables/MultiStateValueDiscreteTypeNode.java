package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateValueDiscreteType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
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
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;

public class MultiStateValueDiscreteTypeNode extends DiscreteItemTypeNode implements MultiStateValueDiscreteType {
    public MultiStateValueDiscreteTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                           UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                           double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public EnumValueType[] getEnumValues() throws UaException {
        PropertyTypeNode node = getEnumValuesNode();
        return cast(node.getValue().getValue().getValue(), EnumValueType[].class);
    }

    @Override
    public void setEnumValues(EnumValueType[] enumValues) throws UaException {
        PropertyTypeNode node = getEnumValuesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), enumValues);
        node.setValue(new Variant(encoded));
    }

    @Override
    public EnumValueType[] readEnumValues() throws UaException {
        try {
            return readEnumValuesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnumValues(EnumValueType[] enumValues) throws UaException {
        try {
            writeEnumValuesAsync(enumValues).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends EnumValueType[]> readEnumValuesAsync() {
        return getEnumValuesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), EnumValueType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeEnumValuesAsync(EnumValueType[] enumValues) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), enumValues);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getEnumValuesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEnumValuesNode() throws UaException {
        try {
            return getEnumValuesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEnumValuesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnumValues", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getValueAsText() throws UaException {
        PropertyTypeNode node = getValueAsTextNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setValueAsText(LocalizedText valueAsText) throws UaException {
        PropertyTypeNode node = getValueAsTextNode();
        node.setValue(new Variant(valueAsText));
    }

    @Override
    public LocalizedText readValueAsText() throws UaException {
        try {
            return readValueAsTextAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeValueAsText(LocalizedText valueAsText) throws UaException {
        try {
            writeValueAsTextAsync(valueAsText).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readValueAsTextAsync() {
        return getValueAsTextNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeValueAsTextAsync(LocalizedText valueAsText) {
        DataValue value = DataValue.valueOnly(new Variant(valueAsText));
        return getValueAsTextNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getValueAsTextNode() throws UaException {
        try {
            return getValueAsTextNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getValueAsTextNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ValueAsText", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
