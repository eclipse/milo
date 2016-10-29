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

package org.eclipse.milo.opcua.stack;

import java.nio.ByteOrder;
import java.util.function.BiFunction;
import java.util.function.Function;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import static org.testng.Assert.assertEquals;

public class SerializationFixture2 {

    protected <T> void assertSerializable(T encoded,
                                          BiFunction<T, ByteBuf, ByteBuf> encoder,
                                          Function<ByteBuf, T> decoder) {

        ByteBuf buffer = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);

        T decoded = decoder.apply(encoder.apply(encoded, buffer));

        assertEquals(encoded, decoded);
    }

}
