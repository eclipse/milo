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
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.Set;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public interface CertificateManager {

    /**
     * Get the {@link KeyPair} belonging to the certificate identified by {@code thumbprint}.
     * <p>
     * {@code thumbprint} is a SHA1 hash of the encoded certificate.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link KeyPair} belonging to the certificate identified by {@code thumbprint}.
     */
    Optional<KeyPair> getKeyPair(ByteString thumbprint);

    /**
     * Get the {@link X509Certificate} identified by {@code thumbprint}.
     * <p>
     * {@code thumbprint} is a SHA1 hash of the encoded certificate.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link X509Certificate} identified by {@code thumbprint}.
     */
    Optional<X509Certificate> getCertificate(ByteString thumbprint);

    /**
     * Get the {@link X509Certificate} identified by {@code thumbprint} as well as any certificates in its chain.
     *
     * @param thumbprint the thumbprint of the certificate.
     * @return the {@link X509Certificate} identified by {@code thumbprint} as well as any certificates in its chain.
     */
    Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint);

    /**
     * @return the Set of all managed {@link KeyPair}s.
     */
    Set<KeyPair> getKeyPairs();

    /**
     * @return the Set of all managed {@link X509Certificate}s.
     */
    Set<X509Certificate> getCertificates();

}
