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

import static org.eclipse.milo.opcua.sdk.server.events.conversions.NodeIdConversions.nodeIdToExpandedNodeId;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.NodeIdConversions.nodeIdToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.junit.jupiter.api.Test;

public class NodeIdConversionsTest {

    @Test
    public void testNodeIdToExpandedNodeId() {
        NodeId nodeId = new NodeId(0, "foo");
        ExpandedNodeId expanded = nodeId.expanded();

        assertEquals(expanded, nodeIdToExpandedNodeId(nodeId));
    }

    @Test
    public void testNodeIdToString() {
        NodeId nodeId = new NodeId(0, "foo");

        assertEquals(nodeId.toParseableString(), nodeIdToString(nodeId));
    }

}
