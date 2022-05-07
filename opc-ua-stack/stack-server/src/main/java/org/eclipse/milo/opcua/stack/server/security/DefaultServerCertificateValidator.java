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
import org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil;
import org.eclipse.milo.opcua.stack.core.util.validation.ValidationCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.buildTrustedCertPath;
import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.validateTrustedCertPath;

public class DefaultServerCertificateValidator implements ServerCertificateValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultServerCertificateValidator.class);

    private final TrustListManager trustListManager;
    private final ImmutableSet<ValidationCheck> validationChecks;

    /**
     * Create a {@link DefaultServerCertificateValidator} that performs no optional validation checks.
     *
     * @param trustListManager the configured {@link TrustListManager}.
     */
    public DefaultServerCertificateValidator(TrustListManager trustListManager) {
        this(trustListManager, ValidationCheck.NO_OPTIONAL_CHECKS);
    }

    /**
     * Create a {@link DefaultServerCertificateValidator} that performs a given set of optional validation checks.
     *
     * @param trustListManager the configured {@link TrustListManager}.
     * @param validationChecks the set of optional {@link ValidationCheck}s to perform.
     */
    public DefaultServerCertificateValidator(
        TrustListManager trustListManager,
        Set<ValidationCheck> validationChecks
    ) {

        this.trustListManager = trustListManager;
        this.validationChecks = ImmutableSet.copyOf(validationChecks);
    }

    @Override
    public void validateCertificateChain(List<X509Certificate> certificateChain) throws UaException {
        PKIXCertPathBuilderResult certPathResult;

        try {
            certPathResult = buildTrustedCertPath(
                certificateChain,
                trustListManager.getTrustedCertificates(),
                trustListManager.getIssuerCertificates()
            );
        } catch (UaException e) {
            certificateChain.forEach(trustListManager::addRejectedCertificate);

            long statusCode = e.getStatusCode().getValue();

            LOGGER.debug("validateCertificateChain failed, underlying status: {}", statusCode, e);

            if (statusCode == StatusCodes.Bad_CertificateUntrusted) {
                // servers need to report a less informative StatusCode if the
                // certificate was not trusted, either explicitly or because it
                // or one if its issuers was revoked.

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
            } else {
                throw new UaException(e.getStatusCode());
            }
        }

        try {
            List<X509CRL> crls = new ArrayList<>();
            crls.addAll(trustListManager.getTrustedCrls());
            crls.addAll(trustListManager.getIssuerCrls());

            validateTrustedCertPath(
                certPathResult.getCertPath(),
                certPathResult.getTrustAnchor(),
                crls,
                validationChecks,
                true
            );
        } catch (UaException e) {
            long statusCode = e.getStatusCode().getValue();

            LOGGER.debug("validateCertificateChain failed, underlying status: {}", statusCode, e);

            if (statusCode == StatusCodes.Bad_CertificateRevoked ||
                statusCode == StatusCodes.Bad_CertificateIssuerRevoked
            ) {
                // servers need to report a less informative StatusCode if the
                // certificate was not trusted, either explicitly or because it
                // or one if its issuers was revoked.

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
            } else {
                throw new UaException(e.getStatusCode());
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

        try {
            CertificateValidationUtil.checkApplicationUri(certificate, applicationUri);
        } catch (UaException e) {
            if (validationChecks.contains(ValidationCheck.APPLICATION_URI)) {
                throw e;
            } else {
                LOGGER.warn(
                    "check suppressed: certificate failed application uri check: {} != {}",
                    applicationUri, CertificateValidationUtil.getSubjectAltNameUri(certificate)
                );
            }
        }
    }
}
