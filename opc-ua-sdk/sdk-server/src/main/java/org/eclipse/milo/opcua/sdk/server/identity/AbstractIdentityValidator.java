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
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import javax.crypto.Cipher;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.IssuedIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;

public abstract class AbstractIdentityValidator implements IdentityValidator {

    @Override
    public Object validateIdentityToken(
        ServerSecureChannel channel,
        Session session,
        UserIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        if (token instanceof AnonymousIdentityToken) {
            return validateAnonymousToken(
                channel, session, (AnonymousIdentityToken) token, tokenPolicy, tokenSignature);
        } else if (token instanceof UserNameIdentityToken) {
            return validateUsernameToken(
                channel, session, (UserNameIdentityToken) token, tokenPolicy, tokenSignature);
        } else if (token instanceof X509IdentityToken) {
            return validateX509Token(
                channel, session, (X509IdentityToken) token, tokenPolicy, tokenSignature);
        } else if (token instanceof IssuedIdentityToken) {
            return validateIssuedIdentityToken(
                channel, session, (IssuedIdentityToken) token, tokenPolicy, tokenSignature);
        } else {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }
    }

    /**
     * Validate an {@link AnonymousIdentityToken} and return an identity Object that represents the user.
     * <p>
     * This Object should implement equality in such a way that a subsequent identity validation for the same user
     * yields a comparable Object.
     *
     * @param channel        the {@link ServerSecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link AnonymousIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateAnonymousToken(
        ServerSecureChannel channel,
        Session session,
        AnonymousIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
    }

    /**
     * Validate a {@link UserNameIdentityToken} and return an identity Object that represents the user.
     * <p>
     * This Object should implement equality in such a way that a subsequent identity validation for the same user
     * yields a comparable Object.
     *
     * @param channel        the {@link ServerSecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link UserNameIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateUsernameToken(
        ServerSecureChannel channel,
        Session session,
        UserNameIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
    }

    /**
     * Validate an {@link X509IdentityToken} and return an identity Object that represents the user.
     * <p>
     * This Object should implement equality in such a way that a subsequent identity validation for the same user
     * yields a comparable Object.
     *
     * @param channel        the {@link ServerSecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link X509IdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateX509Token(
        ServerSecureChannel channel,
        Session session,
        X509IdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
    }

    /**
     * Validate an {@link IssuedIdentityToken} and return an identity Object that represents the user.
     * <p>
     * This Object should implement equality in such a way that a subsequent identity validation for the same user
     * yields a comparable Object.
     *
     * @param channel        the {@link ServerSecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link IssuedIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateIssuedIdentityToken(
        ServerSecureChannel channel,
        Session session,
        IssuedIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
    }

    /**
     * Decrypt the data contained in a {@link UserNameIdentityToken} or {@link IssuedIdentityToken}.
     * <p>
     * See {@link UserNameIdentityToken#getPassword()} and {@link IssuedIdentityToken#getTokenData()}.
     *
     * @param channel   the {@link ServerSecureChannel}.
     * @param session   the current {@link Session}.
     * @param dataBytes the encrypted data.
     * @return the decrypted data.
     * @throws UaException if decryption fails.
     */
    protected byte[] decryptTokenData(ServerSecureChannel channel,
                                      Session session,
                                      SecurityAlgorithm algorithm,
                                      byte[] dataBytes) throws UaException {

        X509Certificate certificate = CertificateUtil.decodeCertificate(
            channel.getEndpointDescription()
                .getServerCertificate()
                .bytesOrEmpty()
        );

        int cipherTextBlockSize = SecureChannel.getAsymmetricCipherTextBlockSize(certificate, algorithm);
        int blockCount = dataBytes.length / cipherTextBlockSize;

        int plainTextBufferSize = cipherTextBlockSize * blockCount;

        byte[] plainTextBytes = new byte[plainTextBufferSize];
        ByteBuffer plainTextNioBuffer = ByteBuffer.wrap(plainTextBytes);
        ByteBuffer passwordNioBuffer = ByteBuffer.wrap(dataBytes);

        try {
            KeyPair keyPair = session.getServer()
                .getConfig()
                .getCertificateManager()
                .getKeyPair(ByteString.of(DigestUtil.sha1(certificate.getEncoded())))
                .orElseThrow(() -> new UaException(StatusCodes.Bad_SecurityChecksFailed));

            Cipher cipher = getCipher(algorithm, keyPair);

            for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                passwordNioBuffer.limit(passwordNioBuffer.position() + cipherTextBlockSize);

                cipher.doFinal(passwordNioBuffer, plainTextNioBuffer);
            }
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }

        return plainTextBytes;
    }

    private Cipher getCipher(SecurityAlgorithm algorithm, KeyPair keyPair) throws UaException {
        try {
            String transformation = algorithm.getTransformation();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }
    }

}
