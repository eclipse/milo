/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerConfigurationNode extends BaseObjectNode implements ServerConfigurationType {
    public ServerConfigurationNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getServerCapabilitiesNode() {
        return getPropertyNode(ServerConfigurationType.SERVER_CAPABILITIES);
    }

    public CompletableFuture<String[]> getServerCapabilities() {
        return getProperty(ServerConfigurationType.SERVER_CAPABILITIES);
    }

    public CompletableFuture<StatusCode> setServerCapabilities(String[] value) {
        return setProperty(ServerConfigurationType.SERVER_CAPABILITIES, value);
    }

    public CompletableFuture<PropertyNode> getSupportedPrivateKeyFormatsNode() {
        return getPropertyNode(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);
    }

    public CompletableFuture<String[]> getSupportedPrivateKeyFormats() {
        return getProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);
    }

    public CompletableFuture<StatusCode> setSupportedPrivateKeyFormats(String[] value) {
        return setProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS, value);
    }

    public CompletableFuture<PropertyNode> getMaxTrustListSizeNode() {
        return getPropertyNode(ServerConfigurationType.MAX_TRUST_LIST_SIZE);
    }

    public CompletableFuture<UInteger> getMaxTrustListSize() {
        return getProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE);
    }

    public CompletableFuture<StatusCode> setMaxTrustListSize(UInteger value) {
        return setProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE, value);
    }

    public CompletableFuture<PropertyNode> getMulticastDnsEnabledNode() {
        return getPropertyNode(ServerConfigurationType.MULTICAST_DNS_ENABLED);
    }

    public CompletableFuture<Boolean> getMulticastDnsEnabled() {
        return getProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED);
    }

    public CompletableFuture<StatusCode> setMulticastDnsEnabled(Boolean value) {
        return setProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED, value);
    }

    public CompletableFuture<CertificateGroupFolderNode> getCertificateGroupsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "CertificateGroups").thenApply(CertificateGroupFolderNode.class::cast);
    }
}
