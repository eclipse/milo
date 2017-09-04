/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditConditionCommentEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditConditionCommentEventNode extends AuditConditionEventNode implements AuditConditionCommentEventType {
    public AuditConditionCommentEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditConditionCommentEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getEventIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditConditionCommentEventType.EVENT_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public ByteString getEventId() {
        Optional<ByteString> propertyValue = getProperty(AuditConditionCommentEventType.EVENT_ID);
        return propertyValue.orElse(null);
    }

    public void setEventId(ByteString value) {
        setProperty(AuditConditionCommentEventType.EVENT_ID, value);
    }

    public PropertyNode getCommentNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditConditionCommentEventType.COMMENT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public LocalizedText getComment() {
        Optional<LocalizedText> propertyValue = getProperty(AuditConditionCommentEventType.COMMENT);
        return propertyValue.orElse(null);
    }

    public void setComment(LocalizedText value) {
        setProperty(AuditConditionCommentEventType.COMMENT, value);
    }
}
