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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ServerConfigurationType")
public class ServerConfigurationNode extends BaseObjectNode implements ServerConfigurationType {

    public ServerConfigurationNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String[] getServerCapabilities() {
        Optional<String[]> property = getProperty(ServerConfigurationType.SERVER_CAPABILITIES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServerCapabilitiesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.SERVER_CAPABILITIES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServerCapabilities(String[] value) {
        setProperty(ServerConfigurationType.SERVER_CAPABILITIES, value);
    }

    @Override
    public String[] getSupportedPrivateKeyFormats() {
        Optional<String[]> property = getProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSupportedPrivateKeyFormatsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSupportedPrivateKeyFormats(String[] value) {
        setProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS, value);
    }

    @Override
    public UInteger getMaxTrustListSize() {
        Optional<UInteger> property = getProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxTrustListSizeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.MAX_TRUST_LIST_SIZE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxTrustListSize(UInteger value) {
        setProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE, value);
    }

    @Override
    public Boolean getMulticastDnsEnabled() {
        Optional<Boolean> property = getProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMulticastDnsEnabledNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.MULTICAST_DNS_ENABLED.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMulticastDnsEnabled(Boolean value) {
        setProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED, value);
    }

    @Override
    public CertificateGroupFolderNode getCertificateGroupsNode() {
        Optional<ObjectNode> component = getObjectComponent("CertificateGroups");

        return component.map(node -> (CertificateGroupFolderNode) node).orElse(null);
    }

}
