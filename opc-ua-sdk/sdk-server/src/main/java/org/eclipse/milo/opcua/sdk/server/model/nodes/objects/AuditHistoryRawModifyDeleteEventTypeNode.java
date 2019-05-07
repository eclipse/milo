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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryRawModifyDeleteEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditHistoryRawModifyDeleteEventTypeNode extends AuditHistoryDeleteEventTypeNode implements AuditHistoryRawModifyDeleteEventType {
    public AuditHistoryRawModifyDeleteEventTypeNode(UaNodeContext context, NodeId nodeId,
                                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                    UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditHistoryRawModifyDeleteEventTypeNode(UaNodeContext context, NodeId nodeId,
                                                    QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                                    UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getIsDeleteModifiedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getIsDeleteModified() {
        Optional<Boolean> propertyValue = getProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED);
        return propertyValue.orElse(null);
    }

    @Override
    public void setIsDeleteModified(Boolean value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.IS_DELETE_MODIFIED, value);
    }

    @Override
    public PropertyTypeNode getStartTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.START_TIME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getStartTime() {
        Optional<DateTime> propertyValue = getProperty(AuditHistoryRawModifyDeleteEventType.START_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStartTime(DateTime value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.START_TIME, value);
    }

    @Override
    public PropertyTypeNode getEndTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.END_TIME);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getEndTime() {
        Optional<DateTime> propertyValue = getProperty(AuditHistoryRawModifyDeleteEventType.END_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setEndTime(DateTime value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.END_TIME, value);
    }

    @Override
    public PropertyTypeNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DataValue[] getOldValues() {
        Optional<DataValue[]> propertyValue = getProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryRawModifyDeleteEventType.OLD_VALUES, value);
    }
}
