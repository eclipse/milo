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

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ObjectTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.VariableTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class NodeFactoryTest {

    @Test
    public void testCreateAnalogItemType() throws Exception {
        OpcUaServer server = Mockito.mock(OpcUaServer.class);

        UaNodeManager nodeManager = new UaNodeManager();
        NamespaceManager namespaceManager = new NamespaceManager();

        UaNodeContext context = new UaNodeContext() {
            @Override
            public UaNodeManager getNodeManager() {
                return nodeManager;
            }

            @Override
            public OpcUaServer getServer() {
                return server;
            }
        };

        Mockito.when(server.getNamespaceManager()).thenReturn(namespaceManager);

        new UaNodeLoader(context).loadNodes();

        ObjectTypeManager objectTypeManager = new ObjectTypeManager();
        ObjectTypeManagerInitializer.initialize(objectTypeManager);

        VariableTypeManager variableTypeManager = new VariableTypeManager();
        VariableTypeManagerInitializer.initialize(variableTypeManager);

        NodeFactory nodeFactory = new NodeFactory(
            context,
            objectTypeManager,
            variableTypeManager
        );

        AnalogItemNode analogItem = (AnalogItemNode) nodeFactory.createNode(
            new NodeId(1, "TestAnalog"),
            Identifiers.AnalogItemType,
            true
        );

        assertNotNull(analogItem);
        assertTrue(nodeManager.containsNode(analogItem));
    }

}
