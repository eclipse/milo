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

public interface ExclusiveLimitStateMachineType extends FiniteStateMachineType {
    CompletableFuture<? extends StateType> getHighHighNode();

    CompletableFuture<? extends StateType> getHighNode();

    CompletableFuture<? extends StateType> getLowNode();

    CompletableFuture<? extends StateType> getLowLowNode();

    CompletableFuture<? extends TransitionType> getLowLowToLowNode();

    CompletableFuture<? extends TransitionType> getLowToLowLowNode();

    CompletableFuture<? extends TransitionType> getHighHighToHighNode();

    CompletableFuture<? extends TransitionType> getHighToHighHighNode();
}
