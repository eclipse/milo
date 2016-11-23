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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryValueUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditHistoryValueUpdateEventType")
public class AuditHistoryValueUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryValueUpdateEventType {

    public AuditHistoryValueUpdateEventNode(
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
    public NodeId getUpdatedNode() {
        Optional<NodeId> property = getProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUpdatedNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.UPDATED_NODE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUpdatedNode(NodeId value) {
        setProperty(AuditHistoryValueUpdateEventType.UPDATED_NODE, value);
    }

    @Override
    public PerformUpdateType getPerformInsertReplace() {
        Optional<PerformUpdateType> property = getProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getPerformInsertReplaceNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setPerformInsertReplace(PerformUpdateType value) {
        setProperty(AuditHistoryValueUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    @Override
    public DataValue[] getNewValues() {
        Optional<DataValue[]> property = getProperty(AuditHistoryValueUpdateEventType.NEW_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNewValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.NEW_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNewValues(DataValue[] value) {
        setProperty(AuditHistoryValueUpdateEventType.NEW_VALUES, value);
    }

    @Override
    public DataValue[] getOldValues() {
        Optional<DataValue[]> property = getProperty(AuditHistoryValueUpdateEventType.OLD_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryValueUpdateEventType.OLD_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOldValues(DataValue[] value) {
        setProperty(AuditHistoryValueUpdateEventType.OLD_VALUES, value);
    }

}
