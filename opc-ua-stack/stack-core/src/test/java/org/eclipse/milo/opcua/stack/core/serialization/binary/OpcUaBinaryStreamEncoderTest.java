/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OpcUaBinaryStreamEncoderTest {

    ByteBuf buffer;
    OpcUaBinaryStreamEncoder writer;

    @BeforeTest
    public void initializeTest() {
        buffer = Unpooled.buffer().order(ByteOrder.LITTLE_ENDIAN);
        writer = new OpcUaBinaryStreamEncoder(buffer);
    }

    @Test
    public void testWriteBit() throws Exception {
        {
            writer.writeBit(1);
            writer.writeBit(1);
            writer.writeBit(1);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(0);
            writer.writeBit(0);
            writer.writeBit(0);
            assertEquals(buffer.readUnsignedByte(), 0b00001111);
        }
        {
            writer.writeBit(0);
            writer.writeBit(0);
            writer.writeBit(0);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(1);
            writer.writeBit(1);
            writer.writeBit(1);
            assertEquals(buffer.readUnsignedByte(), 0b11110000);
        }
        {
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            assertEquals(buffer.readUnsignedByte(), 0b10101010);
        }
        {
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            writer.writeBit(1);
            writer.writeBit(0);
            assertEquals(buffer.readUnsignedByte(), 0b01010101);
        }
    }

}