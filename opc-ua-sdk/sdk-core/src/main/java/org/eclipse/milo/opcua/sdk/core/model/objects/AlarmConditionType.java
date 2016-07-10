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
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

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

    NodeId getInputNode();

    PropertyType getInputNodeNode();

    void setInputNode(NodeId value);

    Boolean getSuppressedOrShelved();

    PropertyType getSuppressedOrShelvedNode();

    void setSuppressedOrShelved(Boolean value);

    Double getMaxTimeShelved();

    PropertyType getMaxTimeShelvedNode();

    void setMaxTimeShelved(Double value);

    ShelvedStateMachineType getShelvingStateNode();

    LocalizedText getEnabledState();

    TwoStateVariableType getEnabledStateNode();

    void setEnabledState(LocalizedText value);

    LocalizedText getActiveState();

    TwoStateVariableType getActiveStateNode();

    void setActiveState(LocalizedText value);

    LocalizedText getSuppressedState();

    TwoStateVariableType getSuppressedStateNode();

    void setSuppressedState(LocalizedText value);
}
