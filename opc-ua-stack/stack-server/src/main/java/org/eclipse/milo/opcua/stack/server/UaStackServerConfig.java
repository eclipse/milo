/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;

public interface UaStackServerConfig {

    /**
     * @return the {@link EndpointConfiguration}s for this server.
     */
    Set<EndpointConfiguration> getEndpoints();

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
     * @return the {@link ChannelConfig}.
     */
    ChannelConfig getChannelConfig();

    /**
     * @return the configured {@link EncodingLimits}.
     */
    EncodingLimits getEncodingLimits();

    /**
     * @return the {@link CertificateManager} for this server.
     */
    CertificateManager getCertificateManager();

    /**
     * @return the {@link CertificateValidator} for this server.
     */
    CertificateValidator getCertificateValidator();

    /**
     * @return the {@link KeyPair} used for SSL/TLS with HTTPS endpoints.
     */
    Optional<KeyPair> getHttpsKeyPair();

    /**
     * @return the {@link X509Certificate} used for SSL/TLS with HTTPS endpoints.
     */
    Optional<X509Certificate> getHttpsCertificate();

    /**
     * @return the {@link ExecutorService} for this server.
     */
    ExecutorService getExecutor();

    static UaStackServerConfigBuilder builder() {
        return new UaStackServerConfigBuilder();
    }

    static UaStackServerConfigBuilder copy(UaStackServerConfig config) {
        UaStackServerConfigBuilder builder = builder();

        // TODO

        return builder;
    }

    static UaStackServerConfig copy(
        UaStackServerConfig config,
        Consumer<UaStackServerConfigBuilder> consumer) {

        UaStackServerConfigBuilder builder = copy(config);

        consumer.accept(builder);

        return builder.build();
    }

}
