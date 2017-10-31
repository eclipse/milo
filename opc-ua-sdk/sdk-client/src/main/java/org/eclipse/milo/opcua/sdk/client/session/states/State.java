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

package org.eclipse.milo.opcua.sdk.client.session.states;


import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;

public interface State {

    /**
     * @return a {@link CompletableFuture} containing the {@link OpcUaSession} managed by the FSM.
     */
    CompletableFuture<OpcUaSession> getSessionFuture();

    /**
     * Called after an {@link Event} triggers an internal transition to this state.
     *
     * @param fsm   the {@link SessionFsm}.
     * @param event the triggering {@link Event}.
     */
    default void onInternalTransition(SessionFsm fsm, Event event) {}

    /**
     * Called after an {@link Event} triggers an external transition to this state.
     *
     * @param fsm   the {@link SessionFsm}.
     * @param prev  the previous {@link State}.
     * @param event the triggering {@link Event}.
     */
    default void onExternalTransition(SessionFsm fsm, State prev, Event event) {}

    /**
     * Given the current state, execute some action, then return state to transition to.
     *
     * @param fsm   the {@link SessionFsm}.
     * @param event the triggering {@link Event}.
     * @return the {@link State} to transition to.
     */
    State execute(SessionFsm fsm, Event event);

}
