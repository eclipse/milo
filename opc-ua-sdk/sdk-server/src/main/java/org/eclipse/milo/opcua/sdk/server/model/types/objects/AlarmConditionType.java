/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.AudioVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.2</a>
 */
public interface AlarmConditionType extends AcknowledgeableConditionType {
    QualifiedProperty<NodeId> INPUT_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InputNode",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<Boolean> SUPPRESSED_OR_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SuppressedOrShelved",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<Double> MAX_TIME_SHELVED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTimeShelved",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<Boolean> AUDIBLE_ENABLED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AudibleEnabled",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=1"),
        -1,
        Boolean.class
    );

    QualifiedProperty<Double> ON_DELAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OnDelay",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<Double> OFF_DELAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OffDelay",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<Double> RE_ALARM_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReAlarmTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    NodeId getInputNode();

    void setInputNode(NodeId value);

    PropertyType getInputNodeNode();

    Boolean getSuppressedOrShelved();

    void setSuppressedOrShelved(Boolean value);

    PropertyType getSuppressedOrShelvedNode();

    Double getMaxTimeShelved();

    void setMaxTimeShelved(Double value);

    PropertyType getMaxTimeShelvedNode();

    Boolean getAudibleEnabled();

    void setAudibleEnabled(Boolean value);

    PropertyType getAudibleEnabledNode();

    Double getOnDelay();

    void setOnDelay(Double value);

    PropertyType getOnDelayNode();

    Double getOffDelay();

    void setOffDelay(Double value);

    PropertyType getOffDelayNode();

    Double getReAlarmTime();

    void setReAlarmTime(Double value);

    PropertyType getReAlarmTimeNode();

    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);

    TwoStateVariableType getSuppressedStateNode();

    LocalizedText getSuppressedState();

    void setSuppressedState(LocalizedText value);

    TwoStateVariableType getOutOfServiceStateNode();

    LocalizedText getOutOfServiceState();

    void setOutOfServiceState(LocalizedText value);

    ShelvedStateMachineType getShelvingStateNode();

    AudioVariableType getAudibleSoundNode();

    ByteString getAudibleSound();

    void setAudibleSound(ByteString value);

    TwoStateVariableType getSilenceStateNode();

    LocalizedText getSilenceState();

    void setSilenceState(LocalizedText value);

    BaseDataVariableType getFirstInGroupFlagNode();

    Boolean getFirstInGroupFlag();

    void setFirstInGroupFlag(Boolean value);

    AlarmGroupType getFirstInGroupNode();

    TwoStateVariableType getLatchedStateNode();

    LocalizedText getLatchedState();

    void setLatchedState(LocalizedText value);

    BaseDataVariableType getReAlarmRepeatCountNode();

    Short getReAlarmRepeatCount();

    void setReAlarmRepeatCount(Short value);

    MethodNode getSilenceMethodNode();

    MethodNode getSuppressMethodNode();

    MethodNode getSuppress2MethodNode();

    MethodNode getUnsuppressMethodNode();

    MethodNode getUnsuppress2MethodNode();

    MethodNode getRemoveFromServiceMethodNode();

    MethodNode getRemoveFromService2MethodNode();

    MethodNode getPlaceInServiceMethodNode();

    MethodNode getPlaceInService2MethodNode();

    MethodNode getResetMethodNode();

    MethodNode getReset2MethodNode();

    MethodNode getGetGroupMembershipsMethodNode();
}
