/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;

public interface MethodServices {

    /**
     * This service is used to call (invoke) a list of methods. Each method call is invoked within the context of an
     * existing session. If the session is terminated, the results of the method’s execution cannot be returned to the
     * client and are discarded. This is independent of the task actually performed at the server.
     *
     * @param methodsToCall a list of methods to call.
     * @return a {@link CompletableFuture} containing the {@link CallResponse}.
     */
    CompletableFuture<CallResponse> callAsync(List<CallMethodRequest> methodsToCall);

    /**
     * Call (invoke) a method.
     *
     * @param request the {@link CallMethodRequest} describing the method to invoke.
     * @return a {@link CompletableFuture} containing the {@link CallMethodResult}.
     * @see #callAsync(List)
     */
    default CompletableFuture<CallMethodResult> callAsync(CallMethodRequest request) {
        return callAsync(List.of(request))
            .thenApply(response -> List.of(response.getResults()).get(0));
    }

}
