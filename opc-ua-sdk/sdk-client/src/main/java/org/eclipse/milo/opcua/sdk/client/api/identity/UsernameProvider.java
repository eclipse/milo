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

package org.eclipse.milo.opcua.sdk.client.api.identity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Optional;
import javax.crypto.Cipher;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserNameIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.jooq.lambda.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link IdentityProvider} that will choose the first available username+password {@link UserTokenPolicy}.
 */
public class UsernameProvider implements IdentityProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String username;
    private final String password;

    public UsernameProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Tuple2<UserIdentityToken, SignatureData> getIdentityToken(EndpointDescription endpoint,
                                                                     ByteString serverNonce) throws Exception {

        UserTokenPolicy tokenPolicy = Arrays.stream(endpoint.getUserIdentityTokens())
            .filter(t -> t.getTokenType() == UserTokenType.UserName)
            .findFirst().orElseThrow(() -> new Exception("no username token policy found"));

        String policyId = tokenPolicy.getPolicyId();

        SecurityPolicy securityPolicy = SecurityPolicy.None;

        String securityPolicyUri = tokenPolicy.getSecurityPolicyUri();
        try {
            if (securityPolicyUri != null && !securityPolicyUri.isEmpty()) {
                securityPolicy = SecurityPolicy.fromUri(securityPolicyUri);
            } else {
                securityPolicyUri = endpoint.getSecurityPolicyUri();
                securityPolicy = SecurityPolicy.fromUri(securityPolicyUri);
            }
        } catch (Throwable t) {
            logger.warn("Error parsing SecurityPolicy for uri={}, falling back to no security.", securityPolicyUri);
        }

        byte[] passwordBytes = password.getBytes("UTF-8");
        byte[] nonceBytes = Optional.ofNullable(serverNonce.bytes()).orElse(new byte[0]);

        ByteBuf buffer = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);

        if (securityPolicy == SecurityPolicy.None) {
            buffer.writeBytes(passwordBytes);
        } else {
            buffer.writeInt(passwordBytes.length + nonceBytes.length);
            buffer.writeBytes(passwordBytes);
            buffer.writeBytes(nonceBytes);

            ByteString bs = endpoint.getServerCertificate();
            X509Certificate certificate = CertificateUtil.decodeCertificate(bs.bytes());

            int plainTextBlockSize = getPlainTextBlockSize(certificate, securityPolicy);
            int cipherTextBlockSize = getCipherTextBlockSize(certificate, securityPolicy);
            int blockCount = (buffer.readableBytes() + plainTextBlockSize - 1) / plainTextBlockSize;
            Cipher cipher = getAndInitializeCipher(certificate, securityPolicy);

            ByteBuffer plainTextNioBuffer = buffer.nioBuffer();
            ByteBuffer cipherTextNioBuffer = Unpooled.buffer(cipherTextBlockSize * blockCount)
                .order(ByteOrder.LITTLE_ENDIAN)
                .nioBuffer(0, cipherTextBlockSize * blockCount);

            for (int blockNumber = 0; blockNumber < blockCount; blockNumber++) {
                int position = blockNumber * plainTextBlockSize;
                int limit = Math.min(buffer.readableBytes(), (blockNumber + 1) * plainTextBlockSize);
                plainTextNioBuffer.position(position).limit(limit);

                cipher.doFinal(plainTextNioBuffer, cipherTextNioBuffer);
            }

            cipherTextNioBuffer.flip();
            buffer = Unpooled.wrappedBuffer(cipherTextNioBuffer);
        }

        byte[] bs = new byte[buffer.readableBytes()];
        buffer.readBytes(bs);

        // UA Part 4, Section 7.35.3 UserNameIdentityToken:
        // encryptionAlgorithm parameter is null if the password is not encrypted.
        String securityAlgorithmUri = securityPolicy.getAsymmetricEncryptionAlgorithm().getUri();
        String encryptionAlgorithm = securityAlgorithmUri.isEmpty() ? null : securityAlgorithmUri;

        UserNameIdentityToken token = new UserNameIdentityToken(
            policyId,
            username,
            ByteString.of(bs),
            encryptionAlgorithm
        );

        return new Tuple2<>(token, new SignatureData());
    }

    public Cipher getAndInitializeCipher(X509Certificate serverCertificate,
                                         SecurityPolicy securityPolicy) throws UaException {

        assert (serverCertificate != null);

        try {
            String transformation = securityPolicy.getAsymmetricEncryptionAlgorithm().getTransformation();
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, serverCertificate.getPublicKey());
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }
    }

    public int getPlainTextBlockSize(X509Certificate certificate, SecurityPolicy securityPolicy) {
        SecurityAlgorithm algorithm = securityPolicy.getAsymmetricEncryptionAlgorithm();

        switch (algorithm) {
            case Rsa15:
                return (getAsymmetricKeyLength(certificate) + 1) / 8 - 11;
            case RsaOaep:
                return (getAsymmetricKeyLength(certificate) + 1) / 8 - 42;
            default:
                return 1;
        }
    }

    public int getCipherTextBlockSize(X509Certificate certificate, SecurityPolicy securityPolicy) {
        SecurityAlgorithm algorithm = securityPolicy.getAsymmetricEncryptionAlgorithm();

        switch (algorithm) {
            case Rsa15:
            case RsaOaep:
                return (getAsymmetricKeyLength(certificate) + 1) / 8;
            default:
                return 1;
        }
    }


    private int getAsymmetricKeyLength(X509Certificate certificate) {
        PublicKey publicKey = certificate != null ?
            certificate.getPublicKey() : null;

        return (publicKey instanceof RSAPublicKey) ?
            ((RSAPublicKey) publicKey).getModulus().bitLength() : 0;
    }

    @Override
    public String toString() {
        return "UsernameProvider{" +
            "username='" + username + '\'' +
            '}';
    }

}
