/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.security.MessageDigest;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;

public enum SecurityAlgorithm {

    None("", ""),

    /**
     * Symmetric Signature; transformation to be used with {@link Mac#getInstance(String)}.
     */
    HmacSha1("http://www.w3.org/2000/09/xmldsig#hmac-sha1", "HmacSHA1"),

    /**
     * Symmetric Signature; transformation to be used with {@link Mac#getInstance(String)}.
     */
    HmacSha256("http://www.w3.org/2000/09/xmldsig#hmac-sha256", "HmacSHA256"),

    /**
     * Symmetric Signature; transformation to be used with {@link Mac#getInstance(String)}.
     * Requires Bouncy Castle installed as a Security Provider.
     * <p>
     * An algorithm to create a message authentication code defined in
     * <a href="https://tools.ietf.org/html/rfc8439">https://tools.ietf.org/html/rfc8439</a>.
     * <p>
     * When using SignOnly, the Poly1305 key is generated using the algorithm specified in 2.6 of
     * RFC8439.
     */
    Poly1305("https://tools.ietf.org/html/rfc8439", "POLY1305"),

    /**
     * Symmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     */
    Aes128("http://www.w3.org/2001/04/xmlenc#aes128-cbc", "AES/CBC/NoPadding"),

    /**
     * Symmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     */
    Aes256("http://www.w3.org/2001/04/xmlenc#aes256-cbc", "AES/CBC/NoPadding"),

    /**
     * Symmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     * <p>
     * A symmetric authenticated encryption algorithm defined in
     * <a href="https://tools.ietf.org/html/rfc7539">https://tools.ietf.org/html/rfc7539</a>.
     */
    ChaCha20_Poly1305("https://tools.ietf.org/html/rfc7539", ""),

    /**
     * Asymmetric Signature; transformation to be used with {@link Signature#getInstance(String)}.
     */
    RsaSha1("http://www.w3.org/2000/09/xmldsig#rsa-sha1", "SHA1withRSA"),

    /**
     * Asymmetric Signature; transformation to be used with {@link Signature#getInstance(String)}.
     */
    RsaSha256("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", "SHA256withRSA"),

    /**
     * Asymmetric Signature; transformation to be used with {@link Signature#getInstance(String)}.
     * <p>
     * Requires Bouncy Castle installed as a Security Provider.
     */
    RsaSha256Pss("http://opcfoundation.org/UA/security/rsa-pss-sha2-256", "SHA256withRSA/PSS"),

    /**
     * Asymmetric Signature; transformation to be used with {@link Signature#getInstance(String)}.
     * <p>
     * ECC digital signature algorithm Ed25519 described in
     * <a href="http://tools.ietf.org/html/rfc8032">http://tools.ietf.org/html/rfc8032</a>.
     */
    PureEdDSA_25519("http://tools.ietf.org/html/rfc8032", "Ed25519"),

    /**
     * Asymmetric Signature; transformation to be used with {@link Signature#getInstance(String)}.
     * <p>
     * ECC digital signature algorithm described in
     * <a href="http://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.186-4.pdf">http://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.186-4.pdf</a>.
     * <p>
     * The hash algorithm is SHA2 with 256 bits and is described in
     * <a href="http://tools.ietf.org/html/rfc6234">http://tools.ietf.org/html/rfc6234</a>.
     */
    ECDSA_SHA2_256("http://nvlpubs.nist.gov/nistpubs/FIPS/NIST.FIPS.186-4.pdf", "SHA256withECDSA"),

    /**
     * Asymmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     */
    Rsa15("http://www.w3.org/2001/04/xmlenc#rsa-1_5", "RSA/ECB/PKCS1Padding"),

    /**
     * Asymmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     */
    RsaOaepSha1("http://www.w3.org/2001/04/xmlenc#rsa-oaep", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),

    /**
     * Asymmetric Encryption; transformation to be used with {@link Cipher#getInstance(String)}.
     * <p>
     * Important note: the transformation used is "RSA/ECB/OAEPWithSHA256AndMGF1Padding" as opposed to
     * "RSA/ECB/OAEPWithSHA-256AndMGF1Padding".
     * <p>
     * While similar, the former is provided by Bouncy Castle whereas the latter is provided by SunJCE.
     * <p>
     * This is important because the BC version uses SHA256 in the padding while the SunJCE version uses Sha1.
     */
    RsaOaepSha256("http://opcfoundation.org/UA/security/rsa-oaep-sha2-256", "RSA/ECB/OAEPWithSHA256AndMGF1Padding"),

    /**
     * Asymmetric Key Wrap
     */
    KwRsa15("http://www.w3.org/2001/04/xmlenc#rsa-1_5", ""),

    /**
     * Asymmetric Key Wrap
     */
    KwRsaOaep("http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p", ""),

    /**
     * Key Derivation
     */
    PSha1("http://docs.oasis-open.org/ws-sx/ws-secureconversation/200512/dk/p_sha1", ""),

    /**
     * Key Derivation
     */
    PSha256("http://docs.oasis-open.org/ws-sx/ws-secureconversation/200512/dk/p_sha256", ""),

    /**
     * Key Derivation
     * <p>
     * The HKDF pseudo-random function defined in
     * <a href="http://tools.ietf.org/html/rfc5869">http://tools.ietf.org/html/rfc5869</a>.
     * <p>
     * The hash algorithm is SHA2 with 256 bits and is described in
     * <a href="http://tools.ietf.org/html/rfc6234">http://tools.ietf.org/html/rfc6234</a>.
     */
    HKDF_SHA2_256("http://tools.ietf.org/html/rfc5869", ""),

    /**
     * Cryptographic Hash; transformation to be used with {@link MessageDigest#getInstance(String)}.
     */
    Sha1("http://www.w3.org/2000/09/xmldsig#sha1", "SHA-1"),

    /**
     * Cryptographic Hash; transformation to be used with {@link MessageDigest#getInstance(String)}.
     */
    Sha256("http://www.w3.org/2001/04/xmlenc#sha256", "SHA-256");

    private final String uri;
    private final String transformation;

    SecurityAlgorithm(String uri, String transformation) {
        this.uri = uri;
        this.transformation = transformation;
    }

    /**
     * @return The URI identifying this security algorithm.
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return The transformation string to use with the appropriate provider SPI.
     */
    public String getTransformation() {
        return transformation;
    }

    public static SecurityAlgorithm fromUri(String securityAlgorithmUri) throws UaException {
        for (SecurityAlgorithm algorithm : values()) {
            if (algorithm.getUri().equals(securityAlgorithmUri)) {
                return algorithm;
            }
        }

        throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
            "unknown securityAlgorithmUri: " + securityAlgorithmUri);
    }

}
