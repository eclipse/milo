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
import java.security.cert.X509Certificate;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;


public interface SecureClientExample {

    default String getDiscoveryEndpointUrl() {
        return "opc.tcp://localhost:4840/discovery";
    }

    default SecurityPolicy getSecurityPolicy() {
        return SecurityPolicy.Basic256Sha256;
    }

    default MessageSecurityMode getMessageSecurityMode() {
        return MessageSecurityMode.SignAndEncrypt;
    }

    default IdentityProvider getIdentityProvider() {
        return new AnonymousProvider();
    }

    void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception;

    /**
     * Only needs to be implemented if the used identity provider is a X509IdentityProvider
     */
    default X509Certificate getClientCertificate() {
        return null;
    }

    /**
     * Only needs to be implemented if the used identity provider is a X509IdentityProvider
     */
    default KeyPair getKeyPair() {
        return null;
    }

}
