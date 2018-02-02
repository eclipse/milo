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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public class ServerCapabilitiesNode extends BaseObjectNode implements ServerCapabilitiesType {
    public ServerCapabilitiesNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getServerProfileArrayNode() {
        return getPropertyNode(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    }

    public CompletableFuture<String[]> getServerProfileArray() {
        return getProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    }

    public CompletableFuture<StatusCode> setServerProfileArray(String[] value) {
        return setProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY, value);
    }

    public CompletableFuture<PropertyNode> getLocaleIdArrayNode() {
        return getPropertyNode(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    }

    public CompletableFuture<String[]> getLocaleIdArray() {
        return getProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    }

    public CompletableFuture<StatusCode> setLocaleIdArray(String[] value) {
        return setProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY, value);
    }

    public CompletableFuture<PropertyNode> getMinSupportedSampleRateNode() {
        return getPropertyNode(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    }

    public CompletableFuture<Double> getMinSupportedSampleRate() {
        return getProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    }

    public CompletableFuture<StatusCode> setMinSupportedSampleRate(Double value) {
        return setProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE, value);
    }

    public CompletableFuture<PropertyNode> getMaxBrowseContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxBrowseContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxBrowseContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyNode> getMaxQueryContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxQueryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxQueryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyNode> getMaxHistoryContinuationPointsNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    }

    public CompletableFuture<UShort> getMaxHistoryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    }

    public CompletableFuture<StatusCode> setMaxHistoryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS, value);
    }

    public CompletableFuture<PropertyNode> getSoftwareCertificatesNode() {
        return getPropertyNode(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    }

    public CompletableFuture<SignedSoftwareCertificate[]> getSoftwareCertificates() {
        return getProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    }

    public CompletableFuture<StatusCode> setSoftwareCertificates(SignedSoftwareCertificate[] value) {
        return setProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES, value);
    }

    public CompletableFuture<PropertyNode> getMaxArrayLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxArrayLength() {
        return getProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxArrayLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH, value);
    }

    public CompletableFuture<PropertyNode> getMaxStringLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_STRING_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_STRING_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_STRING_LENGTH, value);
    }

    public CompletableFuture<PropertyNode> getMaxByteStringLengthNode() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    }

    public CompletableFuture<UInteger> getMaxByteStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    }

    public CompletableFuture<StatusCode> setMaxByteStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH, value);
    }

    public CompletableFuture<OperationLimitsNode> getOperationLimitsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "OperationLimits").thenApply(OperationLimitsNode.class::cast);
    }

    public CompletableFuture<FolderNode> getModellingRulesNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "ModellingRules").thenApply(FolderNode.class::cast);
    }

    public CompletableFuture<FolderNode> getAggregateFunctionsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions").thenApply(FolderNode.class::cast);
    }
}
