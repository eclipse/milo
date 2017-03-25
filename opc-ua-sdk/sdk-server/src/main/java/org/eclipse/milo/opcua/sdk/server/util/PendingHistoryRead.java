/*
 * Copyright (c) 2017 Ari Suutari
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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResult;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadValueId;

public class PendingHistoryRead implements Pending<HistoryReadValueId, HistoryReadResult> {

    private final CompletableFuture<HistoryReadResult> future = new CompletableFuture<>();

    private final HistoryReadValueId id;

    public PendingHistoryRead(HistoryReadValueId id) {
        this.id = id;
    }

    @Override
    public HistoryReadValueId getInput() {
        return id;
    }

    @Override
    public CompletableFuture<HistoryReadResult> getFuture() {
        return future;
    }
}
