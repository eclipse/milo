package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.SessionDiagnosticsArrayType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;

public class SessionDiagnosticsArrayTypeNode extends BaseDataVariableTypeNode implements SessionDiagnosticsArrayType {
    public SessionDiagnosticsArrayTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                           UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                           double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public SessionDiagnosticsDataType getSessionDiagnostics() throws UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        return cast(node.getValue().getValue().getValue(), SessionDiagnosticsDataType.class);
    }

    @Override
    public void setSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws
        UaException {
        SessionDiagnosticsVariableTypeNode node = getSessionDiagnosticsNode();
        ExtensionObject value = ExtensionObject.encode(client.getSerializationContext(), sessionDiagnostics);
        node.setValue(new Variant(value));
    }

    @Override
    public SessionDiagnosticsDataType readSessionDiagnostics() throws UaException {
        try {
            return readSessionDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSessionDiagnostics(SessionDiagnosticsDataType sessionDiagnostics) throws
        UaException {
        try {
            writeSessionDiagnosticsAsync(sessionDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsDataType> readSessionDiagnosticsAsync() {
        return getSessionDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SessionDiagnosticsDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSessionDiagnosticsAsync(
        SessionDiagnosticsDataType sessionDiagnostics) {
        ExtensionObject encoded = ExtensionObject.encode(client.getSerializationContext(), sessionDiagnostics);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSessionDiagnosticsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public SessionDiagnosticsVariableTypeNode getSessionDiagnosticsNode() throws UaException {
        try {
            return getSessionDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SessionDiagnosticsVariableTypeNode> getSessionDiagnosticsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SessionDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (SessionDiagnosticsVariableTypeNode) node);
    }
}
