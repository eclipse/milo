/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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

public interface CertificateValidator {

    /**
     * Check that trust can be established using the provided certificate chain and then validate
     * every certificate in the chain.
     * <p>
     * The chain must begin with the end-entity certificate at index 0 followed by the remaining
     * certificates in the chain, if any, in the correct order.
     *
     * @throws UaException if {@code certificateChain} is not trusted or validation fails.
     */
    void validateCertificateChain(List<X509Certificate> certificateChain) throws UaException;

}
