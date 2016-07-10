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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public interface AuditHistoryEventDeleteEventType extends AuditHistoryDeleteEventType {

    Property<ByteString[]> EVENT_IDS = new BasicProperty<>(
        QualifiedName.parse("0:EventIds"),
        NodeId.parse("ns=0;i=15"),
        1,
        ByteString[].class
    );

    Property<HistoryEventFieldList> OLD_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:OldValues"),
        NodeId.parse("ns=0;i=920"),
        -1,
        HistoryEventFieldList.class
    );

    ByteString[] getEventIds();

    PropertyType getEventIdsNode();

    void setEventIds(ByteString[] value);

    HistoryEventFieldList getOldValues();

    PropertyType getOldValuesNode();

    void setOldValues(HistoryEventFieldList value);
}
