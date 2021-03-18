/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.identity;

import java.security.cert.X509Certificate;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.jetbrains.annotations.Nullable;

public class X509IdentityValidator extends AbstractX509IdentityValidator {

    private final Predicate<X509Certificate> predicate;

    public X509IdentityValidator(Predicate<X509Certificate> predicate) {
        this.predicate = predicate;
    }

    @Nullable
    @Override
    protected Object authenticateIdentityCertificate(Session session, X509Certificate identityCertificate) {
        if (predicate.test(identityCertificate)) {
            return identityCertificate;
        } else {
            return null;
        }
    }

}
