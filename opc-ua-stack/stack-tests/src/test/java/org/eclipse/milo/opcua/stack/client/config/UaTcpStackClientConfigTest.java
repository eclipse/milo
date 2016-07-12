package org.eclipse.milo.opcua.stack.client.config;

import java.util.Optional;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;

public class UaTcpStackClientConfigTest extends SecurityFixture {

    @Test
    public void testCopy() {
        UaTcpStackClientConfig original = UaTcpStackClientConfig.builder()
            .setEndpointUrl("test")
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setApplicationName(LocalizedText.english("testName"))
            .setApplicationUri("testApplicationUri")
            .setProductUri("testProductUri")
            .setChannelConfig(ChannelConfig.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .setSecureChannelReauthenticationEnabled(true)
            .build();

        UaTcpStackClientConfig copy = UaTcpStackClientConfig.copy(original).build();

        assertEquals(copy.getEndpointUrl(), original.getEndpointUrl());
        assertEquals(copy.getKeyPair(), original.getKeyPair());
        assertEquals(copy.getCertificate(), original.getCertificate());
        assertEquals(copy.getApplicationName(), original.getApplicationName());
        assertEquals(copy.getApplicationUri(), original.getApplicationUri());
        assertEquals(copy.getProductUri(), original.getProductUri());
        assertEquals(copy.getChannelConfig(), original.getChannelConfig());
        assertEquals(copy.getChannelLifetime(), original.getChannelLifetime());
        assertEquals(copy.getExecutor(), original.getExecutor());
        assertEquals(copy.getEventLoop(), original.getEventLoop());
        assertEquals(copy.getWheelTimer(), original.getWheelTimer());
        assertEquals(
            copy.isSecureChannelReauthenticationEnabled(),
            original.isSecureChannelReauthenticationEnabled());
    }

    @Test
    public void testCopyAndModify() {
        UaTcpStackClientConfig original = UaTcpStackClientConfig.builder()
            .setEndpointUrl("test")
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setApplicationName(LocalizedText.english("testName"))
            .setApplicationUri("testApplicationUri")
            .setProductUri("testProductUri")
            .setChannelConfig(ChannelConfig.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .setSecureChannelReauthenticationEnabled(true)
            .build();

        UaTcpStackClientConfig copy = UaTcpStackClientConfig.copy(original,
            builder -> builder.setEndpointUrl("foo")
                .setKeyPair(null)
                .setCertificate(null)
                .setApplicationName(LocalizedText.english("fooName"))
                .setApplicationUri("fooApplicationUri")
                .setProductUri("fooProductUri")
                .setChannelConfig(null)
                .setChannelLifetime(uint(0))
        );

        assertEquals(copy.getEndpointUrl(), Optional.of("foo"));
        assertEquals(copy.getKeyPair(), Optional.empty());
        assertEquals(copy.getCertificate(), Optional.empty());
        assertEquals(copy.getApplicationName(), LocalizedText.english("fooName"));
        assertEquals(copy.getApplicationUri(), "fooApplicationUri");
        assertEquals(copy.getProductUri(), "fooProductUri");
        assertEquals(copy.getChannelConfig(), null);
        assertEquals(copy.getChannelLifetime(), uint(0));
    }

}