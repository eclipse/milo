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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BuildInfoType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;

@org.eclipse.milo.opcua.sdk.core.annotations.UaVariableNode(typeName = "0:BuildInfoType")
public class BuildInfoNode extends BaseDataVariableNode implements BuildInfoType {

    public BuildInfoNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        VariableTypeNode variableTypeNode) {

        super(nodeManager, nodeId, variableTypeNode);
    }

    public BuildInfoNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        DataValue value,
        NodeId dataType,
        Integer valueRank,
        UInteger[] arrayDimensions,
        UByte accessLevel,
        UByte userAccessLevel,
        Double minimumSamplingInterval,
        boolean historizing) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask,
            value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public synchronized DataValue getValue() {
        BuildInfo value = new BuildInfo(
            getProductUri(),
            getManufacturerName(),
            getProductName(),
            getSoftwareVersion(),
            getBuildNumber(),
            getBuildDate()
        );

        return new DataValue(new Variant(value));
    }

    @Override
    public synchronized void setValue(DataValue value) {
        super.setValue(value);

        Object o = value.getValue().getValue();

        if (o instanceof BuildInfo) {
            BuildInfo v = (BuildInfo) o;

            setProductUri(v.getProductUri());
            setManufacturerName(v.getManufacturerName());
            setProductName(v.getProductName());
            setSoftwareVersion(v.getSoftwareVersion());
            setBuildNumber(v.getBuildNumber());
            setBuildDate(v.getBuildDate());
        }
    }

    @Override
    public String getProductUri() {
        Optional<VariableNode> component = getVariableComponent("ProductUri");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getProductUriNode() {
        Optional<VariableNode> component = getVariableComponent("ProductUri");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setProductUri(String value) {
        getVariableComponent("ProductUri")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getManufacturerName() {
        Optional<VariableNode> component = getVariableComponent("ManufacturerName");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getManufacturerNameNode() {
        Optional<VariableNode> component = getVariableComponent("ManufacturerName");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setManufacturerName(String value) {
        getVariableComponent("ManufacturerName")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getProductName() {
        Optional<VariableNode> component = getVariableComponent("ProductName");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getProductNameNode() {
        Optional<VariableNode> component = getVariableComponent("ProductName");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setProductName(String value) {
        getVariableComponent("ProductName")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getSoftwareVersion() {
        Optional<VariableNode> component = getVariableComponent("SoftwareVersion");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getSoftwareVersionNode() {
        Optional<VariableNode> component = getVariableComponent("SoftwareVersion");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setSoftwareVersion(String value) {
        getVariableComponent("SoftwareVersion")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public String getBuildNumber() {
        Optional<VariableNode> component = getVariableComponent("BuildNumber");

        return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getBuildNumberNode() {
        Optional<VariableNode> component = getVariableComponent("BuildNumber");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setBuildNumber(String value) {
        getVariableComponent("BuildNumber")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public DateTime getBuildDate() {
        Optional<VariableNode> component = getVariableComponent("BuildDate");

        return component.map(node -> (DateTime) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public BaseDataVariableNode getBuildDateNode() {
        Optional<VariableNode> component = getVariableComponent("BuildDate");

        return component.map(node -> (BaseDataVariableNode) node).orElse(null);
    }

    @Override
    public void setBuildDate(DateTime value) {
        getVariableComponent("BuildDate")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
