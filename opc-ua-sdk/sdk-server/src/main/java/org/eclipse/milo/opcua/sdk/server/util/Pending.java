/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Pending<I, O> {

    /**
     * @return The input parameter to the pending operation.
     */
    I getInput();

    /**
     * @return The {@link CompletableFuture} to complete when the result is ready.
     */
    CompletableFuture<O> getOutputFuture();

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

            for (int i = 0; i < pending.size(); i++) {
                pending.get(i).getOutputFuture().complete(results.get(i));
            }
        });

        return future;
    }

}
