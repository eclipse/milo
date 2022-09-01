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
import org.eclipse.milo.opcua.stack.core.types.structured.AccessRestrictionType;
import org.eclipse.milo.opcua.stack.core.types.structured.RolePermissionType;

public class ExtensionFieldsTypeNode extends BaseObjectTypeNode implements ExtensionFieldsType {
    public ExtensionFieldsTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                   QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                   UInteger writeMask, UInteger userWriteMask, RolePermissionType[] rolePermissions,
                                   RolePermissionType[] userRolePermissions, AccessRestrictionType accessRestrictions,
                                   UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, rolePermissions, userRolePermissions, accessRestrictions, eventNotifier);
    }

    @Override
    public Object getExtensionFieldNamePlaceholder() throws UaException {
        PropertyTypeNode node = getExtensionFieldNamePlaceholderNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setExtensionFieldNamePlaceholder(Object value) throws UaException {
        PropertyTypeNode node = getExtensionFieldNamePlaceholderNode();
        node.setValue(new Variant(value));
    }

    @Override
    public Object readExtensionFieldNamePlaceholder() throws UaException {
        try {
            return readExtensionFieldNamePlaceholderAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeExtensionFieldNamePlaceholder(Object value) throws UaException {
        try {
            writeExtensionFieldNamePlaceholderAsync(value).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readExtensionFieldNamePlaceholderAsync() {
        return getExtensionFieldNamePlaceholderNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeExtensionFieldNamePlaceholderAsync(
        Object extensionFieldNamePlaceholder) {
        DataValue value = DataValue.valueOnly(new Variant(extensionFieldNamePlaceholder));
        return getExtensionFieldNamePlaceholderNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getExtensionFieldNamePlaceholderNode() throws UaException {
        try {
            return getExtensionFieldNamePlaceholderNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getExtensionFieldNamePlaceholderNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync(
            "http://opcfoundation.org/UA/",
            "ExtensionFieldName_Placeholder",
            ExpandedNodeId.parse("ns=0;i=46"),
            false
        );
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}