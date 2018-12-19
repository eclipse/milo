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

package org.eclipse.milo.opcua.sdk.client.api.config;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.binaryschema.GenericBsdParser;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfigBuilder;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaClientConfigBuilder extends UaTcpStackClientConfigBuilder {

    private Supplier<String> sessionName;

    private UInteger sessionTimeout = uint(120000);
    private UInteger maxResponseMessageSize = uint(0);
    private UInteger requestTimeout = uint(60000);
    private UInteger maxPendingPublishRequests = uint(UInteger.MAX_VALUE);
    private IdentityProvider identityProvider = new AnonymousProvider();
    private BsdParser bsdParser = new GenericBsdParser();
    private String[] sessionLocaleIds = new String[0];

    public OpcUaClientConfigBuilder setSessionName(Supplier<String> sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public OpcUaClientConfigBuilder setSessionTimeout(UInteger sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    public OpcUaClientConfigBuilder setMaxResponseMessageSize(UInteger maxResponseMessageSize) {
        this.maxResponseMessageSize = maxResponseMessageSize;
        return this;
    }

    public OpcUaClientConfigBuilder setMaxPendingPublishRequests(UInteger maxPendingPublishRequests) {
        this.maxPendingPublishRequests = maxPendingPublishRequests;
        return this;
    }

    public OpcUaClientConfigBuilder setRequestTimeout(UInteger requestTimeout) {
        this.requestTimeout = requestTimeout;
        return this;
    }

    public OpcUaClientConfigBuilder setIdentityProvider(IdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
        return this;
    }

    public OpcUaClientConfigBuilder setBsdParser(BsdParser bsdParser) {
        this.bsdParser = bsdParser;
        return this;
    }

    public OpcUaClientConfigBuilder setSessionLocaleIds(String[] sessionLocaleIds) {
        this.sessionLocaleIds = sessionLocaleIds;
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setEndpointUrl(String endpointUrl) {
        super.setEndpointUrl(endpointUrl);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setEndpoint(EndpointDescription endpoint) {
        super.setEndpoint(endpoint);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setKeyPair(KeyPair keyPair) {
        super.setKeyPair(keyPair);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setCertificate(X509Certificate certificate) {
        super.setCertificate(certificate);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setCertificateChain(X509Certificate[] certificateChain) {
        super.setCertificateChain(certificateChain);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setCertificateValidator(CertificateValidator certificateValidator) {
        super.setCertificateValidator(certificateValidator);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setApplicationName(LocalizedText applicationName) {
        super.setApplicationName(applicationName);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setApplicationUri(String applicationUri) {
        super.setApplicationUri(applicationUri);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setProductUri(String productUri) {
        super.setProductUri(productUri);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setChannelConfig(ChannelConfig channelConfig) {
        super.setChannelConfig(channelConfig);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setChannelLifetime(UInteger channelLifetime) {
        super.setChannelLifetime(channelLifetime);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setExecutor(ExecutorService executor) {
        super.setExecutor(executor);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setEventLoop(NioEventLoopGroup eventLoop) {
        super.setEventLoop(eventLoop);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setWheelTimer(HashedWheelTimer wheelTimer) {
        super.setWheelTimer(wheelTimer);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setAcknowledgeTimeout(UInteger acknowledgeTimeout) {
        super.setAcknowledgeTimeout(acknowledgeTimeout);
        return this;
    }

    public OpcUaClientConfig build() {
        UaTcpStackClientConfig stackClientConfig = super.build();

        if (sessionName == null) {
            sessionName = () -> String.format("UaSession:%s:%s",
                stackClientConfig.getApplicationName().getText(),
                System.currentTimeMillis());
        }

        return new OpcUaClientConfigImpl(
            stackClientConfig,
            sessionName,
            sessionTimeout,
            maxResponseMessageSize,
            maxPendingPublishRequests,
            requestTimeout,
            identityProvider,
            bsdParser,
            sessionLocaleIds
        );
    }

    public static class OpcUaClientConfigImpl implements OpcUaClientConfig {

        private final UaTcpStackClientConfig stackClientConfig;
        private final Supplier<String> sessionName;
        private final UInteger sessionTimeout;
        private final UInteger maxResponseMessageSize;
        private final UInteger maxPendingPublishRequests;
        private final UInteger requestTimeout;
        private final IdentityProvider identityProvider;
        private final BsdParser bsdParser;
        private final String[] sessionLocaleIds;

        public OpcUaClientConfigImpl(UaTcpStackClientConfig stackClientConfig,
                                     Supplier<String> sessionName,
                                     UInteger sessionTimeout,
                                     UInteger maxResponseMessageSize,
                                     UInteger maxPendingPublishRequests,
                                     UInteger requestTimeout,
                                     IdentityProvider identityProvider,
                                     BsdParser bsdParser,
                                     String[] sessionLocaleIds) {

            this.stackClientConfig = stackClientConfig;
            this.sessionName = sessionName;
            this.sessionTimeout = sessionTimeout;
            this.maxResponseMessageSize = maxResponseMessageSize;
            this.maxPendingPublishRequests = maxPendingPublishRequests;
            this.requestTimeout = requestTimeout;
            this.identityProvider = identityProvider;
            this.bsdParser = bsdParser;
            this.sessionLocaleIds = sessionLocaleIds;
        }

        @Override
        public Supplier<String> getSessionName() {
            return sessionName;
        }

        @Override
        public UInteger getSessionTimeout() {
            return sessionTimeout;
        }

        @Override
        public UInteger getMaxResponseMessageSize() {
            return maxResponseMessageSize;
        }

        @Override
        public UInteger getMaxPendingPublishRequests() {
            return maxPendingPublishRequests;
        }

        @Override
        public UInteger getRequestTimeout() {
            return requestTimeout;
        }

        @Override
        public IdentityProvider getIdentityProvider() {
            return identityProvider;
        }

        @Override
        public BsdParser getBsdParser() {
            return bsdParser;
        }
        
        @Override
        public String[] getSessionLocaleIds() {
            return sessionLocaleIds;
        }

        @Override
        public Optional<String> getEndpointUrl() {
            return stackClientConfig.getEndpointUrl();
        }

        @Override
        public Optional<EndpointDescription> getEndpoint() {
            return stackClientConfig.getEndpoint();
        }

        @Override
        public Optional<KeyPair> getKeyPair() {
            return stackClientConfig.getKeyPair();
        }

        @Override
        public Optional<X509Certificate> getCertificate() {
            return stackClientConfig.getCertificate();
        }

        @Override
        public Optional<X509Certificate[]> getCertificateChain() {
            return stackClientConfig.getCertificateChain();
        }

        @Override
        public CertificateValidator getCertificateValidator() {
            return stackClientConfig.getCertificateValidator();
        }

        @Override
        public LocalizedText getApplicationName() {
            return stackClientConfig.getApplicationName();
        }

        @Override
        public String getApplicationUri() {
            return stackClientConfig.getApplicationUri();
        }

        @Override
        public String getProductUri() {
            return stackClientConfig.getProductUri();
        }

        @Override
        public ChannelConfig getChannelConfig() {
            return stackClientConfig.getChannelConfig();
        }

        @Override
        public UInteger getChannelLifetime() {
            return stackClientConfig.getChannelLifetime();
        }

        @Override
        public ExecutorService getExecutor() {
            return stackClientConfig.getExecutor();
        }

        @Override
        public NioEventLoopGroup getEventLoop() {
            return stackClientConfig.getEventLoop();
        }

        @Override
        public HashedWheelTimer getWheelTimer() {
            return stackClientConfig.getWheelTimer();
        }

        @Override
        public UInteger getAcknowledgeTimeout() {
            return stackClientConfig.getAcknowledgeTimeout();
        }

    }

}
