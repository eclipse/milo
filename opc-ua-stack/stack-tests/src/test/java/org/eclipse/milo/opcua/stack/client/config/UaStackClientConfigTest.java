/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.config;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;

public class UaStackClientConfigTest extends SecurityFixture {

    private final CertificateValidator validator = new CertificateValidator() {
        @Override
        public void validate(X509Certificate certificate) throws UaException {}

        @Override
        public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {}
    };

    @Test
    public void testCopy() {
        UaStackClientConfig original = UaStackClientConfig.builder()
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateChain(new X509Certificate[]{clientCertificate})
            .setCertificateValidator(validator)
            .setMessageLimits(MessageLimits.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .setAcknowledgeTimeout(uint(12345))
            .build();

        UaStackClientConfig copy = UaStackClientConfig.copy(original).build();

        assertEquals(copy.getKeyPair(), original.getKeyPair());
        assertEquals(copy.getCertificate(), original.getCertificate());
        assertEquals(copy.getCertificateChain(), original.getCertificateChain());
        assertEquals(copy.getCertificateValidator(), original.getCertificateValidator());
        assertEquals(copy.getMessageLimits(), original.getMessageLimits());
        assertEquals(copy.getChannelLifetime(), original.getChannelLifetime());
        assertEquals(copy.getExecutor(), original.getExecutor());
        assertEquals(copy.getEventLoop(), original.getEventLoop());
        assertEquals(copy.getWheelTimer(), original.getWheelTimer());
        assertEquals(copy.getAcknowledgeTimeout(), original.getAcknowledgeTimeout());
    }

    @Test
    public void testCopyAndModify() {
        UaStackClientConfig original = UaStackClientConfig.builder()
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateValidator(validator)
            .setMessageLimits(MessageLimits.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .build();

        UaStackClientConfig copy = UaStackClientConfig.copy(
            original,
            builder ->
                builder
                    .setKeyPair(null)
                    .setCertificate(null)
                    .setCertificateChain(null)
                    .setCertificateValidator(null)
                    .setMessageLimits(null)
                    .setChannelLifetime(uint(0))
                    .setAcknowledgeTimeout(uint(12345))
        );

        assertEquals(copy.getKeyPair(), Optional.empty());
        assertEquals(copy.getCertificate(), Optional.empty());
        assertEquals(copy.getCertificateChain(), Optional.empty());
        assertEquals(copy.getCertificateValidator(), null);
        assertEquals(copy.getMessageLimits(), null);
        assertEquals(copy.getChannelLifetime(), uint(0));
        assertEquals(copy.getAcknowledgeTimeout(), uint(12345));
    }

}
