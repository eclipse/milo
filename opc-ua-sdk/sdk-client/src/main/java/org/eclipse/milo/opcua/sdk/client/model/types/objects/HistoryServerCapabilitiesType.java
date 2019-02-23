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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface HistoryServerCapabilitiesType extends BaseObjectType {
    QualifiedProperty<Boolean> ACCESS_HISTORY_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AccessHistoryDataCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> ACCESS_HISTORY_EVENTS_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AccessHistoryEventsCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UInteger> MAX_RETURN_DATA_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReturnDataValues",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_RETURN_EVENT_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxReturnEventValues",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<Boolean> INSERT_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertDataCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> REPLACE_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReplaceDataCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> UPDATE_DATA_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateDataCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_RAW_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteRawCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_AT_TIME_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteAtTimeCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> INSERT_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertEventCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> REPLACE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ReplaceEventCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> UPDATE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateEventCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> DELETE_EVENT_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "DeleteEventCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Boolean> INSERT_ANNOTATION_CAPABILITY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InsertAnnotationCapability",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    CompletableFuture<? extends PropertyType> getAccessHistoryDataCapabilityNode();

    CompletableFuture<Boolean> getAccessHistoryDataCapability();

    CompletableFuture<StatusCode> setAccessHistoryDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getAccessHistoryEventsCapabilityNode();

    CompletableFuture<Boolean> getAccessHistoryEventsCapability();

    CompletableFuture<StatusCode> setAccessHistoryEventsCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getMaxReturnDataValuesNode();

    CompletableFuture<UInteger> getMaxReturnDataValues();

    CompletableFuture<StatusCode> setMaxReturnDataValues(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxReturnEventValuesNode();

    CompletableFuture<UInteger> getMaxReturnEventValues();

    CompletableFuture<StatusCode> setMaxReturnEventValues(UInteger value);

    CompletableFuture<? extends PropertyType> getInsertDataCapabilityNode();

    CompletableFuture<Boolean> getInsertDataCapability();

    CompletableFuture<StatusCode> setInsertDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getReplaceDataCapabilityNode();

    CompletableFuture<Boolean> getReplaceDataCapability();

    CompletableFuture<StatusCode> setReplaceDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getUpdateDataCapabilityNode();

    CompletableFuture<Boolean> getUpdateDataCapability();

    CompletableFuture<StatusCode> setUpdateDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getDeleteRawCapabilityNode();

    CompletableFuture<Boolean> getDeleteRawCapability();

    CompletableFuture<StatusCode> setDeleteRawCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getDeleteAtTimeCapabilityNode();

    CompletableFuture<Boolean> getDeleteAtTimeCapability();

    CompletableFuture<StatusCode> setDeleteAtTimeCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getInsertEventCapabilityNode();

    CompletableFuture<Boolean> getInsertEventCapability();

    CompletableFuture<StatusCode> setInsertEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getReplaceEventCapabilityNode();

    CompletableFuture<Boolean> getReplaceEventCapability();

    CompletableFuture<StatusCode> setReplaceEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getUpdateEventCapabilityNode();

    CompletableFuture<Boolean> getUpdateEventCapability();

    CompletableFuture<StatusCode> setUpdateEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getDeleteEventCapabilityNode();

    CompletableFuture<Boolean> getDeleteEventCapability();

    CompletableFuture<StatusCode> setDeleteEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> getInsertAnnotationCapabilityNode();

    CompletableFuture<Boolean> getInsertAnnotationCapability();

    CompletableFuture<StatusCode> setInsertAnnotationCapability(Boolean value);

    CompletableFuture<? extends FolderType> getAggregateFunctionsNode();
}
