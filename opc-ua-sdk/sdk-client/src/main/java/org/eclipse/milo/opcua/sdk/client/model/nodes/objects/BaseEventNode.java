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

    @Override
    public CompletableFuture<PropertyNode> eventId() {
        return getPropertyNode(BaseEventType.EVENT_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<ByteString> getEventId() {
        return getProperty(BaseEventType.EVENT_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setEventId(ByteString value) {
        return setProperty(BaseEventType.EVENT_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> eventType() {
        return getPropertyNode(BaseEventType.EVENT_TYPE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getEventType() {
        return getProperty(BaseEventType.EVENT_TYPE);
    }

    @Override
    public CompletableFuture<StatusCode> setEventType(NodeId value) {
        return setProperty(BaseEventType.EVENT_TYPE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> sourceNode() {
        return getPropertyNode(BaseEventType.SOURCE_NODE.getBrowseName());
    }

    @Override
    public CompletableFuture<NodeId> getSourceNode() {
        return getProperty(BaseEventType.SOURCE_NODE);
    }

    @Override
    public CompletableFuture<StatusCode> setSourceNode(NodeId value) {
        return setProperty(BaseEventType.SOURCE_NODE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> sourceName() {
        return getPropertyNode(BaseEventType.SOURCE_NAME.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getSourceName() {
        return getProperty(BaseEventType.SOURCE_NAME);
    }

    @Override
    public CompletableFuture<StatusCode> setSourceName(String value) {
        return setProperty(BaseEventType.SOURCE_NAME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> time() {
        return getPropertyNode(BaseEventType.TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getTime() {
        return getProperty(BaseEventType.TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setTime(DateTime value) {
        return setProperty(BaseEventType.TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> receiveTime() {
        return getPropertyNode(BaseEventType.RECEIVE_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getReceiveTime() {
        return getProperty(BaseEventType.RECEIVE_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setReceiveTime(DateTime value) {
        return setProperty(BaseEventType.RECEIVE_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> localTime() {
        return getPropertyNode(BaseEventType.LOCAL_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<TimeZoneDataType> getLocalTime() {
        return getProperty(BaseEventType.LOCAL_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setLocalTime(TimeZoneDataType value) {
        return setProperty(BaseEventType.LOCAL_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> message() {
        return getPropertyNode(BaseEventType.MESSAGE.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getMessage() {
        return getProperty(BaseEventType.MESSAGE);
    }

    @Override
    public CompletableFuture<StatusCode> setMessage(LocalizedText value) {
        return setProperty(BaseEventType.MESSAGE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> severity() {
        return getPropertyNode(BaseEventType.SEVERITY.getBrowseName());
    }

    @Override
    public CompletableFuture<UShort> getSeverity() {
        return getProperty(BaseEventType.SEVERITY);
    }

    @Override
    public CompletableFuture<StatusCode> setSeverity(UShort value) {
        return setProperty(BaseEventType.SEVERITY, value);
    }


}