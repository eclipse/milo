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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.testng.Assert.assertEquals;

public class AttributeDelegateChainTest {

    @Test
    public void testCreate() throws Exception {
        List<String> list = new ArrayList<>();

        AttributeDelegate delegate = AttributeDelegateChain.create(
            new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    list.add("root");
                    return node.getValue();
                }
            },
            parent -> new DelegatingAttributeDelegate(parent) {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    list.add("child1");
                    return super.getValue(context, node);
                }
            },
            parent -> new DelegatingAttributeDelegate(parent) {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    list.add("child2");
                    return super.getValue(context, node);
                }
            },
            parent -> new DelegatingAttributeDelegate(parent) {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
                    list.add("child3");
                    return super.getValue(context, node);
                }
            }
        );

        UaNodeManager nodeManager = new UaNodeManager();

        UaNodeContext context = new UaNodeContext() {
            @Override
            public OpcUaServer getServer() {
                return null;
            }

            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }

            @Override
            public NamespaceTable getNamespaceTable() {
                return new NamespaceTable();
            }
        };

        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(context)
            .setNodeId(NodeId.NULL_VALUE)
            .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
            .setBrowseName(QualifiedName.NULL_VALUE)
            .setDisplayName(LocalizedText.NULL_VALUE)
            .setDataType(Identifiers.String)
            .setTypeDefinition(Identifiers.BaseDataVariableType)
            .build();

        node.setValue(new DataValue(new Variant("foo")));

        DataValue value = delegate.getValue(new AttributeContext(null, null), node);
        assertEquals(value.getValue().getValue(), "foo");

        assertEquals(list.get(0), "child3");
        assertEquals(list.get(1), "child2");
        assertEquals(list.get(2), "child1");
        assertEquals(list.get(3), "root");
    }

}
