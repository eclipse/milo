/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2.batching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.OpcUaSubscription;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;

import static java.util.Objects.requireNonNull;

public class CreateMonitoredItemBatch {

    private final List<OpcUaMonitoredItem> monitoredItems = new ArrayList<>();

    private final OpcUaSubscription subscription;

    public CreateMonitoredItemBatch(OpcUaSubscription subscription) {
        this.subscription = subscription;
    }

    public void add(OpcUaMonitoredItem item) {
        monitoredItems.add(item);
    }

    public List<CreateMonitoredItemResult> execute() throws UaException {
        OpcUaClient client = subscription.getClient();

        UInteger subscriptionId = subscription.getSubscriptionId()
            .orElseThrow(() -> new UaException(StatusCodes.Bad_InvalidState));

        var itemsToCreate = new ArrayList<MonitoredItemCreateRequest>();

        CreateMonitoredItemsResponse response = client.createMonitoredItems(
            subscriptionId,
            TimestampsToReturn.Both,
            itemsToCreate
        );

        MonitoredItemCreateResult[] results = requireNonNull(response.getResults());

        return null;
    }

    public CompletionStage<List<CreateMonitoredItemResult>> executeAsync() {
        return null;
    }

    public static class CreateMonitoredItemResult {

        private final StatusCode serviceResult;
        private final StatusCode operationResult;

        public CreateMonitoredItemResult(StatusCode serviceResult, StatusCode operationResult) {
            this.serviceResult = serviceResult;
            this.operationResult = operationResult;
        }

        public StatusCode getServiceResult() {
            return serviceResult;
        }

        public StatusCode getOperationResult() {
            return operationResult;
        }

    }

}
