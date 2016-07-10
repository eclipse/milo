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
import javax.crypto.Cipher;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.IssuedIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.X509IdentityToken;

public abstract class AbstractIdentityValidator implements IdentityValidator {

    @Override
    public Object validateIdentityToken(
        SecureChannel channel,
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
     * @param channel        the {@link SecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link AnonymousIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateAnonymousToken(
        SecureChannel channel,
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
     * @param channel        the {@link SecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link UserNameIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateUsernameToken(
        SecureChannel channel,
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
     * @param channel        the {@link SecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link X509IdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateX509Token(
        SecureChannel channel,
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
     * @param channel        the {@link SecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link IssuedIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}.
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    protected Object validateIssuedIdentityToken(
        SecureChannel channel,
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
     * @param secureChannel the {@link SecureChannel}.
     * @param dataBytes     the encrypted data.
     * @return the decrypted data.
     * @throws UaException if decryption fails.
     */
    protected byte[] decryptTokenData(SecureChannel secureChannel,
                                      SecurityAlgorithm algorithm,
                                      byte[] dataBytes) throws UaException {

        int cipherTextBlockSize = secureChannel.getLocalAsymmetricCipherTextBlockSize();
        int blockCount = dataBytes.length / cipherTextBlockSize;

        int plainTextBufferSize = cipherTextBlockSize * blockCount;

        byte[] plainTextBytes = new byte[plainTextBufferSize];
        ByteBuffer plainTextNioBuffer = ByteBuffer.wrap(plainTextBytes);

        ByteBuffer passwordNioBuffer = ByteBuffer.wrap(dataBytes);

        try {
            Cipher cipher = getCipher(secureChannel, algorithm);

            for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                passwordNioBuffer.limit(passwordNioBuffer.position() + cipherTextBlockSize);

                cipher.doFinal(passwordNioBuffer, plainTextNioBuffer);
            }
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }

        return plainTextBytes;
    }

    private Cipher getCipher(SecureChannel channel, SecurityAlgorithm algorithm) throws UaException {
        try {
            String transformation = algorithm.getTransformation();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, channel.getKeyPair().getPrivate());
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }
    }

}
