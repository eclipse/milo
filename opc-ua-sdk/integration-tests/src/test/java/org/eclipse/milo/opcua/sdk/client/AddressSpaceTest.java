/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressSpaceTest extends AbstractClientServerTest {

    @Test
    public void getNode() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaNode serverNode = addressSpace.getNode(Identifiers.Server);
        assertNotNull(serverNode);
        assertTrue(serverNode instanceof ServerTypeNode);

        UaNode serverStatusNode = addressSpace.getNode(Identifiers.Server_ServerStatus);
        assertNotNull(serverStatusNode);
        assertTrue(serverStatusNode instanceof ServerStatusTypeNode);
    }

    @Test
    public void getObjectNode() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();
        ServerTypeNode serverNode = (ServerTypeNode) addressSpace.getObjectNode(Identifiers.Server);

        assertNotNull(serverNode);
        assertEquals(Identifiers.Server, serverNode.getNodeId());

        // should be cached now, check instance equality
        assertSame(serverNode, addressSpace.getObjectNode(Identifiers.Server));
    }

    @Test
    public void getObjectNodeWithNonObject() {
        AddressSpace addressSpace = client.getAddressSpace();

        assertThrows(
            UaException.class,
            () -> addressSpace.getObjectNode(Identifiers.Server_ServerStatus)
        );
    }

    @Test
    public void getVariableNode() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();
        ServerStatusTypeNode serverNode = (ServerStatusTypeNode)
            addressSpace.getVariableNode(Identifiers.Server_ServerStatus);

        assertNotNull(serverNode);
        assertEquals(Identifiers.Server_ServerStatus, serverNode.getNodeId());

        // should be cached now, check instance equality
        assertSame(serverNode, addressSpace.getVariableNode(Identifiers.Server_ServerStatus));
    }

    @Test
    public void getVariableNodeWithNonVariable() {
        AddressSpace addressSpace = client.getAddressSpace();

        assertThrows(
            UaException.class,
            () -> addressSpace.getVariableNode(Identifiers.Server)
        );
    }

}
