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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;


public class ServerNode extends BaseObjectNode implements ServerType {

    public ServerNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> serverArray() {
        return getPropertyNode(ServerType.SERVER_ARRAY.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getServerArray() {
        return getProperty(ServerType.SERVER_ARRAY);
    }

    @Override
    public CompletableFuture<StatusCode> setServerArray(String[] value) {
        return setProperty(ServerType.SERVER_ARRAY, value);
    }

    @Override
    public CompletableFuture<PropertyNode> namespaceArray() {
        return getPropertyNode(ServerType.NAMESPACE_ARRAY.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getNamespaceArray() {
        return getProperty(ServerType.NAMESPACE_ARRAY);
    }

    @Override
    public CompletableFuture<StatusCode> setNamespaceArray(String[] value) {
        return setProperty(ServerType.NAMESPACE_ARRAY, value);
    }

    @Override
    public CompletableFuture<PropertyNode> serviceLevel() {
        return getPropertyNode(ServerType.SERVICE_LEVEL.getBrowseName());
    }

    @Override
    public CompletableFuture<UByte> getServiceLevel() {
        return getProperty(ServerType.SERVICE_LEVEL);
    }

    @Override
    public CompletableFuture<StatusCode> setServiceLevel(UByte value) {
        return setProperty(ServerType.SERVICE_LEVEL, value);
    }

    @Override
    public CompletableFuture<PropertyNode> auditing() {
        return getPropertyNode(ServerType.AUDITING.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getAuditing() {
        return getProperty(ServerType.AUDITING);
    }

    @Override
    public CompletableFuture<StatusCode> setAuditing(Boolean value) {
        return setProperty(ServerType.AUDITING, value);
    }

    @Override
    public CompletableFuture<PropertyNode> estimatedReturnTime() {
        return getPropertyNode(ServerType.ESTIMATED_RETURN_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getEstimatedReturnTime() {
        return getProperty(ServerType.ESTIMATED_RETURN_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setEstimatedReturnTime(DateTime value) {
        return setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
    }


    @Override
    public CompletableFuture<ServerStatusNode> serverStatus() {
        return getVariableComponent(QualifiedName.parse("0:ServerStatus"))
            .thenApply(ServerStatusNode.class::cast);
    }

    public CompletableFuture<ServerStatusDataType> getServerStatus() {
        return serverStatus()
            .thenCompose(UaVariableNode::getValue)
            .thenApply(o -> cast(o, ServerStatusDataType.class));
    }

    @Override
    public CompletableFuture<StatusCode> setServerStatus(ServerStatusDataType value) {
        return serverStatus()
            .thenCompose(node -> node.setValue(value));
    }

    @Override
    public CompletableFuture<ServerCapabilitiesNode> serverCapabilities() {
        return getObjectComponent(QualifiedName.parse("0:ServerCapabilities"))
            .thenApply(ServerCapabilitiesNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerDiagnosticsNode> serverDiagnostics() {
        return getObjectComponent(QualifiedName.parse("0:ServerDiagnostics"))
            .thenApply(ServerDiagnosticsNode.class::cast);
    }

    @Override
    public CompletableFuture<VendorServerInfoNode> vendorServerInfo() {
        return getObjectComponent(QualifiedName.parse("0:VendorServerInfo"))
            .thenApply(VendorServerInfoNode.class::cast);
    }

    @Override
    public CompletableFuture<ServerRedundancyNode> serverRedundancy() {
        return getObjectComponent(QualifiedName.parse("0:ServerRedundancy"))
            .thenApply(ServerRedundancyNode.class::cast);
    }

    @Override
    public CompletableFuture<NamespacesNode> namespaces() {
        return getObjectComponent(QualifiedName.parse("0:Namespaces"))
            .thenApply(NamespacesNode.class::cast);
    }

}