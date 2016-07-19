package org.eclipse.milo.opcua.sdk.client.config;

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
            .build();

        OpcUaClientConfig copy = OpcUaClientConfig.copy(original).build();

        assertEquals(copy.getSessionName(), original.getSessionName());
        assertEquals(copy.getSessionTimeout(), original.getSessionTimeout());
        assertEquals(copy.getRequestTimeout(), original.getRequestTimeout());
        assertEquals(copy.getMaxResponseMessageSize(), original.getMaxResponseMessageSize());
        assertEquals(copy.getMaxPendingPublishRequests(), original.getMaxPendingPublishRequests());
        assertEquals(copy.getIdentityProvider(), original.getIdentityProvider());
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
        );

        assertNotEquals(copy.getSessionName(), original.getSessionName());
        assertNotEquals(copy.getIdentityProvider(), original.getIdentityProvider());

        assertEquals(copy.getSessionTimeout(), uint(0));
        assertEquals(copy.getRequestTimeout(), uint(0));
        assertEquals(copy.getMaxResponseMessageSize(), uint(0));
        assertEquals(copy.getMaxPendingPublishRequests(), uint(0));
    }

}
