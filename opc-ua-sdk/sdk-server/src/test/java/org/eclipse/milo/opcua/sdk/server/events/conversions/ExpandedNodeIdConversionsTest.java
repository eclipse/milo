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

import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.ExpandedNodeIdConversions.expandedNodeIdToNodeId;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ExpandedNodeIdConversions.expandedNodeIdToString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class ExpandedNodeIdConversionsTest {

    @Test
    public void testExpandedNodeIdToNodeId() {
        assertNull(
            expandedNodeIdToNodeId(
                new ExpandedNodeId(0, "foo", Namespaces.OPC_UA, 2)));

        NodeId nodeId = new NodeId(0, "bar");

        assertEquals(expandedNodeIdToNodeId(new ExpandedNodeId(nodeId)), nodeId);
    }

    @Test
    public void testExpandedNodeIdToString() {
        ExpandedNodeId e1 = new ExpandedNodeId(0, "foo", Namespaces.OPC_UA, 2);
        ExpandedNodeId e2 = new ExpandedNodeId(new NodeId(1, "bar"));

        assertEquals(expandedNodeIdToString(e1), e1.toParseableString());
        assertEquals(expandedNodeIdToString(e2), e2.toParseableString());
    }

}
