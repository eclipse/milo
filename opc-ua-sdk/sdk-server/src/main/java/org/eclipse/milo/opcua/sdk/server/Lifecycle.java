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

public interface Lifecycle {

    /**
     * Call to start this Lifecycle.
     * <p>
     * Subsequent invocations throw {@link IllegalStateException}.
     *
     * @throws IllegalStateException on subsequent invocations.
     */
    void startup() throws IllegalStateException;

    /**
     * Call to stop this Lifecycle.
     * <p>
     * If {@link #startup()} hasn't been called yet {@link IllegalStateException} is thrown.
     * <p>
     * Subsequent invocations have no effect.
     *
     * @throws IllegalStateException if not started yet.
     */
    void shutdown() throws IllegalStateException;

    /**
     * @return {@code true} after {@link #startup()} is called and before {@link #shutdown()} is called.
     */
    boolean isRunning();

}
