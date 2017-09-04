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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryAtTimeDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditHistoryAtTimeDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryAtTimeDeleteEventType {
    public AuditHistoryAtTimeDeleteEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditHistoryAtTimeDeleteEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getReqTimesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DateTime[] getReqTimes() {
        Optional<DateTime[]> propertyValue = getProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES);
        return propertyValue.orElse(null);
    }

    public void setReqTimes(DateTime[] value) {
        setProperty(AuditHistoryAtTimeDeleteEventType.REQ_TIMES, value);
    }

    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DataValue[] getOldValues() {
        Optional<DataValue[]> propertyValue = getProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES);
        return propertyValue.orElse(null);
    }

    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryAtTimeDeleteEventType.OLD_VALUES, value);
    }
}
