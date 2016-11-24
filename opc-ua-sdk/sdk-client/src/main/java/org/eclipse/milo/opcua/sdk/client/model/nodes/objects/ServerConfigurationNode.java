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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public class ServerConfigurationNode extends BaseObjectNode implements ServerConfigurationType {

    public ServerConfigurationNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> serverCapabilities() {
        return getPropertyNode(ServerConfigurationType.SERVER_CAPABILITIES.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getServerCapabilities() {
        return getProperty(ServerConfigurationType.SERVER_CAPABILITIES);
    }

    @Override
    public CompletableFuture<StatusCode> setServerCapabilities(String[] value) {
        return setProperty(ServerConfigurationType.SERVER_CAPABILITIES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> supportedPrivateKeyFormats() {
        return getPropertyNode(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getSupportedPrivateKeyFormats() {
        return getProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);
    }

    @Override
    public CompletableFuture<StatusCode> setSupportedPrivateKeyFormats(String[] value) {
        return setProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxTrustListSize() {
        return getPropertyNode(ServerConfigurationType.MAX_TRUST_LIST_SIZE.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxTrustListSize() {
        return getProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxTrustListSize(UInteger value) {
        return setProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> multicastDnsEnabled() {
        return getPropertyNode(ServerConfigurationType.MULTICAST_DNS_ENABLED.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getMulticastDnsEnabled() {
        return getProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED);
    }

    @Override
    public CompletableFuture<StatusCode> setMulticastDnsEnabled(Boolean value) {
        return setProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED, value);
    }


    @Override
    public CompletableFuture<CertificateGroupFolderNode> certificateGroups() {
        return getObjectComponent(QualifiedName.parse("0:CertificateGroups"))
            .thenApply(CertificateGroupFolderNode.class::cast);
    }

}