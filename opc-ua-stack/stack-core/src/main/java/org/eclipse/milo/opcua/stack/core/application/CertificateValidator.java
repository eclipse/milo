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

package org.eclipse.milo.opcua.stack.core.application;


import java.security.cert.X509Certificate;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.UaException;

public interface CertificateValidator {

    /**
     * Check that the provided certificate is valid.
     *
     * @param certificate the {@link X509Certificate} to check the validity of.
     * @throws UaException if {@code certificate} is invalid.
     */
    void validate(X509Certificate certificate) throws UaException;

    /**
     * Check that the provided certificate is trusted.
     *
     * @param certificate the {@link X509Certificate} to verify.
     * @param chain       the chain of intermediate certificates, if present, that lead to a trust anchor.
     * @throws UaException if {@code certificate} is not trusted.
     */
    void verifyTrustChain(X509Certificate certificate, List<X509Certificate> chain) throws UaException;

}
