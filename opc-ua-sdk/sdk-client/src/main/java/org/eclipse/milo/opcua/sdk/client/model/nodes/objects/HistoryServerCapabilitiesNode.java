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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoryServerCapabilitiesType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class HistoryServerCapabilitiesNode extends BaseObjectNode implements HistoryServerCapabilitiesType {
    public HistoryServerCapabilitiesNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getAccessHistoryDataCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
    }

    public CompletableFuture<Boolean> getAccessHistoryDataCapability() {
        return getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setAccessHistoryDataCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_DATA_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getAccessHistoryEventsCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
    }

    public CompletableFuture<Boolean> getAccessHistoryEventsCapability() {
        return getProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setAccessHistoryEventsCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.ACCESS_HISTORY_EVENTS_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getMaxReturnDataValuesNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
    }

    public CompletableFuture<UInteger> getMaxReturnDataValues() {
        return getProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES);
    }

    public CompletableFuture<StatusCode> setMaxReturnDataValues(UInteger value) {
        return setProperty(HistoryServerCapabilitiesType.MAX_RETURN_DATA_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getMaxReturnEventValuesNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
    }

    public CompletableFuture<UInteger> getMaxReturnEventValues() {
        return getProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES);
    }

    public CompletableFuture<StatusCode> setMaxReturnEventValues(UInteger value) {
        return setProperty(HistoryServerCapabilitiesType.MAX_RETURN_EVENT_VALUES, value);
    }

    public CompletableFuture<PropertyNode> getInsertDataCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
    }

    public CompletableFuture<Boolean> getInsertDataCapability() {
        return getProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setInsertDataCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.INSERT_DATA_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getReplaceDataCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
    }

    public CompletableFuture<Boolean> getReplaceDataCapability() {
        return getProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setReplaceDataCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.REPLACE_DATA_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getUpdateDataCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
    }

    public CompletableFuture<Boolean> getUpdateDataCapability() {
        return getProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setUpdateDataCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.UPDATE_DATA_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getDeleteRawCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
    }

    public CompletableFuture<Boolean> getDeleteRawCapability() {
        return getProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setDeleteRawCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.DELETE_RAW_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getDeleteAtTimeCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
    }

    public CompletableFuture<Boolean> getDeleteAtTimeCapability() {
        return getProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setDeleteAtTimeCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.DELETE_AT_TIME_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getInsertEventCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
    }

    public CompletableFuture<Boolean> getInsertEventCapability() {
        return getProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setInsertEventCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.INSERT_EVENT_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getReplaceEventCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
    }

    public CompletableFuture<Boolean> getReplaceEventCapability() {
        return getProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setReplaceEventCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.REPLACE_EVENT_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getUpdateEventCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
    }

    public CompletableFuture<Boolean> getUpdateEventCapability() {
        return getProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setUpdateEventCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.UPDATE_EVENT_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getDeleteEventCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
    }

    public CompletableFuture<Boolean> getDeleteEventCapability() {
        return getProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setDeleteEventCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.DELETE_EVENT_CAPABILITY, value);
    }

    public CompletableFuture<PropertyNode> getInsertAnnotationCapabilityNode() {
        return getPropertyNode(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
    }

    public CompletableFuture<Boolean> getInsertAnnotationCapability() {
        return getProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY);
    }

    public CompletableFuture<StatusCode> setInsertAnnotationCapability(Boolean value) {
        return setProperty(HistoryServerCapabilitiesType.INSERT_ANNOTATION_CAPABILITY, value);
    }

    public CompletableFuture<FolderNode> getAggregateFunctionsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions").thenApply(FolderNode.class::cast);
    }
}
