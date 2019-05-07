/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditConditionConfirmEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditConditionConfirmEventTypeNode extends AuditConditionEventTypeNode implements AuditConditionConfirmEventType {
    public AuditConditionConfirmEventTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditConditionConfirmEventTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getEventIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditConditionConfirmEventType.EVENT_ID);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public ByteString getEventId() {
        Optional<ByteString> propertyValue = getProperty(AuditConditionConfirmEventType.EVENT_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEventId(ByteString value) {
        setProperty(AuditConditionConfirmEventType.EVENT_ID, value);
    }

    @Override
    public PropertyTypeNode getCommentNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditConditionConfirmEventType.COMMENT);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText getComment() {
        Optional<LocalizedText> propertyValue = getProperty(AuditConditionConfirmEventType.COMMENT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setComment(LocalizedText value) {
        setProperty(AuditConditionConfirmEventType.COMMENT, value);
    }
}
