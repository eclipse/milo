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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface ShelvedStateMachineType extends FiniteStateMachineType {
    QualifiedProperty<Double> UNSHELVE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UnshelveTime",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    CompletableFuture<? extends PropertyType> getUnshelveTimeNode();

    CompletableFuture<Double> getUnshelveTime();

    CompletableFuture<StatusCode> setUnshelveTime(Double value);

    CompletableFuture<? extends StateType> getUnshelvedNode();

    CompletableFuture<? extends StateType> getTimedShelvedNode();

    CompletableFuture<? extends StateType> getOneShotShelvedNode();

    CompletableFuture<? extends TransitionType> getUnshelvedToTimedShelvedNode();

    CompletableFuture<? extends TransitionType> getUnshelvedToOneShotShelvedNode();

    CompletableFuture<? extends TransitionType> getTimedShelvedToUnshelvedNode();

    CompletableFuture<? extends TransitionType> getTimedShelvedToOneShotShelvedNode();

    CompletableFuture<? extends TransitionType> getOneShotShelvedToUnshelvedNode();

    CompletableFuture<? extends TransitionType> getOneShotShelvedToTimedShelvedNode();
}
