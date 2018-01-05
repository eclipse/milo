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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface FiniteStateMachineType extends StateMachineType {
    CompletableFuture<? extends FiniteStateVariableType> getCurrentStateNode();

    CompletableFuture<LocalizedText> getCurrentState();

    CompletableFuture<StatusCode> setCurrentState(LocalizedText value);

    CompletableFuture<? extends FiniteTransitionVariableType> getLastTransitionNode();

    CompletableFuture<LocalizedText> getLastTransition();

    CompletableFuture<StatusCode> setLastTransition(LocalizedText value);
}
