/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
