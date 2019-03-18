/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ObjectTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.VariableTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UaNodeTest {

    private OpcUaServer server;

    @BeforeTest
    public void setup() throws Exception {
        server = Mockito.mock(OpcUaServer.class);

        AddressSpaceManager addressSpaceManager = new AddressSpaceManager(server);
        NamespaceTable namespaceTable = new NamespaceTable();
        ObjectTypeManager objectTypeManager = new ObjectTypeManager();
        VariableTypeManager variableTypeManager = new VariableTypeManager();

        Mockito.when(server.getNamespaceTable()).thenReturn(namespaceTable);
        Mockito.when(server.getAddressSpaceManager()).thenReturn(addressSpaceManager);
        Mockito.when(server.getObjectTypeManager()).thenReturn(objectTypeManager);
        Mockito.when(server.getVariableTypeManager()).thenReturn(variableTypeManager);

        UaNodeManager nodeManager = new UaNodeManager();
        addressSpaceManager.register(nodeManager);

        UaNodeContext nodeContext = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        new UaNodeLoader(nodeContext, nodeManager).loadNodes();

        ObjectTypeManagerInitializer.initialize(
            server.getNamespaceTable(),
            objectTypeManager
        );

        VariableTypeManagerInitializer.initialize(variableTypeManager);
    }

    @Test
    public void testCreateDelete() {
        NodeId nodeId = new NodeId(1, "TestObject");

        UaNodeManager nodeManager = new UaNodeManager();
        server.getAddressSpaceManager().register(nodeManager);

        UaNodeContext nodeContext = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }
        };

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(0, nodeManager.getReferences(nodeId).size());

        UaObjectNode objectNode = UaObjectNode.builder(nodeContext)
            .setNodeId(nodeId)
            .setBrowseName(new QualifiedName(1, "TestObject"))
            .setDisplayName(LocalizedText.english("TestObject"))
            .setTypeDefinition(Identifiers.FolderType)
            .build();

        nodeManager.addNode(objectNode);

        objectNode.addReference(new Reference(
            nodeId,
            Identifiers.HasComponent,
            Identifiers.ObjectNode.expanded(),
            Reference.Direction.INVERSE
        ));

        assertTrue(nodeManager.containsNode(nodeId));
        assertTrue(nodeManager.getReferences(nodeId).size() > 0);
        assertTrue(nodeManager.getReferences(Identifiers.ObjectNode).size() > 0);

        objectNode.delete();

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(0, nodeManager.getReferences(nodeId).size());
        assertEquals(0, nodeManager.getReferences(Identifiers.ObjectNode).size());
    }

    @Test
    public void testCreateDeleteComplexInstance() throws UaException {
        NodeId nodeId = new NodeId(1, "TestAnalog");

        UaNodeManager nodeManager = new UaNodeManager();
        server.getAddressSpaceManager().register(nodeManager);

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(0, nodeManager.getReferences(nodeId).size());

        NodeFactory nodeFactory = new NodeFactory(
            new UaNodeContext() {
                @Override
                public OpcUaServer getServer() {
                    return server;
                }

                @Override
                public NodeManager<UaNode> getNodeManager() {
                    return nodeManager;
                }
            }
        );

        AnalogItemNode analogItem = (AnalogItemNode) nodeFactory.createNode(
            nodeId,
            Identifiers.AnalogItemType,
            true
        );

        assertTrue(nodeManager.containsNode(nodeId));
        assertTrue(nodeManager.getReferences(nodeId).size() > 0);

        analogItem.delete();

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(0, nodeManager.getReferences(nodeId).size());
    }

}
