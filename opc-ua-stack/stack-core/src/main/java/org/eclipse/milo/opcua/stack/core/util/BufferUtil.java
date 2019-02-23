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

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

public class BufferUtil {

    private static final ByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;

    /**
     * Get a pooled {@link ByteBuf} in <b>LITTLE ENDIAN</b> byte order.
     * <p>
     * This is now deprecated in netty in favor of using ByteBuf#getFooLE() methods.
     *
     * @return a pooled {@link ByteBuf} in <b>LITTLE ENDIAN</b> byte order.
     */
    @Deprecated
    public static ByteBuf buffer() {
        return allocator.buffer().order(ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * Get a pooled {@link ByteBuf} in <b>LITTLE ENDIAN</b> byte order, with an initial capacity of
     * {@code initialCapacity}.
     * <p>
     * This is now deprecated in netty in favor of using ByteBuf#getFooLE() methods.
     *
     * @return a pooled {@link ByteBuf} in <b>LITTLE ENDIAN</b> byte order.
     */
    @Deprecated
    public static ByteBuf buffer(int initialCapacity) {
        return allocator.buffer(initialCapacity).order(ByteOrder.LITTLE_ENDIAN);
    }

    /**
     * @return a pooled {@link CompositeByteBuf}.
     */
    public static CompositeByteBuf compositeBuffer() {
        return allocator.compositeBuffer();
    }

    /**
     * @return a pooled {@link ByteBuf} in <b>BIG ENDIAN</b> byte order.
     */
    public static ByteBuf pooledBuffer() {
        return allocator.buffer();
    }

    /**
     * @return a pooled {@link ByteBuf} in <b>BIG ENDIAN</b> byte order, with an initial capacity of
     * {@code initialCapacity}.
     */
    public static ByteBuf pooledBuffer(int initialCapacity) {
        return allocator.buffer(initialCapacity);
    }

}
