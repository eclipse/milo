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

package org.eclipse.milo.opcua.stack.client.handlers;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;

public class UaRequestFuture {

    private final UaRequestMessage request;
    private final CompletableFuture<UaResponseMessage> future;

    public UaRequestFuture(UaRequestMessage request) {
        this(request, new CompletableFuture<>());
    }

    public UaRequestFuture(UaRequestMessage request, CompletableFuture<UaResponseMessage> future) {
        this.request = request;
        this.future = future;
    }

    public UaRequestMessage getRequest() {
        return request;
    }

    public CompletableFuture<UaResponseMessage> getFuture() {
        return future;
    }

}
