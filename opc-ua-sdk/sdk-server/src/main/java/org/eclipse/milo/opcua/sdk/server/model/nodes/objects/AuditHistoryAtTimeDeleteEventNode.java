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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryAtTimeDeleteEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditHistoryAtTimeDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryAtTimeDeleteEventType {
    public AuditHistoryAtTimeDeleteEventNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditHistoryAtTimeDeleteEventNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getReqTimesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime[] getReqTimes() {
        Optional<DateTime[]> propertyValue = getProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setReqTimes(DateTime[] value) {
        setProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES, value);
    }

    @Override
    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public DataValue[] getOldValues() {
        Optional<DataValue[]> propertyValue = getProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
        return propertyValue.orElse(null);
    }

    @Override
    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES, value);
    }
}
