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

package org.eclipse.milo.opcua.sdk.client.methods;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;

public abstract class AbstractUaMethod {

    private final UaClient client;
    private final NodeId objectId;
    private final NodeId methodId;

    public AbstractUaMethod(UaClient client, NodeId objectId, NodeId methodId) {
        this.client = client;
        this.objectId = objectId;
        this.methodId = methodId;
    }

    public CompletableFuture<Variant[]> invoke(Variant[] inputArguments) {
        CallMethodRequest request = new CallMethodRequest(
            objectId, methodId, inputArguments);

        return client.call(request).thenCompose(result -> {
            StatusCode statusCode = result.getStatusCode();

            if (statusCode.isGood()) {
                Variant[] outputArguments = result.getOutputArguments();

                return CompletableFuture.completedFuture(outputArguments);
            } else {
                UaMethodException ex = new UaMethodException(
                    statusCode,
                    result.getInputArgumentResults(),
                    result.getInputArgumentDiagnosticInfos()
                );

                CompletableFuture<Variant[]> f = new CompletableFuture<>();
                f.completeExceptionally(ex);
                return f;
            }
        });
    }

}
