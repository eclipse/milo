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
     * Invoke the given {@link CallMethodRequest} and complete {@code future} when finished.
     * <p>
     * Under no circumstances should the future be completed exceptionally.
     *
     * @param accessContext the {@link AccessContext}.
     * @param request       the {@link CallMethodRequest}.
     * @param future        the {@link CompletableFuture} to complete.
     */
    void invoke(AccessContext accessContext, CallMethodRequest request, CompletableFuture<CallMethodResult> future);

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
                new Variant[0]);

            future.complete(nodeIdUnknown);
        }
    }

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
                new Variant[0]);

            future.complete(nodeIdUnknown);
        }
    }

}
