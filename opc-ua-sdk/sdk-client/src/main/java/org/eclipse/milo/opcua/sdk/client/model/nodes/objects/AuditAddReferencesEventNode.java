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

    @Override
    public CompletableFuture<PropertyNode> referencesToAdd() {
        return getPropertyNode(AuditAddReferencesEventType.REFERENCES_TO_ADD.getBrowseName());
    }

    @Override
    public CompletableFuture<AddReferencesItem[]> getReferencesToAdd() {
        return getProperty(AuditAddReferencesEventType.REFERENCES_TO_ADD);
    }

    @Override
    public CompletableFuture<StatusCode> setReferencesToAdd(AddReferencesItem[] value) {
        return setProperty(AuditAddReferencesEventType.REFERENCES_TO_ADD, value);
    }


}