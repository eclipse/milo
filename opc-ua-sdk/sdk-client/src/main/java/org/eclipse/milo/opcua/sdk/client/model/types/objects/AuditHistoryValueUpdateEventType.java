/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public interface AuditHistoryValueUpdateEventType extends AuditHistoryUpdateEventType {
    QualifiedProperty<NodeId> UPDATED_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdatedNode",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<PerformUpdateType> PERFORM_INSERT_REPLACE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PerformInsertReplace",
        NodeId.parse("ns=0;i=11293"),
        ValueRanks.Scalar,
        PerformUpdateType.class
    );

    QualifiedProperty<DataValue[]> NEW_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    CompletableFuture<? extends PropertyType> getUpdatedNodeNode();

    CompletableFuture<NodeId> getUpdatedNode();

    CompletableFuture<StatusCode> setUpdatedNode(NodeId value);

    CompletableFuture<? extends PropertyType> getPerformInsertReplaceNode();

    CompletableFuture<PerformUpdateType> getPerformInsertReplace();

    CompletableFuture<StatusCode> setPerformInsertReplace(PerformUpdateType value);

    CompletableFuture<? extends PropertyType> getNewValuesNode();

    CompletableFuture<DataValue[]> getNewValues();

    CompletableFuture<StatusCode> setNewValues(DataValue[] value);

    CompletableFuture<? extends PropertyType> getOldValuesNode();

    CompletableFuture<DataValue[]> getOldValues();

    CompletableFuture<StatusCode> setOldValues(DataValue[] value);
}
