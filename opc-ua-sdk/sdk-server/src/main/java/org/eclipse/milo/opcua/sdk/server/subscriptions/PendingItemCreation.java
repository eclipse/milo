/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateResult;

final class PendingItemCreation {

    private final CompletableFuture<MonitoredItemCreateResult> resultFuture = new CompletableFuture<>();

    private final MonitoredItemCreateRequest request;

    public PendingItemCreation(MonitoredItemCreateRequest request) {
        this.request = request;
    }

    public MonitoredItemCreateRequest getRequest() {
        return request;
    }

    public CompletableFuture<MonitoredItemCreateResult> getResultFuture() {
        return resultFuture;
    }

}
