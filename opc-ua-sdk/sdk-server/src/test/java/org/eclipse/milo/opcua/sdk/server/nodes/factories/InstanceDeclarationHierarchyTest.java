/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.List;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class InstanceDeclarationHierarchyTest {


    @Test
    public void test() throws Exception {
        NamespaceTable namespaceTable = new NamespaceTable();

        UaNodeManager nodeManager = new UaNodeManager();

        AddressSpaceManager addressSpaceManager = Mockito.mock(AddressSpaceManager.class);

        Mockito
            .when(addressSpaceManager.getManagedNode(Mockito.any(NodeId.class)))
            .then(
                (Answer<Optional<UaNode>>) invocationOnMock ->
                    nodeManager.getNode(invocationOnMock.getArgument(0))
            );

        Mockito
            .when(addressSpaceManager.getManagedNode(Mockito.any(ExpandedNodeId.class)))
            .then(
                (Answer<Optional<UaNode>>) invocationOnMock ->
                    nodeManager.getNode(invocationOnMock.getArgument(0), namespaceTable)
            );

        Mockito
            .when(addressSpaceManager.getManagedReferences(Mockito.any(NodeId.class)))
            .then(
                (Answer<List<Reference>>) invocationOnMock ->
                    nodeManager.getReferences(invocationOnMock.getArgument(0))
            );

        OpcUaServer server = Mockito.mock(OpcUaServer.class);

        Mockito.when(server.getAddressSpaceManager()).thenReturn(addressSpaceManager);
        Mockito.when(server.getNamespaceTable()).thenReturn(namespaceTable);

        UaNodeContext context = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        new UaNodeLoader(context, nodeManager).loadNodes();

        InstanceDeclarationHierarchy idh = InstanceDeclarationHierarchy.create(
            addressSpaceManager,
            namespaceTable,
            Identifiers.AnalogItemType,
            true
        );

        System.out.println(idh);

        assertNotNull(idh);
    }

}
