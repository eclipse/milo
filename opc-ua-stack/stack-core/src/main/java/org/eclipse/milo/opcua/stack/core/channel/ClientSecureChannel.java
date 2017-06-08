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

package org.eclipse.milo.opcua.stack.core.channel;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.List;

import com.google.common.base.MoreObjects;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
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
    public ChannelSecurity.SecretKeys getEncryptionKeys(ChannelSecurity.SecuritySecrets secretKeys) {
        return secretKeys.getClientKeys();
    }

    @Override
    public ChannelSecurity.SecretKeys getDecryptionKeys(ChannelSecurity.SecuritySecrets secretKeys) {
        return secretKeys.getServerKeys();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("channelId", channelId)
            .add("securityPolicy", securityPolicy)
            .toString();
    }

}
