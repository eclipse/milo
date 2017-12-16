/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class BuildInfoNode extends BaseDataVariableNode implements BuildInfoType {
    public BuildInfoNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<BaseDataVariableNode> getProductUriNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProductUri").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getProductUri() {
        return getProductUriNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setProductUri(String value) {
        return getProductUriNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getManufacturerNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ManufacturerName").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getManufacturerName() {
        return getManufacturerNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setManufacturerName(String value) {
        return getManufacturerNameNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getProductNameNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ProductName").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getProductName() {
        return getProductNameNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setProductName(String value) {
        return getProductNameNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getSoftwareVersionNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "SoftwareVersion").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getSoftwareVersion() {
        return getSoftwareVersionNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setSoftwareVersion(String value) {
        return getSoftwareVersionNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getBuildNumberNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildNumber").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getBuildNumber() {
        return getBuildNumberNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, String.class));
    }

    public CompletableFuture<StatusCode> setBuildNumber(String value) {
        return getBuildNumberNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<BaseDataVariableNode> getBuildDateNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "BuildDate").thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getBuildDate() {
        return getBuildDateNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, DateTime.class));
    }

    public CompletableFuture<StatusCode> setBuildDate(DateTime value) {
        return getBuildDateNode().thenCompose(node -> node.setValue(value));
    }
}
