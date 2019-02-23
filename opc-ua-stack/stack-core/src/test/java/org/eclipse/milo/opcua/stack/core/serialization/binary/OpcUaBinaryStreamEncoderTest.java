/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization.binary;

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
        buffer = Unpooled.buffer();
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