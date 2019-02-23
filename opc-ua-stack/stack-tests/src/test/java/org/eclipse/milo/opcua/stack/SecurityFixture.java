/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.testng.annotations.BeforeSuite;

public abstract class SecurityFixture {

    private static final String CLIENT_ALIAS = "client-test-certificate";
    private static final String SERVER_ALIAS = "server-test-certificate";
    private static final char[] PASSWORD = "test".toCharArray();

    protected volatile X509Certificate clientCertificate;
    protected volatile byte[] clientCertificateBytes;
    protected volatile KeyPair clientKeyPair;

    protected volatile X509Certificate serverCertificate;
    protected volatile byte[] serverCertificateBytes;
    protected volatile KeyPair serverKeyPair;

    protected volatile CertificateManager serverCertificateManager;
    protected volatile CertificateValidator serverCertificateValidator;

    @BeforeSuite
    public void setUp() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        keyStore.load(getClass().getClassLoader().getResourceAsStream("test-keystore.pfx"), PASSWORD);

        Key clientPrivateKey = keyStore.getKey(CLIENT_ALIAS, PASSWORD);
        if (clientPrivateKey instanceof PrivateKey) {
            clientCertificate = (X509Certificate) keyStore.getCertificate(CLIENT_ALIAS);
            clientCertificateBytes = clientCertificate.getEncoded();

            PublicKey clientPublicKey = clientCertificate.getPublicKey();
            clientKeyPair = new KeyPair(clientPublicKey, (PrivateKey) clientPrivateKey);
        }

        Key serverPrivateKey = keyStore.getKey(SERVER_ALIAS, PASSWORD);
        if (serverPrivateKey instanceof PrivateKey) {
            serverCertificate = (X509Certificate) keyStore.getCertificate(SERVER_ALIAS);
            serverCertificateBytes = serverCertificate.getEncoded();

            PublicKey serverPublicKey = serverCertificate.getPublicKey();
            serverKeyPair = new KeyPair(serverPublicKey, (PrivateKey) serverPrivateKey);
        }

        serverCertificateManager = new TestCertificateManager(
            serverKeyPair,
            serverCertificate
        );

        serverCertificateValidator = new TestCertificateValidator(clientCertificate);
    }

}
