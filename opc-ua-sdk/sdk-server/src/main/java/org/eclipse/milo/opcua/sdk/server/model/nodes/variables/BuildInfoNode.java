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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class BuildInfoNode extends BaseDataVariableNode implements BuildInfoType {
    public BuildInfoNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                         LocalizedText displayName, LocalizedText description, UInteger writeMask,
                         UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public BuildInfoNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                         LocalizedText displayName, LocalizedText description, UInteger writeMask,
                         UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                         UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                         double minimumSamplingInterval, boolean historizing) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    public BaseDataVariableNode getProductUriNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ProductUri");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public String getProductUri() {
        Optional<VariableNode> component = getVariableComponent("ProductUri");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setProductUri(String value) {
        getVariableComponent("ProductUri").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getManufacturerNameNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ManufacturerName");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public String getManufacturerName() {
        Optional<VariableNode> component = getVariableComponent("ManufacturerName");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setManufacturerName(String value) {
        getVariableComponent("ManufacturerName").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getProductNameNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ProductName");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public String getProductName() {
        Optional<VariableNode> component = getVariableComponent("ProductName");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setProductName(String value) {
        getVariableComponent("ProductName").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getSoftwareVersionNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SoftwareVersion");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public String getSoftwareVersion() {
        Optional<VariableNode> component = getVariableComponent("SoftwareVersion");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSoftwareVersion(String value) {
        getVariableComponent("SoftwareVersion").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getBuildNumberNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BuildNumber");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public String getBuildNumber() {
        Optional<VariableNode> component = getVariableComponent("BuildNumber");
        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setBuildNumber(String value) {
        getVariableComponent("BuildNumber").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public BaseDataVariableNode getBuildDateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "BuildDate");
        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    public DateTime getBuildDate() {
        Optional<VariableNode> component = getVariableComponent("BuildDate");
        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setBuildDate(DateTime value) {
        getVariableComponent("BuildDate").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
