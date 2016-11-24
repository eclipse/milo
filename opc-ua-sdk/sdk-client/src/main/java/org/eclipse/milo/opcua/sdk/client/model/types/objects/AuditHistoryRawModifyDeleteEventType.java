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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;


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


    CompletableFuture<? extends PropertyType> isDeleteModified();

    CompletableFuture<Boolean> getIsDeleteModified();

    CompletableFuture<StatusCode> setIsDeleteModified(Boolean value);

    CompletableFuture<? extends PropertyType> startTime();

    CompletableFuture<DateTime> getStartTime();

    CompletableFuture<StatusCode> setStartTime(DateTime value);

    CompletableFuture<? extends PropertyType> endTime();

    CompletableFuture<DateTime> getEndTime();

    CompletableFuture<StatusCode> setEndTime(DateTime value);

    CompletableFuture<? extends PropertyType> oldValues();

    CompletableFuture<DataValue[]> getOldValues();

    CompletableFuture<StatusCode> setOldValues(DataValue[] value);


}