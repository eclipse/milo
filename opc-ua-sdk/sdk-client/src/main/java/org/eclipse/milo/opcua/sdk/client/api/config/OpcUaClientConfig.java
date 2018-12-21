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

package org.eclipse.milo.opcua.sdk.client.api.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;

public interface OpcUaClientConfig extends UaTcpStackClientConfig {

    /**
     * @return a {@link Supplier} for the session name.
     */
    Supplier<String> getSessionName();

    /**
     * @return the session timeout, in milliseconds, to request.
     */
    UInteger getSessionTimeout();

    /**
     * @return the timeout, in milliseconds, before failing a request due to timeout.
     */
    UInteger getRequestTimeout();

    /**
     * @return the maximum size for a response from the server.
     */
    UInteger getMaxResponseMessageSize();

    /**
     * @return the maximum number of outstanding {@link PublishRequest}s allowed at any given time.
     */
    UInteger getMaxPendingPublishRequests();

    /**
     * @return an {@link IdentityProvider} to use when activating a session.
     */
    IdentityProvider getIdentityProvider();

    /**
     * @return the {@link BsdParser} implementation used to serialize DataTypes defined by the server.
     */
    BsdParser getBsdParser();

    /**
     * @return the list of locale ids in priority order for localized strings
     */
    String[] getSessionLocaleIds();

    /**
     * @return a new {@link OpcUaClientConfigBuilder}.
     */
    static OpcUaClientConfigBuilder builder() {
        return new OpcUaClientConfigBuilder();
    }

    /**
     * Copy the values from an existing {@link OpcUaClientConfig} into a new {@link OpcUaClientConfigBuilder}. This
     * builder can be used to make any desired modifications before invoking {@link OpcUaClientConfigBuilder#build()}
     * to produce a new config.
     *
     * @param config the {@link OpcUaClientConfig} to copy from.
     * @return a {@link OpcUaClientConfigBuilder} pre-populated with values from {@code config}.
     */
    static OpcUaClientConfigBuilder copy(OpcUaClientConfig config) {
        OpcUaClientConfigBuilder builder = new OpcUaClientConfigBuilder();

        // UaTcpStackClientConfig values
        config.getEndpointUrl().ifPresent(builder::setEndpointUrl);
        config.getEndpoint().ifPresent(builder::setEndpoint);
        config.getKeyPair().ifPresent(builder::setKeyPair);
        config.getCertificate().ifPresent(builder::setCertificate);
        config.getCertificateChain().ifPresent(builder::setCertificateChain);
        builder.setApplicationName(config.getApplicationName());
        builder.setApplicationUri(config.getApplicationUri());
        builder.setProductUri(config.getProductUri());
        builder.setChannelConfig(config.getChannelConfig());
        builder.setChannelLifetime(config.getChannelLifetime());
        builder.setExecutor(config.getExecutor());
        builder.setEventLoop(config.getEventLoop());
        builder.setWheelTimer(config.getWheelTimer());

        // OpcUaClientConfig values
        builder.setSessionName(config.getSessionName());
        builder.setSessionTimeout(config.getSessionTimeout());
        builder.setRequestTimeout(config.getRequestTimeout());
        builder.setMaxResponseMessageSize(config.getMaxResponseMessageSize());
        builder.setMaxPendingPublishRequests(config.getMaxPendingPublishRequests());
        builder.setIdentityProvider(config.getIdentityProvider());
        builder.setBsdParser(config.getBsdParser());
        builder.setSessionLocaleIds(config.getSessionLocaleIds());

        return builder;
    }

    /**
     * Copy the values from an existing {@link OpcUaClientConfig} into a new {@link OpcUaClientConfigBuilder} and then
     * submit the builder to the provided consumer for modification.
     *
     * @param config   the {@link OpcUaClientConfig} to copy from.
     * @param consumer a {@link Consumer} that may modify the builder.
     * @return a {@link OpcUaClientConfig} built from the builder provided to {@code consumer}.
     */
    static OpcUaClientConfig copy(
        OpcUaClientConfig config,
        Consumer<OpcUaClientConfigBuilder> consumer) {

        OpcUaClientConfigBuilder builder = copy(config);

        consumer.accept(builder);

        return builder.build();
    }

}
