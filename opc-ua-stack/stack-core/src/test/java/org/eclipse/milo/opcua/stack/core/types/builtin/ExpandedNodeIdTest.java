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

import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ExpandedNodeIdTest {

    private final NamespaceTable namespaceTable = new NamespaceTable();

    @Test
    public void testLocal() {
        namespaceTable.addUri("uri:test");

        ExpandedNodeId xni0 = new ExpandedNodeId(ushort(0), null, "test");
        assertTrue(xni0.local(namespaceTable).isPresent());

        ExpandedNodeId xni1 = new ExpandedNodeId(ushort(1), null, "test");
        assertTrue(xni1.local(namespaceTable).isPresent());

        ExpandedNodeId xni2 = new ExpandedNodeId(ushort(99), namespaceTable.getUri(0), "test");
        assertTrue(xni2.local(namespaceTable).isPresent());

        ExpandedNodeId xni3 = new ExpandedNodeId(ushort(99), namespaceTable.getUri(1), "test");
        assertTrue(xni3.local(namespaceTable).isPresent());

        ExpandedNodeId xni4 = new ExpandedNodeId(ushort(99), "uri:notpresent", "test");
        assertFalse(xni4.local(namespaceTable).isPresent());
    }

    @Test
    public void testIsNull() {
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier(uint(0)).build().isNull());
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier((String) null).build().isNull());
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier("").build().isNull());
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier(new UUID(0, 0)).build().isNull());
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier(ByteString.NULL_VALUE).build().isNull());
        assertTrue(ExpandedNodeId.newBuilder().setIdentifier(ByteString.of(new byte[0])).build().isNull());
    }
    
}
