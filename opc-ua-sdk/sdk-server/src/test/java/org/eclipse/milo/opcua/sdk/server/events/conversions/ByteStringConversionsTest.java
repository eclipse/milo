/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.junit.jupiter.api.Test;

import io.netty.buffer.ByteBufUtil;

public class ByteStringConversionsTest {

    @Test
    public void testByteStringToGuid() {
        long msb = 0xABCDABCDABCDABCDL;
        long lsb = 0xEFEFEFEFEFEFEFEFL;
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(msb).putLong(lsb);
        ByteString bs = ByteString.of(bb.array());

        UUID uuid = new UUID(msb, lsb);

        assertEquals(ByteStringConversions.byteStringToGuid(bs), uuid);
    }

    @Test
    public void testByteStringToString() {
        ByteString bs = ByteString.of(new byte[]{0x01, 0x02, 0x03});

        assertEquals(ByteStringConversions.byteStringToString(bs), ByteBufUtil.hexDump(bs.bytesOrEmpty()));
    }

}
