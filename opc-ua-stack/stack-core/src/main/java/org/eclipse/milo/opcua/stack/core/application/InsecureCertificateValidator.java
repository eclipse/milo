/*
 * Copyright (c) 2017 Kevin Herron
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.application;

import java.security.cert.X509Certificate;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link CertificateValidator} that does not actually validate or verify; all certificates are implicitly trusted.
 */
public class InsecureCertificateValidator implements CertificateValidator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void validate(X509Certificate certificate) throws UaException {
        logger.warn("Skipping validation for certificate: {}", certificate.getSubjectX500Principal());
    }

    @Override
    public void verifyTrustChain(X509Certificate certificate, List<X509Certificate> chain) throws UaException {
        logger.warn("Skipping trust chain verification for certificate: {}", certificate.getSubjectX500Principal());
    }

}
