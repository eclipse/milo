/*
 * Copyright (c) 2016 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
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

        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(null)
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