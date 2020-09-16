package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ConditionVariableType;
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

public class ConditionVariableTypeNode extends BaseDataVariableTypeNode implements ConditionVariableType {
    public ConditionVariableTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                     QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                     UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                     Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                     Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public DateTime getSourceTimestamp() throws UaException {
        PropertyTypeNode node = getSourceTimestampNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setSourceTimestamp(DateTime sourceTimestamp) throws UaException {
        PropertyTypeNode node = getSourceTimestampNode();
        node.setValue(new Variant(sourceTimestamp));
    }

    @Override
    public DateTime readSourceTimestamp() throws UaException {
        try {
            return readSourceTimestampAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSourceTimestamp(DateTime sourceTimestamp) throws UaException {
        try {
            writeSourceTimestampAsync(sourceTimestamp).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readSourceTimestampAsync() {
        return getSourceTimestampNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSourceTimestampAsync(DateTime sourceTimestamp) {
        DataValue value = DataValue.valueOnly(new Variant(sourceTimestamp));
        return getSourceTimestampNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSourceTimestampNode() throws UaException {
        try {
            return getSourceTimestampNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSourceTimestampNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SourceTimestamp", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
