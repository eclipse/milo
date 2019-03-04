/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultCertificateManager implements CertificateManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<ByteString, KeyPair> privateKeys = new HashMap<>();
    private final Map<ByteString, X509Certificate[]> certificates = new HashMap<>();

    public DefaultCertificateManager() {}

    public DefaultCertificateManager(KeyPair keyPair, X509Certificate certificate) {
        checkNotNull(keyPair, "keyPair must be non-null");
        checkNotNull(certificate, "certificate must be non-null");

        add(keyPair, certificate);
    }

    public DefaultCertificateManager(KeyPair keyPair, X509Certificate[] certificateChain) {
        checkNotNull(keyPair, "keyPair must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");

        add(keyPair, certificateChain);
    }

    /**
     * Add a {@link KeyPair} and associated {@link X509Certificate} to this certificate manager.
     *
     * @param keyPair     the {@link KeyPair} containing with the public and private key.
     * @param certificate the {@link X509Certificate} the key pair is associated with.
     */
    public synchronized void add(KeyPair keyPair, X509Certificate certificate) {
        add(keyPair, new X509Certificate[]{certificate});
    }

    /**
     * Add a {@link KeyPair} and associated {@link X509Certificate} chain to this certificate manager.
     *
     * @param keyPair          the {@link KeyPair} containing the public and private key.
     * @param certificateChain the {@link X509Certificate} chain the key pair is associated with.
     */
    public synchronized void add(KeyPair keyPair, X509Certificate[] certificateChain) {
        checkNotNull(keyPair, "keyPair must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");

        try {
            X509Certificate certificate = certificateChain[0];
            ByteString thumbprint = ByteString.of(DigestUtil.sha1(certificate.getEncoded()));

            this.privateKeys.put(thumbprint, keyPair);
            this.certificates.put(thumbprint, certificateChain);
        } catch (CertificateEncodingException e) {
            logger.error("Error getting certificate thumbprint.", e);
        }
    }

    public synchronized void remove(ByteString thumbprint) {
        privateKeys.remove(thumbprint);
        certificates.remove(thumbprint);
    }

    public synchronized void replace(ByteString thumbprint, KeyPair keyPair, X509Certificate[] certificateChain) {
        remove(thumbprint);

        add(keyPair, certificateChain);
    }

    @Override
    public synchronized Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return Optional.ofNullable(privateKeys.get(thumbprint));
    }

    @Override
    public synchronized Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        X509Certificate[] chain = certificates.get(thumbprint);

        if (chain != null && chain.length > 0) {
            return Optional.of(chain[0]);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public synchronized Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return Optional.ofNullable(certificates.get(thumbprint));
    }

    @Override
    public synchronized Set<KeyPair> getKeyPairs() {
        return Sets.newHashSet(privateKeys.values());
    }

    @Override
    public synchronized Set<X509Certificate> getCertificates() {
        return certificates.values().stream()
            .map(a -> a[0]).collect(Collectors.toSet());
    }

}
