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

package org.eclipse.milo.opcua.stack.core.channel;

import com.google.common.base.Preconditions;

// TODO refactor/rename: MessageLimits?
public class ChannelConfig {

    /**
     * A {@link ChannelConfig} that uses the default settings.
     */
    public static final ChannelConfig DEFAULT = new ChannelConfig();

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
     * Create a {@link ChannelConfig} using the default parameters.
     *
     * @see ChannelConfig#DEFAULT_MAX_CHUNK_SIZE
     * @see ChannelConfig#DEFAULT_MAX_CHUNK_COUNT
     * @see ChannelConfig#DEFAULT_MAX_MESSAGE_SIZE
     */
    public ChannelConfig() {
        this(DEFAULT_MAX_CHUNK_SIZE,
            DEFAULT_MAX_CHUNK_COUNT,
            DEFAULT_MAX_MESSAGE_SIZE);
    }

    public ChannelConfig(
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
