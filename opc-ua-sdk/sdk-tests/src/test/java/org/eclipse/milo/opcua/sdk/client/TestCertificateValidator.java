/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;

public class TestCertificateValidator implements CertificateValidator {

    private final Set<X509Certificate> trustedCertificates = Sets.newConcurrentHashSet();

    public TestCertificateValidator(X509Certificate certificate) {
        trustedCertificates.add(certificate);
    }

    public TestCertificateValidator(X509Certificate... certificates) {
        Collections.addAll(trustedCertificates, certificates);
    }

    @Override
    public void validate(X509Certificate certificate) throws UaException {
        CertificateValidationUtil.validateCertificateValidity(certificate);
    }

    @Override
    public void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {
        CertificateValidationUtil.verifyTrustChain(certificateChain, trustedCertificates, Sets.newHashSet());
    }

}
