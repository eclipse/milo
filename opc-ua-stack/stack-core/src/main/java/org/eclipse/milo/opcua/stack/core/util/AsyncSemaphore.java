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

package org.eclipse.milo.opcua.stack.core.util;

import java.util.ArrayDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncSemaphore {

    private final ArrayDeque<CompletableFuture<SemaphorePermit>> waitQueue = new ArrayDeque<>();

    private final AtomicInteger availablePermits;

    public AsyncSemaphore(int initialPermits) {
        availablePermits = new AtomicInteger(initialPermits);
    }

    public CompletableFuture<SemaphorePermit> acquire() {
        CompletableFuture<SemaphorePermit> f = new CompletableFuture<>();

        boolean permitAvailable = false;

        synchronized (this) {
            if (availablePermits.get() > 0) {
                availablePermits.decrementAndGet();

                permitAvailable = true;
            } else {
                waitQueue.addLast(f);
            }
        }

        if (permitAvailable) {
            f.complete(new PermitImpl());
        }

        return f;
    }

    public interface SemaphorePermit {

        /**
         * Releases this semaphore permit.
         */
        void release();

    }

    private final class PermitImpl implements SemaphorePermit {
        @Override
        public void release() {
            CompletableFuture<SemaphorePermit> next;

            synchronized (AsyncSemaphore.this) {
                next = waitQueue.pollFirst();
                if (next == null) availablePermits.incrementAndGet();
            }

            if (next != null) next.complete(new PermitImpl());
        }
    }

}
