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
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscription;
import org.eclipse.milo.opcua.sdk.server.util.GroupMapCollate;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class BatchModifyMonitoredItems {

    private final Map<OpcUaMonitoredItem, BatchModifyParametersBuilder> buildersByItem =
        Collections.synchronizedMap(new LinkedHashMap<>());

    private final ListMultimap<OpcUaMonitoredItem, CompletableFuture<ModifyResult>> futuresByItem =
        Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    private final List<CompletableFuture<ModifyResult>> resultFutures =
        Collections.synchronizedList(new ArrayList<>());

    private final AtomicInteger modifyServiceCount = new AtomicInteger(0);

    private final OpcUaClient client;
    private final OpcUaSubscription subscription;

    public BatchModifyMonitoredItems(ManagedSubscription subscription) {
        this(subscription.getClient(), subscription.getSubscription());
    }

    public BatchModifyMonitoredItems(OpcUaClient client, OpcUaSubscription subscription) {
        this.client = client;
        this.subscription = subscription;
    }

    /**
     * Get the number of service invocations that were needed to execute this batch.
     *
     * @return the number of service invocations that were needed to execute this batch.
     */
    public int getServiceInvocationCount() {
        return modifyServiceCount.get();
    }

    /**
     * Add an {@link OpcUaMonitoredItem} to be modified as part of a batch.
     *
     * @param monitoredItem   the {@link OpcUaMonitoredItem} to modify.
     * @param builderConsumer a consumer that will set changes on a {@link BatchModifyParametersBuilder}.
     * @return a {@link CompletableFuture} that completes successfully
     */
    public CompletableFuture<ModifyResult> add(
        OpcUaMonitoredItem monitoredItem,
        Consumer<BatchModifyParametersBuilder> builderConsumer
    ) {

        BatchModifyParametersBuilder builder = buildersByItem.computeIfAbsent(
            monitoredItem,
            BatchModifyMonitoredItems::parametersBuilderFromItem
        );

        builderConsumer.accept(builder);

        CompletableFuture<ModifyResult> future = new CompletableFuture<>();
        futuresByItem.put(monitoredItem, future);
        resultFutures.add(future);
        return future;
    }

    public List<ModifyResult> execute() throws UaException {
        try {
            return executeAsync().get();
        } catch (InterruptedException e) {
            throw new UaException(StatusCodes.Bad_UnexpectedError, e);
        } catch (ExecutionException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<List<ModifyResult>> executeAsync() {
        return readOperationLimit(client).thenCompose(this::executeAsync);
    }

    private CompletableFuture<List<ModifyResult>> executeAsync(UInteger operationLimit) {
        List<BatchModifyParameters> allMonitoringParameters = buildersByItem.values().stream()
            .map(BatchModifyParametersBuilder::build)
            .collect(Collectors.toList());

        CompletableFuture<List<ModifyResult>> resultsFuture = GroupMapCollate.groupMapCollate(
            allMonitoringParameters,
            parametersItem -> parametersItem.timestamps,
            (TimestampsToReturn timestampsKey) -> parameterGroup -> {
                List<MonitoredItemModifyRequest> itemsToModify = parameterGroup.stream()
                    .map(parameters -> new MonitoredItemModifyRequest(
                        parameters.item.getMonitoredItemId(),
                        new MonitoringParameters(
                            parameters.clientHandle,
                            parameters.samplingInterval,
                            parameters.filter,
                            parameters.queueSize,
                            parameters.discardOldest
                        )
                    ))
                    .collect(Collectors.toList());

                List<CompletableFuture<List<ModifyResult>>> partitionFutures =
                    Lists.partition(itemsToModify, operationLimit.intValue())
                        .stream()
                        .map(partition -> modifyItems(timestampsKey, partition))
                        .collect(Collectors.toList());

                return FutureUtils.flatSequence(partitionFutures);
            }
        );

        return resultsFuture.thenCompose(results -> {
            List<OpcUaMonitoredItem> items = new ArrayList<>(buildersByItem.keySet());

            assert items.size() == results.size();

            for (int i = 0; i < items.size(); i++) {
                OpcUaMonitoredItem item = items.get(i);
                ModifyResult result = results.get(i);

                List<CompletableFuture<ModifyResult>> futures;
                synchronized (futuresByItem) {
                    futures = new ArrayList<>(futuresByItem.get(item));
                }
                futures.forEach(f -> f.complete(result));
            }

            return FutureUtils.sequence(resultFutures);
        });
    }

    /**
     * Make the ModifyMonitoredItems service call for a partition of MonitoredItemModifyRequest.
     * <p>
     * The returned CompletableFuture always completes successfully.
     * <p>
     * The corresponding operation result for each item to modify may be either the StatusCode from the service call if
     * the call failed or the StatusCode from the operation if the service call succeeded.
     *
     * @param timestamps    the {@link TimestampsToReturn} to use in the  modify call.
     * @param itemsToModify the {@link MonitoredItemModifyRequest} for the items to modify.
     * @return a {@link CompletableFuture} that is always completed successfully.
     */
    private CompletableFuture<List<ModifyResult>> modifyItems(
        TimestampsToReturn timestamps,
        List<MonitoredItemModifyRequest> itemsToModify
    ) {

        modifyServiceCount.incrementAndGet();

        CompletableFuture<List<StatusCode>> modifyResults =
            subscription.modifyMonitoredItems(timestamps, itemsToModify);

        CompletableFuture<List<ModifyResult>> batchItemResults =
            modifyResults.thenApply(statusCodes ->
                statusCodes.stream()
                    .map(statusCode -> new ModifyResult(StatusCode.GOOD, statusCode))
                    .collect(Collectors.toList())
            );

        return batchItemResults.exceptionally(ex -> {
            StatusCode serviceResult = UaException
                .extractStatusCode(ex)
                .orElse(new StatusCode(StatusCodes.Bad_UnexpectedError));


            ModifyResult result = new ModifyResult(serviceResult, null);

            return Collections.nCopies(itemsToModify.size(), result);
        });
    }

    private static BatchModifyParametersBuilder parametersBuilderFromItem(OpcUaMonitoredItem item) {
        return new BatchModifyParametersBuilder(item)
            .setTimestamps(item.getTimestamps())
            .setClientHandle(item.getClientHandle())
            .setSamplingInterval(item.getRevisedSamplingInterval())
            .setFilter(item.getMonitoringFilter())
            .setQueueSize(item.getRevisedQueueSize())
            .setDiscardOldest(item.getDiscardOldest());
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

    public static class ModifyResult implements BatchItemResult<StatusCode> {

        private final StatusCode serviceResult;
        private final StatusCode operationResult;

        ModifyResult(StatusCode serviceResult, @Nullable StatusCode operationResult) {
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

    public static class BatchModifyParameters {

        final OpcUaMonitoredItem item;
        final TimestampsToReturn timestamps;
        final UInteger clientHandle;
        final Double samplingInterval;
        final ExtensionObject filter;
        final UInteger queueSize;
        final Boolean discardOldest;

        BatchModifyParameters(
            OpcUaMonitoredItem item, TimestampsToReturn timestamps,
            UInteger clientHandle,
            Double samplingInterval,
            ExtensionObject filter,
            UInteger queueSize,
            Boolean discardOldest
        ) {

            this.item = item;
            this.timestamps = timestamps;
            this.clientHandle = clientHandle;
            this.samplingInterval = samplingInterval;
            this.filter = filter;
            this.queueSize = queueSize;
            this.discardOldest = discardOldest;
        }
    }

    public static class BatchModifyParametersBuilder {

        private TimestampsToReturn timestamps;
        private UInteger clientHandle;
        private Double samplingInterval;
        private ExtensionObject filter;
        private UInteger queueSize;
        private Boolean discardOldest;

        private final OpcUaMonitoredItem item;

        public BatchModifyParametersBuilder(OpcUaMonitoredItem item) {
            this.item = item;
        }

        public BatchModifyParametersBuilder setTimestamps(TimestampsToReturn timestamps) {
            this.timestamps = timestamps;
            return this;
        }

        public BatchModifyParametersBuilder setClientHandle(UInteger clientHandle) {
            this.clientHandle = clientHandle;
            return this;
        }

        public BatchModifyParametersBuilder setSamplingInterval(double samplingInterval) {
            this.samplingInterval = samplingInterval;
            return this;
        }

        public BatchModifyParametersBuilder setFilter(ExtensionObject filter) {
            this.filter = filter;
            return this;
        }

        public BatchModifyParametersBuilder setQueueSize(UInteger queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public BatchModifyParametersBuilder setDiscardOldest(boolean discardOldest) {
            this.discardOldest = discardOldest;
            return this;
        }

        BatchModifyParameters build() {
            return new BatchModifyParameters(
                item, timestamps,
                clientHandle,
                samplingInterval,
                filter,
                queueSize,
                discardOldest
            );
        }

    }

}
