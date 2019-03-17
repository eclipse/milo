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

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ExpandedNodeIdTest {

    private final NamespaceTable namespaceTable = new NamespaceTable();

    @Test
    public void testLocal() {
        namespaceTable.addUri("uri:test");

        ExpandedNodeId xni0 = new ExpandedNodeId(new NodeId(0, "test"));
        assertTrue(xni0.local(namespaceTable).isPresent());

        ExpandedNodeId xni1 = new ExpandedNodeId(new NodeId(1, "test"));
        assertTrue(xni1.local(namespaceTable).isPresent());

        ExpandedNodeId xni2 = new ExpandedNodeId(new NodeId(99, "test"), namespaceTable.getUri(0));
        assertTrue(xni2.local(namespaceTable).isPresent());

        ExpandedNodeId xni3 = new ExpandedNodeId(new NodeId(99, "test"), namespaceTable.getUri(1));
        assertTrue(xni3.local(namespaceTable).isPresent());

        ExpandedNodeId xni4 = new ExpandedNodeId(new NodeId(99, "test"), "uri:notpresent");
        assertFalse(xni4.local(namespaceTable).isPresent());
    }

}
