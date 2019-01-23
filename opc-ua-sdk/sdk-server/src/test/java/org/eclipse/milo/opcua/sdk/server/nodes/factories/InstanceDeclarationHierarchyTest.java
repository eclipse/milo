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

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.testng.annotations.Test;

public class InstanceDeclarationHierarchyTest {



    @Test
    public void test() throws Exception {
        UaNodeManager nodeManager = new UaNodeManager();


        UaNodeContext context = new UaNodeContext() {
            @Override
            public NodeManager<UaNode> getNodeManager() {
                return nodeManager;
            }

            @Override
            public OpcUaServer getServer() {
                return null;
            }
        };

        new UaNodeLoader(context).loadNodes();

        InstanceDeclarationHierarchy idh = InstanceDeclarationHierarchy
            .create(nodeManager, Identifiers.AnalogItemType, true);

        System.out.println(idh);
    }

}
