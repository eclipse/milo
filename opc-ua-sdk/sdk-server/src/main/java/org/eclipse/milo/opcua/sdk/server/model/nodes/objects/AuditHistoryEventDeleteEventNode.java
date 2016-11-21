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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditHistoryEventDeleteEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditHistoryEventDeleteEventType")
public class AuditHistoryEventDeleteEventNode extends AuditHistoryDeleteEventNode implements AuditHistoryEventDeleteEventType {

    public AuditHistoryEventDeleteEventNode(
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
    public ByteString[] getEventIds() {
        Optional<ByteString[]> property = getProperty(AuditHistoryEventDeleteEventType.EVENT_IDS);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEventIdsNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventDeleteEventType.EVENT_IDS.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEventIds(ByteString[] value) {
        setProperty(AuditHistoryEventDeleteEventType.EVENT_IDS, value);
    }

    @Override
    public HistoryEventFieldList getOldValues() {
        Optional<HistoryEventFieldList> property = getProperty(AuditHistoryEventDeleteEventType.OLD_VALUES);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOldValuesNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditHistoryEventDeleteEventType.OLD_VALUES.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOldValues(HistoryEventFieldList value) {
        setProperty(AuditHistoryEventDeleteEventType.OLD_VALUES, value);
    }

}
