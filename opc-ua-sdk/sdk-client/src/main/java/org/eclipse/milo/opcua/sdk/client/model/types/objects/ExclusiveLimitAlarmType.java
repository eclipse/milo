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

import org.eclipse.milo.opcua.sdk.client.model.types.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface ExclusiveLimitAlarmType extends LimitAlarmType {
    CompletableFuture<? extends TwoStateVariableType> getActiveStateNode();

    CompletableFuture<LocalizedText> getActiveState();

    CompletableFuture<StatusCode> setActiveState(LocalizedText value);

    CompletableFuture<? extends ExclusiveLimitStateMachineType> getLimitStateNode();
}
