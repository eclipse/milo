/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.slf4j.LoggerFactory;

public class NonceUtil {

    private static volatile boolean SECURE_RANDOM_ENABLED = true;

    private static final AtomicReference<SecureRandom> SECURE_RANDOM = new AtomicReference<>();

    static {
        // The first call to a SecureRandom causes it to seed, which is potentially blocking, so don't make the
        // SecureRandom instance available until after a seed has been generated.
        new Thread(() -> {
            long start = System.nanoTime();

            SecureRandom sr = new SecureRandom();
            sr.nextBytes(new byte[32]);
            SECURE_RANDOM.set(sr);

            long delta = System.nanoTime() - start;

            LoggerFactory.getLogger(NonceUtil.class).info(
                "SecureRandom seeded in {}ms.",
                TimeUnit.MILLISECONDS.convert(delta, TimeUnit.NANOSECONDS));
        }, "NonceUtilSecureRandom").start();
    }

    /**
     * Enable the use of a {@link SecureRandom} for nonce generation. This is the default.
     */
    public static void enableSecureRandom() {
        SECURE_RANDOM_ENABLED = true;
    }

    /**
     * Disable the use of a {@link SecureRandom} for nonce generation. Not recommended.
     */
    public static void disableSecureRandom() {
        SECURE_RANDOM_ENABLED = false;
    }

    /**
     * @return {@code true} is nonce generation uses a {@link SecureRandom} instance.
     */
    public static boolean isSecureRandomEnabled() {
        return SECURE_RANDOM_ENABLED;
    }

    /**
     * @param length the length of the nonce to generate.
     * @return a nonce of the given length.
     */
    public static ByteString generateNonce(int length) {
        if (length == 0) return ByteString.NULL_VALUE;

        byte[] bs = new byte[length];

        Random random = null;

        if (SECURE_RANDOM_ENABLED) {
            random = SECURE_RANDOM.get();
        }

        if (random == null) {
            random = ThreadLocalRandom.current();
        }

        random.nextBytes(bs);

        return new ByteString(bs);
    }

    /**
     * Generate a nonce for the given {@link SecurityPolicy}.
     * <p>
     * The length is determined by the policy: see {@link #getNonceLength(SecurityPolicy)}.
     *
     * @param securityPolicy the {@link SecurityPolicy} to use when determining the nonce length.
     * @return a nonce of the appropriate length for the given security policy.
     */
    public static ByteString generateNonce(SecurityPolicy securityPolicy) {
        return generateNonce(getNonceLength(securityPolicy));
    }

    /**
     * Get the minimum nonce length for use with {@code securityPolicy}.
     * <p>
     * For an RSA-based {@link SecurityPolicy}, the nonce shall be a cryptographic random number with a length equal to
     * the key length specified by policy's SymmetricEncryptionAlgorithm or the length of hash algorithm used by the
     * policy's KeyDerivationAlgorithm, whichever is greater.
     *
     * @param securityPolicy the {@link SecurityPolicy} in use.
     * @return the minimum nonce length for use with {@code securityPolicy}.
     */
    public static int getNonceLength(SecurityPolicy securityPolicy) {
        switch (securityPolicy) {
            case Basic128Rsa15:
                return 16;
            case Basic256:
            case Basic256Sha256:
            case Aes128_Sha256_RsaOaep:
            case Aes256_Sha256_RsaPss:
                return 32;
            case None:
            default:
                return 0;
        }
    }

}
