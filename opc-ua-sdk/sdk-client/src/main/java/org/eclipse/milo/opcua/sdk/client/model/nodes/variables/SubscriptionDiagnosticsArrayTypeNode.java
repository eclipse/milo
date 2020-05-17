package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SubscriptionDiagnosticsArrayType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class SubscriptionDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SubscriptionDiagnosticsArrayType {
    public SubscriptionDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId,
                                                NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                LocalizedText description, UInteger writeMask, UInteger userWriteMask, DataValue value,
                                                NodeId dataType, int valueRank, UInteger[] arrayDimensions, UByte accessLevel,
                                                UByte userAccessLevel, double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public SubscriptionDiagnosticsDataType getSubscriptionDiagnostics() throws UaException {
        SubscriptionDiagnosticsTypeNode node = getSubscriptionDiagnosticsNode();
        return (SubscriptionDiagnosticsDataType) node.getValue().getValue().getValue();
    }

    @Override
    public void setSubscriptionDiagnostics(SubscriptionDiagnosticsDataType subscriptionDiagnostics)
        throws UaException {
        SubscriptionDiagnosticsTypeNode node = getSubscriptionDiagnosticsNode();
        node.setValue(new Variant(subscriptionDiagnostics));
    }

    @Override
    public SubscriptionDiagnosticsDataType readSubscriptionDiagnostics() throws UaException {
        try {
            return readSubscriptionDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSubscriptionDiagnostics(SubscriptionDiagnosticsDataType subscriptionDiagnostics)
        throws UaException {
        try {
            writeSubscriptionDiagnosticsAsync(subscriptionDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsDataType> readSubscriptionDiagnosticsAsync(
    ) {
        return getSubscriptionDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (SubscriptionDiagnosticsDataType) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeSubscriptionDiagnosticsAsync(
        SubscriptionDiagnosticsDataType subscriptionDiagnostics) {
        DataValue value = DataValue.valueOnly(new Variant(subscriptionDiagnostics));
        return getSubscriptionDiagnosticsNodeAsync()
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
    public SubscriptionDiagnosticsTypeNode getSubscriptionDiagnosticsNode() throws UaException {
        try {
            return getSubscriptionDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SubscriptionDiagnosticsTypeNode> getSubscriptionDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SubscriptionDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=2172"), false);
        return future.thenApply(node -> (SubscriptionDiagnosticsTypeNode) node);
    }
}
