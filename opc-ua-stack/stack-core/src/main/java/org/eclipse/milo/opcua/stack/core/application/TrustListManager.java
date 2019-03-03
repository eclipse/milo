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

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public interface TrustListManager {

    ImmutableList<X509CRL> getIssuerCrls();

    ImmutableList<X509CRL> getTrustedCrls();

    ImmutableList<X509Certificate> getIssuerCertificates();

    ImmutableList<X509Certificate> getTrustedCertificates();

    ImmutableList<X509Certificate> getRejectedCertificates();

    void addIssuerCertificate(X509Certificate certificate);

    void addTrustedCertificate(X509Certificate certificate);

    void addRejectedCertificate(X509Certificate certificate);

    boolean removeIssuerCertificate(ByteString thumbprint);

    boolean removeTrustedCertificate(ByteString thumbprint);

    boolean removeRejectedCertificate(ByteString thumbprint);

}
