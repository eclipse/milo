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

import java.security.cert.X509Certificate;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCertificateValidator implements CertificateValidator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TrustListManager trustListManager;

    public DefaultCertificateValidator(TrustListManager trustListManager) {
        this.trustListManager = trustListManager;
    }

    @Override
    public synchronized void validate(X509Certificate certificate) throws UaException {
        try {
            CertificateValidationUtil.validateCertificateValidity(certificate);
        } catch (UaException e) {
            logger.debug("validation failed: {}", certificate.getSubjectX500Principal());
            trustListManager.addRejectedCertificate(certificate);
            throw e;
        }
    }

    @Override
    public synchronized void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {
        try {
            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                trustListManager.getTrustedCertificates(),
                trustListManager.getTrustedCrls(),
                trustListManager.getIssuerCertificates(),
                trustListManager.getIssuerCrls()
            );
        } catch (UaException e) {
            X509Certificate certificate = certificateChain.get(0);
            logger.debug("verification failed: {}", certificate.getSubjectX500Principal());
            certificateChain.forEach(trustListManager::addRejectedCertificate);
            throw e;
        }
    }

}
