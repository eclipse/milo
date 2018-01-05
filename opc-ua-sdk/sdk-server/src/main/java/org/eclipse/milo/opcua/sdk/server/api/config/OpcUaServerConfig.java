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

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.CompositeValidator;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.X509IdentityValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;

public interface OpcUaServerConfig extends UaTcpStackServerConfig {

    /**
     * A {@link UserTokenPolicy} for anonymous access.
     */
    UserTokenPolicy USER_TOKEN_POLICY_ANONYMOUS = new UserTokenPolicy(
        "anonymous",
        UserTokenType.Anonymous,
        null,
        null,
        null
    );

    /**
     * A {@link UserTokenPolicy} for username-based access.
     */
    UserTokenPolicy USER_TOKEN_POLICY_USERNAME = new UserTokenPolicy(
        "username",
        UserTokenType.UserName,
        null,
        null,
        SecurityPolicy.Basic256.getSecurityPolicyUri()
    );

    UserTokenPolicy USER_TOKEN_POLICY_X509 = new UserTokenPolicy(
        "certificate",
        UserTokenType.Certificate,
        null,
        null,
        SecurityPolicy.Basic256.getSecurityPolicyUri()
    );

    /**
     * @return the port to bind to.
     */
    int getBindPort();

    /**
     * @return the list of addresses to bind to.
     */
    List<String> getBindAddresses();

    /**
     * @return the list of addresses used to build endpoint URLs and {@link EndpointDescription}s.
     */
    List<String> getEndpointAddresses();

    /**
     * The set of {@link SecurityPolicy}s supported by this server.
     * <p>
     * Any policies other than {@link SecurityPolicy#None} require the server to have a certificate configured.
     *
     * @return the set of {@link SecurityPolicy}s supported by this server.
     */
    EnumSet<SecurityPolicy> getSecurityPolicies();

    /**
     * Get the {@link IdentityValidator} for the server.
     *
     * @return the {@link IdentityValidator} for the server.
     * @see AnonymousIdentityValidator
     * @see UsernameIdentityValidator
     * @see X509IdentityValidator
     * @see CompositeValidator
     */
    IdentityValidator getIdentityValidator();

    /**
     * @return the server {@link BuildInfo}.
     */
    BuildInfo getBuildInfo();

    /**
     * @return the {@link OpcUaServerConfigLimits}.
     */
    OpcUaServerConfigLimits getLimits();

    /**
     * @return a {@link OpcUaServerConfigBuilder}.
     */
    static OpcUaServerConfigBuilder builder() {
        return new OpcUaServerConfigBuilder();
    }

    /**
     * Copy the values from an existing {@link OpcUaServerConfig} into a new {@link OpcUaServerConfigBuilder}. This
     * builder can be used to make any desired modifications before invoking {@link OpcUaServerConfigBuilder#build()}
     * to produce a new config.
     *
     * @param config the {@link OpcUaServerConfig} to copy from.
     * @return a {@link OpcUaServerConfigBuilder} pre-populated with values from {@code config}.
     */
    static OpcUaServerConfigBuilder copy(OpcUaServerConfig config) {
        OpcUaServerConfigBuilder builder = new OpcUaServerConfigBuilder();

        // UaTcpStackServerConfig values
        builder.setServerName(config.getServerName());
        builder.setApplicationName(config.getApplicationName());
        builder.setApplicationUri(config.getApplicationUri());
        builder.setProductUri(config.getProductUri());
        builder.setCertificateManager(config.getCertificateManager());
        builder.setCertificateValidator(config.getCertificateValidator());
        builder.setExecutor(config.getExecutor());
        builder.setUserTokenPolicies(config.getUserTokenPolicies());
        builder.setSoftwareCertificates(config.getSoftwareCertificates());
        builder.setChannelConfig(config.getChannelConfig());
        builder.setStrictEndpointUrlsEnabled(config.isStrictEndpointUrlsEnabled());

        // OpcUaServerConfig values
        builder.setSecurityPolicies(config.getSecurityPolicies());
        builder.setBindPort(config.getBindPort());
        builder.setBindAddresses(config.getBindAddresses());
        builder.setEndpointAddresses(config.getEndpointAddresses());
        builder.setIdentityValidator(config.getIdentityValidator());
        builder.setBuildInfo(config.getBuildInfo());
        builder.setLimits(config.getLimits());

        return builder;
    }

    /**
     * Copy the values from an existing {@link OpcUaServerConfig} into a new {@link OpcUaServerConfigBuilder} and then
     * submit the builder to the provided consumer for modification.
     *
     * @param config   the {@link OpcUaServerConfig} to copy from.
     * @param consumer a {@link Consumer} that may modify the builder.
     * @return a {@link OpcUaServerConfig} built from the builder provided to {@code consumer}.
     */
    static OpcUaServerConfig copy(
        OpcUaServerConfig config,
        Consumer<OpcUaServerConfigBuilder> consumer) {

        OpcUaServerConfigBuilder builder = copy(config);

        consumer.accept(builder);

        return builder.build();
    }

}
