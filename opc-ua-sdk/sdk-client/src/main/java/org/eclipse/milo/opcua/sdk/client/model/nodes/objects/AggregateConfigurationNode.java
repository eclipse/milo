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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AggregateConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;


public class AggregateConfigurationNode extends BaseObjectNode implements AggregateConfigurationType {

    public AggregateConfigurationNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> treatUncertainAsBad() {
        return getPropertyNode(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getTreatUncertainAsBad() {
        return getProperty(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD);
    }

    @Override
    public CompletableFuture<StatusCode> setTreatUncertainAsBad(Boolean value) {
        return setProperty(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD, value);
    }

    @Override
    public CompletableFuture<PropertyNode> percentDataBad() {
        return getPropertyNode(AggregateConfigurationType.PERCENT_DATA_BAD.getBrowseName());
    }

    @Override
    public CompletableFuture<UByte> getPercentDataBad() {
        return getProperty(AggregateConfigurationType.PERCENT_DATA_BAD);
    }

    @Override
    public CompletableFuture<StatusCode> setPercentDataBad(UByte value) {
        return setProperty(AggregateConfigurationType.PERCENT_DATA_BAD, value);
    }

    @Override
    public CompletableFuture<PropertyNode> percentDataGood() {
        return getPropertyNode(AggregateConfigurationType.PERCENT_DATA_GOOD.getBrowseName());
    }

    @Override
    public CompletableFuture<UByte> getPercentDataGood() {
        return getProperty(AggregateConfigurationType.PERCENT_DATA_GOOD);
    }

    @Override
    public CompletableFuture<StatusCode> setPercentDataGood(UByte value) {
        return setProperty(AggregateConfigurationType.PERCENT_DATA_GOOD, value);
    }

    @Override
    public CompletableFuture<PropertyNode> useSlopedExtrapolation() {
        return getPropertyNode(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getUseSlopedExtrapolation() {
        return getProperty(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION);
    }

    @Override
    public CompletableFuture<StatusCode> setUseSlopedExtrapolation(Boolean value) {
        return setProperty(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION, value);
    }


}