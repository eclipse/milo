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

import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;

public class PendingCall implements Pending<CallMethodRequest, CallMethodResult> {

    private final CompletableFuture<CallMethodResult> future = new CompletableFuture<>();

    private final CallMethodRequest request;

    public PendingCall(CallMethodRequest request) {
        this.request = request;
    }

    @Override
    public CompletableFuture<CallMethodResult> getFuture() {
        return future;
    }

    @Override
    public CallMethodRequest getInput() {
        return request;
    }

}
