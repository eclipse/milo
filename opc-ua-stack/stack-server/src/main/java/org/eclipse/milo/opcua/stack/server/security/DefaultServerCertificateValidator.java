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
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.TrustListManager;
import org.eclipse.milo.opcua.stack.core.util.validation.ValidationCheck;

import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.buildTrustedCertPath;
import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.checkApplicationUri;
import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.validateTrustedCertPath;

public class DefaultServerCertificateValidator implements ServerCertificateValidator {

    private final TrustListManager trustListManager;
    private final ImmutableSet<ValidationCheck> validationChecks;

    public DefaultServerCertificateValidator(TrustListManager trustListManager) {
        this(trustListManager, ValidationCheck.NO_OPTIONAL_CHECKS);
    }

    public DefaultServerCertificateValidator(
        TrustListManager trustListManager,
        Set<ValidationCheck> validationChecks
    ) {

        this.trustListManager = trustListManager;
        this.validationChecks = ImmutableSet.copyOf(validationChecks);
    }

    @Override
    public void validateCertificateChain(List<X509Certificate> certificateChain) throws UaException {
        try {
            PKIXCertPathBuilderResult certPathResult = buildTrustedCertPath(
                certificateChain,
                trustListManager.getTrustedCertificates(),
                trustListManager.getIssuerCertificates()
            );

            List<X509CRL> crls = new ArrayList<>();
            crls.addAll(trustListManager.getTrustedCrls());
            crls.addAll(trustListManager.getIssuerCrls());

            validateTrustedCertPath(
                certPathResult.getCertPath(),
                certPathResult.getTrustAnchor(),
                crls,
                validationChecks
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

        checkApplicationUri(certificate, applicationUri);
    }
}
