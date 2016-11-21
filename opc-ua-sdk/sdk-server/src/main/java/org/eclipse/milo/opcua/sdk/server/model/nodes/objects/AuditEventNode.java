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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditEventType")
public class AuditEventNode extends BaseEventNode implements AuditEventType {

    public AuditEventNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public DateTime getActionTimeStamp() {
        Optional<DateTime> property = getProperty(AuditEventType.ACTION_TIME_STAMP);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getActionTimeStampNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditEventType.ACTION_TIME_STAMP.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setActionTimeStamp(DateTime value) {
        setProperty(AuditEventType.ACTION_TIME_STAMP, value);
    }

    @Override
    public Boolean getStatus() {
        Optional<Boolean> property = getProperty(AuditEventType.STATUS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStatusNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditEventType.STATUS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStatus(Boolean value) {
        setProperty(AuditEventType.STATUS, value);
    }

    @Override
    public String getServerId() {
        Optional<String> property = getProperty(AuditEventType.SERVER_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getServerIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditEventType.SERVER_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setServerId(String value) {
        setProperty(AuditEventType.SERVER_ID, value);
    }

    @Override
    public String getClientAuditEntryId() {
        Optional<String> property = getProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientAuditEntryIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditEventType.CLIENT_AUDIT_ENTRY_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientAuditEntryId(String value) {
        setProperty(AuditEventType.CLIENT_AUDIT_ENTRY_ID, value);
    }

    @Override
    public String getClientUserId() {
        Optional<String> property = getProperty(AuditEventType.CLIENT_USER_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientUserIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditEventType.CLIENT_USER_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientUserId(String value) {
        setProperty(AuditEventType.CLIENT_USER_ID, value);
    }

}
