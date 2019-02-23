/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel;

import com.google.common.base.Preconditions;

public class MessageLimits {

    /**
     * A {@link MessageLimits} that uses the default settings.
     */
    public static final MessageLimits DEFAULT = new MessageLimits();

    /**
     * The default maximum size of a message; 2MB by default.
     */
    public static final int DEFAULT_MAX_MESSAGE_SIZE = 2 * 1024 * 1024;

    /**
     * The default maximum size of a single chunk.
     */
    public static final int DEFAULT_MAX_CHUNK_SIZE = 65535;

    /**
     * The default maximum number of chunks that a message can break down into.
     * <p>
     * More than chunks than constitute {@link #DEFAULT_MAX_MESSAGE_SIZE} are needed because of overhead
     * when constructing chunks; not all of the chunk size is dedicated to message bytes.
     */
    public static final int DEFAULT_MAX_CHUNK_COUNT =
        (DEFAULT_MAX_MESSAGE_SIZE / DEFAULT_MAX_CHUNK_SIZE) * 2;


    private final int maxChunkSize;
    private final int maxChunkCount;
    private final int maxMessageSize;

    /**
     * Create a {@link MessageLimits} using the default parameters.
     *
     * @see MessageLimits#DEFAULT_MAX_CHUNK_SIZE
     * @see MessageLimits#DEFAULT_MAX_CHUNK_COUNT
     * @see MessageLimits#DEFAULT_MAX_MESSAGE_SIZE
     */
    public MessageLimits() {
        this(DEFAULT_MAX_CHUNK_SIZE,
            DEFAULT_MAX_CHUNK_COUNT,
            DEFAULT_MAX_MESSAGE_SIZE);
    }

    public MessageLimits(
        int maxChunkSize,
        int maxChunkCount,
        int maxMessageSize) {

        Preconditions.checkArgument(maxChunkSize >= 8196,
            "maxChunkSize must be greater than or equal to 8196");

        this.maxChunkSize = maxChunkSize;
        this.maxChunkCount = maxChunkCount;
        this.maxMessageSize = maxMessageSize;
    }

    public int getMaxChunkSize() {
        return maxChunkSize;
    }

    public int getMaxChunkCount() {
        return maxChunkCount;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

}
