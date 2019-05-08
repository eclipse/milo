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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AggregateConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public class AggregateConfigurationTypeNode extends BaseObjectTypeNode implements AggregateConfigurationType {
    public AggregateConfigurationTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getTreatUncertainAsBadNode() {
        return getPropertyNode(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD);
    }

    public CompletableFuture<Boolean> getTreatUncertainAsBad() {
        return getProperty(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD);
    }

    public CompletableFuture<StatusCode> setTreatUncertainAsBad(Boolean value) {
        return setProperty(AggregateConfigurationType.TREAT_UNCERTAIN_AS_BAD, value);
    }

    public CompletableFuture<PropertyTypeNode> getPercentDataBadNode() {
        return getPropertyNode(AggregateConfigurationType.PERCENT_DATA_BAD);
    }

    public CompletableFuture<UByte> getPercentDataBad() {
        return getProperty(AggregateConfigurationType.PERCENT_DATA_BAD);
    }

    public CompletableFuture<StatusCode> setPercentDataBad(UByte value) {
        return setProperty(AggregateConfigurationType.PERCENT_DATA_BAD, value);
    }

    public CompletableFuture<PropertyTypeNode> getPercentDataGoodNode() {
        return getPropertyNode(AggregateConfigurationType.PERCENT_DATA_GOOD);
    }

    public CompletableFuture<UByte> getPercentDataGood() {
        return getProperty(AggregateConfigurationType.PERCENT_DATA_GOOD);
    }

    public CompletableFuture<StatusCode> setPercentDataGood(UByte value) {
        return setProperty(AggregateConfigurationType.PERCENT_DATA_GOOD, value);
    }

    public CompletableFuture<PropertyTypeNode> getUseSlopedExtrapolationNode() {
        return getPropertyNode(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION);
    }

    public CompletableFuture<Boolean> getUseSlopedExtrapolation() {
        return getProperty(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION);
    }

    public CompletableFuture<StatusCode> setUseSlopedExtrapolation(Boolean value) {
        return setProperty(AggregateConfigurationType.USE_SLOPED_EXTRAPOLATION, value);
    }
}
