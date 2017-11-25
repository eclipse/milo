/*
 * Copyright (c) 2016 Kevin Herron and others
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

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfigBuilder;

import static com.google.common.collect.Lists.newArrayList;

public class OpcUaServerConfigBuilder extends UaTcpStackServerConfigBuilder {

    private int bindPort = Stack.DEFAULT_PORT;
    private List<String> bindAddresses = newArrayList("0.0.0.0");
    private List<String> endpointAddresses = newArrayList(HostnameUtil.getHostname());
    private EnumSet<SecurityPolicy> securityPolicies = EnumSet.of(SecurityPolicy.None);
    private IdentityValidator identityValidator = AnonymousIdentityValidator.INSTANCE;

    private BuildInfo buildInfo = new BuildInfo(
        "",
        "",
        "",
        "",
        "",
        DateTime.MIN_VALUE
    );

    private OpcUaServerConfigLimits limits = new OpcUaServerConfigLimits() {};


    public OpcUaServerConfigBuilder setBindPort(int bindPort) {
        this.bindPort = bindPort;
        return this;
    }

    public OpcUaServerConfigBuilder setBindAddresses(List<String> bindAddresses) {
        this.bindAddresses = bindAddresses;
        return this;
    }

    public OpcUaServerConfigBuilder setEndpointAddresses(List<String> endpointAddresses) {
        this.endpointAddresses = endpointAddresses;
        return this;
    }

    public OpcUaServerConfigBuilder setSecurityPolicies(EnumSet<SecurityPolicy> securityPolicies) {
        this.securityPolicies = securityPolicies;
        return this;
    }

    public OpcUaServerConfigBuilder setIdentityValidator(IdentityValidator identityValidator) {
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

    @Override
    public OpcUaServerConfigBuilder setServerName(String serverName) {
        super.setServerName(serverName);
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
    public OpcUaServerConfigBuilder setCertificateValidator(CertificateValidator certificateValidator) {
        super.setCertificateValidator(certificateValidator);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setUserTokenPolicies(List<UserTokenPolicy> userTokenPolicies) {
        super.setUserTokenPolicies(userTokenPolicies);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setSoftwareCertificates(List<SignedSoftwareCertificate> softwareCertificates) {
        super.setSoftwareCertificates(softwareCertificates);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setExecutor(ExecutorService executor) {
        super.setExecutor(executor);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setChannelConfig(ChannelConfig channelConfig) {
        super.setChannelConfig(channelConfig);
        return this;
    }

    @Override
    public OpcUaServerConfigBuilder setStrictEndpointUrlsEnabled(boolean strictEndpointUrlsEnforced) {
        super.setStrictEndpointUrlsEnabled(strictEndpointUrlsEnforced);
        return this;
    }

    public OpcUaServerConfig build() {
        UaTcpStackServerConfig stackServerConfig = super.build();

        return new OpcUaServerConfigImpl(
            stackServerConfig,
            bindPort, bindAddresses,
            endpointAddresses,
            securityPolicies,
            identityValidator,
            buildInfo,
            limits
        );
    }


    public static final class OpcUaServerConfigImpl implements OpcUaServerConfig {

        private final UaTcpStackServerConfig stackServerConfig;

        private final int bindPort;
        private final List<String> bindAddresses;
        private final List<String> endpointAddresses;
        private final EnumSet<SecurityPolicy> securityPolicies;
        private final IdentityValidator identityValidator;
        private final BuildInfo buildInfo;
        private final OpcUaServerConfigLimits limits;

        public OpcUaServerConfigImpl(UaTcpStackServerConfig stackServerConfig,
                                     int bindPort,
                                     List<String> bindAddresses,
                                     List<String> endpointAddresses,
                                     EnumSet<SecurityPolicy> securityPolicies,
                                     IdentityValidator identityValidator,
                                     BuildInfo buildInfo,
                                     OpcUaServerConfigLimits limits) {

            this.stackServerConfig = stackServerConfig;
            this.bindPort = bindPort;
            this.bindAddresses = bindAddresses;
            this.endpointAddresses = endpointAddresses;
            this.securityPolicies = securityPolicies;
            this.identityValidator = identityValidator;
            this.buildInfo = buildInfo;
            this.limits = limits;
        }

        @Override
        public int getBindPort() {
            return bindPort;
        }

        @Override
        public List<String> getBindAddresses() {
            return bindAddresses;
        }

        @Override
        public List<String> getEndpointAddresses() {
            return endpointAddresses;
        }

        @Override
        public IdentityValidator getIdentityValidator() {
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
        public EnumSet<SecurityPolicy> getSecurityPolicies() {
            return securityPolicies;
        }

        @Override
        public String getServerName() {
            return stackServerConfig.getServerName();
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
        public CertificateValidator getCertificateValidator() {
            return stackServerConfig.getCertificateValidator();
        }

        @Override
        public ExecutorService getExecutor() {
            return stackServerConfig.getExecutor();
        }

        @Override
        public List<UserTokenPolicy> getUserTokenPolicies() {
            return stackServerConfig.getUserTokenPolicies();
        }

        @Override
        public List<SignedSoftwareCertificate> getSoftwareCertificates() {
            return stackServerConfig.getSoftwareCertificates();
        }

        @Override
        public ChannelConfig getChannelConfig() {
            return stackServerConfig.getChannelConfig();
        }

        @Override
        public boolean isStrictEndpointUrlsEnabled() {
            return stackServerConfig.isStrictEndpointUrlsEnabled();
        }

    }

}
