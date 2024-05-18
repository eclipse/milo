/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class SerializationFixture2 {

    protected <T> void assertSerializable(T encoded,
                                          BiFunction<T, ByteBuf, ByteBuf> encoder,
                                          Function<ByteBuf, T> decoder) {

        ByteBuf buffer = Unpooled.buffer();

        T decoded = decoder.apply(encoder.apply(encoded, buffer));

        assertEquals(decoded, encoded);
    }

}
