/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryEventFieldList;

public interface AuditHistoryEventDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<ByteString[]> EVENT_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EventIds",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.OneDimension,
        ByteString[].class
    );

    QualifiedProperty<HistoryEventFieldList> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        NodeId.parse("ns=0;i=920"),
        ValueRanks.Scalar,
        HistoryEventFieldList.class
    );

    PropertyType getEventIdsNode();

    ByteString[] getEventIds();

    void setEventIds(ByteString[] value);

    PropertyType getOldValuesNode();

    HistoryEventFieldList getOldValues();

    void setOldValues(HistoryEventFieldList value);
}
