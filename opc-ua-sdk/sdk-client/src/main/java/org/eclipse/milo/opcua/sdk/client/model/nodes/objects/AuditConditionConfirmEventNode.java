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
import org.eclipse.milo.opcua.sdk.client.model.types.objects.AuditConditionConfirmEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AuditConditionConfirmEventNode extends AuditConditionEventNode implements AuditConditionConfirmEventType {
    public AuditConditionConfirmEventNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getEventIdNode() {
        return getPropertyNode(AuditConditionConfirmEventType.EVENT_ID);
    }

    public CompletableFuture<ByteString> getEventId() {
        return getProperty(AuditConditionConfirmEventType.EVENT_ID);
    }

    public CompletableFuture<StatusCode> setEventId(ByteString value) {
        return setProperty(AuditConditionConfirmEventType.EVENT_ID, value);
    }

    public CompletableFuture<PropertyNode> getCommentNode() {
        return getPropertyNode(AuditConditionConfirmEventType.COMMENT);
    }

    public CompletableFuture<LocalizedText> getComment() {
        return getProperty(AuditConditionConfirmEventType.COMMENT);
    }

    public CompletableFuture<StatusCode> setComment(LocalizedText value) {
        return setProperty(AuditConditionConfirmEventType.COMMENT, value);
    }
}
