package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionSecurityDiagnosticsArrayType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionSecurityDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SessionSecurityDiagnosticsArrayType {
    public SessionSecurityDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId,
                                                   NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                   LocalizedText description, UInteger writeMask, UInteger userWriteMask, DataValue value,
                                                   NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel,
                                                   UByte userAccessLevel, Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() throws UaException {
        SessionSecurityDiagnosticsTypeNode node = getSessionSecurityDiagnosticsNode();
        return cast(node.getValue().getValue().getValue(), SessionSecurityDiagnosticsDataType.class);
    }

    @Override
    public void setSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) throws UaException {
        SessionSecurityDiagnosticsTypeNode node = getSessionSecurityDiagnosticsNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), sessionSecurityDiagnostics);
        node.setValue(new Variant(value));
    }

    @Override
    public SessionSecurityDiagnosticsDataType readSessionSecurityDiagnostics() throws UaException {
        try {
            return readSessionSecurityDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionSecurityDiagnostics(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) throws UaException {
        try {
            writeSessionSecurityDiagnosticsAsync(sessionSecurityDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsDataType> readSessionSecurityDiagnosticsAsync(
    ) {
        return getSessionSecurityDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SessionSecurityDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSessionSecurityDiagnosticsAsync(
        SessionSecurityDiagnosticsDataType sessionSecurityDiagnostics) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), sessionSecurityDiagnostics);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSessionSecurityDiagnosticsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public SessionSecurityDiagnosticsTypeNode getSessionSecurityDiagnosticsNode() throws UaException {
        try {
            return getSessionSecurityDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionSecurityDiagnosticsTypeNode> getSessionSecurityDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SessionSecurityDiagnosticsTypeNode) node);
    }
}
