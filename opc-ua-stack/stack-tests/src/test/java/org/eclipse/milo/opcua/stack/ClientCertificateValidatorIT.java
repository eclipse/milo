/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfigBuilder;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ClientCertificateValidatorIT extends StackIntegrationTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CountDownLatch latch = new CountDownLatch(2);

    private final CertificateValidator validator = new CertificateValidator() {
        @Override
        public void validate(X509Certificate certificate) throws UaException {
            logger.info("validate: {}", certificate.getSubjectX500Principal());
            latch.countDown();
        }

        @Override
        public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {
            X509Certificate certificate = certificateChain.get(0);
            logger.info("verifyTrustChain: {}", certificate.getSubjectX500Principal());
            latch.countDown();
        }
    };

    @Test
    public void testClientCertificateValidatorIsCalled() throws InterruptedException {
        assertTrue(latch.await(10, TimeUnit.SECONDS), "latch not obtained!");
    }

    @Override
    protected UaTcpStackClientConfigBuilder configureClient(UaTcpStackClientConfigBuilder builder) {
        return builder.setCertificateValidator(validator);
    }

    @Override
    protected EndpointDescription selectEndpoint(EndpointDescription[] endpoints) {
        return Arrays.stream(endpoints)
            .filter(e -> !SecurityPolicy.None.getSecurityPolicyUri().equals(e.getSecurityPolicyUri()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("no secure endpoint found!"));
    }

}
