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

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.X509IdentityProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;


public class SecureClientExample implements ClientExample{

    SecurityPolicy securityPolicy = SecurityPolicy.valueOf("certificateSignatureAlgorithm");
    IdentityProvider identityProvider;

    public SecureClientExample(X509Certificate certificate, PrivateKey privateKey){
        identityProvider = new X509IdentityProvider(certificate, privateKey);
    }


    public SecurityPolicy getSecurityPolicy() {
        return securityPolicy;
    }

    public IdentityProvider getIdentityProvider(){
        return identityProvider;
    }

    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception{

    };

}
