package org.eclipse.milo.opcua.sdk.server.services2;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;

public interface MethodServiceSet2 {

    CompletableFuture<CallResponse> onCall(ServiceRequestContext context, CallRequest request);

}
