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

package org.eclipse.milo.opcua.stack.server.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public class UaTcpStackServerConfigBuilder {

    private String serverName = "";
    private LocalizedText applicationName = LocalizedText
        .english("server application name not configured");
    private String applicationUri = "server application uri not configured";
    private String productUri = "server product uri not configured";

    private ChannelConfig channelConfig = ChannelConfig.DEFAULT;
    private boolean strictEndpointUrlsEnabled = true;

    private CertificateManager certificateManager;
    private CertificateValidator certificateValidator;

    private ExecutorService executor;
    private List<UserTokenPolicy> userTokenPolicies = new ArrayList<>();
    private List<SignedSoftwareCertificate> softwareCertificates = new ArrayList<>();


    public UaTcpStackServerConfigBuilder setServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public UaTcpStackServerConfigBuilder setApplicationName(LocalizedText applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public UaTcpStackServerConfigBuilder setApplicationUri(String applicationUri) {
        this.applicationUri = applicationUri;
        return this;
    }

    public UaTcpStackServerConfigBuilder setProductUri(String productUri) {
        this.productUri = productUri;
        return this;
    }

    public UaTcpStackServerConfigBuilder setCertificateManager(CertificateManager certificateManager) {
        this.certificateManager = certificateManager;
        return this;
    }

    public UaTcpStackServerConfigBuilder setCertificateValidator(CertificateValidator certificateValidator) {
        this.certificateValidator = certificateValidator;
        return this;
    }

    public UaTcpStackServerConfigBuilder setUserTokenPolicies(List<UserTokenPolicy> userTokenPolicies) {
        this.userTokenPolicies = userTokenPolicies;
        return this;
    }

    public UaTcpStackServerConfigBuilder setSoftwareCertificates(List<SignedSoftwareCertificate> softwareCertificates) {
        this.softwareCertificates = softwareCertificates;
        return this;
    }

    public UaTcpStackServerConfigBuilder setExecutor(ExecutorService executor) {
        this.executor = executor;
        return this;
    }

    public UaTcpStackServerConfigBuilder setChannelConfig(ChannelConfig channelConfig) {
        this.channelConfig = channelConfig;
        return this;
    }

    public UaTcpStackServerConfigBuilder setStrictEndpointUrlsEnabled(boolean strictEndpointUrlsEnabled) {
        this.strictEndpointUrlsEnabled = strictEndpointUrlsEnabled;
        return this;
    }

    public UaTcpStackServerConfig build() {
        Preconditions.checkNotNull(certificateManager, "certificateManager must be non-null");
        Preconditions.checkNotNull(certificateValidator, "certificateValidator must be non-null");

        if (executor == null) {
            executor = Stack.sharedExecutor();
        }

        return new UaTcpStackServerConfigImpl(
            serverName,
            applicationName,
            applicationUri,
            productUri,
            channelConfig,
            strictEndpointUrlsEnabled,
            certificateManager,
            certificateValidator,
            executor,
            userTokenPolicies,
            softwareCertificates
        );
    }

    private static class UaTcpStackServerConfigImpl implements UaTcpStackServerConfig {

        private final String serverName;
        private final LocalizedText applicationName;
        private final String applicationUri;
        private final String productUri;

        private final ChannelConfig channelConfig;
        private final boolean strictEndpointUrlsEnabled;

        private final CertificateManager certificateManager;
        private final CertificateValidator certificateValidator;

        private final ExecutorService executor;
        private final List<UserTokenPolicy> userTokenPolicies;
        private final List<SignedSoftwareCertificate> softwareCertificates;

        public UaTcpStackServerConfigImpl(String serverName,
                                          LocalizedText applicationName,
                                          String applicationUri,
                                          String productUri,
                                          ChannelConfig channelConfig,
                                          boolean strictEndpointUrlsEnabled,
                                          CertificateManager certificateManager,
                                          CertificateValidator certificateValidator,
                                          ExecutorService executor,
                                          List<UserTokenPolicy> userTokenPolicies,
                                          List<SignedSoftwareCertificate> softwareCertificates) {

            this.serverName = serverName;
            this.applicationName = applicationName;
            this.applicationUri = applicationUri;
            this.productUri = productUri;
            this.channelConfig = channelConfig;
            this.strictEndpointUrlsEnabled = strictEndpointUrlsEnabled;
            this.certificateManager = certificateManager;
            this.certificateValidator = certificateValidator;
            this.executor = executor;
            this.userTokenPolicies = userTokenPolicies;
            this.softwareCertificates = softwareCertificates;
        }

        @Override
        public String getServerName() {
            return serverName;
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
        public ChannelConfig getChannelConfig() {
            return channelConfig;
        }

        @Override
        public boolean isStrictEndpointUrlsEnabled() {
            return strictEndpointUrlsEnabled;
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
        public ExecutorService getExecutor() {
            return executor;
        }

        @Override
        public List<UserTokenPolicy> getUserTokenPolicies() {
            return userTokenPolicies;
        }

        @Override
        public List<SignedSoftwareCertificate> getSoftwareCertificates() {
            return softwareCertificates;
        }

    }

}
