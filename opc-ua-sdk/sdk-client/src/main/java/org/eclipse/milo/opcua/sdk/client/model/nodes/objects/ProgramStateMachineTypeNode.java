package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteStateVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ProgramDiagnosticTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ProgramStateMachineType;
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
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ProgramStateMachineTypeNode extends FiniteStateMachineTypeNode implements ProgramStateMachineType {
    public ProgramStateMachineTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getCreatable() throws UaException {
        PropertyTypeNode node = getCreatableNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setCreatable(Boolean creatable) throws UaException {
        PropertyTypeNode node = getCreatableNode();
        node.setValue(new Variant(creatable));
    }

    @Override
    public Boolean readCreatable() throws UaException {
        try {
            return readCreatableAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCreatable(Boolean creatable) throws UaException {
        try {
            writeCreatableAsync(creatable).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readCreatableAsync() {
        return getCreatableNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCreatableAsync(Boolean creatable) {
        DataValue value = DataValue.valueOnly(new Variant(creatable));
        return getCreatableNodeAsync()
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
    public PropertyTypeNode getCreatableNode() throws UaException {
        try {
            return getCreatableNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getCreatableNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Creatable", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getDeletable() throws UaException {
        PropertyTypeNode node = getDeletableNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setDeletable(Boolean deletable) throws UaException {
        PropertyTypeNode node = getDeletableNode();
        node.setValue(new Variant(deletable));
    }

    @Override
    public Boolean readDeletable() throws UaException {
        try {
            return readDeletableAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDeletable(Boolean deletable) throws UaException {
        try {
            writeDeletableAsync(deletable).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readDeletableAsync() {
        return getDeletableNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeDeletableAsync(Boolean deletable) {
        DataValue value = DataValue.valueOnly(new Variant(deletable));
        return getDeletableNodeAsync()
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
    public PropertyTypeNode getDeletableNode() throws UaException {
        try {
            return getDeletableNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDeletableNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Deletable", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getAutoDelete() throws UaException {
        PropertyTypeNode node = getAutoDeleteNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setAutoDelete(Boolean autoDelete) throws UaException {
        PropertyTypeNode node = getAutoDeleteNode();
        node.setValue(new Variant(autoDelete));
    }

    @Override
    public Boolean readAutoDelete() throws UaException {
        try {
            return readAutoDeleteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAutoDelete(Boolean autoDelete) throws UaException {
        try {
            writeAutoDeleteAsync(autoDelete).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readAutoDeleteAsync() {
        return getAutoDeleteNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeAutoDeleteAsync(Boolean autoDelete) {
        DataValue value = DataValue.valueOnly(new Variant(autoDelete));
        return getAutoDeleteNodeAsync()
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
    public PropertyTypeNode getAutoDeleteNode() throws UaException {
        try {
            return getAutoDeleteNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAutoDeleteNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AutoDelete", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Integer getRecycleCount() throws UaException {
        PropertyTypeNode node = getRecycleCountNode();
        return (Integer) node.getValue().getValue().getValue();
    }

    @Override
    public void setRecycleCount(Integer recycleCount) throws UaException {
        PropertyTypeNode node = getRecycleCountNode();
        node.setValue(new Variant(recycleCount));
    }

    @Override
    public Integer readRecycleCount() throws UaException {
        try {
            return readRecycleCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeRecycleCount(Integer recycleCount) throws UaException {
        try {
            writeRecycleCountAsync(recycleCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Integer> readRecycleCountAsync() {
        return getRecycleCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Integer) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeRecycleCountAsync(Integer recycleCount) {
        DataValue value = DataValue.valueOnly(new Variant(recycleCount));
        return getRecycleCountNodeAsync()
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
    public PropertyTypeNode getRecycleCountNode() throws UaException {
        try {
            return getRecycleCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getRecycleCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RecycleCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getInstanceCount() throws UaException {
        PropertyTypeNode node = getInstanceCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setInstanceCount(UInteger instanceCount) throws UaException {
        PropertyTypeNode node = getInstanceCountNode();
        node.setValue(new Variant(instanceCount));
    }

    @Override
    public UInteger readInstanceCount() throws UaException {
        try {
            return readInstanceCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInstanceCount(UInteger instanceCount) throws UaException {
        try {
            writeInstanceCountAsync(instanceCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readInstanceCountAsync() {
        return getInstanceCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeInstanceCountAsync(UInteger instanceCount) {
        DataValue value = DataValue.valueOnly(new Variant(instanceCount));
        return getInstanceCountNodeAsync()
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
    public PropertyTypeNode getInstanceCountNode() throws UaException {
        try {
            return getInstanceCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInstanceCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InstanceCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxInstanceCount() throws UaException {
        PropertyTypeNode node = getMaxInstanceCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxInstanceCount(UInteger maxInstanceCount) throws UaException {
        PropertyTypeNode node = getMaxInstanceCountNode();
        node.setValue(new Variant(maxInstanceCount));
    }

    @Override
    public UInteger readMaxInstanceCount() throws UaException {
        try {
            return readMaxInstanceCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxInstanceCount(UInteger maxInstanceCount) throws UaException {
        try {
            writeMaxInstanceCountAsync(maxInstanceCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxInstanceCountAsync() {
        return getMaxInstanceCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxInstanceCountAsync(UInteger maxInstanceCount) {
        DataValue value = DataValue.valueOnly(new Variant(maxInstanceCount));
        return getMaxInstanceCountNodeAsync()
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
    public PropertyTypeNode getMaxInstanceCountNode() throws UaException {
        try {
            return getMaxInstanceCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxInstanceCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxInstanceCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxRecycleCount() throws UaException {
        PropertyTypeNode node = getMaxRecycleCountNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxRecycleCount(UInteger maxRecycleCount) throws UaException {
        PropertyTypeNode node = getMaxRecycleCountNode();
        node.setValue(new Variant(maxRecycleCount));
    }

    @Override
    public UInteger readMaxRecycleCount() throws UaException {
        try {
            return readMaxRecycleCountAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxRecycleCount(UInteger maxRecycleCount) throws UaException {
        try {
            writeMaxRecycleCountAsync(maxRecycleCount).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxRecycleCountAsync() {
        return getMaxRecycleCountNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeMaxRecycleCountAsync(UInteger maxRecycleCount) {
        DataValue value = DataValue.valueOnly(new Variant(maxRecycleCount));
        return getMaxRecycleCountNodeAsync()
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
    public PropertyTypeNode getMaxRecycleCountNode() throws UaException {
        try {
            return getMaxRecycleCountNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxRecycleCountNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxRecycleCount", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getCurrentState() throws UaException {
        FiniteStateVariableTypeNode node = getCurrentStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setCurrentState(LocalizedText currentState) throws UaException {
        FiniteStateVariableTypeNode node = getCurrentStateNode();
        node.setValue(new Variant(currentState));
    }

    @Override
    public LocalizedText readCurrentState() throws UaException {
        try {
            return readCurrentStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeCurrentState(LocalizedText currentState) throws UaException {
        try {
            writeCurrentStateAsync(currentState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readCurrentStateAsync() {
        return getCurrentStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeCurrentStateAsync(LocalizedText currentState) {
        DataValue value = DataValue.valueOnly(new Variant(currentState));
        return getCurrentStateNodeAsync()
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
    public FiniteStateVariableTypeNode getCurrentStateNode() throws UaException {
        try {
            return getCurrentStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends FiniteStateVariableTypeNode> getCurrentStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CurrentState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FiniteStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getLastTransition() throws UaException {
        FiniteTransitionVariableTypeNode node = getLastTransitionNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setLastTransition(LocalizedText lastTransition) throws UaException {
        FiniteTransitionVariableTypeNode node = getLastTransitionNode();
        node.setValue(new Variant(lastTransition));
    }

    @Override
    public LocalizedText readLastTransition() throws UaException {
        try {
            return readLastTransitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLastTransition(LocalizedText lastTransition) throws UaException {
        try {
            writeLastTransitionAsync(lastTransition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readLastTransitionAsync() {
        return getLastTransitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<Unit> writeLastTransitionAsync(LocalizedText lastTransition) {
        DataValue value = DataValue.valueOnly(new Variant(lastTransition));
        return getLastTransitionNodeAsync()
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
    public FiniteTransitionVariableTypeNode getLastTransitionNode() throws UaException {
        try {
            return getLastTransitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends FiniteTransitionVariableTypeNode> getLastTransitionNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LastTransition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FiniteTransitionVariableTypeNode) node);
    }

    @Override
    public ProgramDiagnosticDataType getProgramDiagnostics() throws UaException {
        ProgramDiagnosticTypeNode node = getProgramDiagnosticsNode();
        return cast(node.getValue().getValue().getValue(), ProgramDiagnosticDataType.class);
    }

    @Override
    public void setProgramDiagnostics(ProgramDiagnosticDataType programDiagnostics) throws
        UaException {
        ProgramDiagnosticTypeNode node = getProgramDiagnosticsNode();
        ExtensionObject value = ExtensionObject.encode(client.getSerializationContext(), programDiagnostics);
        node.setValue(new Variant(value));
    }

    @Override
    public ProgramDiagnosticDataType readProgramDiagnostics() throws UaException {
        try {
            return readProgramDiagnosticsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeProgramDiagnostics(ProgramDiagnosticDataType programDiagnostics) throws
        UaException {
        try {
            writeProgramDiagnosticsAsync(programDiagnostics).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ProgramDiagnosticDataType> readProgramDiagnosticsAsync() {
        return getProgramDiagnosticsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), ProgramDiagnosticDataType.class));
    }

    @Override
    public CompletableFuture<Unit> writeProgramDiagnosticsAsync(
        ProgramDiagnosticDataType programDiagnostics) {
        ExtensionObject encoded = ExtensionObject.encode(client.getSerializationContext(), programDiagnostics);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getProgramDiagnosticsNodeAsync()
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
    public ProgramDiagnosticTypeNode getProgramDiagnosticsNode() throws UaException {
        try {
            return getProgramDiagnosticsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends ProgramDiagnosticTypeNode> getProgramDiagnosticsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ProgramDiagnostics", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ProgramDiagnosticTypeNode) node);
    }

    public BaseObjectTypeNode getFinalResultDataNode() throws UaException {
        try {
            return getFinalResultDataNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends BaseObjectTypeNode> getFinalResultDataNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "FinalResultData", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseObjectTypeNode) node);
    }

    public StateTypeNode getReadyNode() throws UaException {
        try {
            return getReadyNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends StateTypeNode> getReadyNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Ready", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateTypeNode) node);
    }

    public StateTypeNode getRunningNode() throws UaException {
        try {
            return getRunningNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends StateTypeNode> getRunningNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Running", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateTypeNode) node);
    }

    public StateTypeNode getSuspendedNode() throws UaException {
        try {
            return getSuspendedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends StateTypeNode> getSuspendedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Suspended", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateTypeNode) node);
    }

    public StateTypeNode getHaltedNode() throws UaException {
        try {
            return getHaltedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends StateTypeNode> getHaltedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Halted", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateTypeNode) node);
    }

    public TransitionTypeNode getHaltedToReadyNode() throws UaException {
        try {
            return getHaltedToReadyNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getHaltedToReadyNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "HaltedToReady", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getReadyToRunningNode() throws UaException {
        try {
            return getReadyToRunningNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getReadyToRunningNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReadyToRunning", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getRunningToHaltedNode() throws UaException {
        try {
            return getRunningToHaltedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getRunningToHaltedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RunningToHalted", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getRunningToReadyNode() throws UaException {
        try {
            return getRunningToReadyNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getRunningToReadyNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RunningToReady", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getRunningToSuspendedNode() throws UaException {
        try {
            return getRunningToSuspendedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getRunningToSuspendedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "RunningToSuspended", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getSuspendedToRunningNode() throws UaException {
        try {
            return getSuspendedToRunningNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getSuspendedToRunningNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SuspendedToRunning", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getSuspendedToHaltedNode() throws UaException {
        try {
            return getSuspendedToHaltedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getSuspendedToHaltedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SuspendedToHalted", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getSuspendedToReadyNode() throws UaException {
        try {
            return getSuspendedToReadyNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getSuspendedToReadyNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SuspendedToReady", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }

    public TransitionTypeNode getReadyToHaltedNode() throws UaException {
        try {
            return getReadyToHaltedNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends TransitionTypeNode> getReadyToHaltedNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ReadyToHalted", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionTypeNode) node);
    }
}
