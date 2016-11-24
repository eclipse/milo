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


public interface OperationLimitsType extends BaseObjectType {

    Property<UInteger> MAX_NODES_PER_READ = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerRead"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_HISTORY_READ_DATA = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerHistoryReadData"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_HISTORY_READ_EVENTS = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerHistoryReadEvents"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_WRITE = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerWrite"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_HISTORY_UPDATE_DATA = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerHistoryUpdateData"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_HISTORY_UPDATE_EVENTS = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerHistoryUpdateEvents"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_METHOD_CALL = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerMethodCall"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_BROWSE = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerBrowse"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_REGISTER_NODES = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerRegisterNodes"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerTranslateBrowsePathsToNodeIds"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_NODES_PER_NODE_MANAGEMENT = new BasicProperty<>(
        QualifiedName.parse("0:MaxNodesPerNodeManagement"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_MONITORED_ITEMS_PER_CALL = new BasicProperty<>(
        QualifiedName.parse("0:MaxMonitoredItemsPerCall"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );


    CompletableFuture<? extends PropertyType> maxNodesPerRead();

    CompletableFuture<UInteger> getMaxNodesPerRead();

    CompletableFuture<StatusCode> setMaxNodesPerRead(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerHistoryReadData();

    CompletableFuture<UInteger> getMaxNodesPerHistoryReadData();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryReadData(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerHistoryReadEvents();

    CompletableFuture<UInteger> getMaxNodesPerHistoryReadEvents();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryReadEvents(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerWrite();

    CompletableFuture<UInteger> getMaxNodesPerWrite();

    CompletableFuture<StatusCode> setMaxNodesPerWrite(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerHistoryUpdateData();

    CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateData();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateData(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerHistoryUpdateEvents();

    CompletableFuture<UInteger> getMaxNodesPerHistoryUpdateEvents();

    CompletableFuture<StatusCode> setMaxNodesPerHistoryUpdateEvents(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerMethodCall();

    CompletableFuture<UInteger> getMaxNodesPerMethodCall();

    CompletableFuture<StatusCode> setMaxNodesPerMethodCall(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerBrowse();

    CompletableFuture<UInteger> getMaxNodesPerBrowse();

    CompletableFuture<StatusCode> setMaxNodesPerBrowse(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerRegisterNodes();

    CompletableFuture<UInteger> getMaxNodesPerRegisterNodes();

    CompletableFuture<StatusCode> setMaxNodesPerRegisterNodes(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerTranslateBrowsePathsToNodeIds();

    CompletableFuture<UInteger> getMaxNodesPerTranslateBrowsePathsToNodeIds();

    CompletableFuture<StatusCode> setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value);

    CompletableFuture<? extends PropertyType> maxNodesPerNodeManagement();

    CompletableFuture<UInteger> getMaxNodesPerNodeManagement();

    CompletableFuture<StatusCode> setMaxNodesPerNodeManagement(UInteger value);

    CompletableFuture<? extends PropertyType> maxMonitoredItemsPerCall();

    CompletableFuture<UInteger> getMaxMonitoredItemsPerCall();

    CompletableFuture<StatusCode> setMaxMonitoredItemsPerCall(UInteger value);


}