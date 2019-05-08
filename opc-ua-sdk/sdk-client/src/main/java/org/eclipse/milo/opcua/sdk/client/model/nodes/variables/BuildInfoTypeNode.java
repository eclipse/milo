/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class BuildInfoTypeNode extends BaseDataVariableTypeNode implements BuildInfoType {
    public BuildInfoTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getProductUriNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProductUri").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getProductUri() {
        return getProductUriNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProductUri(String value) {
        return getProductUriNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getManufacturerNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ManufacturerName").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getManufacturerName() {
        return getManufacturerNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setManufacturerName(String value) {
        return getManufacturerNameNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getProductNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProductName").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getProductName() {
        return getProductNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProductName(String value) {
        return getProductNameNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getSoftwareVersionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SoftwareVersion").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getSoftwareVersion() {
        return getSoftwareVersionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSoftwareVersion(String value) {
        return getSoftwareVersionNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getBuildNumberNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildNumber").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<String> getBuildNumber() {
        return getBuildNumberNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildNumber(String value) {
        return getBuildNumberNode().thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableTypeNode> getBuildDateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildDate").thenApply(BaseDataVariableTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<DateTime> getBuildDate() {
        return getBuildDateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildDate(DateTime value) {
        return getBuildDateNode().thenCompose(node -> node.setValue(value));
    }
}
