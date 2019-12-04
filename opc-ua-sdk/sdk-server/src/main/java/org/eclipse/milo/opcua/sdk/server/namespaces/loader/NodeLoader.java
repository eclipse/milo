/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.namespaces.loader;

import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;

public class NodeLoader {

    private final UaNodeContext context;
    private final NodeManager<UaNode> nodeManager;

    public NodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    public void loadNodes() throws Exception {
        new DataTypeNodeLoader(context, nodeManager).loadAllNodes();
        new MethodNodeLoader(context, nodeManager).loadAllNodes();
        new ObjectNodeLoader(context, nodeManager).loadAllNodes();
        new ObjectTypeNodeLoader(context, nodeManager).loadAllNodes();
        new ReferenceTypeNodeLoader(context, nodeManager).loadAllNodes();
        new VariableNodeLoader(context, nodeManager).loadAllNodes();
        new VariableTypeNodeLoader(context, nodeManager).loadAllNodes();
    }

}
