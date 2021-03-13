package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public class ServerCapabilitiesTypeNode extends BaseObjectTypeNode implements ServerCapabilitiesType {
    public ServerCapabilitiesTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String[] getServerProfileArray() throws UaException {
        PropertyTypeNode node = getServerProfileArrayNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerProfileArray(String[] serverProfileArray) throws UaException {
        PropertyTypeNode node = getServerProfileArrayNode();
        node.setValue(new Variant(serverProfileArray));
    }

    @Override
    public String[] readServerProfileArray() throws UaException {
        try {
            return readServerProfileArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerProfileArray(String[] serverProfileArray) throws UaException {
        try {
            writeServerProfileArrayAsync(serverProfileArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readServerProfileArrayAsync() {
        return getServerProfileArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeServerProfileArrayAsync(String[] serverProfileArray) {
        DataValue value = DataValue.valueOnly(new Variant(serverProfileArray));
        return getServerProfileArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getServerProfileArrayNode() throws UaException {
        try {
            return getServerProfileArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getServerProfileArrayNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerProfileArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String[] getLocaleIdArray() throws UaException {
        PropertyTypeNode node = getLocaleIdArrayNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setLocaleIdArray(String[] localeIdArray) throws UaException {
        PropertyTypeNode node = getLocaleIdArrayNode();
        node.setValue(new Variant(localeIdArray));
    }

    @Override
    public String[] readLocaleIdArray() throws UaException {
        try {
            return readLocaleIdArrayAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeLocaleIdArray(String[] localeIdArray) throws UaException {
        try {
            writeLocaleIdArrayAsync(localeIdArray).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readLocaleIdArrayAsync() {
        return getLocaleIdArrayNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeLocaleIdArrayAsync(String[] localeIdArray) {
        DataValue value = DataValue.valueOnly(new Variant(localeIdArray));
        return getLocaleIdArrayNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getLocaleIdArrayNode() throws UaException {
        try {
            return getLocaleIdArrayNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getLocaleIdArrayNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LocaleIdArray", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Double getMinSupportedSampleRate() throws UaException {
        PropertyTypeNode node = getMinSupportedSampleRateNode();
        return (Double) node.getValue().getValue().getValue();
    }

    @Override
    public void setMinSupportedSampleRate(Double minSupportedSampleRate) throws UaException {
        PropertyTypeNode node = getMinSupportedSampleRateNode();
        node.setValue(new Variant(minSupportedSampleRate));
    }

    @Override
    public Double readMinSupportedSampleRate() throws UaException {
        try {
            return readMinSupportedSampleRateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMinSupportedSampleRate(Double minSupportedSampleRate) throws UaException {
        try {
            writeMinSupportedSampleRateAsync(minSupportedSampleRate).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Double> readMinSupportedSampleRateAsync() {
        return getMinSupportedSampleRateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Double) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMinSupportedSampleRateAsync(
        Double minSupportedSampleRate) {
        DataValue value = DataValue.valueOnly(new Variant(minSupportedSampleRate));
        return getMinSupportedSampleRateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMinSupportedSampleRateNode() throws UaException {
        try {
            return getMinSupportedSampleRateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMinSupportedSampleRateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MinSupportedSampleRate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UShort getMaxBrowseContinuationPoints() throws UaException {
        PropertyTypeNode node = getMaxBrowseContinuationPointsNode();
        return (UShort) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxBrowseContinuationPoints(UShort maxBrowseContinuationPoints) throws
        UaException {
        PropertyTypeNode node = getMaxBrowseContinuationPointsNode();
        node.setValue(new Variant(maxBrowseContinuationPoints));
    }

    @Override
    public UShort readMaxBrowseContinuationPoints() throws UaException {
        try {
            return readMaxBrowseContinuationPointsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxBrowseContinuationPoints(UShort maxBrowseContinuationPoints) throws
        UaException {
        try {
            writeMaxBrowseContinuationPointsAsync(maxBrowseContinuationPoints).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UShort> readMaxBrowseContinuationPointsAsync() {
        return getMaxBrowseContinuationPointsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UShort) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxBrowseContinuationPointsAsync(
        UShort maxBrowseContinuationPoints) {
        DataValue value = DataValue.valueOnly(new Variant(maxBrowseContinuationPoints));
        return getMaxBrowseContinuationPointsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxBrowseContinuationPointsNode() throws UaException {
        try {
            return getMaxBrowseContinuationPointsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxBrowseContinuationPointsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxBrowseContinuationPoints", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UShort getMaxQueryContinuationPoints() throws UaException {
        PropertyTypeNode node = getMaxQueryContinuationPointsNode();
        return (UShort) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxQueryContinuationPoints(UShort maxQueryContinuationPoints) throws UaException {
        PropertyTypeNode node = getMaxQueryContinuationPointsNode();
        node.setValue(new Variant(maxQueryContinuationPoints));
    }

    @Override
    public UShort readMaxQueryContinuationPoints() throws UaException {
        try {
            return readMaxQueryContinuationPointsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxQueryContinuationPoints(UShort maxQueryContinuationPoints) throws
        UaException {
        try {
            writeMaxQueryContinuationPointsAsync(maxQueryContinuationPoints).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UShort> readMaxQueryContinuationPointsAsync() {
        return getMaxQueryContinuationPointsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UShort) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxQueryContinuationPointsAsync(
        UShort maxQueryContinuationPoints) {
        DataValue value = DataValue.valueOnly(new Variant(maxQueryContinuationPoints));
        return getMaxQueryContinuationPointsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxQueryContinuationPointsNode() throws UaException {
        try {
            return getMaxQueryContinuationPointsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxQueryContinuationPointsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxQueryContinuationPoints", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UShort getMaxHistoryContinuationPoints() throws UaException {
        PropertyTypeNode node = getMaxHistoryContinuationPointsNode();
        return (UShort) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxHistoryContinuationPoints(UShort maxHistoryContinuationPoints) throws
        UaException {
        PropertyTypeNode node = getMaxHistoryContinuationPointsNode();
        node.setValue(new Variant(maxHistoryContinuationPoints));
    }

    @Override
    public UShort readMaxHistoryContinuationPoints() throws UaException {
        try {
            return readMaxHistoryContinuationPointsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxHistoryContinuationPoints(UShort maxHistoryContinuationPoints) throws
        UaException {
        try {
            writeMaxHistoryContinuationPointsAsync(maxHistoryContinuationPoints).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UShort> readMaxHistoryContinuationPointsAsync() {
        return getMaxHistoryContinuationPointsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UShort) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxHistoryContinuationPointsAsync(
        UShort maxHistoryContinuationPoints) {
        DataValue value = DataValue.valueOnly(new Variant(maxHistoryContinuationPoints));
        return getMaxHistoryContinuationPointsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxHistoryContinuationPointsNode() throws UaException {
        try {
            return getMaxHistoryContinuationPointsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxHistoryContinuationPointsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxHistoryContinuationPoints", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public SignedSoftwareCertificate[] getSoftwareCertificates() throws UaException {
        PropertyTypeNode node = getSoftwareCertificatesNode();
        return cast(node.getValue().getValue().getValue(), SignedSoftwareCertificate[].class);
    }

    @Override
    public void setSoftwareCertificates(SignedSoftwareCertificate[] softwareCertificates) throws
        UaException {
        PropertyTypeNode node = getSoftwareCertificatesNode();
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), softwareCertificates);
        node.setValue(new Variant(encoded));
    }

    @Override
    public SignedSoftwareCertificate[] readSoftwareCertificates() throws UaException {
        try {
            return readSoftwareCertificatesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSoftwareCertificates(SignedSoftwareCertificate[] softwareCertificates) throws
        UaException {
        try {
            writeSoftwareCertificatesAsync(softwareCertificates).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends SignedSoftwareCertificate[]> readSoftwareCertificatesAsync() {
        return getSoftwareCertificatesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), SignedSoftwareCertificate[].class));
    }

    @Override
    public CompletableFuture<StatusCode> writeSoftwareCertificatesAsync(
        SignedSoftwareCertificate[] softwareCertificates) {
        ExtensionObject[] encoded = ExtensionObject.encodeArray(client.getStaticSerializationContext(), softwareCertificates);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getSoftwareCertificatesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSoftwareCertificatesNode() throws UaException {
        try {
            return getSoftwareCertificatesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSoftwareCertificatesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SoftwareCertificates", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxArrayLength() throws UaException {
        PropertyTypeNode node = getMaxArrayLengthNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxArrayLength(UInteger maxArrayLength) throws UaException {
        PropertyTypeNode node = getMaxArrayLengthNode();
        node.setValue(new Variant(maxArrayLength));
    }

    @Override
    public UInteger readMaxArrayLength() throws UaException {
        try {
            return readMaxArrayLengthAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxArrayLength(UInteger maxArrayLength) throws UaException {
        try {
            writeMaxArrayLengthAsync(maxArrayLength).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxArrayLengthAsync() {
        return getMaxArrayLengthNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxArrayLengthAsync(UInteger maxArrayLength) {
        DataValue value = DataValue.valueOnly(new Variant(maxArrayLength));
        return getMaxArrayLengthNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxArrayLengthNode() throws UaException {
        try {
            return getMaxArrayLengthNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxArrayLengthNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxArrayLength", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxStringLength() throws UaException {
        PropertyTypeNode node = getMaxStringLengthNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxStringLength(UInteger maxStringLength) throws UaException {
        PropertyTypeNode node = getMaxStringLengthNode();
        node.setValue(new Variant(maxStringLength));
    }

    @Override
    public UInteger readMaxStringLength() throws UaException {
        try {
            return readMaxStringLengthAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxStringLength(UInteger maxStringLength) throws UaException {
        try {
            writeMaxStringLengthAsync(maxStringLength).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxStringLengthAsync() {
        return getMaxStringLengthNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxStringLengthAsync(UInteger maxStringLength) {
        DataValue value = DataValue.valueOnly(new Variant(maxStringLength));
        return getMaxStringLengthNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxStringLengthNode() throws UaException {
        try {
            return getMaxStringLengthNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxStringLengthNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxStringLength", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxByteStringLength() throws UaException {
        PropertyTypeNode node = getMaxByteStringLengthNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxByteStringLength(UInteger maxByteStringLength) throws UaException {
        PropertyTypeNode node = getMaxByteStringLengthNode();
        node.setValue(new Variant(maxByteStringLength));
    }

    @Override
    public UInteger readMaxByteStringLength() throws UaException {
        try {
            return readMaxByteStringLengthAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxByteStringLength(UInteger maxByteStringLength) throws UaException {
        try {
            writeMaxByteStringLengthAsync(maxByteStringLength).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxByteStringLengthAsync() {
        return getMaxByteStringLengthNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxByteStringLengthAsync(UInteger maxByteStringLength) {
        DataValue value = DataValue.valueOnly(new Variant(maxByteStringLength));
        return getMaxByteStringLengthNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxByteStringLengthNode() throws UaException {
        try {
            return getMaxByteStringLengthNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxByteStringLengthNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxByteStringLength", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    public OperationLimitsTypeNode getOperationLimitsNode() throws UaException {
        try {
            return getOperationLimitsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends OperationLimitsTypeNode> getOperationLimitsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OperationLimits", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (OperationLimitsTypeNode) node);
    }

    public FolderTypeNode getModellingRulesNode() throws UaException {
        try {
            return getModellingRulesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends FolderTypeNode> getModellingRulesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ModellingRules", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FolderTypeNode) node);
    }

    public FolderTypeNode getAggregateFunctionsNode() throws UaException {
        try {
            return getAggregateFunctionsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends FolderTypeNode> getAggregateFunctionsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AggregateFunctions", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FolderTypeNode) node);
    }
}
