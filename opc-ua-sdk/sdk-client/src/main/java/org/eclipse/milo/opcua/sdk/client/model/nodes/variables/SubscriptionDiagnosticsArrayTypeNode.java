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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class SubscriptionDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SubscriptionDiagnosticsArrayType {
    public SubscriptionDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId,
                                                NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                LocalizedText description, UInteger writeMask, UInteger userWriteMask, DataValue value,
                                                NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel,
                                                UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public SubscriptionDiagnosticsDataType getSubscriptionDiagnostics() throws UaException {
        SubscriptionDiagnosticsTypeNode node = getSubscriptionDiagnosticsNode();
        return cast(node.getValue().getValue().getValue(), SubscriptionDiagnosticsDataType.class);
    }

    @Override
    public void setSubscriptionDiagnostics(SubscriptionDiagnosticsDataType subscriptionDiagnostics)
        throws UaException {
        SubscriptionDiagnosticsTypeNode node = getSubscriptionDiagnosticsNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), subscriptionDiagnostics);
        node.setValue(new Variant(value));
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
        return getSubscriptionDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SubscriptionDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSubscriptionDiagnosticsAsync(
        SubscriptionDiagnosticsDataType subscriptionDiagnostics) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), subscriptionDiagnostics);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSubscriptionDiagnosticsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
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
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SubscriptionDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SubscriptionDiagnosticsTypeNode) node);
    }
}
