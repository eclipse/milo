package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class BuildInfoTypeNode extends BaseDataVariableTypeNode implements BuildInfoType {
    public BuildInfoTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                             UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                             Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                             Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public String getProductUri() throws UaException {
        BaseDataVariableTypeNode node = getProductUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setProductUri(String productUri) throws UaException {
        BaseDataVariableTypeNode node = getProductUriNode();
        node.setValue(new Variant(productUri));
    }

    @Override
    public String readProductUri() throws UaException {
        try {
            return readProductUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeProductUri(String productUri) throws UaException {
        try {
            writeProductUriAsync(productUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readProductUriAsync() {
        return getProductUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeProductUriAsync(String productUri) {
        DataValue value = DataValue.valueOnly(new Variant(productUri));
        return getProductUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getProductUriNode() throws UaException {
        try {
            return getProductUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getProductUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ProductUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getManufacturerName() throws UaException {
        BaseDataVariableTypeNode node = getManufacturerNameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setManufacturerName(String manufacturerName) throws UaException {
        BaseDataVariableTypeNode node = getManufacturerNameNode();
        node.setValue(new Variant(manufacturerName));
    }

    @Override
    public String readManufacturerName() throws UaException {
        try {
            return readManufacturerNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeManufacturerName(String manufacturerName) throws UaException {
        try {
            writeManufacturerNameAsync(manufacturerName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readManufacturerNameAsync() {
        return getManufacturerNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeManufacturerNameAsync(String manufacturerName) {
        DataValue value = DataValue.valueOnly(new Variant(manufacturerName));
        return getManufacturerNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getManufacturerNameNode() throws UaException {
        try {
            return getManufacturerNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getManufacturerNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ManufacturerName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getProductName() throws UaException {
        BaseDataVariableTypeNode node = getProductNameNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setProductName(String productName) throws UaException {
        BaseDataVariableTypeNode node = getProductNameNode();
        node.setValue(new Variant(productName));
    }

    @Override
    public String readProductName() throws UaException {
        try {
            return readProductNameAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeProductName(String productName) throws UaException {
        try {
            writeProductNameAsync(productName).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readProductNameAsync() {
        return getProductNameNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeProductNameAsync(String productName) {
        DataValue value = DataValue.valueOnly(new Variant(productName));
        return getProductNameNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getProductNameNode() throws UaException {
        try {
            return getProductNameNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getProductNameNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ProductName", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getSoftwareVersion() throws UaException {
        BaseDataVariableTypeNode node = getSoftwareVersionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setSoftwareVersion(String softwareVersion) throws UaException {
        BaseDataVariableTypeNode node = getSoftwareVersionNode();
        node.setValue(new Variant(softwareVersion));
    }

    @Override
    public String readSoftwareVersion() throws UaException {
        try {
            return readSoftwareVersionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSoftwareVersion(String softwareVersion) throws UaException {
        try {
            writeSoftwareVersionAsync(softwareVersion).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readSoftwareVersionAsync() {
        return getSoftwareVersionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSoftwareVersionAsync(String softwareVersion) {
        DataValue value = DataValue.valueOnly(new Variant(softwareVersion));
        return getSoftwareVersionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getSoftwareVersionNode() throws UaException {
        try {
            return getSoftwareVersionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getSoftwareVersionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SoftwareVersion", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public String getBuildNumber() throws UaException {
        BaseDataVariableTypeNode node = getBuildNumberNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setBuildNumber(String buildNumber) throws UaException {
        BaseDataVariableTypeNode node = getBuildNumberNode();
        node.setValue(new Variant(buildNumber));
    }

    @Override
    public String readBuildNumber() throws UaException {
        try {
            return readBuildNumberAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBuildNumber(String buildNumber) throws UaException {
        try {
            writeBuildNumberAsync(buildNumber).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readBuildNumberAsync() {
        return getBuildNumberNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeBuildNumberAsync(String buildNumber) {
        DataValue value = DataValue.valueOnly(new Variant(buildNumber));
        return getBuildNumberNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getBuildNumberNode() throws UaException {
        try {
            return getBuildNumberNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBuildNumberNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BuildNumber", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }

    @Override
    public DateTime getBuildDate() throws UaException {
        BaseDataVariableTypeNode node = getBuildDateNode();
        return (DateTime) node.getValue().getValue().getValue();
    }

    @Override
    public void setBuildDate(DateTime buildDate) throws UaException {
        BaseDataVariableTypeNode node = getBuildDateNode();
        node.setValue(new Variant(buildDate));
    }

    @Override
    public DateTime readBuildDate() throws UaException {
        try {
            return readBuildDateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeBuildDate(DateTime buildDate) throws UaException {
        try {
            writeBuildDateAsync(buildDate).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends DateTime> readBuildDateAsync() {
        return getBuildDateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (DateTime) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeBuildDateAsync(DateTime buildDate) {
        DataValue value = DataValue.valueOnly(new Variant(buildDate));
        return getBuildDateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public BaseDataVariableTypeNode getBuildDateNode() throws UaException {
        try {
            return getBuildDateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends BaseDataVariableTypeNode> getBuildDateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "BuildDate", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (BaseDataVariableTypeNode) node);
    }
}
