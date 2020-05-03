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
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription;
import org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class BatchSetMonitoringMode {

    private final Map<OpcUaMonitoredItem, MonitoringMode> monitoringModesByItem =
        Collections.synchronizedMap(new LinkedHashMap<>());

    private final ListMultimap<OpcUaMonitoredItem, CompletableFuture<SetMonitoringModeResult>> futuresByItem =
        Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    private final List<CompletableFuture<SetMonitoringModeResult>> resultFutures =
        Collections.synchronizedList(new ArrayList<>());

    private final AtomicInteger serviceInvocationCount = new AtomicInteger(0);

    // TODO Use client to read operation limits

    private final OpcUaClient client;
    private final OpcUaSubscription subscription;

    public BatchSetMonitoringMode(ManagedSubscription subscription) {
        this(subscription.getClient(), subscription.getSubscription());
    }

    public BatchSetMonitoringMode(OpcUaClient client, OpcUaSubscription subscription) {
        this.client = client;
        this.subscription = subscription;
    }

    public int getServiceInvocationCount() {
        return serviceInvocationCount.get();
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

        MonitoringMode monitoringMode = itemsAndModes.get(0).getValue();

        List<UaMonitoredItem> items = itemsAndModes.stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        CompletableFuture<List<SetMonitoringModeResult>> resultsFuture =
            subscription.setMonitoringMode(monitoringMode, items).thenApply(
                statusCodes ->
                    statusCodes.stream()
                        .map(statusCode -> new SetMonitoringModeResult(StatusCode.GOOD, statusCode))
                        .collect(Collectors.toList())
            );

        return resultsFuture.exceptionally(ex -> {
            StatusCode serviceResult = UaException
                .extractStatusCode(ex)
                .orElse(new StatusCode(StatusCodes.Bad_UnexpectedError));

            SetMonitoringModeResult result = new SetMonitoringModeResult(serviceResult);

            return Collections.nCopies(items.size(), result);
        });
    }

    private static CompletableFuture<UInteger> readOperationLimit(OpcUaClient client) {
        CompletableFuture<VariableNode> nodeFuture = client.getAddressSpace().getVariableNode(
            Identifiers.Server_ServerCapabilities_OperationLimits_MaxMonitoredItemsPerCall
        );

        return nodeFuture.thenCompose(
            variableNode ->
                variableNode.getValue()
                    .thenApply(UInteger.class::cast)
                    .exceptionally(ex -> uint(1000))
        );
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
