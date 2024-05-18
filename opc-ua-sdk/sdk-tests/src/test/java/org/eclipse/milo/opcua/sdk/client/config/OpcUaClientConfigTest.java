/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.config;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.junit.jupiter.api.Test;

public class OpcUaClientConfigTest {

    private final EndpointDescription endpoint = new EndpointDescription(
        "opc.tcp://localhost:62541",
        null,
        null,
        null,
        null,
        new UserTokenPolicy[]{
            new UserTokenPolicy(
                "anonymous",
                UserTokenType.Anonymous,
                null, null, null)
        },
        null,
        null
    );

    @Test
    public void testCopy() {
        OpcUaClientConfig original = OpcUaClientConfig.builder()
            .setEndpoint(endpoint)
            .setSessionName(() -> "testSessionName")
            .setSessionTimeout(uint(60000 * 60))
            .setRequestTimeout(uint(120000))
            .setMaxResponseMessageSize(UInteger.MAX)
            .setMaxPendingPublishRequests(uint(2))
            .setIdentityProvider(new AnonymousProvider())
            .setSessionLocaleIds(new String[]{"en", "es"})
            .setSubscriptionWatchdogMultiplier(1.25)
            .build();

        OpcUaClientConfig copy = OpcUaClientConfig.copy(original).build();

        assertEquals(original.getSessionName(), copy.getSessionName());
        assertEquals(original.getSessionTimeout(), copy.getSessionTimeout());
        assertEquals(original.getRequestTimeout(), copy.getRequestTimeout());
        assertEquals(original.getMaxResponseMessageSize(), copy.getMaxResponseMessageSize());
        assertEquals(original.getMaxPendingPublishRequests(), copy.getMaxPendingPublishRequests());
        assertEquals(original.getIdentityProvider(), copy.getIdentityProvider());
        assertEquals(original.getKeepAliveFailuresAllowed(), copy.getKeepAliveFailuresAllowed());
        assertEquals(original.getKeepAliveInterval(), copy.getKeepAliveInterval());
        assertEquals(original.getKeepAliveTimeout(), copy.getKeepAliveTimeout());
        assertEquals(original.getSessionLocaleIds(), copy.getSessionLocaleIds());
        assertEquals(original.getSubscriptionWatchdogMultiplier(), copy.getSubscriptionWatchdogMultiplier());
    }

    @Test
    public void testCopyAndModify() {
        OpcUaClientConfig original = OpcUaClientConfig.builder()
            .setEndpoint(endpoint)
            .setSessionName(() -> "testSessionName")
            .setSessionTimeout(uint(60000 * 60))
            .setRequestTimeout(uint(120000))
            .setMaxResponseMessageSize(UInteger.MAX)
            .setMaxPendingPublishRequests(uint(2))
            .setIdentityProvider(new AnonymousProvider())
            .setSubscriptionWatchdogMultiplier(3.0)
            .build();

        OpcUaClientConfig copy = OpcUaClientConfig.copy(original,
            builder ->
                builder.setSessionName(() -> "foo")
                    .setSessionTimeout(uint(0))
                    .setRequestTimeout(uint(0))
                    .setMaxResponseMessageSize(uint(0))
                    .setMaxPendingPublishRequests(uint(0))
                    .setIdentityProvider(new AnonymousProvider())
                    .setKeepAliveFailuresAllowed(uint(2))
                    .setKeepAliveInterval(uint(10000))
                    .setKeepAliveTimeout(uint(15000))
                    .setSessionLocaleIds(new String[]{"en", "es"})
        );

        assertNotEquals(copy.getSessionName(), original.getSessionName());
        assertNotEquals(copy.getIdentityProvider(), original.getIdentityProvider());
        assertNotEquals(copy.getSessionLocaleIds(), original.getSessionLocaleIds());

        assertEquals(uint(0), copy.getSessionTimeout());
        assertEquals(uint(0), copy.getRequestTimeout());
        assertEquals(uint(0), copy.getMaxResponseMessageSize());
        assertEquals(uint(0), copy.getMaxPendingPublishRequests());
        assertEquals(uint(2), copy.getKeepAliveFailuresAllowed());
        assertEquals(uint(10000), copy.getKeepAliveInterval());
        assertEquals(uint(15000), copy.getKeepAliveTimeout());
        assertArrayEquals(new String[]{"en", "es"}, copy.getSessionLocaleIds());
        assertEquals(3.0, copy.getSubscriptionWatchdogMultiplier());
    }

}
