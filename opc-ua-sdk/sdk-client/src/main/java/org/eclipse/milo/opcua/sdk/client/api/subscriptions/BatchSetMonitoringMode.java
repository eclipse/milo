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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;

import static java.util.Collections.emptyList;

public class BatchSetMonitoringMode {

    private final List<CompletableFuture<Unit>> futures = new ArrayList<>();

    private final LinkedHashMap<ManagedDataItem, MonitoringMode> monitoringModes = new LinkedHashMap<>();

    private final ManagedSubscription subscription;

    public BatchSetMonitoringMode(ManagedSubscription subscription) {
        this.subscription = subscription;
    }

    public CompletableFuture<Unit> add(ManagedDataItem dataItem, MonitoringMode monitoringMode) {
        monitoringModes.put(dataItem, monitoringMode);

        CompletableFuture<Unit> future = new CompletableFuture<>();
        futures.add(future);
        return future;
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
        Map<MonitoringMode, List<Map.Entry<ManagedDataItem, MonitoringMode>>> byMonitoringMode =
            monitoringModes.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue));

        List<ManagedDataItem> itemsToDisable = byMonitoringMode.getOrDefault(MonitoringMode.Disabled, emptyList())
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        List<ManagedDataItem> itemsToSample = byMonitoringMode.getOrDefault(MonitoringMode.Sampling, emptyList())
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        List<ManagedDataItem> itemsToReport = byMonitoringMode.getOrDefault(MonitoringMode.Reporting, emptyList())
            .stream()
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        CompletableFuture<List<StatusCode>> disableFuture;
        CompletableFuture<List<StatusCode>> samplingFuture;
        CompletableFuture<List<StatusCode>> reportingFuture;

        if (itemsToDisable.isEmpty()) {
            disableFuture = CompletableFuture.completedFuture(emptyList());
        } else {
            disableFuture = subscription.getSubscription().setMonitoringMode(
                MonitoringMode.Disabled,
                itemsToDisable.stream()
                    .map(ManagedDataItem::getMonitoredItem)
                    .collect(Collectors.toList())
            );
        }

        if (itemsToSample.isEmpty()) {
            samplingFuture = CompletableFuture.completedFuture(emptyList());
        } else {
            samplingFuture = subscription.getSubscription().setMonitoringMode(
                MonitoringMode.Sampling,
                itemsToSample.stream()
                    .map(ManagedDataItem::getMonitoredItem)
                    .collect(Collectors.toList())
            );
        }

        if (itemsToReport.isEmpty()) {
            reportingFuture = CompletableFuture.completedFuture(emptyList());
        } else {
            reportingFuture = subscription.getSubscription().setMonitoringMode(
                MonitoringMode.Reporting,
                itemsToReport.stream()
                    .map(ManagedDataItem::getMonitoredItem)
                    .collect(Collectors.toList())
            );
        }

        return FutureUtils.sequence(disableFuture, samplingFuture, reportingFuture).thenApply(v -> Unit.VALUE);
    }

}
