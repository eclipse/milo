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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerType;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public class ServerNode extends BaseObjectNode implements ServerType {
    public ServerNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getServerArrayNode() {
        return getPropertyNode(ServerType.SERVER_ARRAY);
    }

    public CompletableFuture<String[]> getServerArray() {
        return getProperty(ServerType.SERVER_ARRAY);
    }

    public CompletableFuture<StatusCode> setServerArray(String[] value) {
        return setProperty(ServerType.SERVER_ARRAY, value);
    }

    public CompletableFuture<PropertyNode> getNamespaceArrayNode() {
        return getPropertyNode(ServerType.NAMESPACE_ARRAY);
    }

    public CompletableFuture<String[]> getNamespaceArray() {
        return getProperty(ServerType.NAMESPACE_ARRAY);
    }

    public CompletableFuture<StatusCode> setNamespaceArray(String[] value) {
        return setProperty(ServerType.NAMESPACE_ARRAY, value);
    }

    public CompletableFuture<PropertyNode> getServiceLevelNode() {
        return getPropertyNode(ServerType.SERVICE_LEVEL);
    }

    public CompletableFuture<UByte> getServiceLevel() {
        return getProperty(ServerType.SERVICE_LEVEL);
    }

    public CompletableFuture<StatusCode> setServiceLevel(UByte value) {
        return setProperty(ServerType.SERVICE_LEVEL, value);
    }

    public CompletableFuture<PropertyNode> getAuditingNode() {
        return getPropertyNode(ServerType.AUDITING);
    }

    public CompletableFuture<Boolean> getAuditing() {
        return getProperty(ServerType.AUDITING);
    }

    public CompletableFuture<StatusCode> setAuditing(Boolean value) {
        return setProperty(ServerType.AUDITING, value);
    }

    public CompletableFuture<PropertyNode> getEstimatedReturnTimeNode() {
        return getPropertyNode(ServerType.ESTIMATED_RETURN_TIME);
    }

    public CompletableFuture<DateTime> getEstimatedReturnTime() {
        return getProperty(ServerType.ESTIMATED_RETURN_TIME);
    }

    public CompletableFuture<StatusCode> setEstimatedReturnTime(DateTime value) {
        return setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
    }

    public CompletableFuture<ServerStatusNode> getServerStatusNode() {
        return getVariableComponent("http://opcfoundation.org/UA/", "ServerStatus").thenApply(ServerStatusNode.class::cast);
    }

    public CompletableFuture<ServerStatusDataType> getServerStatus() {
        return getServerStatusNode().thenCompose(UaVariableNode::getValue).thenApply(o -> cast(o, ServerStatusDataType.class));
    }

    public CompletableFuture<StatusCode> setServerStatus(ServerStatusDataType value) {
        return getServerStatusNode().thenCompose(node -> node.setValue(value));
    }

    public CompletableFuture<ServerCapabilitiesNode> getServerCapabilitiesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerCapabilities").thenApply(ServerCapabilitiesNode.class::cast);
    }

    public CompletableFuture<ServerDiagnosticsNode> getServerDiagnosticsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerDiagnostics").thenApply(ServerDiagnosticsNode.class::cast);
    }

    public CompletableFuture<VendorServerInfoNode> getVendorServerInfoNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "VendorServerInfo").thenApply(VendorServerInfoNode.class::cast);
    }

    public CompletableFuture<ServerRedundancyNode> getServerRedundancyNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ServerRedundancy").thenApply(ServerRedundancyNode.class::cast);
    }

    public CompletableFuture<NamespacesNode> getNamespacesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "Namespaces").thenApply(NamespacesNode.class::cast);
    }
}
