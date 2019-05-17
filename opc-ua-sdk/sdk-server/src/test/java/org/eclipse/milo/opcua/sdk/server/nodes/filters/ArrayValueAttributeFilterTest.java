/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.filters;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.ObjectTypeManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.VariableTypeManager;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.ObjectTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.model.VariableTypeManagerInitializer;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.testng.Assert.assertEquals;

public class ArrayValueAttributeFilterTest {

    private final UaNodeManager nodeManager = new UaNodeManager();

    private OpcUaServer server;
    private UaNodeContext context;

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

        addressSpaceManager.register(nodeManager);

        context = new UaNodeContext() {
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

        ObjectTypeManagerInitializer.initialize(
            server.getNamespaceTable(),
            objectTypeManager
        );

        VariableTypeManagerInitializer.initialize(
            server.getNamespaceTable(),
            variableTypeManager
        );
    }

    @Test
    public void testArrayValueAttributeFilter() {
        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(context)
            .setNodeId(NodeId.NULL_VALUE)
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(new QualifiedName(1, "foo"))
            .setDisplayName(LocalizedText.NULL_VALUE)
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.getFilterChain().addLast(new ArrayValueAttributeFilter(Identifiers.BaseDataVariableType));

        for (int size = 1; size <= 10; size++) {
            int[] vs = new int[size];
            for (int i = 0; i < size; i++) {
                vs[i] = i;
            }
            node.setValue(new DataValue(new Variant(vs)));

            for (int i = 0; i < size; i++) {
                UaVariableNode elementNode = (UaVariableNode) node.findNode(
                    new QualifiedName(1, String.format("foo[%s]", i))
                ).get();

                assertEquals(elementNode.getValue().getValue().getValue(), i);
            }
        }

        assertEquals(node.getComponentNodes().size(), 10);

        node.setValue(new DataValue(new Variant(new int[0])));

        assertEquals(node.getComponentNodes().size(), 0);
    }

}
