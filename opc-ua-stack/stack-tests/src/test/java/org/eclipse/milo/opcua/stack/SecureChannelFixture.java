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

package org.eclipse.milo.opcua.stack;

import java.security.cert.X509Certificate;

import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ChannelSecurityToken;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.NonceUtil.generateNonce;

public abstract class SecureChannelFixture extends SecurityFixture {

    protected SecureChannel[] generateChannels(SecurityPolicy securityPolicy, MessageSecurityMode messageSecurity) throws Exception {
        super.setUp();

        ByteString clientNonce = generateNonce(securityPolicy);
        ByteString serverNonce = generateNonce(securityPolicy);

        ClientSecureChannel clientChannel = new ClientSecureChannel(
            securityPolicy == SecurityPolicy.None ? null : clientKeyPair,
            securityPolicy == SecurityPolicy.None ? null : clientCertificate,
            securityPolicy == SecurityPolicy.None ? null : newArrayList(clientCertificate),
            securityPolicy == SecurityPolicy.None ? null : serverCertificate,
            securityPolicy == SecurityPolicy.None ? null : newArrayList(serverCertificate),
            securityPolicy,
            messageSecurity
        );

        clientChannel.setLocalNonce(clientNonce);
        clientChannel.setRemoteNonce(serverNonce);

        ServerSecureChannel serverChannel = new ServerSecureChannel();
        serverChannel.setSecurityPolicy(securityPolicy);
        serverChannel.setMessageSecurityMode(messageSecurity);
        serverChannel.setLocalNonce(serverNonce);
        serverChannel.setRemoteNonce(clientNonce);

        switch (securityPolicy) {
            case None:
                break;

            case Basic128Rsa15:
            case Basic256:
            case Basic256Sha256:
            default:
                if (messageSecurity != MessageSecurityMode.None) {
                    ChannelSecurity.SecuritySecrets clientSecrets = ChannelSecurity.generateKeyPair(
                        clientChannel,
                        clientChannel.getLocalNonce(),
                        clientChannel.getRemoteNonce()
                    );

                    ChannelSecurityToken clientToken = new ChannelSecurityToken(
                        uint(0), uint(1), DateTime.now(), uint(60000));

                    clientChannel.setChannelSecurity(new ChannelSecurity(clientSecrets, clientToken));
                }


                serverChannel.setKeyPair(serverKeyPair);
                serverChannel.setLocalCertificate(serverCertificate);
                serverChannel.setLocalCertificateChain(new X509Certificate[]{serverCertificate});
                serverChannel.setRemoteCertificate(clientCertificateBytes);

                if (messageSecurity != MessageSecurityMode.None) {
                    ChannelSecurity.SecuritySecrets serverSecrets = ChannelSecurity.generateKeyPair(
                        serverChannel,
                        serverChannel.getRemoteNonce(),
                        serverChannel.getLocalNonce()
                    );

                    ChannelSecurityToken serverToken = new ChannelSecurityToken(
                        uint(0), uint(1), DateTime.now(), uint(60000));

                    serverChannel.setChannelSecurity(new ChannelSecurity(serverSecrets, serverToken));
                }

                break;
        }

        return new SecureChannel[]{clientChannel, serverChannel};
    }

}
