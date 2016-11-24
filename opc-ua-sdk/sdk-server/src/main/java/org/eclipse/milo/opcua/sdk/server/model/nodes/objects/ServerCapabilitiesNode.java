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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ServerCapabilitiesType")
public class ServerCapabilitiesNode extends BaseObjectNode implements ServerCapabilitiesType {

    public ServerCapabilitiesNode(
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
    public String[] getServerProfileArray() {
        Optional<String[]> property = getProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServerProfileArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.SERVER_PROFILE_ARRAY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServerProfileArray(String[] value) {
        setProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY, value);
    }

    @Override
    public String[] getLocaleIdArray() {
        Optional<String[]> property = getProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLocaleIdArrayNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.LOCALE_ID_ARRAY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLocaleIdArray(String[] value) {
        setProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY, value);
    }

    @Override
    public Double getMinSupportedSampleRate() {
        Optional<Double> property = getProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMinSupportedSampleRateNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMinSupportedSampleRate(Double value) {
        setProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE, value);
    }

    @Override
    public UShort getMaxBrowseContinuationPoints() {
        Optional<UShort> property = getProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxBrowseContinuationPointsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxBrowseContinuationPoints(UShort value) {
        setProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS, value);
    }

    @Override
    public UShort getMaxQueryContinuationPoints() {
        Optional<UShort> property = getProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxQueryContinuationPointsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxQueryContinuationPoints(UShort value) {
        setProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS, value);
    }

    @Override
    public UShort getMaxHistoryContinuationPoints() {
        Optional<UShort> property = getProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxHistoryContinuationPointsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxHistoryContinuationPoints(UShort value) {
        setProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS, value);
    }

    @Override
    public SignedSoftwareCertificate[] getSoftwareCertificates() {
        Optional<SignedSoftwareCertificate[]> property = getProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSoftwareCertificatesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.SOFTWARE_CERTIFICATES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSoftwareCertificates(SignedSoftwareCertificate[] value) {
        setProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES, value);
    }

    @Override
    public UInteger getMaxArrayLength() {
        Optional<UInteger> property = getProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxArrayLengthNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_ARRAY_LENGTH.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxArrayLength(UInteger value) {
        setProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH, value);
    }

    @Override
    public UInteger getMaxStringLength() {
        Optional<UInteger> property = getProperty(ServerCapabilitiesType.MAX_STRING_LENGTH);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxStringLengthNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_STRING_LENGTH.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxStringLength(UInteger value) {
        setProperty(ServerCapabilitiesType.MAX_STRING_LENGTH, value);
    }

    @Override
    public UInteger getMaxByteStringLength() {
        Optional<UInteger> property = getProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxByteStringLengthNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxByteStringLength(UInteger value) {
        setProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH, value);
    }

    @Override
    public OperationLimitsNode getOperationLimitsNode() {
        Optional<ObjectNode> component = getObjectComponent("OperationLimits");

        return component.map(node -> (OperationLimitsNode) node).orElse(null);
    }

    @Override
    public FolderNode getModellingRulesNode() {
        Optional<ObjectNode> component = getObjectComponent("ModellingRules");

        return component.map(node -> (FolderNode) node).orElse(null);
    }

    @Override
    public FolderNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("AggregateFunctions");

        return component.map(node -> (FolderNode) node).orElse(null);
    }

}
