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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class LimitAlarmNode extends AlarmConditionNode implements LimitAlarmType {
    public LimitAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getHighHighLimitNode() {
        return getPropertyNode(LimitAlarmType.HIGH_HIGH_LIMIT);
    }

    public CompletableFuture<Double> getHighHighLimit() {
        return getProperty(LimitAlarmType.HIGH_HIGH_LIMIT);
    }

    public CompletableFuture<StatusCode> setHighHighLimit(Double value) {
        return setProperty(LimitAlarmType.HIGH_HIGH_LIMIT, value);
    }

    public CompletableFuture<PropertyNode> getHighLimitNode() {
        return getPropertyNode(LimitAlarmType.HIGH_LIMIT);
    }

    public CompletableFuture<Double> getHighLimit() {
        return getProperty(LimitAlarmType.HIGH_LIMIT);
    }

    public CompletableFuture<StatusCode> setHighLimit(Double value) {
        return setProperty(LimitAlarmType.HIGH_LIMIT, value);
    }

    public CompletableFuture<PropertyNode> getLowLimitNode() {
        return getPropertyNode(LimitAlarmType.LOW_LIMIT);
    }

    public CompletableFuture<Double> getLowLimit() {
        return getProperty(LimitAlarmType.LOW_LIMIT);
    }

    public CompletableFuture<StatusCode> setLowLimit(Double value) {
        return setProperty(LimitAlarmType.LOW_LIMIT, value);
    }

    public CompletableFuture<PropertyNode> getLowLowLimitNode() {
        return getPropertyNode(LimitAlarmType.LOW_LOW_LIMIT);
    }

    public CompletableFuture<Double> getLowLowLimit() {
        return getProperty(LimitAlarmType.LOW_LOW_LIMIT);
    }

    public CompletableFuture<StatusCode> setLowLowLimit(Double value) {
        return setProperty(LimitAlarmType.LOW_LOW_LIMIT, value);
    }
}
