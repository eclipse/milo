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

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.junit.jupiter.api.Test;

public class DefaultCertificateManagerTest {

    @Test
    public void testNullPrivateKeyOrCertificateFails() {
    	assertThrows(
    		Exception.class,
            () -> new DefaultCertificateManager((KeyPair) null, (X509Certificate) null)
        );

    	assertThrows(
            Exception.class,
            () -> new DefaultCertificateManager().add(null, (X509Certificate) null)
        );
    }

}
