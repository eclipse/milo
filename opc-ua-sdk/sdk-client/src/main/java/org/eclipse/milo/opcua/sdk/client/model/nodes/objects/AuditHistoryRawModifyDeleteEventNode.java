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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryRawModifyDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AuditHistoryRawModifyDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryRawModifyDeleteEventType {

    public AuditHistoryRawModifyDeleteEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> isDeleteModified() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getIsDeleteModified() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);
    }

    @Override
    public CompletableFuture<StatusCode> setIsDeleteModified(Boolean value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED, value);
    }

    @Override
    public CompletableFuture<PropertyNode> startTime() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.START_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getStartTime() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.START_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setStartTime(DateTime value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.START_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> endTime() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.END_TIME.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getEndTime() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.END_TIME);
    }

    @Override
    public CompletableFuture<StatusCode> setEndTime(DateTime value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.END_TIME, value);
    }

    @Override
    public CompletableFuture<PropertyNode> oldValues() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.OLD_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES, value);
    }


}