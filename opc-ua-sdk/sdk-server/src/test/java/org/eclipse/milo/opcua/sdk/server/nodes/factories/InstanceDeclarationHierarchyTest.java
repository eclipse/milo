package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.namespaces.loader.UaNodeLoader;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.testng.annotations.Test;

public class InstanceDeclarationHierarchyTest {



    @Test
    public void test() throws Exception {
        UaNodeManager nodeManager = new UaNodeManager();


        UaNodeContext context = new UaNodeContext() {
            @Override
            public UaNodeManager getNodeManager() {
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