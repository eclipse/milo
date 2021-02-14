package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ArrayItemType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public class ArrayItemTypeNode extends DataItemTypeNode implements ArrayItemType {
    public ArrayItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                             UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                             Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                             Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public Range getInstrumentRange() throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        return cast(node.getValue().getValue().getValue(), Range.class);
    }

    @Override
    public void setInstrumentRange(Range instrumentRange) throws UaException {
        PropertyTypeNode node = getInstrumentRangeNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), instrumentRange);
        node.setValue(new Variant(value));
    }

    @Override
    public Range readInstrumentRange() throws UaException {
        try {
            return readInstrumentRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeInstrumentRange(Range instrumentRange) throws UaException {
        try {
            writeInstrumentRangeAsync(instrumentRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readInstrumentRangeAsync() {
        return getInstrumentRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), Range.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeInstrumentRangeAsync(Range instrumentRange) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), instrumentRange);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getInstrumentRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getInstrumentRangeNode() throws UaException {
        try {
            return getInstrumentRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getInstrumentRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "InstrumentRange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Range getEuRange() throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        return cast(node.getValue().getValue().getValue(), Range.class);
    }

    @Override
    public void setEuRange(Range euRange) throws UaException {
        PropertyTypeNode node = getEuRangeNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), euRange);
        node.setValue(new Variant(value));
    }

    @Override
    public Range readEuRange() throws UaException {
        try {
            return readEuRangeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEuRange(Range euRange) throws UaException {
        try {
            writeEuRangeAsync(euRange).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Range> readEuRangeAsync() {
        return getEuRangeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), Range.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeEuRangeAsync(Range euRange) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), euRange);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getEuRangeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEuRangeNode() throws UaException {
        try {
            return getEuRangeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEuRangeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EURange", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public EUInformation getEngineeringUnits() throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        return cast(node.getValue().getValue().getValue(), EUInformation.class);
    }

    @Override
    public void setEngineeringUnits(EUInformation engineeringUnits) throws UaException {
        PropertyTypeNode node = getEngineeringUnitsNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), engineeringUnits);
        node.setValue(new Variant(value));
    }

    @Override
    public EUInformation readEngineeringUnits() throws UaException {
        try {
            return readEngineeringUnitsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEngineeringUnits(EUInformation engineeringUnits) throws UaException {
        try {
            writeEngineeringUnitsAsync(engineeringUnits).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends EUInformation> readEngineeringUnitsAsync() {
        return getEngineeringUnitsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), EUInformation.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeEngineeringUnitsAsync(EUInformation engineeringUnits) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), engineeringUnits);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getEngineeringUnitsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getEngineeringUnitsNode() throws UaException {
        try {
            return getEngineeringUnitsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getEngineeringUnitsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EngineeringUnits", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public LocalizedText getTitle() throws UaException {
        PropertyTypeNode node = getTitleNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setTitle(LocalizedText title) throws UaException {
        PropertyTypeNode node = getTitleNode();
        node.setValue(new Variant(title));
    }

    @Override
    public LocalizedText readTitle() throws UaException {
        try {
            return readTitleAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTitle(LocalizedText title) throws UaException {
        try {
            writeTitleAsync(title).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readTitleAsync() {
        return getTitleNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTitleAsync(LocalizedText title) {
        DataValue value = DataValue.valueOnly(new Variant(title));
        return getTitleNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getTitleNode() throws UaException {
        try {
            return getTitleNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getTitleNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Title", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public AxisScaleEnumeration getAxisScaleType() throws UaException {
        PropertyTypeNode node = getAxisScaleTypeNode();
        Object value = node.getValue().getValue().getValue();
        if (value instanceof Integer) {
            return AxisScaleEnumeration.from((Integer) value);
        } else if (value instanceof AxisScaleEnumeration) {
            return (AxisScaleEnumeration) value;
        } else {
            return null;
        }
    }

    @Override
    public void setAxisScaleType(AxisScaleEnumeration axisScaleType) throws UaException {
        PropertyTypeNode node = getAxisScaleTypeNode();
        node.setValue(new Variant(axisScaleType));
    }

    @Override
    public AxisScaleEnumeration readAxisScaleType() throws UaException {
        try {
            return readAxisScaleTypeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAxisScaleType(AxisScaleEnumeration axisScaleType) throws UaException {
        try {
            writeAxisScaleTypeAsync(axisScaleType).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisScaleEnumeration> readAxisScaleTypeAsync() {
        return getAxisScaleTypeNodeAsync()
            .thenCompose(node -> node.readAttributeAsync(AttributeId.Value))
            .thenApply(v -> {
                Object value = v.getValue().getValue();
                if (value instanceof Integer) {
                    return AxisScaleEnumeration.from((Integer) value);
                } else {
                    return null;
                }
            });
    }

    @Override
    public CompletableFuture<StatusCode> writeAxisScaleTypeAsync(AxisScaleEnumeration axisScaleType) {
        DataValue value = DataValue.valueOnly(new Variant(axisScaleType));
        return getAxisScaleTypeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getAxisScaleTypeNode() throws UaException {
        try {
            return getAxisScaleTypeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getAxisScaleTypeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AxisScaleType", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
