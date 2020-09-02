package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.SemanticChangeEventType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.SemanticChangeStructureDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class SemanticChangeEventTypeNode extends BaseModelChangeEventTypeNode implements SemanticChangeEventType {
    public SemanticChangeEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SemanticChangeStructureDataType[] getChanges() throws UaException {
        PropertyTypeNode node = getChangesNode();
        return cast(node.getValue().getValue().getValue(), SemanticChangeStructureDataType[].class);
    }

    @Override
    public void setChanges(SemanticChangeStructureDataType[] changes) throws UaException {
        PropertyTypeNode node = getChangesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), changes);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SemanticChangeStructureDataType[] readChanges() throws UaException {
        try {
            return readChangesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeChanges(SemanticChangeStructureDataType[] changes) throws UaException {
        try {
            writeChangesAsync(changes).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SemanticChangeStructureDataType[]> readChangesAsync() {
        return getChangesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SemanticChangeStructureDataType[].class));
    }

    @Override
    public CompletableFuture<Unit> writeChangesAsync(SemanticChangeStructureDataType[] changes) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getSerializationContext(), changes);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getChangesNodeAsync()
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
    public PropertyTypeNode getChangesNode() throws UaException {
        try {
            return getChangesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getChangesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Changes", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
