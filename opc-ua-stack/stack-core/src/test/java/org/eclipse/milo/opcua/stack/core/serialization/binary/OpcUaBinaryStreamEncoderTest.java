/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.TestSerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.AccessLevelType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.testng.Assert.assertEquals;

public class OpcUaBinaryStreamEncoderTest {

    ByteBuf buffer;
    OpcUaBinaryStreamEncoder writer;

    @BeforeTest
    public void initializeTest() {
        buffer = Unpooled.buffer();
        writer = new OpcUaBinaryStreamEncoder(new TestSerializationContext()).setBuffer(buffer);
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

    @Test
    public void testWriteOptionSet() {
        AccessLevelType accessLevelType = AccessLevelType.of(
            AccessLevelType.Field.CurrentRead,
            AccessLevelType.Field.CurrentWrite
        );

        writer.writeVariant(new Variant(accessLevelType));

        assertEquals(ubyte(buffer.readUnsignedByte()), accessLevelType.getValue());
    }

}
