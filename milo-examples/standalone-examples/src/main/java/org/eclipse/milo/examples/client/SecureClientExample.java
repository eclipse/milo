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

package org.eclipse.milo.examples.client;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateBuilder;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class SecureClientExample {

    private Logger logger = LoggerFactory.getLogger(SecureClientExample.class);

    protected KeyPair keyPair;
    protected X509Certificate certificate;

    String getDiscoveryEndpointUrl() {
        return "opc.tcp://localhost:4840/discovery";
    }

    SecurityPolicy getSecurityPolicy() {
        return SecurityPolicy.Basic256Sha256;
    }

    MessageSecurityMode getMessageSecurityMode() {
        return MessageSecurityMode.SignAndEncrypt;
    }

    IdentityProvider getIdentityProvider() {
        return new AnonymousProvider();
    }

    abstract void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception;

    X509Certificate getClientCertificate() {
        if (certificate == null) {
            generateSelfSignedCertificate();
        }
        return certificate;
    }

    KeyPair getKeyPair() {
        if (keyPair == null) {
            generateSelfSignedCertificate();
        }
        return keyPair;
    }

    protected void generateSelfSignedCertificate() {
        //Generate self-signed certificate
        try {
            keyPair = SelfSignedCertificateGenerator.generateRsaKeyPair(2048);
        } catch (NoSuchAlgorithmException n) {
            logger.error("Could not generate RSA Key Pair.", n);
            System.exit(1);
        }

        SelfSignedCertificateBuilder builder = new SelfSignedCertificateBuilder(keyPair)
                .setCommonName("Eclipse Milo Example Client")
                .setOrganization("digitalpetri")
                .setOrganizationalUnit("dev")
                .setLocalityName("Folsom")
                .setStateName("CA")
                .setCountryCode("US")
                .setApplicationUri("urn:eclipse:milo:examples:client");

        try {
            certificate = builder.build();
        } catch (Exception e) {
            logger.error("Could not build certificate.", e);
            System.exit(1);
        }
    }
}
