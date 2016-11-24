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

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ServerType")
public class ServerNode extends BaseObjectNode implements ServerType {

    public ServerNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String[] getServerArray() {
        Optional<String[]> property = getProperty(ServerType.SERVER_ARRAY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServerArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVER_ARRAY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServerArray(String[] value) {
        setProperty(ServerType.SERVER_ARRAY, value);
    }

    @Override
    public String[] getNamespaceArray() {
        Optional<String[]> property = getProperty(ServerType.NAMESPACE_ARRAY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNamespaceArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.NAMESPACE_ARRAY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNamespaceArray(String[] value) {
        setProperty(ServerType.NAMESPACE_ARRAY, value);
    }

    @Override
    public UByte getServiceLevel() {
        Optional<UByte> property = getProperty(ServerType.SERVICE_LEVEL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServiceLevelNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVICE_LEVEL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServiceLevel(UByte value) {
        setProperty(ServerType.SERVICE_LEVEL, value);
    }

    @Override
    public Boolean getAuditing() {
        Optional<Boolean> property = getProperty(ServerType.AUDITING);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAuditingNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.AUDITING.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAuditing(Boolean value) {
        setProperty(ServerType.AUDITING, value);
    }

    @Override
    public DateTime getEstimatedReturnTime() {
        Optional<DateTime> property = getProperty(ServerType.ESTIMATED_RETURN_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEstimatedReturnTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerType.ESTIMATED_RETURN_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEstimatedReturnTime(DateTime value) {
        setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
    }

    @Override
    public ServerCapabilitiesNode getServerCapabilitiesNode() {
        Optional<ObjectNode> component = getObjectComponent("ServerCapabilities");

        return component.map(node -> (ServerCapabilitiesNode) node).orElse(null);
    }

    @Override
    public ServerDiagnosticsNode getServerDiagnosticsNode() {
        Optional<ObjectNode> component = getObjectComponent("ServerDiagnostics");

        return component.map(node -> (ServerDiagnosticsNode) node).orElse(null);
    }

    @Override
    public VendorServerInfoNode getVendorServerInfoNode() {
        Optional<ObjectNode> component = getObjectComponent("VendorServerInfo");

        return component.map(node -> (VendorServerInfoNode) node).orElse(null);
    }

    @Override
    public ServerRedundancyNode getServerRedundancyNode() {
        Optional<ObjectNode> component = getObjectComponent("ServerRedundancy");

        return component.map(node -> (ServerRedundancyNode) node).orElse(null);
    }

    @Override
    public NamespacesNode getNamespacesNode() {
        Optional<ObjectNode> component = getObjectComponent("Namespaces");

        return component.map(node -> (NamespacesNode) node).orElse(null);
    }

    @Override
    public ServerStatusDataType getServerStatus() {
        Optional<VariableNode> component = getVariableComponent("ServerStatus");

        return component.map(node -> (ServerStatusDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public ServerStatusNode getServerStatusNode() {
        Optional<VariableNode> component = getVariableComponent("ServerStatus");

        return component.map(node -> (ServerStatusNode) node).orElse(null);
    }

    @Override
    public void setServerStatus(ServerStatusDataType value) {
        getVariableComponent("ServerStatus")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
