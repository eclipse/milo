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

import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ProgramDiagnosticDataType;

public interface ProgramStateMachineType extends FiniteStateMachineType {
    QualifiedProperty<Boolean> CREATABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Creatable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Deletable",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> AUTO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AutoDelete",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Integer> RECYCLE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RecycleCount",
        NodeId.parse("ns=0;i=6"),
        ValueRanks.Scalar,
        Integer.class
    );

    QualifiedProperty<UInteger> INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstanceCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxInstanceCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_RECYCLE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxRecycleCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    CompletableFuture<? extends PropertyType> getCreatableNode();

    CompletableFuture<Boolean> getCreatable();

    CompletableFuture<StatusCode> setCreatable(Boolean value);

    CompletableFuture<? extends PropertyType> getDeletableNode();

    CompletableFuture<Boolean> getDeletable();

    CompletableFuture<StatusCode> setDeletable(Boolean value);

    CompletableFuture<? extends PropertyType> getAutoDeleteNode();

    CompletableFuture<Boolean> getAutoDelete();

    CompletableFuture<StatusCode> setAutoDelete(Boolean value);

    CompletableFuture<? extends PropertyType> getRecycleCountNode();

    CompletableFuture<Integer> getRecycleCount();

    CompletableFuture<StatusCode> setRecycleCount(Integer value);

    CompletableFuture<? extends PropertyType> getInstanceCountNode();

    CompletableFuture<UInteger> getInstanceCount();

    CompletableFuture<StatusCode> setInstanceCount(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxInstanceCountNode();

    CompletableFuture<UInteger> getMaxInstanceCount();

    CompletableFuture<StatusCode> setMaxInstanceCount(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxRecycleCountNode();

    CompletableFuture<UInteger> getMaxRecycleCount();

    CompletableFuture<StatusCode> setMaxRecycleCount(UInteger value);

    CompletableFuture<? extends FiniteStateVariableType> getCurrentStateNode();

    CompletableFuture<LocalizedText> getCurrentState();

    CompletableFuture<StatusCode> setCurrentState(LocalizedText value);

    CompletableFuture<? extends FiniteTransitionVariableType> getLastTransitionNode();

    CompletableFuture<LocalizedText> getLastTransition();

    CompletableFuture<StatusCode> setLastTransition(LocalizedText value);

    CompletableFuture<? extends ProgramDiagnosticType> getProgramDiagnosticsNode();

    CompletableFuture<ProgramDiagnosticDataType> getProgramDiagnostics();

    CompletableFuture<StatusCode> setProgramDiagnostics(ProgramDiagnosticDataType value);

    CompletableFuture<? extends BaseObjectType> getFinalResultDataNode();

    CompletableFuture<? extends StateType> getReadyNode();

    CompletableFuture<? extends StateType> getRunningNode();

    CompletableFuture<? extends StateType> getSuspendedNode();

    CompletableFuture<? extends StateType> getHaltedNode();

    CompletableFuture<? extends TransitionType> getHaltedToReadyNode();

    CompletableFuture<? extends TransitionType> getReadyToRunningNode();

    CompletableFuture<? extends TransitionType> getRunningToHaltedNode();

    CompletableFuture<? extends TransitionType> getRunningToReadyNode();

    CompletableFuture<? extends TransitionType> getRunningToSuspendedNode();

    CompletableFuture<? extends TransitionType> getSuspendedToRunningNode();

    CompletableFuture<? extends TransitionType> getSuspendedToHaltedNode();

    CompletableFuture<? extends TransitionType> getSuspendedToReadyNode();

    CompletableFuture<? extends TransitionType> getReadyToHaltedNode();
}
