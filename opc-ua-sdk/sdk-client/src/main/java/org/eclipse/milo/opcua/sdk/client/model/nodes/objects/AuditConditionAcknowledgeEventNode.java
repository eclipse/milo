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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionAcknowledgeEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


public class AuditConditionAcknowledgeEventNode extends AuditConditionEventNode implements AuditConditionAcknowledgeEventType {

    public AuditConditionAcknowledgeEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> eventId() {
        return getPropertyNode(AuditConditionAcknowledgeEventType.EVENT_ID.getBrowseName());
    }

    @Override
    public CompletableFuture<ByteString> getEventId() {
        return getProperty(AuditConditionAcknowledgeEventType.EVENT_ID);
    }

    @Override
    public CompletableFuture<StatusCode> setEventId(ByteString value) {
        return setProperty(AuditConditionAcknowledgeEventType.EVENT_ID, value);
    }

    @Override
    public CompletableFuture<PropertyNode> comment() {
        return getPropertyNode(AuditConditionAcknowledgeEventType.COMMENT.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getComment() {
        return getProperty(AuditConditionAcknowledgeEventType.COMMENT);
    }

    @Override
    public CompletableFuture<StatusCode> setComment(LocalizedText value) {
        return setProperty(AuditConditionAcknowledgeEventType.COMMENT, value);
    }


}