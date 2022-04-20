/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.security.Security;

import io.netty.buffer.ByteBufUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.testng.Assert.assertEquals;

class CurveUtilTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @ParameterizedTest
    @EnumSource(value = SecurityPolicy.class, names = {"ECC_curve25519", "ECC_nistP256"})
    public void clientServerExchange(SecurityPolicy securityPolicy) throws UaException {
        CurveUtil.EcdhExchange client = CurveUtil.newEcdhExchange(securityPolicy, "opcua-client");
        CurveUtil.EcdhExchange server = CurveUtil.newEcdhExchange(securityPolicy, "opcua-server");

        // client sends nonce to server
        server.setRemoteNonce(client.getLocalNonce());

        // server sends nonce to client
        client.setRemoteNonce(server.getLocalNonce());

        client.calculateSecret(securityPolicy);
        byte[] csx = client.getSecretX();
        byte[] csy = client.getSecretY();

        server.calculateSecret(securityPolicy);
        byte[] ssx = server.getSecretX();
        byte[] ssy = server.getSecretY();

        System.out.println("client secret x: " + ByteBufUtil.hexDump(csx));
        System.out.println("server secret x: " + ByteBufUtil.hexDump(ssx));

        System.out.println("client secret y: " + ByteBufUtil.hexDump(csy));
        System.out.println("server secret y: " + ByteBufUtil.hexDump(ssy));

        assertEquals(csx, ssx);
        assertEquals(csy, ssy);
    }

    @Test
    public void eccKeyExchange() throws Exception {
        var client = new EccKeyExchange.ClientKeyExchange();
        var server = new EccKeyExchange.ServerKeyExchange();

        client.setServerNonce(server.getServerNonce());
        server.setClientNonce(client.getClientNonce());

        ChannelSecurity.SecretKeys clientClientKeys = client.generateClientKeys();
        ChannelSecurity.SecretKeys clientServerKeys = client.generateServerKeys();
        {
            System.out.println("--- client ---");
            System.out.println("client signatureKey: " + ByteBufUtil.hexDump(clientClientKeys.getSignatureKey()));
            System.out.println("client encryptionKey: " + ByteBufUtil.hexDump(clientClientKeys.getEncryptionKey()));
            System.out.println("client initializationVector: " + ByteBufUtil.hexDump(clientClientKeys.getInitializationVector()));
            System.out.println("server signatureKey: " + ByteBufUtil.hexDump(clientServerKeys.getSignatureKey()));
            System.out.println("server encryptionKey: " + ByteBufUtil.hexDump(clientServerKeys.getEncryptionKey()));
            System.out.println("server initializationVector: " + ByteBufUtil.hexDump(clientServerKeys.getInitializationVector()));
        }

        ChannelSecurity.SecretKeys serverClientKeys = server.generateClientKeys();
        ChannelSecurity.SecretKeys serverServerKeys = server.generateServerKeys();
        {
            System.out.println("--- server ---");
            System.out.println("client signatureKey: " + ByteBufUtil.hexDump(serverClientKeys.getSignatureKey()));
            System.out.println("client encryptionKey: " + ByteBufUtil.hexDump(serverClientKeys.getEncryptionKey()));
            System.out.println("client initializationVector: " + ByteBufUtil.hexDump(serverClientKeys.getInitializationVector()));
            System.out.println("server signatureKey: " + ByteBufUtil.hexDump(serverServerKeys.getSignatureKey()));
            System.out.println("server encryptionKey: " + ByteBufUtil.hexDump(serverServerKeys.getEncryptionKey()));
            System.out.println("server initializationVector: " + ByteBufUtil.hexDump(serverServerKeys.getInitializationVector()));
        }

        assertEquals(clientClientKeys.getSignatureKey(), serverClientKeys.getSignatureKey());
        assertEquals(clientClientKeys.getEncryptionKey(), serverClientKeys.getEncryptionKey());
        assertEquals(clientClientKeys.getInitializationVector(), serverClientKeys.getInitializationVector());

        assertEquals(clientServerKeys.getSignatureKey(), serverServerKeys.getSignatureKey());
        assertEquals(clientServerKeys.getEncryptionKey(), serverServerKeys.getEncryptionKey());
        assertEquals(clientServerKeys.getInitializationVector(), serverServerKeys.getInitializationVector());
    }

}
