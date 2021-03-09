package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.NDimensionArrayItemType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public class NDimensionArrayItemTypeNode extends ArrayItemTypeNode implements NDimensionArrayItemType {
    public NDimensionArrayItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                       Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                       Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public AxisInformation[] getAxisDefinition() throws UaException {
        PropertyTypeNode node = getAxisDefinitionNode();
        return cast(node.getValue().getValue().getValue(), AxisInformation[].class);
    }

    @Override
    public void setAxisDefinition(AxisInformation[] axisDefinition) throws UaException {
        PropertyTypeNode node = getAxisDefinitionNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), axisDefinition);
        node.setValue(new Variant(encoded));
    }

    @Override
    public AxisInformation[] readAxisDefinition() throws UaException {
        try {
            return readAxisDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAxisDefinition(AxisInformation[] axisDefinition) throws UaException {
        try {
            writeAxisDefinitionAsync(axisDefinition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisInformation[]> readAxisDefinitionAsync() {
        return getAxisDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), AxisInformation[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeAxisDefinitionAsync(AxisInformation[] axisDefinition) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), axisDefinition);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getAxisDefinitionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getAxisDefinitionNode() throws UaException {
        try {
            return getAxisDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAxisDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AxisDefinition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
