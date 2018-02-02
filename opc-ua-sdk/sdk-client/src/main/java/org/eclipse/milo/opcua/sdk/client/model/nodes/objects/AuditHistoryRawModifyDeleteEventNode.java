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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryRawModifyDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditHistoryRawModifyDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryRawModifyDeleteEventType {
    public AuditHistoryRawModifyDeleteEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getIsDeleteModifiedNode() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);
    }

    public CompletableFuture<Boolean> getIsDeleteModified() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);
    }

    public CompletableFuture<StatusCode> setIsDeleteModified(Boolean value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED, value);
    }

    public CompletableFuture<PropertyNode> getStartTimeNode() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.START_TIME);
    }

    public CompletableFuture<DateTime> getStartTime() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.START_TIME);
    }

    public CompletableFuture<StatusCode> setStartTime(DateTime value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.START_TIME, value);
    }

    public CompletableFuture<PropertyNode> getEndTimeNode() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.END_TIME);
    }

    public CompletableFuture<DateTime> getEndTime() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.END_TIME);
    }

    public CompletableFuture<StatusCode> setEndTime(DateTime value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.END_TIME, value);
    }

    public CompletableFuture<PropertyNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES, value);
    }
}
