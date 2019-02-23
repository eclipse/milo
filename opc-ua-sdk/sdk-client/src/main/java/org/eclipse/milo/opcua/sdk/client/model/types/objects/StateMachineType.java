/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface StateMachineType extends BaseObjectType {
    CompletableFuture<? extends StateVariableType> getCurrentStateNode();

    CompletableFuture<LocalizedText> getCurrentState();

    CompletableFuture<StatusCode> setCurrentState(LocalizedText value);

    CompletableFuture<? extends TransitionVariableType> getLastTransitionNode();

    CompletableFuture<LocalizedText> getLastTransition();

    CompletableFuture<StatusCode> setLastTransition(LocalizedText value);
}
