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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;


public class ServerCapabilitiesNode extends BaseObjectNode implements ServerCapabilitiesType {

    public ServerCapabilitiesNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> serverProfileArray() {
        return getPropertyNode(ServerCapabilitiesType.SERVER_PROFILE_ARRAY.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getServerProfileArray() {
        return getProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    }

    @Override
    public CompletableFuture<StatusCode> setServerProfileArray(String[] value) {
        return setProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY, value);
    }

    @Override
    public CompletableFuture<PropertyNode> localeIdArray() {
        return getPropertyNode(ServerCapabilitiesType.LOCALE_ID_ARRAY.getBrowseName());
    }

    @Override
    public CompletableFuture<String[]> getLocaleIdArray() {
        return getProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    }

    @Override
    public CompletableFuture<StatusCode> setLocaleIdArray(String[] value) {
        return setProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY, value);
    }

    @Override
    public CompletableFuture<PropertyNode> minSupportedSampleRate() {
        return getPropertyNode(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getMinSupportedSampleRate() {
        return getProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    }

    @Override
    public CompletableFuture<StatusCode> setMinSupportedSampleRate(Double value) {
        return setProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxBrowseContinuationPoints() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS.getBrowseName());
    }

    @Override
    public CompletableFuture<UShort> getMaxBrowseContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxBrowseContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxQueryContinuationPoints() {
        return getPropertyNode(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS.getBrowseName());
    }

    @Override
    public CompletableFuture<UShort> getMaxQueryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxQueryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxHistoryContinuationPoints() {
        return getPropertyNode(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS.getBrowseName());
    }

    @Override
    public CompletableFuture<UShort> getMaxHistoryContinuationPoints() {
        return getProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxHistoryContinuationPoints(UShort value) {
        return setProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> softwareCertificates() {
        return getPropertyNode(ServerCapabilitiesType.SOFTWARE_CERTIFICATES.getBrowseName());
    }

    @Override
    public CompletableFuture<SignedSoftwareCertificate[]> getSoftwareCertificates() {
        return getProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    }

    @Override
    public CompletableFuture<StatusCode> setSoftwareCertificates(SignedSoftwareCertificate[] value) {
        return setProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxArrayLength() {
        return getPropertyNode(ServerCapabilitiesType.MAX_ARRAY_LENGTH.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxArrayLength() {
        return getProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxArrayLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxStringLength() {
        return getPropertyNode(ServerCapabilitiesType.MAX_STRING_LENGTH.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_STRING_LENGTH);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_STRING_LENGTH, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxByteStringLength() {
        return getPropertyNode(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH.getBrowseName());
    }

    @Override
    public CompletableFuture<UInteger> getMaxByteStringLength() {
        return getProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxByteStringLength(UInteger value) {
        return setProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH, value);
    }


    @Override
    public CompletableFuture<OperationLimitsNode> operationLimits() {
        return getObjectComponent(QualifiedName.parse("0:OperationLimits"))
            .thenApply(OperationLimitsNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderNode> modellingRules() {
        return getObjectComponent(QualifiedName.parse("0:ModellingRules"))
            .thenApply(FolderNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderNode> aggregateFunctions() {
        return getObjectComponent(QualifiedName.parse("0:AggregateFunctions"))
            .thenApply(FolderNode.class::cast);
    }

}