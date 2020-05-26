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

import java.util.EnumSet;
import java.util.List;

import org.eclipse.milo.opcua.sdk.client.AddressSpace.BrowseOptions;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.ServerTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.ServerStatusTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressSpaceTest extends AbstractClientServerTest {

    @Test
    public void browse() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaNode serverNode = addressSpace.getNode(Identifiers.Server);
        List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode);

        nodes.forEach(n -> {
            System.out.println(String.format("%s (%s) [%s]",
                n.getBrowseName().toParseableString(),
                n.getNodeId().toParseableString(),
                n.getNodeClass()
            ));

            if (n instanceof UaVariableNode) {
                System.out.println("\u2514\u2500 value = " +
                    ((UaVariableNode) n).getValue().getValue());
            }
        });
    }

    @Test
    public void browseWithBrowseDirection() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        {
            UaNode serverNode = addressSpace.getNode(Identifiers.Server);
            BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
                b ->
                    b.setBrowseDirection(BrowseDirection.Inverse)
            );

            List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode, browseOptions);

            assertEquals(1, nodes.size());
            assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.ObjectsFolder)));
        }

        {
            UaNode objectsFolderNode = addressSpace.getNode(Identifiers.ObjectsFolder);
            BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
                b ->
                    b.setBrowseDirection(BrowseDirection.Both)
            );

            List<? extends UaNode> nodes = addressSpace.browseNodes(objectsFolderNode, browseOptions);

            assertEquals(2, nodes.size());
            assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.RootFolder)));
            assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server)));
        }
    }

    @Test
    public void browseWithReferenceType() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaNode serverNode = addressSpace.getNode(Identifiers.Server);

        BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
            b ->
                b.setReferenceType(BuiltinReferenceType.HasProperty)
        );

        List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode, browseOptions);

        assertEquals(5, nodes.size());
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServerArray)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_NamespaceArray)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_ServiceLevel)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_Auditing)));
        assertTrue(nodes.stream().anyMatch(n -> n.getNodeId().equals(Identifiers.Server_EstimatedReturnTime)));
    }

    @Test
    public void browseWithNodeClassMask() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();
        UaNode serverNode = addressSpace.getNode(Identifiers.Server);

        {
            BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
                b ->
                    b.setNodeClassMask(EnumSet.of(NodeClass.Method))
            );

            List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode, browseOptions);

            assertFalse(nodes.isEmpty());
            assertTrue(nodes.stream().allMatch(n -> n.getNodeClass() == NodeClass.Method));
        }

        {
            BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
                b ->
                    b.setNodeClassMask(EnumSet.of(NodeClass.Object))
            );

            List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode, browseOptions);

            assertFalse(nodes.isEmpty());
            assertTrue(nodes.stream().allMatch(n -> n.getNodeClass() == NodeClass.Object));
        }

        {
            BrowseOptions browseOptions = addressSpace.getBrowseOptions().copy(
                b ->
                    b.setNodeClassMask(EnumSet.of(NodeClass.Variable))
            );

            List<? extends UaNode> nodes = addressSpace.browseNodes(serverNode, browseOptions);

            assertFalse(nodes.isEmpty());
            assertTrue(nodes.stream().allMatch(n -> n.getNodeClass() == NodeClass.Variable));
        }
    }

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
