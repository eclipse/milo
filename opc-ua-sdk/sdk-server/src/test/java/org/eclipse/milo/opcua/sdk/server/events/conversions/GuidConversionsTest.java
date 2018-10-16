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

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GuidConversionsTest {

    @Test
    public void testGuidToByteString() {
        long msb = 0xABCDABCDABCDABCDL;
        long lsb = 0xEFEFEFEFEFEFEFEFL;
        UUID uuid = new UUID(msb, lsb);

        ByteString bs = GuidConversions.guidToByteString(uuid);

        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(msb).putLong(lsb);

        assertEquals(bs, ByteString.of(bb.array()));
    }

    @Test
    public void testGuidToString() {
        UUID uuid = UUID.randomUUID();

        assertEquals(uuid.toString(), GuidConversions.guidToString(uuid));
    }

}
