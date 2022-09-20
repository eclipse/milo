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

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import com.google.common.base.Preconditions;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.client.security.ClientCertificateValidator;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcTcpTransportConfigBuilder {

    private EndpointDescription endpoint;
    private KeyPair keyPair;
    private X509Certificate certificate;
    private X509Certificate[] certificateChain;
    private ClientCertificateValidator certificateValidator = new ClientCertificateValidator.InsecureValidator();

    private UInteger connectTimeout = uint(5_000);
    private UInteger acknowledgeTimeout = uint(5_000);
    private UInteger requestTimeout = uint(60_000);
    private UInteger channelLifetime = uint(60 * 60 * 1000);

    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;

    private ExecutorService executor;
    private ScheduledExecutorService scheduledExecutor;
    private NioEventLoopGroup eventLoop;
    private HashedWheelTimer wheelTimer;

    public OpcTcpTransportConfigBuilder setEndpoint(EndpointDescription endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public OpcTcpTransportConfigBuilder setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public OpcTcpTransportConfigBuilder setCertificate(X509Certificate certificate) {
        this.certificate = certificate;
        return this;
    }

    public OpcTcpTransportConfigBuilder setCertificateChain(X509Certificate[] certificateChain) {
        this.certificateChain = certificateChain;
        return this;
    }

    public OpcTcpTransportConfigBuilder setCertificateValidator(ClientCertificateValidator certificateValidator) {
        this.certificateValidator = certificateValidator;
        return this;
    }

    public OpcTcpTransportConfigBuilder setConnectTimeout(UInteger connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public OpcTcpTransportConfigBuilder setAcknowledgeTimeout(UInteger acknowledgeTimeout) {
        this.acknowledgeTimeout = acknowledgeTimeout;
        return this;
    }

    public OpcTcpTransportConfigBuilder setRequestTimeout(UInteger requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public OpcTcpTransportConfigBuilder setChannelLifetime(UInteger channelLifetime) {
        this.channelLifetime = channelLifetime;
        return this;
    }

    public OpcTcpTransportConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        this.encodingLimits = encodingLimits;
        return this;
    }

    public OpcTcpTransportConfigBuilder setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public OpcTcpTransportConfigBuilder setScheduledExecutor(ScheduledExecutorService scheduledExecutor) {
        this.scheduledExecutor = scheduledExecutor;
        return this;
    }

    public OpcTcpTransportConfigBuilder setEventLoop(NioEventLoopGroup eventLoop) {
        this.eventLoop = eventLoop;
        return this;
    }

    public OpcTcpTransportConfigBuilder setWheelTimer(HashedWheelTimer wheelTimer) {
        this.wheelTimer = wheelTimer;
        return this;
    }

    public OpcTcpTransportConfig build() {
        Preconditions.checkNotNull(endpoint, "endpoint must be non-null");

        if (certificate == null && certificateChain != null) {
            certificate = certificateChain[0];
        }
        if (certificate != null && certificateChain == null) {
            certificateChain = new X509Certificate[]{certificate};
        }
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

        return new OpcTcpTransportConfigImpl(
            endpoint,
            keyPair,
            certificate,
            certificateChain,
            certificateValidator,
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

    static class OpcTcpTransportConfigImpl implements OpcTcpTransportConfig {

        private final EndpointDescription endpoint;
        private final @Nullable KeyPair keyPair;
        private final @Nullable X509Certificate certificate;
        private final @Nullable X509Certificate[] certificateChain;
        private final ClientCertificateValidator certificateValidator;
        private final UInteger connectTimeout;
        private final UInteger acknowledgeTimeout;
        private final UInteger requestTimeout;
        private final UInteger channelLifetime;
        private final EncodingLimits encodingLimits;
        private final ExecutorService executor;
        private final ScheduledExecutorService scheduledExecutor;
        private final NioEventLoopGroup eventLoop;
        private final HashedWheelTimer wheelTimer;

        public OpcTcpTransportConfigImpl(
            EndpointDescription endpoint,
            @Nullable KeyPair keyPair,
            @Nullable X509Certificate certificate,
            @Nullable X509Certificate[] certificateChain,
            ClientCertificateValidator certificateValidator,
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

            this.endpoint = endpoint;
            this.keyPair = keyPair;
            this.certificate = certificate;
            this.certificateChain = certificateChain;
            this.certificateValidator = certificateValidator;
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
        public EndpointDescription getEndpoint() {
            return endpoint;
        }

        @Override
        public Optional<KeyPair> getKeyPair() {
            return Optional.of(keyPair);
        }

        @Override
        public Optional<X509Certificate> getCertificate() {
            return Optional.ofNullable(certificate);
        }

        @Override
        public Optional<X509Certificate[]> getCertificateChain() {
            return Optional.ofNullable(certificateChain);
        }

        @Override
        public ClientCertificateValidator getCertificateValidator() {
            return certificateValidator;
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
