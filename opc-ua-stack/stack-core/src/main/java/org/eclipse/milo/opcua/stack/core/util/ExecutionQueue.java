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
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Queues up submitted {@link java.lang.Runnable}s and executes them in serial on an
 * {@link java.util.concurrent.ExecutorService}.
 */
public class ExecutionQueue {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Object queueLock = new Object();
    private final ArrayDeque<Runnable> queue = new ArrayDeque<>();

    private volatile boolean pollSubmitted = false;
    private volatile boolean paused = false;

    private final ExecutorService service;

    public ExecutionQueue(ExecutorService service) {
        this.service = service;
    }

    /**
     * Submit a {@link Runnable} to be executed.
     *
     * @param runnable the {@link Runnable} to be executed.
     */
    public void submit(Runnable runnable) {
        synchronized (queueLock) {
            queue.add(runnable);

            maybeSubmitPoll();
        }
    }

    /**
     * Submit a {@link Runnable} to be executed at the head of the queue.
     *
     * @param runnable the {@link Runnable} to be executed.
     */
    public void submitToHead(Runnable runnable) {
        synchronized (queueLock) {
            queue.addFirst(runnable);

            maybeSubmitPoll();
        }
    }

    /**
     * Pause execution of queued {@link java.lang.Runnable}s.
     */
    public void pause() {
        synchronized (queueLock) {
            paused = true;
        }
    }

    /**
     * Resume execution of queued {@link java.lang.Runnable}s.
     */
    public void resume() {
        synchronized (queueLock) {
            paused = false;

            maybeSubmitPoll();
        }
    }

    private void maybeSubmitPoll() {
        synchronized (queueLock) {
            if (!pollSubmitted && !paused && !queue.isEmpty()) {
                service.submit(new PollAndExecute());
                pollSubmitted = true;
            }
        }
    }

    private class PollAndExecute implements Runnable {
        @Override
        public void run() {
            Runnable runnable;

            synchronized (queueLock) {
                runnable = queue.poll();
            }

            try {
                runnable.run();
            } catch (Throwable throwable) {
                log.warn("Uncaught Throwable during execution.", throwable);
            }

            synchronized (queueLock) {
                if (queue.isEmpty() || paused) {
                    pollSubmitted = false;
                } else {
                    // polling remains true
                    service.submit(new PollAndExecute());
                }
            }
        }
    }

}
