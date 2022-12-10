/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.servicesets;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public interface MonitoredItemServiceSet {

    CompletableFuture<CreateMonitoredItemsResponse> onCreateMonitoredItems(
        ServiceRequestContext context,
        CreateMonitoredItemsRequest request
    );

    CompletableFuture<ModifyMonitoredItemsResponse> onModifyMonitoredItems(
        ServiceRequestContext context,
        ModifyMonitoredItemsRequest request
    );

    CompletableFuture<DeleteMonitoredItemsResponse> onDeleteMonitoredItems(
        ServiceRequestContext context,
        DeleteMonitoredItemsRequest request
    );

    CompletableFuture<SetMonitoringModeResponse> onSetMonitoringMode(
        ServiceRequestContext context,
        SetMonitoringModeRequest request
    );

    CompletableFuture<SetTriggeringResponse> onSetTriggering(
        ServiceRequestContext context,
        SetTriggeringRequest request
    );

}
