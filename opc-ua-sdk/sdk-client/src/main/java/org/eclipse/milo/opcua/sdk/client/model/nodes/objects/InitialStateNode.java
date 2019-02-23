/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.InitialStateType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class InitialStateNode extends StateNode implements InitialStateType {
    public InitialStateNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }
}
