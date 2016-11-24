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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.LimitAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class LimitAlarmNode extends AlarmConditionNode implements LimitAlarmType {

    public LimitAlarmNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> highHighLimit() {
        return getPropertyNode(LimitAlarmType.HIGH_HIGH_LIMIT.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getHighHighLimit() {
        return getProperty(LimitAlarmType.HIGH_HIGH_LIMIT);
    }

    @Override
    public CompletableFuture<StatusCode> setHighHighLimit(Double value) {
        return setProperty(LimitAlarmType.HIGH_HIGH_LIMIT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> highLimit() {
        return getPropertyNode(LimitAlarmType.HIGH_LIMIT.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getHighLimit() {
        return getProperty(LimitAlarmType.HIGH_LIMIT);
    }

    @Override
    public CompletableFuture<StatusCode> setHighLimit(Double value) {
        return setProperty(LimitAlarmType.HIGH_LIMIT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lowLimit() {
        return getPropertyNode(LimitAlarmType.LOW_LIMIT.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getLowLimit() {
        return getProperty(LimitAlarmType.LOW_LIMIT);
    }

    @Override
    public CompletableFuture<StatusCode> setLowLimit(Double value) {
        return setProperty(LimitAlarmType.LOW_LIMIT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> lowLowLimit() {
        return getPropertyNode(LimitAlarmType.LOW_LOW_LIMIT.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getLowLowLimit() {
        return getProperty(LimitAlarmType.LOW_LOW_LIMIT);
    }

    @Override
    public CompletableFuture<StatusCode> setLowLowLimit(Double value) {
        return setProperty(LimitAlarmType.LOW_LOW_LIMIT, value);
    }


}