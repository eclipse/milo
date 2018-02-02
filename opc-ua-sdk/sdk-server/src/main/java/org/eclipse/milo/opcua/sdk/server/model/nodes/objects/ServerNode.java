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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public class ServerNode extends BaseObjectNode implements ServerType {
    public ServerNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                      UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ServerNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                      UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getServerArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVER_ARRAY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String[] getServerArray() {
        Optional<String[]> propertyValue = getProperty(ServerType.SERVER_ARRAY);
        return propertyValue.orElse(null);
    }

    public void setServerArray(String[] value) {
        setProperty(ServerType.SERVER_ARRAY, value);
    }

    public PropertyNode getNamespaceArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.NAMESPACE_ARRAY);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String[] getNamespaceArray() {
        Optional<String[]> propertyValue = getProperty(ServerType.NAMESPACE_ARRAY);
        return propertyValue.orElse(null);
    }

    public void setNamespaceArray(String[] value) {
        setProperty(ServerType.NAMESPACE_ARRAY, value);
    }

    public PropertyNode getServiceLevelNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVICE_LEVEL);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UByte getServiceLevel() {
        Optional<UByte> propertyValue = getProperty(ServerType.SERVICE_LEVEL);
        return propertyValue.orElse(null);
    }

    public void setServiceLevel(UByte value) {
        setProperty(ServerType.SERVICE_LEVEL, value);
    }

    public PropertyNode getAuditingNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.AUDITING);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Boolean getAuditing() {
        Optional<Boolean> propertyValue = getProperty(ServerType.AUDITING);
        return propertyValue.orElse(null);
    }

    public void setAuditing(Boolean value) {
        setProperty(ServerType.AUDITING, value);
    }

    public PropertyNode getEstimatedReturnTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.ESTIMATED_RETURN_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DateTime getEstimatedReturnTime() {
        Optional<DateTime> propertyValue = getProperty(ServerType.ESTIMATED_RETURN_TIME);
        return propertyValue.orElse(null);
    }

    public void setEstimatedReturnTime(DateTime value) {
        setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
    }

    public ServerStatusNode getServerStatusNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerStatus");
        return component.map(node -> (ServerStatusNode) node).orElse(null);
    }

    public ServerStatusDataType getServerStatus() {
        Optional<VariableNode> component = getVariableComponent("ServerStatus");
        return component.map(node -> (ServerStatusDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setServerStatus(ServerStatusDataType value) {
        getVariableComponent("ServerStatus").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public ServerCapabilitiesNode getServerCapabilitiesNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerCapabilities");
        return component.map(node -> (ServerCapabilitiesNode) node).orElse(null);
    }

    public ServerDiagnosticsNode getServerDiagnosticsNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerDiagnostics");
        return component.map(node -> (ServerDiagnosticsNode) node).orElse(null);
    }

    public VendorServerInfoNode getVendorServerInfoNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "VendorServerInfo");
        return component.map(node -> (VendorServerInfoNode) node).orElse(null);
    }

    public ServerRedundancyNode getServerRedundancyNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerRedundancy");
        return component.map(node -> (ServerRedundancyNode) node).orElse(null);
    }

    public NamespacesNode getNamespacesNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Namespaces");
        return component.map(node -> (NamespacesNode) node).orElse(null);
    }
}
