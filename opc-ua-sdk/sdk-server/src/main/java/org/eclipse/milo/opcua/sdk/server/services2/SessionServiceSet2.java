package org.eclipse.milo.opcua.sdk.server.services2;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public interface SessionServiceSet2 {

    CompletableFuture<CreateSessionResponse> onCreateSession(
        ServiceRequestContext context,
        CreateSessionRequest request
    );

    CompletableFuture<ActivateSessionResponse> onActivateSession(
        ServiceRequestContext context,
        ActivateSessionRequest request
    );

    CompletableFuture<CloseSessionResponse> onCloseSession(
        ServiceRequestContext context,
        CloseSessionRequest request
    );

    CompletableFuture<CancelResponse> onCancel(ServiceRequestContext context, CancelRequest request);

}
