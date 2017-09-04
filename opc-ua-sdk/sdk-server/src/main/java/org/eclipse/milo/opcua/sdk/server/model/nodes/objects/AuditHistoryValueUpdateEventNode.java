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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryValueUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public class AuditHistoryValueUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryValueUpdateEventType {
    public AuditHistoryValueUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditHistoryValueUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getUpdatedNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.UPDATED_NODE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public NodeId getUpdatedNode() {
        Optional<NodeId> propertyValue = getProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE);
        return propertyValue.orElse(null);
    }

    public void setUpdatedNode(NodeId value) {
        setProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE, value);
    }

    public PropertyNode getPerformInsertReplaceNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public PerformUpdateType getPerformInsertReplace() {
        Optional<PerformUpdateType> propertyValue = getProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);
        return propertyValue.orElse(null);
    }

    public void setPerformInsertReplace(PerformUpdateType value) {
        setProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    public PropertyNode getNewValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.NEW_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DataValue[] getNewValues() {
        Optional<DataValue[]> propertyValue = getProperty(AuditHistoryValueUpdateEventType.NEW_VALUES);
        return propertyValue.orElse(null);
    }

    public void setNewValues(DataValue[] value) {
        setProperty(AuditHistoryValueUpdateEventType.NEW_VALUES, value);
    }

    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.OLD_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public DataValue[] getOldValues() {
        Optional<DataValue[]> propertyValue = getProperty(AuditHistoryValueUpdateEventType.OLD_VALUES);
        return propertyValue.orElse(null);
    }

    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryValueUpdateEventType.OLD_VALUES, value);
    }
}
