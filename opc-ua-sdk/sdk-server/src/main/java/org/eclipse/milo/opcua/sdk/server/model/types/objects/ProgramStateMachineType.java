/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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

    QualifiedProperty<UInteger> MAX_RECYCLE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxRecycleCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstanceCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> AUTO_DELETE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AutoDelete",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UInteger> MAX_INSTANCE_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxInstanceCount",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> DELETABLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Deletable",
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

    PropertyType getCreatableNode();

    Boolean getCreatable();

    void setCreatable(Boolean value);

    PropertyType getMaxRecycleCountNode();

    UInteger getMaxRecycleCount();

    void setMaxRecycleCount(UInteger value);

    PropertyType getInstanceCountNode();

    UInteger getInstanceCount();

    void setInstanceCount(UInteger value);

    PropertyType getAutoDeleteNode();

    Boolean getAutoDelete();

    void setAutoDelete(Boolean value);

    PropertyType getMaxInstanceCountNode();

    UInteger getMaxInstanceCount();

    void setMaxInstanceCount(UInteger value);

    PropertyType getDeletableNode();

    Boolean getDeletable();

    void setDeletable(Boolean value);

    PropertyType getRecycleCountNode();

    Integer getRecycleCount();

    void setRecycleCount(Integer value);

    BaseObjectType getFinalResultDataNode();

    UaMethodNode getResetMethodNode();

    FiniteTransitionVariableType getLastTransitionNode();

    LocalizedText getLastTransition();

    void setLastTransition(LocalizedText value);

    UaMethodNode getSuspendMethodNode();

    StateType getHaltedNode();

    UaMethodNode getHaltMethodNode();

    StateType getSuspendedNode();

    TransitionType getHaltedToReadyNode();

    TransitionType getReadyToRunningNode();

    StateType getReadyNode();

    StateType getRunningNode();

    TransitionType getRunningToSuspendedNode();

    TransitionType getSuspendedToRunningNode();

    TransitionType getRunningToReadyNode();

    TransitionType getRunningToHaltedNode();

    FiniteStateVariableType getCurrentStateNode();

    LocalizedText getCurrentState();

    void setCurrentState(LocalizedText value);

    TransitionType getSuspendedToReadyNode();

    TransitionType getSuspendedToHaltedNode();

    TransitionType getReadyToHaltedNode();

    UaMethodNode getStartMethodNode();

    UaMethodNode getResumeMethodNode();

    ProgramDiagnosticType getProgramDiagnosticsNode();

    ProgramDiagnosticDataType getProgramDiagnostics();

    void setProgramDiagnostics(ProgramDiagnosticDataType value);
}
