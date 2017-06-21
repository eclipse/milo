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

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaPropertyNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class NodeManagerTest {

    @Test
    public void testDerivedInverseReferences() {
        UaNodeManager nodeManager = new UaNodeManager();

        UaNodeContext nodeContext = Mockito.mock(UaNodeContext.class);
        Mockito.when(nodeContext.getNodeManager()).thenReturn(nodeManager);

        UaFolderNode a = new UaFolderNode(
            nodeContext,
            new NodeId(0, "A"),
            new QualifiedName(0, "A"),
            LocalizedText.english("A")
        );

        nodeManager.addNode(a);

        UaPropertyNode b = new UaPropertyNode(
            nodeContext,
            new NodeId(0, "B"),
            new QualifiedName(0, "B"),
            LocalizedText.english("B")
        );

        nodeManager.addNode(b);

        a.addReference(new Reference(
            new NodeId(0, "A"),
            Identifiers.Organizes,
            new NodeId(0, "B").expanded(),
            NodeClass.Variable,
            true
        ));

        assertTrue(b.getReferences().contains(
            new Reference(
                new NodeId(0, "B"),
                Identifiers.Organizes,
                new NodeId(0, "A").expanded(),
                NodeClass.Object,
                false
            )
        ));
    }

}
