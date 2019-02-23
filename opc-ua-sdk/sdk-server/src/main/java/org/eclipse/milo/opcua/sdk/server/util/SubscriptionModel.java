/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;
import org.eclipse.milo.opcua.sdk.server.DiagnosticsContext;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices;
import org.eclipse.milo.opcua.sdk.server.api.AttributeServices.ReadContext;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;

public class SubscriptionModel {

    private final Set<DataItem> itemSet = Collections.newSetFromMap(Maps.newConcurrentMap());

    private final List<ScheduledUpdate> schedule = Lists.newCopyOnWriteArrayList();

    private final ExecutorService executor;
    private final ScheduledExecutorService scheduler;
    private final ExecutionQueue executionQueue;

    private final OpcUaServer server;
    private final AttributeServices attributeServices;

    public SubscriptionModel(OpcUaServer server, AttributeServices attributeServices) {
        this.server = server;

        this.attributeServices = attributeServices;

        executor = server.getExecutorService();
        scheduler = server.getScheduledExecutorService();

        executionQueue = new ExecutionQueue(executor);
    }

    public void onDataItemsCreated(List<DataItem> items) {
        executionQueue.submit(() -> {
            itemSet.addAll(items);
            reschedule();
        });
    }

    public void onDataItemsModified(List<DataItem> items) {
        executionQueue.submit(this::reschedule);
    }

    public void onDataItemsDeleted(List<DataItem> items) {
        executionQueue.submit(() -> {
            itemSet.removeAll(items);
            reschedule();
        });
    }

    public void onMonitoringModeChanged(List<MonitoredItem> items) {
        executionQueue.submit(this::reschedule);
    }

    private void reschedule() {
        Map<Double, List<DataItem>> bySamplingInterval = itemSet.stream()
            .filter(DataItem::isSamplingEnabled)
            .collect(Collectors.groupingBy(DataItem::getSamplingInterval));

        List<ScheduledUpdate> updates = bySamplingInterval.keySet().stream()
            .map(samplingInterval -> {
                List<DataItem> items = bySamplingInterval.get(samplingInterval);

                return new ScheduledUpdate(samplingInterval, items);
            })
            .collect(Collectors.toList());

        schedule.forEach(ScheduledUpdate::cancel);
        schedule.clear();
        schedule.addAll(updates);
        schedule.forEach(scheduler::execute);
    }

    private class ScheduledUpdate implements Runnable {

        private volatile boolean cancelled = false;

        private final long samplingInterval;
        private final List<DataItem> items;

        private ScheduledUpdate(double samplingInterval, List<DataItem> items) {
            this.samplingInterval = DoubleMath.roundToLong(samplingInterval, RoundingMode.UP);
            this.items = items;
        }

        private void cancel() {
            cancelled = true;
        }

        @Override
        public void run() {
            List<PendingRead> pending = items.stream()
                .map(item -> new PendingRead(item.getReadValueId()))
                .collect(Collectors.toList());

            List<ReadValueId> ids = pending.stream()
                .map(PendingRead::getInput)
                .collect(Collectors.toList());

            CompletableFuture<List<DataValue>> future = new CompletableFuture<>();

            ReadContext context = new ReadContext(
                server, null, future, new DiagnosticsContext<>());

            future.thenAcceptAsync(values -> {
                Iterator<DataItem> ii = items.iterator();
                Iterator<DataValue> vi = values.iterator();

                while (ii.hasNext() && vi.hasNext()) {
                    DataItem item = ii.next();
                    DataValue value = vi.next();

                    TimestampsToReturn timestamps = item.getTimestampsToReturn();

                    if (timestamps != null) {
                        UInteger attributeId = item.getReadValueId().getAttributeId();

                        value = (AttributeId.Value.isEqual(attributeId)) ?
                            DataValue.derivedValue(value, timestamps) :
                            DataValue.derivedNonValue(value, timestamps);
                    }

                    item.setValue(value);
                }

                if (!cancelled) {
                    scheduler.schedule(this, samplingInterval, TimeUnit.MILLISECONDS);
                }
            }, executor);

            executor.execute(() -> attributeServices.read(context, 0d, TimestampsToReturn.Both, ids));
        }

    }

}
