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

public class UaNodeLoader {

    private final UaNodeContext context;
    private final NodeManager<UaNode> nodeManager;

    public UaNodeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager) {
        this.context = context;
        this.nodeManager = nodeManager;
    }

    public void loadNodes() throws Exception {
        new UaDataTypeLoader(context, nodeManager).buildNodes();
        new UaMethodLoader(context, nodeManager).buildNodes();
        new UaObjectLoader(context, nodeManager).buildNodes();
        new UaObjectTypeLoader(context, nodeManager).buildNodes();
        new UaReferenceTypeLoader(context, nodeManager).buildNodes();
        new UaVariableLoader(context, nodeManager).buildNodes();
        new UaVariableTypeLoader(context, nodeManager).buildNodes();
        new UaViewLoader(context, nodeManager).buildNodes();
    }

}
