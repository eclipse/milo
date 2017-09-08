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

package org.eclipse.milo.opcua.stack.core.application;

import java.security.KeyPair;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultCertificateManager implements CertificateManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<ByteString, KeyPair> privateKeys = Maps.newConcurrentMap();
    private final Map<ByteString, X509Certificate[]> certificates = Maps.newConcurrentMap();

    public DefaultCertificateManager() {}

    public DefaultCertificateManager(KeyPair privateKey, X509Certificate certificate) {
        checkNotNull(privateKey, "privateKey must be non-null");
        checkNotNull(certificate, "certificate must be non-null");

        add(privateKey, certificate);
    }

    public DefaultCertificateManager(KeyPair privateKey, X509Certificate[] certificateChain) {
        checkNotNull(privateKey, "privateKey must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");

        add(privateKey, certificateChain);
    }

    public DefaultCertificateManager(List<KeyPair> privateKeys,
                                     List<X509Certificate> certificates) {

        Preconditions.checkState(privateKeys.size() == certificates.size(),
            "privateKeys.size() and certificates.size() must be equal");

        for (int i = 0; i < privateKeys.size(); i++) {
            KeyPair privateKey = privateKeys.get(i);
            X509Certificate certificate = certificates.get(i);

            add(privateKey, certificate);
        }
    }

    /**
     * Add a {@link KeyPair} and associated {@link X509Certificate} to this certificate manager.
     *
     * @param privateKey  the {@link KeyPair} containing with the public and private key.
     * @param certificate the {@link X509Certificate} the key pair is associated with.
     */
    public void add(KeyPair privateKey, X509Certificate certificate) {
        add(privateKey, new X509Certificate[]{certificate});
    }

    /**
     * Add a {@link KeyPair} and associated {@link X509Certificate} chain to this certificate manager.
     *
     * @param privateKey       the {@link KeyPair} containing the public and private key.
     * @param certificateChain the {@link X509Certificate} chain the key pair is associated with.
     */
    public void add(KeyPair privateKey, X509Certificate[] certificateChain) {
        checkNotNull(privateKey, "privateKey must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");

        try {
            X509Certificate certificate = certificateChain[0];
            ByteString thumbprint = ByteString.of(DigestUtil.sha1(certificate.getEncoded()));

            this.privateKeys.put(thumbprint, privateKey);
            this.certificates.put(thumbprint, certificateChain);
        } catch (CertificateEncodingException e) {
            logger.error("Error getting certificate thumbprint.", e);
        }
    }

    @Override
    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return Optional.ofNullable(privateKeys.get(thumbprint));
    }

    @Override
    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        X509Certificate[] chain = certificates.get(thumbprint);

        if (chain != null && chain.length > 0) {
            return Optional.of(chain[0]);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return Optional.ofNullable(certificates.get(thumbprint));
    }

    @Override
    public Set<KeyPair> getKeyPairs() {
        return Sets.newHashSet(privateKeys.values());
    }

    @Override
    public Set<X509Certificate> getCertificates() {
        return certificates.values().stream()
            .map(a -> a[0]).collect(Collectors.toSet());
    }

}
