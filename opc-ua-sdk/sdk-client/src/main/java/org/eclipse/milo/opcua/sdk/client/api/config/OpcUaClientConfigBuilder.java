/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfigBuilder;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaClientConfigBuilder extends UaStackClientConfigBuilder {

    private LocalizedText applicationName = LocalizedText.english("client application name not configured");
    private String applicationUri = "client application uri not configured";
    private String productUri = "client product uri not configured";

    private Supplier<String> sessionName;
    private String[] sessionLocaleIds = new String[0];
    private UInteger sessionTimeout = uint(120000);

    private IdentityProvider identityProvider = new AnonymousProvider();

    private UInteger maxResponseMessageSize = uint(0);
    private UInteger maxPendingPublishRequests = uint(UInteger.MAX_VALUE);

    private BsdParser bsdParser = new GenericBsdParser();

    private UInteger keepAliveFailuresAllowed = uint(1);
    private UInteger keepAliveInterval = uint(5000);
    private UInteger keepAliveTimeout = uint(5000);

    public OpcUaClientConfigBuilder setApplicationName(LocalizedText applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public OpcUaClientConfigBuilder setApplicationUri(String applicationUri) {
        this.applicationUri = applicationUri;
        return this;
    }

    public OpcUaClientConfigBuilder setProductUri(String productUri) {
        this.productUri = productUri;
        return this;
    }

    public OpcUaClientConfigBuilder setSessionName(Supplier<String> sessionName) {
        this.sessionName = sessionName;
        return this;
    }

    public OpcUaClientConfigBuilder setSessionLocaleIds(String[] sessionLocaleIds) {
        this.sessionLocaleIds = sessionLocaleIds;
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

    public OpcUaClientConfigBuilder setIdentityProvider(IdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
        return this;
    }

    public OpcUaClientConfigBuilder setBsdParser(BsdParser bsdParser) {
        this.bsdParser = bsdParser;
        return this;
    }

    public OpcUaClientConfigBuilder setKeepAliveFailuresAllowed(UInteger keepAliveFailuresAllowed) {
        this.keepAliveFailuresAllowed = keepAliveFailuresAllowed;
        return this;
    }

    public OpcUaClientConfigBuilder setKeepAliveInterval(UInteger keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
        return this;
    }

    public OpcUaClientConfigBuilder setKeepAliveTimeout(UInteger keepAliveTimeout) {
        this.keepAliveTimeout = keepAliveTimeout;
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
    public OpcUaClientConfigBuilder setMessageLimits(MessageLimits messageLimits) {
        super.setMessageLimits(messageLimits);
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
    public OpcUaClientConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        super.setEncodingLimits(encodingLimits);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setConnectTimeout(UInteger connectTimeout) {
        super.setConnectTimeout(connectTimeout);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setAcknowledgeTimeout(UInteger acknowledgeTimeout) {
        super.setAcknowledgeTimeout(acknowledgeTimeout);
        return this;
    }

    @Override
    public OpcUaClientConfigBuilder setRequestTimeout(UInteger requestTimeout) {
        super.setRequestTimeout(requestTimeout);
        return this;
    }

    public OpcUaClientConfig build() {
        UaStackClientConfig stackClientConfig = super.build();

        if (sessionName == null) {
            sessionName = () -> String.format("UaSession:%s:%s",
                applicationName.getText(),
                System.currentTimeMillis()
            );
        }

        return new OpcUaClientConfigImpl(
            stackClientConfig,
            applicationName,
            applicationUri,
            productUri,
            sessionName,
            sessionLocaleIds,
            sessionTimeout,
            maxResponseMessageSize,
            maxPendingPublishRequests,
            identityProvider,
            bsdParser,
            keepAliveFailuresAllowed,
            keepAliveInterval,
            keepAliveTimeout
        );
    }

    static class OpcUaClientConfigImpl implements OpcUaClientConfig {

        private final UaStackClientConfig stackClientConfig;
        private final LocalizedText applicationName;
        private final String applicationUri;
        private final String productUri;
        private final Supplier<String> sessionName;
        private final String[] sessionLocaleIds;
        private final UInteger sessionTimeout;
        private final UInteger maxResponseMessageSize;
        private final UInteger maxPendingPublishRequests;
        private final IdentityProvider identityProvider;
        private final BsdParser bsdParser;
        private final UInteger keepAliveFailuresAllowed;
        private final UInteger keepAliveInterval;
        private final UInteger keepAliveTimeout;

        OpcUaClientConfigImpl(
            UaStackClientConfig stackClientConfig,
            LocalizedText applicationName,
            String applicationUri,
            String productUri,
            Supplier<String> sessionName,
            String[] sessionLocaleIds,
            UInteger sessionTimeout,
            UInteger maxResponseMessageSize,
            UInteger maxPendingPublishRequests,
            IdentityProvider identityProvider,
            BsdParser bsdParser,
            UInteger keepAliveFailuresAllowed,
            UInteger keepAliveInterval,
            UInteger keepAliveTimeout) {

            this.stackClientConfig = stackClientConfig;
            this.applicationName = applicationName;
            this.applicationUri = applicationUri;
            this.productUri = productUri;
            this.sessionName = sessionName;
            this.sessionLocaleIds = sessionLocaleIds;
            this.sessionTimeout = sessionTimeout;
            this.maxResponseMessageSize = maxResponseMessageSize;
            this.maxPendingPublishRequests = maxPendingPublishRequests;
            this.identityProvider = identityProvider;
            this.bsdParser = bsdParser;
            this.keepAliveFailuresAllowed = keepAliveFailuresAllowed;
            this.keepAliveInterval = keepAliveInterval;
            this.keepAliveTimeout = keepAliveTimeout;
        }

        @Override
        public LocalizedText getApplicationName() {
            return applicationName;
        }

        @Override
        public String getApplicationUri() {
            return applicationUri;
        }

        @Override
        public String getProductUri() {
            return productUri;
        }

        @Override
        public Supplier<String> getSessionName() {
            return sessionName;
        }

        @Override
        public String[] getSessionLocaleIds() {
            return sessionLocaleIds;
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
        public IdentityProvider getIdentityProvider() {
            return identityProvider;
        }

        @Override
        public BsdParser getBsdParser() {
            return bsdParser;
        }

        @Override
        public UInteger getKeepAliveFailuresAllowed() {
            return keepAliveFailuresAllowed;
        }

        @Override
        public UInteger getKeepAliveInterval() {
            return keepAliveInterval;
        }

        @Override
        public UInteger getKeepAliveTimeout() {
            return keepAliveTimeout;
        }

        @Override
        public EndpointDescription getEndpoint() {
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
        public MessageLimits getMessageLimits() {
            return stackClientConfig.getMessageLimits();
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return stackClientConfig.getEncodingLimits();
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
        public UInteger getConnectTimeout() {
            return stackClientConfig.getConnectTimeout();
        }

        @Override
        public UInteger getAcknowledgeTimeout() {
            return stackClientConfig.getAcknowledgeTimeout();
        }

        @Override
        public UInteger getRequestTimeout() {
            return stackClientConfig.getRequestTimeout();
        }

    }

}
