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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Pending<I, O> {

    /**
     * @return The {@link CompletableFuture} to complete when the result is ready.
     */
    CompletableFuture<O> getFuture();

    /**
     * @return The input parameter to the pending operation.
     */
    I getInput();

    /**
     * Builds a {@link CompletableFuture} suitable for use as a completion callback. When completed, each of the
     * provided {@link Pending}'s {@link CompletableFuture} will be completed.
     * <p>
     * It is assumed that the size of the list this future is completed with matches the number of provided {@link
     * Pending}s. A {@link RuntimeException} will be thrown otherwise.
     *
     * @param pending A list of {@link Pending} operations.
     * @param <I>     Input parameter of {@link Pending} operations.
     * @param <O>     Output parameter of {@link Pending} operations.
     * @return A {@link CompletableFuture} that, when completed, then completes each of the given {@link Pending}'s
     * {@link CompletableFuture}.
     */
    static <I, O> CompletableFuture<List<O>> callback(List<? extends Pending<I, O>> pending) {
        CompletableFuture<List<O>> future = new CompletableFuture<>();

        future.thenAccept(results -> {
            if (results.size() != pending.size()) {
                String message = String.format(
                    "result size (%s) does not match pending size (%s)",
                    results.size(), pending.size());
                throw new RuntimeException(message);
            }

            Iterator<? extends Pending<I, O>> pi = pending.iterator();
            Iterator<O> ri = results.iterator();

            while (pi.hasNext() && ri.hasNext()) {
                pi.next().getFuture().complete(ri.next());
            }
        });

        return future;
    }

}
