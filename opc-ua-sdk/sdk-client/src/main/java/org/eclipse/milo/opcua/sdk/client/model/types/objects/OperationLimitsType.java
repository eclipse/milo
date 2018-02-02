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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface OperationLimitsType extends FolderType {
    QualifiedProperty<UInteger> MAX_NODES_PER_READ = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRead",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadData",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadEvents",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_WRITE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerWrite",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateData",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateEvents",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_METHOD_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerMethodCall",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_BROWSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerBrowse",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_REGISTER_NODES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRegisterNodes",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerTranslateBrowsePathsToNodeIds",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_NODE_MANAGEMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerNodeManagement",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_MONITORED_ITEMS_PER_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxMonitoredItemsPerCall",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    CompletableFuture<? extends PropertyType> getMaxNodesPerReadNode();

    CompletableFuture<UInteger> getMaxNodesPerRead();

    CompletableFuture<StatusCode> setMaxNodesPerRead(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryReadDataNode();

    CompletableFuture<UInteger> getMaxNodesPerHistoryReadData();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryReadData(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryReadEventsNode();

    CompletableFuture<UInteger> getMaxNodesPerHistoryReadEvents();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryReadEvents(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerWriteNode();

    CompletableFuture<UInteger> getMaxNodesPerWrite();

    CompletableFuture<StatusCode> setMaxNodesPerWrite(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryUpdateDataNode();

    CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateData();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateData(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerHistoryUpdateEventsNode();

    CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateEvents();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateEvents(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerMethodCallNode();

    CompletableFuture<UInteger> getMaxNodesPerMethodCall();

    CompletableFuture<StatusCode> setMaxNodesPerMethodCall(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerBrowseNode();

    CompletableFuture<UInteger> getMaxNodesPerBrowse();

    CompletableFuture<StatusCode> setMaxNodesPerBrowse(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerRegisterNodesNode();

    CompletableFuture<UInteger> getMaxNodesPerRegisterNodes();

    CompletableFuture<StatusCode> setMaxNodesPerRegisterNodes(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerTranslateBrowsePathsToNodeIdsNode();

    CompletableFuture<UInteger> getMaxNodesPerTranslateBrowsePathsToNodeIds();

    CompletableFuture<StatusCode> setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxNodesPerNodeManagementNode();

    CompletableFuture<UInteger> getMaxNodesPerNodeManagement();

    CompletableFuture<StatusCode> setMaxNodesPerNodeManagement(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxMonitoredItemsPerCallNode();

    CompletableFuture<UInteger> getMaxMonitoredItemsPerCall();

    CompletableFuture<StatusCode> setMaxMonitoredItemsPerCall(UInteger value);
}
