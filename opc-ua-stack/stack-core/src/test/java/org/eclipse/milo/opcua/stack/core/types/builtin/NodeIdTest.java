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

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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
    public void testParseInt() {
        for (int i = 0; i < UShort.MAX_VALUE; i++) {
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

}