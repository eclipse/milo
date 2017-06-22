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

package org.eclipse.milo.opcua.stack.examples;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.examples.client.ClientExample;
import org.eclipse.milo.opcua.stack.examples.server.ServerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class ClientServerExample {

    private static final String CLIENT_ALIAS = "client-certificate";
    private static final String SERVER_ALIAS = "server-certificate";
    private static final char[] PASSWORD = "test".toCharArray();

    public static void main(String[] args) throws Exception {
        new ClientServerExample();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ClientServerExample() throws Exception {
        KeyStoreLoader loader = new KeyStoreLoader().load();

        ServerExample serverExample = new ServerExample(loader.getServerCertificate(), loader.getServerKeyPair());
        serverExample.startup();

        ClientExample client = new ClientExample(loader.getClientCertificate(), loader.getClientKeyPair());

        for (int i = 0; i < 5; i++) {
            NodeId nodeId = new NodeId(2, i);
            logger.info("Sending synchronous TestStackRequest NodeId={}", i);
            ReadResponse response = client.testStack(nodeId).get();
            logger.info("Received TestStackResponse value={}", l(response.getResults()).get(0).getValue());
        }

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            NodeId nodeId = new NodeId(2, i);
            logger.info("Sending asynchronous TestStackRequest NodeId={}", nodeId);

            CompletableFuture<ReadResponse> future = client.testStack(nodeId);

            future.whenComplete((response, ex) -> {
                if (response != null) {
                    logger.info("Received TestStackResponse value={}", l(response.getResults()).get(0).getValue());
                } else {
                    logger.error("Error: {}", ex.getMessage(), ex);
                }
            });

            futures.add(future);
        }

        CompletableFuture<?> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture<?>[futures.size()]));

        all.whenComplete((r, ex) -> {
            try {
                client.disconnect().get();
                serverExample.shutdown();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            Stack.releaseSharedResources();
        });
    }

    private static class KeyStoreLoader {

        private X509Certificate serverCertificate;
        private KeyPair serverKeyPair;
        private X509Certificate clientCertificate;
        private KeyPair clientKeyPair;

        public KeyStoreLoader load() throws Exception {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(getClass().getClassLoader().getResourceAsStream("example-keystore.pfx"), PASSWORD);

            Key serverPrivateKey = keyStore.getKey(SERVER_ALIAS, PASSWORD);
            if (serverPrivateKey instanceof PrivateKey) {
                serverCertificate = (X509Certificate) keyStore.getCertificate(SERVER_ALIAS);
                PublicKey serverPublicKey = serverCertificate.getPublicKey();
                serverKeyPair = new KeyPair(serverPublicKey, (PrivateKey) serverPrivateKey);
            }

            Key clientPrivateKey = keyStore.getKey(CLIENT_ALIAS, PASSWORD);
            if (clientPrivateKey instanceof PrivateKey) {
                clientCertificate = (X509Certificate) keyStore.getCertificate(CLIENT_ALIAS);
                PublicKey clientPublicKey = clientCertificate.getPublicKey();
                clientKeyPair = new KeyPair(clientPublicKey, (PrivateKey) clientPrivateKey);
            }

            return this;
        }

        public X509Certificate getServerCertificate() {
            return serverCertificate;
        }

        public KeyPair getServerKeyPair() {
            return serverKeyPair;
        }

        public X509Certificate getClientCertificate() {
            return clientCertificate;
        }

        public KeyPair getClientKeyPair() {
            return clientKeyPair;
        }

    }


}
