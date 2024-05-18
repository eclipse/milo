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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.TestSerializationContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class OpcUaBinaryStreamEncoderTest {

    ByteBuf buffer;
    OpcUaBinaryStreamEncoder writer;

    @BeforeAll
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
            assertEquals(0b00001111, buffer.readUnsignedByte());
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
            assertEquals(0b11110000, buffer.readUnsignedByte());
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
            assertEquals(0b10101010, buffer.readUnsignedByte());
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
            assertEquals(0b01010101, buffer.readUnsignedByte());
        }
    }

}
