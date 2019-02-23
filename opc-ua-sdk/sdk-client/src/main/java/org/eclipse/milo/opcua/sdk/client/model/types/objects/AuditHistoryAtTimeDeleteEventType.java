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

public interface AuditHistoryAtTimeDeleteEventType extends AuditHistoryDeleteEventType {
    QualifiedProperty<DateTime[]> REQ_TIMES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReqTimes",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.OneDimension,
        DateTime[].class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    CompletableFuture<? extends PropertyType> getReqTimesNode();

    CompletableFuture<DateTime[]> getReqTimes();

    CompletableFuture<StatusCode> setReqTimes(DateTime[] value);

    CompletableFuture<? extends PropertyType> getOldValuesNode();

    CompletableFuture<DataValue[]> getOldValues();

    CompletableFuture<StatusCode> setOldValues(DataValue[] value);
}
