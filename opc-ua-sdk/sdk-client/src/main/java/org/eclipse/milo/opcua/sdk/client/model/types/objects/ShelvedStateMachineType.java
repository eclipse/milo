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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface ShelvedStateMachineType extends FiniteStateMachineType {

    Property<Double> UNSHELVE_TIME = new BasicProperty<>(
        QualifiedName.parse("0:UnshelveTime"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );


    CompletableFuture<? extends PropertyType> unshelveTime();

    CompletableFuture<Double> getUnshelveTime();

    CompletableFuture<StatusCode> setUnshelveTime(Double value);

    CompletableFuture<? extends StateType> unshelved();

    CompletableFuture<? extends StateType> timedShelved();

    CompletableFuture<? extends StateType> oneShotShelved();

    CompletableFuture<? extends TransitionType> unshelvedToTimedShelved();

    CompletableFuture<? extends TransitionType> unshelvedToOneShotShelved();

    CompletableFuture<? extends TransitionType> timedShelvedToUnshelved();

    CompletableFuture<? extends TransitionType> timedShelvedToOneShotShelved();

    CompletableFuture<? extends TransitionType> oneShotShelvedToUnshelved();

    CompletableFuture<? extends TransitionType> oneShotShelvedToTimedShelved();


}