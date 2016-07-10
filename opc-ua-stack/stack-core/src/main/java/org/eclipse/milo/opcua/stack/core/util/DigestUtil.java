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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;

public class DigestUtil {

    private static final ThreadLocal<MessageDigest> sha1Digest = new ThreadLocal<>();

    /**
     * Compute the SHA1 digest for a given input.
     *
     * @param input the input to compute the digest for.
     * @return the SHA1 digest of {@code input}.
     */
    public static byte[] sha1(byte[] input) {
        MessageDigest messageDigest = sha1Digest.get();

        if (messageDigest == null) {
            try {
                messageDigest = MessageDigest.getInstance("SHA1");
                sha1Digest.set(messageDigest);
            } catch (NoSuchAlgorithmException e) {
                throw new UaRuntimeException(StatusCodes.Bad_InternalError, e);
            }
        }

        return messageDigest.digest(input);
    }

}
