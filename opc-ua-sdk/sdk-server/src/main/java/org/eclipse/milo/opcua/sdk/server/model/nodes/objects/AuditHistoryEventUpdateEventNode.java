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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryEventUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public class AuditHistoryEventUpdateEventNode extends AuditHistoryUpdateEventNode implements AuditHistoryEventUpdateEventType {
    public AuditHistoryEventUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditHistoryEventUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getUpdatedNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.UPDATED_NODE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public NodeId getUpdatedNode() {
        Optional<NodeId> propertyValue = getProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE);
        return propertyValue.orElse(null);
    }

    public void setUpdatedNode(NodeId value) {
        setProperty(AuditHistoryEventUpdateEventType.UPDATED_NODE, value);
    }

    public PropertyNode getPerformInsertReplaceNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public PerformUpdateType getPerformInsertReplace() {
        Optional<PerformUpdateType> propertyValue = getProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE);
        return propertyValue.orElse(null);
    }

    public void setPerformInsertReplace(PerformUpdateType value) {
        setProperty(AuditHistoryEventUpdateEventType.PERFORM_INSERT_REPLACE, value);
    }

    public PropertyNode getFilterNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.FILTER);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public EventFilter getFilter() {
        Optional<EventFilter> propertyValue = getProperty(AuditHistoryEventUpdateEventType.FILTER);
        return propertyValue.orElse(null);
    }

    public void setFilter(EventFilter value) {
        setProperty(AuditHistoryEventUpdateEventType.FILTER, value);
    }

    public PropertyNode getNewValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.NEW_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public HistoryEventFieldList[] getNewValues() {
        Optional<HistoryEventFieldList[]> propertyValue = getProperty(AuditHistoryEventUpdateEventType.NEW_VALUES);
        return propertyValue.orElse(null);
    }

    public void setNewValues(HistoryEventFieldList[] value) {
        setProperty(AuditHistoryEventUpdateEventType.NEW_VALUES, value);
    }

    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventUpdateEventType.OLD_VALUES);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public HistoryEventFieldList[] getOldValues() {
        Optional<HistoryEventFieldList[]> propertyValue = getProperty(AuditHistoryEventUpdateEventType.OLD_VALUES);
        return propertyValue.orElse(null);
    }

    public void setOldValues(HistoryEventFieldList[] value) {
        setProperty(AuditHistoryEventUpdateEventType.OLD_VALUES, value);
    }
}
