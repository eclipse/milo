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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;


public interface AuditHistoryValueUpdateEventType extends AuditHistoryUpdateEventType {

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

    Property<DataValue[]> NEW_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:NewValues"),
        NodeId.parse("ns=0;i=23"),
        1,
        DataValue[].class
    );

    Property<DataValue[]> OLD_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:OldValues"),
        NodeId.parse("ns=0;i=23"),
        1,
        DataValue[].class
    );


    CompletableFuture<? extends PropertyType> updatedNode();

    CompletableFuture<NodeId> getUpdatedNode();

    CompletableFuture<StatusCode> setUpdatedNode(NodeId value);

    CompletableFuture<? extends PropertyType> performInsertReplace();

    CompletableFuture<PerformUpdateType> getPerformInsertReplace();

    CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value);

    CompletableFuture<? extends PropertyType> newValues();

    CompletableFuture<DataValue[]> getNewValues();

    CompletableFuture<StatusCode> setNewValues(DataValue[] value);

    CompletableFuture<? extends PropertyType> oldValues();

    CompletableFuture<DataValue[]> getOldValues();

    CompletableFuture<StatusCode> setOldValues(DataValue[] value);


}