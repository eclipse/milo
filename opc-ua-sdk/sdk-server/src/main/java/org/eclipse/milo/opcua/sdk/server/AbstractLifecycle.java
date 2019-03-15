/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractLifecycle {

    private final AtomicReference<LifecycleState> state = new AtomicReference<>(LifecycleState.NEW);

    /**
     * Call to start this thing up. The first time this is called, {@link #onStartup()} will be called. Subsequent
     * invocations will throw an {@link IllegalStateException}.
     */
    public final void startup() {
        LifecycleState previous =
            state.getAndUpdate(prev -> prev == LifecycleState.NEW ? LifecycleState.RUNNING : prev);

        if (previous == LifecycleState.NEW) {
            this.onStartup();
        } else {
            throw new IllegalStateException("cannot call startup when state=" + previous);
        }
    }

    /**
     * Call to shut this thing down. Must have called startup first for this to have an effect.
     */
    public final void shutdown() {
        LifecycleState previous =
            state.getAndUpdate(prev -> prev == LifecycleState.RUNNING ? LifecycleState.STOPPED : prev);

        if (previous == LifecycleState.RUNNING) {
            this.onShutdown();
        } else if (previous == LifecycleState.NEW) {
            throw new IllegalStateException("Cannot call shutdown, never started.");
        }
        // else if previous == STOPPED, calling shutdown() multiple times is OK, just treat as no-op
    }

    /**
     * @return true after {@link #startup()} is called and before {@link #shutdown()} is called.
     */
    public final boolean isRunning() {
        return this.state.get() == LifecycleState.RUNNING;
    }

    /**
     * Execute startup logic, if there is any.
     */
    protected abstract void onStartup();

    /**
     * Execute shutdown logic, if there is any.
     */
    protected abstract void onShutdown();

    private enum LifecycleState {
        NEW, RUNNING, STOPPED
    }

}
