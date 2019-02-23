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

    public CompletableFuture<PropertyNode> getReqTimesNode() {
        return getPropertyNode(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
    }

    public CompletableFuture<DateTime[]> getReqTimes() {
        return getProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
    }

    public CompletableFuture<StatusCode> setReqTimes(DateTime[] value) {
        return setProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES, value);
    }

    public CompletableFuture<PropertyNode> getOldValuesNode() {
        return getPropertyNode(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<DataValue[]> getOldValues() {
        return getProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
    }

    public CompletableFuture<StatusCode> setOldValues(DataValue[] value) {
        return setProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES, value);
    }
}
