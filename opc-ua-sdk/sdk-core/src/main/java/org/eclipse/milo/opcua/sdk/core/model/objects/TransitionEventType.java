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

import org.eclipse.milo.opcua.sdk.core.model.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.core.model.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface TransitionEventType extends BaseEventType {

    LocalizedText getTransition();

    TransitionVariableType getTransitionNode();

    void setTransition(LocalizedText value);

    LocalizedText getFromState();

    StateVariableType getFromStateNode();

    void setFromState(LocalizedText value);

    LocalizedText getToState();

    StateVariableType getToStateNode();

    void setToState(LocalizedText value);
}
