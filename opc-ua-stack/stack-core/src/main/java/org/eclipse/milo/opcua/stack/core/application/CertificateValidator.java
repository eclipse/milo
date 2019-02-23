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
     * Check that trust can be established using the provided certificate chain.
     * <p>
     * The chain must begin with the end-entity certificate at index 0 followed by the remaining certificates in the
     * chain, if any, in the correct order.
     *
     * @throws UaException if {@code certificate} is not trusted.
     */
    void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException;

}
