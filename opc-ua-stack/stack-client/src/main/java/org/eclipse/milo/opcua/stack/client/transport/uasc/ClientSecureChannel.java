/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.client.transport.uasc;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.MoreObjects;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.client.UaStackClientConfig;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelSecurity;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;

public class ClientSecureChannel extends DefaultAttributeMap implements SecureChannel {

    public static final AttributeKey<LongSequence> KEY_REQUEST_ID_SEQUENCE =
        AttributeKey.valueOf("request-id-sequence");

    private volatile Channel channel;
    private volatile long channelId = 0;
    private volatile ChannelSecurity channelSecurity;
    private volatile ByteString localNonce = ByteString.NULL_VALUE;
    private volatile ByteString remoteNonce = ByteString.NULL_VALUE;

    private final KeyPair keyPair;
    private final X509Certificate localCertificate;
    private final List<X509Certificate> localCertificateChain;
    private final X509Certificate remoteCertificate;
    private final List<X509Certificate> remoteCertificateChain;
    private final SecurityPolicy securityPolicy;
    private final MessageSecurityMode messageSecurityMode;

    public ClientSecureChannel(SecurityPolicy securityPolicy, MessageSecurityMode messageSecurityMode) {
        this(null, null, null, null, null, securityPolicy, messageSecurityMode);
    }

    public ClientSecureChannel(KeyPair keyPair,
                               X509Certificate localCertificate,
                               List<X509Certificate> localCertificateChain,
                               X509Certificate remoteCertificate,
                               List<X509Certificate> remoteCertificateChain,
                               SecurityPolicy securityPolicy,
                               MessageSecurityMode messageSecurityMode) {

        this.keyPair = keyPair;
        this.localCertificate = localCertificate;
        this.localCertificateChain = localCertificateChain;
        this.remoteCertificate = remoteCertificate;
        this.remoteCertificateChain = remoteCertificateChain;
        this.securityPolicy = securityPolicy;
        this.messageSecurityMode = messageSecurityMode;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public void setChannelSecurity(ChannelSecurity channelSecurity) {
        this.channelSecurity = channelSecurity;
    }

    public void setLocalNonce(ByteString localNonce) {
        this.localNonce = localNonce;
    }

    public void setRemoteNonce(ByteString remoteNonce) {
        this.remoteNonce = remoteNonce;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public long getChannelId() {
        return channelId;
    }

    @Override
    public KeyPair getKeyPair() {
        return keyPair;
    }

    @Override
    public ByteString getLocalNonce() {
        return localNonce;
    }

    @Override
    public ByteString getRemoteNonce() {
        return remoteNonce;
    }

    @Override
    public SecurityPolicy getSecurityPolicy() {
        return securityPolicy;
    }

    @Override
    public MessageSecurityMode getMessageSecurityMode() {
        return messageSecurityMode;
    }

    @Override
    public X509Certificate getLocalCertificate() {
        return localCertificate;
    }

    @Override
    public List<X509Certificate> getLocalCertificateChain() {
        return localCertificateChain;
    }

    @Override
    public X509Certificate getRemoteCertificate() {
        return remoteCertificate;
    }

    @Override
    public List<X509Certificate> getRemoteCertificateChain() {
        return remoteCertificateChain;
    }

    @Override
    public ChannelSecurity getChannelSecurity() {
        return channelSecurity;
    }

    @Override
    public ChannelSecurity.SecretKeys getEncryptionKeys(ChannelSecurity.SecurityKeys securityKeys) {
        return securityKeys.getClientKeys();
    }

    @Override
    public ChannelSecurity.SecretKeys getDecryptionKeys(ChannelSecurity.SecurityKeys securityKeys) {
        return securityKeys.getServerKeys();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("channelId", channelId)
            .add("securityPolicy", securityPolicy)
            .toString();
    }

    public static ClientSecureChannel fromConfig(UaStackClientConfig config) throws UaException {
        EndpointDescription endpoint = config.getEndpoint();

        SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

        if (securityPolicy == SecurityPolicy.None) {
            return new ClientSecureChannel(
                securityPolicy,
                endpoint.getSecurityMode()
            );
        } else {
            KeyPair keyPair = config.getKeyPair().orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "no KeyPair configured")
            );

            X509Certificate certificate = config.getCertificate().orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "no certificate configured")
            );

            List<X509Certificate> certificateChain = Arrays.asList(
                config.getCertificateChain().orElseThrow(() ->
                    new UaException(
                        StatusCodes.Bad_ConfigurationError,
                        "no certificate chain configured"))
            );

            X509Certificate remoteCertificate = CertificateUtil
                .decodeCertificate(endpoint.getServerCertificate().bytes());

            List<X509Certificate> remoteCertificateChain = CertificateUtil
                .decodeCertificates(endpoint.getServerCertificate().bytes());

            return new ClientSecureChannel(
                keyPair,
                certificate,
                certificateChain,
                remoteCertificate,
                remoteCertificateChain,
                securityPolicy,
                endpoint.getSecurityMode()
            );
        }
    }

}
