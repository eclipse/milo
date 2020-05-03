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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription;
import org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

public class BatchSetMonitoringMode {

    private final Map<OpcUaMonitoredItem, MonitoringMode> monitoringModesByItem =
        Collections.synchronizedMap(new LinkedHashMap<>());

    private final ListMultimap<OpcUaMonitoredItem, CompletableFuture<SetMonitoringModeResult>> futuresByItem =
        Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    private final List<CompletableFuture<SetMonitoringModeResult>> resultFutures =
        Collections.synchronizedList(new ArrayList<>());

    private final AtomicInteger serviceInvocationCount = new AtomicInteger(0);

    private final OpcUaClient client;
    private final OpcUaSubscription subscription;

    public BatchSetMonitoringMode(ManagedSubscription subscription) {
        this(subscription.getClient(), subscription.getSubscription());
    }

    public BatchSetMonitoringMode(OpcUaClient client, OpcUaSubscription subscription) {
        this.client = client;
        this.subscription = subscription;
    }

    public CompletableFuture<SetMonitoringModeResult> add(
        OpcUaMonitoredItem monitoredItem,
        MonitoringMode monitoringMode
    ) {

        monitoringModesByItem.put(monitoredItem, monitoringMode);

        CompletableFuture<SetMonitoringModeResult> future = new CompletableFuture<>();
        futuresByItem.put(monitoredItem, future);
        resultFutures.add(future);
        return future;
    }

    public List<SetMonitoringModeResult> execute() throws UaException {
        try {
            return executeAsync().get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<List<SetMonitoringModeResult>> executeAsync() {
        List<Map.Entry<OpcUaMonitoredItem, MonitoringMode>> entries =
            new ArrayList<>(monitoringModesByItem.entrySet());

        CompletableFuture<List<SetMonitoringModeResult>> resultsFuture = GroupMapCollate.groupMapCollate(
            entries,
            Map.Entry::getValue,
            monitoringMode -> this::setMonitoringModeAsync
        );

        return resultsFuture.thenCompose(results -> {
            List<OpcUaMonitoredItem> items =
                new ArrayList<>(monitoringModesByItem.keySet());

            assert items.size() == results.size();

            for (int i = 0; i < items.size(); i++) {
                OpcUaMonitoredItem item = items.get(i);
                SetMonitoringModeResult result = results.get(i);

                List<CompletableFuture<SetMonitoringModeResult>> futures;
                synchronized (futuresByItem) {
                    futures = new ArrayList<>(futuresByItem.get(item));
                }
                futures.forEach(f -> f.complete(result));
            }

            return FutureUtils.sequence(resultFutures);
        });
    }

    private CompletableFuture<List<SetMonitoringModeResult>> setMonitoringModeAsync(
        List<Map.Entry<OpcUaMonitoredItem, MonitoringMode>> itemsAndModes
    ) {

        serviceInvocationCount.incrementAndGet();

        // TODO

        SetMonitoringModeResult result = new SetMonitoringModeResult(
            new StatusCode(StatusCodes.Bad_NotImplemented)
        );

        return CompletableFuture.completedFuture(Collections.nCopies(itemsAndModes.size(), result));
    }

    /**
     * Holds the operation- and service-level results of an operation that is part of a
     * {@link BatchSetMonitoringMode} execution.
     */
    public static class SetMonitoringModeResult implements BatchItemResult<StatusCode> {

        private final StatusCode serviceResult;
        private final StatusCode operationResult;

        SetMonitoringModeResult(StatusCode serviceResult) {
            this(serviceResult, null);
        }

        SetMonitoringModeResult(StatusCode serviceResult, @Nullable StatusCode operationResult) {
            this.serviceResult = serviceResult;
            this.operationResult = operationResult;
        }

        @Override
        public StatusCode serviceResult() {
            return serviceResult;
        }

        @Override
        public Optional<StatusCode> operationResult() {
            return Optional.ofNullable(operationResult);
        }

        public boolean isServiceResultGood() {
            return serviceResult.isGood();
        }

        public boolean isOperationResultGood() {
            return operationResult().map(StatusCode::isGood).orElse(false);
        }

    }

}
