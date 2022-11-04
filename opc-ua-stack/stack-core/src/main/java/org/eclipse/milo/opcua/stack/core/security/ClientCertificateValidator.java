/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ClientCertificateValidator extends CertificateValidator {

    void validateCertificateChain(
        List<X509Certificate> certificateChain,
        String applicationUri,
        String... validHostNames
    ) throws UaException;

    class InsecureValidator implements ClientCertificateValidator {

        private static final Logger LOGGER = LoggerFactory.getLogger(InsecureValidator.class);

        @Override
        public void validateCertificateChain(
            List<X509Certificate> certificateChain,
            String applicationUri,
            String... validHostNames
        ) {

            X509Certificate certificate = certificateChain.get(0);

            LOGGER.warn("Skipping validation for certificate: {}", certificate.getSubjectX500Principal());
        }

        @Override
        public void validateCertificateChain(List<X509Certificate> certificateChain) {
            X509Certificate certificate = certificateChain.get(0);

            LOGGER.warn("Skipping validation for certificate: {}", certificate.getSubjectX500Principal());
        }

    }

}
