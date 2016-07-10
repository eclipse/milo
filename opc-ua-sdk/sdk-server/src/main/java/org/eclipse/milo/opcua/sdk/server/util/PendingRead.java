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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public class PendingRead implements Pending<ReadValueId, DataValue> {

    private final CompletableFuture<DataValue> future = new CompletableFuture<>();

    private final ReadValueId id;

    public PendingRead(ReadValueId id) {
        this.id = id;
    }

    @Override
    public ReadValueId getInput() {
        return id;
    }

    @Override
    public CompletableFuture<DataValue> getFuture() {
        return future;
    }
}
