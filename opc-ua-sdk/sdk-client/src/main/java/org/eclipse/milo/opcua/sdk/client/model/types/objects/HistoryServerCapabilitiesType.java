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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface HistoryServerCapabilitiesType extends BaseObjectType {

    Property<Boolean> ACCESS_HISTORY_DATA_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:AccessHistoryDataCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> ACCESS_HISTORY_EVENTS_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:AccessHistoryEventsCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<UInteger> MAX_RETURN_DATA_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:MaxReturnDataValues"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_RETURN_EVENT_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:MaxReturnEventValues"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<Boolean> INSERT_DATA_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:InsertDataCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> REPLACE_DATA_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:ReplaceDataCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> UPDATE_DATA_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:UpdateDataCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> DELETE_RAW_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:DeleteRawCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> DELETE_AT_TIME_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:DeleteAtTimeCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> INSERT_EVENT_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:InsertEventCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> REPLACE_EVENT_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:ReplaceEventCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> UPDATE_EVENT_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:UpdateEventCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> DELETE_EVENT_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:DeleteEventCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<Boolean> INSERT_ANNOTATION_CAPABILITY = new BasicProperty<>(
        QualifiedName.parse("0:InsertAnnotationCapability"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );


    CompletableFuture<? extends PropertyType> accessHistoryDataCapability();

    CompletableFuture<Boolean> getAccessHistoryDataCapability();

    CompletableFuture<StatusCode> setAccessHistoryDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> accessHistoryEventsCapability();

    CompletableFuture<Boolean> getAccessHistoryEventsCapability();

    CompletableFuture<StatusCode> setAccessHistoryEventsCapability(Boolean value);

    CompletableFuture<? extends PropertyType> maxReturnDataValues();

    CompletableFuture<UInteger> getMaxReturnDataValues();

    CompletableFuture<StatusCode> setMaxReturnDataValues(UInteger value);

    CompletableFuture<? extends PropertyType> maxReturnEventValues();

    CompletableFuture<UInteger> getMaxReturnEventValues();

    CompletableFuture<StatusCode> setMaxReturnEventValues(UInteger value);

    CompletableFuture<? extends PropertyType> insertDataCapability();

    CompletableFuture<Boolean> getInsertDataCapability();

    CompletableFuture<StatusCode> setInsertDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> replaceDataCapability();

    CompletableFuture<Boolean> getReplaceDataCapability();

    CompletableFuture<StatusCode> setReplaceDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> updateDataCapability();

    CompletableFuture<Boolean> getUpdateDataCapability();

    CompletableFuture<StatusCode> setUpdateDataCapability(Boolean value);

    CompletableFuture<? extends PropertyType> deleteRawCapability();

    CompletableFuture<Boolean> getDeleteRawCapability();

    CompletableFuture<StatusCode> setDeleteRawCapability(Boolean value);

    CompletableFuture<? extends PropertyType> deleteAtTimeCapability();

    CompletableFuture<Boolean> getDeleteAtTimeCapability();

    CompletableFuture<StatusCode> setDeleteAtTimeCapability(Boolean value);

    CompletableFuture<? extends PropertyType> insertEventCapability();

    CompletableFuture<Boolean> getInsertEventCapability();

    CompletableFuture<StatusCode> setInsertEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> replaceEventCapability();

    CompletableFuture<Boolean> getReplaceEventCapability();

    CompletableFuture<StatusCode> setReplaceEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> updateEventCapability();

    CompletableFuture<Boolean> getUpdateEventCapability();

    CompletableFuture<StatusCode> setUpdateEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> deleteEventCapability();

    CompletableFuture<Boolean> getDeleteEventCapability();

    CompletableFuture<StatusCode> setDeleteEventCapability(Boolean value);

    CompletableFuture<? extends PropertyType> insertAnnotationCapability();

    CompletableFuture<Boolean> getInsertAnnotationCapability();

    CompletableFuture<StatusCode> setInsertAnnotationCapability(Boolean value);

    CompletableFuture<? extends FolderType> aggregateFunctions();


}