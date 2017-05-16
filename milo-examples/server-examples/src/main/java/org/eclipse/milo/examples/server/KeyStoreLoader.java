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

package org.eclipse.milo.examples.server;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class KeyStoreLoader {

    private static final String SERVER_ALIAS_2048 = "server-ai";
    private static final String SERVER_ALIAS_4096 = "server-ai-4096";
    private static final char[] PASSWORD = "password".toCharArray();

    private X509Certificate serverCertificate;
    private KeyPair serverKeyPair;

    public KeyStoreLoader load() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(getClass().getClassLoader().getResourceAsStream("server-example.pfx"), PASSWORD);

        Key serverPrivateKey = keyStore.getKey(SERVER_ALIAS_2048, PASSWORD);
        if (serverPrivateKey instanceof PrivateKey) {
            serverCertificate = (X509Certificate) keyStore.getCertificate(SERVER_ALIAS_2048);
            PublicKey serverPublicKey = serverCertificate.getPublicKey();
            serverKeyPair = new KeyPair(serverPublicKey, (PrivateKey) serverPrivateKey);
        }

        return this;
    }

    public X509Certificate getServerCertificate() {
        return serverCertificate;
    }

    public KeyPair getServerKeyPair() {
        return serverKeyPair;
    }

}
