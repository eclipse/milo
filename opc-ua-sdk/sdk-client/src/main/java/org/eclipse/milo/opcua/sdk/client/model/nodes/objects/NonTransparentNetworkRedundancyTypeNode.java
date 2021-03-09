package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.NonTransparentNetworkRedundancyType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkGroupDataType;

public class NonTransparentNetworkRedundancyTypeNode extends NonTransparentRedundancyTypeNode implements NonTransparentNetworkRedundancyType {
    public NonTransparentNetworkRedundancyTypeNode(OpcUaClient client, NodeId nodeId,
                                                   NodeClass nodeClass, QualifiedName browseName, LocalizedText displayName,
                                                   LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public NetworkGroupDataType[] getServerNetworkGroups() throws UaException {
        PropertyTypeNode node = getServerNetworkGroupsNode();
        return cast(node.getValue().getValue().getValue(), NetworkGroupDataType[].class);
    }

    @Override
    public void setServerNetworkGroups(NetworkGroupDataType[] serverNetworkGroups) throws
        UaException {
        PropertyTypeNode node = getServerNetworkGroupsNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), serverNetworkGroups);
        node.setValue(new Variant(encoded));
    }

    @Override
    public NetworkGroupDataType[] readServerNetworkGroups() throws UaException {
        try {
            return readServerNetworkGroupsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerNetworkGroups(NetworkGroupDataType[] serverNetworkGroups) throws
        UaException {
        try {
            writeServerNetworkGroupsAsync(serverNetworkGroups).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends NetworkGroupDataType[]> readServerNetworkGroupsAsync() {
        return getServerNetworkGroupsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), NetworkGroupDataType[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeServerNetworkGroupsAsync(
        NetworkGroupDataType[] serverNetworkGroups) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), serverNetworkGroups);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getServerNetworkGroupsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getServerNetworkGroupsNode() throws UaException {
        try {
            return getServerNetworkGroupsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getServerNetworkGroupsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerNetworkGroups", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
