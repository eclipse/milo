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

import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemModifyResult;

final class PendingItemModification {

    private final CompletableFuture<MonitoredItemModifyResult> resultFuture = new CompletableFuture<>();

    private final MonitoredItemModifyRequest request;

    public PendingItemModification(MonitoredItemModifyRequest request) {
        this.request = request;
    }

    public MonitoredItemModifyRequest getRequest() {
        return request;
    }

    public CompletableFuture<MonitoredItemModifyResult> getResultFuture() {
        return resultFuture;
    }

}
