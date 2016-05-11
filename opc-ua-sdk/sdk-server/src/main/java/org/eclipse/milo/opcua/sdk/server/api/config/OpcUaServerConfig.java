/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.EnumSet;
import java.util.List;

import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.identity.UsernameIdentityValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;

public interface OpcUaServerConfig extends UaTcpStackServerConfig {

    /**
     * A {@link UserTokenPolicy} for anonymous access.
     */
    UserTokenPolicy USER_TOKEN_POLICY_ANONYMOUS = new UserTokenPolicy(
        "anonymous",
        UserTokenType.Anonymous,
        null, null, null);

    /**
     * A {@link UserTokenPolicy} for username-based access.
     */
    UserTokenPolicy USER_TOKEN_POLICY_USERNAME = new UserTokenPolicy(
        "username",
        UserTokenType.UserName,
        null, null, null);

    /**
     * The set of {@link SecurityPolicy}s supported by this server.
     * <p>
     * Any policies other than {@link SecurityPolicy#None} require the server to have a certificate configured.
     *
     * @return the set of {@link SecurityPolicy}s supported by this server.
     */
    EnumSet<SecurityPolicy> getSecurityPolicies();

    /**
     * Get the hostname to use in endpoint URLs.
     * <p>
     * Endpoint URLs will be of the format "opc.tcp://{hostname}:{port}/{serverName}".
     *
     * @return the hostname to use in endpoint URLs.
     */
    String getHostname();

    /**
     * @return the list of addresses to bind to.
     */
    List<String> getBindAddresses();

    /**
     * @return the port to bind to.
     */
    int getBindPort();

    /**
     * Get the {@link IdentityValidator} for the server.
     *
     * @return the {@link IdentityValidator} for the server.
     * @see AnonymousIdentityValidator
     * @see UsernameIdentityValidator
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

}
