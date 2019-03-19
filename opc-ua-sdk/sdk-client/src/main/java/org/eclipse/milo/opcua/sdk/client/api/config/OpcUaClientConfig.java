/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.config;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;

public interface OpcUaClientConfig extends UaStackClientConfig {

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
     * @return a {@link Supplier} for the session name.
     */
    Supplier<String> getSessionName();

    /**
     * @return the list of locale ids in priority order for localized strings
     */
    String[] getSessionLocaleIds();

    /**
     * @return the session timeout, in milliseconds, to request.
     */
    UInteger getSessionTimeout();

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
     * @return the number of consecutive keep-alive request failures allowed before a connection is determined to be in
     * error state.
     */
    UInteger getKeepAliveFailuresAllowed();

    /**
     * @return the interval, in milliseconds, between consecutive keep-alive requests.
     */
    UInteger getKeepAliveInterval();

    /**
     * @return the amount of time to wait, in milliseconds, for a keep-alive request before timing out.
     */
    UInteger getKeepAliveTimeout();

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

        // UaStackClientConfig values
        builder.setEndpoint(config.getEndpoint());
        config.getKeyPair().ifPresent(builder::setKeyPair);
        config.getCertificate().ifPresent(builder::setCertificate);
        config.getCertificateChain().ifPresent(builder::setCertificateChain);
        builder.setApplicationName(config.getApplicationName());
        builder.setApplicationUri(config.getApplicationUri());
        builder.setProductUri(config.getProductUri());
        builder.setMessageLimits(config.getMessageLimits());
        builder.setEncodingLimits(config.getEncodingLimits());
        builder.setChannelLifetime(config.getChannelLifetime());
        builder.setExecutor(config.getExecutor());
        builder.setEventLoop(config.getEventLoop());
        builder.setWheelTimer(config.getWheelTimer());
        builder.setConnectTimeout(config.getConnectTimeout());
        builder.setAcknowledgeTimeout(config.getAcknowledgeTimeout());
        builder.setRequestTimeout(config.getRequestTimeout());

        // OpcUaClientConfig values
        builder.setSessionName(config.getSessionName());
        builder.setSessionTimeout(config.getSessionTimeout());
        builder.setRequestTimeout(config.getRequestTimeout());
        builder.setMaxResponseMessageSize(config.getMaxResponseMessageSize());
        builder.setMaxPendingPublishRequests(config.getMaxPendingPublishRequests());
        builder.setIdentityProvider(config.getIdentityProvider());
        builder.setBsdParser(config.getBsdParser());
        builder.setKeepAliveFailuresAllowed(config.getKeepAliveFailuresAllowed());
        builder.setKeepAliveInterval(config.getKeepAliveInterval());
        builder.setKeepAliveTimeout(config.getKeepAliveTimeout());
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
