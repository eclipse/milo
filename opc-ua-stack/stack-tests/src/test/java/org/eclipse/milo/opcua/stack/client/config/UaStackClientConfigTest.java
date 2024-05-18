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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.stack.SecurityFixture;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.client.security.ClientCertificateValidator;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.junit.jupiter.api.Test;

public class UaStackClientConfigTest extends SecurityFixture {

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

    private final ClientCertificateValidator validator = new ClientCertificateValidator() {

        @Override
        public void validateCertificateChain(List<X509Certificate> certificateChain) {}

        @Override
        public void validateCertificateChain(
            List<X509Certificate> certificateChain,
            String applicationUri,
            String... validHostNames
        ) {}

    };

    @Test
    public void testCopy() {
        UaStackClientConfig original = UaStackClientConfig.builder()
            .setEndpoint(endpoint)
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateChain(new X509Certificate[]{clientCertificate})
            .setCertificateValidator(validator)
            .setEncodingLimits(EncodingLimits.DEFAULT)
            .setChannelLifetime(uint(1234))
            .setExecutor(Stack.sharedExecutor())
            .setEventLoop(Stack.sharedEventLoop())
            .setWheelTimer(Stack.sharedWheelTimer())
            .setAcknowledgeTimeout(uint(12345))
            .build();

        UaStackClientConfig copy = UaStackClientConfig.copy(original).build();

        assertEquals(original.getKeyPair(), copy.getKeyPair());
        assertEquals(original.getCertificate(), copy.getCertificate());
        assertEquals(original.getCertificateChain(), copy.getCertificateChain());
        assertEquals(original.getCertificateValidator(), copy.getCertificateValidator());
        assertEquals(original.getEncodingLimits(), copy.getEncodingLimits());
        assertEquals(original.getChannelLifetime(), copy.getChannelLifetime());
        assertEquals(original.getExecutor(), copy.getExecutor());
        assertEquals(original.getEventLoop(), copy.getEventLoop());
        assertEquals(original.getWheelTimer(), copy.getWheelTimer());
        assertEquals(original.getAcknowledgeTimeout(), copy.getAcknowledgeTimeout());
    }

    @Test
    public void testCopyAndModify() {
        UaStackClientConfig original = UaStackClientConfig.builder()
            .setEndpoint(endpoint)
            .setKeyPair(clientKeyPair)
            .setCertificate(clientCertificate)
            .setCertificateValidator(validator)
            .setEncodingLimits(EncodingLimits.DEFAULT)
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
                    .setEncodingLimits(null)
                    .setChannelLifetime(uint(0))
                    .setAcknowledgeTimeout(uint(12345))
        );

        assertEquals(Optional.empty(), copy.getKeyPair());
        assertEquals(Optional.empty(), copy.getCertificate());
        assertEquals(Optional.empty(), copy.getCertificateChain());
        assertEquals(null, copy.getCertificateValidator());
        assertEquals(null, copy.getEncodingLimits());
        assertEquals(uint(0), copy.getChannelLifetime());
        assertEquals(uint(12345), copy.getAcknowledgeTimeout());
    }

}
