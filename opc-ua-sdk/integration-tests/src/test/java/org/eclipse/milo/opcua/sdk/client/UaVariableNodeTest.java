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

import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.AnalogItemTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilter;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilterContext;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UaVariableNodeTest extends AbstractClientServerTest {

    @Test
    public void getVariableComponent() throws UaException {
        UaVariableNode variableNode = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus);

        assertNotNull(variableNode.getVariableComponent("CurrentTime"));
    }

    @Test
    public void getTypeDefinition() throws UaException {
        UaVariableNode variableNode = client.getAddressSpace().getVariableNode(Identifiers.Server_ServerStatus);

        UaVariableTypeNode variableTypeNode = variableNode.getTypeDefinition();

        assertEquals(variableTypeNode.getNodeId(), Identifiers.ServerStatusType);
    }

    @Test
    public void analogValueNode() throws UaException {
        AnalogItemTypeNode analogNode = (AnalogItemTypeNode) client.getAddressSpace()
            .getVariableNode(NodeId.parse("ns=2;s=TestAnalogValue"));

        assertNotNull(analogNode.getEuRange());
    }

    @Test
    public void readAttributeWithIncorrectDataType() throws UaException {
        testNamespace.configureNode((nodeContext, nodeManager) -> {
            org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode serverNode =
                new org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode.UaVariableNodeBuilder(nodeContext)
                    .setNodeId(new NodeId(testNamespace.getNamespaceIndex(), "IncorrectMinimumSamplingIntervalDataType"))
                    .setAccessLevel(AccessLevel.READ_WRITE)
                    .setUserAccessLevel(AccessLevel.READ_WRITE)
                    .setBrowseName(new QualifiedName(testNamespace.getNamespaceIndex(), "IncorrectMinimumSamplingIntervalDataType"))
                    .setDisplayName(LocalizedText.english("IncorrectMinimumSamplingIntervalDataType"))
                    .setDataType(Identifiers.Double)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

            serverNode.getFilterChain().addLast(new AttributeFilter() {
                @Override
                public Object getAttribute(AttributeFilterContext.GetAttributeContext ctx, AttributeId attributeId) {
                    if (attributeId == AttributeId.MinimumSamplingInterval) {
                        // intentionally return the wrong datatype
                        return uint(100);
                    } else {
                        return ctx.getAttribute(attributeId);
                    }
                }
            });

            serverNode.addReference(new Reference(
                serverNode.getNodeId(),
                Identifiers.HasComponent,
                Identifiers.ObjectsFolder.expanded(),
                Reference.Direction.INVERSE
            ));

            nodeManager.addNode(serverNode);
        });

        UaVariableNode variableNode = client.getAddressSpace().getVariableNode(
            new NodeId(2, "IncorrectMinimumSamplingIntervalDataType")
        );

        assertNull(variableNode.getMinimumSamplingInterval());
    }

}
