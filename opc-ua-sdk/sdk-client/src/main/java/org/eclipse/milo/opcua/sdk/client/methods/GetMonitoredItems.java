/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.methods;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.api.UaClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class GetMonitoredItems extends AbstractUaMethod {

    public GetMonitoredItems(UaClient client, NodeId objectId, NodeId methodId) {
        super(client, objectId, methodId);
    }

    /**
     * GetMonitoredItems is used to get information about monitored items of a subscription.
     *
     * @param subscriptionId identifier of the subscription.
     * @return a 2-dimensional array containing the output arguments.
     * <p>
     * serverHandles (UInt32[]) - array of serverHandles for all MonitoredItems of the subscription identified by
     * subscriptionId.
     * <p>
     * clientHandles (UInt32[]) - array of clientHandles for all MonitoredItems of the subscription identified by
     * subscriptionId.
     */
    public CompletableFuture<UInteger[][]> invoke(UInteger subscriptionId) {
        Variant[] inputArguments = new Variant[]{
            new Variant(subscriptionId)
        };

        return invoke(inputArguments).thenCompose(outputArguments -> {
            try {
                UInteger[] serverHandles = (UInteger[]) outputArguments[0].getValue();
                UInteger[] clientHandles = (UInteger[]) outputArguments[1].getValue();

                return CompletableFuture.completedFuture(new UInteger[][]{serverHandles, clientHandles});
            } catch (Throwable t) {
                CompletableFuture<UInteger[][]> f = new CompletableFuture<>();
                f.completeExceptionally(new UaException(t));
                return f;
            }
        });
    }

}
