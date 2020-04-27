/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters.MonitoringParametersBuilder;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ModifyMonitoredItemsBatch {

    private final ConcurrentMap<ManagedDataItem, MonitoringParametersBuilder<?, ?>> builders =
        new ConcurrentHashMap<>();

    private final ManagedSubscription subscription;

    public ModifyMonitoredItemsBatch(ManagedSubscription subscription) {
        this.subscription = subscription;
    }

    public void add(ManagedDataItem dataItem, Consumer<MonitoringParametersBuilder<?, ?>> builderConsumer) {
        MonitoringParametersBuilder<?, ?> builder =
            builders.computeIfAbsent(dataItem, this::parametersBuilderForItem);

        builderConsumer.accept(builder);
    }

    private MonitoringParametersBuilder<?, ?> parametersBuilderForItem(ManagedDataItem item) {
        return MonitoringParameters.builder()
            .clientHandle(item.getMonitoredItem().getClientHandle())
            .samplingInterval(item.getMonitoredItem().getRevisedSamplingInterval())
            .filter(item.getMonitoredItem().getMonitoringFilter())
            .queueSize(item.getMonitoredItem().getRevisedQueueSize())
            .discardOldest(true); // TODO
    }

    public void execute() throws UaException {
        try {
            executeAsync().get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<Unit> executeAsync() {
        List<MonitoredItemModifyRequest> itemsToModify = new ArrayList<>();

        builders.forEach((item, builder) -> {
            MonitoredItemModifyRequest modifyRequest = new MonitoredItemModifyRequest(
                item.getMonitoredItem().getMonitoredItemId(),
                builder.build()
            );

            itemsToModify.add(modifyRequest);
        });

        CompletableFuture<List<StatusCode>> future = subscription.getSubscription().modifyMonitoredItems(
            subscription.getDefaultTimestamps(),
            itemsToModify
        );

        return future.thenApply(statusCodes -> Unit.VALUE);
    }

}
