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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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

    PropertyType getAccessHistoryDataCapabilityNode();

    Boolean getAccessHistoryDataCapability();

    void setAccessHistoryDataCapability(Boolean value);

    PropertyType getAccessHistoryEventsCapabilityNode();

    Boolean getAccessHistoryEventsCapability();

    void setAccessHistoryEventsCapability(Boolean value);

    PropertyType getMaxReturnDataValuesNode();

    UInteger getMaxReturnDataValues();

    void setMaxReturnDataValues(UInteger value);

    PropertyType getMaxReturnEventValuesNode();

    UInteger getMaxReturnEventValues();

    void setMaxReturnEventValues(UInteger value);

    PropertyType getInsertDataCapabilityNode();

    Boolean getInsertDataCapability();

    void setInsertDataCapability(Boolean value);

    PropertyType getReplaceDataCapabilityNode();

    Boolean getReplaceDataCapability();

    void setReplaceDataCapability(Boolean value);

    PropertyType getUpdateDataCapabilityNode();

    Boolean getUpdateDataCapability();

    void setUpdateDataCapability(Boolean value);

    PropertyType getDeleteRawCapabilityNode();

    Boolean getDeleteRawCapability();

    void setDeleteRawCapability(Boolean value);

    PropertyType getDeleteAtTimeCapabilityNode();

    Boolean getDeleteAtTimeCapability();

    void setDeleteAtTimeCapability(Boolean value);

    PropertyType getInsertEventCapabilityNode();

    Boolean getInsertEventCapability();

    void setInsertEventCapability(Boolean value);

    PropertyType getReplaceEventCapabilityNode();

    Boolean getReplaceEventCapability();

    void setReplaceEventCapability(Boolean value);

    PropertyType getUpdateEventCapabilityNode();

    Boolean getUpdateEventCapability();

    void setUpdateEventCapability(Boolean value);

    PropertyType getDeleteEventCapabilityNode();

    Boolean getDeleteEventCapability();

    void setDeleteEventCapability(Boolean value);

    PropertyType getInsertAnnotationCapabilityNode();

    Boolean getInsertAnnotationCapability();

    void setInsertAnnotationCapability(Boolean value);

    FolderType getAggregateFunctionsNode();
}
