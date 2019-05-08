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

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionRespondEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditConditionRespondEventTypeNode extends AuditConditionEventTypeNode implements AuditConditionRespondEventType {
    public AuditConditionRespondEventTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getSelectedResponseNode() {
        return getPropertyNode(AuditConditionRespondEventType.SELECTED_RESPONSE);
    }

    public CompletableFuture<Integer> getSelectedResponse() {
        return getProperty(AuditConditionRespondEventType.SELECTED_RESPONSE);
    }

    public CompletableFuture<StatusCode> setSelectedResponse(Integer value) {
        return setProperty(AuditConditionRespondEventType.SELECTED_RESPONSE, value);
    }
}
