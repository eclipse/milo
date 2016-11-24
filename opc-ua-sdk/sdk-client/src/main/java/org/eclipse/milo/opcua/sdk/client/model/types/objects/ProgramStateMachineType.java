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

import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;


public interface ProgramStateMachineType extends FiniteStateMachineType {

    Property<Boolean> CREATABLE = new BasicProperty<>(
        QualifiedName.parse("0:Creatable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> DELETABLE = new BasicProperty<>(
        QualifiedName.parse("0:Deletable"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> AUTO_DELETE = new BasicProperty<>(
        QualifiedName.parse("0:AutoDelete"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Integer> RECYCLE_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:RecycleCount"),
        NodeId.parse("ns=0;i=6"),
        -1,
        Integer.class
    );

    Property<UInteger> INSTANCE_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:InstanceCount"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_INSTANCE_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:MaxInstanceCount"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_RECYCLE_COUNT = new BasicProperty<>(
        QualifiedName.parse("0:MaxRecycleCount"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );


    CompletableFuture<? extends PropertyType> creatable();

    CompletableFuture<Boolean> getCreatable();

    CompletableFuture<StatusCode> setCreatable(Boolean value);

    CompletableFuture<? extends PropertyType> deletable();

    CompletableFuture<Boolean> getDeletable();

    CompletableFuture<StatusCode> setDeletable(Boolean value);

    CompletableFuture<? extends PropertyType> autoDelete();

    CompletableFuture<Boolean> getAutoDelete();

    CompletableFuture<StatusCode> setAutoDelete(Boolean value);

    CompletableFuture<? extends PropertyType> recycleCount();

    CompletableFuture<Integer> getRecycleCount();

    CompletableFuture<StatusCode> setRecycleCount(Integer value);

    CompletableFuture<? extends PropertyType> instanceCount();

    CompletableFuture<UInteger> getInstanceCount();

    CompletableFuture<StatusCode> setInstanceCount(UInteger value);

    CompletableFuture<? extends PropertyType> maxInstanceCount();

    CompletableFuture<UInteger> getMaxInstanceCount();

    CompletableFuture<StatusCode> setMaxInstanceCount(UInteger value);

    CompletableFuture<? extends PropertyType> maxRecycleCount();

    CompletableFuture<UInteger> getMaxRecycleCount();

    CompletableFuture<StatusCode> setMaxRecycleCount(UInteger value);

    CompletableFuture<? extends BaseObjectType> finalResultData();

    CompletableFuture<? extends StateType> ready();

    CompletableFuture<? extends StateType> running();

    CompletableFuture<? extends StateType> suspended();

    CompletableFuture<? extends StateType> halted();

    CompletableFuture<? extends TransitionType> haltedToReady();

    CompletableFuture<? extends TransitionType> readyToRunning();

    CompletableFuture<? extends TransitionType> runningToHalted();

    CompletableFuture<? extends TransitionType> runningToReady();

    CompletableFuture<? extends TransitionType> runningToSuspended();

    CompletableFuture<? extends TransitionType> suspendedToRunning();

    CompletableFuture<? extends TransitionType> suspendedToHalted();

    CompletableFuture<? extends TransitionType> suspendedToReady();

    CompletableFuture<? extends TransitionType> readyToHalted();

    CompletableFuture<? extends FiniteStateVariableType> currentState();

    CompletableFuture<LocalizedText> getCurrentState();

    CompletableFuture<StatusCode> setCurrentState(LocalizedText value);

    CompletableFuture<? extends FiniteTransitionVariableType> lastTransition();

    CompletableFuture<LocalizedText> getLastTransition();

    CompletableFuture<StatusCode> setLastTransition(LocalizedText value);

    CompletableFuture<? extends ProgramDiagnosticType> programDiagnostics();

    CompletableFuture<ProgramDiagnosticDataType> getProgramDiagnostics();

    CompletableFuture<StatusCode> setProgramDiagnostics(ProgramDiagnosticDataType value);

}