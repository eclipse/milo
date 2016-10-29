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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.core.model.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.core.model.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
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

    Boolean getCreatable();

    PropertyType getCreatableNode();

    void setCreatable(Boolean value);

    Boolean getDeletable();

    PropertyType getDeletableNode();

    void setDeletable(Boolean value);

    Boolean getAutoDelete();

    PropertyType getAutoDeleteNode();

    void setAutoDelete(Boolean value);

    Integer getRecycleCount();

    PropertyType getRecycleCountNode();

    void setRecycleCount(Integer value);

    UInteger getInstanceCount();

    PropertyType getInstanceCountNode();

    void setInstanceCount(UInteger value);

    UInteger getMaxInstanceCount();

    PropertyType getMaxInstanceCountNode();

    void setMaxInstanceCount(UInteger value);

    UInteger getMaxRecycleCount();

    PropertyType getMaxRecycleCountNode();

    void setMaxRecycleCount(UInteger value);

    BaseObjectType getFinalResultDataNode();

    StateType getReadyNode();

    StateType getRunningNode();

    StateType getSuspendedNode();

    StateType getHaltedNode();

    TransitionType getHaltedToReadyNode();

    TransitionType getReadyToRunningNode();

    TransitionType getRunningToHaltedNode();

    TransitionType getRunningToReadyNode();

    TransitionType getRunningToSuspendedNode();

    TransitionType getSuspendedToRunningNode();

    TransitionType getSuspendedToHaltedNode();

    TransitionType getSuspendedToReadyNode();

    TransitionType getReadyToHaltedNode();

    LocalizedText getCurrentState();

    FiniteStateVariableType getCurrentStateNode();

    void setCurrentState(LocalizedText value);

    LocalizedText getLastTransition();

    FiniteTransitionVariableType getLastTransitionNode();

    void setLastTransition(LocalizedText value);

    ProgramDiagnosticDataType getProgramDiagnostics();

    ProgramDiagnosticType getProgramDiagnosticsNode();

    void setProgramDiagnostics(ProgramDiagnosticDataType value);
}
