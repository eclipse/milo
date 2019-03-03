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

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public interface TrustListManager {

    ImmutableList<X509CRL> getIssuerCrls();

    ImmutableList<X509CRL> getTrustedCrls();

    ImmutableList<X509Certificate> getIssuerCertificates();

    ImmutableList<X509Certificate> getTrustedCertificates();

    ImmutableList<X509Certificate> getRejectedCertificates();

    /**
     * Set a new list of Issuer CRLs. This replaces any existing Issuer CRLs.
     *
     * @param issuerCrls a new list of issuer {@link X509CRL}s.
     */
    void setIssuerCrls(List<X509CRL> issuerCrls);

    /**
     * Set a new list of Trusted CRLs. This replaces any existing Trusted CRLs.
     *
     * @param trustedCrls a new list of trusted {@link X509CRL}s.
     */
    void setTrustedCrls(List<X509CRL> trustedCrls);

    /**
     * Set a new list of Issuer Certificates. This replaces any existing Issuer Certificates.
     *
     * @param issuerCertificates a new list of issuer {@link X509Certificate}s.
     */
    void setIssuerCertificates(List<X509Certificate> issuerCertificates);

    /**
     * Set a new list of Trusted Certificates. This replaces any existing Trusted Certificates.
     *
     * @param trustedCertificates a new list of trusted {@link X509Certificate}s.
     */
    void setTrustedCertificates(List<X509Certificate> trustedCertificates);

    void addIssuerCertificate(X509Certificate certificate);

    void addTrustedCertificate(X509Certificate certificate);

    void addRejectedCertificate(X509Certificate certificate);

    boolean removeIssuerCertificate(ByteString thumbprint);

    boolean removeTrustedCertificate(ByteString thumbprint);

    boolean removeRejectedCertificate(ByteString thumbprint);

}
