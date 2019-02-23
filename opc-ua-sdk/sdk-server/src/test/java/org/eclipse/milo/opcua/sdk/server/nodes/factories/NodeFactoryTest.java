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

import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.sdk.server.NamespaceManager;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ObjectTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.VariableTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class NodeFactoryTest {

    private OpcUaServer server;
    private UaNodeManager nodeManager;
    private NodeFactory nodeFactory;

    @BeforeTest
    public void setup() throws Exception {
        server = Mockito.mock(OpcUaServer.class);

        nodeManager = new UaNodeManager();
        NamespaceManager namespaceManager = new NamespaceManager();

        UaNodeContext context = new UaNodeContext() {
            @Override
            public NodeManager<UaNode> getNodeManager() {
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

        nodeFactory = new NodeFactory(
            context,
            objectTypeManager,
            variableTypeManager
        );
    }

    @Test
    public void testCreateAnalogItemType() throws Exception {
        AnalogItemNode analogItem = (AnalogItemNode) nodeFactory.createNode(
            new NodeId(1, "TestAnalog"),
            Identifiers.AnalogItemType,
            true
        );

        assertNotNull(analogItem);
        assertTrue(nodeManager.containsNode(analogItem));
    }

    @Test
    public void testInstanceListener() throws Exception {
        final AtomicBoolean methodAdded = new AtomicBoolean(false);
        final AtomicBoolean objectAdded = new AtomicBoolean(false);
        final AtomicBoolean variableAdded = new AtomicBoolean(false);

        ServerNode serverNode = (ServerNode) nodeFactory.createNode(
            new NodeId(1, "Server"),
            Identifiers.ServerType,
            true,
            new NodeFactory.InstanceListener() {
                @Override
                public void onMethodAdded(@Nullable UaObjectNode parent, UaMethodNode instance) {
                    String pbn = parent != null ? parent.getBrowseName().getName() : null;
                    System.out.println("onMethodAdded parent=" + pbn + " instance=" + instance.getBrowseName().getName());
                    methodAdded.set(true);
                }

                @Override
                public void onObjectAdded(@Nullable UaObjectNode parent, UaObjectNode instance, NodeId typeDefinitionId) {
                    String pbn = parent != null ? parent.getBrowseName().getName() : null;
                    System.out.println("onObjectAdded parent=" + pbn + " instance=" + instance.getBrowseName().getName());
                    objectAdded.set(true);
                }

                @Override
                public void onVariableAdded(@Nullable UaNode parent, UaVariableNode instance, NodeId typeDefinitionId) {
                    String pbn = parent != null ? parent.getBrowseName().getName() : null;
                    System.out.println("onVariableAdded parent=" + pbn + " instance=" + instance.getBrowseName().getName());
                    variableAdded.set(true);
                }
            }
        );

        assertTrue(methodAdded.get());
        assertTrue(objectAdded.get());
        assertTrue(variableAdded.get());
    }

}
