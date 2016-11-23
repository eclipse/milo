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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryRawModifyDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditHistoryRawModifyDeleteEventType")
public class AuditHistoryRawModifyDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryRawModifyDeleteEventType {

    public AuditHistoryRawModifyDeleteEventNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getIsDeleteModified() {
        Optional<Boolean> property = getProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getIsDeleteModifiedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setIsDeleteModified(Boolean value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED, value);
    }

    @Override
    public DateTime getStartTime() {
        Optional<DateTime> property = getProperty(AuditHistoryRawModifyDeleteEventType.START_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStartTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.START_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStartTime(DateTime value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.START_TIME, value);
    }

    @Override
    public DateTime getEndTime() {
        Optional<DateTime> property = getProperty(AuditHistoryRawModifyDeleteEventType.END_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEndTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.END_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEndTime(DateTime value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.END_TIME, value);
    }

    @Override
    public DataValue[] getOldValues() {
        Optional<DataValue[]> property = getProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.OLD_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES, value);
    }

}
