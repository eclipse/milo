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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public interface AuditHistoryEventUpdateEventType extends AuditHistoryUpdateEventType {

    Property<NodeId> UPDATED_NODE = new BasicProperty<>(
        QualifiedName.parse("0:UpdatedNode"),
        NodeId.parse("ns=0;i=17"),
        -1,
        NodeId.class
    );

    Property<PerformUpdateType> PERFORM_INSERT_REPLACE = new BasicProperty<>(
        QualifiedName.parse("0:PerformInsertReplace"),
        NodeId.parse("ns=0;i=11293"),
        -1,
        PerformUpdateType.class
    );

    Property<EventFilter> FILTER = new BasicProperty<>(
        QualifiedName.parse("0:Filter"),
        NodeId.parse("ns=0;i=725"),
        -1,
        EventFilter.class
    );

    Property<HistoryEventFieldList[]> NEW_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:NewValues"),
        NodeId.parse("ns=0;i=920"),
        1,
        HistoryEventFieldList[].class
    );

    Property<HistoryEventFieldList[]> OLD_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:OldValues"),
        NodeId.parse("ns=0;i=920"),
        1,
        HistoryEventFieldList[].class
    );

    NodeId getUpdatedNode();

    PropertyType getUpdatedNodeNode();

    void setUpdatedNode(NodeId value);

    PerformUpdateType getPerformInsertReplace();

    PropertyType getPerformInsertReplaceNode();

    void setPerformInsertReplace(PerformUpdateType value);

    EventFilter getFilter();

    PropertyType getFilterNode();

    void setFilter(EventFilter value);

    HistoryEventFieldList[] getNewValues();

    PropertyType getNewValuesNode();

    void setNewValues(HistoryEventFieldList[] value);

    HistoryEventFieldList[] getOldValues();

    PropertyType getOldValuesNode();

    void setOldValues(HistoryEventFieldList[] value);
}
