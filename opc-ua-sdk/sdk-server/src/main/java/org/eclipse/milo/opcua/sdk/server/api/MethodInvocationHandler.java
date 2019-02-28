/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;

public interface MethodInvocationHandler {

    /**
     * A shareable instance of {@link NotImplementedHandler}.
     */
    NotImplementedHandler NOT_IMPLEMENTED = new NotImplementedHandler();

    /**
     * Invoke the given {@link CallMethodRequest} and complete {@code future} when finished.
     * <p>
     * Under no circumstances should the future be completed exceptionally.
     *
     * @param accessContext the {@link AccessContext}.
     * @param request       the {@link CallMethodRequest}.
     * @param future        the {@link CompletableFuture} to complete.
     */
    void invoke(AccessContext accessContext, CallMethodRequest request, CompletableFuture<CallMethodResult> future);


    /**
     * A {@link MethodInvocationHandler} that always completes with {@link StatusCodes#Bad_NodeIdUnknown}.
     */
    final class NodeIdUnknownHandler implements MethodInvocationHandler {
        @Override
        public void invoke(
            AccessContext accessContext,
            CallMethodRequest request,
            CompletableFuture<CallMethodResult> future) {

            CallMethodResult nodeIdUnknown = new CallMethodResult(
                new StatusCode(StatusCodes.Bad_NodeIdUnknown),
                new StatusCode[0],
                new DiagnosticInfo[0],
                new Variant[0]
            );

            future.complete(nodeIdUnknown);
        }
    }

    /**
     * A {@link MethodInvocationHandler} that always completes with {@link StatusCodes#Bad_NotImplemented}.
     */
    final class NotImplementedHandler implements MethodInvocationHandler {
        @Override
        public void invoke(
            AccessContext accessContext,
            CallMethodRequest request,
            CompletableFuture<CallMethodResult> future) {

            CallMethodResult nodeIdUnknown = new CallMethodResult(
                new StatusCode(StatusCodes.Bad_NotImplemented),
                new StatusCode[0],
                new DiagnosticInfo[0],
                new Variant[0]
            );

            future.complete(nodeIdUnknown);
        }
    }

}
