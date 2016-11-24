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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public interface AlarmConditionType extends AcknowledgeableConditionType {

    Property<NodeId> INPUT_NODE = new BasicProperty<>(
        QualifiedName.parse("0:InputNode"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<Boolean> SUPPRESSED_OR_SHELVED = new BasicProperty<>(
        QualifiedName.parse("0:SuppressedOrShelved"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Double> MAX_TIME_SHELVED = new BasicProperty<>(
        QualifiedName.parse("0:MaxTimeShelved"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );


    CompletableFuture<? extends PropertyType> inputNode();

    CompletableFuture<NodeId> getInputNode();

    CompletableFuture<StatusCode> setInputNode(NodeId value);

    CompletableFuture<? extends PropertyType> suppressedOrShelved();

    CompletableFuture<Boolean> getSuppressedOrShelved();

    CompletableFuture<StatusCode> setSuppressedOrShelved(Boolean value);

    CompletableFuture<? extends PropertyType> maxTimeShelved();

    CompletableFuture<Double> getMaxTimeShelved();

    CompletableFuture<StatusCode> setMaxTimeShelved(Double value);

    CompletableFuture<? extends ShelvedStateMachineType> shelvingState();

    CompletableFuture<? extends TwoStateVariableType> enabledState();

    CompletableFuture<LocalizedText> getEnabledState();

    CompletableFuture<StatusCode> setEnabledState(LocalizedText value);

    CompletableFuture<? extends TwoStateVariableType> activeState();

    CompletableFuture<LocalizedText> getActiveState();

    CompletableFuture<StatusCode> setActiveState(LocalizedText value);

    CompletableFuture<? extends TwoStateVariableType> suppressedState();

    CompletableFuture<LocalizedText> getSuppressedState();

    CompletableFuture<StatusCode> setSuppressedState(LocalizedText value);

}