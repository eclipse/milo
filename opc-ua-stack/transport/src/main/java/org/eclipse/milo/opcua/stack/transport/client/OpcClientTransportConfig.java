/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import io.netty.channel.EventLoopGroup;
import io.netty.util.HashedWheelTimer;

public interface OpcClientTransportConfig {

    /**
     * Get the {@link ExecutorService} to be used by this transport.
     *
     * @return the {@link ExecutorService} to be used by this transport.
     */
    ExecutorService getExecutor();

    /**
     * Get the {@link ScheduledExecutorService} to be used by this transport.
     *
     * @return the {@link ScheduledExecutorService} to be used by this transport.
     */
    ScheduledExecutorService getScheduledExecutor();

    /**
     * Get the {@link EventLoopGroup} to be used by this transport.
     *
     * @return the {@link EventLoopGroup} to be used by this transport.
     */
    EventLoopGroup getEventLoop();

    /**
     * Get the {@link HashedWheelTimer} to be used by this transport.
     *
     * @return the {@link HashedWheelTimer} to be used by this transport.
     */
    HashedWheelTimer getWheelTimer();

}
