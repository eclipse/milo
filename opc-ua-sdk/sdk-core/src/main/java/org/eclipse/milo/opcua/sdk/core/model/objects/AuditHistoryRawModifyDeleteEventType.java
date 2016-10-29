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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface AuditHistoryRawModifyDeleteEventType extends AuditHistoryDeleteEventType {

    Property<Boolean> IS_DELETE_MODIFIED = new BasicProperty<>(
        QualifiedName.parse("0:IsDeleteModified"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<DateTime> START_TIME = new BasicProperty<>(
        QualifiedName.parse("0:StartTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DateTime> END_TIME = new BasicProperty<>(
        QualifiedName.parse("0:EndTime"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DataValue[]> OLD_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:OldValues"),
        NodeId.parse("ns=0;i=23"),
        1,
        DataValue[].class
    );

    Boolean getIsDeleteModified();

    PropertyType getIsDeleteModifiedNode();

    void setIsDeleteModified(Boolean value);

    DateTime getStartTime();

    PropertyType getStartTimeNode();

    void setStartTime(DateTime value);

    DateTime getEndTime();

    PropertyType getEndTimeNode();

    void setEndTime(DateTime value);

    DataValue[] getOldValues();

    PropertyType getOldValuesNode();

    void setOldValues(DataValue[] value);
}
