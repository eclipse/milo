/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public class UaStackServerConfigBuilder {

    private Set<EndpointConfiguration> endpoints = new HashSet<>();

    private LocalizedText applicationName = LocalizedText
        .english("server application name not configured");

    private String applicationUri = "server application uri not configured";

    private String productUri = "server product uri not configured";

    private MessageLimits messageLimits = MessageLimits.DEFAULT;
    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;

    private CertificateManager certificateManager;
    private CertificateValidator certificateValidator;

    private KeyPair httpsKeyPair;
    private X509Certificate httpsCertificate;

    private ExecutorService executor;

    public UaStackServerConfigBuilder setEndpoints(Set<EndpointConfiguration> endpointConfigurations) {
        this.endpoints = endpointConfigurations;
        return this;
    }

    public UaStackServerConfigBuilder setApplicationName(LocalizedText applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public UaStackServerConfigBuilder setApplicationUri(String applicationUri) {
        this.applicationUri = applicationUri;
        return this;
    }

    public UaStackServerConfigBuilder setProductUri(String productUri) {
        this.productUri = productUri;
        return this;
    }

    public UaStackServerConfigBuilder setMessageLimits(MessageLimits messageLimits) {
        this.messageLimits = messageLimits;
        return this;
    }

    public UaStackServerConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        this.encodingLimits = encodingLimits;
        return this;
    }

    public UaStackServerConfigBuilder setCertificateManager(CertificateManager certificateManager) {
        this.certificateManager = certificateManager;
        return this;
    }

    public UaStackServerConfigBuilder setCertificateValidator(CertificateValidator certificateValidator) {
        this.certificateValidator = certificateValidator;
        return this;
    }

    public UaStackServerConfigBuilder setHttpsKeyPair(KeyPair httpsKeyPair) {
        this.httpsKeyPair = httpsKeyPair;
        return this;
    }

    public UaStackServerConfigBuilder setHttpsCertificate(X509Certificate httpsCertificate) {
        this.httpsCertificate = httpsCertificate;
        return this;
    }

    public UaStackServerConfigBuilder setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public UaStackServerConfig build() {
        if (executor == null) {
            executor = Stack.sharedExecutor();
        }

        return new UaStackServerConfigImpl(
            endpoints,
            applicationName,
            applicationUri,
            productUri,
            messageLimits,
            encodingLimits,
            certificateManager,
            certificateValidator,
            httpsKeyPair,
            httpsCertificate,
            executor
        );
    }


    private static class UaStackServerConfigImpl implements UaStackServerConfig {

        private final Set<EndpointConfiguration> endpointConfigurations;

        private final LocalizedText applicationName;
        private final String applicationUri;
        private final String productUri;

        private final MessageLimits messageLimits;
        private final EncodingLimits encodingLimits;

        private final CertificateManager certificateManager;
        private final CertificateValidator certificateValidator;

        private final KeyPair httpsKeyPair;
        private final X509Certificate httpsCertificate;

        private final ExecutorService executor;

        UaStackServerConfigImpl(
            Set<EndpointConfiguration> endpointConfigurations,
            LocalizedText applicationName,
            String applicationUri,
            String productUri,
            MessageLimits messageLimits,
            EncodingLimits encodingLimits,
            CertificateManager certificateManager,
            CertificateValidator certificateValidator,
            @Nullable KeyPair httpsKeyPair,
            @Nullable X509Certificate httpsCertificate,
            ExecutorService executor) {

            this.endpointConfigurations = endpointConfigurations;
            this.applicationName = applicationName;
            this.applicationUri = applicationUri;
            this.productUri = productUri;
            this.messageLimits = messageLimits;
            this.encodingLimits = encodingLimits;
            this.certificateManager = certificateManager;
            this.certificateValidator = certificateValidator;
            this.httpsKeyPair = httpsKeyPair;
            this.httpsCertificate = httpsCertificate;
            this.executor = executor;
        }

        @Override
        public Set<EndpointConfiguration> getEndpoints() {
            return endpointConfigurations;
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
        public MessageLimits getMessageLimits() {
            return messageLimits;
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return encodingLimits;
        }

        @Override
        public CertificateManager getCertificateManager() {
            return certificateManager;
        }

        @Override
        public CertificateValidator getCertificateValidator() {
            return certificateValidator;
        }

        @Override
        public Optional<KeyPair> getHttpsKeyPair() {
            return Optional.ofNullable(httpsKeyPair);
        }

        @Override
        public Optional<X509Certificate> getHttpsCertificate() {
            return Optional.ofNullable(httpsCertificate);
        }

        @Override
        public ExecutorService getExecutor() {
            return executor;
        }

    }

}
