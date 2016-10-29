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

package org.eclipse.milo.opcua.stack.core.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;

public class NonceUtil {

    private static volatile boolean secureRandomEnabled = false;

    private static volatile SecureRandom secureRandom;

    static {
        /*
         * The first call to a SecureRandom causes it to seed, which is potentially blocking, so don't make the
         * SecureRandom instance available until after its seeded and generated output already.
         */
        new Thread(() -> {
            SecureRandom sr;
            try {
                sr = SecureRandom.getInstanceStrong();
            } catch (NoSuchAlgorithmException e) {
                sr = new SecureRandom();
            }
            sr.nextBytes(new byte[32]);
            secureRandom = sr;
        }, "SecureRandomGetInstanceStrong").start();
    }

    public static void enableSecureRandom() {
        secureRandomEnabled = true;
    }

    public static void disableSecureRandom() {
        secureRandomEnabled = false;
    }

    public static boolean isSecureRandomEnabled() {
        return secureRandomEnabled;
    }

    /**
     * @param length the length of the nonce to generate.
     * @return a nonce of the given length.
     */
    public static ByteString generateNonce(int length) {
        if (length == 0) return ByteString.NULL_VALUE;

        byte[] bs = new byte[length];

        if (secureRandom != null && secureRandomEnabled) {
            secureRandom.nextBytes(bs);
        } else {
            ThreadLocalRandom.current().nextBytes(bs);
        }

        return new ByteString(bs);
    }

    /**
     * Generate a nonce for the given {@link SecurityAlgorithm}. The length is determined by the algorithm.
     *
     * @param algorithm the algorithm to use when determined the nonce length.
     * @return a nonce of the appropriate length for the given algorithm.
     */
    public static ByteString generateNonce(SecurityAlgorithm algorithm) {
        return generateNonce(getNonceLength(algorithm));
    }

    /**
     * Get the nonce length for use with {@code algorithm}.
     *
     * @param algorithm a symmetric encryption algorithm.
     * @return the nonce length.
     */
    public static int getNonceLength(SecurityAlgorithm algorithm) {
        switch (algorithm) {
            case Aes128:
                return 16;
            case Aes256:
                return 32;
            default:
                return 0;
        }
    }

}
