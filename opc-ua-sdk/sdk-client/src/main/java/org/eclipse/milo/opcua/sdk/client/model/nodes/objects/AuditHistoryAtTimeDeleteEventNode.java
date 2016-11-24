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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditHistoryAtTimeDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AuditHistoryAtTimeDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryAtTimeDeleteEventType {

    public AuditHistoryAtTimeDeleteEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> reqTimes() {
        return getPropertyNode(AuditHistoryAtTimeDeleteEventType.REQ_TIMES.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime[]> getReqTimes() {
        return getProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
    }

    @Override
    public CompletableFuture<StatusCode> setReqTimes(DateTime[] value) {
        return setProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES, value);
    }

    @Override
    public CompletableFuture<PropertyNode> oldValues() {
        return getPropertyNode(AuditHistoryAtTimeDeleteEventType.OLD_VALUES.getBrowseName());
    }

    @Override
    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
    }

    @Override
    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES, value);
    }


}