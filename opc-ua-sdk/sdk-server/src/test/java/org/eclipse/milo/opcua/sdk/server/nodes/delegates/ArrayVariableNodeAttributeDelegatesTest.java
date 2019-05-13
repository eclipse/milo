/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ArrayVariableNodeAttributeDelegatesTest {

    private OpcUaServer server;
    private UaNodeContext nodeContext;
    private UaNodeManager nodeManager;

    @BeforeTest
    public void setup() throws Exception {
        server = Mockito.mock(OpcUaServer.class);

        NamespaceTable namespaceTable = new NamespaceTable();
        Mockito.when(server.getNamespaceTable()).thenReturn(namespaceTable);

        nodeManager = new UaNodeManager();

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

        Mockito.when(server.getAddressSpaceManager()).thenReturn(addressSpaceManager);

        nodeContext = new UaNodeContext() {
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

        ObjectTypeManager objectTypeManager = new ObjectTypeManager();
        ObjectTypeManagerInitializer.initialize(
            server.getNamespaceTable(),
            objectTypeManager
        );

        VariableTypeManager variableTypeManager = new VariableTypeManager();
        VariableTypeManagerInitializer.initialize(
            server.getNamespaceTable(),
            variableTypeManager
        );
    }

    @Test
    public void testGet() {
        UaVariableNode arrayNode = UaVariableNode.builder(nodeContext)
            .setNodeId(new NodeId(0, "Test"))
            .setBrowseName(new QualifiedName(0, "Test"))
            .setDisplayName(LocalizedText.english("Test"))
            .setDataType(Identifiers.Int32)
            .setArrayDimensions(new UInteger[]{uint(0)})
            .setValueRank(ValueRanks.OneDimension)
            .build();

        nodeManager.addNode(arrayNode);

        ArrayVariableNodeAttributeDelegates.install(arrayNode);

        arrayNode.setValue(new DataValue(new Variant(new int[]{0, 1, 2, 3})));

        for (int i = 0; i < 4; i++) {
            QualifiedName browseName = new QualifiedName(0, String.format("0:Test[%d]", i));

            UaVariableNode elementNode = (UaVariableNode) arrayNode.findNode(browseName).get();

            assertNotNull(elementNode);

            assertEquals(elementNode.getValue().getValue().getValue(), i);

            elementNode.setValue(new DataValue(new Variant(i + 1)));
        }

        int[] arrayValue = (int[]) arrayNode.getValue().getValue().getValue();
        for (int i = 0; i < arrayValue.length; i++) {
            System.out.println(arrayValue[i]);
        }
        assertTrue(Objects.deepEquals(arrayValue, new int[]{1, 2, 3, 4}));
    }

}
