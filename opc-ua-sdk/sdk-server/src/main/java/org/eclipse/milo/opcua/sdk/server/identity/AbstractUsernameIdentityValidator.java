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

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public abstract class AbstractUsernameIdentityValidator<T> extends AbstractIdentityValidator<T> {

    @Override
    protected T validateAnonymousToken(
        Session session,
        AnonymousIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature
    ) throws UaException {

        return authenticateAnonymousOrThrow(session);
    }

    @Override
    protected T validateUsernameToken(
        Session session,
        UserNameIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature
    ) throws UaException {

        String username = token.getUserName();
        ByteString lastNonce = session.getLastNonce();
        int lastNonceLength = lastNonce.length();

        if (username == null || username.isEmpty()) {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }

        SecurityAlgorithm algorithm;

        String algorithmUri = token.getEncryptionAlgorithm();

        if (algorithmUri == null || algorithmUri.isEmpty()) {
            SecurityPolicy securityPolicy = session
                .getSecurityConfiguration().getSecurityPolicy();

            algorithm = securityPolicy.getAsymmetricEncryptionAlgorithm();
        } else {
            try {
                algorithm = SecurityAlgorithm.fromUri(algorithmUri);
            } catch (UaException e) {
                throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
            }

            if (algorithm != SecurityAlgorithm.Rsa15 &&
                algorithm != SecurityAlgorithm.RsaOaepSha1 &&
                algorithm != SecurityAlgorithm.RsaOaepSha256) {

                throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
            }
        }

        byte[] tokenBytes = token.getPassword().bytesOrEmpty();

        if (algorithm != SecurityAlgorithm.None) {
            byte[] plainTextBytes = decryptTokenData(session, algorithm, tokenBytes);

            //@formatter:off
            int length =
                ((plainTextBytes[3] & 0xFF) << 24) |
                ((plainTextBytes[2] & 0xFF) << 16) |
                ((plainTextBytes[1] & 0xFF) << 8 ) |
                ( plainTextBytes[0] & 0xFF       );
            //@formatter:on

            byte[] passwordBytes = new byte[length - lastNonceLength];
            byte[] nonceBytes = new byte[lastNonceLength];

            System.arraycopy(plainTextBytes, 4, passwordBytes, 0, passwordBytes.length);
            System.arraycopy(plainTextBytes, 4 + passwordBytes.length, nonceBytes, 0, lastNonceLength);

            if (MessageDigest.isEqual(lastNonce.bytes(), nonceBytes)) {
                String password = new String(passwordBytes, Charset.forName("UTF-8"));

                return authenticateUsernameOrThrow(session, username, password);
            } else {
                throw new UaException(StatusCodes.Bad_UserAccessDenied);
            }
        } else {
            String password = new String(tokenBytes, Charset.forName("UTF-8"));

            return authenticateUsernameOrThrow(session, username, password);
        }
    }

    private T authenticateAnonymousOrThrow(Session session) throws UaException {
        T identityObject = authenticateAnonymous(session);

        if (identityObject != null) {
            return identityObject;
        } else {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    private T authenticateUsernameOrThrow(Session session, String username, String password) throws UaException {
        T identityObject = authenticateUsernamePassword(session, username, password);

        if (identityObject != null) {
            return identityObject;
        } else {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    /**
     * Create and return an identity object for an anonymous user.
     *
     * @param session the {@link Session} being activated.
     * @return an identity object of type {@code T} representig an anonymous user, or {@code null} if anonymous
     * authentication is not allowed.
     */
    @Nullable
    protected abstract T authenticateAnonymous(Session session);

    /**
     * Authenticate {@code username} with {@code password}, returning an identity object of type {@code T} if the
     * authentication succeeded, or {@code null} if the authentication failed.
     *
     * @param session  the {@link Session} being activated.
     * @param username the username to authenticate.
     * @param password the password to authenticate the user with.
     * @return an identity object of type {@code T} if the authentication succeeded, or {@code null} if it failed.
     */
    @Nullable
    protected abstract T authenticateUsernamePassword(Session session, String username, String password);

}
