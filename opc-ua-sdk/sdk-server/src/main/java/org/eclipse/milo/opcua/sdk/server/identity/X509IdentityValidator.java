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
import java.util.List;
import java.util.Set;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.DefaultServerCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.MemoryCertificateQuarantine;
import org.eclipse.milo.opcua.stack.core.security.TrustListManager;
import org.eclipse.milo.opcua.stack.core.util.validation.ValidationCheck;
import org.jetbrains.annotations.Nullable;

public class X509IdentityValidator extends AbstractX509IdentityValidator {

    // TODO this shouldn't re-use DefaultServerCertificateValidator because
    //  the certificate requirements are not the same.

    private final CertificateValidator certificateValidator;

    private final TrustListManager trustListManager;

    public X509IdentityValidator(TrustListManager trustListManager) {
        this.trustListManager = trustListManager;

        certificateValidator = new DefaultServerCertificateValidator(
            trustListManager,
            Set.of(ValidationCheck.VALIDITY, ValidationCheck.REVOCATION),
            new MemoryCertificateQuarantine()
        );
    }


    @Override
    protected @Nullable Identity.X509UserIdentity authenticateCertificate(
        Session session,
        X509Certificate certificate
    ) {

        try {
            certificateValidator.validateCertificateChain(List.of(certificate), null, null);

            return new DefaultX509UserIdentity(certificate);
        } catch (UaException e) {
            return null;
        }
    }

}
