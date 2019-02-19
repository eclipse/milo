/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.serialization;

import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;

public class EncodingLimits {

    public static final int DEFAULT_MAX_ARRAY_LENGTH = MessageLimits.DEFAULT_MAX_MESSAGE_SIZE;
    public static final int DEFAULT_MAX_STRING_LENGTH = MessageLimits.DEFAULT_MAX_MESSAGE_SIZE;
    public static final int DEFAULT_MAX_RECURSION_DEPTH = 128;

    public static final EncodingLimits DEFAULT = new EncodingLimits(
        DEFAULT_MAX_ARRAY_LENGTH,
        DEFAULT_MAX_STRING_LENGTH,
        DEFAULT_MAX_RECURSION_DEPTH
    );

    private final int maxArrayLength;
    private final int maxStringLength;
    private final int maxRecursionDepth;

    public EncodingLimits(int maxArrayLength, int maxStringLength, int maxRecursionDepth) {
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
        this.maxRecursionDepth = maxRecursionDepth;
    }

    public int getMaxArrayLength() {
        return maxArrayLength;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

}
