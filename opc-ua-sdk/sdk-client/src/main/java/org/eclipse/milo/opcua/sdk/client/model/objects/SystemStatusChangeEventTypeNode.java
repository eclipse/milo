package org.eclipse.milo.opcua.sdk.client.model.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.variables.PropertyTypeNode;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class SystemStatusChangeEventTypeNode extends SystemEventTypeNode implements SystemStatusChangeEventType {
    public SystemStatusChangeEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                           QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                           UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                           RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                           UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public ServerState getSystemState() throws UaException {
        PropertyTypeNode node = getSystemStateNode();
        Object value = node.getValue().getValue().getValue();

        if (value instanceof Integer) {
            return ServerState.from((Integer) value);
        } else if (value instanceof ServerState) {
            return (ServerState) value;
        } else {
            return null;
        }
    }

    @Override
    public void setSystemState(ServerState value) throws UaException {
        PropertyTypeNode node = getSystemStateNode();
        node.setValue(new Variant(value));
    }

    @Override
    public ServerState readSystemState() throws UaException {
        try {
            return readSystemStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSystemState(ServerState value) throws UaException {
        try {
            writeSystemStateAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ServerState> readSystemStateAsync() {
        return getSystemStateNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return ServerState.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writeSystemStateAsync(ServerState systemState) {
        DataValue value = DataValue.valueOnly(new Variant(systemState));
        return getSystemStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSystemStateNode() throws UaException {
        try {
            return getSystemStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSystemStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "SystemState",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}