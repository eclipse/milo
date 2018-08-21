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

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;

public class UaTcpStackClientConfigTest extends SecurityFixture {

    private final CertificateValidator validator = new CertificateValidator() {
        @Override
        public void validate(X509Certificate certificate) throws UaException {}

        @Override
        public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {}
    };

    @Test
    public void testCopy() {


        UaTcpStackClientConfig original = UaTcpStackClientConfig.builder()
            .setEndpointUrl("test")
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateChain(new X509Certificate[]{clientCertificate})
            .setCertificateValidator(validator)
            .setApplicationName(LocalizedText.english("testName"))
            .setApplicationUri("testApplicationUri")
            .setProductUri("testProductUri")
            .setChannelConfig(ChannelConfig.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .setAcknowledgeTimeout(uint(12345))
            .build();

        UaTcpStackClientConfig copy = UaTcpStackClientConfig.copy(original).build();

        assertEquals(copy.getEndpointUrl(), original.getEndpointUrl());
        assertEquals(copy.getKeyPair(), original.getKeyPair());
        assertEquals(copy.getCertificate(), original.getCertificate());
        assertEquals(copy.getCertificateChain(), original.getCertificateChain());
        assertEquals(copy.getCertificateValidator(), original.getCertificateValidator());
        assertEquals(copy.getApplicationName(), original.getApplicationName());
        assertEquals(copy.getApplicationUri(), original.getApplicationUri());
        assertEquals(copy.getProductUri(), original.getProductUri());
        assertEquals(copy.getChannelConfig(), original.getChannelConfig());
        assertEquals(copy.getChannelLifetime(), original.getChannelLifetime());
        assertEquals(copy.getExecutor(), original.getExecutor());
        assertEquals(copy.getEventLoop(), original.getEventLoop());
        assertEquals(copy.getWheelTimer(), original.getWheelTimer());
        assertEquals(copy.getAcknowledgeTimeout(), original.getAcknowledgeTimeout());
    }

    @Test
    public void testCopyAndModify() {
        UaTcpStackClientConfig original = UaTcpStackClientConfig.builder()
            .setEndpointUrl("test")
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateValidator(validator)
            .setApplicationName(LocalizedText.english("testName"))
            .setApplicationUri("testApplicationUri")
            .setProductUri("testProductUri")
            .setChannelConfig(ChannelConfig.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .build();

        UaTcpStackClientConfig copy = UaTcpStackClientConfig.copy(original,
            builder -> builder.setEndpointUrl("foo")
                .setKeyPair(null)
                .setCertificate(null)
                .setCertificateChain(null)
                .setCertificateValidator(null)
                .setApplicationName(LocalizedText.english("fooName"))
                .setApplicationUri("fooApplicationUri")
                .setProductUri("fooProductUri")
                .setChannelConfig(null)
                .setChannelLifetime(uint(0))
                .setAcknowledgeTimeout(uint(12345))
        );

        assertEquals(copy.getEndpointUrl(), Optional.of("foo"));
        assertEquals(copy.getKeyPair(), Optional.empty());
        assertEquals(copy.getCertificate(), Optional.empty());
        assertEquals(copy.getCertificateChain(), Optional.empty());
        assertEquals(copy.getCertificateValidator(), null);
        assertEquals(copy.getApplicationName(), LocalizedText.english("fooName"));
        assertEquals(copy.getApplicationUri(), "fooApplicationUri");
        assertEquals(copy.getProductUri(), "fooProductUri");
        assertEquals(copy.getChannelConfig(), null);
        assertEquals(copy.getChannelLifetime(), uint(0));
        assertEquals(copy.getAcknowledgeTimeout(), uint(12345));
    }

}