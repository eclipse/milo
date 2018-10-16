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
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.NodeIdConversions.nodeIdToExpandedNodeId;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.NodeIdConversions.nodeIdToString;
import static org.testng.Assert.assertEquals;

public class NodeIdConversionsTest {

    @Test
    public void testNodeIdToExpandedNodeId() {
        NodeId nodeId = new NodeId(0, "foo");
        ExpandedNodeId expanded = nodeId.expanded();

        assertEquals(nodeIdToExpandedNodeId(nodeId), expanded);
    }

    @Test
    public void testNodeIdToString() {
        NodeId nodeId = new NodeId(0, "foo");

        assertEquals(nodeIdToString(nodeId), nodeId.toParseableString());
    }

}
