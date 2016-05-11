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

package org.eclipse.milo.opcua.sdk.client.api.config;

import java.util.function.Supplier;

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

    static OpcUaClientConfigBuilder builder() {
        return new OpcUaClientConfigBuilder();
    }

}
