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

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public class UsernameIdentityValidator extends AbstractIdentityValidator {

    private final boolean allowAnonymous;
    private final Predicate<AuthenticationChallenge> predicate;

    public UsernameIdentityValidator(boolean allowAnonymous,
                                     Predicate<AuthenticationChallenge> predicate) {

        this.allowAnonymous = allowAnonymous;
        this.predicate = predicate;
    }

    @Override
    public Object validateAnonymousToken(
        ServerSecureChannel channel,
        Session session,
        AnonymousIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        if (allowAnonymous) {
            return String.format("anonymous_%s_%s",
                session.getSessionName(), session.getSessionId().toParseableString());
        } else {
            throw new UaException(StatusCodes.Bad_UserAccessDenied);
        }
    }

    @Override
    public Object validateUsernameToken(
        ServerSecureChannel channel,
        Session session,
        UserNameIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException {

        return validateUserNameIdentityToken(channel, session, token);
    }

    private String validateUserNameIdentityToken(
        ServerSecureChannel channel,
        Session session,
        UserNameIdentityToken token) throws UaException {

        SecurityPolicy securityPolicy = channel.getSecurityPolicy();
        String username = token.getUserName();
        ByteString lastNonce = session.getLastNonce();
        int lastNonceLength = lastNonce.length();

        if (username == null || username.isEmpty()) {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }

        SecurityAlgorithm algorithm;

        String algorithmUri = token.getEncryptionAlgorithm();
        if (algorithmUri == null || algorithmUri.isEmpty()) {
            algorithm = channel.getSecurityPolicy().getAsymmetricEncryptionAlgorithm();
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

        byte[] tokenBytes = token.getPassword().bytes();
        if (tokenBytes == null) tokenBytes = new byte[0];

        if (algorithm != SecurityAlgorithm.None) {
            byte[] plainTextBytes = decryptTokenData(channel, session, algorithm, tokenBytes);

            int length = ((plainTextBytes[3] & 0xFF) << 24) |
                ((plainTextBytes[2] & 0xFF) << 16) |
                ((plainTextBytes[1] & 0xFF) << 8) |
                (plainTextBytes[0] & 0xFF);

            byte[] passwordBytes = new byte[length - lastNonceLength];
            byte[] nonceBytes = new byte[lastNonceLength];

            System.arraycopy(plainTextBytes, 4, passwordBytes, 0, passwordBytes.length);
            System.arraycopy(plainTextBytes, 4 + passwordBytes.length, nonceBytes, 0, lastNonceLength);

            String password = new String(passwordBytes, Charset.forName("UTF-8"));
            AuthenticationChallenge challenge = new AuthenticationChallenge(username, password);

            if (Arrays.equals(lastNonce.bytes(), nonceBytes) && predicate.test(challenge)) {
                return username;
            } else {
                throw new UaException(StatusCodes.Bad_UserAccessDenied);
            }
        } else {
            String password = new String(tokenBytes, Charset.forName("UTF-8"));
            AuthenticationChallenge challenge = new AuthenticationChallenge(username, password);

            if (predicate.test(challenge)) {
                return username;
            } else {
                throw new UaException(StatusCodes.Bad_UserAccessDenied);
            }
        }
    }

    public static final class AuthenticationChallenge {

        private final String username;
        private final String password;

        public AuthenticationChallenge(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }

}
