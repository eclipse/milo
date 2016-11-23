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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryEventUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditHistoryEventUpdateEventType")
public class AuditHistoryEventUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryEventUpdateEventType {

    public AuditHistoryEventUpdateEventNode(
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
        Optional<NodeId> property = getProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUpdatedNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.UPDATED_NODE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUpdatedNode(NodeId value) {
        setProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE, value);
    }

    @Override
    public PerformUpdateType getPerformInsertReplace() {
        Optional<PerformUpdateType> property = getProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getPerformInsertReplaceNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setPerformInsertReplace(PerformUpdateType value) {
        setProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    @Override
    public EventFilter getFilter() {
        Optional<EventFilter> property = getProperty(AuditHistoryEventUpdateEventType.FILTER);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getFilterNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.FILTER.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setFilter(EventFilter value) {
        setProperty(AuditHistoryEventUpdateEventType.FILTER, value);
    }

    @Override
    public HistoryEventFieldList[] getNewValues() {
        Optional<HistoryEventFieldList[]> property = getProperty(AuditHistoryEventUpdateEventType.NEW_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNewValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.NEW_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNewValues(HistoryEventFieldList[] value) {
        setProperty(AuditHistoryEventUpdateEventType.NEW_VALUES, value);
    }

    @Override
    public HistoryEventFieldList[] getOldValues() {
        Optional<HistoryEventFieldList[]> property = getProperty(AuditHistoryEventUpdateEventType.OLD_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.OLD_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOldValues(HistoryEventFieldList[] value) {
        setProperty(AuditHistoryEventUpdateEventType.OLD_VALUES, value);
    }

}
