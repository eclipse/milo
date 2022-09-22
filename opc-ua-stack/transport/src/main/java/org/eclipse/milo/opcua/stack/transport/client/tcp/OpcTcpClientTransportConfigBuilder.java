/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client.tcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcTcpClientTransportConfigBuilder {

    private UInteger connectTimeout = uint(5_000);
    private UInteger acknowledgeTimeout = uint(5_000);
    private UInteger requestTimeout = uint(60_000);
    private UInteger channelLifetime = uint(60 * 60 * 1000);

    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;

    private ExecutorService executor;
    private ScheduledExecutorService scheduledExecutor;
    private NioEventLoopGroup eventLoop;
    private HashedWheelTimer wheelTimer;

    public OpcTcpClientTransportConfigBuilder setConnectTimeout(UInteger connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setAcknowledgeTimeout(UInteger acknowledgeTimeout) {
        this.acknowledgeTimeout = acknowledgeTimeout;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setRequestTimeout(UInteger requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setChannelLifetime(UInteger channelLifetime) {
        this.channelLifetime = channelLifetime;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        this.encodingLimits = encodingLimits;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setScheduledExecutor(ScheduledExecutorService scheduledExecutor) {
        this.scheduledExecutor = scheduledExecutor;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setEventLoop(NioEventLoopGroup eventLoop) {
        this.eventLoop = eventLoop;
        return this;
    }

    public OpcTcpClientTransportConfigBuilder setWheelTimer(HashedWheelTimer wheelTimer) {
        this.wheelTimer = wheelTimer;
        return this;
    }

    public OpcTcpClientTransportConfig build() {
        if (executor == null) {
            executor = Stack.sharedExecutor();
        }
        if (scheduledExecutor == null) {
            scheduledExecutor = Stack.sharedScheduledExecutor();
        }
        if (eventLoop == null) {
            eventLoop = Stack.sharedEventLoop();
        }
        if (wheelTimer == null) {
            wheelTimer = Stack.sharedWheelTimer();
        }

        return new OpcTcpClientTransportConfigImpl(
            connectTimeout,
            acknowledgeTimeout,
            requestTimeout,
            channelLifetime,
            encodingLimits,
            executor,
            scheduledExecutor,
            eventLoop,
            wheelTimer
        );
    }

    static class OpcTcpClientTransportConfigImpl implements OpcTcpClientTransportConfig {

        private final UInteger connectTimeout;
        private final UInteger acknowledgeTimeout;
        private final UInteger requestTimeout;
        private final UInteger channelLifetime;
        private final EncodingLimits encodingLimits;
        private final ExecutorService executor;
        private final ScheduledExecutorService scheduledExecutor;
        private final NioEventLoopGroup eventLoop;
        private final HashedWheelTimer wheelTimer;

        public OpcTcpClientTransportConfigImpl(
            UInteger connectTimeout,
            UInteger acknowledgeTimeout,
            UInteger requestTimeout,
            UInteger channelLifetime,
            EncodingLimits encodingLimits,
            ExecutorService executor,
            ScheduledExecutorService scheduledExecutor,
            NioEventLoopGroup eventLoop,
            HashedWheelTimer wheelTimer
        ) {

            this.connectTimeout = connectTimeout;
            this.acknowledgeTimeout = acknowledgeTimeout;
            this.requestTimeout = requestTimeout;
            this.channelLifetime = channelLifetime;
            this.encodingLimits = encodingLimits;
            this.executor = executor;
            this.scheduledExecutor = scheduledExecutor;
            this.eventLoop = eventLoop;
            this.wheelTimer = wheelTimer;
        }


        @Override
        public UInteger getConnectTimeout() {
            return connectTimeout;
        }

        @Override
        public UInteger getAcknowledgeTimeout() {
            return acknowledgeTimeout;
        }

        @Override
        public UInteger getRequestTimeout() {
            return requestTimeout;
        }

        @Override
        public UInteger getChannelLifetime() {
            return channelLifetime;
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return encodingLimits;
        }

        @Override
        public ExecutorService getExecutor() {
            return executor;
        }

        @Override
        public ScheduledExecutorService getScheduledExecutor() {
            return scheduledExecutor;
        }

        @Override
        public NioEventLoopGroup getEventLoop() {
            return eventLoop;
        }

        @Override
        public HashedWheelTimer getWheelTimer() {
            return wheelTimer;
        }

    }

}
