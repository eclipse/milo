/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
