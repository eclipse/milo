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

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.X509IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SecureReadExample implements ClientExample {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    SecurityPolicy securityPolicy = SecurityPolicy.valueOf("Basic256Sha256");
    IdentityProvider identityProvider;

    public static void main(String[] args) throws Exception {
        SecureReadExample example = new SecureReadExample();
        new ClientExampleRunner(example).run();
    }


    @Override
    public SecurityPolicy getSecurityPolicy() {
        return securityPolicy;
    }

    @Override
    public IdentityProvider getIdentityProvider(){
        return identityProvider;
    }

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {

        /* Get keystore password */
//        Console console = System.console();
//        if (console == null) {
//            System.out.println("Couldn't get Console instance");
//            System.exit(0);
//        }
//        char[] passwordArray = console.readPassword("Enter your secret password: ");
        char[] passwordArray = "opcuakeystore".toCharArray();

        /*Get key from keystore */
        File file = new File(System.getProperty("user.home") + File.separatorChar + "opcua.keystore");
        FileInputStream is = new FileInputStream(file);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, passwordArray);
        is.close();
        PrivateKey key = (PrivateKey)keystore.getKey("opcuakey", passwordArray);

        /* Get certificate of public key */
        X509Certificate cert = (X509Certificate) keystore.getCertificate("opcuakey");


        identityProvider = new X509IdentityProvider(cert, key);

        // synchronous connect
        client.connect().get();

        // synchronous read request via VariableNode
        VariableNode node = client.getAddressSpace().createVariableNode(Identifiers.Server_ServerStatus_StartTime);
        DataValue value = node.readValue().get();

        logger.info("StartTime={}", value.getValue().getValue());

        // asynchronous read request
        readServerStateAndTime(client).thenAccept(values -> {
            DataValue v0 = values.get(0);
            DataValue v1 = values.get(1);

            logger.info("State={}", ServerState.from((Integer) v0.getValue().getValue()));
            logger.info("CurrentTime={}", v1.getValue().getValue());

            future.complete(client);
        });
    }

    private CompletableFuture<List<DataValue>> readServerStateAndTime(OpcUaClient client) {
        List<NodeId> nodeIds = ImmutableList.of(
            Identifiers.Server_ServerStatus_State,
            Identifiers.Server_ServerStatus_CurrentTime);

        return client.readValues(0.0, TimestampsToReturn.Both, nodeIds);
    }

}
