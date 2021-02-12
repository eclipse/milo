/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.TrustListManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.server.EndpointConfiguration;
import org.eclipse.milo.opcua.stack.server.UaStackServerConfig;
import org.eclipse.milo.opcua.stack.server.UaStackServerConfigBuilder;
import org.eclipse.milo.opcua.stack.server.security.ServerCertificateValidator;

public class OpcUaServerConfigBuilder extends UaStackServerConfigBuilder {

    private IdentityValidator<?> identityValidator = AnonymousIdentityValidator.INSTANCE;

    private BuildInfo buildInfo = new BuildInfo(
        "",
        "",
        "",
        "",
        "",
        DateTime.MIN_VALUE
    );

    private OpcUaServerConfigLimits limits = new OpcUaServerConfigLimits() {};

    private ScheduledExecutorService scheduledExecutorService;

    public OpcUaServerConfigBuilder setIdentityValidator(IdentityValidator<?> identityValidator) {
        this.identityValidator = identityValidator;
        return this;
    }

    public OpcUaServerConfigBuilder setBuildInfo(BuildInfo buildInfo) {
        this.buildInfo = buildInfo;
        return this;
    }

    public OpcUaServerConfigBuilder setLimits(OpcUaServerConfigLimits limits) {
        this.limits = limits;
        return this;
    }

    public OpcUaServerConfigBuilder setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setEndpoints(Set<EndpointConfiguration> endpointConfigurations) {
        super.setEndpoints(endpointConfigurations);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setApplicationName(LocalizedText applicationName) {
        super.setApplicationName(applicationName);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setApplicationUri(String applicationUri) {
        super.setApplicationUri(applicationUri);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setProductUri(String productUri) {
        super.setProductUri(productUri);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setCertificateManager(CertificateManager certificateManager) {
        super.setCertificateManager(certificateManager);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setTrustListManager(TrustListManager trustListManager) {
        super.setTrustListManager(trustListManager);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setCertificateValidator(ServerCertificateValidator certificateValidator) {
        super.setCertificateValidator(certificateValidator);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setHttpsKeyPair(KeyPair httpsKeyPair) {
        super.setHttpsKeyPair(httpsKeyPair);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setHttpsCertificate(X509Certificate httpsCertificate) {
        super.setHttpsCertificate(httpsCertificate);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setExecutor(ExecutorService executor) {
        super.setExecutor(executor);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        super.setEncodingLimits(encodingLimits);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setMinimumSecureChannelLifetime(UInteger minimumSecureChannelLifetime) {
        super.setMinimumSecureChannelLifetime(minimumSecureChannelLifetime);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setMaximumSecureChannelLifetime(UInteger maximumSecureChannelLifetime) {
        super.setMaximumSecureChannelLifetime(maximumSecureChannelLifetime);
        return this;
    }

    public OpcUaServerConfig build() {
        UaStackServerConfig stackServerConfig = super.build();

        ScheduledExecutorService scheduledExecutorService = this.scheduledExecutorService;
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Stack.sharedScheduledExecutor();
        }

        return new OpcUaServerConfigImpl(
            stackServerConfig,
            identityValidator,
            buildInfo,
            limits,
            scheduledExecutorService
        );
    }


    public static final class OpcUaServerConfigImpl implements OpcUaServerConfig {

        private final UaStackServerConfig stackServerConfig;

        private final IdentityValidator<?> identityValidator;
        private final BuildInfo buildInfo;
        private final OpcUaServerConfigLimits limits;
        private final ScheduledExecutorService scheduledExecutorService;

        public OpcUaServerConfigImpl(
            UaStackServerConfig stackServerConfig,
            IdentityValidator<?> identityValidator,
            BuildInfo buildInfo,
            OpcUaServerConfigLimits limits,
            ScheduledExecutorService scheduledExecutorService
        ) {

            this.stackServerConfig = stackServerConfig;
            this.identityValidator = identityValidator;
            this.buildInfo = buildInfo;
            this.limits = limits;
            this.scheduledExecutorService = scheduledExecutorService;
        }

        @Override
        public IdentityValidator<?> getIdentityValidator() {
            return identityValidator;
        }

        @Override
        public BuildInfo getBuildInfo() {
            return buildInfo;
        }

        @Override
        public OpcUaServerConfigLimits getLimits() {
            return limits;
        }

        @Override
        public ScheduledExecutorService getScheduledExecutorService() {
            return scheduledExecutorService;
        }

        @Override
        public Set<EndpointConfiguration> getEndpoints() {
            return stackServerConfig.getEndpoints();
        }

        @Override
        public LocalizedText getApplicationName() {
            return stackServerConfig.getApplicationName();
        }

        @Override
        public String getApplicationUri() {
            return stackServerConfig.getApplicationUri();
        }

        @Override
        public String getProductUri() {
            return stackServerConfig.getProductUri();
        }

        @Override
        public CertificateManager getCertificateManager() {
            return stackServerConfig.getCertificateManager();
        }

        @Override
        public TrustListManager getTrustListManager() {
            return stackServerConfig.getTrustListManager();
        }

        @Override
        public ServerCertificateValidator getCertificateValidator() {
            return stackServerConfig.getCertificateValidator();
        }

        @Override
        public Optional<KeyPair> getHttpsKeyPair() {
            return stackServerConfig.getHttpsKeyPair();
        }

        @Override
        public Optional<X509Certificate> getHttpsCertificate() {
            return stackServerConfig.getHttpsCertificate();
        }

        @Override
        public ExecutorService getExecutor() {
            return stackServerConfig.getExecutor();
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return stackServerConfig.getEncodingLimits();
        }

        @Override
        public UInteger getMinimumSecureChannelLifetime() {
            return stackServerConfig.getMinimumSecureChannelLifetime();
        }

        @Override
        public UInteger getMaximumSecureChannelLifetime() {
            return stackServerConfig.getMaximumSecureChannelLifetime();
        }

    }

}
