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
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.jooq.lambda.tuple.Tuple2;

public class GetMonitoredItems extends AbstractUaMethod {

    public GetMonitoredItems(UaClient client, NodeId objectId, NodeId methodId) {
        super(client, objectId, methodId);
    }

    /**
     * GetMonitoredItems is used to get information about monitored items of a subscription.
     *
     * @param subscriptionId identifier of the subscription.
     * @return a {@link Tuple2} containing the output arguments.
     * <p>
     * serverHandles (UInt32[]) - array of serverHandles for all MonitoredItems of the subscription identified by
     * subscriptionId.
     * <p>
     * clientHandles (UInt32[]) - array of clientHandles for all MonitoredItems of the subscription identified by
     * subscriptionId.
     */
    public CompletableFuture<Tuple2<UInteger[], UInteger[]>> invoke(UInteger subscriptionId) {
        Variant[] inputArguments = new Variant[]{
            new Variant(subscriptionId)
        };

        return invoke(inputArguments).thenCompose(outputArguments -> {
            try {
                UInteger[] v0 = (UInteger[]) outputArguments[0].getValue();
                UInteger[] v1 = (UInteger[]) outputArguments[1].getValue();

                return CompletableFuture.completedFuture(new Tuple2<>(v0, v1));
            } catch (Throwable t) {
                CompletableFuture<Tuple2<UInteger[], UInteger[]>> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(t));
                return f;
            }
        });
    }

}
