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
