/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Random;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertThrows;

public class NodeIdTest {

    @Test
    public void testParseLargerThanIntMax() {
        long i = Integer.MAX_VALUE + 1L;
        NodeId nodeId = NodeId.parse("ns=1;i=" + i);

        assertNotNull(nodeId);
        assertEquals(nodeId.getIdentifier(), uint(i));
    }

    @Test
    public void testParseInvalid() {
        assertNull(NodeId.parseOrNull(""));
        assertNull(NodeId.parseOrNull("n"));
        assertNull(NodeId.parseOrNull("ns"));
        assertNull(NodeId.parseOrNull("ns="));
        assertNull(NodeId.parseOrNull("ns=0"));
        assertNull(NodeId.parseOrNull("ns=0;"));
        assertNull(NodeId.parseOrNull("ns=0;s"));
    }

    @Test
    public void testParseInvalidInt() {
        assertThrows(UaRuntimeException.class, () -> NodeId.parse("ns=0;i=" + UInteger.MAX_VALUE + 1));
        assertThrows(UaRuntimeException.class, () -> NodeId.parse("ns=0;i=" + -1));
    }

    @Test
    public void testParseNamespaceIndex() {
        for (int i = 0; i < UShort.MAX_VALUE; i++) {
            NodeId nodeId = NodeId.parseOrNull("ns=" + i + ";i=" + i);
            assertNotNull(nodeId);
            assertEquals(nodeId.getNamespaceIndex(), ushort(i));
            assertEquals(nodeId.getIdentifier(), uint(i));
        }
    }

    @Test
    public void testParseInt() {
        long[] is = new long[]{0, UByte.MAX_VALUE, UShort.MAX_VALUE, UInteger.MAX_VALUE};

        for (long i : is) {
            assertNotNull(NodeId.parseOrNull("i=" + i));
            assertNotNull(NodeId.parseOrNull("ns=0;i=" + i));
        }
    }

    @Test
    public void testParseString() {
        assertNotNull(NodeId.parseOrNull("s="));
        assertNotNull(NodeId.parseOrNull("s=foo"));
        assertNotNull(NodeId.parseOrNull("ns=0;s="));
        assertNotNull(NodeId.parseOrNull("ns=0;s=foo"));
    }

    @Test
    public void testParseGuid() {
        for (int i = 0; i < 100; i++) {
            UUID uuid = UUID.randomUUID();
            {
                NodeId nodeId = NodeId.parseOrNull("g=" + uuid.toString());
                assertNotNull(nodeId);
                assertEquals(nodeId.getIdentifier(), uuid);
            }
            {
                NodeId nodeId = NodeId.parseOrNull("ns=0;g=" + uuid.toString());
                assertNotNull(nodeId);
                assertEquals(nodeId.getIdentifier(), uuid);
            }
        }
    }

    @Test
    public void testParseByteString() {
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            byte[] bs = new byte[r.nextInt(64) + 1];
            r.nextBytes(bs);
            String bss = DatatypeConverter.printBase64Binary(bs);

            {
                NodeId nodeId = NodeId.parseOrNull("b=" + bss);
                assertNotNull(nodeId);
                assertEquals(nodeId.getIdentifier(), ByteString.of(bs));
            }
            {
                NodeId nodeId = NodeId.parseOrNull("ns=0;b=" + bss);
                assertNotNull(nodeId);
                assertEquals(nodeId.getIdentifier(), ByteString.of(bs));
            }
        }
    }

}