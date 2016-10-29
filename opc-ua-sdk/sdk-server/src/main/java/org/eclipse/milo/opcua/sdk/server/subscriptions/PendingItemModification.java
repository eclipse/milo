/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
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
