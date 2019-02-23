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

import com.google.common.primitives.Bytes;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;

public class X509IdentityValidator extends AbstractIdentityValidator {

    private final Predicate<X509Certificate> predicate;

    public X509IdentityValidator(Predicate<X509Certificate> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Object validateX509Token(
        Session session,
        X509IdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        ByteString clientCertificateBs = token.getCertificateData();
        X509Certificate identityCertificate = CertificateUtil.decodeCertificate(clientCertificateBs.bytesOrEmpty());

        // verify the algorithm matches the one specified by the tokenPolicy or else the channel itself
        if (tokenPolicy.getSecurityPolicyUri() != null) {
            SecurityPolicy securityPolicy = SecurityPolicy.fromUri(tokenPolicy.getSecurityPolicyUri());

            if (!securityPolicy.getAsymmetricSignatureAlgorithm().getUri().equals(tokenSignature.getAlgorithm())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "algorithm in token signature did not match algorithm specified by token policy");
            }
        } else {
            SecurityPolicy securityPolicy = session.getSecurityConfiguration().getSecurityPolicy();

            if (!securityPolicy.getAsymmetricSignatureAlgorithm().getUri().equals(tokenSignature.getAlgorithm())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "algorithm in token signature did not match algorithm specified by secure channel");
            }
        }

        SecurityAlgorithm algorithm = SecurityAlgorithm.fromUri(tokenSignature.getAlgorithm());

        if (algorithm != SecurityAlgorithm.None) {
            verifySignature(
                session,
                tokenSignature,
                identityCertificate,
                algorithm
            );
        }

        if (predicate.test(identityCertificate)) {
            return identityCertificate;
        } else {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    private void verifySignature(
        Session session,
        SignatureData tokenSignature,
        X509Certificate identityCertificate,
        SecurityAlgorithm algorithm) throws UaException {

        ByteString serverCertificateBs = session
            .getSecurityConfiguration()
            .getServerCertificateBytes();
        
        ByteString lastNonceBs = session.getLastNonce();

        byte[] dataBytes = Bytes.concat(serverCertificateBs.bytesOrEmpty(), lastNonceBs.bytesOrEmpty());
        byte[] signatureBytes = tokenSignature.getSignature().bytesOrEmpty();

        SignatureUtil.verify(
            algorithm,
            identityCertificate,
            dataBytes,
            signatureBytes
        );
    }

}
