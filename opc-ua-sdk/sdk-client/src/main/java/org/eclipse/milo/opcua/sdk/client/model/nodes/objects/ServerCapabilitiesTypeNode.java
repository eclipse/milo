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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public class ServerCapabilitiesTypeNode extends BaseObjectTypeNode implements ServerCapabilitiesType {
    public ServerCapabilitiesTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getServerProfileArrayNode() {
        return getPropertyNode(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    }

    public CompletableFuture<String[]> getServerProfileArray() {
        return getProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    }

    public CompletableFuture<StatusCode> setServerProfileArray(String[] value) {
        return setProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY, value);
    }

    public CompletableFuture<PropertyTypeNode> getLocaleIdArrayNode() {
        return getPropertyNode(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    }

    public CompletableFuture<String[]> getLocaleIdArray() {
        return getProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    }

    public CompletableFuture<StatusCode> setLocaleIdArray(String[] value) {
        return setProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY, value);
    }

    public CompletableFuture<PropertyTypeNode> getMinSupportedSampleRateNode() {
        return getPropertyNode(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    }

    public CompletableFuture<Double> getMinSupportedSampleRate() {
        return getProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    }

    public CompletableFuture<StatusCode> setMinSupportedSampleRate(Double value) {
        return setProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxBrowseContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxBrowseContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxBrowseContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxQueryContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxQueryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxQueryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxHistoryContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxHistoryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxHistoryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyTypeNode> getSoftwareCertificatesNode() {
        return getPropertyNode(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    }

    public CompletableFuture<SignedSoftwareCertificate[]> getSoftwareCertificates() {
        return getProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    }

    public CompletableFuture<StatusCode> setSoftwareCertificates(SignedSoftwareCertificate[] value) {
        return setProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxArrayLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxArrayLength() {
        return getProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxArrayLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxStringLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_STRING_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_STRING_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_STRING_LENGTH, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxByteStringLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxByteStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxByteStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH, value);
    }

    @Override
    public CompletableFuture<OperationLimitsTypeNode> getOperationLimitsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OperationLimits").thenApply(OperationLimitsTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderTypeNode> getModellingRulesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ModellingRules").thenApply(FolderTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderTypeNode> getAggregateFunctionsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions").thenApply(FolderTypeNode.class::cast);
    }
}
