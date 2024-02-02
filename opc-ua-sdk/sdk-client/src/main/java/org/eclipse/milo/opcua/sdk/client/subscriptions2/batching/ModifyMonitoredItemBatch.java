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

import java.util.List;
import java.util.concurrent.CompletionStage;

import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class ModifyMonitoredItemBatch {


    public void add(OpcUaMonitoredItem item) {

    }

    public List<ModifyMonitoredItemResult> execute() {
        return null;
    }

    public CompletionStage<List<ModifyMonitoredItemResult>> executeAsync() {
        return null;
    }

    public static class ModifyMonitoredItemResult {

        private final StatusCode serviceResult;
        private final StatusCode operationResult;

        public ModifyMonitoredItemResult(StatusCode serviceResult, StatusCode operationResult) {
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
