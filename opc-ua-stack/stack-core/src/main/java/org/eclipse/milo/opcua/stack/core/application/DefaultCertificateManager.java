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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;

public class DefaultCertificateManager implements CertificateManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<ByteString, KeyPair> privateKeys = Maps.newConcurrentMap();
    private final Map<ByteString, X509Certificate> certificates = Maps.newConcurrentMap();

    public DefaultCertificateManager() {
        this((KeyPair) null, null);
    }

    public DefaultCertificateManager(KeyPair privateKey, X509Certificate certificate) {
        this(newArrayList(privateKey), newArrayList(certificate));
    }

    public DefaultCertificateManager(List<KeyPair> privateKeys,
                                     List<X509Certificate> certificates) {

        Preconditions.checkState(privateKeys.size() == certificates.size(),
            "privateKeys.size() and certificates.size() must be equal");

        for (int i = 0; i < privateKeys.size(); i++) {
            KeyPair privateKey = privateKeys.get(0);
            X509Certificate certificate = certificates.get(0);

            if (privateKey != null && certificate != null) {
                try {
                    ByteString thumbprint = ByteString.of(DigestUtil.sha1(certificate.getEncoded()));

                    this.privateKeys.put(thumbprint, privateKey);
                    this.certificates.put(thumbprint, certificate);
                } catch (CertificateEncodingException e) {
                    logger.error("Error getting certificate thumbprint.", e);
                }
            }
        }
    }

    @Override
    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return Optional.ofNullable(privateKeys.get(thumbprint));
    }

    @Override
    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return Optional.ofNullable(certificates.get(thumbprint));
    }

    @Override
    public Set<KeyPair> getKeyPairs() {
        return Sets.newHashSet(privateKeys.values());
    }

    @Override
    public Set<X509Certificate> getCertificates() {
        return Sets.newHashSet(certificates.values());
    }

}
