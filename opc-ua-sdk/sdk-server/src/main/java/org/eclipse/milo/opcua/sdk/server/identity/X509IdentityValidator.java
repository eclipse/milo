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

package org.eclipse.milo.opcua.sdk.server.identity;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

public class X509IdentityValidator extends AbstractIdentityValidator {

    private final Predicate<X509Certificate> predicate;

    public X509IdentityValidator(Predicate<X509Certificate> predicate) {
        this.predicate = predicate;
    }

    @Override
    public Object validateX509Token(
        SecureChannel channel,
        Session session,
        X509IdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        ByteString clientCertificateBs = token.getCertificateData();
        X509Certificate identityCertificate = CertificateUtil.decodeCertificate(clientCertificateBs.bytesOrEmpty());

        // verify the algorithm matches the one specified by the tokenPolicy or else the channel itself
        if (tokenPolicy.getSecurityPolicyUri() != null) {
            SecurityPolicy securityPolicy = SecurityPolicy.fromUri(tokenPolicy.getSecurityPolicyUri());
            if (!tokenSignature.getAlgorithm().equals(securityPolicy.getAsymmetricSignatureAlgorithm().getUri())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "algorithm in token signature did not match algorithm specified by token policy");
            }
        } else {
            SecurityPolicy securityPolicy = channel.getSecurityPolicy();
            if (!tokenSignature.getAlgorithm().equals(securityPolicy.getAsymmetricSignatureAlgorithm().getUri())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "algorithm in token signature did not match algorithm specified by secure channel");
            }
        }

        SecurityAlgorithm algorithm = SecurityAlgorithm.fromUri(tokenSignature.getAlgorithm());

        if (algorithm != SecurityAlgorithm.None) {
            validateSignature(
                channel,
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

    private void validateSignature(
        SecureChannel channel,
        Session session,
        SignatureData tokenSignature,
        X509Certificate identityCertificate,
        SecurityAlgorithm algorithm) throws UaException {

        try {
            Signature signature = Signature.getInstance(algorithm.getTransformation());
            signature.initVerify(identityCertificate);

            ByteString serverCertificateBs = channel.getLocalCertificateBytes();
            ByteString lastNonceBs = session.getLastNonce();

            ByteBuffer toVerify = ByteBuffer.allocate(serverCertificateBs.length() + lastNonceBs.length());
            toVerify.put(serverCertificateBs.bytesOrEmpty());
            toVerify.put(lastNonceBs.bytesOrEmpty());
            toVerify.flip();

            signature.update(toVerify);

            if (!signature.verify(tokenSignature.getSignature().bytes())) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, "could not verify signature");
            }
        } catch (NoSuchAlgorithmException | SignatureException e) {
            throw new UaException(StatusCodes.Bad_InternalError, e);
        } catch (InvalidKeyException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }
    }

}
