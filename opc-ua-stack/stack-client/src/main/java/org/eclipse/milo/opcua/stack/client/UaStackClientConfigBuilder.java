/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.application.InsecureCertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UaStackClientConfigBuilder {

    private EndpointDescription endpoint;
    private KeyPair keyPair;
    private X509Certificate certificate;
    private X509Certificate[] certificateChain;
    private CertificateValidator certificateValidator = new InsecureCertificateValidator();

    private ExecutorService executor;
    private NioEventLoopGroup eventLoop;
    private HashedWheelTimer wheelTimer;

    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;
    private UInteger connectTimeout = uint(5_000);
    private UInteger acknowledgeTimeout = uint(5_000);
    private UInteger requestTimeout = uint(60_000);
    private MessageLimits messageLimits = MessageLimits.DEFAULT;
    private UInteger channelLifetime = uint(60 * 60 * 1000);

    public UaStackClientConfigBuilder setEndpoint(EndpointDescription endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public UaStackClientConfigBuilder setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public UaStackClientConfigBuilder setCertificate(X509Certificate certificate) {
        this.certificate = certificate;
        return this;
    }

    public UaStackClientConfigBuilder setCertificateChain(X509Certificate[] certificateChain) {
        this.certificateChain = certificateChain;
        return this;
    }

    public UaStackClientConfigBuilder setCertificateValidator(CertificateValidator certificateValidator) {
        this.certificateValidator = certificateValidator;
        return this;
    }

    public UaStackClientConfigBuilder setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public UaStackClientConfigBuilder setEventLoop(NioEventLoopGroup eventLoop) {
        this.eventLoop = eventLoop;
        return this;
    }

    public UaStackClientConfigBuilder setWheelTimer(HashedWheelTimer wheelTimer) {
        this.wheelTimer = wheelTimer;
        return this;
    }

    public UaStackClientConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        this.encodingLimits = encodingLimits;
        return this;
    }

    public UaStackClientConfigBuilder setConnectTimeout(UInteger connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public UaStackClientConfigBuilder setAcknowledgeTimeout(UInteger acknowledgeTimeout) {
        this.acknowledgeTimeout = acknowledgeTimeout;
        return this;
    }

    public UaStackClientConfigBuilder setRequestTimeout(UInteger requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public UaStackClientConfigBuilder setMessageLimits(MessageLimits messageLimits) {
        this.messageLimits = messageLimits;
        return this;
    }

    public UaStackClientConfigBuilder setChannelLifetime(UInteger channelLifetime) {
        this.channelLifetime = channelLifetime;
        return this;
    }

    public UaStackClientConfig build() {
        if (executor == null) {
            executor = Stack.sharedExecutor();
        }
        if (eventLoop == null) {
            eventLoop = Stack.sharedEventLoop();
        }
        if (wheelTimer == null) {
            wheelTimer = Stack.sharedWheelTimer();
        }

        return new UaStackClientConfigImpl(
            endpoint,
            keyPair,
            certificate,
            certificateChain,
            certificateValidator,
            encodingLimits,
            executor,
            eventLoop,
            wheelTimer,
            connectTimeout,
            acknowledgeTimeout,
            requestTimeout,
            messageLimits,
            channelLifetime
        );
    }

    static class UaStackClientConfigImpl implements UaStackClientConfig {

        private final EndpointDescription endpoint;
        private final KeyPair keyPair;
        private final X509Certificate certificate;
        private final X509Certificate[] certificateChain;
        private final CertificateValidator certificateValidator;

        private final EncodingLimits encodingLimits;
        private final ExecutorService executor;
        private final NioEventLoopGroup eventLoop;
        private final HashedWheelTimer wheelTimer;
        private final UInteger connectTimeout;
        private final UInteger acknowledgeTimeout;
        private final UInteger requestTimeout;
        private final MessageLimits messageLimits;
        private final UInteger channelLifetime;

        UaStackClientConfigImpl(
            EndpointDescription endpoint,
            @Nullable KeyPair keyPair,
            @Nullable X509Certificate certificate,
            @Nullable X509Certificate[] certificateChain,
            CertificateValidator certificateValidator,
            EncodingLimits encodingLimits,
            ExecutorService executor,
            NioEventLoopGroup eventLoop,
            HashedWheelTimer wheelTimer,
            UInteger connectTimeout,
            UInteger acknowledgeTimeout,
            UInteger requestTimeout,
            MessageLimits messageLimits,
            UInteger channelLifetime) {

            this.endpoint = endpoint;
            this.keyPair = keyPair;
            this.certificate = certificate;
            this.certificateChain = certificateChain;
            this.certificateValidator = certificateValidator;
            this.encodingLimits = encodingLimits;
            this.executor = executor;
            this.eventLoop = eventLoop;
            this.wheelTimer = wheelTimer;
            this.connectTimeout = connectTimeout;
            this.acknowledgeTimeout = acknowledgeTimeout;
            this.requestTimeout = requestTimeout;
            this.messageLimits = messageLimits;
            this.channelLifetime = channelLifetime;
        }

        @Override
        public EndpointDescription getEndpoint() {
            return endpoint;
        }

        @Override
        public Optional<KeyPair> getKeyPair() {
            return Optional.ofNullable(keyPair);
        }

        @Override
        public Optional<X509Certificate> getCertificate() {
            return Optional.ofNullable(certificate);
        }

        @Override
        public Optional<X509Certificate[]> getCertificateChain() {
            if (certificateChain != null) {
                return Optional.of(certificateChain);
            } else {
                if (certificate != null) {
                    return Optional.of(new X509Certificate[]{certificate});
                } else {
                    return Optional.empty();
                }
            }
        }

        @Override
        public CertificateValidator getCertificateValidator() {
            return certificateValidator;
        }

        @Override
        public MessageLimits getMessageLimits() {
            return messageLimits;
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return encodingLimits;
        }

        @Override
        public UInteger getChannelLifetime() {
            return channelLifetime;
        }

        @Override
        public ExecutorService getExecutor() {
            return executor;
        }

        @Override
        public NioEventLoopGroup getEventLoop() {
            return eventLoop;
        }

        @Override
        public HashedWheelTimer getWheelTimer() {
            return wheelTimer;
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

    }

}
