/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.security;

import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.TrustListManager;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;

public class DefaultServerCertificateValidator implements ServerCertificateValidator {

    private final TrustListManager trustListManager;

    public DefaultServerCertificateValidator(TrustListManager trustListManager) {
        this.trustListManager = trustListManager;
    }

    @Override
    public void validateCertificateChain(List<X509Certificate> certificateChain) throws UaException {
        try {
            PKIXCertPathBuilderResult certPathResult = CertificateValidationUtil.buildTrustedCertPath(
                certificateChain,
                trustListManager.getTrustedCertificates(),
                trustListManager.getIssuerCertificates()
            );

            List<X509CRL> crls = new ArrayList<>();
            crls.addAll(trustListManager.getTrustedCrls());
            crls.addAll(trustListManager.getIssuerCrls());

            CertificateValidationUtil.validateTrustedCertPath(
                certPathResult.getCertPath(),
                certPathResult.getTrustAnchor(),
                crls,
                true
            );
        } catch (UaException e) {
            // servers need to report a less informative StatusCode if the
            // certificate was not trusted, either explicitly or because it
            // or one if its issuers was revoked.

            long statusCode = e.getStatusCode().getValue();

            if (statusCode == StatusCodes.Bad_CertificateUntrusted ||
                statusCode == StatusCodes.Bad_CertificateRevoked ||
                statusCode == StatusCodes.Bad_CertificateIssuerRevoked
            ) {

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e.getMessage(), e);
            } else {
                throw e;
            }
        }
    }

    @Override
    public void validateCertificateChain(
        List<X509Certificate> certificateChain,
        String applicationUri
    ) throws UaException {

        validateCertificateChain(certificateChain);

        X509Certificate certificate = certificateChain.get(0);

        CertificateValidationUtil.checkApplicationUri(certificate, applicationUri);
    }
}
