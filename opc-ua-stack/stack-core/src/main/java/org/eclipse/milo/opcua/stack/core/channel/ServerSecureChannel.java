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
import java.util.Arrays;
import java.util.List;

import com.google.common.base.MoreObjects;
import io.netty.util.DefaultAttributeMap;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

public class ServerSecureChannel extends DefaultAttributeMap implements SecureChannel {

    private volatile long channelId = 0;
    private volatile ChannelSecurity channelSecurity;
    private volatile ByteString localNonce = ByteString.NULL_VALUE;
    private volatile ByteString remoteNonce = ByteString.NULL_VALUE;

    private volatile KeyPair keyPair;
    private volatile X509Certificate localCertificate;
    private volatile List<X509Certificate> localCertificateChain;

    private volatile X509Certificate remoteCertificate;
    private volatile List<X509Certificate> remoteCertificateChain;

    private volatile SecurityPolicy securityPolicy;
    private volatile MessageSecurityMode messageSecurityMode;
    private volatile EndpointDescription endpointDescription;

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public void setLocalNonce(ByteString localNonce) {
        this.localNonce = localNonce;
    }

    public void setRemoteNonce(ByteString remoteNonce) {
        this.remoteNonce = remoteNonce;
    }

    public void setChannelSecurity(ChannelSecurity channelSecurity) {
        this.channelSecurity = channelSecurity;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public void setLocalCertificate(X509Certificate localCertificate) {
        this.localCertificate = localCertificate;
    }

    public void setLocalCertificateChain(X509Certificate[] localCertificateChain) {
        this.localCertificateChain = Arrays.asList(localCertificateChain);
    }

    public void setRemoteCertificate(byte[] certificateBytes) throws UaException {
        remoteCertificate = CertificateUtil.decodeCertificate(certificateBytes);
        remoteCertificateChain = CertificateUtil.decodeCertificates(certificateBytes);
    }

    public void setSecurityPolicy(SecurityPolicy securityPolicy) {
        this.securityPolicy = securityPolicy;
    }

    public void setMessageSecurityMode(MessageSecurityMode messageSecurityMode) {
        this.messageSecurityMode = messageSecurityMode;
    }

    public void setEndpointDescription(EndpointDescription endpointDescription) {
        this.endpointDescription = endpointDescription;
    }

    public EndpointDescription getEndpointDescription() {
        return endpointDescription;
    }

    @Override
    public KeyPair getKeyPair() {
        return keyPair;
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
    public SecurityPolicy getSecurityPolicy() {
        return securityPolicy;
    }

    @Override
    public MessageSecurityMode getMessageSecurityMode() {
        return messageSecurityMode;
    }

    @Override
    public long getChannelId() {
        return channelId;
    }

    @Override
    public ChannelSecurity getChannelSecurity() {
        return channelSecurity;
    }

    @Override
    public ChannelSecurity.SecretKeys getEncryptionKeys(ChannelSecurity.SecuritySecrets secretKeys) {
        return secretKeys.getServerKeys();
    }

    @Override
    public ChannelSecurity.SecretKeys getDecryptionKeys(ChannelSecurity.SecuritySecrets secretKeys) {
        return secretKeys.getClientKeys();
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
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("channelId", channelId)
            .add("securityPolicy", securityPolicy)
            .toString();
    }

}
