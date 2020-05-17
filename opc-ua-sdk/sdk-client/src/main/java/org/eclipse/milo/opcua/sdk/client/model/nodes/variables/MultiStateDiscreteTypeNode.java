package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.MultiStateDiscreteType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class MultiStateDiscreteTypeNode extends DiscreteItemTypeNode implements MultiStateDiscreteType {
    public MultiStateDiscreteTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                      UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                      double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public LocalizedText[] getEnumStrings() throws UaException {
        PropertyTypeNode node = getEnumStringsNode();
        return (LocalizedText[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnumStrings(LocalizedText[] enumStrings) throws UaException {
        PropertyTypeNode node = getEnumStringsNode();
        node.setValue(new Variant(enumStrings));
    }

    @Override
    public LocalizedText[] readEnumStrings() throws UaException {
        try {
            return readEnumStringsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnumStrings(LocalizedText[] enumStrings) throws UaException {
        try {
            writeEnumStringsAsync(enumStrings).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText[]> readEnumStringsAsync() {
        return getEnumStringsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeEnumStringsAsync(LocalizedText[] enumStrings) {
        DataValue value = DataValue.valueOnly(new Variant(enumStrings));
        return getEnumStringsNodeAsync()
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
    public PropertyTypeNode getEnumStringsNode() throws UaException {
        try {
            return getEnumStringsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEnumStringsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnumStrings", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=68"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
