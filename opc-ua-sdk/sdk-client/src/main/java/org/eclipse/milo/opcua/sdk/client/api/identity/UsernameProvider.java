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
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.crypto.Cipher;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
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

import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

/**
 * An {@link IdentityProvider} that chooses a {@link UserTokenPolicy} with {@link UserTokenType#UserName}.
 */
public class UsernameProvider implements IdentityProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final String username;
    private final String password;
    private final Function<List<UserTokenPolicy>, UserTokenPolicy> policyChooser;

    /**
     * Construct a {@link UsernameProvider} that selects the first available {@link UserTokenPolicy} with
     * {@link UserTokenType#UserName}.
     *
     * @param username the username to authenticate with.
     * @param password the password to authenticate with.
     */
    public UsernameProvider(String username, String password) {
        this(username, password, ps -> ps.get(0));
    }

    /**
     * Construct a {@link UsernameProvider} that selects a {@link UserTokenPolicy} using {@code policyChooser}.
     * <p>
     * Useful if the server might return more than one {@link UserTokenPolicy} with {@link UserTokenType#UserName}.
     *
     * @param username      the username to authenticate with.
     * @param password      the password to authenticate with.
     * @param policyChooser a function that selects a {@link UserTokenPolicy} to use. The policy list is guaranteed to
     *                      be non-null and non-empty.
     */
    public UsernameProvider(
        String username,
        String password,
        Function<List<UserTokenPolicy>, UserTokenPolicy> policyChooser) {

        this.username = username;
        this.password = password;
        this.policyChooser = policyChooser;
    }

    @Override
    public Tuple2<UserIdentityToken, SignatureData> getIdentityToken(EndpointDescription endpoint,
                                                                     ByteString serverNonce) throws Exception {

        List<UserTokenPolicy> userIdentityTokens = l(endpoint.getUserIdentityTokens());

        List<UserTokenPolicy> tokenPolicies = userIdentityTokens.stream()
            .filter(t -> t.getTokenType() == UserTokenType.UserName)
            .collect(Collectors.toList());

        if (tokenPolicies.isEmpty()) {
            throw new Exception("no UserTokenPolicy with UserTokenType.UserName found");
        }

        UserTokenPolicy tokenPolicy = policyChooser.apply(tokenPolicies);

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

            if (bs == null || bs.isNull()) {
                throw new UaException(
                    StatusCodes.Bad_ConfigurationError,
                    "UserTokenPolicy requires encryption but " +
                        "server did not provide a certificate in endpoint");
            }

            X509Certificate certificate = CertificateUtil.decodeCertificate(bs.bytes());

            int plainTextBlockSize = SecureChannel.getAsymmetricPlainTextBlockSize(
                certificate,
                securityPolicy.getAsymmetricEncryptionAlgorithm()
            );
            int cipherTextBlockSize = SecureChannel.getAsymmetricCipherTextBlockSize(
                certificate,
                securityPolicy.getAsymmetricEncryptionAlgorithm()
            );
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

    private Cipher getAndInitializeCipher(X509Certificate serverCertificate,
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

    @Override
    public String toString() {
        return "UsernameProvider{" +
            "username='" + username + '\'' +
            '}';
    }

    public static UsernameProvider of(String username, String password) {
        return new UsernameProvider(username, password);
    }
}
