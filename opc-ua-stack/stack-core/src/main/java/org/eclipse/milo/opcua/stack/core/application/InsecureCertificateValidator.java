/*
 * Copyright (c) 2017 Kevin Herron
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
    public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {
        X509Certificate certificate = certificateChain.get(0);

        logger.warn("Skipping trust chain verification for certificate: {}", certificate.getSubjectX500Principal());
    }

}
