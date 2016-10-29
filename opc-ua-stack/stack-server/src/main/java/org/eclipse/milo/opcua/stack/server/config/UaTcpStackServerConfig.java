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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public interface UaTcpStackServerConfig {

    /**
     * The server name to use when building endpoint URLs: "opc.tcp://{hostname}:{port}/{serverName}.".
     * <p/>
     * If empty, endpoint URLs will be of the format "opc.tcp://{hostname}:{port}".
     *
     * @return the server name to use when building endpoint URLs.
     */
    String getServerName();

    /**
     * Get the application name for the server.
     * <p/>
     * This will be used in the {@link ApplicationDescription} returned to clients.
     *
     * @return the application name for the server.
     */
    LocalizedText getApplicationName();

    /**
     * Get the application uri for the server.
     * <p/>
     * This will be used in the {@link ApplicationDescription} returned to clients.
     * <p/>
     * <b>The application uri must match the application uri used on the server's application instance certificate.</b>
     *
     * @return the application uri for the server.
     */
    String getApplicationUri();

    /**
     * Get the product uri for the server.
     * <p/>
     * This will be used in the {@link ApplicationDescription} returned to clients.
     *
     * @return the product uri for the server.
     */
    String getProductUri();

    /**
     * @return the {@link CertificateManager} for this server.
     */
    CertificateManager getCertificateManager();

    /**
     * @return the {@link CertificateValidator} for this server.
     */
    CertificateValidator getCertificateValidator();

    ExecutorService getExecutor();

    /**
     * Get the list of {@link UserTokenPolicy}s supported by the server.
     *
     * @return the list of {@link UserTokenPolicy}s supported by the server.
     */
    List<UserTokenPolicy> getUserTokenPolicies();

    List<SignedSoftwareCertificate> getSoftwareCertificates();

    ChannelConfig getChannelConfig();

    /**
     * If {@code true}, when a UA TCP "Hello" message is received, endpoint URL must exactly match a registered server
     * name. If {@code false}, and only one server is registered, that server will be returned even if the path does not
     * match.
     *
     * @return {@code true} if strict endpoint URLs are enabled.
     */
    boolean isStrictEndpointUrlsEnabled();

    /**
     * @return a new {@link UaTcpStackServerConfigBuilder}.
     */
    static UaTcpStackServerConfigBuilder builder() {
        return new UaTcpStackServerConfigBuilder();
    }

    /**
     * Copy the values from an existing {@link UaTcpStackServerConfig} into a new {@link UaTcpStackServerConfigBuilder}.
     * This builder can be used to make any desired modifications before invoking
     * {@link UaTcpStackServerConfigBuilder#build()} to produce a new config.
     *
     * @param config the {@link UaTcpStackServerConfig} to copy from.
     * @return a {@link UaTcpStackServerConfigBuilder} pre-populated with values from {@code config}.
     */
    static UaTcpStackServerConfigBuilder copy(UaTcpStackServerConfig config) {
        UaTcpStackServerConfigBuilder builder = new UaTcpStackServerConfigBuilder();

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

        return builder;
    }

    /**
     * Copy the values from an existing {@link UaTcpStackServerConfig} into a new {@link UaTcpStackServerConfigBuilder}
     * and then submit the builder to the provided consumer for modification.
     *
     * @param config   the {@link UaTcpStackServerConfig} to copy from.
     * @param consumer a {@link Consumer} that may modify the builder.
     * @return a {@link UaTcpStackServerConfig} built from the builder provided to {@code consumer}.
     */
    static UaTcpStackServerConfig copy(
        UaTcpStackServerConfig config,
        Consumer<UaTcpStackServerConfigBuilder> consumer) {

        UaTcpStackServerConfigBuilder builder = copy(config);

        consumer.accept(builder);

        return builder.build();
    }

}
