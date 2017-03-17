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

import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateDetails;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResult;

public class PendingHistoryUpdate implements Pending<HistoryUpdateDetails, HistoryUpdateResult> {

    private final CompletableFuture<HistoryUpdateResult> future = new CompletableFuture<>();

    private final HistoryUpdateDetails details;

    public PendingHistoryUpdate(HistoryUpdateDetails details) {
        this.details = details;
    }

    @Override
    public HistoryUpdateDetails getInput() {
        return details;
    }

    @Override
    public CompletableFuture<HistoryUpdateResult> getFuture() {
        return future;
    }
}
