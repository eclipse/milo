/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TaskQueue {

    /**
     * Default concurrency limit, 1. Submitted tasks will be executed serially.
     */
    private static final int DEFAULT_MAX_CONCURRENT_TASKS = 1;

    /**
     * Default priority ratio, 5. Elevated priority tasks will be executed 5:1 against regular
     * priority tasks when the queues are saturated.
     */
    private static final int DEFAULT_PRIORITY_RATIO = 5;

    /**
     * Default max queue size, effectively unlimited.
     */
    private static final int DEFAULT_MAX_QUEUE_SIZE = Integer.MAX_VALUE;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private int pending = 0;
    private boolean paused = false;
    private boolean shutdown = false;
    private CountDownLatch shutdownLatch;

    private final PrioritizedTaskQueue taskQueue;
    private final Lock taskQueueLock = new ReentrantLock(true);

    private final Executor executor;
    private final int maxConcurrentTasks;
    private final int maxQueueSize;

    /**
     * Create a {@link TaskQueue} that executes {@link Task}s on the provided {@link Executor}.
     * <p>
     * All other parameters are default values.
     *
     * @param executor the {@link Executor} that {@link Task}s will use to execute.
     * @see #DEFAULT_MAX_CONCURRENT_TASKS
     * @see #DEFAULT_MAX_QUEUE_SIZE
     * @see #DEFAULT_PRIORITY_RATIO
     */
    public TaskQueue(Executor executor) {
        this(executor, DEFAULT_MAX_CONCURRENT_TASKS, DEFAULT_MAX_QUEUE_SIZE, DEFAULT_PRIORITY_RATIO);
    }

    /**
     * Create a {@link TaskQueue} that executes {@link Task}s on the provided {@link Executor}.
     *
     * @param executor           the {@link Executor} that {@link Task}s will use to execute.
     * @param maxConcurrentTasks the number of concurrently executing tasks allowed. When 1, the
     *                           default value, submitted Tasks are guaranteed to execute serially.
     * @param maxQueueSize       the maximum number of Tasks that can be queued before backpressure
     *                           is applied and {@link #submit(Task)} starts returning false.
     * @param priorityRatio      ratio of {@link TaskPriority#ELEVATED} tasks to
     *                           {@link TaskPriority#REGULAR} tasks that will be executed when
     *                           elevated tasks are continually being dequeued.
     */
    public TaskQueue(Executor executor, int maxConcurrentTasks, int maxQueueSize, int priorityRatio) {
        this.executor = executor;
        this.maxConcurrentTasks = maxConcurrentTasks;
        this.maxQueueSize = maxQueueSize;

        taskQueue = new PrioritizedTaskQueue(priorityRatio);
    }

    /**
     * Submit a {@link Task} to be executed.
     *
     * @param task the {@link Task} to be executed.
     * @return {@code true} if {@code task} was queued for execution, or {@code false} if it was
     * not queued, either because this {@link TaskQueue} is shut down, or because backpressure
     * is being applied because the configured max queue size would be exceeded.
     */
    public boolean submit(Task task) {
        taskQueueLock.lock();
        try {
            if (shutdown || taskQueue.size() >= maxQueueSize) {
                return false;
            }

            taskQueue.add(task);

            maybePollAndExecute();

            return true;
        } finally {
            taskQueueLock.unlock();
        }
    }

    /**
     * Pause execution of queued {@link Task}s.
     */
    public void pause() {
        taskQueueLock.lock();
        try {
            paused = true;
        } finally {
            taskQueueLock.unlock();
        }
    }

    /**
     * Resume execution of queued {@link Task}s.
     */
    public void resume() {
        taskQueueLock.lock();
        try {
            paused = false;

            maybePollAndExecute();
        } finally {
            taskQueueLock.unlock();
        }
    }

    /**
     * Shut down this executor, optionally awaiting completion of any currently-executing tasks.
     *
     * @param awaitQuiescence {@code true} if this method should block awaiting completion of any
     *                        currently-executing tasks.
     * @return a List of {@link Task}s that were queued and will not be executed.
     * @throws InterruptedException if the current Thread is interrupted while waiting.
     */
    public List<Task> shutdown(boolean awaitQuiescence) throws InterruptedException {
        taskQueueLock.lock();
        try {
            shutdown = true;

            if (taskQueue.isEmpty() && pending == 0) {
                return List.of();
            }

            if (awaitQuiescence) {
                // wait for pending to execute, clear queue and return un-executed tasks afterwards
                shutdownLatch = new CountDownLatch(pending);
            } else {
                // don't wait for pending to execute, just clear queue and return un-executed tasks
                List<Task> tasks = taskQueue.getTasks();
                taskQueue.clear();
                return tasks;
            }
        } finally {
            taskQueueLock.unlock();
        }

        // if we made it this far we have pending tasks to await
        assert shutdownLatch != null;
        shutdownLatch.await();

        taskQueueLock.lock();
        try {
            List<Task> tasks = taskQueue.getTasks();
            taskQueue.clear();
            return tasks;
        } finally {
            taskQueueLock.unlock();
        }
    }

    public boolean isShutdown() {
        taskQueueLock.lock();
        try {
            return shutdown;
        } finally {
            taskQueueLock.unlock();
        }
    }

    private void maybePollAndExecute() {
        taskQueueLock.lock();
        try {
            if (pending < maxConcurrentTasks && !paused && !shutdown && !taskQueue.isEmpty()) {
                executor.execute(new TaskWrapper(taskQueue.poll()));
                pending++;
            }
        } finally {
            taskQueueLock.unlock();
        }
    }

    /**
     * @return a new {@link Builder} instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    private class TaskWrapper implements Runnable {

        private final Task task;

        private TaskWrapper(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                task.execute();
            } catch (Throwable throwable) {
                logger.warn("Uncaught Throwable during Task execution.", throwable);
            }

            Task inlineTask = null;

            taskQueueLock.lock();
            try {
                if (taskQueue.isEmpty() || paused || shutdown) {
                    pending--;

                    if (shutdown && shutdownLatch != null) {
                        shutdownLatch.countDown();
                    }
                } else {
                    // pending count remains the same
                    inlineTask = taskQueue.poll();
                }
            } finally {
                taskQueueLock.unlock();
            }

            if (inlineTask != null) {
                try {
                    inlineTask.execute();
                } catch (Throwable throwable) {
                    logger.warn("Uncaught Throwable during Task execution.", throwable);
                }

                taskQueueLock.lock();
                try {
                    if (taskQueue.isEmpty() || paused || shutdown) {
                        pending--;

                        if (shutdown && shutdownLatch != null) {
                            shutdownLatch.countDown();
                        }
                    } else {
                        // pending count remains the same
                        executor.execute(new TaskWrapper(taskQueue.poll()));
                    }
                } finally {
                    taskQueueLock.unlock();
                }
            }
        }

    }

    interface Task {

        /**
         * Execute this {@link Task}.
         */
        void execute();

        /**
         * Get the {@link TaskPriority} assigned to this {@link Task}.
         *
         * @return the {@link TaskPriority} assigned to this {@link Task}.
         */
        default TaskPriority getPriority() {
            return TaskPriority.REGULAR;
        }

    }

    enum TaskPriority {

        /**
         * The default priority for a task. No special treatment.
         */
        REGULAR,

        /**
         * Elevated priority for a task. When present in the queue, elevated requests are favored
         * for execution over regular priority tasks, using the configured priority ratio to
         * prevent "starving" the regular priority tasks.
         */
        ELEVATED,

        /**
         * Critical priority for a task. When present in the queue, always executed ahead of
         * elevated or regular priority tasks. No limit or ratio to prevent "starving" lower
         * priority tasks.
         */
        CRITICAL

    }

    public static final class Builder {

        private Executor executor;
        private int maxConcurrentTasks = DEFAULT_MAX_CONCURRENT_TASKS;
        private int maxQueueSize = DEFAULT_MAX_QUEUE_SIZE;
        private int priorityRatio = DEFAULT_PRIORITY_RATIO;

        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Builder setMaxConcurrentTasks(int maxConcurrentTasks) {
            this.maxConcurrentTasks = maxConcurrentTasks;
            return this;
        }

        public Builder setMaxQueueSize(int maxQueueSize) {
            this.maxQueueSize = maxQueueSize;
            return this;
        }

        public Builder setPriorityRatio(int priorityRatio) {
            this.priorityRatio = priorityRatio;
            return this;
        }

        /**
         * Build a {@link TaskQueue} using the parameters configured on this {@link Builder}.
         * <p>
         * At a minimum, the {@code executor} parameter must be set. See
         * {@link #setExecutor(Executor)}.
         *
         * @return a new {@link TaskQueue} built using the parameters configured on this
         * {@link Builder}.
         */
        public TaskQueue build() {
            if (executor == null) {
                throw new NullPointerException("executor must be non-null");
            }
            maxConcurrentTasks = Math.max(1, maxConcurrentTasks);
            maxQueueSize = Math.max(1, maxQueueSize);
            priorityRatio = Math.max(1, priorityRatio);

            return new TaskQueue(executor, maxConcurrentTasks, maxQueueSize, priorityRatio);
        }

    }

    private static final class PrioritizedTaskQueue {

        private final ArrayDeque<Task> regular = new ArrayDeque<>();
        private final ArrayDeque<Task> elevated = new ArrayDeque<>();
        private final ArrayDeque<Task> critical = new ArrayDeque<>();

        private int consecutiveElevatedExecutions = 0;
        private final int priorityRatio;

        private PrioritizedTaskQueue(int priorityRatio) {
            this.priorityRatio = priorityRatio;
        }

        void add(Task task) {
            switch (task.getPriority()) {
                case REGULAR:
                    regular.add(task);
                    break;
                case ELEVATED:
                    elevated.add(task);
                    break;
                case CRITICAL:
                    critical.add(task);
                    break;
            }
        }

        boolean isEmpty() {
            return regular.isEmpty() && elevated.isEmpty() && critical.isEmpty();
        }

        Task poll() {
            if (!critical.isEmpty()) {
                return critical.poll();
            } else if (consecutiveElevatedExecutions >= priorityRatio) {
                if (!regular.isEmpty()) {
                    consecutiveElevatedExecutions = 0;
                    return regular.poll();
                } else if (!elevated.isEmpty()) {
                    consecutiveElevatedExecutions++;
                    return elevated.poll();
                } else {
                    return null;
                }
            } else {
                if (!elevated.isEmpty()) {
                    consecutiveElevatedExecutions++;
                    return elevated.poll();
                } else if (!regular.isEmpty()) {
                    consecutiveElevatedExecutions = 0;
                    return regular.poll();
                } else {
                    return null;
                }
            }
        }

        List<Task> getTasks() {
            var tasks = new ArrayList<Task>();
            tasks.addAll(critical);
            tasks.addAll(elevated);
            tasks.addAll(regular);
            return tasks;
        }

        void clear() {
            critical.clear();
            elevated.clear();
            regular.clear();
        }

        int size() {
            return critical.size() + elevated.size() + regular.size();
        }

    }

}
