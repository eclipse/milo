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

import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class NodeIdTest {

    @Test
    public void testParseLargerThanIntMax() {
        long i = Integer.MAX_VALUE + 1L;
        NodeId nodeId = NodeId.parse("ns=1;i=" + i);

        assertNotNull(nodeId);
        assertEquals(nodeId.getIdentifier(), uint(i));
    }

    @Test
    public void testStringWithNewlineCharacters() {
        NodeId nodeId1 = NodeId.parse("s=foo\nbar\nbaz");
        assertNotNull(nodeId1);
        assertNotNull(NodeId.parse(nodeId1.toParseableString()));

        NodeId nodeId2 = NodeId.parse("ns=2;s=foo\n\bar\nbaz");
        assertNotNull(nodeId2);
        assertNotNull(NodeId.parse(nodeId2.toParseableString()));
    }

}