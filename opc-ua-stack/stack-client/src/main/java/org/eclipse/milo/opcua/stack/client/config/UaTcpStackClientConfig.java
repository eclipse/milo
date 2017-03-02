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

package org.eclipse.milo.opcua.stack.client.config;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public interface UaTcpStackClientConfig {

    /**
     * The endpoint url to connect to.
     *
     * @return an {@link Optional} containing the endpoint url to connect to. A {@link UaTcpStackClientConfig} must
     * have either an endpoint url or endpoint configured.
     */
    Optional<String> getEndpointUrl();

    /**
     * The {@link EndpointDescription} to connect to. May be absent if the connection will only be used for retrieving
     * endpoints, must be present if the connection will be used beyond that.
     *
     * @return an {@link Optional} containing the {@link EndpointDescription} to connect to. A {@link UaTcpStackClient}
     * must have either an endpoint url or endpoint configured.
     */
    Optional<EndpointDescription> getEndpoint();

    /**
     * Get the {@link KeyPair} to use. May be absent if connecting without security, must be present if connecting with
     * security.
     *
     * @return an {@link Optional} containing the {@link KeyPair} to use.
     */
    Optional<KeyPair> getKeyPair();

    /**
     * Get the {@link X509Certificate} to use. May be absent if connecting without security, must be present if
     * connecting with security.
     *
     * @return an {@link Optional} containing the {@link X509Certificate} to use.
     */
    Optional<X509Certificate> getCertificate();

    /**
     * Get the {@link X509Certificate} to use as well as any certificates in the certificate chain.
     *
     * @return the {@link X509Certificate} to use as well as any certificates in the certificate chain.
     */
    Optional<X509Certificate[]> getCertificateChain();

    /**
     * Get the {@link CertificateValidator} this client will use to validate server certificates when connecting.
     *
     * @return the {@link CertificateValidator} this client will use to validate server certificates when connecting.
     */
    CertificateValidator getCertificateValidator();

    /**
     * @return the name of the client application, as a {@link LocalizedText}.
     */
    LocalizedText getApplicationName();

    /**
     * @return a URI for the client's application instance. This should be the same as the URI in the client
     * certificate, if present.
     */
    String getApplicationUri();

    /**
     * @return the URI for the client's application product.
     */
    String getProductUri();

    /**
     * @return the {@link ChannelConfig} to use when creating secure channels.
     */
    ChannelConfig getChannelConfig();

    /**
     * @return the secure channel lifetime to request, in milliseconds.
     */
    UInteger getChannelLifetime();

    /**
     * @return the {@link ExecutorService} the {@link UaTcpStackClient} will use.
     */
    ExecutorService getExecutor();

    /**
     * @return the {@link NioEventLoopGroup} the {@link UaTcpStackClient} will use.
     */
    NioEventLoopGroup getEventLoop();

    /**
     * @return the {@link HashedWheelTimer} the {@link UaTcpStackClient} will use.
     */
    HashedWheelTimer getWheelTimer();


    /**
     * @return the maximum amount of time, in milliseconds, to wait for an Acknowledge message in response to the
     * client's Hello message.
     */
    UInteger getAcknowledgeTimeout();

    /**
     * @return a new {@link UaTcpStackClientConfigBuilder}.
     */
    static UaTcpStackClientConfigBuilder builder() {
        return new UaTcpStackClientConfigBuilder();
    }

    /**
     * Copy the values from an existing {@link UaTcpStackClientConfig} into a new {@link UaTcpStackClientConfigBuilder}.
     * This builder can be used to make any desired modifications before invoking
     * {@link UaTcpStackClientConfigBuilder#build()} to produce a new config.
     *
     * @param config the {@link UaTcpStackClientConfig} to copy from.
     * @return a {@link UaTcpStackClientConfigBuilder} pre-populated with values from {@code config}.
     */
    static UaTcpStackClientConfigBuilder copy(UaTcpStackClientConfig config) {
        UaTcpStackClientConfigBuilder builder = new UaTcpStackClientConfigBuilder();

        config.getEndpointUrl().ifPresent(builder::setEndpointUrl);
        config.getEndpoint().ifPresent(builder::setEndpoint);
        config.getKeyPair().ifPresent(builder::setKeyPair);
        config.getCertificate().ifPresent(builder::setCertificate);
        config.getCertificateChain().ifPresent(builder::setCertificateChain);
        builder.setCertificateValidator(config.getCertificateValidator());
        builder.setApplicationName(config.getApplicationName());
        builder.setApplicationUri(config.getApplicationUri());
        builder.setProductUri(config.getProductUri());
        builder.setChannelConfig(config.getChannelConfig());
        builder.setChannelLifetime(config.getChannelLifetime());
        builder.setExecutor(config.getExecutor());
        builder.setEventLoop(config.getEventLoop());
        builder.setWheelTimer(config.getWheelTimer());
        builder.setAcknowledgeTimeout(config.getAcknowledgeTimeout());

        return builder;
    }

    /**
     * Copy the values from an existing {@link UaTcpStackClientConfig} into a new {@link UaTcpStackClientConfigBuilder}
     * and then submit the builder to the provided consumer for modification.
     *
     * @param config   the {@link UaTcpStackClientConfig} to copy from.
     * @param consumer a {@link Consumer} that may modify the builder.
     * @return a {@link UaTcpStackClientConfig} built from the builder provided to {@code consumer}.
     */
    static UaTcpStackClientConfig copy(
        UaTcpStackClientConfig config,
        Consumer<UaTcpStackClientConfigBuilder> consumer) {

        UaTcpStackClientConfigBuilder builder = copy(config);

        consumer.accept(builder);

        return builder.build();
    }

}
