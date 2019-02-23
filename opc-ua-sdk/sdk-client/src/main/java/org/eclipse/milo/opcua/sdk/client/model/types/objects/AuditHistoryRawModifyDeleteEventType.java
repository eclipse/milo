/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface AuditHistoryRawModifyDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<Boolean> IS_DELETE_MODIFIED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IsDeleteModified",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> START_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StartTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> END_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    CompletableFuture<? extends PropertyType> getIsDeleteModifiedNode();

    CompletableFuture<Boolean> getIsDeleteModified();

    CompletableFuture<StatusCode> setIsDeleteModified(Boolean value);

    CompletableFuture<? extends PropertyType> getStartTimeNode();

    CompletableFuture<DateTime> getStartTime();

    CompletableFuture<StatusCode> setStartTime(DateTime value);

    CompletableFuture<? extends PropertyType> getEndTimeNode();

    CompletableFuture<DateTime> getEndTime();

    CompletableFuture<StatusCode> setEndTime(DateTime value);

    CompletableFuture<? extends PropertyType> getOldValuesNode();

    CompletableFuture<DataValue[]> getOldValues();

    CompletableFuture<StatusCode> setOldValues(DataValue[] value);
}
