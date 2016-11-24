/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class BuildInfoNode extends BaseDataVariableNode implements BuildInfoType {

    public BuildInfoNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }


    @Override
    public CompletableFuture<BaseDataVariableNode> productUri() {
        return getComponent(QualifiedName.parse("0:ProductUri"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getProductUri() {
        return productUri()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProductUri(String value) {
        return productUri()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> manufacturerName() {
        return getComponent(QualifiedName.parse("0:ManufacturerName"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getManufacturerName() {
        return manufacturerName()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setManufacturerName(String value) {
        return manufacturerName()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> productName() {
        return getComponent(QualifiedName.parse("0:ProductName"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getProductName() {
        return productName()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setProductName(String value) {
        return productName()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> softwareVersion() {
        return getComponent(QualifiedName.parse("0:SoftwareVersion"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getSoftwareVersion() {
        return softwareVersion()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setSoftwareVersion(String value) {
        return softwareVersion()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> buildNumber() {
        return getComponent(QualifiedName.parse("0:BuildNumber"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<String> getBuildNumber() {
        return buildNumber()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, String.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildNumber(String value) {
        return buildNumber()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<BaseDataVariableNode> buildDate() {
        return getComponent(QualifiedName.parse("0:BuildDate"))
            .thenApply(BaseDataVariableNode.class::cast);
    }

    public CompletableFuture<DateTime> getBuildDate() {
        return buildDate()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, DateTime.class));
    }

    @Override
    public CompletableFuture<StatusCode> setBuildDate(DateTime value) {
        return buildDate()
            .thenCompose(node -> node.setValue(value));
    }

}