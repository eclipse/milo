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

import org.eclipse.milo.opcua.binaryschema.GenericBsdParser;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class OpcUaClientConfigTest {

    @Test
    public void testCopy() {
        OpcUaClientConfig original = OpcUaClientConfig.builder()
            .setSessionName(() -> "testSessionName")
            .setSessionTimeout(uint(60000 * 60))
            .setRequestTimeout(uint(120000))
            .setMaxResponseMessageSize(UInteger.MAX)
            .setMaxPendingPublishRequests(uint(2))
            .setIdentityProvider(new AnonymousProvider())
            .setBsdParser(new GenericBsdParser())
            .setSessionLocaleIds(new String[]{"en", "es"})
            .build();

        OpcUaClientConfig copy = OpcUaClientConfig.copy(original).build();

        assertEquals(copy.getSessionName(), original.getSessionName());
        assertEquals(copy.getSessionTimeout(), original.getSessionTimeout());
        assertEquals(copy.getRequestTimeout(), original.getRequestTimeout());
        assertEquals(copy.getMaxResponseMessageSize(), original.getMaxResponseMessageSize());
        assertEquals(copy.getMaxPendingPublishRequests(), original.getMaxPendingPublishRequests());
        assertEquals(copy.getIdentityProvider(), original.getIdentityProvider());
        assertEquals(copy.getBsdParser(), original.getBsdParser());
        assertEquals(copy.getKeepAliveFailuresAllowed(), original.getKeepAliveFailuresAllowed());
        assertEquals(copy.getKeepAliveInterval(), original.getKeepAliveInterval());
        assertEquals(copy.getKeepAliveTimeout(), original.getKeepAliveTimeout());
        assertEquals(copy.getSessionLocaleIds(), original.getSessionLocaleIds());
    }

    @Test
    public void testCopyAndModify() {
        OpcUaClientConfig original = OpcUaClientConfig.builder()
            .setSessionName(() -> "testSessionName")
            .setSessionTimeout(uint(60000 * 60))
            .setRequestTimeout(uint(120000))
            .setMaxResponseMessageSize(UInteger.MAX)
            .setMaxPendingPublishRequests(uint(2))
            .setIdentityProvider(new AnonymousProvider())
            .build();

        OpcUaClientConfig copy = OpcUaClientConfig.copy(original,
            builder ->
                builder.setSessionName(() -> "foo")
                    .setSessionTimeout(uint(0))
                    .setRequestTimeout(uint(0))
                    .setMaxResponseMessageSize(uint(0))
                    .setMaxPendingPublishRequests(uint(0))
                    .setIdentityProvider(new AnonymousProvider())
                    .setBsdParser(new GenericBsdParser())
                    .setKeepAliveFailuresAllowed(uint(2))
                    .setKeepAliveInterval(uint(10000))
                    .setKeepAliveTimeout(uint(15000))
                    .setSessionLocaleIds(new String[]{"en", "es"})
        );

        assertNotEquals(copy.getSessionName(), original.getSessionName());
        assertNotEquals(copy.getIdentityProvider(), original.getIdentityProvider());
        assertNotEquals(copy.getBsdParser(), original.getBsdParser());
        assertNotEquals(copy.getSessionLocaleIds(), original.getSessionLocaleIds());

        assertEquals(copy.getSessionTimeout(), uint(0));
        assertEquals(copy.getRequestTimeout(), uint(0));
        assertEquals(copy.getMaxResponseMessageSize(), uint(0));
        assertEquals(copy.getMaxPendingPublishRequests(), uint(0));
        assertEquals(copy.getKeepAliveFailuresAllowed(), uint(2));
        assertEquals(copy.getKeepAliveInterval(), uint(10000));
        assertEquals(copy.getKeepAliveTimeout(), uint(15000));
        assertEquals(copy.getSessionLocaleIds(), new String[]{"en", "es"});
    }

}
