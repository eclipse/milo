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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;


public interface TwoStateVariableType extends StateVariableType {

    Property<Boolean> ID = new BasicProperty<>(
        QualifiedName.parse("0:Id"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<DateTime> TRANSITION_TIME = new BasicProperty<>(
        QualifiedName.parse("0:TransitionTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DateTime> EFFECTIVE_TRANSITION_TIME = new BasicProperty<>(
        QualifiedName.parse("0:EffectiveTransitionTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<LocalizedText> TRUE_STATE = new BasicProperty<>(
        QualifiedName.parse("0:TrueState"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );

    Property<LocalizedText> FALSE_STATE = new BasicProperty<>(
        QualifiedName.parse("0:FalseState"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );


    Boolean getId();

    PropertyType getIdNode();

    void setId(Boolean value);

    DateTime getTransitionTime();

    PropertyType getTransitionTimeNode();

    void setTransitionTime(DateTime value);

    DateTime getEffectiveTransitionTime();

    PropertyType getEffectiveTransitionTimeNode();

    void setEffectiveTransitionTime(DateTime value);

    LocalizedText getTrueState();

    PropertyType getTrueStateNode();

    void setTrueState(LocalizedText value);

    LocalizedText getFalseState();

    PropertyType getFalseStateNode();

    void setFalseState(LocalizedText value);

}
