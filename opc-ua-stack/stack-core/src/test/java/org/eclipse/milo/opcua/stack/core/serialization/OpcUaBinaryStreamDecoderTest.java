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

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class OpcUaBinaryStreamDecoderTest {

    @Test
    public void testReadDiagnosticInfoStackOverflow() {
        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 10000; i++) {
            buffer.writeByte(0x40);
        }
        buffer.writeByte(0x00);

        assertThrows(UaSerializationException.class,
            () -> new OpcUaBinaryStreamDecoder(buffer).readDiagnosticInfo());
    }

    @Test
    public void testReadVariantStackOverflow() {
        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 10000; i++) {
            buffer.writerIndex(5 * i);

            buffer.writeByte(24 | 0x80);
            buffer.writeByte(1);
            buffer.writeByte(0);
            buffer.writeByte(0);
            buffer.writeByte(0);
        }
        buffer.writeByte(0);

        assertThrows(UaSerializationException.class,
            () -> new OpcUaBinaryStreamDecoder(buffer).readVariant());
    }

    @Test
    public void testReadVariantStackOverflow2() {
        ByteBuf buffer = Unpooled.buffer();

        for (int i = 0; i < 10000; i++) {
            buffer.writerIndex(2 * i);

            buffer.writeByte(23);
            buffer.writeByte(1);
        }
        buffer.writeByte(0);

        assertThrows(UaSerializationException.class,
            () -> new OpcUaBinaryStreamDecoder(buffer).readVariant());
    }

}
