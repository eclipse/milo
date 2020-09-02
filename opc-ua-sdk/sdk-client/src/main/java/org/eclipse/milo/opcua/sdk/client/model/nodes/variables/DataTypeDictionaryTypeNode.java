package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataTypeDictionaryType;
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

public class DataTypeDictionaryTypeNode extends BaseDataVariableTypeNode implements DataTypeDictionaryType {
    public DataTypeDictionaryTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType, int valueRank,
                                      UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                      double minimumSamplingInterval, boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public String getDataTypeVersion() throws UaException {
        PropertyTypeNode node = getDataTypeVersionNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setDataTypeVersion(String dataTypeVersion) throws UaException {
        PropertyTypeNode node = getDataTypeVersionNode();
        node.setValue(new Variant(dataTypeVersion));
    }

    @Override
    public String readDataTypeVersion() throws UaException {
        try {
            return readDataTypeVersionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeDataTypeVersion(String dataTypeVersion) throws UaException {
        try {
            writeDataTypeVersionAsync(dataTypeVersion).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readDataTypeVersionAsync() {
        return getDataTypeVersionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeDataTypeVersionAsync(String dataTypeVersion) {
        DataValue value = DataValue.valueOnly(new Variant(dataTypeVersion));
        return getDataTypeVersionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getDataTypeVersionNode() throws UaException {
        try {
            return getDataTypeVersionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getDataTypeVersionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "DataTypeVersion", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String getNamespaceUri() throws UaException {
        PropertyTypeNode node = getNamespaceUriNode();
        return (String) node.getValue().getValue().getValue();
    }

    @Override
    public void setNamespaceUri(String namespaceUri) throws UaException {
        PropertyTypeNode node = getNamespaceUriNode();
        node.setValue(new Variant(namespaceUri));
    }

    @Override
    public String readNamespaceUri() throws UaException {
        try {
            return readNamespaceUriAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNamespaceUri(String namespaceUri) throws UaException {
        try {
            writeNamespaceUriAsync(namespaceUri).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String> readNamespaceUriAsync() {
        return getNamespaceUriNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNamespaceUriAsync(String namespaceUri) {
        DataValue value = DataValue.valueOnly(new Variant(namespaceUri));
        return getNamespaceUriNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNamespaceUriNode() throws UaException {
        try {
            return getNamespaceUriNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNamespaceUriNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NamespaceUri", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
