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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.ObjectTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.VariableTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemTypeNode;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.NodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UaNodeTest {

    private OpcUaServer server;

    @BeforeEach
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

        new NodeLoader(nodeContext, nodeManager).loadNodes();

        ObjectTypeInitializer.initialize(
            server.getNamespaceTable(),
            objectTypeManager
        );

        VariableTypeInitializer.initialize(
            server.getNamespaceTable(),
            variableTypeManager
        );
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
        assertEquals(nodeManager.getReferences(nodeId).size(), 0);

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
        assertEquals(nodeManager.getReferences(nodeId).size(), 0);
        assertEquals(nodeManager.getReferences(Identifiers.ObjectNode).size(), 0);
    }

    @Test
    public void testCreateDeleteComplexInstance() throws UaException {
        NodeId nodeId = new NodeId(1, "TestAnalog");

        UaNodeManager nodeManager = new UaNodeManager();
        server.getAddressSpaceManager().register(nodeManager);

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(nodeManager.getReferences(nodeId).size(), 0);

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

        AnalogItemTypeNode analogItem = (AnalogItemTypeNode) nodeFactory.createNode(
            nodeId,
            Identifiers.AnalogItemType,
            new NodeFactory.InstantiationCallback() {
                @Override
                public boolean includeOptionalNode(NodeId typeDefinitionId, QualifiedName browseName) {
                    return true;
                }
            }
        );

        assertTrue(nodeManager.containsNode(nodeId));
        assertTrue(nodeManager.getReferences(nodeId).size() > 0);

        analogItem.delete();

        assertFalse(nodeManager.containsNode(nodeId));
        assertEquals(nodeManager.getReferences(nodeId).size(), 0);
    }

}
