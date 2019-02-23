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
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditAddReferencesEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesItem;

public class AuditAddReferencesEventNode extends AuditNodeManagementEventNode implements AuditAddReferencesEventType {
    public AuditAddReferencesEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getReferencesToAddNode() {
        return getPropertyNode(AuditAddReferencesEventType.REFERENCES_TO_ADD);
    }

    public CompletableFuture<AddReferencesItem[]> getReferencesToAdd() {
        return getProperty(AuditAddReferencesEventType.REFERENCES_TO_ADD);
    }

    public CompletableFuture<StatusCode> setReferencesToAdd(AddReferencesItem[] value) {
        return setProperty(AuditAddReferencesEventType.REFERENCES_TO_ADD, value);
    }
}
