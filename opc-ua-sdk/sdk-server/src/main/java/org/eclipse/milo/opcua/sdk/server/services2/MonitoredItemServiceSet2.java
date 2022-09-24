package org.eclipse.milo.opcua.sdk.server.services2;

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

public interface MonitoredItemServiceSet2 {

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
