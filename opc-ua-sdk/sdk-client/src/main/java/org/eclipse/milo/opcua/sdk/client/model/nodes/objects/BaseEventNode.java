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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.BaseEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

public class BaseEventNode extends BaseObjectNode implements BaseEventType {
    public BaseEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEventIdNode() {
        return getPropertyNode(BaseEventType.EVENT_ID);
    }

    public CompletableFuture<ByteString> getEventId() {
        return getProperty(BaseEventType.EVENT_ID);
    }

    public CompletableFuture<StatusCode> setEventId(ByteString value) {
        return setProperty(BaseEventType.EVENT_ID, value);
    }

    public CompletableFuture<PropertyNode> getEventTypeNode() {
        return getPropertyNode(BaseEventType.EVENT_TYPE);
    }

    public CompletableFuture<NodeId> getEventType() {
        return getProperty(BaseEventType.EVENT_TYPE);
    }

    public CompletableFuture<StatusCode> setEventType(NodeId value) {
        return setProperty(BaseEventType.EVENT_TYPE, value);
    }

    public CompletableFuture<PropertyNode> getSourceNodeNode() {
        return getPropertyNode(BaseEventType.SOURCE_NODE);
    }

    public CompletableFuture<NodeId> getSourceNode() {
        return getProperty(BaseEventType.SOURCE_NODE);
    }

    public CompletableFuture<StatusCode> setSourceNode(NodeId value) {
        return setProperty(BaseEventType.SOURCE_NODE, value);
    }

    public CompletableFuture<PropertyNode> getSourceNameNode() {
        return getPropertyNode(BaseEventType.SOURCE_NAME);
    }

    public CompletableFuture<String> getSourceName() {
        return getProperty(BaseEventType.SOURCE_NAME);
    }

    public CompletableFuture<StatusCode> setSourceName(String value) {
        return setProperty(BaseEventType.SOURCE_NAME, value);
    }

    public CompletableFuture<PropertyNode> getTimeNode() {
        return getPropertyNode(BaseEventType.TIME);
    }

    public CompletableFuture<DateTime> getTime() {
        return getProperty(BaseEventType.TIME);
    }

    public CompletableFuture<StatusCode> setTime(DateTime value) {
        return setProperty(BaseEventType.TIME, value);
    }

    public CompletableFuture<PropertyNode> getReceiveTimeNode() {
        return getPropertyNode(BaseEventType.RECEIVE_TIME);
    }

    public CompletableFuture<DateTime> getReceiveTime() {
        return getProperty(BaseEventType.RECEIVE_TIME);
    }

    public CompletableFuture<StatusCode> setReceiveTime(DateTime value) {
        return setProperty(BaseEventType.RECEIVE_TIME, value);
    }

    public CompletableFuture<PropertyNode> getLocalTimeNode() {
        return getPropertyNode(BaseEventType.LOCAL_TIME);
    }

    public CompletableFuture<TimeZoneDataType> getLocalTime() {
        return getProperty(BaseEventType.LOCAL_TIME);
    }

    public CompletableFuture<StatusCode> setLocalTime(TimeZoneDataType value) {
        return setProperty(BaseEventType.LOCAL_TIME, value);
    }

    public CompletableFuture<PropertyNode> getMessageNode() {
        return getPropertyNode(BaseEventType.MESSAGE);
    }

    public CompletableFuture<LocalizedText> getMessage() {
        return getProperty(BaseEventType.MESSAGE);
    }

    public CompletableFuture<StatusCode> setMessage(LocalizedText value) {
        return setProperty(BaseEventType.MESSAGE, value);
    }

    public CompletableFuture<PropertyNode> getSeverityNode() {
        return getPropertyNode(BaseEventType.SEVERITY);
    }

    public CompletableFuture<UShort> getSeverity() {
        return getProperty(BaseEventType.SEVERITY);
    }

    public CompletableFuture<StatusCode> setSeverity(UShort value) {
        return setProperty(BaseEventType.SEVERITY, value);
    }
}
